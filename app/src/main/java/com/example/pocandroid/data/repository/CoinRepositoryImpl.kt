package com.example.pocandroid.data.repository

import com.example.pocandroid.data.remote.CoinApi
import com.example.pocandroid.domain.model.Exchange
import com.example.pocandroid.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(private val coinApi: CoinApi) :
    CoinRepository {
    override suspend fun getExchanges(): List<Exchange> {
        return coinApi.getExchanges().map { it.toExchange() }
    }
}