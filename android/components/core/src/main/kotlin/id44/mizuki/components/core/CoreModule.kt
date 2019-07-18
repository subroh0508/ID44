package id44.mizuki.components.core

import android.app.Application
import android.os.Build
import dagger.Module
import dagger.Provides
import io.ktor.client.features.UserAgent
import io.ktor.client.features.json.JsonSerializer
import javax.inject.Singleton

@Module
class CoreModule(
    private val application: Application,
    private val applicationId: String,
    private val versionName: String,
    private val json: JsonSerializer
) {
    @Provides
    @Singleton
    fun provideApp(): Application = application

    @Provides
    @Singleton
    fun provideUserAgent(): UserAgent = UserAgent(
        "$applicationId/$versionName (Android ${Build.VERSION.RELEASE}; ${Build.PRODUCT})"
    )

    @Provides
    @Singleton
    fun provideJsonSerializer(): JsonSerializer = json
}
