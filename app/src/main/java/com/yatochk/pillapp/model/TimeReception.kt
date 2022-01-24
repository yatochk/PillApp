package com.yatochk.pillapp.model

import com.yatochk.pillapp.utils.isEqualsDay
import java.io.Serializable
import java.util.*

data class TimeReception(
    var time: Long,
    var checkedDays: List<Long>
) : Serializable {

    fun isCheckedForDay(date: Date): Boolean {
        return checkedDays
            .map { Date(it) }
            .any {
                date.isEqualsDay(it)
            }
    }

}