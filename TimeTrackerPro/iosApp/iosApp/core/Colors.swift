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
    
    static let primaryColorTimeTracker = Color(hex: colors.PrimaryColor)
    static let onPrimaryColorTimeTracker = Color(hex: colors.OnPrimaryColor)
    static let darkGreyBackground = Color(hex: colors.DarkGreyBackground)
    static let offWhiteBackground = Color(hex: colors.OffWhiteBackground)
    static let textColorBlack = Color(hex: colors.TextColorBlack)
    static let textColorWhite = Color(hex: colors.TextColorWhite)
        
    static let primaryColor = Color(lightColor: .primaryColorTimeTracker, darkColor: .primaryColorTimeTracker)
    static let backgroundColor = Color(lightColor: .offWhiteBackground, darkColor: .darkGreyBackground)
    static let onPrimaryColor = Color(lightColor: .onPrimaryColorTimeTracker, darkColor: .onPrimaryColorTimeTracker)
    static let onBackgroundColor = Color(lightColor: .textColorBlack, darkColor: .textColorWhite)
    static let surfaceColor = Color(lightColor: .offWhiteBackground, darkColor: .darkGreyBackground)
    static let onSurfaceColor = Color(lightColor: .textColorBlack, darkColor: .textColorWhite)
    
    static let lightPrimaryColor = Color(hex: colors.LightPrimaryColor)
    static let lightGreen = Color(hex: colors.LightGreen)
    static let lightRed = Color(hex: colors.LightRed)
    static let lightBlue = Color(hex: colors.LightBlue)
    static let lightPink = Color(hex: colors.LightPink)
    static let lightOrange = Color(hex: colors.LightOrange)
    static let lightViolet = Color(hex: colors.LightViolet)
    static let lightYellow = Color(hex: colors.LightYellow)
    static let lightIndigo = Color(hex: colors.LightIndigo)
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
