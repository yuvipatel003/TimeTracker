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
    onUiErrorDisplayed: @escaping () -> Void
) -> some View {
    switch (uiError as! UiErrorNotification) {
    case .missingRequiredField:
        Toast(onErrorMessageDisplayed: onUiErrorDisplayed, toastMessage: "Please provide all required information")
    case .invalidInput:
        Toast(onErrorMessageDisplayed: onUiErrorDisplayed, toastMessage: "Invalid input")
    case .invalidEmail:
        Toast(onErrorMessageDisplayed: onUiErrorDisplayed, toastMessage: "Invalid email")
    case .invalidUsername:
        Toast(onErrorMessageDisplayed: onUiErrorDisplayed, toastMessage: "Invalid username")
    default:
        EmptyView()
    }
}

struct Toast: View {
    @State private var showToast = false
    let onErrorMessageDisplayed: () -> Void
    let toastMessage: String
    
    var body: some View {
        VStack {
            
        }.onAppear{
            showToast = !toastMessage.isEmpty
        }
        .alert(isPresented: $showToast) {
            Alert(
                title: Text(toastMessage),
                dismissButton: .default(Text("OK")){
                    onErrorMessageDisplayed()
                }
            )
        }
    }
}

