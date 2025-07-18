package com.example.franccompose.ujitingkat.ujitingkat1

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.franccompose.fiturmulaibelajar.datastore.DataStoreManager
import com.example.franccompose.materipecahan.ujitingkat1.SoalTingkat1
import com.example.franccompose.materipecahan.ujitingkat2.SoalTingkat2
import com.example.franccompose.viewmodel.UjiQuestion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class UjiTingkatViewModel : ViewModel() {

    // Timer
    var timeLeft by mutableStateOf(3600)

    // State soal dan skor
    private val _currentQuestionIndex = mutableStateOf(0)
    val currentQuestionIndex: State<Int> = _currentQuestionIndex

    private val _selectedAnswers = mutableStateMapOf<Int, Int>()
    val selectedAnswers: Map<Int, Int> = _selectedAnswers

    private val _score = mutableStateOf(0)
    val score: State<Int> = _score

    private val _questions = MutableStateFlow<List<UjiQuestion>>(emptyList())
    val questions: StateFlow<List<UjiQuestion>> = _questions

    private var poinPerSoal = 0

    // DataStoreManager
    private var dataStoreManager: DataStoreManager? = null
    fun setDataStore(dataStore: DataStoreManager) {
        this.dataStoreManager = dataStore
    }

    enum class UjiLevel { SATU, DUA }

    fun loadUjiTingkat(level: UjiLevel) {
        val soal = when (level) {
            UjiLevel.SATU -> SoalTingkat1
            UjiLevel.DUA -> SoalTingkat2
        }
        loadUjiTingkatSoal(soal)
    }
    fun loadUjiTingkatSoal(soal: List<UjiQuestion>) {
        _questions.value = soal
        _currentQuestionIndex.value = 0
        _selectedAnswers.clear()
        _score.value = 0
        poinPerSoal = if (soal.isNotEmpty()) 100 / soal.size else 0
    }



    // Simpan jawaban
    fun submitAnswer(answerIndex: Int) {
        val currentIndex = _currentQuestionIndex.value
        val questionList = questions.value

        if (questionList.isEmpty() || currentIndex >= questionList.size) return

        val correctAnswer = questionList[currentIndex].correctAnswerIndex
        val previousAnswer = _selectedAnswers[currentIndex]

        if (previousAnswer == null && answerIndex == correctAnswer) {
            _score.value += poinPerSoal
        }
        if (previousAnswer == correctAnswer && answerIndex != correctAnswer) {
            _score.value -= poinPerSoal
        }
        if (previousAnswer != null && previousAnswer != correctAnswer && answerIndex == correctAnswer) {
            _score.value += poinPerSoal
        }

        _selectedAnswers[currentIndex] = answerIndex
    }

    fun nextQuestion() {
        if (_currentQuestionIndex.value < questions.value.lastIndex) {
            _currentQuestionIndex.value++
        }
    }

    fun prevQuestion() {
        if (_currentQuestionIndex.value > 0) {
            _currentQuestionIndex.value--
        }
    }

    fun isLastQuestion(): Boolean = _currentQuestionIndex.value == questions.value.lastIndex

    fun simpanSkorUjiTingkat(waktu: Int, onSaved: (Int) -> Unit) {
        viewModelScope.launch {
            dataStoreManager?.let { dataStore ->
                val user = dataStore.getLastUser()
                user?.let { (nama, kelas) ->
                    val skor = _score.value
                    val materiKe = 3 // khusus Uji Tingkat 1

                    // Simpan nilai & waktu
                    dataStore.saveScore(nama, kelas, materiKe, skor)
                    dataStore.saveQuizHistory(nama, kelas, materiKe, skor, waktu)

                    if (skor >= 70) {
                        // 🔼 Naik Level
                        val currentLevel = dataStore.getFinalLevel(nama, kelas)
                        val nextLevel = (currentLevel + 1).coerceAtMost(100)
                        dataStore.setLevel(nama, kelas, nextLevel)
                        dataStore.unlockNextMateri(4)
                        val currentProgress = dataStore.getProgress(nama, kelas)
                        if (currentProgress == 3) {
                            dataStore.saveProgress(nama, kelas, 3)
                        }
                    }

                    onSaved(skor)
                }
            }
        }
    }


}
