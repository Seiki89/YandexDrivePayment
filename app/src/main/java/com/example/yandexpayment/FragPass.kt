package com.example.yandexpayment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.yandexpayment.databinding.FragmentFragPassBinding
import com.example.yandexpayment.room.Maindb
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class FragPass : Fragment() {
    lateinit var bind : FragmentFragPassBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentFragPassBinding.inflate(inflater)
        return bind.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val db = Maindb.getdb(requireActivity())

        fun pass() = GlobalScope.launch {
            db.getDao().getPassword().toList().forEach {
                val passOld = it.pass
                bind.butSavePass.setOnClickListener {
                    if (bind.enterPassR.text.isNotEmpty() && bind.enterPassR.text.isNotBlank()
                        && bind.newPass.text.isNotEmpty() && bind.newPass.text.isNotBlank()
                        && bind.newPass2.text.isNotEmpty() && bind.newPass2.text.isNotBlank()
                    ){

                    val newp= bind.newPass.text.toString().toInt()
                    val newp2 = bind.newPass2.text.toString().toInt()
                    val oldp = bind.enterPassR.text.toString().toInt()
                    if (passOld == oldp){
                        if (newp == newp2) {
                            Thread{
                                val changePassword = Password_db(1,
                                    bind.newPass2.text.toString().toInt())
                                db.getDao().insertPass(changePassword)
                                bind.txtError.text = "Пароль сохранен"
                            }.start()
                        } else bind.txtError.text = "Ошибка, новые пароли не совпадают"
                    } else bind.txtError.text = "Ошибка, старый пароль не верен"
                    } else Toast.makeText(context, "Заполните все поля", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        pass()
    }

        companion object {
            @JvmStatic
            fun newInstance() = FragPass()
        }
    }

