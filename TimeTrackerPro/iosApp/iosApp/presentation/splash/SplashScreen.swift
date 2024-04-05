//
//  SplashScreen.swift
//  iosApp
//
//  Created by AndroidLab on 2024-04-04.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SplashScreen: View {
    let featureDataSource: FeatureDataSource
    let featureClient: FeaturesClient
    let settingsDataSource: SettingsDataSource
    let appConfig: AppConfig
    let featureManager: FeatureManager
    var onEvent: (SplashEvent?) -> Void
    @ObservedObject var viewModel: IosSplashViewModel
    
    init(featureDataSource: FeatureDataSource,
         featureClient: FeaturesClient,
         settingsDataSource: SettingsDataSource,
         appConfig: AppConfig,
         featureManager: FeatureManager,
         onEvent: @escaping (SplashEvent?) -> Void) {
        self.featureDataSource = featureDataSource
        self.featureClient = featureClient
        self.settingsDataSource = settingsDataSource
        self.appConfig = appConfig
        self.featureManager = featureManager
        self.onEvent = onEvent
        self.viewModel = IosSplashViewModel(
            featureDataSource: featureDataSource,
            featureClient: featureClient,
            settingsDataSource: settingsDataSource,
            appConfig: appConfig,
            featureManager: featureManager
        )
        
        viewModel.$state.sink { state in
            if let splashEvent = state.event {
                onEvent(splashEvent)
            }
        }
        .store(in: &viewModel.cancellables)
    }
    
    @State private var scale: CGFloat = 1
    
    var body: some View {
        let spacing = LocalSpacing.current
        VStack {
            // Error Ui for api failure or any Ui message
        
            ErrorUI(
                onPositiveAction: { err in
                    
                },
                onNegativeAction: {
                    
                },
                error: viewModel.state.error
            )
            Spacer()
            VStack {
                AnalogClockView()
                    .frame(width: spacing.splashClockSize, height: spacing.splashClockSize)
                Spacer().frame(height: 120)
                Text("It's all about Time")
                    .font(Font.title.bold())
                    .foregroundColor(Color.onPrimaryColor)
                Spacer().frame(height: spacing.spaceMedium)
                if viewModel.state.isLoading {
                    ProgressView()
                        .animation(.easeInOut, value: viewModel.state.isLoading)
                        .padding()
                        .cornerRadius(100)
                        .progressViewStyle(CircularProgressViewStyle(tint: .white))
                }
            }
            Spacer()
        }
        .frame(maxWidth: .infinity, alignment: .center)
        .background(
            LinearGradient(gradient: Gradient(colors: [Color.primaryAppsDeviser, Color.primaryColorAppsDeviser]), startPoint: .top, endPoint: .bottom)
        )
        .edgesIgnoringSafeArea(.all)
        .onAppear {
            viewModel.startObserving()
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}
