//
//  HomeTitleBar.swift
//  iosApp
//
//  Created by AndroidLab on 2024-05-24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct HomeTitleBar: View {
    var title: String = ""
    var onSettingsClick: () -> Void = {}
    var onNotificationClick: () -> Void = {}

    var spacing = LocalSpacing.current
    
    var body: some View {
        
        ZStack {
            Color.primaryColor // Replace with your actual color
                .edgesIgnoringSafeArea(.top)
            
            HStack {
                Text(title)
                    .font(.headline)
                    .foregroundColor(Color.white)
                    .padding(.leading, spacing.spaceSmall)
                
                Spacer()
                
                Button(action: {
                    onNotificationClick()
                }) {
                    Image(systemName: "bell")
                        .foregroundColor(Color.white)
                }
                .frame(width: 44, height: 44)
                
                Button(action: {
                    onSettingsClick()
                }) {
                    Image(systemName: "gearshape")
                        .foregroundColor(Color.white)
                }
                .frame(width: 44, height: 44)
            }
            .padding(.vertical, spacing.spaceExtraSmall)
            .padding(.horizontal, spacing.spaceSmall)
        }
        .frame(height: 25)// Adjust the height as needed
    }
}

