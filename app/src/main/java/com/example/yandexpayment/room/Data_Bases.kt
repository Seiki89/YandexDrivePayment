package com.example.yandexpayment

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
    (tableName = "Password")
data class Password_db (
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null,
    @ColumnInfo (name ="pass")
    var pass : Int
)

@Entity
    (tableName = "Settings")
data class Settings_db(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo (name ="KF1_min")
    var kf1_min: Int,
    @ColumnInfo (name ="KF_1_max")
    var kf1_max: Int,

    @ColumnInfo (name ="KF2_min")
    var kf2_min: Int,
    @ColumnInfo (name ="KF2_max")
    var kf2_max: Int,

    @ColumnInfo (name ="KF3_min")
    var kf3_min: Int,
    @ColumnInfo (name ="KF3_max")
    var kf3_max: Int,

    @ColumnInfo (name ="KF1_sum")
    var kf1_sum: Int,
    @ColumnInfo (name ="KF2_sum")
    var kf2_sum: Int,
    @ColumnInfo (name ="KF3_sum")
    var kf3_sum: Int,

    @ColumnInfo (name ="NDS_sum")
    var nds_sum: Int
)

@Entity
    (tableName = "MainBase")
data class MainBase_db(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo (name ="dOy")
    var doy: Int,
    @ColumnInfo (name ="day")
    var day: Int,
    @ColumnInfo (name ="month")
    var month: Int,
    @ColumnInfo (name ="year")
    var year: Int,
    @ColumnInfo (name ="litres")
    var litr: Int,
    @ColumnInfo (name ="rub")
    var rub: Int
)
