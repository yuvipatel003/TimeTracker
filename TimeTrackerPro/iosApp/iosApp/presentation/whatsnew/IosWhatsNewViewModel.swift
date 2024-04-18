//
//  IosWhatsNewViewModel.swift
//  iosApp
//
//  Created by AndroidLab on 2024-04-16.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import Combine
import shared

extension WhatsNewScreen {
    @MainActor class IosWhatsNewViewModel: ObservableObject {
        
        private var viewModel: WhatsNewViewModel
        var cancellables = Set<AnyCancellable>()
        private var handle: DisposableHandle?
        
        @Published var state: WhatsNewState = WhatsNewState(
            list: [], currentSelection: 0, event: nil
        )
        
        init() {
            self.viewModel = WhatsNewViewModel(
                coroutineScope: nil)
        }
        
        func onEvent(event: WhatsNewEvent){
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
