package com.example.harrypotter.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HarryPotterApi {
    @GET("characters/")
    suspend fun getData(): Response<List<CharactersHarryPotter>>

    @GET("character/{id}")
    suspend fun getDetailData(@Path("id")id:String): Response<DetailCharacterHarryPotter>
}