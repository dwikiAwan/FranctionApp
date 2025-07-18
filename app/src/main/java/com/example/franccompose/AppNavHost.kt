package com.example.franccompose.navigation

import Routes
import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.franccompose.HomeActivity
import com.example.franccompose.KeluarActivity
import com.example.franccompose.LevelkuScreen
import com.example.franccompose.ProfileScreen
import com.example.franccompose.RegisScreen
import com.example.franccompose.SecondScreen
import com.example.franccompose.SplashScreen
import com.example.franccompose.TentangScreen
import com.example.franccompose.fiturmulaibelajar.DaftarMateriScreen
import com.example.franccompose.fiturmulaibelajar.datastore.DataStoreManager
import com.example.franccompose.fiturmulaibelajar.viewmodel.LevelkuViewModel
import com.example.franccompose.fiturmulaibelajar.viewmodel.LevelkuViewModelFactory
import com.example.franccompose.fiturmulaibelajar.viewmodel.MateriViewModel
import com.example.franccompose.fiturmulaibelajar.viewmodel.MateriViewModelFactory
import com.example.franccompose.materipecahan.materi1.PengenalanPecahan1Screen
import com.example.franccompose.materipecahan.materi1.PengenalanPecahan2Screen
import com.example.franccompose.materipecahan.materi1.PengenalanPecahan3Screen
import com.example.franccompose.materipecahan.materi2.BandingUrut1Screen
import com.example.franccompose.materipecahan.materi2.BandingUrut2Screen
import com.example.franccompose.materipecahan.materi2.BandingUrut3Screen
import com.example.franccompose.materipecahan.materi2.BandingUrut4Screen
import com.example.franccompose.materipecahan.materi3.Penjumlahan1Screen
import com.example.franccompose.materipecahan.materi3.Penjumlahan2Screen
import com.example.franccompose.materipecahan.materi4.Pengurangan1Screen
import com.example.franccompose.materipecahan.ujitingkat1.UjiTingkat1ResultScreen
import com.example.franccompose.materipecahan.ujitingkat1.UjiTingkat1Screen
import com.example.franccompose.materipecahan.ujitingkat2.UjiTingkat2ResultScreen
import com.example.franccompose.materipecahan.ujitingkat2.UjiTingkat2Screen
import com.example.franccompose.quiz.HasilQuizScreen
import com.example.franccompose.quiz.QuizScreen
import com.example.franccompose.quiz.QuizViewModel
import com.example.franccompose.rapotnilai.RapotScreen
import com.example.franccompose.ujitingkat.ujitingkat1.UjiTingkatViewModel
import kotlinx.coroutines.launch


@Composable
fun AppNavHost(
    navController: NavHostController,
    materiViewModel: MateriViewModel
) {
    NavHost(navController = navController, startDestination = Routes.Splash) {
        composable(Routes.Splash) {
            SplashScreen(navController)
        }
        composable(Routes.Second) {
            SecondScreen(onNextClick = {
                navController.navigate(Routes.Register)
            })
        }
        composable(Routes.Register) {
            RegisScreen(navController, materiViewModel)
        }
        composable(Routes.DaftarMateri) {
            DaftarMateriScreen(navController, materiViewModel)
        }
        composable(Routes.home) {
            HomeActivity(navController)
        }
        composable(Routes.tentang) {
            TentangScreen(navController)
        }
        composable("levelku") {
            val context = LocalContext.current
            val application = context.applicationContext as Application
            val viewModel: LevelkuViewModel = viewModel(
                factory = LevelkuViewModelFactory(application)
            )

            LaunchedEffect(Unit) {
                viewModel.loadProgress()
            }

            LevelkuScreen(navController = navController, viewModel = viewModel)
        }


        composable(Routes.profile) {
            ProfileScreen(navController)
        }
        composable(Routes.keluar) {
            KeluarActivity(navController)
        }

        //Materi 1
        composable("materi1satu") {
            PengenalanPecahan1Screen(navController)
        }
        composable("materi1dua") {
            PengenalanPecahan2Screen(navController)
        }
        composable("materi1tiga") {
            PengenalanPecahan3Screen(navController)
        }

        //Materi 2
        composable("materi2satu") {
           BandingUrut1Screen(navController)
        }
        composable("materi2dua") {
            BandingUrut2Screen(navController)
        }
        composable("materi2tiga") {
            BandingUrut3Screen(navController)
        }
        composable("materi2empat") {
            BandingUrut4Screen(navController)
        }


        //Materi 3
        composable("materi3satu") {
            Penjumlahan1Screen(navController)
        }
        composable("materi3dua") {
            Penjumlahan2Screen(navController)
        }

        //Materi 4
        composable("materi4satu") {
            Pengurangan1Screen(navController)
        }
        //Pemanggilan Quiz
        composable("materi1Quiz") { navBackStackEntry ->
            val context = LocalContext.current
            val dataStore = remember { DataStoreManager(context) }
            val quizViewModel: QuizViewModel = viewModel()

            QuizScreen(
                navController = navController,
                viewModel = quizViewModel,
                dataStore = dataStore,
                materiKe = 1
            )
        }
        composable("materi2Quiz") { navBackStackEntry ->
            val context = LocalContext.current
            val dataStore = remember { DataStoreManager(context) }
            val quizViewModel: QuizViewModel = viewModel()

            QuizScreen(
                navController = navController,
                viewModel = quizViewModel,
                dataStore = dataStore,
                materiKe = 2
            )
        }
        composable("materi3Quiz") { navBackStackEntry ->
            val context = LocalContext.current
            val dataStore = remember { DataStoreManager(context) }
            val quizViewModel: QuizViewModel = viewModel()

            QuizScreen(
                navController = navController,
                viewModel = quizViewModel,
                dataStore = dataStore,
                materiKe = 4
            )
        }
        composable("materi4Quiz") { navBackStackEntry ->
            val context = LocalContext.current
            val dataStore = remember { DataStoreManager(context) }
            val quizViewModel: QuizViewModel = viewModel()

            QuizScreen(
                navController = navController,
                viewModel = quizViewModel,
                dataStore = dataStore,
                materiKe = 5
            )
        }

        //UjiTingkat1
        composable("ujitingkat1") {
            val context = LocalContext.current
            val dataStore = remember { DataStoreManager(context) }
            val viewModel: UjiTingkatViewModel = viewModel()
            LaunchedEffect(Unit) {
                viewModel.setDataStore(dataStore)
                viewModel.loadUjiTingkat(UjiTingkatViewModel.UjiLevel.SATU)
            }
            UjiTingkat1Screen(
                navController = navController,
                viewModel = viewModel,
                dataStoreManager = dataStore,
                materiKe = 3, //
                onQuizFinished = { skorFinal ->
                    val elapsed = 1800 - viewModel.timeLeft
                    navController.navigate("hasiluji1/$skorFinal/$elapsed/3") {
                        popUpTo("ujitingkat1") { inclusive = true }
                    }
                }
            )
        }
        composable("hasiluji1/{skor}/{waktu}/{materiKe}") { backStackEntry ->
            val skor = backStackEntry.arguments?.getString("skor")?.toIntOrNull() ?: 0
            val waktu = backStackEntry.arguments?.getString("waktu")?.toIntOrNull() ?: 0
            val materiKe = backStackEntry.arguments?.getString("materiKe")?.toIntOrNull() ?: 3

            val context = LocalContext.current
            val dataStoreManager = remember { DataStoreManager(context) }
            val viewModel: UjiTingkatViewModel = viewModel()
            val materiViewModel: MateriViewModel = viewModel(
                factory = MateriViewModelFactory(context.applicationContext as Application)
            )
            val coroutineScope = rememberCoroutineScope()
            val isPassed = skor >= 70
            val sudahDiproses = remember { mutableStateOf(false) }

            LaunchedEffect(Unit) {
                if (!sudahDiproses.value) {
                    if (isPassed) {
                        viewModel.simpanSkorUjiTingkat(
                            waktu = waktu,
                            onSaved = {
                                println("✅ Skor Uji Tingkat disimpan & progress/level naik")
                            }
                        )

                        val user = dataStoreManager.getLastUser()
                        user?.let { (nama, kelas) ->
                            dataStoreManager.saveProgress(nama, kelas, 4) // Materi ke-3
                            dataStoreManager.upgradeLevel(nama, kelas, 3, "ujian") // dari level 3 → 4
                            materiViewModel.selesaiQuiz(nama, kelas, 3)
                        }
                    } else {
                        val user = dataStoreManager.getLastUser()
                        user?.let { (nama, kelas) ->
                            dataStoreManager.saveQuizHistory(nama, kelas, materiKe, skor, waktu)
                            dataStoreManager.saveScore(nama, kelas, materiKe, skor)
                        }
                    }
                    sudahDiproses.value = true
                }
            }


            UjiTingkat1ResultScreen(
                score = skor,
                elapsedTime = waktu,
                materiKe = materiKe,
                dataStoreManager = dataStoreManager,
                viewModel = viewModel,
                onBackToHome = {
                    navController.navigate("daftarmateri") {
                        popUpTo("daftarmateri") { inclusive = true }
                    }
                },
                onUlangUji = {
                    navController.navigate("ujitingkat1") {
                        popUpTo("ujitingkat1") { inclusive = true }
                    }
                },
                onNextBelajar = {
                    // ✅ Sesuaikan dengan alur: setelah UjiTingkat1, masuk Materi 3
                    navController.navigate("materi3satu")
                }
            )
        }

        //UjiTingkat2
        composable("ujitingkat2") {
            val context = LocalContext.current
            val dataStore = remember { DataStoreManager(context) }
            val viewModel: UjiTingkatViewModel = viewModel()
            LaunchedEffect(Unit) {
                viewModel.setDataStore(dataStore)
                viewModel.loadUjiTingkat(UjiTingkatViewModel.UjiLevel.DUA)
            }
            UjiTingkat2Screen(
                navController = navController,
                viewModel = viewModel,
                dataStoreManager = dataStore,
                materiKe = 6, //
                onQuizFinished = { skorFinal ->
                    val elapsed = 1800 - viewModel.timeLeft
                    navController.navigate("hasiluji2/$skorFinal/$elapsed/6") {
                        popUpTo("ujitingkat2") { inclusive = true }
                    }
                }
            )
        }
        composable("hasiluji2/{skor}/{waktu}/{materiKe}") { backStackEntry ->
            val skor = backStackEntry.arguments?.getString("skor")?.toIntOrNull() ?: 0
            val waktu = backStackEntry.arguments?.getString("waktu")?.toIntOrNull() ?: 0
            val materiKe = backStackEntry.arguments?.getString("materiKe")?.toIntOrNull() ?: 6

            val context = LocalContext.current
            val dataStoreManager = remember { DataStoreManager(context) }
            val viewModel: UjiTingkatViewModel = viewModel()
            val materiViewModel: MateriViewModel = viewModel(
                factory = MateriViewModelFactory(context.applicationContext as Application)
            )
            val coroutineScope = rememberCoroutineScope()

            LaunchedEffect(Unit) {
                viewModel.setDataStore(dataStoreManager)
                viewModel.simpanSkorUjiTingkat(waktu) {
                    println("✅ Skor Uji Tingkat berhasil disimpan: $it")
                }

                val user = dataStoreManager.getLastUser()
                user?.let { (nama, kelas) ->
                    // ✅ Tambahkan penyimpanan progress ke level 7 (setelah UjiTingkat2)
                    dataStoreManager.saveProgress(nama, kelas, 7)

                    // ✅ Upgrade level ke 7
                    dataStoreManager.upgradeLevel(nama, kelas, materiKe, "ujian")

                    // ✅ Tandai ujian telah selesai
                    materiViewModel.selesaiQuiz(nama, kelas, materiKe)
                }
            }

            UjiTingkat2ResultScreen(
                score = skor,
                elapsedTime = waktu,
                materiKe = materiKe,
                dataStoreManager = dataStoreManager,
                viewModel = viewModel,
                onBackToHome = {
                    navController.navigate("daftarmateri") {
                        popUpTo("daftarmateri") { inclusive = true }
                    }
                },
                onUlangUji = {
                    navController.navigate("ujitingkat2") {
                        popUpTo("ujitingkat2") { inclusive = true }
                    }
                },
                onNextBelajar = {
                    // ✅ Setelah UjiTingkat2, bisa navigasi ke "levelku" atau ke tampilan lain
                    navController.navigate("levelku")
                }
            )
        }


        composable("hasilQuiz/{skor}/{waktu}/{materiKe}") { backStack ->
            val skor = backStack.arguments?.getString("skor")?.toInt() ?: 0
            val waktu = backStack.arguments?.getString("waktu")?.toInt() ?: 0
            val materiKe = backStack.arguments?.getString("materiKe")?.toInt() ?: 1
            val coroutineScope = rememberCoroutineScope()
            val context = LocalContext.current
            val dataStore = remember { DataStoreManager(context) }
            val materiViewModel: MateriViewModel = viewModel(
                factory = MateriViewModelFactory(context.applicationContext as Application)
            )
            HasilQuizScreen(
                score = skor,
                elapsedTime = waktu,
                materiKe = materiKe,
                onNextClick = {
                    if (skor < 60) {
                        navController.navigate("quiz/$materiKe")
                    } else {
                        coroutineScope.launch {
                            val user = dataStore.getLastUser()
                            user?.let { (nama, kelas) ->
                                materiViewModel.selesaiQuiz(nama, kelas, materiKe)
                                dataStore.upgradeLevel(nama, kelas, materiKe, "quiz")
                            }


                            val nextMateriScreen = when (materiKe + 1) {
                                2 -> "materi2satu"
                                3 -> "ujitingkat1"
                                4 -> "materi3satu"
                                5 -> "materi4satu"
                                6 -> "ujitingkat2"
                                else -> "daftarMateri"
                            }
                            navController.navigate(nextMateriScreen)
                        }
                    }
                }

                ,
                onUlangMateriClick = {
                    val screen = when (materiKe) {
                        1 -> "materi1satu"
                        2 -> "materi2satu"
                        3 -> "ujitingkat1"
                        4 -> "materi3satu"
                        5 -> "materi4satu"
                        6 -> "ujitingkat2"
                        else -> "homeScreen"
                    }
                    navController.navigate(screen)
                },
                onMainMenuClick = {
                    navController.navigate("daftarMateri") {
                        popUpTo("hasilQuiz") { inclusive = true }
                    }
                },
                materiViewModel = materiViewModel
            )
        }
        composable("rapot/{materiKe}") { backStackEntry ->
            val context = LocalContext.current
            val dataStore = remember { DataStoreManager(context) }
            val materiKe = backStackEntry.arguments?.getString("materiKe")?.toIntOrNull() ?: 1

            var filteredHistory by remember { mutableStateOf<List<Pair<Int, Int>>>(emptyList()) }

            val namaMateri = when (materiKe) {
                1 -> "Quiz Pengenalan Pecahan"
                2 -> "Quiz Membandingkan Pecahan"
                3 -> "Ujian Tingkat 1"
                4 -> "Quiz Penjumlahan Pecahan"
                5 -> "Quiz Pengurangan Pecahan"
                6 -> "Ujian Tingkat 2"
                else -> "Materi Tidak Dikenal"
            }


            LaunchedEffect(Unit) {
                val user = dataStore.getLastUser()
                user?.let { (nama, kelas) ->
                    filteredHistory = dataStore.getQuizHistoryForMateri(nama, kelas, materiKe)
                }
            }

            RapotScreen(
                namaMateri = namaMateri,
                history = filteredHistory,
                materiKe = materiKe,
                onBackClick = { navController.popBackStack() },

            )
        }





    }//main
}

