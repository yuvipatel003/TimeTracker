//
//  ActionButton.swift
//  iosApp
//
//  Created by AndroidLab on 2024-04-06.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct ActionButton: View {
    let text: String
    let onClick: () -> Void
    let isEnabled: Bool
    let textStyle: Font
    
    init(text: String,
         onClick: @escaping () -> Void,
         isEnabled: Bool = true,
         textStyle: Font = .title2) {
        self.text = text
        self.onClick = onClick
        self.isEnabled = isEnabled
        self.textStyle = textStyle
    }
    
    var body: some View {
        Button(action: onClick) {
            Text(text)
                .frame(maxWidth: .infinity)
                .font(TimeTrackerTheme.current.titleSmall.bold())
                .padding()
                .foregroundColor(Color.white)
                .background(isEnabled ? Color.blue : Color.gray)
                .clipShape(Capsule())
        }
        .disabled(!isEnabled)
    }
}
