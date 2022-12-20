package com.example.yandexpayment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.yandexpayment.databinding.ActivityYandexBinding

class YandexActivity : AppCompatActivity() {
    private lateinit var bindYandex: ActivityYandexBinding
    private val dataModel : DataLogin by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindYandex = ActivityYandexBinding.inflate(layoutInflater)
        setContentView(bindYandex.root)
        openFrag(FragDay.newInstance(), R.id.placeHolder)

        dataModel.settingsToActivity.observe(this) {
            val bool = it
            if (bool == true) {
                Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
                openFrag(FragSett.newInstance(), R.id.placeHolder)
            }
        } //переход на настройки

        dataModel.updateDbToActivity.observe(this) {
            val bool = it
            if (bool == 2) {
                Toast.makeText(this, "Перезапись базы", Toast.LENGTH_SHORT).show()
                openFrag(fragUpdateb.newInstance(), R.id.placeHolder)
            }
        } //переход на настройки

        bindYandex.BottomYandex.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.sum_day -> {
                    openFrag(FragDay.newInstance(), R.id.placeHolder)
                }
                R.id.sum_week -> {
                    openFrag(FragWeek.newInstance(), R.id.placeHolder)
                }
                R.id.sum_month -> {
                    openFrag(FragMonth.newInstance(), R.id.placeHolder)
                }
                R.id.sum_quarter -> {
                    openFrag(FragQuart.newInstance(), R.id.placeHolder)
                }
                R.id.exit -> {
                    Toast.makeText(this, "ВЫХОД", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            true

        }  //нижняя навигация
    }
    private fun openFrag(f: Fragment, idHolder: Int) {
        supportFragmentManager
            .beginTransaction()
            .replace(idHolder, f)
            .commit()
    } //работа с фрагментами
}