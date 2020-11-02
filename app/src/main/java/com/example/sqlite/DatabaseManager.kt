package com.example.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseManager(context: Context) : SQLiteOpenHelper(context, DATABASENAME, null, DATABASEVERSION){

    companion object {
        private val DATABASENAME = "test"
        private val DATABASEVERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createQuery = "CREATE TABLE USER (userID INTEGER PRIMARY KEY,userName TEXT,userAge INTEGER)"
        if (db != null) {
            db.execSQL(createQuery)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS USER")
        }
    }

    fun addValues(users :User) {
        val db = this.writableDatabase
        var values = ContentValues()
        values.put("userID", users.userID)
        values.put("userName", users.userName)
        values.put("userAge", users.userAge)
        db.insert("USER", null, values)
        db.close()
    println("inserted user ${users.userID}")
    }

    fun getUsers(): List<User> {
        val db = this.writableDatabase
        val list = ArrayList<User>()
        val cursor: Cursor
        cursor = db.rawQuery("SELECT * FROM USER", null)
        if (cursor != null) {
            if (cursor.count > 0) {
                cursor.moveToFirst()
                do {
                    val userID = cursor.getInt(cursor.getColumnIndex("userID"))
                    val userName = cursor.getString(cursor.getColumnIndex("userName"))
                    val userAge = cursor.getInt(cursor.getColumnIndex("userAge"))
                    val user = User(userID, userName, userAge)
                    list.add(user)
                } while (cursor.moveToNext())
            }
        }
        return list
    }

    fun updateUser(users: User) {
        val db = this.writableDatabase
        var values = ContentValues()
        values.put("userID", users.userID)
        values.put("userName", users.userName)
        values.put("userAge", users.userAge)

        val update_result = db.update("USER", values, "userID = " + users.userID, null)
        if (update_result >= 1) {
            println(" Record updated for userID ${users.userID}")
        } else {
            println("Not updated")
        }
        db.close()

    }

    fun deleteUser(userID : Int) {
        val db = this.writableDatabase
        val deleteResult = db.delete("USER", "userID = '" + userID + "'", null)
        if (deleteResult >= 1) {
            println(" Record deleted for userID ${userID}")
        } else {
            println(" Record Not deleted")
        }
        db.close()
    }
}