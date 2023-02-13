package com.example.footballflow.api

import com.example.footballflow.CompResponse
import com.example.footballflow.pojo.Response2
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @GET("competitions")
    @Headers("Accept: application/json","X-Auth-Token:87d8928e168f4149b3ea9d89501ba603")
    suspend fun getCompetitions(): CompResponse

    @GET("competitions/2000")
    @Headers("Accept: application/json","X-Auth-Token:87d8928e168f4149b3ea9d89501ba603")
    suspend fun getCompetitionAndTeam():Response2
}