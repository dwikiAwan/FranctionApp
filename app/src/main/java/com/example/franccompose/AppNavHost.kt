package com.example.franccompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.franccompose.HomeActivity
import com.example.franccompose.KeluarActivity
import com.example.franccompose.LevelkuScreen
import com.example.franccompose.ProfileScreen
import com.example.franccompose.RegisScreen
import com.example.franccompose.SecondScreen
import com.example.franccompose.SplashScreen
import com.example.franccompose.TentangScreen
import com.example.franccompose.fiturmulaibelajar.DaftarMateriScreen




@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Splash) {
        composable(Routes.Splash) {
            SplashScreen(navController)
        }
        composable(Routes.Second) {
            SecondScreen(onNextClick = {
                navController.navigate(Routes.reg)
            })
        }
        composable(Routes.reg) {
            RegisScreen(navController)
        }
        composable(Routes.home) {
            HomeActivity(navController)
        }
        composable(Routes.misi) {
            DaftarMateriScreen(navController)
        }

        composable(Routes.tentang) {
            TentangScreen(navController)
        }
        composable(Routes.levelku) {
            LevelkuScreen(navController)
        }
        composable(Routes.profile) {
            ProfileScreen(navController)
        }
        composable(Routes.keluar) {
            KeluarActivity(navController)
        }

    }
}

