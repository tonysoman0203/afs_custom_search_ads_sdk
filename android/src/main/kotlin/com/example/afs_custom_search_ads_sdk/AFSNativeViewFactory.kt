package com.example.afs_custom_search_ads_sdk

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.ads.afsn.AdListener
import com.google.android.gms.ads.afsn.SearchAdController
import com.google.android.gms.ads.afsn.search.SearchAdOptions
import com.google.android.gms.ads.afsn.search.SearchAdRequest
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory

class AFSNativeViewFactory : PlatformViewFactory(StandardMessageCodec.INSTANCE) {
    private var adOptions: SearchAdOptions? = null
    private var adOptionBuilder: SearchAdOptions.Builder? = null
    private var searchAdController: SearchAdController? = null

    private val ADS_KEY = "NWAFSSearchAd_"
    private var adContainer: ViewGroup? = null

    override fun create(context: Context?, viewId: Int, args: Any?): PlatformView {
        val creationParams = args as Map<String?, Any?>?
        if (creationParams != null) {
            handleBuildSearchAdOptions(creationParams)
            handleBuildSearchAdController(context, creationParams)
            loadAds(creationParams)
        }

        return AFSNativeAdsView(context!!, viewId, creationParams, adContainer!!)
    }

    fun handleBuildSearchAdOptions(call: Map<String?, Any?>) {
        val numOfAdsRequested = call["numOfAdsRequested"] as Int?
        val preFetch: Boolean = call["preFetch"] as Boolean? ?: true

        adOptionBuilder?.let {
            adOptions = it.build()
        }?: run {
            adOptionBuilder = SearchAdOptions.Builder()
            adOptionBuilder?.setAdType(SearchAdOptions.AD_TYPE_TEXT)
            adOptionBuilder?.setPrefetch(preFetch)
            numOfAdsRequested?.let {
                adOptionBuilder?.setNumAdsRequested(it)
            }
            adOptions = adOptionBuilder?.build()
        }
    }

    fun loadAds(call: Map<String?, Any?>) {
        val keyword = call["keyword"] as String?
        val requestBuilder = SearchAdRequest.Builder()
        keyword?.let {
            requestBuilder.setQuery(it)
        }
        searchAdController?.loadAds(requestBuilder.build())
    }

    fun createAndShowAds() {
        // Create a new view that will contain the ad.
        val adView: View? = searchAdController?.createAdView()
        // Attach the new view to the view hierarchy.

        adContainer?.addView(adView)
        // Display the ad inside the adView. We need to provide an adKey to
        // indicate which ad is to be displayed in the adView. In this example,
        // since we only have one ad, we can provide any constant string. However,
        // if you intend to display multiple ads, each ad you wish to display
        // should be given a unique adKey of your choosing.
        adView?.let {
            searchAdController?.populateAdView(it, ADS_KEY)
        }
    }

    fun handleBuildSearchAdController(context: Context?, call: Map<String?, Any?>) {
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
}