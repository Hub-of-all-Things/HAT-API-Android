package com.hubofallthings.android.hatApi.objects.purchase
data class PurchaseObject(
        val firstName : String = "",
        val termsAgreed : Boolean = false,
        val lastName : String = "",
        val email : String = "",
        val hatName : String = "",
        val password : String = "",
        val hatCluster : String = "",
        val hatCountry : String = "",
        val membership : PurchaseMembershipObject,
        val optins : ArrayList<String>?,
        val applicationId : String = ""
)
