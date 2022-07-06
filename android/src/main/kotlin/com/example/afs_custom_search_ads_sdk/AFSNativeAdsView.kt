package com.example.afs_custom_search_ads_sdk

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.flutter.plugin.platform.PlatformView

internal class AFSNativeAdsView(
    context: Context,
    id: Int, creationParams:
    Map<String?, Any?>?,
    private val viewGroup: ViewGroup
): PlatformView {
    override fun getView(): View = viewGroup

    override fun dispose() {
        TODO("Not yet implemented")
    }
}