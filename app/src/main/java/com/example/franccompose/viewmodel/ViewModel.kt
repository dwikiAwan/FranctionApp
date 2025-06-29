package com.example.franccompose.fiturmulaibelajar.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.franccompose.R
import com.example.franccompose.fiturmulaibelajar.datastore.DataStoreManager
import kotlinx.coroutines.launch

// Modelnya di luar ViewModel
data class Materi(
    val id: Int,
    val title: String,
    val subtitle: String,
    val iconRes: Int,
    val isUnlocked: Boolean
)

class MateriViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext
    private val dataStore = DataStoreManager(context)

    var currentProgress by mutableStateOf(1)
        private set

    var namaUser = ""
    var kelasUser = ""

    var materiList = mutableStateListOf<Materi>()
        private set

    fun loadUser(nama: String, kelas: String) {
        namaUser = nama
        kelasUser = kelas

        viewModelScope.launch {
            currentProgress = dataStore.getProgress(namaUser, kelasUser)
            updateMateriList()
        }
    }

    private fun updateMateriList() {
        materiList.clear()
        materiList.addAll(
            listOf(
                Materi(1, "Materi 1", "Pengenalan Pecahan", R.drawable.doneicon, true),
                Materi(2, "Materi 2", "Membandingkan Pecahan", R.drawable.prosicon, currentProgress >= 2),
                Materi(3, "Materi 3", "Penjumlahan Pecahan", R.drawable.secicon, currentProgress >= 3),
                Materi(4, "Materi 4", "Pengurangan Pecahan", R.drawable.secicon, currentProgress >= 4)
            )
        )
    }

    fun unlockNext(materiId: Int) {
        if (currentProgress < materiId + 1) {
            currentProgress = materiId + 1
            viewModelScope.launch {
                dataStore.saveProgress(namaUser, kelasUser, currentProgress)
                updateMateriList()
            }
        }
    }
}
