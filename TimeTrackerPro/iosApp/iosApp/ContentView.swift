import SwiftUI
import shared

struct ContentView: View {
    let greet = Greeting().greet()
    
    var appModule: AppModule = AppModule()
    
    var body: some View {
        SplashScreen(
            featureDataSource: appModule.featureDataSource,
            featureClient: appModule.featureClient,
            settingsDataSource: appModule.settingsDataSource,
            appConfig: appModule.appConfig,
            featureManager: appModule.featureManager
        )
    }
}
