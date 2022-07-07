package com.example.afs_custom_search_ads_sdk

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.ads.afsn.AdListener
import com.google.android.gms.ads.afsn.SearchAdController
import com.google.android.gms.ads.afsn.search.SearchAdOptions
import com.google.android.gms.ads.afsn.search.SearchAdRequest
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory

class AFSNativeViewFactory(private val messenger: BinaryMessenger) : PlatformViewFactory(StandardMessageCodec.INSTANCE) {
    override fun create(context: Context?, viewId: Int, args: Any?): PlatformView {
        return AFSNativeAdsPlatformView(context, messenger, viewId, args)
    }
}