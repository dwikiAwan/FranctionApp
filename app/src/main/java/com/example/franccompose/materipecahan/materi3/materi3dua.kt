package com.example.franccompose.materipecahan.materi3

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.franccompose.R
import com.example.franccompose.materipecahan.PecahanBiasa
import com.example.franccompose.materipecahan.PecahanCampuran
import com.example.franccompose.materipecahan.PecahanVertikal

@Composable
fun Penjumlahan2Screen(
    navController: NavController,
    title: String = "Materi 3",
    subtitle: String = "Operasi Penjumlahan Pecahan",
    onNextClick: () -> Unit = {}
) {
    val scrollState = rememberScrollState()
    val isScrollAtBottom = remember {
        derivedStateOf {
            scrollState.value >= scrollState.maxValue
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF4CAF50))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, top = 50.dp, end = 24.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = subtitle,
                        fontSize = 24.sp,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Image(
                    painter = painterResource(id = R.drawable.org2),
                    contentDescription = "Ilustrasi Materi",
                    modifier = Modifier.size(50.dp)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .shadow(8.dp, shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .padding(24.dp)
                ) {
                    Text(
                        "Operasi penjumlahan pada pecahan",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )


                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Metode 1.", fontWeight = FontWeight.Bold)
                    Text("Menjumlahkan Pecahan dengan Penyebut yang sama")
                    Text("Jika penyebut kedua pecahan yang ingin kamu jumlahkan sama, tuliskan penyebut tersebut hanya satu kali sebagai penyebut jawabanmu.")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Contoh:")
                    Text("Contoh:")
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PecahanBiasa(1, 5)
                        Text(" + ")
                        PecahanBiasa(2, 5)
                        Text(" = ")
                        PecahanVertikal("1 + 2", 5)
                        Text(" = ")
                        PecahanBiasa(3, 5)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Penjelasan:")
                    Text("• Jumlahkan pembilang. Pembilang adalah angka yang berada di atas pecahan apa pun.")
                    Text("• Biarkan Penyebutnya, karena penyebutnya sama, cukup jumlahkan pembilang!")
                    Text("• Penyebut tidak berubah karena keduanya memiliki penyebut yang sama.")
                    Text("• Jadi, dari soal di atas, kita mendapatkan jawaban 3/5. " )
                    PecahanBiasa(3, 5)
                    Text("Benar!" )
                    PecahanBiasa(3, 5)
                    Text("adalah bentuk pecahan kita!")

                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Metode 2.", fontWeight = FontWeight.Bold)
                    Text("Menjumlahkan Pecahan dengan Penyebut yang Berbeda")
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PecahanBiasa(2, 3)
                        Text(" + ")
                        PecahanBiasa(4, 6)
                    }
                    Text("Carilah penyebut terkecil yang sama. Artinya, penyebut terkecil yang sama untuk kedua pecahan. Jika penyebut tidak sama, ubah terlebih dahulu keduanya.")
                    Text("Untuk mencari penyebut terkecil yang sama dari kedua pecahan, kamu bisa melakukannya dengan dua metode:")
                    Spacer(modifier = Modifier.height(8.dp))

                    Text("1. Pertama:", fontWeight = FontWeight.Bold)
                    Text("Tuliskan kelipatannya")
                    Text("Kelipatan 3: 3, 6, 9, 12, 15, 18")
                    Text("Kelipatan 6: 6, 12, 18, 20")
                    Text("Berapa angka terkecil yang merupakan kelipatan dari keduanya?")
                    Text("12! Itulah penyebut terkecil yang sama.")

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("2. Kedua:", fontWeight = FontWeight.Bold)//Lembar 2

                    Text("Faktorisasi prima.", color = Color.Blue, fontWeight = FontWeight.Bold)
                    Text("Jika kamu mengetahui tentang faktor, kamu bisa melakukan faktorisasi prima. Artinya, kamu mencari angka yang menyusun penyebut.")
                    Text("Untuk angka 4, faktornya adalah 2 dan 2. Untuk angka 3, faktornya adalah 3 dan 1.")
                    Text("Kemudian, kalikan semuanya: 3 × 2 × 2 = 12. Penyebut terkecil yang sama!")

                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PecahanVertikal("2×4", 12)
                        Text(" + ")
                        PecahanVertikal("3×3", 12)
                        Text(" = ")
                        PecahanBiasa(8, 12)
                        Text(" + ")
                        PecahanBiasa(9, 12)
                        Text(" = ")
                        PecahanBiasa(17, 12)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("→ Mengubah ke pecahan campuran: ")
                        PecahanCampuran(1, 5, 12)
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Ketiga:", fontWeight = FontWeight.Bold, color = Color.Blue)
                    Text("Kalikan semua angka untuk angka yang kecil.")
                    Text("Dalam beberapa soal, misalnya soal uji, kamu bisa mengalikan kedua angka 3 × 4 = 12.")
                    Text("Akan tetapi jika angka penyebut besar: jangan lakukan hal ini!")
                    Text("Kamu tidak ingin mengalikan 56 × 44 dan bersusah-susah untuk mendapatkan hasil sebesar 2.464!")

                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Metode 3.", fontWeight = FontWeight.Bold)
                    Text("Menjumlahkan Pecahan campuran dan biasa")
                    Text("1. Ubah pecahan campuran menjadi pecahan biasa.")
                    Text("Pecahan campuran adalah pecahan yang memiliki bilangan cacah dan pecahan.")
                    Text("Pecahan biasa adalah pecahan yang pembilangnya (angka atas) lebih besar daripada penyebutnya (angka bawah).")

                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Contoh:")
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PecahanCampuran(1, 1, 12)
                        Text(" + ")
                        PecahanCampuran(2, 1, 8)
                        Text(" = ")
                        PecahanBiasa(13, 12)
                        Text(" + ")
                        PecahanBiasa(17, 8)
                    }


                    Spacer(modifier = Modifier.height(8.dp))
                    Text("→ Untuk contoh pada bagian ini, kita akan menggunakan")
                    Row {
                        PecahanBiasa(13, 12)
                        Text(" dan ")
                        PecahanBiasa(17, 8)
                    }
                    Text("2. Carilah penyebut yang sama. Dengan menuliskan kelipatan, menuliskan faktorisasi prima, atau menyalin penyebut:")
                    Text("12: 12, 24, 36")
                    Text("8: 8, 16, 24")
                    Text("→ Penyebut terkecil yang sama: 24")

                    Spacer(modifier = Modifier.height(8.dp))//Lembar 3
                    Text(
                        "3. Kalikan Pembilang dan Penyebut untuk mencari pecahan yang setara",
                        fontWeight = FontWeight.Bold
                    )
                    Text("Penyebut harus diubah menjadi 24.")
                    Text("Bagaimana caramu membuat 12 menjadi 24?")
                    Text("2 × 8 menjadi 24")
                    Text("Tapi jangan lupa untuk mengalikan pembilangnya juga")
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        PecahanBiasa(13 * 2, 12 * 2)
                        Text(" + ")
                        PecahanBiasa(17 * 3, 8 * 3)
                        Text(" = ")
                        PecahanBiasa(26, 24)
                        Text(" + ")
                        PecahanBiasa(51, 24)
                        Text(" = ")
                        PecahanBiasa(77, 24)
                    }
                    Row {
                        Text("= ")
                        PecahanCampuran(3, 5, 24)
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Text("Metode 4", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text("Menjumlahkan Pecahan tanpa mencari KPK", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        PecahanBiasa(1, 2)
                        Text(" + ")
                        PecahanBiasa(3, 4)
                        Text(" + ")
                        PecahanBiasa(5, 8)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("- Kalikan 1 dengan pembilang pecahan lainnya")
                    Text("- Kalikan 1 dengan 4 dan 8 → [32]")
                    Text("1 × 4 × 8 = 32")
                    Text("3 × 2 × 8 = 48")
                    Text("5 × 4 × 2 = 40")

                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Jumlahkan semuanya: 32 + 48 + 40 = 120")
                    Text("→ 120 menjadi nilai pembilang")
                    Text("Kalikan seluruh penyebut pecahan: 2 × 4 × 8 = 64")

                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Dan hasilnya adalah: ")
                    Row {
                        PecahanBiasa(120, 64)
                        Text(" → bisa kita kecilkan lagi → 1")
                        PecahanVertikal(56, 64)
                        Text("Pembilang dan Penyebut kita bagi dengan 8")
                        Text("=")
                        PecahanCampuran(1, 7, 8)
                    }


                    //Lembar 4
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Latihan:", fontWeight = FontWeight.Bold, color = Color.Blue)
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PecahanBiasa(2, 4)
                        Text(" + ")
                        PecahanBiasa(1, 5)
                        Text(" + ")
                        PecahanBiasa(1, 6)
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "Langkah pertama: Samakan penyebut dengan KPK",
                        fontWeight = FontWeight.Bold
                    )
                    Text("KPK dari 4, 5, dan 6 adalah 60", fontSize = 16.sp)
                    Text("Langkah kedua : Ubah setiap pecahan ke penyebut -> 60", fontSize = 16.sp)
                    Row {
                        PecahanBiasa(2, 4)
                        Text("= ")
                        PecahanVertikal("2 x 15", "4 x 15")
                        Text("= ")
                        PecahanBiasa(30, 60)
                    }
                    Row {
                        PecahanBiasa(1, 5)
                        Text("= ")
                        PecahanVertikal("1 x 12", "5 x 12")
                        Text("= ")
                        PecahanBiasa(12, 60)
                    }

                    Row{
                        PecahanBiasa(1, 6)
                        Text("= ")
                        PecahanVertikal("1 x 10", "6 x 10")
                        Text("= ")
                        PecahanBiasa(10, 60)
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Langkah 3: Jumlahkan Pecahan", fontWeight = FontWeight.Bold)
                    Row {
                        PecahanBiasa(30, 60)
                        Text(" + ")
                        PecahanBiasa(12, 60)
                        Text(" + ")
                        PecahanBiasa(10, 60)
                        Text(" = ")
                        PecahanBiasa(52, 60)
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Langkah 4: Sederhanakan Pecahan", fontWeight = FontWeight.Bold)
                    Text("Cari FPB dari 52 dan 60 adalah 4:")
                    Row {
                        PecahanBiasa(52, 60)
                        PecahanVertikal(": 2", ": 2")
                        Text(" = ")
                        PecahanBiasa(26, 30)
                    }
                    Text(" Lalu ")
                    Row {
                        PecahanVertikal("26 : 2", "30 : 2")
                        Text(" = ")
                        PecahanBiasa(13, 15)
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Kesimpulan:", fontWeight = FontWeight.Bold)
                    Row {
                        PecahanBiasa(2, 4)
                        Text(" + ")
                        PecahanBiasa(1, 5)
                        Text(" + ")
                        PecahanBiasa(1, 6)
                        Text(" = ")
                        PecahanBiasa(13, 15)


                        Spacer(modifier = Modifier.height(100.dp))
                    }
                }
            }

        }//kolumn
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .shadow(8.dp)
                .background(Color.White)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        navController.navigate("daftarMateri") {
                            popUpTo("hasilQuiz") { inclusive = true }
                        }
                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800)),
                    modifier = Modifier
                        .width(140.dp)
                        .height(48.dp)
                ) {
                    Text("Kembali", color = Color.White, fontSize = 16.sp)
                }
                Button(
                    onClick = {
                        navController.navigate("materi3Quiz")
                    },
                    enabled = isScrollAtBottom.value,
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isScrollAtBottom.value) Color(0xFF00BCD4) else Color(
                            0xFF03A9F4
                        ).copy(alpha = 0.5f)
                    ),
                    modifier = Modifier
                        .width(140.dp)
                        .height(48.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Quiz", color = Color.White, fontSize = 16.sp)
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Quiz",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}

