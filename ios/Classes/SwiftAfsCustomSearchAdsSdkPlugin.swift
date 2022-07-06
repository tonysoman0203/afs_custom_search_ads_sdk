import Flutter
import UIKit

public class SwiftAfsCustomSearchAdsSdkPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "afs_custom_search_ads_sdk", binaryMessenger: registrar.messenger())
    let instance = SwiftAfsCustomSearchAdsSdkPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    result("iOS " + UIDevice.current.systemVersion)
  }
}
