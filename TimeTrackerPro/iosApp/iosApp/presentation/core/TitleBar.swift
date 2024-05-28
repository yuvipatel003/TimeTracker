//
//  TitleBar.swift
//  iosApp
//
//  Created by AndroidLab on 2024-05-24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct TitleBar: View {
    var title: String = ""
    var onBackClick: () -> Void = {}

    var spacing = LocalSpacing.current
    
    var body: some View {
        
        ZStack {
            Color.primaryColor // Replace with your actual color
                .edgesIgnoringSafeArea(.top)
            
            HStack {
                Button(action: {
                    onBackClick()
                }) {
                    Image(systemName: "chevron.backward")
                        .foregroundColor(Color.white)
                }
                .frame(width: 44, height: 44)
                Spacer()
                Text(title)
                    .font(.headline)
                    .foregroundColor(Color.white)
                    .padding(.trailing, spacing.spaceLarge)
                Spacer()
            }
            .padding(.vertical, spacing.spaceExtraSmall)
            .padding(.horizontal, spacing.spaceExtraSmall)
                
        }
        .frame(height: 25)// Adjust the height as needed
    }
}

