package com.appsdeviser.onboarding.presentation.splash

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.appsdeviser.core_db.domain.settings.SettingsItem
import com.appsdeviser.core_db.featuremanager.FeatureManager
import com.appsdeviser.onboarding.data.local.FakeAppConfig
import com.appsdeviser.onboarding.data.local.FakeFeatureDataSource
import com.appsdeviser.onboarding.data.local.FakeSettingsDataSource
import com.appsdeviser.onboarding.data.remote.FakeFeaturesClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class SplashViewModelTest {

    private lateinit var viewModel: SplashViewModel
    private lateinit var featureDataSource: FakeFeatureDataSource
    private lateinit var featuresClient: FakeFeaturesClient
    private lateinit var settingsDataSource: FakeSettingsDataSource
    private lateinit var appConfig: FakeAppConfig
    private lateinit var featureManager: FeatureManager

    @BeforeTest
    fun setUp() {
        featureDataSource = FakeFeatureDataSource()
        featuresClient = FakeFeaturesClient()
        settingsDataSource = FakeSettingsDataSource()
        appConfig = FakeAppConfig()
        featureManager = FeatureManager(
            featureDataSource =featureDataSource,
            appConfig = appConfig
        )
        viewModel = SplashViewModel(
            featureDataSource = featureDataSource,
            featuresClient = featuresClient,
            settingsDataSource = settingsDataSource,
            coroutineScope = CoroutineScope(Dispatchers.Default),
            appConfig = appConfig,
            featureManager = featureManager
        )
    }

    @Test
    fun `State and Settings Item are properly combined` () = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(SplashState())

            val item = SettingsItem(
                id = 0,
                userName = "",
                email = "",
                currentAppVersion = "1"
            )
            settingsDataSource.setSettings(item)

            val state = awaitItem()

            val expected = SettingsItem(
                id = item.id!!,
                userName = item.userName,
                email = item.email,
                currentAppVersion = item.currentAppVersion
            )

            assertThat(state.email).isEqualTo(expected.email)
            assertThat(state.username).isEqualTo(expected.userName)
            assertThat(state.event).isEqualTo(SplashEvent.GoToHomePage)
            assertThat(state.listOfFeatures).isEqualTo(FakeFeaturesClient.featureList.list)
        }
    }
}