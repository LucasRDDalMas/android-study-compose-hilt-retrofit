package com.example.pocandroid.data.remote

import com.example.pocandroid.data.remote.dto.CoinDto
import com.example.pocandroid.data.remote.dto.ExchangeDto
import retrofit2.http.GET
import retrofit2.http.Headers

interface CoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @Headers("X-CoinAPI-Key: A19F8069-3215-4644-A706-B519FEC3A8B1")
    @GET("/v1/exchanges")
    suspend fun getExchanges(): List<ExchangeDto>
}