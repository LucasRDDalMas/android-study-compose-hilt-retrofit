package com.example.pocandroid.data.remote

import com.example.pocandroid.BuildConfig
import com.example.pocandroid.data.remote.dto.ExchangeDto
import retrofit2.http.GET
import retrofit2.http.Headers

interface CoinApi {
    @Headers("X-CoinAPI-Key: ")
    @GET("/v1/exchanges")
    suspend fun getExchanges(): List<ExchangeDto>
}
