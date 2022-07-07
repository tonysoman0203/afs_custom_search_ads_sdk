package com.example.afs_custom_search_ads_sdk

import android.content.Context
import android.util.Log
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
class AfsCustomSearchAdsSdkPlugin: FlutterPlugin {
  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    flutterPluginBinding
      .platformViewRegistry
      .registerViewFactory(
        "AFSNativeAds",
        AFSNativeViewFactory(flutterPluginBinding.binaryMessenger)
      )
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {

  }
}
