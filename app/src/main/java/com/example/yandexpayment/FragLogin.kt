package com.example.yandexpayment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels

import com.example.yandexpayment.databinding.FragmentFragLoginBinding
import com.example.yandexpayment.room.Maindb
import kotlinx.coroutines.*


class FragLogin : Fragment() {
    lateinit var bind: FragmentFragLoginBinding
    private val dataLogin : DataLogin by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentFragLoginBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val db = Maindb.getdb(requireActivity())

        bind.butReg.setOnClickListener {
            dataLogin.registrationToActivity.value = 1

        }
        val scope = CoroutineScope(Dispatchers.Default)
            fun login() = scope.launch {
                db.getDao().getPassword().toList().forEach {
                    var pass: Int = it.pass
                    bind.butEnter.setOnClickListener {
                        if (bind.txtPassEnter.text.isNotEmpty() || bind.txtPassEnter.text.isNotBlank()) {
                            if (pass == bind.txtPassEnter.text.toString().toInt()) {
                                bind.txtErrorr.visibility = View.INVISIBLE
                                dataLogin.changeToActivity.value = true
                            } else {
                                bind.txtErrorr.text = "Пароль введен не верно"
                                bind.txtErrorr.visibility = View.VISIBLE
                            }
                        } else Toast.makeText(context, "Введите пароль", Toast.LENGTH_SHORT)
                                .show()
                    }
                }
            }
            login()

        }
    companion object {
        @JvmStatic
         fun newInstance() = FragLogin()

                }
            }
