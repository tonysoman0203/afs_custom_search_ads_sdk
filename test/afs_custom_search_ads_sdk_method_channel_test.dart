import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:afs_custom_search_ads_sdk/afs_custom_search_ads_sdk_method_channel.dart';

void main() {
  MethodChannelAfsCustomSearchAdsSdk platform = MethodChannelAfsCustomSearchAdsSdk();
  const MethodChannel channel = MethodChannel('afs_custom_search_ads_sdk');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await platform.getPlatformVersion(), '42');
  });
}
