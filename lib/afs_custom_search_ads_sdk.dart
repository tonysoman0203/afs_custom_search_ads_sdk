
import 'afs_custom_search_ads_sdk_platform_interface.dart';

class AfsCustomSearchAdsSdk {
  Future<String?> getPlatformVersion() {
    return AfsCustomSearchAdsSdkPlatform.instance.getPlatformVersion();
  }

  Future<String?> loadAds(String keyword) async {
    return AfsCustomSearchAdsSdkPlatform.instance.loadAds(keyword);
  }

  Future<String?> buildSearchAdController(String styleId, String publisherId) async {
    return AfsCustomSearchAdsSdkPlatform.instance.buildSearchAdController(
      styleId,
        publisherId
    );
  }

  Future<String?> buildSearchAdOptions(int? numOfAdsRequested, bool? preFetch) async {
    return AfsCustomSearchAdsSdkPlatform.instance.buildSearchAdOptions(
        numOfAdsRequested,
        preFetch
    );
  }

}
