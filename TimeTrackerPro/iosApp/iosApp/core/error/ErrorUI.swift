//
//  ErrorUI.swift
//  iosApp
//
//  Created by AndroidLab on 2024-04-05.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct ErrorUI: View {
    let onPositiveAction: (Error?) -> Void
    let onNegativeAction: () -> Void
    let error: Error?

    var body: some View {
        switch error {
        case is ApiError:
            ApiErrorDialog(
                onPositiveAction: { apiError in
                    onPositiveAction(apiError)
                },
                onNegativeAction: onNegativeAction,
                apiError: error as? ApiError
            )
        case is UiError:
            UiErrorToast(
                uiError: error as? UiError,
                onUiErrorDisplayed: onNegativeAction
            )
        default:
            EmptyView()
        }
    }
}
