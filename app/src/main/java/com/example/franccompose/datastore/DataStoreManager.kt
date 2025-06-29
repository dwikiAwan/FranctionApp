// DataStoreManager.kt
package com.example.franccompose.fiturmulaibelajar.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class DataStoreManager(private val context: Context) {
    private val PROGRESS_KEY = intPreferencesKey("progress")
    private val LAST_USER_KEY = stringPreferencesKey("last_user")
    private val ALL_USERS_KEY = stringPreferencesKey("all_users")


    suspend fun saveProgress(nama: String, kelas: String, progress: Int) {
        val key = intPreferencesKey("progress_${nama}_${kelas}")
        context.dataStore.edit { prefs ->
            prefs[key] = progress
        }
    }

    suspend fun getProgress(nama: String, kelas: String): Int {
        val key = intPreferencesKey("progress_${nama}_${kelas}")
        val prefs = context.dataStore.data.first()
        return prefs[key] ?: 1
    }

    suspend fun saveLastUser(nama: String, kelas: String) {
        context.dataStore.edit { prefs ->
            prefs[LAST_USER_KEY] = "$nama|$kelas"
        }
    }

    suspend fun getLastUser(): Pair<String, String>? {
        val prefs = context.dataStore.data.first()
        return prefs[LAST_USER_KEY]?.split("|")?.takeIf { it.size == 2 }?.let {
            it[0] to it[1]
        }

        suspend fun saveLastUser(nama: String, kelas: String) {
            val newUser = "$nama|$kelas"
            context.dataStore.edit { prefs ->
                prefs[LAST_USER_KEY] = newUser

                val existing = prefs[ALL_USERS_KEY]?.split(",")?.toMutableSet() ?: mutableSetOf()
                existing.add(newUser)
                prefs[ALL_USERS_KEY] = existing.joinToString(",")
            }
        }

        suspend fun getAllUsers(): List<Pair<String, String>> {
            val prefs = context.dataStore.data.first()
            return prefs[ALL_USERS_KEY]?.split(",")
                ?.mapNotNull {
                    val parts = it.split("|")
                    if (parts.size == 2) Pair(parts[0], parts[1]) else null
                } ?: emptyList()
        }

    }
}