package com.example.pocandroid.presentation.get_exchanges

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pocandroid.common.Resource
import com.example.pocandroid.domain.use_case.get_exchanges.GetExchangesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ExchangeListViewModel @Inject constructor(
    private val getExchangesUseCase: GetExchangesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(ExchangeListState())
    val state: State<ExchangeListState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        getExchangesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ExchangeListState(exchanges = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = ExchangeListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = ExchangeListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}