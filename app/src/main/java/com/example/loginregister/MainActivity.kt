@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

package com.example.loginregister

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginregister.ui.theme.LoginRegisterTheme
import com.example.loginregister.viewmodel.StopifyViewModel
import com.example.loginregister.views.LoginLayout
import com.example.loginregister.views.HomeScreen
import com.example.loginregister.views.RegistreScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginRegisterTheme {
                val windowSize = calculateWindowSizeClass(this)
                val navigationController = rememberNavController()

                val stopifyViewModel: StopifyViewModel = viewModel()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.LoginLayout.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Routes.LoginLayout.route) {
                            LoginLayout(
                                modifier = Modifier,
                                navController = navigationController, windowSize = windowSize, viewModel = stopifyViewModel)
                        }
                        composable(Routes.RegistreScreen.route) {
                            RegistreScreen(
                                navController = navigationController,
                                windowSize = windowSize,
                                viewModel = stopifyViewModel
                            )
                        }
                        composable(Routes.HomeScreen.route) {
                            HomeScreen(
                                navController = navigationController,
                                windowSizeClass = windowSize
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LoginRegisterTheme {
        Greeting("Android")
    }
}