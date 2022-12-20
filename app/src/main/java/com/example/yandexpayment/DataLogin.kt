package com.example.yandexpayment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataLogin: ViewModel() {
    val changeToActivity : MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    } //загрузка активити Yandex
    val settingsToActivity : MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    } //загрузка настроек
    val registrationToActivity : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    } //загрузка  страницы регистрации
    val updateDbToActivity : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    } //загрузка  страницы перехаписи
}