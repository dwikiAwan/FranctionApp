package com.example.franccompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    name: String = "Someone",
    email: String = "someone@gmail.com",
    kelas: String = "6A",
    sekolah: String = "SDN Contoh",
    onLogoutClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "My Profile",
            fontSize = 30.sp,
            color = Color(0xFF0D75BB),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        Image(
            painter = painterResource(id = R.drawable.proficon),
            contentDescription = "Avatar",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(3.dp, Color(0xFF0D75BB), CircleShape)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            "Level 1",
            color = Color(0xFF2E7D32),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(36.dp))

        // NAMA
        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Nama", color = Color(0xFF3F51B5), fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = name,
                onValueChange = {},
                readOnly = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF3F51B5),
                    unfocusedBorderColor = Color(0xFF03A9F4),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // EMAIL
        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Email", color = Color(0xFFFF9800), fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = email,
                onValueChange = {},
                readOnly = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFFF9800),
                    unfocusedBorderColor = Color(0xFFFF9800),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // KELAS
        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Kelas", color = Color(0xFF9C27B0), fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = kelas,
                onValueChange = {},
                readOnly = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF9C27B0),
                    unfocusedBorderColor = Color(0xFFBA68C8),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // SEKOLAH
        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Sekolah", color = Color(0xFF009688), fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = sekolah,
                onValueChange = {},
                readOnly = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF009688),
                    unfocusedBorderColor = Color(0xFF80CBC4),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // TOMBOL
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val buttonModifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(vertical = 6.dp)

            Button(
                onClick = { /* TODO: Simpan */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                shape = RoundedCornerShape(8.dp),
                modifier = buttonModifier
            ) {
                Text("Simpan", color = Color.White)
            }

            Button(
                onClick = { navController.navigate("secScreen") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03A9F4)),
                shape = RoundedCornerShape(8.dp),
                modifier = buttonModifier
            ) {
                Text("Ganti Akun", color = Color.White)
            }

            Button(
                onClick = { navController.navigate("homeScreen") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF0D0D)),
                shape = RoundedCornerShape(8.dp),
                modifier = buttonModifier
            ) {
                Text("Kembali", color = Color.White)
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController())
}
