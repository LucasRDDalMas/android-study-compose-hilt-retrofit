package com.example.pocandroid.presentation.get_exchanges

import com.example.pocandroid.domain.model.Exchange

data class ExchangeListState(
    val isLoading: Boolean = false,
    val exchanges: List<Exchange> = emptyList(),
    val error: String = ""
)
