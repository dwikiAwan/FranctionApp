package com.example.franccompose.materipecahan.materi4



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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.franccompose.R
import com.example.franccompose.materipecahan.PecahanBiasa
import com.example.franccompose.materipecahan.PecahanCampuran

@Composable
fun Pengurangan1Screen(
    navController: NavController,
    title: String = "Materi 4",
    subtitle: String = "Operasi Pengurangan Pecahan",
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
                                "Operasi Pengurangan Pecahan",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )

                            Spacer(modifier = Modifier.height(16.dp))
                            Text("Apa itu Pengurangan Pecahan?", fontWeight = FontWeight.Bold)
                            Text("Pengurangan pecahan adalah mengurangkan satu pecahan dengan pecahan lainnya.")
                            Text("Pada prinsipnya pengurangan pecahan sama dengan penjumlahan pecahan")
                            Text("Secara umum, pengurangan dibagi menjadi dua yaitu : ")
                            Text("\t 1. Pengurangan bilangan Berpenyebut sama")
                            Text("\t 1. Pengurangan bilangan Berpenyebut berbeda ")
                            Text("Contoh : ")
                            Row {
                                PecahanBiasa(5, 6)
                                Text(" - ")
                                PecahanBiasa(2, 6)
                                Text(" = ")
                                PecahanBiasa(3, 6)
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                "1. Pengurangan dengan Penyebut Sama",
                                fontWeight = FontWeight.Bold
                            )
                            Text("Cukup kurangi pembilangnya. Penyebut tetap sama.")
                            Row {
                                PecahanBiasa(5, 8)
                                Text(" - ")
                                PecahanBiasa(3, 8)
                                Text(" = ")
                                PecahanBiasa(2, 8)
                                Text(" = ")
                                PecahanBiasa(1, 4)
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                "2. Pengurangan dengan Penyebut Berbeda",
                                fontWeight = FontWeight.Bold
                            )
                            Text("Samakan penyebut dengan KPK, lalu kurangi pembilangnya.")
                            Row {
                                PecahanBiasa(2, 3)
                                Text(" - ")
                                PecahanBiasa(1, 4)
                                Text(" → KPK dari 3 dan 4 adalah 12 → ")
                                PecahanBiasa(8, 12)
                                Text(" - ")
                                PecahanBiasa(3, 12)
                                Text(" = ")
                                PecahanBiasa(5, 12)
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                            Text("3. Pengurangan Pecahan Campuran", fontWeight = FontWeight.Bold)
                            Text("Pecahan campuran adalah gabungan angka bulat dan pecahan.")
                            Text("Langkah: pisahkan angka bulat, samakan penyebut pecahan, lalu kurangi.")
                            Text("Contoh: ")
                            Row {
                                PecahanCampuran(8, 3, 4)
                                Text(" - ")
                                PecahanCampuran(4, 2, 6)
                            }
                            Text("→ (8 - 4) + (")
                            Row {
                                PecahanBiasa(3, 4)
                                Text(" - ")
                                PecahanBiasa(2, 6)
                                Text(")")
                            }
                            Row {
                                Text("= 4 + ")
                                PecahanBiasa(9, 12)
                                Text(" - ")
                                PecahanBiasa(4, 12)
                                Text(" = ")
                                PecahanCampuran(4, 5, 12)
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                            Text("Contoh Soal:", fontWeight = FontWeight.Bold)
                            Row {
                                PecahanCampuran(3, 5, 10)
                                Text(" - ")
                                PecahanCampuran(1, 9, 10)
                            }
                            Text("Ubah ke pecahan biasa:")
                            Row {
                                PecahanBiasa(35, 10)
                                Text(" - ")
                                PecahanBiasa(19, 10)
                                Text(" = ")
                                PecahanBiasa(16, 10)
                                Text(" = ")
                                PecahanBiasa(8, 5)
                                Text(" = ")
                                PecahanCampuran(1, 3, 5)
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                            Text("Latihan Soal:", fontWeight = FontWeight.Bold)
                            Row {
                                PecahanCampuran(1, 9, 10)
                                Text(" - ")
                                PecahanBiasa(3, 5)
                            }
                            Text("Ubah ke pecahan biasa:")
                            Row {
                                PecahanBiasa(19, 10)
                                Text(" - ")
                                PecahanBiasa(6, 10)
                                Text(" = ")
                                PecahanBiasa(13, 10)
                                Text(" = ")
                                PecahanCampuran(1, 3, 10)
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                            Text("Kesimpulan:", fontWeight = FontWeight.Bold)
                            Text("✔ Jika penyebut sama, langsung kurangi pembilang.")
                            Text("✔ Jika penyebut beda, samakan penyebut dulu.")
                            Text("✔ Untuk pecahan campuran, ubah ke pecahan biasa atau pisahkan angkanya.")

                            Spacer(modifier = Modifier.height(16.dp))
                            Text("Penutup:", fontWeight = FontWeight.Bold)
                            Text("Teruslah berlatih dan jangan takut salah! Matematika akan jadi mudah jika sering dilatih.")



                            Spacer(modifier = Modifier.height(100.dp))
                        }
                    }
                }


                //kolumn
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
                                navController.navigate("materi4Quiz")
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


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Pengurangan1ScreenPreview() {
    Pengurangan1Screen(
        navController = rememberNavController()
    )
}

