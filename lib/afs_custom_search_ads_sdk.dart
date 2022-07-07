
import 'afs_custom_search_ads_sdk_platform_interface.dart';

class AfsCustomSearchAdsSdk {

  Future<String?> loadAds(String keyword, String adKey) async {
    return AfsCustomSearchAdsSdkPlatform.instance.loadAds(keyword, adKey);
  }

  Future<String?> buildSearchAdController({required String styleId, required String publisherId}) async {
    return AfsCustomSearchAdsSdkPlatform.instance.buildSearchAdController(
      styleId : styleId,
        publisherId: publisherId
    );
  }

  Future<String?> buildSearchAdOptions({int? numOfAdsRequested, bool? preFetch, String? channel}) async {
    return AfsCustomSearchAdsSdkPlatform.instance.buildSearchAdOptions(
        numOfAdsRequested: numOfAdsRequested,
        preFetch: preFetch,
        channel: channel
    );
  }

}
