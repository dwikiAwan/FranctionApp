package com.example.franccompose.quiz

data class QuizQuestion(
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)

val materi1Quiz = listOf(
    QuizQuestion
        ("Apa itu Pecahan?",
        listOf("Bagian dari keseluruhan",
            "Alat ukur", "Satuan waktu",
            "Jenis makanan"),
        0),
    QuizQuestion("Apa itu pembilang?", listOf("Jumlah bagian diambil", "Jumlah bagian semua", "Nama pecahan", "Tanda garis"), 0),
    QuizQuestion("Apa itu penyebut?", listOf("Jumlah yang diambil", "Jumlah keseluruhan", "Hasil pecahan", "Sisa bagi"), 1),
    QuizQuestion("Bentuk pecahan?", listOf("a + b", "a x b", "a / b", "a - b"), 1),
    QuizQuestion("1/2 artinya?", listOf("2 dari 2 bagian", "1 dari 2 bagian", "1 dari 1 bagian", "2 dari 1 bagian"), 1),
)

val materi2Quiz = listOf(
    QuizQuestion("Apa itu pembilang?", listOf("Jumlah bagian diambil", "Jumlah bagian semua", "Nama pecahan", "Tanda garis"), 0),

    QuizQuestion
        ("Apa itu Pecahan?",
        listOf("Bagian dari keseluruhan",
            "Alat ukur", "Satuan waktu",
            "Jenis makanan"),
        0),
   QuizQuestion("Apa itu penyebut?", listOf("Jumlah yang diambil", "Jumlah keseluruhan", "Hasil pecahan", "Sisa bagi"), 1),
    QuizQuestion("Bentuk pecahan?", listOf("a + b", "a x b", "a / b", "a - b"), 1),
    QuizQuestion("1/2 artinya?", listOf("2 dari 2 bagian", "1 dari 2 bagian", "1 dari 1 bagian", "2 dari 1 bagian"), 1),
)
val materi3Quiz = listOf(
    QuizQuestion("1/2 artinya?", listOf("2 dari 2 bagian", "1 dari 2 bagian", "1 dari 1 bagian", "2 dari 1 bagian"), 1),

    QuizQuestion
        ("Apa itu Pecahan?",
        listOf("Bagian dari keseluruhan",
            "Alat ukur", "Satuan waktu",
            "Jenis makanan"),
        0),
    QuizQuestion("1/2 artinya?", listOf("2 dari 2 bagian", "1 dari 2 bagian", "1 dari 1 bagian", "2 dari 1 bagian"), 1),

    QuizQuestion("Apa itu pembilang?", listOf("Jumlah bagian diambil", "Jumlah bagian semua", "Nama pecahan", "Tanda garis"), 0),
    QuizQuestion("Apa itu penyebut?", listOf("Jumlah yang diambil", "Jumlah keseluruhan", "Hasil pecahan", "Sisa bagi"), 1),
)
val materi4Quiz = listOf(
    QuizQuestion("1/2 artinya?", listOf("2 dari 2 bagian", "1 dari 2 bagian", "1 dari 1 bagian", "2 dari 1 bagian"), 1),

    QuizQuestion
        ("Apa itu Pecahan?",
        listOf("Bagian dari keseluruhan",
            "Alat ukur", "Satuan waktu",
            "Jenis makanan"),
        0),
    QuizQuestion("Apa itu pembilang?", listOf("Jumlah bagian diambil", "Jumlah bagian semua", "Nama pecahan", "Tanda garis"), 0),
    QuizQuestion("Apa itu penyebut?", listOf("Jumlah yang diambil", "Jumlah keseluruhan", "Hasil pecahan", "Sisa bagi"), 1),
    QuizQuestion("Bentuk pecahan?", listOf("a + b", "a x b", "a / b", "a - b"), 1),
 )
