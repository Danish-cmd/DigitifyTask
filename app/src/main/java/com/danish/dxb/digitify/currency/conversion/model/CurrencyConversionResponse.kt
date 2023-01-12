package com.danish.dxb.digitify.currency.conversion.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrencyConversionResponse(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @SerializedName("quotes")
    val rates: Map<String, String>?,
    @SerializedName("source")
    val source: String?,
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("timestamp")
    val timestamp: Int?,
    @SerializedName("error")
    val error: Error?,
) : Parcelable

@Parcelize
data class Error(
    val code: Int?,
    val info: String?
) : Parcelable


