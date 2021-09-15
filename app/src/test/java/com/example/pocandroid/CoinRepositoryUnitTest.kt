package com.example.pocandroid

import com.example.pocandroid.data.remote.CoinApi
import com.example.pocandroid.data.remote.dto.ExchangeDto
import com.example.pocandroid.data.repository.CoinRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CoinRepositoryUnitTest {

    private lateinit var coinRepository: CoinRepositoryImpl
    private lateinit var coinApi: CoinApi

    @Before
    fun setup() {
        coinApi = mockk<CoinApi>()
        coinRepository = CoinRepositoryImpl(coinApi)
    }

//    @Test
//    fun example() {
//        Assert.assertTrue(coinRepository.getExchanges().isNotEmpty())
//    }

//    @Test
//    suspend fun example() {
//        Assert.assertTrue(coinRepository.getExchanges().isNotEmpty())
//    }

    @Test
    fun `should return a list of exchanges`() = runBlockingTest {

        val listDto: MutableList<ExchangeDto> = mutableListOf()

        listDto.add(
            ExchangeDto(
                "2021-09-09", "2021-09-09", "2021-09-09", "2021-09-09",
                "2021-09-09", "2021-09-09", 10, "2021-09-09", "2021-09-09",
                "BNC", "Binance", 10.0, 1.0, 300.0, "www.google.com"
            )
        )

        val list = listDto.map { it.toExchange() }

        coEvery { coinApi.getExchanges() } returns listDto

        async {
            coinRepository.getExchanges()
        }.await()
            .let { result ->

                Assert.assertTrue(result.isNotEmpty())
                Assert.assertTrue(list.containsAll(result))

            }
    }
}