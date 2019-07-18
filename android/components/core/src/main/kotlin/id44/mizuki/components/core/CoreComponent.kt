package id44.mizuki.components.core

import android.app.Application
import dagger.Component
import io.ktor.client.features.UserAgent
import io.ktor.client.features.json.JsonSerializer
import javax.inject.Singleton

@Singleton
@Component(
    modules = [CoreModule::class]
)
interface CoreComponent {
    @Component.Builder
    interface Builder {
        fun build(): CoreComponent

        fun coreModule(coreModule: CoreModule): Builder
    }

    fun provideApp(): Application
    fun provideUserAgent(): UserAgent
    fun provideJsonSerializer(): JsonSerializer
}
