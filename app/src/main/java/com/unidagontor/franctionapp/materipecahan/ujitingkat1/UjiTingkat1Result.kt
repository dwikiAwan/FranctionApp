package com.unidagontor.franctionapp.materipecahan.ujitingkat1

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unidagontor.franctionapp.R
import com.unidagontor.franctionapp.datastore.DataStoreManager
import com.unidagontor.franctionapp.quiz.formatTime
import com.unidagontor.franctionapp.viewmodel.UjiTingkatViewModel

@Composable
fun UjiTingkat1ResultScreen(
    score: Int,
    elapsedTime: Int,
    materiKe: Int,
    dataStoreManager: DataStoreManager,
    viewModel: UjiTingkatViewModel,
    onBackToHome: () -> Unit,
    onUlangUji: () -> Unit,
    onNextBelajar: () -> Unit
) {
    val isPassed = score >= 70
    val sudahDiproses = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.setDataStore(dataStoreManager)
        if (!sudahDiproses.value) {
            val currentLevel = dataStoreManager.getFinalLevel("namaUser", "kelasUser")
            if (isPassed && currentLevel < 4) {
                println("Skor Uji Tingkat disimpan & level dinaikkan")
            } else {
                println("Sudah pernah lulus, tidak upgrade level lagi")
            }
            sudahDiproses.value = true
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isPassed) Color(0xFF4CAF50) else Color(0xFFDA0B0B))
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.85f)
                .wrapContentHeight()
                .background(Color.White, RoundedCornerShape(30.dp))
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = if (isPassed) R.drawable.medal else R.drawable.gagal),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                if (isPassed) "SELAMAT" else "MAAF",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = if (isPassed) Color(0xFF2C7D17) else Color(0xFFDA0B0B)
            )
            Text(
                if (isPassed) "Anda Lulus Uji Tingkat 1" else "Anda Gagal",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = if (isPassed) Color(0xFF0B46A7) else Color(0xFFFF9800)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text("Skor Anda:", fontWeight = FontWeight.Bold, fontSize = 20.sp)

            Box(
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(20.dp))
                    .border(
                        1.dp,
                        if (isPassed) Color(0xFF2C7D17) else Color(0xFFDA0B0B),
                        RoundedCornerShape(20.dp)
                    )
                    .padding(horizontal = 40.dp, vertical = 24.dp)
            ) {
                Text("$score", fontSize = 36.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text("Waktu pengerjaan: ${formatTime(elapsedTime)}", fontSize = 18.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(24.dp))
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.systemBars)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isPassed) {
                Button(
                    onClick = onNextBelajar,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .border(2.dp, Color(0xFFFF9800), RoundedCornerShape(30.dp)),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Text("Lanjut Belajar", color = Color(0xFFFF9800))
                }

                Spacer(modifier = Modifier.height(12.dp))
            } else {
                Button(
                    onClick = onUlangUji,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .border(2.dp, Color(0xFF0B46A7), RoundedCornerShape(30.dp)),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Text("Coba Lagi", color = Color(0xFF0B46A7))
                }

                Spacer(modifier = Modifier.height(12.dp))
            }

            Button(
                onClick = onBackToHome,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .border(2.dp, Color.Black, RoundedCornerShape(30.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text("Kembali ke Beranda", color = Color.Black)
            }
        }
    }
}