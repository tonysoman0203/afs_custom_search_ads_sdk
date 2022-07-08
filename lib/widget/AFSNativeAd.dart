import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import '../afs_custom_search_ads_sdk_method_channel.dart';

typedef void AFSNativeAdsCreatedCallback(MethodChannelAfsCustomSearchAdsSdk controller);

class AFSNativeAd extends StatelessWidget {
  static const StandardMessageCodec _decoder = StandardMessageCodec();
  AFSNativeAdsCreatedCallback? onAFSNativeAdsCreated;
  final int height;

  AFSNativeAd({
    Key? key,
    this.height = 250,
    this.onAFSNativeAdsCreated,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    if (defaultTargetPlatform == TargetPlatform.android) {
      return AndroidView(
          viewType: 'AFSNativeAds',
          onPlatformViewCreated: _onPlatformViewCreated,
          creationParams: args,
          creationParamsCodec: _decoder);
    }

    return UiKitView(
        viewType: 'MagicPlatformView',
        onPlatformViewCreated: _onPlatformViewCreated,
        creationParamsCodec: _decoder
    );
  }

  void _onPlatformViewCreated(int id) {
    if (onAFSNativeAdsCreated == null) {
      return;
    }
    onAFSNativeAdsCreated!(MethodChannelAfsCustomSearchAdsSdk(id: id));
  }
}
