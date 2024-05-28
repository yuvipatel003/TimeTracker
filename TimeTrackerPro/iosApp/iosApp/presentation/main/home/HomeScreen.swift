//
//  HomeScreen.swift
//  iosApp
//
//  Created by AndroidLab on 2024-04-17.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct HomeScreen: View {
    var body: some View {
        VStack {
//            HomeTitleBar(
//                title: "Title",
//                onSettingsClick: { print("Settings clicked") },
//                onNotificationClick: { print("Notification clicked") }
//            )
//            
            TitleBar(
            title: "Title is to big yess",
            onBackClick: { print("Back clicked") }
            )
            Spacer()
        }
    }
}

#Preview {
    HomeScreen()
}
