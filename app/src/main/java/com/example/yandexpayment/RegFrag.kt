package com.example.yandexpayment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.yandexpayment.databinding.FragmentRegBinding
import com.example.yandexpayment.room.Maindb
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class RegFrag : Fragment() {
    lateinit var bind: FragmentRegBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentRegBinding.inflate(inflater)
        return bind.root
    }


    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val db = Maindb.getdb(requireActivity())
        val safetyPassword = 193764825


        bind.butRegSave.setOnClickListener {
            if (bind.txtRegPass.text.isNotEmpty() && bind.txtRegPass.text.isNotBlank()
                && bind.txtSafetyPass.text.isNotEmpty() && bind.txtSafetyPass.text.isNotBlank()
            ) {

                if (safetyPassword == bind.txtSafetyPass.text.toString().toInt()) {
                    val creatPass = Password_db(1,
                        bind.txtRegPass.text.toString().toInt())

                    fun gpass() = GlobalScope.launch {
                        db.getDao().insertPass(creatPass)
                    }
                    gpass()
                    bind.txtInfo.text = "Пароль сохранен"
                    bind.txtInfo.visibility = View.VISIBLE
                } else {
                    bind.txtInfo.text = "Пароль безопастности не верен"
                    bind.txtInfo.visibility = View.VISIBLE
                }
            } else Toast.makeText(context, "Заполните все поля", Toast.LENGTH_SHORT)
                .show()
        }


    }

    companion object {

        @JvmStatic
        fun newInstance() = RegFrag()
    }
}