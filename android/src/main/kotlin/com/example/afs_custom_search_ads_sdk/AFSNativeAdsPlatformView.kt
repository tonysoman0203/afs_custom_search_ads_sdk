package com.example.afs_custom_search_ads_sdk

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import com.google.android.gms.ads.afsn.AdListener
import com.google.android.gms.ads.afsn.SearchAdController
import com.google.android.gms.ads.afsn.search.SearchAdOptions
import com.google.android.gms.ads.afsn.search.SearchAdRequest
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.platform.PlatformView

class AFSNativeAdsPlatformView(
    private val context: Context?,
    messenger: BinaryMessenger,
    id: Int, creationParams: Any?,
) : PlatformView, MethodChannel.MethodCallHandler {

    private val methodChannel: MethodChannel

    private var adOptions: SearchAdOptions? = null
    private var adOptionBuilder: SearchAdOptions.Builder? = null
    private var searchAdController: SearchAdController? = null

    private var adsKey = ""
    private var cell: View? = null
    private var adsContainer: FrameLayout? = null

    init {
        cell = View.inflate(context!!, R.layout.cell_ads, null)
        adsContainer = cell?.findViewById(R.id.adsContainer)
        methodChannel = MethodChannel(messenger, "AFSNativeAds/$id")
        methodChannel.setMethodCallHandler(this)
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when (call.method) {
            "buildSearchAdOptions" ->  {
                buildSearchAdOptions(call.arguments as Map<String?, Any?>)
            }

            "buildSearchAdController" ->  {
                handleBuildSearchAdController(call.arguments as Map<String?, Any?>)
            }
            "loadAds" -> {
                loadAds(call.arguments as Map<String?, Any?>)
            }
        }
    }

    fun buildSearchAdOptions(call: Map<String?, Any?>) {
        val numOfAdsRequested = call["numOfAdsRequested"] as Int?
        val preFetch: Boolean = call["preFetch"] as Boolean? ?: true
        val searchChannel = call["channel"] as String?

        adOptionBuilder?.let {
            adOptions = it.build()
        }?: run {
            adOptionBuilder = SearchAdOptions.Builder()
            adOptionBuilder?.setAdType(SearchAdOptions.AD_TYPE_TEXT)
            adOptionBuilder?.setPrefetch(preFetch)
            numOfAdsRequested?.let {
                adOptionBuilder?.setNumAdsRequested(it)
            }
            searchChannel?.let {
                adOptionBuilder?.setChannel(it)
            }
            adOptions = adOptionBuilder?.build()
        }
    }

    fun loadAds(call: Map<String?, Any?>) {
        val keyword = call["keyword"] as String?
        adsKey = call["adKey"] as String? ?: ""
        val requestBuilder = SearchAdRequest.Builder()
        keyword?.let {
            requestBuilder.setQuery(it)
        }
        searchAdController?.loadAds(requestBuilder.build())
    }

    fun createAndShowAds() {
        Handler(Looper.getMainLooper()).post {
            val adView: View? = searchAdController?.createAdView()
            if (adView != null) {
                adsContainer?.addView(adView)
                searchAdController?.populateAdView(adView, adsKey)
            }
        }
    }

    fun handleBuildSearchAdController(call: Map<String?, Any?>) {
        val styleId = call["styleId"] as String? ?: "6491009242"
        val publisherId = call["publisherId"] as String? ?: "pub-1848007123599752"
        Log.d("BuildSearchAdController", "styleId $styleId publisherId $publisherId")
        searchAdController?.let {

        } ?: run {
            context?.let { safeContext ->
                adOptions?.let {
                    searchAdController = SearchAdController(
                        safeContext,
                        publisherId,
                        styleId,
                        it,
                        object : AdListener() {
                            override fun onAdFailedToLoad(p0: Int) {
                                super.onAdFailedToLoad(p0)
                            }

                            override fun onAdLeftApplication() {
                                super.onAdLeftApplication()
                            }

                            override fun onAdLoaded() {
                                super.onAdLoaded()
                                createAndShowAds()
                            }
                        }
                    )
                }
            }
        }
    }

    override fun getView(): View? = cell

    override fun dispose() {
        methodChannel.setMethodCallHandler(null)
        cell = null
    }
}