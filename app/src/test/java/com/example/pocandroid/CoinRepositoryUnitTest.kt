package com.example.pocandroid

import com.example.pocandroid.data.remote.CoinApi
import com.example.pocandroid.data.remote.dto.ExchangeDto
import com.example.pocandroid.data.repository.CoinRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.math.BigDecimal

@ExperimentalCoroutinesApi
class CoinRepositoryUnitTest {

    private lateinit var coinRepositoryMockk: CoinRepositoryImpl
    private lateinit var coinApiMockk: CoinApi

    private lateinit var coinRepository: CoinRepositoryImpl
    private lateinit var coinApi: CoinApi

    @Before
    fun setup() {
        coinApiMockk = mockk()
        coinRepositoryMockk = CoinRepositoryImpl(coinApiMockk)

        coinApi = mock()
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
    fun `should return a list of exchanges with mockk`() = runBlockingTest {

        val listDto: MutableList<ExchangeDto> = mutableListOf()

        listDto.add(
            ExchangeDto(
                "2021-09-09", "2021-09-09", "2021-09-09", "2021-09-09",
                "2021-09-09", "2021-09-09", 10, "2021-09-09", "2021-09-09",
                "BNC", "Binance", BigDecimal(10), BigDecimal(10), BigDecimal(10), "www.google.com"
            )
        )

        val list = listDto.map { it.toExchange() }

        coEvery { coinApiMockk.getExchanges() } returns listDto

        val exchanges = coinRepositoryMockk.getExchanges()

        Assert.assertTrue(exchanges.isNotEmpty())
        Assert.assertTrue(list.containsAll(exchanges))
    }

    @Test
    fun `should return a list of exchanges with mockito`() = runBlockingTest {

        val listDto: MutableList<ExchangeDto> = mutableListOf()

        listDto.add(
            ExchangeDto(
                "2021-09-09", "2021-09-09", "2021-09-09", "2021-09-09",
                "2021-09-09", "2021-09-09", 10, "2021-09-09", "2021-09-09",
                "BNC", "Binance", BigDecimal(10), BigDecimal(10), BigDecimal(10), "www.google.com"
            )
        )

        val list = listDto.map { it.toExchange() }

        whenever(coinApi.getExchanges()).thenReturn(listDto)

        val exchanges = coinRepository.getExchanges()

        Assert.assertTrue(exchanges.isNotEmpty())
        Assert.assertTrue(list.containsAll(exchanges))
    }
}