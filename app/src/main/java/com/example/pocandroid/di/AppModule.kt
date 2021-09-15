package com.example.pocandroid.di

import com.example.pocandroid.common.Constants
import com.example.pocandroid.data.remote.CoinApi
import com.example.pocandroid.data.remote.CoinPaprikaApi
import com.example.pocandroid.data.repository.CoinPaprikaRepositoryImpl
import com.example.pocandroid.data.repository.CoinRepositoryImpl
import com.example.pocandroid.domain.repository.CoinPaprikaRepository
import com.example.pocandroid.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaprikaApi(): CoinPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_PAPRIKA)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinApi(): CoinApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinPaprikaRepository(api: CoinPaprikaApi): CoinPaprikaRepository {
        return CoinPaprikaRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinApi): CoinRepository {
        return CoinRepositoryImpl(api)
    }
}