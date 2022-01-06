package com.calmox

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.calmox.databinding.ActivityNameBinding
import android.content.Intent

import android.widget.Toast
import java.util.*

class NameActivity : AppCompatActivity() {
    private lateinit var bind: ActivityNameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityNameBinding.inflate(layoutInflater)
        setContentView(bind.root)
        val storeName = getSharedPreferences("name_pref", Context.MODE_PRIVATE)
        bind.nameNext.setOnClickListener {
            val editor: SharedPreferences.Editor = storeName.edit()
            when {
                bind.nameInput.text.toString().length > 12 -> {
                    editor.putString(
                        "inputName",
                        (bind.nameInput.text.toString().lowercase().substring(0, 10) + "."))
                    editor.apply()
                    Toast.makeText(
                        this@NameActivity,
                        "welcome " + bind.nameInput.text.toString().lowercase().substring(0, 10) + ".",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this@NameActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                bind.nameInput.text.toString().isEmpty() -> {
                    Toast.makeText(
                        this@NameActivity,
                        "please enter valid name",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    editor.putString(
                        "inputName",
                        bind.nameInput.text.toString().lowercase())
                    editor.apply()
                    Toast.makeText(
                        this@NameActivity,
                        "welcome " + bind.nameInput.text.toString().lowercase(),
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this@NameActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        val sp = applicationContext.getSharedPreferences("name_pref", Context.MODE_PRIVATE)
        val name = sp.getString("inputName", "")
        if (name == "") {
            val i = Intent()
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            moveTaskToBack(true)
        }else{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}