package com.example.franccompose.materipecahan.ujitingkat2

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.franccompose.fiturmulaibelajar.datastore.DataStoreManager
import com.example.franccompose.ujitingkat.ujitingkat1.UjiTingkatViewModel
import kotlinx.coroutines.delay

@Composable
fun UjiTingkat2Screen(
    viewModel: UjiTingkatViewModel,
    navController: NavController,
    dataStoreManager: DataStoreManager,
    onQuizFinished: (Int) -> Unit,
    materiKe: Int
) {
    val context = LocalContext.current
    BackHandler(enabled = true) {
        Toast.makeText(context, "Selesaikan Ujian terlebih dahulu!", Toast.LENGTH_SHORT).show()
    }
    // Muat soal hanya sekali saat composable pertama kali muncul
    LaunchedEffect(Unit) {
        viewModel.loadUjiTingkat(UjiTingkatViewModel.UjiLevel.DUA)
        viewModel.setDataStore(dataStoreManager)
    }

    val questions by viewModel.questions.collectAsState()
    val currentIndex by viewModel.currentQuestionIndex
    val selectedAnswers = viewModel.selectedAnswers

    val selectedOption = remember(currentIndex) {
        mutableStateOf(selectedAnswers[currentIndex] ?: -1)
    }
    var showStartPopup by remember { mutableStateOf(true) }

    if (showStartPopup) {
        CardPopupAwalUjian2(
            onStart = {
                showStartPopup = false
            }
        )
        return
    }

    if (questions.isEmpty()) {
        Text("Soal belum dimuat", modifier = Modifier.padding(16.dp))
        return
    }

    val currentQuestion = questions[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFF5D4037),
//                    shape = RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp,topStart = 50.dp, topEnd = 50.dp)
                )
                .padding(top = 16.dp, bottom = 32.dp) // Adjusted top padding slightly
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("💡", fontSize = 24.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Uji Tingkat 2",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFFC107)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("💡", fontSize = 30.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Timer di tengah (center)
            Box(
                modifier = Modifier
                    .border(1.dp, Color.White, shape = RoundedCornerShape(12.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                TimerComponent(
                    totalSeconds = 1800, // 30 minutes (since 10 soal within 30 menit from popup)
                    navController = navController,
                    viewModel = viewModel,
                    materiKe = materiKe
                )
            }
        }

        // KONTEN UTAMA
        Column(
            modifier = Modifier
                .weight(1f) // This makes it take all remaining vertical space
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp) // Keep internal padding
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            // Pertanyaan
            // Tampilkan pertanyaan
            Box(
                modifier = Modifier
                    .weight(0.03f)
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                if (currentQuestion.customQuestionContent != null) {
                    currentQuestion.customQuestionContent.invoke(currentIndex)
                } else {
                    Text(
                        text = "${currentIndex + 1}. ${currentQuestion.question}",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                }
            }

            Box(
                modifier = Modifier
                    .weight(0.07f)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(20.dp))
                        .border(1.dp, Color.Black, RoundedCornerShape(20.dp))
                        .padding(16.dp)
                ) {
                    val optionsText = currentQuestion.options
                    val optionsComposable = currentQuestion.optionContents

                    // Jika pakai opsi teks biasa
                    if (!optionsText.isNullOrEmpty()) {
                        optionsText.forEachIndexed { index, option ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                                    .selectable(
                                        selected = selectedOption.value == index,
                                        onClick = {
                                            selectedOption.value = index
                                            viewModel.submitAnswer(index)
                                        }
                                    )
                            ) {
                                RadioButton(
                                    selected = selectedOption.value == index,
                                    onClick = null
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(option, fontSize = 14.sp, color = Color.Black)
                            }
                        }
                    }
                    // Jika pakai opsi Composable (contohnya PecahanBiasa)
                    else if (!optionsComposable.isNullOrEmpty()) {
                        optionsComposable.forEachIndexed { index, content ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                                    .selectable(
                                        selected = selectedOption.value == index,
                                        onClick = {
                                            selectedOption.value = index
                                            viewModel.submitAnswer(index)
                                        }
                                    )
                            ) {
                                RadioButton(
                                    selected = selectedOption.value == index,
                                    onClick = null
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                content()
                            }
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .windowInsetsPadding(WindowInsets.systemBars)

        ) {
            Button(
                onClick = { viewModel.prevQuestion() },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD32F2F).copy(alpha = if (currentIndex > 0) 1f else 0.5f)
                ),
                enabled = currentIndex > 0
            ) {
                Text("Kembali", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = {
                    if (viewModel.isLastQuestion()) {
                        val durasi = 1800 - viewModel.timeLeft
                        val skorFinal = viewModel.hitungSkor()

                        viewModel.simpanSkorUjiTingkat(
                            skor = skorFinal,
                            waktu = durasi,
                            materiKe = materiKe
                        ) {
                            navController.navigate("hasiluji2/$skorFinal/$durasi/$materiKe") {
                                popUpTo("ujitingkat2") { inclusive = true }
                            }
                        }


                    } else {
                        viewModel.nextQuestion()
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                shape = RectangleShape,
                enabled = selectedOption.value != -1,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFBC02D), // This is for the enabled state (100% opacity)
                    disabledContainerColor = Color(0xFF6F6F6E).copy(alpha = 0.8f) // <-- Set 80% opacity for disabled state
                )
            ) {
                Text(
                    text = if (viewModel.isLastQuestion()) "Selesai" else "Lanjut",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun TimerComponent(
    totalSeconds: Int = 1800, // 1 jam
    navController: NavController,
    viewModel: UjiTingkatViewModel,
    materiKe: Int
) {
    var timeLeft by remember { mutableStateOf(totalSeconds) }

    LaunchedEffect(Unit) {
        while (timeLeft > 0) {
            delay(1000)
            timeLeft--
            viewModel.timeLeft = timeLeft
        }

        if (timeLeft == 0) {
            val elapsed = totalSeconds
            val skorFinal = viewModel.score.value // atau viewModel.hitungSkor() jika kamu punya fungsi itu

            viewModel.simpanSkorUjiTingkat(
                skor = skorFinal,
                waktu = elapsed,
                materiKe = materiKe
            ) {
                navController.navigate("hasiluji2/$skorFinal/$elapsed/$materiKe") {
                    popUpTo("ujitingkat2") { inclusive = true }
                }
            }

        }

    }

    val waktuFormat = remember(timeLeft) {
        String.format("%02d:%02d:%02d", timeLeft / 3600, (timeLeft % 3600) / 60, timeLeft % 60)
    }
    Text(
        text = waktuFormat,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )
}
@Composable
fun CardPopupAwalUjian2(
    onStart: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF5D4037))
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(16.dp))
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "🔔 Persiapan Ujian",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Kamu akan memulai Uji Tingkat 2.\nTerdapat 10 soal yang harus kamu selesaikan dalam waktu 30 Menit.",
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Pada Uji Tingkat 2 ini kamu akan di uji pada materi\n1. Operasi Penjumlahan Pecahan\n2. Operasi Pengurangan Pecahan",
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "🔥 Semangat ya, kami yakin kamu bisa !",
                fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color(0xFF1976D2), textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = onStart,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF1A09)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Mulai Ujian", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}
