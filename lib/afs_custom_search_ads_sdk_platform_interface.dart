import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'afs_custom_search_ads_sdk_method_channel.dart';

abstract class AfsCustomSearchAdsSdkPlatform extends PlatformInterface {
  /// Constructs a AfsCustomSearchAdsSdkPlatform.
  AfsCustomSearchAdsSdkPlatform() : super(token: _token);

  static final Object _token = Object();

  static AfsCustomSearchAdsSdkPlatform _instance = MethodChannelAfsCustomSearchAdsSdk();

  /// The default instance of [AfsCustomSearchAdsSdkPlatform] to use.
  ///
  /// Defaults to [MethodChannelAfsCustomSearchAdsSdk].
  static AfsCustomSearchAdsSdkPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [AfsCustomSearchAdsSdkPlatform] when
  /// they register themselves.
  static set instance(AfsCustomSearchAdsSdkPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  // template generated

  Future<String?> buildSearchAdOptions({
    int? numOfAdsRequested,
    bool? preFetch,
    String? channel
  }) {
    throw UnimplementedError('buildSearchAdOptions() has not been implemented.');
  }

  Future<String?> buildSearchAdController({required String styleId, required String publisherId}) {
    throw UnimplementedError('buildSearchAdController() has not been implemented.');
  }

  Future<String?> loadAds(String keyword, String adKey) {
    throw UnimplementedError('loadAds() has not been implemented.');
  }
}
