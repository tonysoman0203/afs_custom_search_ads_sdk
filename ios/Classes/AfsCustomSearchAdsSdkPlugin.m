#import "AfsCustomSearchAdsSdkPlugin.h"
#if __has_include(<afs_custom_search_ads_sdk/afs_custom_search_ads_sdk-Swift.h>)
#import <afs_custom_search_ads_sdk/afs_custom_search_ads_sdk-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "afs_custom_search_ads_sdk-Swift.h"
#endif

@implementation AfsCustomSearchAdsSdkPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftAfsCustomSearchAdsSdkPlugin registerWithRegistrar:registrar];
}
@end
