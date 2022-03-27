package com.example.veramobile

import com.google.gson.annotations.SerializedName

data class Devices(
    @SerializedName("Devices") val devices :ArrayList<DeviceModel>

    )
