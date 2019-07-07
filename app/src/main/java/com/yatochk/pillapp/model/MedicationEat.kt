package com.yatochk.pillapp.model

import java.io.Serializable

data class MedicationEat(
    val minute: Int,
    val type: EatType
) : Serializable

enum class EatType {
    BEFORE,
    IN_TIME,
    AFTER
}