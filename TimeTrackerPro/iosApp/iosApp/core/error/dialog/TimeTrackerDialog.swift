//
//  TimeTrackerDialog.swift
//  iosApp
//
//  Created by AndroidLab on 2024-04-05.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

@ViewBuilder
func ApiErrorDialog(
    onPositiveAction: @escaping (ApiError?) -> Void,
    onNegativeAction: @escaping () -> Void,
    apiError: ApiError?
) -> some View {
    if let dialogState = getDialogState(for: apiError) {
        DialogWrapper(
            dialogState: dialogState,
            onPositiveAction: { onPositiveAction(apiError) },
            onNegativeAction: onNegativeAction
        )
    }
}

@ViewBuilder
private func DialogWrapper(
    dialogState: DialogState,
    onPositiveAction: @escaping () -> Void,
    onNegativeAction: @escaping () -> Void
) -> some View {
    Dialog(
        title: dialogState.title,
        description: dialogState.description,
        positiveButtonText: dialogState.positiveButtonText,
        negativeButtonText: dialogState.negativeButtonText,
        onPositiveAction: onPositiveAction,
        onNegativeAction: onNegativeAction,
        isCancelable: dialogState.isCancelable
    )
}

private func getDialogState(for apiError: ApiError?) -> DialogState? {
    guard let apiError = apiError else { return nil }
    
    switch (apiError as! ApiErrorNetwork) {
    case .serviceUnavailable:
        return DialogState(
            title: "Service Not Available",
            description: "The service is currently unavailable.",
            positiveButtonText: "OK",
            negativeButtonText: nil,
            isCancelable: false,
            priority: .HIGH
        )
    case .clientError, .serverError:
        return DialogState(
            title: "Something Went Wrong",
            description: "An error occurred. Please try again later.",
            positiveButtonText: "OK",
            negativeButtonText: nil,
            isCancelable: false,
            priority: .HIGH
        )
    case .unknownError, .serializationError, .dataTransformationError:
        return DialogState(
            title: "Missing Features",
            description: "Some features are not available at the moment.",
            positiveButtonText: "OK",
            negativeButtonText: nil,
            isCancelable: false,
            priority: .MEDIUM
        )
    default:
        return nil
    }
}

struct Dialog: View {
    let title: String
    let description: String
    let positiveButtonText: String
    let negativeButtonText: String?
    let onPositiveAction: () -> Void
    let onNegativeAction: () -> Void
    let isCancelable: Bool
    
    @State private var showDialog = true
    
    var body: some View {
        VStack {
        }
        .onAppear {
        }
        .alert(isPresented: $showDialog) {
            if let negativeButtonText = negativeButtonText {
                Alert(
                    title: Text(title).font(/*@START_MENU_TOKEN@*/.title/*@END_MENU_TOKEN@*/),
                    message: Text(description).font(.body),
                    primaryButton: .default(Text(positiveButtonText)) {
                        onPositiveAction()
                        showDialog = false
                    },
                    secondaryButton: .default(Text(negativeButtonText)) {
                        onNegativeAction()
                        showDialog = false
                    }
                )
            } else {
                Alert(
                    title: Text(title).font(/*@START_MENU_TOKEN@*/.title/*@END_MENU_TOKEN@*/),
                    message: Text(description).font(.body),
                    dismissButton: .default(Text(positiveButtonText)){
                        onNegativeAction()
                        showDialog = false
                    }
                )
            }
        }
    }
}
