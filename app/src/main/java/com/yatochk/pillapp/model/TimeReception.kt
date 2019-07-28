package com.yatochk.pillapp.model

import java.io.Serializable

data class TimeReception(
    var time: Long,
    var checked: Boolean
) : Serializable