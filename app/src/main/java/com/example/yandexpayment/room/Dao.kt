package com.example.yandexpayment.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.yandexpayment.MainBase_db
import com.example.yandexpayment.Password_db
import com.example.yandexpayment.Settings_db


@Dao
interface Dao {
// логин, Регистрация, смена пароля
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertPass (pass: Password_db)
    @Query("SELECT * FROM Password")
    fun getPassword(): List<Password_db>
    @Query("DELETE FROM Password")
    fun nukeTable()
// меню настроек
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertSett (list: Settings_db)
    @Query ("SELECT * FROM Settings")
    fun getSett(): List<Settings_db>
//ввод и вывод данных отчетов
    @Insert
    fun insertMain (list: MainBase_db)
    @Query ("SELECT * FROM mainbase")
    fun getMain(): List<MainBase_db>
    @Delete
    fun dltMain (mainbaseDb: MainBase_db)
}