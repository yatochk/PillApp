package com.yatochk.pillapp.model

import com.yatochk.pillapp.R

enum class MedicationType {
    TABLET,
    CAPSULE,
    DROPS,
    INJECTION,
    MIXTURE,
    SPRAY,
    POWDER,
    OINTMENT,
    CANDLES,
    OTHER;

    fun getIcon() = when (this) {
        TABLET -> R.drawable.ic_tablet
        CAPSULE -> R.drawable.ic_pills
        DROPS -> R.drawable.ic_drop
        INJECTION -> R.drawable.ic_inject
        MIXTURE -> R.drawable.ic_mixture
        SPRAY -> R.drawable.ic_spray
        POWDER -> R.drawable.ic_powder
        OINTMENT -> R.drawable.ic_ointment
        CANDLES -> R.drawable.ic_suppository_capsule
        OTHER -> R.drawable.ic_star
    }
}