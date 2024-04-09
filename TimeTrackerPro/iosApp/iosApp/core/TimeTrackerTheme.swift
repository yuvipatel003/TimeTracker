//
//  TimeTrackerTheme.swift
//  iosApp
//
//  Created by AndroidLab on 2024-04-06.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct TimeTrackerTheme {
    static var current: Fonts = .default
}

struct Fonts {
    var headlineLarge: Font = Font(CTFont(.systemDetail, size:30))
    var headlineMedium: Font = Font(CTFont(.systemDetail, size:24))
    var headlineSmall: Font = Font(CTFont(.systemDetail, size:18))
    var titleLarge: Font = Font(CTFont(.systemDetail, size:24))
    var titleMedium: Font = Font(CTFont(.systemDetail, size:20))
    var titleSmall: Font = Font(CTFont(.systemDetail, size:14))
    var bodyLarge: Font = Font(CTFont(.systemDetail, size:20))
    var bodyMedium: Font = Font(CTFont(.systemDetail, size:16))
    var bodySmall: Font = Font(CTFont(.systemDetail, size:12))

    static let `default` = Fonts()
}
