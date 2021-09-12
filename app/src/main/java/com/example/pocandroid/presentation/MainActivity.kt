package com.example.pocandroid.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pocandroid.presentation.coin_list.CoinListScreen
import com.example.pocandroid.presentation.get_exchanges.ExchangeListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Screen.CoinListScreen.route
            ) {
                composable(
                    route = Screen.CoinListScreen.route
                ) {
                    CoinListScreen(navController)
                }

                composable(
                    route = Screen.ExchangeListScreen.route
                ) {
                    ExchangeListScreen(navController)
                }
            }
        }
    }
}