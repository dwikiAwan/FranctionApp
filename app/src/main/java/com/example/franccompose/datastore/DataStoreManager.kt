package com.example.franccompose.fiturmulaibelajar.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class DataStoreManager(private val context: Context) {

    // ──────────────── Keys Utama ────────────────
    private val PROGRESS_KEY = intPreferencesKey("progress")
    private val LAST_USER_KEY = stringPreferencesKey("last_user")
    private val ALL_USERS_KEY = stringPreferencesKey("all_users")

    // ──────────────── Helper Dynamic Key ────────────────
    private fun scoreKey(n: String, k: String, materi: Int) =
        intPreferencesKey("score_${n}_${k}_materi$materi")
    private fun timeKey(n: String, k: String, materi: Int) =
        intPreferencesKey("time_${n}_${k}_materi$materi")
    private fun historyKey(n: String, k: String) =
        stringSetPreferencesKey("quiz_history_${n}_${k}")
    private fun historyPerMateriKey(n: String, k: String, m: Int) =
        stringSetPreferencesKey("quiz_history_${n}_${k}_materi$m")
    private fun emailKey(n: String, k: String) =
        stringPreferencesKey("email_${n}_${k}")
    private fun sekolahKey(n: String, k: String) =
        stringPreferencesKey("sekolah_${n}_${k}")
    private fun levelKey(n: String, k: String) =
        intPreferencesKey("level_${n}_${k}")
    private fun unlockKey(nama: String, kelas: String, materi: Int) =
        intPreferencesKey("unlocked_${nama}_${kelas}_materi$materi")
    private fun timestampKey(n: String, k: String, materi: Int) =
        stringPreferencesKey("timestamp_${n}_${k}_materi$materi")


    // ──────────────── Save & Get Progress ────────────────

    suspend fun saveProgress(nama: String, kelas: String, progress: Int) {
        val key = intPreferencesKey("progress_${nama}_${kelas}")
        context.dataStore.edit { it[key] = progress }
        println("Progress disimpan: $progress untuk $nama - $kelas")
    }

    suspend fun getProgress(nama: String, kelas: String): Int {
        val key = intPreferencesKey("progress_${nama}_${kelas}")
        val prefs = context.dataStore.data.first()
        return prefs[key] ?: 1
    }

    // ──────────────── Last User ────────────────
    suspend fun saveLastUser(nama: String, kelas: String) {
        val newUser = "$nama|$kelas"
        context.dataStore.edit { prefs ->
            prefs[LAST_USER_KEY] = newUser
            val existing = prefs[ALL_USERS_KEY]?.split(",")?.toMutableSet() ?: mutableSetOf()
            existing.add(newUser)
            prefs[ALL_USERS_KEY] = existing.joinToString(",")
        }
    }

    suspend fun getLastUser(): Pair<String, String>? {
        val prefs = context.dataStore.data.first()
        return prefs[LAST_USER_KEY]?.split("|")?.takeIf { it.size == 2 }?.let {
            it[0] to it[1]
        }
    }

    // ──────────────── Nilai / Skor per Materi ────────────────


    suspend fun getFinalLevel(nama: String, kelas: String): Int {
        val storedLevel = context.dataStore.data.first()[levelKey(nama, kelas)] ?: 1
        return storedLevel.coerceAtMost(7)
    }


    suspend fun setLevel(nama: String, kelas: String, level: Int) {
        context.dataStore.edit {
            it[levelKey(nama, kelas)] = level
        }
        println(" Level disimpan: $level untuk $nama - $kelas")
    }


    suspend fun saveScore(nama: String, kelas: String, materiKe: Int, score: Int) {
        context.dataStore.edit { it[scoreKey(nama, kelas, materiKe)] = score }
    }

    suspend fun unlockMateri(nama: String, kelas: String, materi: Int) {
        context.dataStore.edit { prefs ->
            prefs[unlockKey(nama, kelas, materi)] = 1
        }
    }
    suspend fun upgradeLevel(nama: String, kelas: String, materiKe: Int, tipe: String) {
        val targetLevel = when {
            tipe == "quiz" && materiKe == 1 -> 2
            tipe == "quiz" && materiKe == 2 -> 3
            tipe == "ujian" && materiKe == 3 -> 4
            tipe == "quiz" && materiKe == 4 -> 5
            tipe == "quiz" && materiKe == 5 -> 6
            tipe == "ujian" && materiKe == 6 -> 7
            else -> null
        }

        targetLevel?.let {
            setLevel(nama, kelas, it)
            println("Naik level ke $it (tipe: $tipe, materi: $materiKe)")
        }
    }



    // ──────────────── History Quiz per Materi ────────────────
    suspend fun saveQuizHistory(nama: String, kelas: String, materiKe: Int, skor: Int, waktu: Int) {
        val key = historyPerMateriKey(nama, kelas, materiKe)
        // Gunakan Date dan SimpleDateFormat untuk timestamp
        val currentTimestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val newItem = "$skor|$waktu|$currentTimestamp"
        context.dataStore.edit { prefs ->
            val set = prefs[key]?.toMutableSet() ?: mutableSetOf()
            if (!set.contains(newItem)) {
                set.add(newItem)
                prefs[key] = set
            }
        }
    }
    suspend fun getQuizHistoryForMateri(nama: String, kelas: String, materiKe: Int): List<Triple<Int, Int, String>> {
        val prefs = context.dataStore.data.first()
        return prefs[historyPerMateriKey(nama, kelas, materiKe)]?.mapNotNull {
            val parts = it.split('|')
            val skor = parts.getOrNull(0)?.toIntOrNull()
            val waktu = parts.getOrNull(1)?.toIntOrNull()
            val timestamp = parts.getOrNull(2)
            if (skor != null && waktu != null && timestamp != null) Triple(skor, waktu, timestamp) else null
        }?.sortedByDescending { it.third } // Opsional: Urutkan berdasarkan timestamp terbaru (string)
            ?: emptyList()
    }
    // ──────────────── Info User (Email & Sekolah) ────────────────
    suspend fun saveUserInfo(nama: String, kelas: String, email: String, sekolah: String) {
        context.dataStore.edit { prefs ->
            prefs[emailKey(nama, kelas)] = email
            prefs[sekolahKey(nama, kelas)] = sekolah
        }
    }

    suspend fun getUserInfo(nama: String, kelas: String): Pair<String, String> {
        val prefs = context.dataStore.data.first()
        val email = prefs[emailKey(nama, kelas)] ?: ""
        val sekolah = prefs[sekolahKey(nama, kelas)] ?: ""
        return email to sekolah
    }
    // ──────────────── All Users ────────────────
    suspend fun getAllUsers(): List<Pair<String, String>> {
        val prefs = context.dataStore.data.first()
        return prefs[ALL_USERS_KEY]?.split(",")?.mapNotNull {
            val parts = it.split("|")
            if (parts.size == 2) parts[0] to parts[1] else null
        } ?: emptyList()
    }

    suspend fun getAllUserDetails(): List<UserDetail> {
        val prefs = context.dataStore.data.first()
        val users = prefs[ALL_USERS_KEY]?.split(",") ?: emptyList()
        return users.mapNotNull { user ->
            val parts = user.split("|")
            if (parts.size == 2) {
                val nama = parts[0]
                val kelas = parts[1]
                val email = prefs[emailKey(nama, kelas)] ?: ""
                val sekolah = prefs[sekolahKey(nama, kelas)] ?: ""
                UserDetail(nama, kelas, email, sekolah)
            } else null
        }
    }

    fun unlockNextMateri(materiKe: Int) {
    }
    companion object {
        val LAST_USER_NAMA = stringPreferencesKey("last_user_nama")
        val LAST_USER_KELAS = stringPreferencesKey("last_user_kelas")
    }
    suspend fun clearLastUser() {
        context.dataStore.edit { preferences ->
            preferences.remove(LAST_USER_NAMA)
            preferences.remove(LAST_USER_KELAS)
        }
    }
    data class UserDetail(
        val nama: String,
        val kelas: String,
        val email: String,
        val sekolah: String
    )
}