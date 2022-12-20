package com.example.yandexpayment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.yandexpayment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bindMain: ActivityMainBinding
    private val dataModel : DataLogin by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindMain.root)
        openFrag(FragLogin.newInstance(), R.id.placeHolderLogin)

        bindMain.passBottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.done -> {
                    openFrag(FragLogin.newInstance(), R.id.placeHolderLogin)
                }
                R.id.pass -> {
                    openFrag(FragPass.newInstance(), R.id.placeHolderLogin)
                }
                R.id.exit -> {
                    Toast.makeText(this, "выход", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            true
        } //нижняя навигация

        dataModel.changeToActivity.observe(this) {
            val bool = it
            if (bool == true) {
                Toast.makeText(this, "Инициализация", Toast.LENGTH_SHORT).show()
                val toYandexActivity = Intent(this, YandexActivity::class.java)
                startActivity(toYandexActivity)
            }
        } //переход на основной активити

        dataModel.registrationToActivity.observe(this) {
            val bool = it
            if (bool == 1) {
                Toast.makeText(this, "Регистрация", Toast.LENGTH_SHORT).show()
                openFrag(RegFrag.newInstance(), R.id.placeHolderLogin)
            }
        } //переход на Регистрацию.

    }

    private fun openFrag(f: Fragment, idHolder: Int) {
        supportFragmentManager
            .beginTransaction()
            .replace(idHolder, f)
            .commit()
    } //работа с фрагментами
}