package com.example.shabyttan.services


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CreatorApiService {

    companion object{
        private const val BASE_URL = "https://openaccess-api.clevelandart.org"
        private var retrofit : Retrofit? = null

        fun getInstance() : Retrofit{
            if(retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }
}