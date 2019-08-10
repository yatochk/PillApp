package com.yatochk.pillapp.utils

import com.google.android.gms.ads.AdRequest

fun getDefaultAdRequest(): AdRequest =
    AdRequest.Builder().addTestDevice(TEST_DEVICE).build()
