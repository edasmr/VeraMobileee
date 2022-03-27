package com.example.veramobile

class ApiUtils {

    companion object {
        private const val BASE_URL = "https://veramobile.mios.com"

        fun usersDAOInterface(): UsersDAOInterface {
            return RetrofitClient.getClient(BASE_URL).create(UsersDAOInterface::class.java)
        }
    }
}