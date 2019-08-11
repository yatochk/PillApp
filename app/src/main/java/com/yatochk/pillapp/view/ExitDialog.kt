package com.yatochk.pillapp.view

import android.app.Activity
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.yatochk.pillapp.R
import com.yatochk.pillapp.utils.RemoteConfigConstant
import com.yatochk.pillapp.utils.getDefaultAdRequest
import kotlinx.android.synthetic.main.dialog_exit.view.*

class ExitDialog(private val activity: Activity) {

    private val view: View = activity.layoutInflater.inflate(R.layout.dialog_exit, null, false)
    private val buttonExit: Button
    private val buttonCancel: Button
    private var adView: AdView? = null
    private var listener: (() -> Unit)? = null

    private var frameLayout: FrameLayout? = null

    fun setOnCancelListener(l: () -> Unit) {
        listener = l
    }

    init {
        val layout = view.ad_frame
        adView = AdView(activity)
        adView!!.adSize = AdSize.MEDIUM_RECTANGLE
        adView!!.adUnitId = activity.getString(R.string.exit_ad)

        val adsParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.FILL_PARENT,
            FrameLayout.LayoutParams.WRAP_CONTENT,
            android.view.Gravity.TOP or android.view.Gravity.CENTER_HORIZONTAL
        )
        layout.addView(adView, adsParams)

        buttonExit = view.findViewById(R.id.exit_btn)
        buttonCancel = view.findViewById(R.id.cancel_btn)
    }

    fun loadAds() {
        if (FirebaseRemoteConfig.getInstance().getBoolean(RemoteConfigConstant.EXIT_AD)) {
            adView?.isVisible = true
            adView?.loadAd(getDefaultAdRequest())
        } else {
            adView?.isVisible = false
        }
    }

    fun show(frame: FrameLayout) {
        frameLayout = frame
        frame.addView(view)
        buttonExit.setOnClickListener { activity.finish() }
        buttonCancel.setOnClickListener {
            frame.removeView(view)
            listener?.invoke()
        }
    }

    fun cancel() {
        frameLayout!!.removeView(view)
        listener?.invoke()
    }
}