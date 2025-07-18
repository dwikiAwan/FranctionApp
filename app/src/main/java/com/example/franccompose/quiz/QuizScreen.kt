package com.example.franccompose.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.franccompose.R
import com.example.franccompose.fiturmulaibelajar.datastore.DataStoreManager
import kotlinx.coroutines.delay

@Composable
fun QuizScreen(
    navController: NavController,
    viewModel: QuizViewModel,
    dataStore: DataStoreManager,
    materiKe: Int
) {
    val namaMateri = when (materiKe) {
        1 -> "Pengenalan Pecahan"
        2 -> "Membandingkan dan Mengurutkan Pecahan"
        3 -> "Ujitingkat1"
        4 -> "Operasi Penjumlahan Pecahan"
        5 -> "Operasi Pengurangan Pecahan"
        6 -> "Ujitingkat2"
        else -> "Materi Tidak Diketahui"
    }

    var showPopUp by remember { mutableStateOf(true) }

    if (showPopUp) {
        QuizPopUpCard(
            namaMateri = namaMateri,
            onClose = {
                navController.popBackStack() // kembali jika tidak jadi kuis
            },
            onStartQuiz = {
                showPopUp = false // tutup pop up
                viewModel.loadQuiz(materiKe) // load soal hanya sekali
            }
        )
        return
    }

    val questionIndex by viewModel.currentQuestionIndex
    val questions by viewModel.questions.collectAsState()

    if (questions.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Memuat soal...", fontSize = 16.sp)
        }
        return
    }

    val question = questions[questionIndex]
    var selectedOption by remember(questionIndex) {
        mutableStateOf(viewModel.selectedAnswers[questionIndex] ?: -1)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFB6D9F3))
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.soalquiz),
                    contentDescription = "Icon Quiz",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(start = 4.dp)
                )

                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier.height(30.dp))
                        Text("QUIZ", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        Text(namaMateri, fontSize = 16.sp, color = Color.White, textAlign = TextAlign.Center)
                    }
                }

                Box(
                    modifier = Modifier
                        .border(1.dp, Color.Red, shape = RoundedCornerShape(12.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    TimerComponent(
                        totalSeconds = 600,
                        navController = navController,
                        viewModel = viewModel,
                        dataStore = dataStore,   // ← ini ditambahkan
                        materiKe = materiKe
                    )

                }
            }
        }

        // Soal & Pilihan
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "${questionIndex + 1}. ${question.question}",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(20.dp))
                    .border(1.dp, Color.Black, RoundedCornerShape(20.dp))
                    .padding(16.dp)
            ) {
                question.options.forEachIndexed { index, option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .selectable(
                                selected = selectedOption == index,
                                onClick = { selectedOption = index }
                            )
                    ) {
                        RadioButton(
                            selected = selectedOption == index,
                            onClick = { selectedOption = index }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(option, fontSize = 14.sp, color = Color.Black)
                    }
                }
            }
        }

        // Navigasi bawah
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFB6D9F3))
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { viewModel.prevQuestion() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color(0xFF0B2B69))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Kembali", color = Color(0xFF0B2B69), fontSize = 16.sp)
            }

            Button(
                onClick = {
                    viewModel.submitAnswer(selectedOption)

                    if (viewModel.isLastQuestion()) {
                        val elapsed = 600 - viewModel.timeLeft
                        viewModel.simpanSkor(dataStore, materiKe, elapsed) { skorFinal ->
                            navController.navigate("hasilQuiz/$skorFinal/$elapsed/$materiKe") {
                                popUpTo("materi${materiKe}Quiz") { inclusive = true }
                            }
                        }
                    } else {
                        viewModel.nextQuestion()
                    }
                },
                enabled = selectedOption != -1,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Text(
                    if (viewModel.isLastQuestion()) "Selesai" else "Lanjut",
                    color = Color(0xFF0B2B69),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Next", tint = Color(0xFF0B2B69))
            }
        }
    }
}


@Composable
fun TimerComponent(
    totalSeconds: Int = 600,
    navController: NavController,
    viewModel: QuizViewModel,
    dataStore: DataStoreManager,
    materiKe: Int
) {
    var timeLeft by remember { mutableStateOf(totalSeconds) }

    LaunchedEffect(Unit) {
        while (timeLeft > 0) {
            delay(1000)
            timeLeft--
            viewModel.timeLeft = timeLeft
        }

        // ⏱ Waktu habis, simpan skor
        val elapsed = totalSeconds
        viewModel.simpanSkor(dataStore, materiKe, elapsed) { skorFinal ->
            navController.navigate("hasilQuiz/$skorFinal/$elapsed/$materiKe") {
                popUpTo("materi${materiKe}Quiz") { inclusive = true }
            }
        }
    }

    val minutes = timeLeft / 60
    val seconds = timeLeft % 60
    Text(
        text = String.format("%02d:%02d", minutes, seconds),
        color = Color.Red,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    )
}

@Composable
fun QuizPopUpCard(
    namaMateri: String,
    onClose: () -> Unit,
    onStartQuiz: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .clickable(enabled = false) {}
    ) {
        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(24.dp),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .widthIn(min = 280.dp, max = 380.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("🎉 Perhatian! 🎉", fontWeight = FontWeight.Bold, fontSize = 22.sp, color = Color(0xFFDAA520))
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Kamu sudah selesai belajar tentang\n\n💚 $namaMateri 💚",
                    color = Color.Black, fontSize = 16.sp, textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    "Sekarang waktunya menguji kemampuan kamu! 💪\n\n📋 Akan ada 5 soal pilihan ganda\n⏰ Dengan waktu 10 menit",
                    fontSize = 15.sp, color = Color.DarkGray, textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Soalnya seru dan mudah kok,\nanggap seperti main game seru 🎮",
                    fontSize = 14.sp, color = Color(0xFF00796B), fontWeight = FontWeight.Medium, textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "🔥 Semangat ya, kami yakin kamu bisa!",
                    fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color(0xFF1976D2), textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(24.dp))
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Button(
                        onClick = onStartQuiz,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                    ) {
                        Text("Mulai Kuis", color = Color.White)
                    }
                }
            }
        }
    }
}

