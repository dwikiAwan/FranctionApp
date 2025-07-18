package com.example.franccompose.ujitingkat.ujitingkat1

data class UjiQuestion(
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)

val UjiTingkat1 = listOf(
    UjiQuestion("Apa itu Pecahan?", listOf("Bagian dari keseluruhan", "Alat ukur", "Satuan waktu", "Jenis makanan"), 0),
    UjiQuestion("Apa itu pembilang?", listOf("Jumlah bagian diambil", "Jumlah bagian semua", "Nama pecahan", "Tanda garis"), 0),
    UjiQuestion("Apa itu penyebut?", listOf("Jumlah yang diambil", "Jumlah keseluruhan", "Hasil pecahan", "Sisa bagi"), 1),
    UjiQuestion("Bentuk pecahan?", listOf("a + b", "a x b", "a / b", "a - b"), 2),
    UjiQuestion("1/2 artinya?", listOf("2 dari 2 bagian", "1 dari 2 bagian", "1 dari 1 bagian", "2 dari 1 bagian"), 1),
    UjiQuestion("Pecahan dari satu bagian dari empat bagian adalah?", listOf("1/2", "1/3", "1/4", "1/5"), 2),
    UjiQuestion("Pecahan dari tiga bagian dari lima bagian adalah?", listOf("3/4", "3/5", "2/5", "5/3"), 1),
    UjiQuestion("2/4 disederhanakan menjadi?", listOf("1/2", "1/4", "2/2", "4/2"), 0),
    UjiQuestion("Pembilang dari 3/7 adalah?", listOf("3", "7", "10", "4"), 0),
    UjiQuestion("Penyebut dari 5/6 adalah?", listOf("5", "6", "11", "3"), 1),
    UjiQuestion("Manakah pecahan terbesar?", listOf("1/2", "1/4", "1/3", "1/5"), 0),
    UjiQuestion("Manakah pecahan terkecil?", listOf("2/3", "3/4", "1/5", "4/5"), 2),
    UjiQuestion("3/6 = ?", listOf("1/2", "1/3", "2/3", "3/3"), 0),
    UjiQuestion("Pecahan ekuivalen dengan 2/4 adalah?", listOf("1/2", "2/2", "3/4", "1/4"), 0),
    UjiQuestion("Jika kamu membagi pizza menjadi 8 bagian, dan mengambil 3, maka pecahan yang mewakili bagian yang diambil adalah?", listOf("3/8", "5/8", "1/8", "8/3"), 0),
    UjiQuestion("1/3 + 1/3 = ?", listOf("1/6", "2/3", "3/3", "1/2"), 1),
    UjiQuestion("4/8 disederhanakan menjadi?", listOf("2/4", "1/2", "3/4", "4/4"), 1),
    UjiQuestion("Pecahan campuran dari 1 dan 1/2 adalah?", listOf("1/2", "2/1", "1 1/2", "2 1/2"), 2),
    UjiQuestion("1/2 = ?", listOf("2/4", "3/6", "4/8", "Semua benar"), 3),
    UjiQuestion("Pecahan 5/5 sama dengan?", listOf("0", "1", "5", "2"), 1)
)



