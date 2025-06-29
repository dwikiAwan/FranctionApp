package com.example.franccompose.fiturmulaibelajar


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.franccompose.R

@Composable
fun DaftarMateriScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp), // Padding luar
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(24.dp))
                .padding(5.dp)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(50.dp))

                // Tombol "Ayo Belajar !"
                Box(
                    modifier = Modifier
                        .background(Color(0xFFFFC107), shape = RoundedCornerShape(50.dp))
                        .padding(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFF2861A4), shape = RoundedCornerShape(50.dp))
                            .padding(horizontal = 24.dp, vertical = 12.dp)
                    ) {
                        Text(
                            text = "Ayo Belajar !",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Daftar Materi (background biru muda mentok ke bawah)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f) // agar isi mengisi sisa layar & mentok ke bawah
                        .background(Color(0xFFD0E7F9), shape = RoundedCornerShape(24.dp))
                        .padding(10.dp)
                ) {

                    //kolom materi
                    Column {
                        MateriItem(
                            title = "Materi 1",
                            subtitle = "Pengenalan Pecahan",
                            iconRes = R.drawable.doneicon,
                            enabled = true
                        )
                        MateriItem(
                            title = "Materi 2",
                            subtitle = "Membandingkan & Mengurutkan Pecahan",
                            iconRes = R.drawable.prosicon,
                            enabled = true
                        )
                        MateriItem(
                            title = "Materi 3",
                            subtitle = "Operasi Penjumlahan Pecahan",
                            iconRes = R.drawable.secicon,
                            enabled = false
                        )
                        MateriItem(
                            title = "Materi 4",
                            subtitle = "Operasi Pengurangan Pecahan",
                            iconRes = R.drawable.secicon,
                            enabled = false
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MateriItem(
    title: String,
    subtitle: String,
    iconRes: Int,
    enabled: Boolean
) {
    val backgroundColor = if (enabled) Color(0xFF4CAF50) else Color(0xFF707070)
    val textColor = if (enabled) Color.White else Color.LightGray

    var isPressed by remember { mutableStateOf(false) }

    // Animasi untuk scale dan rotasi
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 1.05f else 1f,
        animationSpec = tween(durationMillis = 100)
    )

    val rotation by animateFloatAsState(
        targetValue = if (isPressed) 2f else 0f,
        animationSpec = tween(durationMillis = 100)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .height(100.dp)
            .shadow(8.dp, shape = RoundedCornerShape(12.dp)) // ← shadow ditambahkan
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                rotationZ = rotation
            )
            .background(backgroundColor, shape = RoundedCornerShape(12.dp))
            .padding(10.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isPressed = true
                        tryAwaitRelease()
                        isPressed = false
                    }
                )
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = title,
                color = textColor,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = subtitle,
                color = textColor,
                fontSize = 14.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewDaftarMateriScreen() {
    DaftarMateriScreen(navController = rememberNavController())
}
