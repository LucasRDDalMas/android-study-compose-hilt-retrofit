package com.example.pocandroid.domain.model

import java.math.BigDecimal

data class Exchange(
    val id: String,
    val name: String,
    val volume: BigDecimal
)
