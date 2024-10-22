package com.andres_sagadoc.androidmaster.superheroapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/api/e887b9560ccc9a3a5ca38ee237a8d201/search/{name}")
    suspend fun getSuperHeros(@Path("name") superHeroName: String): Response<SuperHeroDataResponse>

}