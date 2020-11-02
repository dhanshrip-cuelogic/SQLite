package com.example.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CrudActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud)

        val dbManager = DatabaseManager(this)

        val insertButton = findViewById<Button>(R.id.insert_button)
        val readButton = findViewById<Button>(R.id.read_button)
        val updateButton = findViewById<Button>(R.id.update_button)
        val deleteButton = findViewById<Button>(R.id.delete_button)


        val user5 = User(105,"user5", 28)

        insertButton.setOnClickListener {
            dbManager.addValues(user5)
        }

        readButton.setOnClickListener {
            val users = dbManager.getUsers()
            for (user: User in users) {
                println("Users ID : " + user.userID + " Name : " + user.userName + " Age : " + user.userAge)
            }
        }

        updateButton.setOnClickListener {
            val updatedUser3 = User(103,"user3", 32)
            dbManager.updateUser(updatedUser3)
        }

        deleteButton.setOnClickListener {
            dbManager.deleteUser(104)
        }
    }
}