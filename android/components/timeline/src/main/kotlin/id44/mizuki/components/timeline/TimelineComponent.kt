package id44.mizuki.components.timeline

import android.app.Application
import dagger.Component
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.components.core.CoreComponent
import id44.mizuki.libraries.timeline.domain.subscribe.TimelineSubscribeUseCase
import id44.mizuki.libraries.timeline.domain.unsubscribe.TimelineUnsubscribeUseCase

@ModuleScope
@Component(
    modules = [TimelineModule::class],
    dependencies = [CoreComponent::class]
)
interface TimelineComponent {
    @Component.Builder
    interface Builder {
        fun build(): TimelineComponent

        fun coreComponent(coreComponent: CoreComponent): Builder
    }

    fun provideApp(): Application
    fun provideTimelineSubscribeUseCase(): TimelineSubscribeUseCase
    fun provideTimelineUnsubscribeUseCase(): TimelineUnsubscribeUseCase
}
