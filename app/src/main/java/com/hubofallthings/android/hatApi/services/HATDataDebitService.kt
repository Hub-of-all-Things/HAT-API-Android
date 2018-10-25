package com.hubofallthings.android.hatApi.services

import com.hubofallthings.android.hatApi.HATError
import com.hubofallthings.android.hatApi.managers.HATNetworkManager
import com.hubofallthings.android.hatApi.managers.HATParserManager
import com.hubofallthings.android.hatApi.managers.ResultType
import com.hubofallthings.android.hatApi.managers.toKotlinObject
import com.hubofallthings.android.hatApi.objects.datadebits.HATDataDebitObject
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

interface DataDebitService{
    fun getAvailableDataDebits(userToken : String, userDomain : String, completion: ((List<HATDataDebitObject>?, String?) -> Unit), failCallBack: ((HATError) -> Unit))
    fun disableDataDebit(userToken : String, userDomain : String, dataDebitID: String, completion: ((HATDataDebitObject?, String?) -> Unit), failCallBack: ((HATError) -> Unit))
}
class HATDataDebitService: DataDebitService{

    // MARK: Get Data Debits

    /**
    Gets the available data debits for the user

    - parameter userToken: A String representing the user's token
    - parameter userDomain: A String representing the user's domain
    - parameter succesfulCallBack: A function of type (List<HATDataDebitObject>) -> Unit, executed on a successful result
    - parameter failCallBack: A function of type (DataPlugError) -> Void, executed on an unsuccessful result
     */
    override fun getAvailableDataDebits(userToken: String, userDomain: String, completion: (List<HATDataDebitObject>?, String?) -> Unit, failCallBack: (HATError) -> Unit) {
        val url = "https://$userDomain/api/v2.6/data-debit"
        val headers = mapOf("x-auth-token" to userToken)

        HATNetworkManager().getRequest(
                url,
                null,
                headers) { r ->
            when(r){
                ResultType.IsSuccess -> {
                    if (r.statusCode != 401) {
                        val json = r.json!!.content
                        doAsync {
                            val dataDebitObject = HATParserManager().jsonToObjectList(json, HATDataDebitObject::class.java)
                            uiThread{
                                completion(dataDebitObject,r.token)
                            }
                        }
                    }
                }
                ResultType.HasFailed -> {
                    val error = HATError()
                    error.errorCode = r.statusCode
                    error.errorMessage = r.resultString
                    failCallBack(error)
                }
                null -> {}
            }
        }    }
    // MARK: - Disable data debit

    /**
    Disables the specified data debit

    - parameter userToken: A String representing the user's token
    - parameter userDomain: A String representing the user's domain
    - parameter dataDebitID: A String representing the data debit id to disable
    - parameter succesfulCallBack: A function of type (List<HATDataDebitObject>) -> Unit, executed on a successful result
    - parameter failCallBack: A function of type (DataPlugError) -> Void, executed on an unsuccessful result
     */
    override fun disableDataDebit(userToken: String, userDomain: String, dataDebitID: String, completion: (HATDataDebitObject?, String?) -> Unit, failCallBack: (HATError) -> Unit) {
        val url = "https://$userDomain/api/v2.6/data-debit/$dataDebitID/disable?atPeriodEnd=true"
        val headers = mapOf("x-auth-token" to userToken)

        HATNetworkManager().getRequest(
                url,
                null,
                headers) { r ->
            when(r){
                ResultType.IsSuccess -> {
                    if (r.statusCode != 401) {
                        val json = r.json!!.content
                        doAsync {
                            val dataDebit = json.toKotlinObject<HATDataDebitObject?>()
                            uiThread{
                                completion(dataDebit,r.token)
                            }
                        }
                    }
                }
                ResultType.HasFailed -> {
                    val error = HATError()
                    error.errorCode = r.statusCode
                    error.errorMessage = r.resultString
                    failCallBack(error)
                }
                null -> {}
            }
        }
    }
}