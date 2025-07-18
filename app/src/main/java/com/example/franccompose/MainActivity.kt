package com.example.franccompose

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.franccompose.fiturmulaibelajar.viewmodel.MateriViewModel
import com.example.franccompose.fiturmulaibelajar.viewmodel.MateriViewModelFactory
import com.example.franccompose.navigation.AppNavHost
import com.example.franccompose.ui.theme.FrancComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FrancComposeTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val context = LocalContext.current
                    val navController = rememberNavController()

                    // Inisialisasi MateriViewModel
                    val materiViewModel: MateriViewModel = viewModel(
                        factory = MateriViewModelFactory(context.applicationContext as Application)
                    )

                    // Kirim ke AppNavHost
                    AppNavHost(
                        navController = navController,
                        materiViewModel = materiViewModel
                    )
                }
            }
        }
    }
}
