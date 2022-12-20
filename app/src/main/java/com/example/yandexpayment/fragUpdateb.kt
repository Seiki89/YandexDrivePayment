package com.example.yandexpayment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import com.example.yandexpayment.databinding.FragmentFragUpdatebBinding
import com.example.yandexpayment.room.Maindb
import kotlinx.coroutines.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class fragUpdateb : Fragment() {
    lateinit var bind : FragmentFragUpdatebBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        bind = FragmentFragUpdatebBinding.inflate(inflater)
        return bind.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val db = Maindb.getdb(requireActivity())

        var daydb = 0
        var monthdb =0
        var yeardb = 0
        var doy = 0

        val scope = CoroutineScope(Dispatchers.Default)

        fun Load() = scope.launch {
            bind.txtDellApp.visibility = View.INVISIBLE
            db.getDao().getMain().toList().forEach {
                if (it.day == daydb && it.month == monthdb && it.year == yeardb) {
                    val delid = it.id
                    withContext(Dispatchers.Main) { bind.txtoutput.text =
                        "Дата:${it.day}.${it.month}.${it.year}   Пролив: ${it.litr}   Сумма: ${it.rub / 100}\n"}
                    fun del() = GlobalScope.launch {
                        db.getDao().dltMain(MainBase_db(delid,it.doy,it.day,it.month,it.year,it.litr,it.rub))
                    }
                    bind.button.setOnClickListener {
                        del()
                        bind.txtoutput.text = ""
                        bind.txtDellApp.visibility = View.VISIBLE
                    }
                }
            }
        }

        bind.calendarView.setOnDateChangeListener {
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
            Load()
        }


    }

    companion object {
        @JvmStatic
        fun newInstance() = fragUpdateb()

    }
}