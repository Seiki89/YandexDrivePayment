package com.example.yandexpayment

import android.os.Bundle
import kotlinx.coroutines.*
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yandexpayment.databinding.FragmentFragWeekBinding
import com.example.yandexpayment.room.Maindb
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter



class FragWeek : Fragment() {



    lateinit var bind: FragmentFragWeekBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentFragWeekBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val db = Maindb.getdb(requireActivity())

        val formatDay = DateTimeFormatter.ofPattern("D")
        val formatDayOW = DateTimeFormatter.ofPattern("e")
        val dateTime = LocalDateTime.now()
        val doyv = dateTime.format(formatDay).toInt() //номер текущего дня в году
        val dowv = dateTime.format(formatDayOW).toInt() //номер текущего дня в неделе




        val scope = CoroutineScope(Dispatchers.Default)

        fun Week() = scope.launch {
            val finSumNew = mutableListOf<Int>()
            val finSumOld = mutableListOf<Int>()

            db.getDao().getMain().toList().forEach {
                val textNewDate = "\n ${it.day}.${it.month}.${it.year}"
                val textNewLitr = "\n ${it.litr}"
                val textNewRub = "\n ${it.rub/100}"

                if (dowv == 1) {
                    val days = doyv - 5
                    if (it.doy in days..doyv) {
                        withContext(Dispatchers.Main) {
                            bind.txtDateNew.append(textNewDate)
                            bind.txtLitrNew.append(textNewLitr)
                            bind.txtRubNew.append(textNewRub)
                        }
                        finSumNew.add(it.rub/100)
                    }
                } //новая неделя, если сегодня пн
                if (dowv == 2) {
                    val days = doyv - 6
                    if (it.doy in days..doyv) {
                        withContext(Dispatchers.Main) {
                            bind.txtDateNew.append(textNewDate)
                            bind.txtLitrNew.append(textNewLitr)
                            bind.txtRubNew.append(textNewRub)
                        }
                        finSumNew.add(it.rub/100)
                    }
                } //новая неделя, если сегодня вт
                if (dowv == 3) {
                    if (it.doy == doyv) {
                        withContext(Dispatchers.Main) {
                            bind.txtDateNew.append(textNewDate)
                            bind.txtLitrNew.append(textNewLitr)
                            bind.txtRubNew.append(textNewRub)
                        }
                        finSumNew.add(it.rub/100)
                    }
                } //новая неделя, если сегодня ср
                if (dowv == 4) {
                    val days = doyv - 1
                    if (it.doy in days..doyv) {
                        withContext(Dispatchers.Main) {
                            bind.txtDateNew.append(textNewDate)
                            bind.txtLitrNew.append(textNewLitr)
                            bind.txtRubNew.append(textNewRub)
                        }
                        finSumNew.add(it.rub/100)
                    }
                } //новая неделя, если сегодня чт
                if (dowv == 5) {
                    val days = doyv - 2
                    if (it.doy in days..doyv) {
                        withContext(Dispatchers.Main) {
                            bind.txtDateNew.append(textNewDate)
                            bind.txtLitrNew.append(textNewLitr)
                            bind.txtRubNew.append(textNewRub)
                        }
                        finSumNew.add(it.rub/100)
                    }
                } //новая неделя, если сегодня пт
                if (dowv == 6) {
                    val days = doyv - 3
                    if (it.doy in days..doyv) {
                        withContext(Dispatchers.Main) {
                            bind.txtDateNew.append(textNewDate)
                            bind.txtLitrNew.append(textNewLitr)
                            bind.txtRubNew.append(textNewRub)
                        }
                        finSumNew.add(it.rub/100)
                    }
                } //новая неделя, если сегодня сб
                if (dowv == 7) {
                    val days = doyv - 4
                    if (it.doy in days..doyv) {
                        withContext(Dispatchers.Main) {
                            bind.txtDateNew.append(textNewDate)
                            bind.txtLitrNew.append(textNewLitr)
                            bind.txtRubNew.append(textNewRub)
                        }
                    }
                } //новая неделя, если сегодня вск
            }


            db.getDao().getMain().toList().forEach {
                val textOldDate = "\n ${it.day}.${it.month}.${it.year}"
                val textOldLitr = "\n ${it.litr}"
                val textOldRub = "\n ${it.rub/100}"

                if (dowv == 1) {
                    val days = doyv - 12
                    val dayo = doyv - 6
                    if (it.doy in days..dayo) {
                        withContext(Dispatchers.Main) {
                            bind.txtDateOld.append(textOldDate)
                            bind.txtLitrOld.append(textOldLitr)
                            bind.txtRubOld.append(textOldRub)
                        }
                        finSumOld.add(it.rub/100)
                    }

                } //старая неделя, если сегодня пн
                if (dowv == 2) {
                    val days = doyv - 13
                    val dayo = doyv - 7
                    if (it.doy in days..dayo){
                        withContext(Dispatchers.Main) {
                            bind.txtDateOld.append(textOldDate)
                            bind.txtLitrOld.append(textOldLitr)
                            bind.txtRubOld.append(textOldRub)
                        }
                        finSumOld.add(it.rub/100)
                    }
                } //старая неделя, если сегодня вт
                if (dowv == 3) {
                    val days = doyv - 7
                    val dayo = doyv - 1
                    if (it.doy in days..dayo){
                        withContext(Dispatchers.Main) {
                            bind.txtDateOld.append(textOldDate)
                            bind.txtLitrOld.append(textOldLitr)
                            bind.txtRubOld.append(textOldRub)
                        }
                        finSumOld.add(it.rub/100)
                    }
                } //старая неделя, если сегодня ср
                if (dowv == 4) {
                    val days = doyv - 8
                    val dayo = doyv - 2
                    if (it.doy in days..dayo){
                        withContext(Dispatchers.Main) {
                            bind.txtDateOld.append(textOldDate)
                            bind.txtLitrOld.append(textOldLitr)
                            bind.txtRubOld.append(textOldRub)
                        }
                        finSumOld.add(it.rub/100)
                    }
                } //старая неделя, если сегодня чт
                if (dowv == 5) {
                    val days = doyv - 9
                    val dayo = doyv - 3
                    if (it.doy in days..dayo){
                        withContext(Dispatchers.Main) {
                            bind.txtDateOld.append(textOldDate)
                            bind.txtLitrOld.append(textOldLitr)
                            bind.txtRubOld.append(textOldRub)
                        }
                        finSumOld.add(it.rub/100)
                    }
                } //старая неделя, если сегодня пт
                if (dowv == 6) {
                    val days = doyv - 10
                    val dayo = doyv - 4
                    if (it.doy in days..dayo){
                        withContext(Dispatchers.Main) {
                            bind.txtDateOld.append(textOldDate)
                            bind.txtLitrOld.append(textOldLitr)
                            bind.txtRubOld.append(textOldRub)
                        }
                        finSumOld.add(it.rub/100)
                    }
                } //старая неделя, если сегодня сб
                if (dowv == 7) {
                    val days = doyv - 11
                    val dayo = doyv - 5
                    if (it.doy in days..dayo){
                        withContext(Dispatchers.Main) {
                            bind.txtDateOld.append(textOldDate)
                            bind.txtLitrOld.append(textOldLitr)
                            bind.txtRubOld.append(textOldRub)
                        }
                        finSumOld.add(it.rub/100)
                    }
                } //старая неделя, если сегодня вск

            }
            val finSumNew2 = finSumNew.sum()
            val finSumOld2 = finSumOld.sum()
            withContext(Dispatchers.Main) {
                bind.txtFinSumNew.text = "Итого за неделю: ${finSumNew2}"
                bind.txtFinSumOld.text = "Итого за неделю: ${finSumOld2}"
            }

        }
        Week()



    }
    companion object {
        @JvmStatic
        fun newInstance() = FragWeek()
    }
}