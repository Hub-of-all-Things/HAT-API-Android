/*
 * *
 *  * Copyright (C) 2018-2019 DataSwift Ltd
 *  *
 *  * SPDX-License-Identifier: MPL2
 *  *
 *  * This file is part of the Hub of All Things project (HAT).
 *  *
 *  * This Source Code Form is subject to the terms of the Mozilla Public
 *  * License, v. 2.0. If a copy of the MPL was not distributed with this
 *  * file, You can obtain one at http://mozilla.org/MPL/2.0/
 *
 */

package com.hubofallthings.android.hatApi.services

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
import com.hubofallthings.android.hatApi.HATError
import com.hubofallthings.android.hatApi.managers.HATNetworkManager
import com.hubofallthings.android.hatApi.managers.toKotlinObject
import com.hubofallthings.android.hatApi.objects.fileUploadObject.FileUploadObject
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import android.content.ContentResolver
import android.net.Uri


class HATFileService {
    private val TAG = HATFileService::class.java.simpleName
    // MARK: - Search files

    /**
    Searches and returns the files we want

    - parameter token: The authorisation token to authenticate with the hat
    - parameter userDomain: The user's HAT domain
    - parameter status: The status of the file, Completed or Deleted. Default value is Completed
    - parameter name: The name of the file, default is ""
    - parameter tags: The tags of the files, default is [""]
    - parameter successCallback: An @escaping ([FileUploadObject]) -> Void function to execute when the server has returned the files we were looking for
    - parameter errorCallBack: An @escaping (HATError) -> Void to execute when something went wrong
     */
    fun searchFiles(userDomain: String, token: String, status: String? = "Completed", name: String = "", tags: Array<String>? = arrayOf(""), successCallback: (List<FileUploadObject>, String?) -> Unit, errorCallBack: (HATError) -> Unit) {

        val url = "https://$userDomain/api/v2.6/files/search"
        val headers = mapOf("X-Auth-Token" to token)
        val parameters = mapOf("source" to "Android", "name" to name, "status" to mapOf("status" to status, "size" to 0))
//        HATNetworkManager().postRequest()
    }
    // MARK: - Delete File

    /**
    Deletes a file, with the specified ID, from the server

    - parameter fileID: The ID of the file to delete
    - parameter token: The authorisation token to authenticate with the hat
    - parameter userDomain: The user's HAT domain
    - parameter successCallback: An @escaping (Bool) -> Void function to execute when the file has been deleted
    - parameter errorCallBack: An @escaping (HATError) -> Void to execute when something went wrong
     */
    fun deleteFile(fileID: String, token: String, userDomain: String, successCallback: (Boolean, String?) -> Unit, errorCallBack: (HATError) -> Unit) {

//        val url = "https://$userDomain/api/v2.6/files/file/$fileID"

    }

    // MARK: - Upload File to hat

    /**
    Uploads a file to hat

    - parameter fileName: The file name of the file to be uploaded
    - parameter token: The owner's token
    - parameter userDomain: The user hat domain
    - parameter completion: A function to execute on success, returning the object returned from the server
    - parameter errorCallback: A function to execute on failure, returning an error
     */
    fun uploadFileToHAT(
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
                    if (!result.component1().isNullOrEmpty()) {
                        if (result.component1() != null) {
                            val jsonString = result.component1()
                            doAsync {
                                val fileObject = jsonString?.toKotlinObject<FileUploadObject>()
                                uiThread {
                                    if (fileObject != null) {
                                        completion(fileObject, "")
                                    }
                                }
                            }
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
            type: String = "image/png",
            contentUrl: String,
            completion: (String) -> Unit,
            errorCallback: (FuelError) -> Unit) {

        Log.i("mime-type", type)
        FuelManager.instance.baseHeaders = mapOf("x-amz-server-side-encryption" to "AES256", "Content-Type" to type)
        Fuel.put(contentUrl).body(image.readBytes()).responseString { _, response, result ->
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

    // MARK: - Complete Upload File to hat

    /**
    Completes an upload of a file to hat

    - parameter fileID: The fileID of the file uploaded to hat
    - parameter token: The owner's token
    - parameter tags: An array of strings having the tags to add to the file
    - parameter userDomain: The user hat domain
    - parameter completion: A function to execute on success, returning the object returned from the server
    - parameter errorCallback: A function to execute on failure, returning an error
     */
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
                        val jsonString = result.component1()
                        doAsync {
                            val fileObject = jsonString?.toKotlinObject<FileUploadObject>()
                            uiThread {
                                if (fileObject != null) {
                                    completion(fileObject, "")
                                }
                            }
                        }
                    }
                }
                else -> {
                }
            }
        }
    }
    // MARK: - Change File to Public or Private

    /**
    Makes an already uploaded file public

    - parameter fileID: The ID of the file to change
    - parameter token: The authorisation token to authenticate with the hat
    - parameter userDomain: The user's HAT domain
    - parameter successCallback: An @escaping (Bool) -> Void function to execute when the file has been made public
    - parameter errorCallBack: An @escaping (HATError) -> Void to execute when something went wrong
     */
    fun makeFilePublic(
            fileID: String,
            token: String,
            userDomain: String,
            completion: ((Boolean) -> Unit)?,
            errorCallBack: ((FuelError) -> Unit)?) {
        // create the url
        val uploadURL = "https://$userDomain/api/v2.6/files/allowAccessPublic/$fileID"
        FuelManager.instance.baseHeaders = mapOf("Content-Type" to "application/json", "x-auth-token" to token)
        Fuel.get(uploadURL).responseString { _, response, result ->
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

    /**
    Makes an already uploaded file private

    - parameter fileID: The ID of the file to change
    - parameter token: The authorisation token to authenticate with the hat
    - parameter userDomain: The user's HAT domain
    - parameter successCallback: An @escaping (Bool) -> Void function to execute when the file has been made private
    - parameter errorCallBack: An @escaping (HATError) -> Void to execute when something went wrong
     */
    fun makeFilePrivate(fileID: String, token: String, userDomain: String, successCallback: (Boolean) -> Unit, errorCallBack: (HATError) -> Unit) {
        val url: String = "https://$userDomain/api/v2.6/files/restrictAccessPublic/$fileID"
        FuelManager.instance.baseHeaders = mapOf("Content-Type" to "application/json", "x-auth-token" to token)
        Fuel.get(url).responseString { _, response, result ->
            when (result) {
                is Result.Failure -> {
                    val e = HATError()
                    e.errorMessage = response.responseMessage
                    e.errorCode = response.statusCode
                    errorCallBack(e)

                }
                is Result.Success -> {
                    if (response.statusCode == 200) {
                        successCallback(true)
                    }
                }
                else -> {
                }
            }

        }

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

    // MARK: - Upload file to hat wrapper

    /**
    Uploads a file to HAT

    - parameter token: The user's token
    - parameter userDomain: The user's domain
    - parameter fileToUpload: The image to upload
    - parameter tags: The tags to attach to the image
    - parameter name: The name of the file, defauls it rumpelPhoto
    - parameter progressUpdater: A function to execute on the progress of the upload is moving forward
    - parameter completion: A function to execute on success
    - parameter errorCallBack: A Function to execute on failure
     */
    fun uploadFileToHATWrapper(
            token: String,
            userDomain: String,
            fileToUpload: File,
            name: String,
            type: String,
            completion: ((FileUploadObject, String) -> Unit)?,
            errorCallBack: ((FuelError) -> Unit)?
    ) {

        uploadFileToHAT(name, token, userDomain, { fileUploadObject, _ ->
            uploadFile(fileToUpload, type, fileUploadObject.contentUrl, { _ ->
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
