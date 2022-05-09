package binar.andlima.datastore_preferences_example_basic

import android.content.Context
import android.widget.Toast
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class UserManager(context : Context) {

    private val dataStore: DataStore<Preferences> = context.createDataStore(name = "user_pref")

    companion object{
        val NAMA = preferencesKey<String>("USER_NAMA")
        val UMUR = preferencesKey<Int>("USER_UMUR")
    }

//    MENAMBAHKAN DATA KE PREFERENCES
   suspend fun saveData(nama : String, umur : Int){
        dataStore.edit {
            it[NAMA] = nama
            it[UMUR] = umur
        }
    }

//    UNTUK CLEAR DATA YANG ADA DI DATASTORE PREFERENCES
    suspend fun clearData(){
        dataStore.edit {
            it.clear()
        }
    }

//    Create an age flow to retrieve age from the preferences
    val userAgeFlow : Flow<Int> = dataStore.data.map {
        it[UMUR] ?: 0
    }

    val userNameFlow: Flow<String> = dataStore.data.map {
        it[NAMA] ?: ""
    }

}