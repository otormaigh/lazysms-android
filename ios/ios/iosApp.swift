//
//  iosApp.swift
//  ios
//
//  Created by Elliot on 10/10/2020.
//

import SwiftUI
import Persistence

@main
struct iosApp: App {
    var database = Database(DatabaseDriverFactory().createDriver())
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
