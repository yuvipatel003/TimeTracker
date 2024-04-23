//
//  OnboardingScreen.swift
//  iosApp
//
//  Created by AndroidLab on 2024-04-05.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct OnboardingScreen: View {
    let settingsDataSource: SettingsDataSource
    var onEvent: (OnboardingEvent?) -> Void
    @ObservedObject var viewModel: IosOnboardingViewModel
    @State private var userNameState = ""
    @State private var emailState = ""
    
    init(settingsDataSource: SettingsDataSource,
         onEvent: @escaping (OnboardingEvent?) -> Void) {
        self.settingsDataSource = settingsDataSource
        self.onEvent = onEvent
        self.viewModel = IosOnboardingViewModel(
            settingsDataSource: settingsDataSource
        )
        
        userNameState = viewModel.state.userName
        emailState = viewModel.state.email
        
        viewModel.$state.sink { state in
            if let onboardingEvent = state.event {
                onEvent(onboardingEvent)
            }
        }
        .store(in: &viewModel.cancellables)
    }
    
    var body: some View {
        VStack {
            // Error Ui for api failure or any Ui message
            ErrorUI(
                onPositiveAction: { err in
                    viewModel.onEvent(event: OnboardingEvent.OnErrorSeen())
                },
                onNegativeAction: {
                    viewModel.onEvent(event: OnboardingEvent.OnErrorSeen())
                },
                error: viewModel.state.error
            )
            Spacer()
            Text("Welcome")
                .font(TimeTrackerTheme.current.headlineMedium.bold())
                .padding()
            
            Spacer()
                .frame(height: 20)
            
            TextField("Enter username", text: $userNameState)
                .padding(.horizontal)
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .onChange(of: $userNameState.wrappedValue) { newValue in
                    viewModel.onEvent(event: OnboardingEvent.OnUserNameUpdate(username: newValue))
                }
            
            Spacer()
                .frame(height: 15)
            
            TextField("Enter email", text: $emailState)
                .padding(.horizontal)
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .onChange(of: $emailState.wrappedValue) { newValue in
                    viewModel.onEvent(event: OnboardingEvent.OnEmailUpdate(email: newValue))
                }
            
            Spacer()
            
            HStack {
                OutlinedActionButton(text: "Skip", onClick: {
                    viewModel.onEvent(event: OnboardingEvent.OnSkip())
                })
                .frame(maxWidth: .infinity)
                Spacer().frame(width: 20)
                ActionButton(text: "Let's Get Started", onClick: {
                    viewModel.onEvent(event: OnboardingEvent.OnNext())
                })
                .frame(maxWidth: .infinity)
            }
            .padding()
            Spacer()
                .frame(height: 20)
        }
        .frame(maxWidth: .infinity, alignment: .center)
        .onAppear {
            viewModel.startObserving()
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}
