package com.example.sqlite

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref: SharedPreferences = this.getPreferences(MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPref.edit()
        println("Stored details into shared prefs")
        editor.putString("username", "Dhanshri")
        editor.putString("password", "Test@123")

        val username = findViewById<EditText>(R.id.username_textView)
        val password = findViewById<EditText>(R.id.password_textView)
        val loginButton = findViewById<Button>(R.id.login_button)

        loginButton.setOnClickListener {
            println("---------------------------------------------")
            val getSharedPref: SharedPreferences = this.getPreferences(MODE_PRIVATE)
            val storedUsername = getSharedPref.getString("username", "Dhanshri")
            val storedPassword = getSharedPref.getString("password", "Test@123")
            println(storedUsername)
            println(storedPassword)
            if((username.text.toString() == storedUsername) && (password.text.toString() == storedPassword)) {
                val intent = Intent(this, CrudActivity::class.java)
                startActivity(intent)
            } else {
                println("ShreadPrefs didn't match can't login.")
            }

        }
    }
}