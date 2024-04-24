//
//  CategoryWithColor.swift
//  iosApp
//
//  Created by AndroidLab on 2024-04-23.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

enum CategoryWithColor {
    case blue
    case green
    case red
    case pink
    case orange
    case violet
    case yellow
    case indigo
    case `default`

    var color: Color {
        switch self {
        case .blue:
            return Color.lightBlue
        case .green:
            return Color.lightGreen
        case .red:
            return Color.lightRed
        case .pink:
            return Color.lightPink
        case .orange:
            return Color.lightOrange
        case .violet:
            return Color.lightViolet
        case .yellow:
            return Color.lightYellow
        case .indigo:
            return Color.lightIndigo
        case .default:
            return Color.lightPrimaryColor
        }
    }

    static func fromString(name: String) -> CategoryWithColor {
        switch name {
        case CategoryColorCommon().LIGHT_ORANGE:
            return .orange
        case CategoryColorCommon().LIGHT_BLUE:
            return .blue
        case CategoryColorCommon().LIGHT_GREEN:
            return .green
        case CategoryColorCommon().LIGHT_RED:
            return .red
        case CategoryColorCommon().LIGHT_PINK:
            return .pink
        case CategoryColorCommon().LIGHT_VIOLET:
            return .violet
        case CategoryColorCommon().LIGHT_YELLOW:
            return .yellow
        case CategoryColorCommon().LIGHT_INDIGO:
            return .indigo
        default:
            return .default
        }
    }
}
