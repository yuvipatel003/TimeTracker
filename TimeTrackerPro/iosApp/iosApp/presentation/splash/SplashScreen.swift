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
    private var featureDataSource: FeatureDataSource
    private var featureClient: FeaturesClient
    private var settingsDataSource: SettingsDataSource
    private var appConfig: AppConfig
    private var featureManager: FeatureManager
    @ObservedObject var viewModel: IosSplashViewModel
    
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
        self.viewModel = IosSplashViewModel(
            featureDataSource: featureDataSource,
            featureClient: featureClient,
            settingsDataSource: settingsDataSource,
            appConfig: appConfig,
            featureManager: featureManager
        )
    }
    
    @State private var scale: CGFloat = 1
    
    var body: some View {
        let spacing = LocalSpacing.current
        VStack {
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
//                else {
//                    Text(appConfig.applicationVersion + "Code:" + appConfig.appVersionCode!.stringValue)
//                }
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
