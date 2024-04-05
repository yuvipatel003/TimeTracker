package com.appsdeviser.timetrackerpro.di

import app.cash.sqldelight.db.SqlDriver
import com.appsdeviser.AppConfig
import com.appsdeviser.core_api.remote.HttpClientFactory
import com.appsdeviser.core_db.data.feature.FeatureDataSourceImpl
import com.appsdeviser.core_db.data.settings.SettingsDataSourceImpl
import com.appsdeviser.core_db.databasedriver.DatabaseDriverFactory
import com.appsdeviser.core_db.domain.feature.FeatureDataSource
import com.appsdeviser.core_db.domain.settings.SettingsDataSource
import com.appsdeviser.core_db.featuremanager.FeatureManager
import com.appsdeviser.core_db.sqldelight.TimeTrackerDatabase
import com.appsdeviser.onboarding.data.remote.KtorClientFeatures
import com.appsdeviser.onboarding.domain.features.FeaturesClient
import io.ktor.client.HttpClient

class AppModule {

    val httpClient: HttpClient by lazy {
        HttpClientFactory().create()
    }

    val featureClient: FeaturesClient by lazy {
        KtorClientFeatures(httpClient)
    }

    val databaseDriver: SqlDriver by lazy {
        DatabaseDriverFactory().create()
    }

    val featureDataSource: FeatureDataSource by lazy {
        FeatureDataSourceImpl(TimeTrackerDatabase.invoke(databaseDriver))
    }

    val settingsDataSource: SettingsDataSource by lazy {
        SettingsDataSourceImpl(TimeTrackerDatabase.invoke(databaseDriver))
    }

    val appConfig: AppConfig by lazy {
        IosAppConfigImpl()
    }

    val featureManager: FeatureManager by lazy {
        FeatureManager(featureDataSource, appConfig)
    }
}