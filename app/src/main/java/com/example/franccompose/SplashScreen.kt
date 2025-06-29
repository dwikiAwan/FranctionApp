// ✅ SplashScreen.kt
package com.example.franccompose

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.franccompose.fiturmulaibelajar.datastore.DataStoreManager
import com.example.franccompose.navigation.Routes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navController: NavController) {
    val alpha = remember { Animatable(0f) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(true) {
        alpha.animateTo(1f, animationSpec = tween(1000))
        delay(1000)

        val dataStore = DataStoreManager(context)
        val lastUser = dataStore.getLastUser()

        if (lastUser != null) {
            navController.navigate(Routes.home) {
                popUpTo(Routes.Splash) { inclusive = true }
            }
        } else {
            navController.navigate(Routes.Second) {
                popUpTo(Routes.Splash) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.logoapp),
                contentDescription = "Franction Logo",
                modifier = Modifier
                    .size(200.dp)
                    .alpha(alpha.value)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Franction App",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.alpha(alpha.value)
            )
        }
    }
}

