import SwiftUI
import shared

struct ContentView: View {
    let greet = Greeting().greet()
    @State private var activeScreen: Screens = .SPLASH
    
    var appModule: AppModule = AppModule()
    
    var body: some View {
        VStack {
            switch activeScreen {
            case .SPLASH :
                SplashScreen(
                    featureDataSource: appModule.featureDataSource,
                    featureClient: appModule.featureClient,
                    settingsDataSource: appModule.settingsDataSource,
                    appConfig: appModule.appConfig,
                    featureManager: appModule.featureManager
                ) { event in
                    switch (event) {
                    case SplashEvent.GoToHomePage():
                        activeScreen = .HOME
                    case SplashEvent.GoToOnboarding():
                        activeScreen = .ONBOARDING
                    case SplashEvent.GoToWhatsNew():
                        activeScreen = .WHATS_NEW
                    default:
                        print("Default Case")
                    }
                }
            case .ONBOARDING:
                OnboardingScreen(
                    settingsDataSource: appModule.settingsDataSource) {
                        event in
                        switch (event) {
                        case OnboardingEvent.OnFinish():
                            activeScreen = .WHATS_NEW
                        default:
                            print("Default Case")
                        }
                    }
            case .WHATS_NEW:
                WhatsNewScreen(){
                    event in
                    switch (event) {
                    case WhatsNewEvent.OnFinish():
                        activeScreen = .HOME
                    default:
                        print("Default Case")
                    }
                }
            case .HOME:
                HomeScreen()
            }
        }
    }
}


/**
 onEvent: { event in
         switch (event) {
         case SplashEvent.GoToHomePage():
             activeScreen = .HOME
         case SplashEvent.GoToOnboarding():
             activeScreen = .ONBOARDING
         case SplashEvent.GoToWhatsNew():
             activeScreen = .WHATS_NEW
         default:
             print("Default Case")
         }
 }
 */
