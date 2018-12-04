package com.igorronner.interstitialsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.google.android.gms.ads.formats.UnifiedNativeAdView
import com.igorronner.irinterstitial.init.IRAds
import kotlinx.android.synthetic.main.activity_another.*
import kotlinx.android.synthetic.main.card_ad_medium.*

class AnotherActivity : AppCompatActivity() {


    private lateinit var adsInstance: IRAds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_another)
        adsInstance = IRAds.newInstance(this)
        adsInstance.loadNativeAd(cardView as ViewGroup?, true, adViewNative as UnifiedNativeAdView)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        adsInstance.showInterstitial()
    }
}
