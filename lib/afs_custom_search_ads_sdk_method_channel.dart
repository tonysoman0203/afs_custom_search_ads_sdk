import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'afs_custom_search_ads_sdk_platform_interface.dart';

/// An implementation of [AfsCustomSearchAdsSdkPlatform] that uses method channels.
class MethodChannelAfsCustomSearchAdsSdk extends AfsCustomSearchAdsSdkPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  late MethodChannel methodChannel;

  MethodChannelAfsCustomSearchAdsSdk({int? id}) {
    methodChannel = MethodChannel('AFSNativeAds/$id');
  }

  @override
  Future<String?> loadAds(String keyword, String adKey) async {
    final result = await methodChannel.invokeMethod("loadAds", {"keyword":keyword, "adKey":adKey});
    return result;
  }

  @override
  Future<String?> buildSearchAdController(String styleId, String publisherId) async {
    final result = await methodChannel.invokeMethod("buildSearchAdController", <String, dynamic>{
      "styleId": styleId,
      "publisherId": publisherId,
    });
    return result;
  }

  @override
  Future<String?> buildSearchAdOptions({int? numOfAdsRequested, bool? preFetch}) async {
    final result = await methodChannel.invokeMethod("buildSearchAdOptions",  <String, dynamic>{
      "numOfAdsRequested": numOfAdsRequested,
      "preFetch": preFetch,
    });
    return result;
  }
}
