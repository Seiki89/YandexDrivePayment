package com.example.yandexpayment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.yandexpayment.databinding.FragmentFragMonthBinding
import com.example.yandexpayment.room.Maindb
import kotlinx.coroutines.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class FragMonth : Fragment() {
    lateinit var bind : FragmentFragMonthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        bind = FragmentFragMonthBinding.inflate(inflater)
        return bind.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val db = Maindb.getdb(requireActivity())
        val spinner : Spinner = bind.spinMonth

        val formatDay = DateTimeFormatter.ofPattern("yyyy")
        val dateTime = LocalDateTime.now()
        val yearNow = dateTime.format(formatDay).toInt() // текущий год

        if (spinner != null) {
            ArrayAdapter.createFromResource(
                requireActivity(), R.array.MonthSpinner,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter

                spinner.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View, position: Int, id: Long
                    ) {
                        bind.listRub.text = "Сумма"
                        bind.listLiter.text = "Литры"
                        bind.listDate.text = "Дата"
                        bind.txtFinSum.text = ""
                        val list = mutableListOf<Int>() //список сумм готовый

                        val scope = CoroutineScope(Dispatchers.Default)
                        fun monthCalc() = scope.launch {
                            db.getDao().getMain().toList().forEach {
                                if (it.month == position && it.year == yearNow) {
                                    val date = "\n ${it.day}.${it.month}.${it.year}"
                                    val liter = "\n ${it.litr}"
                                    val rub = "\n ${it.rub / 100}"
                                    withContext(Dispatchers.Main) {
                                        bind.listDate.append(date)
                                        bind.listLiter.append(liter)
                                        bind.listRub.append(rub)}
                                    list.add(it.rub)
                                }
                            }
                            val listSum = list.sum()/100
                            withContext(Dispatchers.Main) {bind.txtFinSum.text = "Итого за месяц: $listSum"}

                            fun monthPrecCalc() = GlobalScope.launch{
                                db.getDao().getSett().toList().forEach {
                                    val prec = it.nds_sum
                                    val precSum = (list.sum()/100)*prec/100
                                    withContext(Dispatchers.Main) {bind.txtNds.text = "Сумма налога за месяц: $precSum"}
                                }
                            } //процент от общей суммы

                            monthPrecCalc()
                        }
                        monthCalc()


                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                    }
                } //Адаптер спиннера
            }
        } //Спиннер + бд
    }
    companion object {
        @JvmStatic
        fun newInstance() = FragMonth()
    }
}