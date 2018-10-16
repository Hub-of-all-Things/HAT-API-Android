package com.hubofallthings.android.hatApi

import android.util.Log
import com.hubofallthings.android.hatApi.services.HATExternalAppsService
import com.hubofallthings.android.hatApi.services.HATFeedService
import com.hubofallthings.android.hatApi.objects.feed.HATFeedObject
import com.hubofallthings.hatappandroid.`object`.externalapps.HATExternalAppsObject
import org.junit.Test

class HATTestAsync{
    val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxLU5XVG9kZGVESXFIVmx6eTBHMDFWTnNkNW9cL2pXWVRvaERQbWRPcFBjaFA4enZjZG56N0pKd1wvbWEzMk5UeEdcL0JGdTJKS29yZ3BWRUVNY2lia2NaWlQyUytLN1dxTlIwSmtOST0iLCJyZXNvdXJjZSI6InRlc3RpbmcuaHViYXQubmV0IiwiYWNjZXNzU2NvcGUiOiJvd25lciIsImlzcyI6InRlc3RpbmcuaHViYXQubmV0IiwiZXhwIjoxNTQyMjEyODc2LCJpYXQiOjE1Mzk2MjA4NzYsImp0aSI6IjU2YjUzYmU3YjgxNjdiOGYwNWEwNDYxMjlhM2EwYTJkMzk0OTZkODFjYjQ2MTQ2ZjA3MWI5MTI3YTE4ODkxOWI4YTYwODg1N2NkZWU2ZmY1ZmIzMmRmYzdkNWZkNzJiZmRmZDFlYTc4ZWQ0OGJjOTc1ZjRjM2RlZWFiMzllOWVmYTc2MDM5ZDJlZTNhMzY1MjhlMWY4N2JhODlkODk5MjdlMTc5MWJlNzkwOTZlOGE2NDMwNDdlNWJjNjBhNTE0YThjY2YzZGU5NDUyMDdlMTc4MTdkM2Y1ZGJlZDliNTAxMDkwMDExNTAzN2U5NWZmNzhmODI0MmFhN2FiYzIzMDQifQ.Akbm6CoeHfnN7lklH0BXmLhnEXtuvCgr3ZxO0tBrUkzExbI9zHiMK7PaxQHXIl01AP9GfgBre5THg1OMbmy-d6nRTL5mpUlPtqF06HGZPMKVCobohuhKZDqR6yNdwSV87PZEXw0Jxje0zT7QKab6VmtuuLvLOIvv-unSdUGuXvIXYu7xBM91JthG8pVo70IHm6K_ZVGjt4Ekkqt8Q8cSzDr_6i5qWjql7N3QHdYljXnHbZz2hyoBKbF1R1MUwjx4EOcspEJzXRMdOxNKE4RfR1fdBxY_UB50Fc76fg7joeSIqezOziNB-LMTM3_hiNOJW19jMPeS3enaJJTrxJ1nbg"
    val userDomain = "testing.hubat.net"
    @Test
    fun testApps(){
//        fun completion(externalAppsObj : List<HATExternalAppsObject>?, token : String?){
//            Log.i("externalApps",externalAppsObj?.size.toString())
//            Log.i("externalApps",token.toString())
//        }
//        fun failcallback(error : HATError){
//            Log.i("externalApps","fail")
//        }
//
//       HATExternalAppsService().getExternalApps(token, userDomain,{ obj, token ->completion(obj,token)},{ error -> failcallback(error)})
    }

    @Test
    fun testFeed(){
//        fun completion(feedObj : List<HATFeedObject>?, token : String?){
//            Log.i("HATFeed",feedObj?.size.toString())
//            Log.i("HATFeed",token.toString())
//        }
//        fun failcallback(error : HATError){
//            Log.i("HATFeed","fail")
//        }
//
//        HATFeedService().getFeed(userDomain , token, null,"",{ obj, token ->completion(obj,token)},{ error -> failcallback(error)})
    }
}