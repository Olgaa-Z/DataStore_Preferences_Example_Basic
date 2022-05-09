package binar.andlima.datastore_preferences_example_basic

import androidx.datastore.preferences.preferencesKey

object UserData {

    val NAMA = preferencesKey<String>("USER_NAMA")
    val UMUR = preferencesKey<Int>("USER_UMUR")
}