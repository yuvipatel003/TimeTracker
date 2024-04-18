//
//  WhatsNewContent.swift
//  iosApp
//
//  Created by AndroidLab on 2024-04-16.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Combine

struct WhatsNewContent: View {
    let imageUrl: String
    let title: String
    let description: String
    
    var spacing = LocalSpacing.current
    
    var body: some View {
        VStack {
            AsyncImage(url: URL(string: imageUrl)) { image in
                image.resizable().aspectRatio(contentMode: .fit)
                    .frame(width: 300, height: 300)
                    .padding(spacing.spaceExtraSmall)
            } placeholder: {
                ProgressView()
            }
            
            Text(title)
                .font(.headline)
                .multilineTextAlignment(.leading)
                .padding(.horizontal, spacing.spaceMedium)
                .padding(.vertical, spacing.spaceExtraSmall)
            
            Text(description)
                .font(.body)
                .multilineTextAlignment(.leading)
                .padding(.horizontal, spacing.spaceMedium)
                .padding(.vertical, spacing.spaceExtraSmall)
        }
        .padding(.top, spacing.spaceExtraSmall)
    }
    
}
