@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.franccompose

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.franccompose.fiturmulaibelajar.datastore.DataStoreManager
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    navController: NavController,
    onLogoutClick: () -> Unit = {}
) {
    val context = LocalContext.current
    val dataStore = remember { DataStoreManager(context) }
    val scope = rememberCoroutineScope()

    var name by remember { mutableStateOf("") }
    var kelas by remember { mutableStateOf("") }
    var emailState by remember { mutableStateOf("") }
    var sekolahState by remember { mutableStateOf("") }
    var showWarning by remember { mutableStateOf(false) }
    var warningMessage by remember { mutableStateOf("") }
    var akunDiganti by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    var currentLevel by remember { mutableStateOf(1) }

    // Ambil data user
    LaunchedEffect(Unit) {
        val user = dataStore.getLastUser()
        user?.let {
            name = it.first
            kelas = it.second
            currentLevel = dataStore.getFinalLevel(name, kelas)

            val (email, sekolah) = dataStore.getUserInfo(name, kelas)
            emailState = email
            sekolahState = sekolah
        }
    }

    Scaffold(
        containerColor = Color.White,
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
                .background(Color.White)
                .windowInsetsPadding(WindowInsets.systemBars)
            ,
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("My Profile", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0D75BB))

            Image(
                painter = painterResource(id = R.drawable.proficon),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color(0xFF0D75BB), CircleShape)
            )

            Text(text = "Level $currentLevel", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF2E7D32))

            if (akunDiganti) {
                Text("Akun berhasil diganti", color = Color(0xFF4CAF50), fontSize = 13.sp)
            }

            ProfileField("Nama", name, readOnly = true, borderColor = Color(0xFF3F51B5))
            ProfileField("Email", emailState, onValueChange = { emailState = it }, borderColor = Color(0xFFFF9800))
            ProfileField("Kelas", kelas, readOnly = true, borderColor = Color(0xFF9C27B0))
            ProfileField("Sekolah", sekolahState, onValueChange = { sekolahState = it }, borderColor = Color(0xFF009688))

            if (showWarning) {
                Text(
                    text = warningMessage,
                    color = Color.Red,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        if (emailState.isBlank() || sekolahState.isBlank()) {
                            showWarning = true
                            warningMessage = "Email dan Sekolah tidak boleh kosong!"
                        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailState).matches()) {
                            showWarning = true
                            warningMessage = "Format email tidak valid!"
                        } else {
                            showWarning = false
                            scope.launch {
                                dataStore.saveUserInfo(name, kelas, emailState, sekolahState)
                                snackbarHostState.showSnackbar("Data berhasil disimpan")
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Simpan", color = Color.White, fontSize = 13.sp)
                }

                Button(
                    onClick = {
                        scope.launch {
                            dataStore.clearLastUser()
                        }
                        emailState = ""
                        sekolahState = ""
                        akunDiganti = true
                        showWarning = false
                        navController.navigate("register")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03A9F4)),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        "Ganti Akun",
                        color = Color.White,
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center // Add this line
                    )
                }

                Button(
                    onClick = { navController.navigate("homeScreen") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF0D0D)),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Kembali", color = Color.White, fontSize = 13.sp)
                }
            }
        }
    }
}


@Composable
fun ProfileField(
    label: String,
    value: String,
    onValueChange: ((String) -> Unit)? = null,
    readOnly: Boolean = false,
    borderColor: Color = Color.Gray
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(label, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = borderColor)
        Spacer(modifier = Modifier.height(2.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange ?: {},
            readOnly = readOnly,
            colors = outlinedTextFieldColors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor.copy(alpha = 0.6f),
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                cursorColor = borderColor
            ),
            textStyle = LocalTextStyle.current.copy(fontSize = 14.sp),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
