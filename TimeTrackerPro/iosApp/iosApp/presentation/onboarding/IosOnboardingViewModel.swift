//
//  IosOnboardingViewModel.swift
//  iosApp
//
//  Created by AndroidLab on 2024-04-06.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import Combine
import shared

extension OnboardingScreen {
    @MainActor class IosOnboardingViewModel: ObservableObject {
        private var settingDataSource: SettingsDataSource
        
        private var viewModel: OnboardingViewModel
        var cancellables = Set<AnyCancellable>()
        private var handle: DisposableHandle?
        
        @Published var state: OnboardingState = OnboardingState(
            userName: "",
            email: "",
            settingsItem: nil,
            event: nil,
            error: nil
        )
        
        init(settingsDataSource: SettingsDataSource) {
            self.settingDataSource = settingsDataSource
            self.viewModel = OnboardingViewModel(
                settingsDataSource: settingsDataSource,
                coroutineScope: nil)
        }
        
        func onEvent(event: OnboardingEvent){
            self.viewModel.onEvent(event: event)
        }
        
        func startObserving() {
            handle = viewModel.state.subscribe(onCollect: { state in
                if let state = state {
                    self.state = state
                }
            })
        }
        
        func dispose() {
            handle?.dispose()
        }
    }
}
