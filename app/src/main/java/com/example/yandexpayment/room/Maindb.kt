package com.example.yandexpayment.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.yandexpayment.*


@Database (entities = [Settings_db::class, Password_db::class, MainBase_db::class], version = 1)
abstract class Maindb : RoomDatabase() {
    abstract fun getDao(): Dao

    companion object {

        fun getdb(context: Context): Maindb{
            return Room.databaseBuilder(
                context.applicationContext,
                Maindb::class.java,
                "DataBaseFile.db").build()
        }
           }
}