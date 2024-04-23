//
//  ActionButton.swift
//  iosApp
//
//  Created by AndroidLab on 2024-04-06.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct WhatsNewNavigationButton: View {
    let isNext: Bool
    let onClick: () -> Void
    
    var spacing = LocalSpacing.current
    
    init(isNext: Bool,
         onClick: @escaping () -> Void) {
        self.isNext = isNext
        self.onClick = onClick
    }
    
    var body: some View {
        Button(action: onClick) {
            if(isNext) {
                Image(systemName: "arrow.forward")
                    .padding(spacing.spaceMedium)
                    .foregroundColor(Color.onPrimaryColor)
                    .background(Color.primaryColor)
                    .clipShape(Capsule())
            } else {
                Image(systemName: "arrow.backward")
                    .padding(spacing.spaceMedium)
                    .foregroundColor(Color.onPrimaryColor)
                    .background(Color.primaryColor)
                    .clipShape(Capsule())
            }
        }
    }
}
