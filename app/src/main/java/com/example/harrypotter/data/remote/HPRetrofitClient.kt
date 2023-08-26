package com.example.harrypotter.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HPRetrofitClient {

    companion object{
        private const val URL_BASE = "https://my-json-server.typicode.com/Himuravidal/FakeAPIdata/"

        fun getRetrofitPhone() : HarryPotterApi{
            val mRetrofit = Retrofit.Builder().baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create()).build()
            return mRetrofit.create(HarryPotterApi::class.java)
        }
    }
}