package com.example.harrypotter.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface HarryPotterApi {
    @GET("characters")
    suspend fun getData(): Response<CharactersHarryPotter>
}