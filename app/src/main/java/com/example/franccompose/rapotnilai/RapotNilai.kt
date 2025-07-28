package com.example.franccompose.rapotnilai

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.franccompose.quiz.formatTime
import java.text.SimpleDateFormat
import java.util.Locale


fun formatTimestamp(timestampString: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val date = inputFormat.parse(timestampString)

        val outputFormat = SimpleDateFormat("dd MMM yyyy HH:mm", Locale("id", "ID"))
        if (date != null) {
            outputFormat.format(date)
        } else {
            timestampString
        }
    } catch (e: Exception) {
        timestampString
    }
}

@Composable
fun getScoreColor(score: Int): Color {
    return when {
        score >= 80 -> Color(0xFF4CAF50) // Green for 80-100 (Material Green 500)
        score >= 60 -> Color(0xFFFFEB3B) // Yellow for 60-79 (Material Yellow 500)
        else -> Color(0xFFF44336)       // Red for 0-59 (Material Red 500)
    }
}



@Composable
fun RapotScreen(
    namaMateri: String,
    history: List<Triple<Int, Int, String>>,
    materiKe: Int,
    onBackClick: () -> Unit
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 130.dp)
                .windowInsetsPadding(WindowInsets.systemBars),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(screenHeight * 0.05f))

            Text(
                text = "Nilai Quiz",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2F80ED)
            )

            Spacer(modifier = Modifier.height(4.dp))
            Divider(color = Color.Black, thickness = 2.dp)

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Materi ${materiKe}",
                color = Color(0xFFFF9800),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = namaMateri,
                color = Color(0xFF2E7D32),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xE698CEFA), shape = RoundedCornerShape(12.dp))
                    .padding(12.dp)
            ) {
                // HEADER ROW: Order for display is Timestamp, Score, Time
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text("Selesai Pada", fontWeight = FontWeight.Bold) // First column: Timestamp
                    Text("Skor", fontWeight = FontWeight.Bold)       // Second column: Score
                    Text("Waktu", fontWeight = FontWeight.Bold)      // Third column: Time
                }

                Spacer(modifier = Modifier.height(6.dp))
                Divider(color = Color.Black, thickness = 2.dp)

                history.forEach { (skor, waktu, timestamp) -> // Destructuring from Triple is (skor, waktu, timestamp)
                    // Determine the color for the current score
                    val scoreDisplayColor = getScoreColor(skor) // <--- Use the new function here

                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(formatTimestamp(timestamp), fontSize = 14.sp)
                        Text(
                            skor.toString(),
                            fontSize = 18.sp,
                            color = scoreDisplayColor,
                            fontWeight = FontWeight.Bold, // Optionally make it bold for emphasis
                            modifier = Modifier
                                .background(Color.White, RoundedCornerShape(6.dp)) // <--- Tambahkan background putih rounded
                                .padding(horizontal = 6.dp, vertical = 2.dp) // <--- Tambahkan sedikit padding agar tidak terlalu mepet
                        )
                        Text(formatTime(waktu), fontSize = 18.sp)            // Display Time
                    }
                }

                if (history.isEmpty()) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Belum ada quiz yang diselesaikan.", fontSize = 14.sp)
                }
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onBackClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF64B5F6)),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(48.dp)
            ) {
                Text("Kembali", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}