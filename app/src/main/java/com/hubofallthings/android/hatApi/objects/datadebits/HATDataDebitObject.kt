package com.hubofallthings.android.hatApi.objects.datadebits

import java.io.Serializable

data class DataDebitObject (
    // MARK: - Variables
    /// The data debit key
    val dataDebitKey: String = "",
    /// The date created of the data debit
    val dateCreated: String = "",
    /// The permissions of the data debit
    val permissions: Array<DataDebitPermissionsObject>,
    /// The client name of the data debit
    val requestClientName: String = "",
    /// The client url of the data debit
    val requestClientUrl: String = "",
    /// The client logo URL of the data debit
    val requestClientLogoUrl: String = "",
    /// The description of the data debit
    val requestDescription: String?,
    /// The id of the application, if the data debit is an application
    val requestApplicationId: String?,
    /// Is data debit active?
    val active: Boolean = false,
    /// The start date of the data debit
    val start: String?,
    /// The end date of the data debit
    val end: String?,
    /// Are permissions still active
    val permissionsActive: DataDebitPermissionsObject?,
    /// The last permission set
    val permissionsLatest: DataDebitPermissionsObject
    ) : Serializable