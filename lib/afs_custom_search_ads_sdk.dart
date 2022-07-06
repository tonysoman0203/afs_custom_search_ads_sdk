
import 'afs_custom_search_ads_sdk_platform_interface.dart';

class AfsCustomSearchAdsSdk {
  Future<String?> getPlatformVersion() {
    return AfsCustomSearchAdsSdkPlatform.instance.getPlatformVersion();
  }

  Future<void> loadAds({required String keyword}) async {
    return AfsCustomSearchAdsSdkPlatform.instance.loadAds(keyword: keyword);
  }

  Future<void> buildSearchAdController({required String styleId, required String publisherId}) async {
    return AfsCustomSearchAdsSdkPlatform.instance.buildSearchAdController(
      styleId: styleId, publisherId: publisherId
    );
  }

  Future<void> buildSearchAdOptions({int? numOfAdsRequested, bool? preFetch}) async {
    return AfsCustomSearchAdsSdkPlatform.instance.buildSearchAdOptions(numOfAdsRequested: numOfAdsRequested, preFetch: preFetch);
  }

}
