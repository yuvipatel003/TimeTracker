//
//  UIErrorToast.swift
//  iosApp
//
//  Created by AndroidLab on 2024-04-05.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

@ViewBuilder
func UiErrorToast(
    uiError: UiError?,
    onUiErrorDisplayed: () -> Void
) -> some View {
    switch (uiError as! UiErrorNotification) {
    case .missingRequiredField:
        Toast(message: "Please provide all required information")
    case .invalidInput:
        Toast(message: "Invalid input")
    case .invalidEmail:
        Toast(message: "Invalid email")
    case .invalidUsername:
        Toast(message: "Invalid username")
    default:
        EmptyView()
    }
}

struct Toast: View {
    @State private var showToast = false
    private var toastMessage = ""
    
    init (message: String) {
        toastMessage = message
    }
    
    var body: some View {
        VStack {
            
        }.onAppear{
            showToast = !toastMessage.isEmpty
        }
        .alert(isPresented: $showToast) {
            Alert(
                title: Text(toastMessage)
            )
        }
    }
}

