//
//  DialogState.swift
//  iosApp
//
//  Created by AndroidLab on 2024-04-05.
//  Copyright © 2024 orgName. All rights reserved.
//

enum DialogPriority: Int {
    case HIGH = 1
    case MEDIUM = 2
    case LOW = 3
    case UNKNOWN = 1000
}

// Struct representing dialog state
struct DialogState {
    var title: String
    var description: String
    var positiveButtonText: String
    var negativeButtonText: String?
    var isCancelable: Bool
    var priority: DialogPriority
    
    init(title: String = "No Title",
         description: String = "No Description",
         positiveButtonText: String = "OK",
         negativeButtonText: String? = nil,
         isCancelable: Bool = true,
         priority: DialogPriority = .UNKNOWN) {
        self.title = title
        self.description = description
        self.positiveButtonText = positiveButtonText
        self.negativeButtonText = negativeButtonText
        self.isCancelable = isCancelable
        self.priority = priority
    }
}
