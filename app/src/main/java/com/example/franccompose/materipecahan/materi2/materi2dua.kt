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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.franccompose.R
import com.example.franccompose.materipecahan.PecahanBiasa

@Composable
fun BandingUrut2Screen(
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
                        text = "Mengurutkan Pecahan",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Mengurutkan Pecahan Bersenebut Sama",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "• Untuk mengurutkan pecahan bernyebut sama, sama dengan mengurutkan bilangan cacah.",
                        fontSize = 16.sp
                    )
                    Text("Contoh:", fontWeight = FontWeight.Bold)
                    Row {
                        PecahanBiasa(2, 7)
                        Text(", ")
                        PecahanBiasa(3, 7)
                        Text(", ")
                        PecahanBiasa(6, 7)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Setelah diurutkan dari nilai terkecil ke terbesar menjadi:")
                    Row {
                        PecahanBiasa(2, 7)
                        Text(" < ")
                        PecahanBiasa(3, 7)
                        Text(" < ")
                        PecahanBiasa(6, 7)
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Mengurutkan Pecahan Berbeda Penyebut",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue
                    )
                    Text("• Ubah penyebut menjadi sama menggunakan KPK.")
                    Text("Contoh:")
                    Row {
                        PecahanBiasa(1, 5)
                        Text(", ")
                        PecahanBiasa(3, 8)
                        Text(", ")
                        PecahanBiasa(7, 10)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("KPK dari 5, 8, dan 10 adalah 40")

                    Row {
                        PecahanBiasa(1, 5)
                        Text(" = ")
                        PecahanBiasa(8, 40)
                    }
                    Row {
                        PecahanBiasa(3, 8)
                        Text(" = ")
                        PecahanBiasa(15, 40)
                    }
                    Row {
                        PecahanBiasa(7, 10)
                        Text(" = ")
                        PecahanBiasa(28, 40)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Urutan dari nilai terkecil ke terbesar:")
                    Row {
                        PecahanBiasa(8, 40)
                        Text(" < ")
                        PecahanBiasa(15, 40)
                        Text(" < ")
                        PecahanBiasa(28, 40)
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Mengurutkan Pecahan Campuran",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue
                    )
                    Text("1. Ubah ke bentuk pecahan biasa")
                    Row {
                        Text("Contoh: ")
                        Text("2 ")
                        PecahanBiasa(1, 2)
                        Text(" → ")
                        PecahanBiasa(5, 2)
                    }
                    Text("2. Samakan penyebut semua pecahan")
                    Row {
                        PecahanBiasa(5, 2)
                        Text(", ")
                        PecahanBiasa(7, 3)
                        Text(", ")
                        PecahanBiasa(11, 4)
                    }
                    Text("KPK dari 2, 3, dan 4 = 12")
                    Row {
                        PecahanBiasa(5, 2)
                        Text(" = ")
                        PecahanBiasa(30, 12)
                    }
                    Row {
                        PecahanBiasa(7, 3)
                        Text(" = ")
                        PecahanBiasa(28, 12)
                    }
                    Row {
                        PecahanBiasa(11, 4)
                        Text(" = ")
                        PecahanBiasa(33, 12)
                    }
                    Text("Urutan: ")
                    Row {
                        PecahanBiasa(28, 12)
                        Text(" < ")
                        PecahanBiasa(30, 12)
                        Text(" < ")
                        PecahanBiasa(33, 12)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "3. Membandingkan pembilang pecahannya",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        PecahanBiasa(21, 64)
                        Text(" < ")
                        PecahanBiasa(36, 64)
                    }
                    Text(
                        text = "Karena 21 < 36, maka urutan pita dari yang paling pendek sampai paling panjang adalah:",
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        PecahanBiasa(7, 8)
                        Text(", ")
                        Text("1 ")
                        PecahanBiasa(1, 2)
                        Text(", ")
                        Text("2 ")
                        PecahanBiasa(1, 3)
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Mengurutkan pecahan dengan membandingkan bilangan bulatnya",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "1. Mengubah pecahan tidak murni menjadi pecahan campuran",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Contoh:")
                    Row {
                        PecahanBiasa(8, 7)
                        Text(", ")
                        Text("1 ")
                        PecahanBiasa(1, 2)
                        Text(", ")
                        PecahanBiasa(7, 3)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• Kita akan mengubah pecahan tidak murni menjadi pecahan campuran")
                    Text("• Pecahan tidak murni adalah yang pembilangnya lebih besar dari penyebut")
                    Text("• Misalnya:")
                    Row {
                        Text(" 8 ÷ 3 = 2 sisa 2 → ")
                        Text("2 ")
                        PecahanBiasa(2, 3)
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "2. Membandingkan bilangan bulatnya",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Bandingkan: ")
                    Row {
                        Text("2 ")
                        PecahanBiasa(2, 3)
                        Text(", ")
                        Text("0 ")
                        PecahanBiasa(7, 8)
                        Text(", ")
                        Text("1 ")
                        PecahanBiasa(1, 2)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Kenapa kita beri nilai 0 pada pecahan ")
                        PecahanBiasa(7, 8)
                        Text(" karena itu adalah pecahan murni (kurang dari 1)")
                    }

                    Text("Jadi hasil urutannya adalah:")
                    Row {
                        PecahanBiasa(7, 8)
                        Text(" < ")
                        Text("1 ")
                        PecahanBiasa(1, 2)
                        Text(" < ")
                        Text("2 ")
                        PecahanBiasa(2, 3)
                    }

                    Spacer(modifier = Modifier.height(100.dp))
                }
            }

            // BOTTOM NAVIGATION

        }
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
                        navController.navigate("materi2satu")
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
                        navController.navigate("materi2tiga")
                    },
                    enabled = isScrollAtBottom.value,
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isScrollAtBottom.value) Color(0xFF4CAF50) else Color(0xFF4CAF50).copy(alpha = 0.5f)
                    ),
                    modifier = Modifier
                        .width(140.dp)
                        .height(48.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Lanjut", color = Color.White, fontSize = 16.sp)
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Lanjut",
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
fun BandingUrut2ScreenPreview() {
    BandingUrut2Screen(
        navController = rememberNavController()
    )
}

