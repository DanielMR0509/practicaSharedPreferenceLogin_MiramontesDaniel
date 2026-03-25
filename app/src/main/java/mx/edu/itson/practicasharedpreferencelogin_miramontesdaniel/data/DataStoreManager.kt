package mx.edu.itson.practicasharedpreferencelogin_miramontesdaniel.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {
    private val Context.dataStore by preferencesDataStore("session_prefs")

    companion object {
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    }

    val isLoggedFlow: Flow<Boolean> = context.dataStore.data
        .map { it[IS_LOGGED_IN] ?:false }

    suspend fun logout() {
        context.dataStore.edit {
            it.clear()
        }
    }

    suspend fun setLogin(isLoggedIn: Boolean) {
        context.dataStore.edit {
            it[IS_LOGGED_IN] = isLoggedIn
        }
    }
}