package com.hubofallthings.android.hatApi.objects.datadebits
import com.hubofallthings.android.hatApi.objects.dataoffers.DataOfferRequiredDataDefinitionObjectV2
import java.io.Serializable

data class DataDebitPermissionsObject(
    // MARK: - Variables

    /// The created date of the permission
    val dateCreated: String = "",
    /// The purpose of the permission
    val purpose: String?,
        /// The start date of the permission
    val start: String?,
        /// The end date of the permission
    val end: String?,
        /// A flag indicating if the permissions will auto cancel when the debit will end
    val cancelAtPeriodEnd: Boolean = false,
        /// The terms and conditions URL for the permissions
    val termsUrl: String?,
        /// The period duration
    val period: Number = 0,
        /// Is the permission active
    val active: Boolean = false,
        /// Is the permission accepted
    val accepted: Boolean = false,
        /// It is possible for a permission to have an inner bundle object
    val bundle: DataOfferRequiredDataDefinitionObjectV2
) : Serializable