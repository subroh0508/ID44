package id44.mizuki.timeline.di

import dagger.BindsInstance
import dagger.Component
import id44.mizuki.base.scope.ActivityScope
import id44.mizuki.components.timeline.TimelineComponent
import id44.mizuki.timeline.presentation.ui.TimelineActivity

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
        @BindsInstance fun timelineActivity(activity: TimelineActivity): Builder
    }

    fun inject(activity: TimelineActivity)
}
