package com.appsdeviser.timetrackerpro.android.di

import android.app.Application
import app.cash.sqldelight.db.SqlDriver
import com.appsdeviser.AppConfig
import com.appsdeviser.core_api.remote.HttpClientFactory
import com.appsdeviser.core_db.data.feature.FeatureDataSourceImpl
import com.appsdeviser.core_db.data.settings.SettingsDataSourceImpl
import com.appsdeviser.core_db.data.showrecordpage.ShowRecordPageSettingDataSourceImpl
import com.appsdeviser.core_db.databasedriver.DatabaseDriverFactory
import com.appsdeviser.core_db.domain.feature.FeatureDataSource
import com.appsdeviser.core_db.domain.settings.SettingsDataSource
import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingDataSource
import com.appsdeviser.core_db.featuremanager.FeatureManager
import com.appsdeviser.core_db.sqldelight.TimeTrackerDatabase
import com.appsdeviser.onboarding.data.remote.KtorClientFeatures
import com.appsdeviser.onboarding.domain.features.FeaturesClient
import com.appsdeviser.timetrackerpro.android.AppConfigImpl
import com.appsdeviser.tracker.data.category.CategoryDataSourceImpl
import com.appsdeviser.tracker.data.record.RecordDataSourceImpl
import com.appsdeviser.tracker.data.record.active.ActiveRecordDataSourceImpl
import com.appsdeviser.tracker.domain.category.CategoryDataSource
import com.appsdeviser.tracker.domain.record.RecordDataSource
import com.appsdeviser.tracker.domain.record.active.ActiveRecordDataSource
import com.appsdeviser.tracker.presentation.record.view.FilterRecord
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClientFactory().create()
    }

    @Provides
    @Singleton
    fun provideFeatureClient(httpClient: HttpClient): FeaturesClient {
        return KtorClientFeatures(httpClient)
    }

    @Provides
    @Singleton
    fun provideDatabaseDriver(application: Application): SqlDriver {
        return DatabaseDriverFactory(application).create()
    }

    @Provides
    @Singleton
    fun provideFeatureDataSource(sqlDriver: SqlDriver): FeatureDataSource {
        return FeatureDataSourceImpl(TimeTrackerDatabase.invoke(sqlDriver))
    }

    @Provides
    @Singleton
    fun provideSettingsDataSource(sqlDriver: SqlDriver): SettingsDataSource {
        return SettingsDataSourceImpl(TimeTrackerDatabase.invoke(sqlDriver))
    }

    @Provides
    @Singleton
    fun provideCategoryDataSource(sqlDriver: SqlDriver): CategoryDataSource {
        return CategoryDataSourceImpl(TimeTrackerDatabase.invoke(sqlDriver))
    }

    @Provides
    @Singleton
    fun provideShowRecordPageSetting(sqlDriver: SqlDriver): ShowRecordPageSettingDataSource {
        return ShowRecordPageSettingDataSourceImpl(TimeTrackerDatabase.invoke(sqlDriver))
    }

    @Provides
    @Singleton
    fun provideRecordDataSource(sqlDriver: SqlDriver): RecordDataSource {
        return RecordDataSourceImpl(TimeTrackerDatabase.invoke(sqlDriver))
    }

    @Provides
    @Singleton
    fun provideActiveRecordDataSource(sqlDriver: SqlDriver): ActiveRecordDataSource {
        return ActiveRecordDataSourceImpl(TimeTrackerDatabase.invoke(sqlDriver))
    }

    @Provides
    @Singleton
    fun provideFeatureManager(
        featureDataSource: FeatureDataSource,
        appConfig: AppConfig
    ): FeatureManager {
        return FeatureManager(featureDataSource, appConfig)
    }

    @Provides
    @Singleton
    fun provideAppConfig(): AppConfig {
        return AppConfigImpl()
    }

    @Provides
    @Singleton
    fun provideFilterRecord(): FilterRecord {
        return FilterRecordImpl()
    }
}