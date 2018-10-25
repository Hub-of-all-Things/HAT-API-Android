package com.hubofallthings.hatappandroid.services

import android.util.Log
import com.fasterxml.jackson.annotation.JsonSetter
import com.fasterxml.jackson.annotation.Nulls
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.result.Result
import com.hubofallthings.hatappandroid.`object`.fileuploadobject.FileUploadObject
import java.io.File
import java.io.FileInputStream
import java.io.IOException

class HATFileService {
    private val TAG = HATFileService::class.java.simpleName

    private fun uploadFileToHAT(
        fileName: String,
        token: String,
        userDomain: String,
        completion: (FileUploadObject, String) -> Unit,
        errorCallback: (FuelError) -> Unit
    ) {

        // create the url
        val uploadURL = "https://$userDomain/api/v2.6/files/upload"
        val parameters = createFileUploadingJSONFrom(fileName)

        val mapper = jacksonObjectMapper()
        val parametersJson = mapper.writeValueAsString(parameters)

        FuelManager.instance.baseHeaders = mapOf("Content-Type" to "application/json", "x-auth-token" to token)
        Fuel.post(uploadURL).body(parametersJson).responseString { _, response, result ->
            when (result) {
                is Result.Failure -> {
                    Log.i(TAG, "failed")
                    Log.i(TAG, response.responseMessage + response.statusCode.toString())
                    errorCallback(result.error)
                }
                is Result.Success -> {
                    Log.i(TAG, "success")
                    Log.i(TAG, response.responseMessage + response.statusCode.toString())
                    if (! result.component1().isNullOrEmpty()) {
                        if (result.component1() != null) {
                            val fileObject = jsonToFileUpload(result.component1() !!)
                            completion(fileObject, "")
                        }
                    }
                }
                else -> {
                }
            }
        }
    }

    private fun uploadFile(
        image: File,
        contentUrl: String,
        completion: (String) -> Unit,
        errorCallback: (FuelError) -> Unit) {

        FuelManager.instance.baseHeaders = mapOf("x-amz-server-side-encryption" to "AES256")
        Fuel.put(contentUrl).body(fullyReadFileToBytes(image)).responseString{_, response, result ->
            when (result) {
                is Result.Failure -> {
                    Log.i(TAG, "Uploadfailed")
                    Log.i(TAG, response.responseMessage + response.statusCode.toString())
                    errorCallback(result.error)
                }
                is Result.Success -> {
                    Log.i(TAG, "Uploadsuccess")
                    Log.i(TAG, response.responseMessage + response.statusCode.toString())
                    if (response.statusCode == 200) {
                        completion("")
                    }
                }
                else -> {
                }
            }
        }

    }

    @Throws(IOException::class)
    private fun fullyReadFileToBytes(f: File): ByteArray {
        val size = f.length().toInt()
        val bytes = ByteArray(size)
        val tmpBuff = ByteArray(size)
        val fis = FileInputStream(f)
        try {
            var read = fis.read(bytes, 0, size)
            if (read < size) {
                var remain = size - read
                while (remain > 0) {
                    read = fis.read(tmpBuff, 0, remain)
                    System.arraycopy(tmpBuff, 0, bytes, size - remain, read)
                    remain -= read
                }
            }
        } catch (e: IOException) {
            throw e
        } finally {
            fis.close()
        }

        return bytes
    }
    private fun completeUploadFileToHAT(
        fileID: String,
        token: String,
        userDomain: String,
        completion: (FileUploadObject, String?) -> Unit,
        errorCallback: (FuelError) -> Unit
    ) {
        // create the url
        val uploadURL = "https://$userDomain/api/v2.6/files/file/$fileID/complete"
        FuelManager.instance.baseHeaders = mapOf("Content-Type" to "application/json", "x-auth-token" to token)

        Fuel.put(uploadURL).responseString { _, response, result ->
            when (result) {
                is Result.Failure -> {
                    Log.i(TAG, "Completion failed")
                    Log.i(TAG, response.responseMessage + response.statusCode.toString())
                    errorCallback(result.error)
                }
                is Result.Success -> {
                    Log.i(TAG, "Completion success")
                    Log.i(TAG, response.responseMessage + response.statusCode.toString())
                    if (response.statusCode == 200) {
                        val fileObject = jsonToFileUpload(result.component1() !!)
                        completion(fileObject, "")
                    }
                }
                else -> {
                }
            }
        }
    }
        fun makeFilePublic(
            fileID: String,
            token: String,
            userDomain: String,
            completion: ((Boolean) -> Unit)?,
            errorCallBack: ((FuelError) -> Unit)?) {
            // create the url
            val uploadURL = "https://$userDomain/api/v2.6/files/allowAccessPublic/$fileID"
            FuelManager.instance.baseHeaders = mapOf("Content-Type" to "application/json", "x-auth-token" to token)
            Fuel.get(uploadURL).responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        Log.i(TAG, "Public failed")
                        Log.i(TAG, response.responseMessage + response.statusCode.toString())
                        if (errorCallBack != null) {
                            errorCallBack(result.error)
                        }
                    }
                    is Result.Success -> {
                        Log.i(TAG, "Public success")
                        Log.i(TAG, response.responseMessage + response.statusCode.toString())
                        if (response.statusCode == 200) {
                            if (completion != null) {
                                completion(true)
                            }
                        }
                    }
                    else -> {
                    }
                }

            }
        }

        private fun jsonToFileUpload(json: String): FileUploadObject {
            val mapper = jacksonObjectMapper()
            mapper.configOverride(String::class.java).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.FAIL))
            mapper.setDefaultSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY))
            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            val fileObject = mapper.readValue<FileUploadObject>(json)
            return fileObject
        }
        /**
        Creates the json file to purchase a HAT

        - parameter fileName: The file name of the photo
        - parameter tags: The tags attached to the photo
        - returns: A Map <String, Any>
         */
        private fun createFileUploadingJSONFrom(fileName: String): Map<String, Any> {
            // the final JSON file to be returned
            return mapOf("name" to fileName, "source" to "rumpel", "tags" to listOf("android", "image/jpeg"))
        }

        fun uploadFileToHATWrapper(
            token: String,
            userDomain: String,
            fileToUpload: File,
            name: String,
            completion: ((FileUploadObject, String) -> Unit)?,
            errorCallBack: ((FuelError) -> Unit)?
        ) {

            uploadFileToHAT(name, token, userDomain, { fileUploadObject, _ ->
                uploadFile(fileToUpload, fileUploadObject.contentUrl, { _ ->
                    completeUploadFileToHAT(
                        fileUploadObject.fileId,
                        token,
                        userDomain,
                        { uploadedFile, renewedUserToken ->
                            if (renewedUserToken != null) {
                                if (completion != null) {
                                    completion(uploadedFile, renewedUserToken)
                                }
                            }
                        },
                        { errorResult ->
                            if (errorCallBack != null) {
                                errorCallBack(errorResult)
                            }
                        })
                }, { errorResult ->
                    if (errorCallBack != null) {
                        errorCallBack(errorResult)
                    }
                }
                )
            }, { errorResult ->
                if (errorCallBack != null) {
                    errorCallBack(errorResult)
                }
            }
            )
        }
    }
