package com.example.yandexpayment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.yandexpayment.databinding.FragmentFragQuartBinding
import com.example.yandexpayment.room.Maindb
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FragQuart : Fragment() {
    lateinit var bind: FragmentFragQuartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentFragQuartBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val db = Maindb.getdb(requireActivity())
        val spinner : Spinner = bind.spinQuart

        val formatDay = DateTimeFormatter.ofPattern("yyyy")
        val dateTime = LocalDateTime.now()
        val yearNow = dateTime.format(formatDay).toInt() // текущий год


        ArrayAdapter.createFromResource(
            requireActivity(), R.array.QuartSpinner,
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
                    bind.txtSumQuart.text = "Сумма:"
                    bind.txtMonthQrt.text = "Месяц:"

                    val monthsum = mutableListOf<Int>()
                    val monthsum2 = mutableListOf<Int>()
                    val monthsum3 = mutableListOf<Int>()
                    val monthsum4 = mutableListOf<Int>()
                    val monthsum5 = mutableListOf<Int>()
                    val monthsum6 = mutableListOf<Int>()
                    val monthsum7 = mutableListOf<Int>()
                    val monthsum8 = mutableListOf<Int>()
                    val monthsum9 = mutableListOf<Int>()
                    val monthsum10 = mutableListOf<Int>()
                    val monthsum11 = mutableListOf<Int>()
                    val monthsum12 = mutableListOf<Int>()



                    fun quart1() = GlobalScope.launch {
                        db.getDao().getMain().toList().forEach {
                            if (it.month in 1..3 && it.year == yearNow) {
                                when (it.month){1 -> monthsum.add(it.rub/100)}
                                when (it.month){2 -> monthsum2.add(it.rub/100)}
                                when (it.month){3 -> monthsum3.add(it.rub/100)}
                            }
                        }
                        if (position == 1){
                            bind.txtMonthQrt.append("\n Январь \n Февраль \n Март")
                            bind.txtSumQuart.append("\n ${monthsum.sum()} \n ${monthsum2.sum()} \n ${monthsum3.sum()} ")
                            val sumqf = monthsum.sum()+monthsum2.sum()+monthsum3.sum()
                            bind.sumQuartFin.text = "Итого за квартал: ${sumqf}"
                            Thread{
                                db.getDao().getSett().toList().forEach {
                                    val precSum = sumqf*it.nds_sum/100
                                    bind.txtPrecQuart.text = "Сумма налога за квартал: $precSum"
                                }
                            }.start()//процент от общей суммы
                        }
                    }  // 1 Квартал
                    fun quart2() = GlobalScope.launch {
                        db.getDao().getMain().toList().forEach {
                            if (it.month in 4..6 && it.year == yearNow) {
                                when (it.month){4 -> monthsum4.add(it.rub/100)}
                                when (it.month){5 -> monthsum5.add(it.rub/100)}
                                when (it.month){6 -> monthsum6.add(it.rub/100)}
                            }
                        }
                        if (position == 2){
                            bind.txtMonthQrt.append("\n Апрель \n Май \n Июнь")
                            bind.txtSumQuart.append("\n ${monthsum4.sum()} \n ${monthsum5.sum()} \n ${monthsum6.sum()} ")
                            val sumqf = monthsum4.sum()+monthsum5.sum()+monthsum6.sum()
                            bind.sumQuartFin.text = "Итого за квартал: ${sumqf}"
                            Thread{
                                db.getDao().getSett().toList().forEach {
                                    val precSum = sumqf*it.nds_sum/100
                                    bind.txtPrecQuart.text = "Сумма налога за квартал: $precSum"
                                }
                            }.start() //процент от общей суммы
                        }
                    }  // 2 Квартал
                    fun quart3() = GlobalScope.launch {
                        db.getDao().getMain().toList().forEach {
                            if (it.month in 7..9 && it.year == yearNow) {
                                when (it.month){7 -> monthsum7.add(it.rub/100)}
                                when (it.month){8 -> monthsum8.add(it.rub/100)}
                                when (it.month){9 -> monthsum9.add(it.rub/100)}
                            }
                        }
                        if (position == 3){
                            bind.txtMonthQrt.append("\n Июль \n Август \n Сентябрь")
                            bind.txtSumQuart.append("\n ${monthsum7.sum()} \n ${monthsum8.sum()} \n ${monthsum9.sum()} ")
                            val sumqf = monthsum7.sum()+monthsum8.sum()+monthsum9.sum()
                            bind.sumQuartFin.text = "Итого за квартал: ${sumqf}"
                            Thread{
                                db.getDao().getSett().toList().forEach {
                                    val precSum = sumqf*it.nds_sum/100
                                    bind.txtPrecQuart.text = "Сумма налога за квартал: $precSum"
                                }
                            }.start() //процент от общей суммы
                        }
                    }  // 3 Квартал
                    fun quart4() = GlobalScope.launch {
                        db.getDao().getMain().toList().forEach {
                            if (it.month in 10..12 && it.year == yearNow) {
                                when (it.month){10 -> monthsum10.add(it.rub/100)}
                                when (it.month){11 -> monthsum11.add(it.rub/100)}
                                when (it.month){12 -> monthsum12.add(it.rub/100)}
                            }
                        }
                        if (position == 4){
                            bind.txtMonthQrt.append("\n Октябрь \n Ноябрь \n Декабрь")
                            bind.txtSumQuart.append("\n ${monthsum10.sum()} \n ${monthsum11.sum()} \n ${monthsum12.sum()} ")
                            val sumqf = monthsum10.sum()+monthsum11.sum()+monthsum12.sum()
                            bind.sumQuartFin.text = "Итого за квартал: ${sumqf}"
                            Thread{
                                db.getDao().getSett().toList().forEach {
                                    val precSum = sumqf*it.nds_sum/100
                                    bind.txtPrecQuart.text = "Сумма налога за квартал: $precSum"
                                }
                            }.start() //процент от общей суммы
                        }
                    }  // 4 Квартал
                    quart1()
                    quart2()
                    quart3()
                    quart4()

                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }
        }



    }

    companion object {
        @JvmStatic
        fun newInstance() = FragQuart()

    }
}