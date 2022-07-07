//
//  AFSNativeViewFactory.swift
//  afs_custom_search_ads_sdk
//
//  Created by Tony So on 7/7/2022.
//

import Flutter
import UIKit

public class AFSNativeViewFactory: NSObject, FlutterPlatformViewFactory {
    init(messenger: FlutterBinaryMessenger) {
        self.messenger = messenger
    }
    
    public func create(withFrame frame: CGRect, viewIdentifier viewId: Int64, arguments args: Any?) -> FlutterPlatformView {
        return AFSNativePlatformView((messenger: messenger,
                                      frame: frame, viewId: viewId,
                                      args: args)
                                     
     }
                                     
}
