import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'afs_custom_search_ads_sdk_platform_interface.dart';

/// An implementation of [AfsCustomSearchAdsSdkPlatform] that uses method channels.
class MethodChannelAfsCustomSearchAdsSdk extends AfsCustomSearchAdsSdkPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('afs_custom_search_ads_sdk');

  // template generated
  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }

  @override
  Future<void> loadAds({required String keyword}) async {
    final result = await methodChannel.invokeMethod("loadAds");
    return result;
  }

  @override
  Future<void> buildSearchAdController({required String styleId, required String publisherId}) async {
    final result = await methodChannel.invokeMethod("buildSearchAdController", [
      MapEntry("styleId", styleId),
      MapEntry("publisherId", publisherId),
    ]);
    return result;
  }

  @override
  Future<void> buildSearchAdOptions({int? numOfAdsRequested, bool? preFetch}) async {
    final result = await methodChannel.invokeMethod("buildSearchAdOptions", [
      MapEntry("numOfAdsRequested", numOfAdsRequested),
      MapEntry("preFetch", preFetch),
    ]);
    return result;
  }
}
