package com.calmox

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sp = applicationContext.getSharedPreferences("name_pref", Context.MODE_PRIVATE)
        val name = sp.getString("inputName", "")
        if (name == "") {
            val intent = Intent(this, NameActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}