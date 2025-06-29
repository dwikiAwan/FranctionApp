package com.example.franccompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.franccompose.fiturmulaibelajar.datastore.DataStoreManager
import com.example.franccompose.ui.theme.FrancComposeTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisScreen(navController: NavController) {
    val nama = remember { mutableStateOf("") }
    var selectedAngka by remember { mutableStateOf("-") }
    var expandedAngka by remember { mutableStateOf(false) }
    val angkaList = listOf("-", "1", "2", "3", "4", "5", "6")

    var selectedHuruf by remember { mutableStateOf("-") }
    var expandedHuruf by remember { mutableStateOf(false) }
    val hurufList = listOf("-", "A", "B", "C", "D", "E")

    var showDialog by remember { mutableStateOf(false) }
    var showWarning by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = remember { DataStoreManager(context) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Konfirmasi") },
            text = { Text("Apakah data kamu sudah benar?") },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    val namaFix = nama.value.trim()
                    val kelasFix = "${selectedAngka}${selectedHuruf}"

                    scope.launch {
                        dataStore.saveLastUser(namaFix, kelasFix)
                        navController.navigate("homeScreen")
                    }
                }) {
                    Text("Iya")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Tidak")
                }
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(640.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text("Selamat Datang", fontWeight = FontWeight.Bold, fontSize = 28.sp, color = Color(0xFF5D5FEF))
            Text("Silakan isi datamu dulu ya...", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xFF5D5FEF))

            Image(
                painter = painterResource(id = R.drawable.formicon),
                contentDescription = null,
                modifier = Modifier.size(180.dp)
            )

            OutlinedTextField(
                value = nama.value,
                onValueChange = { nama.value = it },
                label = { Text("Nama") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF4CAF50),
                    unfocusedBorderColor = Color(0xFF5D5FEF),
                    focusedLabelColor = Color(0xFF4CAF50),
                    unfocusedLabelColor = Color(0xFF5D5FEF),
                    focusedTextColor = Color(0xFF4CAF50),
                    unfocusedTextColor = Color(0xFF5D5FEF),
                    cursorColor = Color(0xFF5D5FEF)
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Dropdown kelas angka
                ExposedDropdownMenuBox(
                    expanded = expandedAngka,
                    onExpandedChange = { expandedAngka = !expandedAngka },
                    modifier = Modifier.weight(1f)
                ) {
                    OutlinedTextField(
                        value = selectedAngka,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Kelas") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedAngka) },
                        modifier = Modifier.menuAnchor().fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = OutlinedTextFieldDefaults.colors()
                    )
                    ExposedDropdownMenu(
                        expanded = expandedAngka,
                        onDismissRequest = { expandedAngka = false }
                    ) {
                        angkaList.forEach { angka ->
                            DropdownMenuItem(
                                text = { Text(angka) },
                                onClick = {
                                    selectedAngka = angka
                                    expandedAngka = false
                                }
                            )
                        }
                    }
                }

                // Dropdown huruf subkelas
                ExposedDropdownMenuBox(
                    expanded = expandedHuruf,
                    onExpandedChange = { expandedHuruf = !expandedHuruf },
                    modifier = Modifier.weight(1f)
                ) {
                    OutlinedTextField(
                        value = selectedHuruf,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Subkelas") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedHuruf) },
                        modifier = Modifier.menuAnchor().fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = OutlinedTextFieldDefaults.colors()
                    )
                    ExposedDropdownMenu(
                        expanded = expandedHuruf,
                        onDismissRequest = { expandedHuruf = false }
                    ) {
                        hurufList.forEach { huruf ->
                            DropdownMenuItem(
                                text = { Text(huruf) },
                                onClick = {
                                    selectedHuruf = huruf
                                    expandedHuruf = false
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    if (nama.value.isBlank() || selectedAngka == "-" || selectedHuruf == "-") {
                        showWarning = true
                    } else {
                        showWarning = false
                        showDialog = true
                    }
                },
                modifier = Modifier.fillMaxWidth().height(40.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5D5FEF)),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Masuk", fontWeight = FontWeight.Bold, color = Color.White)
            }

            if (showWarning) {
                Text(
                    text = "Mohon isi semua data terlebih dahulu.",
                    color = Color.Red,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text("Ayo Belajar Matematika Pecahan!", fontSize = 18.sp, color = Color(0xFF5D5FEF), fontWeight = FontWeight.Medium)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisScreenPreview() {
    FrancComposeTheme {
        RegisScreen(navController = rememberNavController())
    }
}
