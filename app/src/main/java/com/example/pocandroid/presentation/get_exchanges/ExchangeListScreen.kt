package com.example.pocandroid.presentation.get_exchanges

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pocandroid.presentation.Screen
import com.example.pocandroid.presentation.get_exchanges.components.ExchangeListItem

@Composable
fun ExchangeListScreen(
    navController: NavController,
    viewModel: ExchangeListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.exchanges) { exchange ->
                ExchangeListItem(
                    exchange = exchange
                )
            }
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.CoinListScreen.route)
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(15.dp),
                backgroundColor = Color.Gray
            ) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Coins",
                    tint = Color.White
                )
            }
        }
    }
}