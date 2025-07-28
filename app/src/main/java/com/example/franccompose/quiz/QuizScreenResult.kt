package com.example.franccompose.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.franccompose.R
import com.example.franccompose.fiturmulaibelajar.datastore.DataStoreManager
import com.example.franccompose.fiturmulaibelajar.viewmodel.MateriViewModel

fun formatTime(sec: Int): String {
    val m = sec / 60
    val s = sec % 60
    return "%02d:%02d".format(m, s)
}

@Composable
fun HasilQuizScreen(
    score: Int,
    elapsedTime: Int,
    materiKe: Int,
    onNextClick: () -> Unit,
    onUlangMateriClick: () -> Unit,
    onMainMenuClick: () -> Unit,
    materiViewModel: MateriViewModel
) {
    val context = LocalContext.current
    val dataStore = remember { DataStoreManager(context) }
    val clampedScore = score.coerceIn(0, 100)
    val sudahDiproses = remember { mutableStateOf(false) }

    if (!sudahDiproses.value && clampedScore >= 80) {
        materiViewModel.unlockNext(materiKe)
        sudahDiproses.value = true
    }


    // ⬇️ UI ditampilkan seperti biasa
    when {
        clampedScore >= 80 -> {
            BerhasilResultScreen(
                score = score,
                elapsedTime = elapsedTime,
                onNextClick = onNextClick,
                onMainMenuClick = onMainMenuClick
            )
        }

        clampedScore <= 60 -> {
            GagalResultScreen(
                score = score,
                elapsedTime = elapsedTime,
                onNextClick = onNextClick,
                onUlangMateriClick = onUlangMateriClick,
                onMainMenuClick = onMainMenuClick
            )
        }

        else -> {
            QuizResultScreen(score = score, onBackToHome = onMainMenuClick)
        }
    }
}


@Composable
fun QuizResultScreen(score: Int, onBackToHome: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA))
            .padding(24.dp)
            .windowInsetsPadding(WindowInsets.systemBars),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Skor Akhir Anda", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))
        Text("$score / 100", fontSize = 40.sp, color = Color(0xFF4CAF50))
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onBackToHome) {
            Text("Kembali ke Beranda")
        }
    }
}

@Composable
fun BerhasilResultScreen(
    score: Int,
    elapsedTime: Int,
    onNextClick: () -> Unit,
    onMainMenuClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF4CAF50))
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.85f)
                .heightIn(min = 450.dp)
                .background(Color.White, RoundedCornerShape(30.dp))
                .padding(horizontal = 24.dp, vertical = 20.dp)
                .windowInsetsPadding(WindowInsets.systemBars),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.medal),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text("SELAMAT", fontWeight = FontWeight.Bold, fontSize = 30.sp, color = Color(0xFF2C7D17))
            Text("Anda BERHASIL", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xFF0B46A7))
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = buildAnnotatedString {
                    append("Menyelesaikan ")
                    withStyle(style = androidx.compose.ui.text.SpanStyle(fontStyle = FontStyle.Italic)) {
                        append("Quiz")
                    }
                    append(" ini")
                },
                fontSize = 18.sp,
                color = Color(0xFF0B46A7)
            )

            Spacer(modifier = Modifier.height(24.dp))
            Text("Skormu :", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xFF0B46A7))

            Box(
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(20.dp))
                    .border(1.dp, Color(0xFF2C7D17), RoundedCornerShape(20.dp))
                    .padding(horizontal = 40.dp, vertical = 24.dp)
            ) {
                Text("$score", fontSize = 36.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2C7D17))
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Waktu pengerjaan: ${formatTime(elapsedTime)}",
                fontSize = 18.sp,
                color = Color.Black
            )

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
            Button(
                onClick = onNextClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .border(3.dp, Color(0xFFFF9800), RoundedCornerShape(30.dp))
            ) {
                Text("Lanjut Belajar", color = Color(0xFFFF9800), fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onMainMenuClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .border(3.dp, Color(0xFFD32F2F), RoundedCornerShape(30.dp))
            ) {
                Text("Beranda", color = Color(0xFFD32F2F), fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun GagalResultScreen(
    score: Int,
    elapsedTime: Int,
    onNextClick: () -> Unit,
    onUlangMateriClick: () -> Unit,
    onMainMenuClick: () -> Unit
)  {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFDA0B0B))
    ) {
        Column(
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.systemBars)
                .align(Alignment.Center)
                .fillMaxWidth(0.85f)
                .heightIn(min = 450.dp)
                .background(Color.White, RoundedCornerShape(30.dp))
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.gagal),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text("MAAF", fontWeight = FontWeight.Bold, fontSize = 30.sp, color = Color(0xFFDA0B0B))
            Text("Anda GAGAL", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xFFFF9800))
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = buildAnnotatedString {
                    append("Menyelesaikan ")
                    withStyle(style = androidx.compose.ui.text.SpanStyle(fontStyle = FontStyle.Italic)) {
                        append("Quiz")
                    }
                    append(" ini")
                },
                fontSize = 18.sp,
                color = Color(0xFFFF9800)
            )

            Spacer(modifier = Modifier.height(24.dp))
            Text("Skormu :", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xFFFF9800))

            Box(
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(20.dp))
                    .border(1.dp, Color(0xFFDA0B0B), RoundedCornerShape(20.dp))
                    .padding(horizontal = 40.dp, vertical = 24.dp)
            ) {
                Text("$score", fontSize =36.sp, fontWeight = FontWeight.Bold, color = Color(0xFFDA0B0B))
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Waktu pengerjaan: ${formatTime(elapsedTime)}",
                fontSize = 18.sp,
                color = Color.Black
            )

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
            Button(
                onClick = onUlangMateriClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .border(3.dp, Color(0xFF0B46A7), RoundedCornerShape(30.dp))
            ) {
                Text("Remidi Quiz", color = Color(0xFF0B46A7), fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onMainMenuClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .border(3.dp, Color(0xFF2C7D17), RoundedCornerShape(30.dp))
            ) {
                Text("Beranda", color = Color(0xFF2C7D17), fontSize = 16.sp)
            }
        }
    }
}
