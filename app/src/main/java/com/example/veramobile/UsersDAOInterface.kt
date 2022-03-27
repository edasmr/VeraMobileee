package com.example.veramobile

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface UsersDAOInterface {
    @POST("/test_android/items.test")
    fun getItems(): Call<Devices>

    @POST("/test_android/items.test")
    @FormUrlEncoded
    fun update(
        @Field("Platform") platform: String
    ): Call<String>

}