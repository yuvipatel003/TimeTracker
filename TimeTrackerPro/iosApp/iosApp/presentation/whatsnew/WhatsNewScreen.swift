//
//  WhatsNewScreen.swift
//  iosApp
//
//  Created by AndroidLab on 2024-04-05.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct WhatsNewScreen: View {
    let onEvent: (WhatsNewEvent) -> Void
    @ObservedObject var viewModel: IosWhatsNewViewModel
    var spacing = LocalSpacing.current
    
    init(onEvent: @escaping (WhatsNewEvent?) -> Void) {
        self.onEvent = onEvent
        self.viewModel = IosWhatsNewViewModel()
        
        viewModel.$state.sink { state in
            if let whatsNewEvent = state.event {
                onEvent(whatsNewEvent)
            }
        }
        .store(in: &viewModel.cancellables)
    }
    
    var body: some View {
        let selectedItem: WhatsNewItem? = viewModel.state.list.indices.contains(Int(viewModel.state.currentSelection)) ? viewModel.state.list[Int(viewModel.state.currentSelection)] : nil
        
        VStack {
            HStack {
                Spacer()
                Text("Skip")
                    .font(.headline)
                    .onTapGesture {
                        onEvent(WhatsNewEvent.OnFinish())
                    }
            }
            if let selectedItem = selectedItem {
                WhatsNewContent(imageUrl: selectedItem.image, title: selectedItem.title, description: selectedItem.description_)
            }
            
            HStack(spacing: spacing.spaceMedium) {
                Spacer()
                ForEach(viewModel.state.list.indices, id: \.self) { index in
                    Circle()
                        .foregroundColor(index == viewModel.state.currentSelection ? .primaryColor : .gray)
                        .frame(width: index == viewModel.state.currentSelection ? spacing.spaceSmall : spacing.spaceExtraSmall, height: index == viewModel.state.currentSelection ? spacing.spaceSmall : spacing.spaceExtraSmall)
                        .padding(0.5)
                }
                Spacer()
            }.padding(.top, spacing.spaceMedium)
            
            HStack {
                Spacer().frame(width: spacing.spaceExtraSmall)
                if viewModel.state.currentSelection != 0 {
                    WhatsNewNavigationButton(
                        isNext: false,
                        onClick: {
                            viewModel.onEvent(event:WhatsNewEvent.OnPrevious())
                        }
                    ).padding(.leading, spacing.spaceMedium)
                }
                Spacer()
                WhatsNewNavigationButton(
                    isNext: true,
                    onClick: {
                        viewModel.onEvent(event:WhatsNewEvent.OnNext())
                    }
                ).padding(.trailing, spacing.spaceMedium)
                Spacer().frame(width: spacing.spaceExtraSmall)
            }.padding(.top, spacing.spaceSmall)
        }
        .padding([.top, .bottom], spacing.spaceLarge)
        .padding([.leading, .trailing], spacing.spaceMedium)
        .onAppear {
            viewModel.startObserving()
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
    
}

