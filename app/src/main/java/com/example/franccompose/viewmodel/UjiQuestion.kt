package com.example.franccompose.viewmodel


data class UjiQuestion(
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)
