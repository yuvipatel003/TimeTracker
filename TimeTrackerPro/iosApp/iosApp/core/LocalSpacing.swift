//
//  LocalSpacing.swift
//  iosApp
//
//  Created by AndroidLab on 2024-04-04.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
struct LocalSpacing {
    static var current: Spacing = .default
}

struct Spacing {
    var splashClockSize: CGFloat = 100
    var spaceMedium: CGFloat = 20
    var spaceLarge: CGFloat = 40
    var spaceExtraSmall: CGFloat = 5
    var spaceSmall: CGFloat = 10
    var spaceExtraLarge: CGFloat = 80
    
    static let `default` = Spacing()
}
