package com.example.yandexpayment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.yandexpayment.databinding.FragmentFragSettBinding
import com.example.yandexpayment.room.Maindb
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FragSett : Fragment() {
    lateinit var bind: FragmentFragSettBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentFragSettBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val db = Maindb.getdb(requireActivity())

        bind.butSaveSett.setOnClickListener {
            if (bind.kf1Min.text.isNotEmpty() && bind.kf1Min.text.isNotBlank()
                && bind.kf1Max.text.isNotEmpty() && bind.kf1Max.text.isNotBlank() &&
                bind.kf2Min.text.isNotEmpty() && bind.kf2Min.text.isNotBlank()
                && bind.kf2Max.text.isNotEmpty() && bind.kf2Max.text.isNotBlank() &&
                bind.kf3Min.text.isNotEmpty() && bind.kf3Min.text.isNotBlank()
                && bind.kf3Max.text.isNotEmpty() && bind.kf3Max.text.isNotBlank() &&
                bind.kf1Sum.text.isNotEmpty() && bind.kf1Sum.text.isNotBlank()
                && bind.kf2Sum.text.isNotEmpty() && bind.kf2Sum.text.isNotBlank() &&
                bind.kf3Sum.text.isNotEmpty() && bind.kf3Sum.text.isNotBlank()
                && bind.ndsSum.text.isNotEmpty() && bind.ndsSum.text.isNotBlank()
            ){
            bind.txtSavedd.visibility = View.VISIBLE
            val kf1s = bind.kf1Sum.text.toString().toFloat() * 100
            val kf2s = bind.kf2Sum.text.toString().toFloat() * 100
            val kf3s = bind.kf3Sum.text.toString().toFloat() * 100
            val settVal = Settings_db(1,
                bind.kf1Min.text.toString().toInt(),
                bind.kf1Max.text.toString().toInt(),
                bind.kf2Min.text.toString().toInt(),
                bind.kf2Max.text.toString().toInt(),
                bind.kf3Min.text.toString().toInt(),
                bind.kf3Max.text.toString().toInt(),
                kf1s.toInt(),
                kf2s.toInt(),
                kf3s.toInt(),
                bind.ndsSum.text.toString().toInt()
            )
            fun settSave() = GlobalScope.launch{
                db.getDao().insertSett(settVal)
            }
            settSave()
            } else Toast.makeText(context, "Заполните все поля", Toast.LENGTH_SHORT)
                .show()

        }
    }
    companion object {
        @JvmStatic
        fun newInstance() = FragSett()

    }
}