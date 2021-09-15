package com.example.pocandroid.domain.repository

import com.example.pocandroid.domain.model.Exchange

interface CoinRepository {
    suspend fun getExchanges(): List<Exchange>
}