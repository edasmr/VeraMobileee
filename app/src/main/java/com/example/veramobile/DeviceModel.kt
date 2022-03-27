package com.example.veramobile

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import retrofit2.http.PATCH

@Parcelize
data class DeviceModel(

    @SerializedName("PK_Device") val pkDevice :Int,
    @SerializedName("MacAddress") val macAddress :String,
    @SerializedName("Firmware")val firmware : String,
    @SerializedName("Platform") var platform: String







):Parcelable
