package com.example.afs_custom_search_ads_sdk

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import com.google.android.gms.ads.afsn.AdListener
import com.google.android.gms.ads.afsn.SearchAdController
import com.google.android.gms.ads.afsn.search.SearchAdOptions
import com.google.android.gms.ads.afsn.search.SearchAdRequest
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result


/** AfsCustomSearchAdsSdkPlugin */
class AfsCustomSearchAdsSdkPlugin: FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel
  private var adOptions: SearchAdOptions? = null
  private var adOptionBuilder: SearchAdOptions.Builder? = null
  private var searchAdController: SearchAdController? = null

  private val ADS_KEY = "NWAFSSearchAd_"
  private lateinit var mContext: Context
  private var adContainer: ViewGroup? = null

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "afs_custom_search_ads_sdk")
    mContext = flutterPluginBinding.applicationContext
    channel.setMethodCallHandler(this)
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    }

    if (call.method == "buildSearchAdOptions") {
      handleBuildSearchAdOptions(call)

    }

    if (call.method == "buildSearchAdController") {
      handleBuildSearchAdController(call)
    }

    if (call.method == "loadAds") {
      loadAds(call)
    }

    else {
      result.notImplemented()
    }
  }

  private fun handleBuildSearchAdController(call: MethodCall) {
    val styleId = call.argument<String>("styleId") ?: "6491009242"
    val publisherId = call.argument<String>("publisherId") ?: "pub-1848007123599752"
    searchAdController?.let {

    } ?: run {
      adOptions?.let {
        searchAdController = SearchAdController(
          mContext,
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

  private fun handleBuildSearchAdOptions(call: MethodCall) {
    val numOfAdsRequested = call.argument<Int>("numOfAdsRequested")
    val preFetch = call.argument<Boolean>("preFetch") ?: true

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

  private fun loadAds(call: MethodCall) {
    val query = call.argument<String>("query")
    val requestBuilder = SearchAdRequest.Builder()
    query?.let {
      requestBuilder.setQuery(it)
    }
    searchAdController?.loadAds(requestBuilder.build())
  }

  private fun createAndShowAds() {
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

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }
}
