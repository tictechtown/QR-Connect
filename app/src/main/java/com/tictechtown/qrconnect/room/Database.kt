package com.tictechtown.qrconnect.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Account::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
}


class DatabaseProvider(private val context: Context) {

    private var instance: AppDatabase? = null

    fun getInstance(): AppDatabase {
        synchronized(this) {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "room_db"
                )
                    .build()
            }
            return instance!!
        }
    }
}