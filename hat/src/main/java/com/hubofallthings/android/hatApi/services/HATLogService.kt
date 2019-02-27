package com.hubofallthings.android.hatApi.services

import android.os.UserManager
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.kittinunf.fuel.core.FuelManager
import com.hubofallthings.android.hatApi.HATError
import com.hubofallthings.android.hatApi.managers.HATNetworkManager
import com.hubofallthings.android.hatApi.objects.log.LogModel
import com.hubofallthings.android.hatApi.objects.purchase.PurchaseObject

class HATLogService{
    fun postLogToHAT(userDomain: String, userToken: String, logModel: LogModel, successfulCallBack: (String, String?) -> Unit, failCallBack:  (HATError) -> Unit){
        val url =  "https://$userDomain/api/v2.6/report-frontend-action"
        val mapper = jacksonObjectMapper()
        val logJson = mapper.writeValueAsString(logModel)
        val headers = mapOf("Content-Type" to "application/json","x-auth-token" to userToken)

        HATNetworkManager().postRequest(url,logJson,headers){
            if(it?.statusCode == 200){
                successfulCallBack("result ok" , "")
            } else {
                val e = HATError()
                e.errorMessage = it?.resultString
                e.errorCode = it?.statusCode
                failCallBack(e)
            }
        }
    }
}