package com.example.franccompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.franccompose.fiturmulaibelajar.datastore.DataStoreManager


@Composable
fun HomeActivity(navController: NavController) {
    val context = LocalContext.current
    val dataStoreManager = remember { DataStoreManager(context) }

    var nama by remember { mutableStateOf("") }
    var kelas by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        val user = dataStoreManager.getLastUser()
        user?.let {
            nama = it.first
            kelas = it.second
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
                    text = "Hallo, $nama | $kelas",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

            }

// SETTING ICON TANPA ANIMASI
            IconButton(
                onClick = {
                    navController.navigate("profile")
                },
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(50.dp)
                    .align(Alignment.CenterEnd)
                    .zIndex(2f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.proficon),
                    contentDescription = "User Icon",
                    tint = Color.Unspecified
                )
            }
        }



        Spacer(modifier = Modifier.height(30.dp))

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
                        navController.navigate("daftarmateri")
                    }
                )
                MenuButton(
                    iconId = R.drawable.levicon,
                    text = "Levelku",
                    textColor = Color(0xFF5BC0DE),
                    onClick = {
                        navController.navigate("levelku")
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
                        navController.navigate("tentangaplikasi")
                    }
                )
                MenuButton(
                    iconId = R.drawable.logout,
                    text = "Keluar Akun",
                    textColor = Color(0xFFD9534F),
                    onClick = {
                        navController.navigate("keluar")
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
