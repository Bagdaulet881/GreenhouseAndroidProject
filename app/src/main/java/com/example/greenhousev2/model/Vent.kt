package com.example.greenhousev2.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize



@Parcelize
data class Sensor(
    @SerializedName("id") val id: Int,
    @SerializedName("state") var state: String,
    @SerializedName("dataType") val dataType: String,
    @SerializedName("info") var info: String

) : Parcelable

@Parcelize
data class VentParcel(
    @SerializedName("id") val id: Int,
    @SerializedName("state") var state: String,
    @SerializedName("position") val position: String

) : Parcelable

@Parcelize
data class WaterD(
    @SerializedName("id") val id: Int,
    @SerializedName("state") var state: String,

) : Parcelable

@Parcelize
data class Fan(
    @SerializedName("id") val id: Int,
    @SerializedName("state") var state: String,
    @SerializedName("rpm") var rpm: String

) : Parcelable

@Parcelize
data class Phytolamp(
    @SerializedName("id") val id: Int,
    @SerializedName("state") var state: String,

    ) : Parcelable