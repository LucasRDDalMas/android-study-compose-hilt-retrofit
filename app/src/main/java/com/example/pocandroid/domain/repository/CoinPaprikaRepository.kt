package com.example.pocandroid.domain.repository

import com.example.pocandroid.domain.model.Coin

interface CoinPaprikaRepository {
    suspend fun getCoins(): List<Coin>
}