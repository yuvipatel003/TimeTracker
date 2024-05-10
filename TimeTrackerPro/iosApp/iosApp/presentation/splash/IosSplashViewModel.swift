//
//  IosSplashViewModel.swift
//  iosApp
//
//  Created by AndroidLab on 2024-04-04.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI
import Combine

extension SplashScreen {
    
    @MainActor class IosSplashViewModel: ObservableObject {
        private var featureDataSource: FeatureDataSource
        private var featureClient: FeaturesClient
        private var settingsDataSource: SettingsDataSource
        private var appConfig: AppConfig
        private var featureManager: FeatureManager
        
        private var viewModel: SplashViewModel
        var cancellables = Set<AnyCancellable>()
        @Published var state: SplashState = SplashState(
            username: "",
            email: "",
            listOfFeatures:[FeatureItem](),
            isLoading: false,
            currentAppVersion: "",
            error: nil,
            event: nil
        )
        private var handle: DisposableHandle?
        
        
        init(featureDataSource: FeatureDataSource,
             featureClient: FeaturesClient,
             settingsDataSource: SettingsDataSource,
             appConfig: AppConfig,
             featureManager: FeatureManager) {
            self.featureDataSource = featureDataSource
            self.featureClient = featureClient
            self.settingsDataSource = settingsDataSource
            self.appConfig = appConfig
            self.featureManager = featureManager
            self.viewModel = SplashViewModel(
                featureDataSource: featureDataSource,
                featuresClient: featureClient,
                settingsDataSource: settingsDataSource,
                coroutineScope: nil,
                appConfig: appConfig,
                featureManager: featureManager
            )
        }
        
        func onEvent(event: SplashEvent){
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
