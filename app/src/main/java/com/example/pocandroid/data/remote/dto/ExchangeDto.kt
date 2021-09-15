package com.example.pocandroid.data.remote.dto


import com.example.pocandroid.domain.model.Exchange
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class ExchangeDto(
    @SerializedName("data_end")
    val dataEnd: String,
    @SerializedName("data_orderbook_end")
    val dataOrderBookEnd: String,
    @SerializedName("data_orderbook_start")
    val dataOrderBookStart: String,
    @SerializedName("data_quote_end")
    val dataQuoteEnd: String,
    @SerializedName("data_quote_start")
    val dataQuoteStart: String,
    @SerializedName("data_start")
    val dataStart: String,
    @SerializedName("data_symbols_count")
    val dataSymbolsCount: Int,
    @SerializedName("data_trade_end")
    val dataTradeEnd: String,
    @SerializedName("data_trade_start")
    val dataTradeStart: String,
    @SerializedName("exchange_id")
    val exchangeId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("volume_1day_usd")
    val volume1dayUsd: BigDecimal,
    @SerializedName("volume_1hrs_usd")
    val volume1hrsUsd: BigDecimal,
    @SerializedName("volume_1mth_usd")
    val volume1mthUsd: BigDecimal,
    @SerializedName("website")
    val website: String
) {
    fun toExchange(): Exchange {
        return Exchange(
            id = exchangeId,
            name = name,
            volume = volume1hrsUsd
        )
    }
}