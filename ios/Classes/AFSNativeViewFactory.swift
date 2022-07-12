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
        super.init()
    }
    
    public func create(withFrame frame: CGRect, viewIdentifier viewId: Int64, arguments args: Any?) -> FlutterPlatformView {
//        return AFSNativePlatformView((messenger: messenger,
//                                      frame: frame, viewId: viewId,
//                                      args: args)
        return AFSNativePlatfornView(frame: frame)
     }
                                     
}

class AFSNativePlatfornView: NSObject, FlutterPlatformView {
    
    var frame: CGRect
    
    init(frame: CGRect) {
        self.frame = frame
    }
    
    func view() -> UIView {
        return UIView(frame: frame)
    }
}
