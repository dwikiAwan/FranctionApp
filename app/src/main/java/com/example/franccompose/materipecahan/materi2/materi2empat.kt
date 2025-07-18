package com.example.franccompose.materipecahan.materi2

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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.franccompose.R
import com.example.franccompose.materipecahan.PecahanBiasa

@Composable
fun BandingUrut4Screen(
    navController: NavController,
    title: String = "Materi 2",
    subtitle: String = "Membandingkan dan Mengurutkan Pecahan",
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
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Membandingkan pecahan ",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Membandingkan pecahan adalah proses menentukan pecahan mana yang lebih besar, lebih kecil, atau sama dengan pecahan lainnya.",
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Untuk membandingkan pecahan, biasanya penyebutnya disamakan terlebih dahulu agar lebih mudah dibandingkan, kemudian pembilangnya dibandingkan.",
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Contoh 1:", fontWeight = FontWeight.Bold)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PecahanBiasa(5, 12)
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("...")
                        Spacer(modifier = Modifier.width(12.dp))
                        PecahanBiasa(7, 12)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text("Contoh 2:", fontWeight = FontWeight.Bold)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PecahanBiasa(5, 7)
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("...")
                        Spacer(modifier = Modifier.width(12.dp))
                        PecahanBiasa(9, 13)
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Text("Dengan melihat kedua contoh di atas kita dapat membandingkan pecahan dengan tanda-tanda:", fontSize = 16.sp)
                    Text("• < (Lebih Kecil dari)")
                    Text("• > (Lebih Besar dari)")
                    Text("• = (Sama Dengan)")

                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Lihat apakah kedua pecahan memiliki penyebut yang sama atau tidak. Itu merupakan langkah pertama dalam membandingkan pecahan.",
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Penyebut adalah bilangan di bagian bawah pecahan, sedangkan pembilang adalah bilangan di bagian atas.",
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Misalnya, pecahan ", fontSize = 16.sp)
                        PecahanBiasa(5, 7)
                        Text(" dan ")
                        PecahanBiasa(9, 13)
                    }

                    Text(
                        text = "tidak memiliki penyebut yang sama, karena 7 tidak sama dengan 13.",
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Jadi, Anda harus melakukan beberapa langkah untuk dapat membandingkan kedua pecahan tersebut.",
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text("Kasus Pertama", fontWeight = FontWeight.Bold, fontSize = 18.sp)

                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PecahanBiasa(5, 12)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("dan")
                        Spacer(modifier = Modifier.width(8.dp))
                        PecahanBiasa(7, 12)
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Jika penyebut kedua pecahan sudah sama, yang harus dilakukan hanyalah melihat pembilang untuk mengetahui pecahan mana yang lebih besar.",
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Misalnya, saat membandingkan pecahan 5/12 dan 7/12, 7 lebih besar daripada 5.",
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Hasilnya: ")
                        PecahanBiasa(5, 12)
                        Text(" < ")
                        PecahanBiasa(7, 12)
                        Text(" artinya 5 lebih kecil dari 7")
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PecahanBiasa(7, 12)
                        Text(" > ")
                        PecahanBiasa(5, 12)
                        Text(" artinya 7 lebih besar dari 5")
                    }

                    Spacer(modifier = Modifier.height(32.dp))
                    Text("Kasus Kedua", fontWeight = FontWeight.Bold, fontSize = 18.sp)

                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PecahanBiasa(5, 7)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("dan")
                        Spacer(modifier = Modifier.width(8.dp))
                        PecahanBiasa(9, 13)
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Jika penyebut kedua pecahan berbeda, temukan penyebut bersama.",
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Untuk dapat membandingkan pecahan, Anda harus menemukan penyebut bersama agar dapat diketahui pecahan mana yang lebih besar.",
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Jika melakukan penjumlahan dan pengurangan pecahan dengan penyebut berbeda, paling baik menemukan penyebut bersama terkecil.",
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Tetapi karena hanya membandingkan pecahan, Anda dapat mengambil jalan pintas dengan mengalikan penyebut kedua pecahan untuk menemukan penyebut bersama.",
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text("7 × 13 = 91. Jadi, penyebut yang baru adalah 91.", fontSize = 16.sp)

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Ubah pembilang kedua pecahan. Karena penyebut sudah diubah, pembilang juga perlu diubah agar nilai pecahan tetap sama. " +
                                "Untuk itu, kalikan pembilang setiap pecahan dengan bilangan yang sama yang digunakan untuk mengalikan penyebut.",
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Berikut ini caranya:", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PecahanBiasa(5, 7)
                        Text(" = ")
                        PecahanBiasa(65, 91)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("• ")
                        Text("5×13 = 65")
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("• ")
                        Text("7×13 = 91")
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PecahanBiasa(9, 13)
                        Text(" = ")
                        PecahanBiasa(63, 91)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("• ")
                        Text("9×7 = 63")
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("• ")
                        Text("13×7 = 91")
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PecahanBiasa(65, 91)
                        Text(" > ")
                        PecahanBiasa(63, 91)
                        Text(" artinya ")
                        PecahanBiasa(5, 7)
                        Text(" lebih besar dari ")
                        PecahanBiasa(9, 13)
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Contoh :",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Blue
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Membandingkan Pecahan",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Red
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Setelah mengubah penyebut dan pembilang, dilanjutkan dengan membandingkan kedua pecahan\n" +
                                "seperti cara membandingkan pecahan dengan penyebut sama."
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Contoh soal:",
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PecahanBiasa(3,5)
                        Text(text = "  ...  ")
                        PecahanBiasa(6,8)
                        Text(text = "  mari kita bandingkan")
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row {
                        PecahanBiasa(3, 5)
                        Text(text = " = ")
                        PecahanBiasa(5, 40)
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Row {
                        PecahanBiasa(6, 30)
                        Text(text = " = ")
                        PecahanBiasa(8, 40)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "(keterangan: KPK dari 5 dan 8 adalah 40)", fontStyle = FontStyle.Italic)

                    Spacer(modifier = Modifier.height(12.dp))

                    Row {
                        Text(text = "Karena 24 < 30 maka ")
                        PecahanBiasa(3, 5)
                        Text(text = " < ")
                        PecahanBiasa(6, 8)
                    }
                }
                Spacer(modifier = Modifier.height(100.dp))
            }
        }

        // BOTTOM NAVIGATION
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .shadow(8.dp)
                .background(Color.White)
                .padding(16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        navController.navigate("materi2tiga")
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
                        navController.navigate("materi2Quiz")
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

