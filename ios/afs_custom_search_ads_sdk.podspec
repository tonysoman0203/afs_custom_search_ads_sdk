#
# To learn more about a Podspec see http://guides.cocoapods.org/syntax/podspec.html.
# Run `pod lib lint afs_custom_search_ads_sdk.podspec` to validate before publishing.
#
Pod::Spec.new do |s|
  s.name             = 'afs_custom_search_ads_sdk'
  s.version          = '0.0.1'
  s.summary          = 'A new Flutter plugin project.'
  s.description      = <<-DESC
A new Flutter plugin project.
                       DESC
  s.homepage         = 'https://github.com/tonysoman0203/flutter_afs_custom_search_ads_sdk/tree/develop'
  s.license          = { :file => '../LICENSE' }
  s.author           = { 'TonySoMan' => 'tonysoman.develop@gmail.com' }
  s.source           = { :path => '.' }
  s.source_files = 'Classes/**/*'
  s.dependency 'Flutter'
#  s.dependency 'Google-AFSNative'
  s.dependency 'Google-Mobile-Ads-SDK','9.6.0'
  s.platform = :ios, '9.0'

  # Flutter.framework does not contain a i386 slice.
  s.pod_target_xcconfig = { 'DEFINES_MODULE' => 'YES', 'EXCLUDED_ARCHS[sdk=iphonesimulator*]' => 'i386' }
  s.swift_version = '5.0'
end
