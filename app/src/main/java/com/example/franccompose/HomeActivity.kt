package com.example.franccompose

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.franccompose.fiturmulaibelajar.datastore.DataStoreManager
import com.example.franccompose.fiturmulaibelajar.viewmodel.LevelkuViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeActivity(
    navController: NavController,
    viewModel: LevelkuViewModel
) {
    val context = LocalContext.current
    val dataStoreManager = remember { DataStoreManager(context) }

    var nama by remember { mutableStateOf("") }
    var kelas by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        val user = dataStoreManager.getLastUser()
        user?.let {
            nama = it.first
            kelas = it.second
        }
    }
    val bannerImages = listOf(
        R.drawable.org1,
        R.drawable.org4,
        R.drawable.org5
    )

    val bannerTexts = listOf(
        "Belajar Pecahan itu Seru loh !",
        "Ayo Pahami Konsep Pecahan !",
        ""
    )

    val bannerBackgroundColors = listOf(
        Color(0xFF9CC5EC),
        Color(0xFFEAC481),
        Color(0xFF86B64D)
    )

    val currentLevel by remember { derivedStateOf { viewModel.levelTerbuka } }

    LaunchedEffect(Unit) {
        viewModel.loadProgress()
    }

    var currentBannerIndex by remember { mutableStateOf(0) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            currentBannerIndex = (currentBannerIndex + 1) % bannerImages.size
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        // HEADER
        Box(
            modifier = Modifier
                .fillMaxWidth()

                .height(95.dp)
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .background(
                        color = Color(0xFF3A69C7),
                        shape = RoundedCornerShape(
                            topEnd = 20.dp,
                            bottomEnd = 20.dp
                        )
                    )
                    .padding(vertical = 15.dp)
                    .fillMaxWidth(0.6f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Hallo, ")
                        withStyle(style = SpanStyle()) {
                            append(nama)
                        }
                        append(" | $kelas")
                    },
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(start = 25.dp)
                        .weight(1f, fill = false)
                )
            }

            IconButton(
                onClick = {
                    navController.navigate("profile")
                },
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(70.dp)
                    .align(Alignment.CenterEnd)
                    .zIndex(2f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.proficon),
                    contentDescription = "User Icon",
                    tint = Color.Unspecified
                )
            }
        }

        // BODY (banner + menu)
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .graphicsLayer { clip = false }
            ) {
                // Background yang berubah
                AnimatedContent(
                    targetState = currentBannerIndex,
                    transitionSpec = {
                        slideInHorizontally { fullWidth -> -fullWidth } with
                                slideOutHorizontally { fullWidth -> fullWidth }
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .zIndex(1f)
                ) { index ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                color = bannerBackgroundColors[index],
                                shape = RoundedCornerShape(12.dp)
                            )
                    )
                }

                // Konten isi banner
                AnimatedContent(
                    targetState = currentBannerIndex,
                    transitionSpec = {
                        slideInHorizontally { fullWidth -> fullWidth } with
                                slideOutHorizontally { fullWidth -> -fullWidth }
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .zIndex(1f)
                ) { index ->
                    when (index) {
                        2 -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(Color.Transparent, shape = RoundedCornerShape(16.dp)) // opsional
                                        .border(
                                            width = 2.dp,
                                            color = Color.White,
                                            shape = RoundedCornerShape(16.dp)
                                        )
                                        .padding(horizontal = 50.dp, vertical = 25.dp)
                                ) {
                                    Text(
                                        text = "Level $currentLevel",
                                        fontSize = 60.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                }
                            }
                        }

                        else -> {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 5.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Image(
                                    painter = painterResource(id = bannerImages[index]),
                                    contentDescription = "Banner Image",
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .size(180.dp)
                                        .offset(x = (-16).dp)
                                        .zIndex(5f)
                                )

                                // Teks luas dan center
                                Box(
                                    modifier = Modifier
                                        .weight(2f)
                                        .fillMaxHeight(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = bannerTexts[index],
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .offset(x = (-30).dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    MenuButton(
                        iconId = R.drawable.belajaricon,
                        text = "Mulai Belajar",
                        textColor = Color(0xFF5CB85C),
                        onClick = {
                            navController.navigate("daftarmateri")
                        }
                    )
                    MenuButton(
                        iconId = R.drawable.levicon,
                        text = "Levelku",
                        textColor = Color(0xFF5BC0DE),
                        onClick = {
                            navController.navigate("levelku")
                        }
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    MenuButton(
                        iconId = R.drawable.tenicon,
                        text = "Tentang Aplikasi",
                        textColor = Color(0xFFF0AD4E),
                        onClick = {
                            navController.navigate("tentangaplikasi")
                        }
                    )
                    MenuButton(
                        iconId = R.drawable.logout,
                        text = "Keluar Akun",
                        textColor = Color(0xFFD9534F),
                        onClick = {
                            navController.navigate("keluar")
                        }
                    )
                }
            }
        }

        // FOOTER
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .background(
                        color = Color(0xFFCDEEFF),
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
                    .padding(20.dp)
                    .padding(bottom = 20.dp)
            ) {
                Text(
                    text = "Belajar Pecahan?\nPasti Bisa Dong",
                    color = Color(0xFFF24E1E),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }

            Image(
                painter = painterResource(id = R.drawable.otak1),
                contentDescription = "Maskot",
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = (-20).dp)
            )
        }
    }
}