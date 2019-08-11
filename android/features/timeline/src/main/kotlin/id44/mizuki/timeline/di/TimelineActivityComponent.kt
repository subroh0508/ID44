package id44.mizuki.timeline.di

import dagger.Component
import id44.mizuki.base.scope.ActivityScope
import id44.mizuki.components.timeline.TimelineComponent
import id44.mizuki.timeline.TimelineActivity

@ActivityScope
@Component(
    modules = [TimelineActivityModule::class],
    dependencies = [TimelineComponent::class]
)
interface TimelineActivityComponent {
    @Component.Builder
    interface Builder {
        fun build(): TimelineActivityComponent

        fun timelineComponent(timelineComponent: TimelineComponent): Builder
    }

    fun inject(activity: TimelineActivity)
}
