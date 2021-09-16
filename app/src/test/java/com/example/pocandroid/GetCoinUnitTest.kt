package com.example.pocandroid

import com.example.pocandroid.common.Resource
import com.example.pocandroid.data.remote.CoinApi
import com.example.pocandroid.data.remote.dto.CoinDto
import com.example.pocandroid.data.remote.dto.ExchangeDto
import com.example.pocandroid.data.repository.CoinRepositoryImpl
import com.example.pocandroid.domain.model.Coin
import com.example.pocandroid.domain.repository.CoinPaprikaRepository
import com.example.pocandroid.domain.use_case.get_coins.GetCoinsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.math.BigDecimal

@ExperimentalCoroutinesApi
class GetCoinUnitTest {

    private lateinit var getCoinsUseCase: GetCoinsUseCase
    private lateinit var coinPaprikaRepository: CoinPaprikaRepository

    @Before
    fun setup() {
        coinPaprikaRepository = mock()
        getCoinsUseCase = GetCoinsUseCase(coinPaprikaRepository)
    }

    @Test
    fun `should first emit resource loading`() = runBlockingTest {

        val listDto: MutableList<CoinDto> = mutableListOf()

        listDto.add(
            CoinDto(
                id = "BTC",
                isActive = true,
                isNew = false,
                name = "Bitcoin",
                rank = 1,
                symbol = "BTC",
                type = "type"
            )
        )

        val list = listDto.map { it.toCoin() }

        whenever(coinPaprikaRepository.getCoins()).thenReturn(list)

        val coins = getCoinsUseCase().first()

        Assert.assertTrue(coins is Resource.Loading)
    }


    @Test
    fun `should return loading then data resource`() = runBlockingTest {

        val list: MutableList<Coin> = mutableListOf()

        list.add(
            Coin(
                id = "BTC",
                isActive = true,
                name = "Bitcoin",
                rank = 1,
                symbol = "BTC"
            )
        )

        whenever(coinPaprikaRepository.getCoins()).thenReturn(list)

        val coins = getCoinsUseCase().toList()

        Assert.assertTrue(coins[0] is Resource.Loading)
        Assert.assertTrue(coins[1] is Resource.Success)
        Assert.assertEquals(coins[1].data, list)
    }
}