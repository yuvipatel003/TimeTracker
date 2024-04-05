//
//  Colors.swift
//  iosApp
//
//  Created by AndroidLab on 2024-04-04.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

extension Color {
    init(hex: Int64, alpha:Double = 1) {
        self.init(
            .sRGB,
            red: Double((hex >> 16) & 0xff) / 255,
            green: Double((hex >> 08) & 0xff) / 255,
            blue: Double((hex >> 00) & 0xff) / 255,
            opacity: alpha
        )
    }

    private static let colors = Colors()
    static let lightBlue = Color(hex: colors.LightBlue)
    static let lightBlueGrey = Color(hex: colors.LightBlueGrey)
    static let primaryAppsDeviser = Color(hex: colors.PrimaryAppsDeviser)
    static let primaryColorAppsDeviser = Color(hex: colors.PrimaryColorAppsDeviser)
    static let secondaryColorAppsDeviser = Color(hex: colors.secondaryColorAppsDeviser)
    static let darkGrey = Color(hex: colors.DarkGrey)

    static let primaryColor = Color(lightColor: .primaryAppsDeviser, darkColor: .primaryAppsDeviser)
    static let backgroundColor = Color(lightColor: .lightBlueGrey, darkColor: .darkGrey)
    static let onPrimaryColor = Color(lightColor: .white, darkColor: .white)
    static let onBackgroundColor = Color(lightColor: .black, darkColor: .white)
    static let surfaceColor = Color(lightColor: .white, darkColor: .darkGrey)
    static let onSurfaceColor = Color(lightColor: .black, darkColor: .white)
}

private extension Color {
    init(lightColor: Self, darkColor:Self) {
        self.init(uiColor: UIColor(light: UIColor(lightColor), dark: UIColor(darkColor)))

    }
}

private extension UIColor {
    convenience init(light: UIColor, dark: UIColor) {
        self.init { traits in
        switch traits.userInterfaceStyle {
            case .light,.unspecified:
                return light
            case .dark:
                return dark
            @unknown default:
                return light
            }
        }
    }
}
