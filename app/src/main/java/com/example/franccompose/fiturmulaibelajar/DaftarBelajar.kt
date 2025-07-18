package com.example.franccompose.fiturmulaibelajar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.franccompose.fiturmulaibelajar.datastore.DataStoreManager
import com.example.franccompose.fiturmulaibelajar.viewmodel.MateriViewModel
import kotlinx.coroutines.launch


@Composable
fun DaftarMateriScreen(
    navController: NavController,
    viewModel: MateriViewModel
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalContext.current as androidx.lifecycle.LifecycleOwner
    val dataStore = remember { DataStoreManager(context) }

    val materiList = viewModel.materiList
    val sudahInit = remember { mutableStateOf(false) }

    // Ambil user dari DataStore langsung
    LaunchedEffect(Unit) {
        val user = dataStore.getLastUser()
        if (user != null && !sudahInit.value) {
            val (nama, kelas) = user
            viewModel.loadUser(nama, kelas)
            sudahInit.value = true
        }
    }

    val scope = rememberCoroutineScope()

    DisposableEffect(lifecycleOwner) {
        val observer = androidx.lifecycle.LifecycleEventObserver { _, event ->
            if (event == androidx.lifecycle.Lifecycle.Event.ON_RESUME) {
                scope.launch {
                    val user = dataStore.getLastUser()
                    user?.let { (nama, kelas) -> viewModel.loadUser(nama, kelas) }
                }
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(16.dp))
        Box(
            Modifier
                .background(Color(0xFFFFC107), RoundedCornerShape(50.dp))
                .padding(8.dp)
        ) {
            Box(
                Modifier
                    .background(Color(0xFF2861A4), RoundedCornerShape(50.dp))
                    .padding(horizontal = 24.dp, vertical = 12.dp)
            ) {
                Text(
                    "Ayo Belajar !",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        Box(
            Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color(0xFFD0E7F9), RoundedCornerShape(24.dp))
                .padding(10.dp)
        ) {
            if (materiList.isEmpty()) {
                Text("Memuat materi...", Modifier.align(Alignment.Center))
            } else {
                Column(
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    materiList.forEach { materi ->
                        MateriItem(
                            title = materi.title,
                            subtitle = materi.subtitle,
                            iconRes = materi.iconRes,
                            enabled = materi.isUnlocked,
                            isSedangDiproses = materi.isSedangDiproses,
                            isSelesai = materi.isSelesai
                        ) {
                            when (materi.id) {
                                1 -> navController.navigate("materi1satu")
                                2 -> navController.navigate("materi2satu")
                                3 -> navController.navigate("ujitingkat1")
                                4 -> navController.navigate("materi3satu")
                                5 -> navController.navigate("materi4satu")
                                6 -> navController.navigate("ujitingkat2")
                            }
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("homeScreen") },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2861A4)),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Kembali", color = Color.White, fontSize = 16.sp)
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
fun MateriItem(
    title: String,
    subtitle: String,
    iconRes: Int,
    enabled: Boolean,
    isSedangDiproses: Boolean = false,
    isSelesai: Boolean = false,
    onClick: () -> Unit
) {
    val bg = when {
        !enabled -> Color(0xFF707070) // Terkunci (abu-abu)
        isSedangDiproses -> Color(0xFFCEBA04) // Kuning saat proses
        else -> Color(0xFF119E17) // Hijau untuk terbuka atau selesai
    }
    val text = if (enabled) Color.White else Color.LightGray


    var pressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (pressed) 0.95f else 1f, tween(100), label = "scale")
    val rot by animateFloatAsState(if (pressed) 2f else 0f, tween(100), label = "rot")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .height(100.dp)
    ) {
        Row(
            modifier = Modifier
                .matchParentSize()
                .shadow(15.dp, RoundedCornerShape(20.dp))
                .graphicsLayer(scaleX = scale, scaleY = scale, rotationZ = rot)
                .background(bg, RoundedCornerShape(12.dp))
                .padding(10.dp)
                .pointerInput(enabled) {
                    awaitPointerEventScope {
                        while (true) {
                            val event = awaitPointerEvent()
                            val down = event.changes.firstOrNull()
                            if (down?.pressed == true) {
                                pressed = true
                            } else {
                                if (pressed) {
                                    pressed = false
                                    if (enabled) onClick()
                                }
                            }
                        }
                    }
                }
            ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painterResource(iconRes), contentDescription = null, modifier = Modifier.size(50.dp))
            Spacer(Modifier.width(12.dp))
            Column {
                Text(title, color = text, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(subtitle, color = text, fontSize = 16.sp)

                if (enabled && (isSedangDiproses || isSelesai)) {
                    Box(
                        modifier = Modifier
                            .padding(top = 6.dp)
                            .background(
                                color = if (isSedangDiproses) Color(0xFFFF3D00) else Color(0xFF2196F3),
                                shape = RoundedCornerShape(6.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = if (isSedangDiproses) "Proses" else "Selesai",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}


