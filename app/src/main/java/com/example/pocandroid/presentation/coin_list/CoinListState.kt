package com.example.pocandroid.presentation.coin_list

import com.example.pocandroid.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
