package com.example.yandexpayment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.yandexpayment.databinding.FragmentFragDayBinding
import com.example.yandexpayment.room.Maindb
import kotlinx.coroutines.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class FragDay : Fragment() {
    lateinit var bind : FragmentFragDayBinding
    private val dataLogin : DataLogin by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentFragDayBinding.inflate(inflater)
        return bind.root

       }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val db = Maindb.getdb(requireActivity())

        var rubs = 0
        var liters = 0
        var daydb = 0
        var monthdb =0
        var yeardb = 0
        var doy = 0


        bind.calendarViewY.setOnDateChangeListener {
                calView: CalendarView, year: Int, month: Int,dayOfMonth: Int->
            val calender: Calendar = Calendar.getInstance()
            calender.set(year, month, dayOfMonth,)
            calView.setDate(calender.timeInMillis, true, true)
            daydb = dayOfMonth
            monthdb = month+1
            yeardb = year
            val formatDay = DateTimeFormatter.ofPattern("D")
            val dateTime = LocalDate.of(yeardb,monthdb,daydb)
            doy = dateTime.format(formatDay).toInt()

            val scopeDbWrite = CoroutineScope(Dispatchers.Default)
            fun dbWrite() = scopeDbWrite.launch {
                db.getDao().getMain().toList().forEach(){
                    if (daydb == it.day && monthdb == it.month && yeardb == it.year){
                        withContext(Dispatchers.Main) {
                            bind.kfCalcSum.text = "Пролив: ${it.litr}, Сумма: ${it.rub/100} "
                        }
                    }
                }
            }
            dbWrite()
            bind.kfCalcSum.text = ""
            bind.dutLitr.setText("")
            bind.txtSaved.visibility = View.INVISIBLE

        }

        bind.imgButSettings.setOnClickListener {
            dataLogin.settingsToActivity.value = true
        } // кнока настроек

        bind.ibgButUpdate.setOnClickListener {
            dataLogin.updateDbToActivity.value = 2
        } // кнока обновления базы

        bind.saveDay.setOnClickListener {
            if (bind.dutLitr.text.isNotEmpty() || bind.dutLitr.text.isNotBlank()) {
                liters = bind.dutLitr.text.toString().toInt()
            } else Toast.makeText(activity, "Введите количество литров по ДУТ", Toast.LENGTH_SHORT).show()

            if (daydb != 0 && bind.dutLitr.text.isNotEmpty() && bind.dutLitr.text.isNotBlank()){
                val scope = CoroutineScope(Dispatchers.Default)

                fun main() = scope.launch {
                db.getDao().getSett().toList().forEach {
                    rubs = bind.dutLitr.text.toString().toInt()
                        when (rubs) {in it.kf1_min..it.kf1_max -> rubs *= it.kf1_sum}
                        when (rubs) {in it.kf2_min..it.kf2_max -> rubs *= it.kf2_sum}
                        when (rubs) {in it.kf3_min..it.kf3_max -> rubs *= it.kf3_sum}

                    withContext(Dispatchers.Main) {bind.kfCalcSum.text = "Заработок = ${rubs / 100}"}

                    if (rubs !=0) {
                        val inputBase = MainBase_db(null,
                            doy,
                            daydb,
                            monthdb,
                            yeardb,
                            liters,
                            rubs
                        )
                        fun saveDay() = GlobalScope.launch {
                            db.getDao().insertMain(inputBase)
                        }
                        saveDay()
                    } else withContext(Dispatchers.Main) {
                        {Toast.makeText(context,"Введите количество литров по ДУТ",Toast.LENGTH_SHORT).show()}
                    }

                }

            } //подсчет суммы дохода + сохранение в бд
                main()
                bind.txtSaved.visibility = View.VISIBLE
            } else {Toast.makeText(context,"Нажмите на нужную дату на календаре",Toast.LENGTH_SHORT).show()}
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = FragDay()
    }
}