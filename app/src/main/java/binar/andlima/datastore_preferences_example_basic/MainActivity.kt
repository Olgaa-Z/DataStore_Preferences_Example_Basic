package binar.andlima.datastore_preferences_example_basic

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import androidx.lifecycle.asLiveData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    lateinit var userManager: UserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialize usermanager
        userManager = UserManager(this)


        btnSave.setOnClickListener {
            val nama = tvName.text.toString()
            val umur = tvAge.text.toString().toInt()
            GlobalScope.launch {
                userManager.saveData(nama,umur)
            }
        }

        btnClear.setOnClickListener {
            GlobalScope.launch {
                userManager.clearData()

            }
        }


//        UNTUK MENAMPILKAN DATA YANG ADA DI DATASTORE KE TEXTVIEW

        userManager.userNameFlow.asLiveData().observe(this,{
            resultName.text = it.toString()
        })

        userManager.userAgeFlow.asLiveData().observe(this,{
            resultAge.text = it.toString()
        })

    }





}