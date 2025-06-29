package com.example.franccompose

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.franccompose.fiturmulaibelajar.datastore.DataStoreManager
import kotlinx.coroutines.delay

@Composable
fun HomeActivity(navController: NavController) {
    val context = LocalContext.current
    val dataStore = remember { DataStoreManager(context) }
    var namaUser by remember { mutableStateOf("...") }

    LaunchedEffect(Unit) {
        val user = dataStore.getLastUser()
        user?.let {
            namaUser = it.first
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // HEADER
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .background(
                        color = Color(0xFF3A69C7),
                        shape = RoundedCornerShape(
                            topEnd = 16.dp,
                            bottomEnd = 16.dp
                        )
                    )
                    .padding(horizontal = 50.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Hallo, $namaUser",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

// SETTING ICON TANPA ANIMASI
            IconButton(
                onClick = {
                    navController.navigate("profileScreen")
                },
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(48.dp)
                    .align(Alignment.CenterEnd)
                    .zIndex(2f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.sett),
                    contentDescription = "User Icon",
                    tint = Color.Unspecified
                )
            }
        }



        Spacer(modifier = Modifier.height(50.dp))

        // BODY (banner + menu)
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .graphicsLayer { clip = false }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFD2E8FF), shape = RoundedCornerShape(12.dp))
                        .zIndex(1f)
                ) {
                    Text(
                        text = "Ayo Hitung \n Pecahan !",
                        color = Color(0xFFFBA834),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 30.dp)
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.org1),
                    contentDescription = "Kid Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(500.dp)
                        .align(Alignment.CenterStart)
                        .offset(x = (-80).dp)
                        .zIndex(5f)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Baris 1 Menu
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                MenuButton(
                    iconId = R.drawable.belajaricon,
                    text = "Mulai Belajar",
                    textColor = Color(0xFF5CB85C),
                    onClick = {
                        navController.navigate("daftarbelajar")
                    }
                )
                MenuButton(
                    iconId = R.drawable.levicon,
                    text = "Levelku",
                    textColor = Color(0xFF5BC0DE),
                    onClick = {
                        navController.navigate("levelkuScreen")
                    }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Baris 2 Menu (yang sebelumnya tidak terlihat)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                MenuButton(
                    iconId = R.drawable.tenicon,
                    text = "Tentang Aplikasi",
                    textColor = Color(0xFFF0AD4E),
                    onClick = {
                        navController.navigate("tentangScreen")
                    }
                )
                MenuButton(
                    iconId = R.drawable.logout,
                    text = "Keluar Akun",
                    textColor = Color(0xFFD9534F),
                    onClick = {
                        navController.navigate("keluarActivity")
                    }
                )
            }
        }

        // FOOTER
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .background(
                        color = Color(0xFFCDEEFF),
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
                    .padding(20.dp)
                    .padding(bottom = 20.dp)
            ) {
                Text(
                    text = "Belajar Pecahan?\nPasti Bisa Dong",
                    color = Color(0xFFF24E1E),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }

            Image(
                painter = painterResource(id = R.drawable.otak1),
                contentDescription = "Maskot",
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = (-20).dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeActivityPreview() {
    HomeActivity(navController = rememberNavController())
}
