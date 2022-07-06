import 'package:flutter_test/flutter_test.dart';
import 'package:afs_custom_search_ads_sdk/afs_custom_search_ads_sdk.dart';
import 'package:afs_custom_search_ads_sdk/afs_custom_search_ads_sdk_platform_interface.dart';
import 'package:afs_custom_search_ads_sdk/afs_custom_search_ads_sdk_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockAfsCustomSearchAdsSdkPlatform 
    with MockPlatformInterfaceMixin
    implements AfsCustomSearchAdsSdkPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final AfsCustomSearchAdsSdkPlatform initialPlatform = AfsCustomSearchAdsSdkPlatform.instance;

  test('$MethodChannelAfsCustomSearchAdsSdk is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelAfsCustomSearchAdsSdk>());
  });

  test('getPlatformVersion', () async {
    AfsCustomSearchAdsSdk afsCustomSearchAdsSdkPlugin = AfsCustomSearchAdsSdk();
    MockAfsCustomSearchAdsSdkPlatform fakePlatform = MockAfsCustomSearchAdsSdkPlatform();
    AfsCustomSearchAdsSdkPlatform.instance = fakePlatform;
  
    expect(await afsCustomSearchAdsSdkPlugin.getPlatformVersion(), '42');
  });
}
