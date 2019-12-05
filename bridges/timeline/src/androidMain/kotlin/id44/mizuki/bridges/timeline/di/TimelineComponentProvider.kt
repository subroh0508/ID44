package id44.mizuki.bridges.timeline.di

import android.app.Activity
import id44.mizuki.components.core.coreComponent

interface TimelineComponentProvider {
    var timelineComponent: TimelineComponent
}

fun Activity.buildTimelineComponent(): TimelineComponent {
    val timelineComponent = DaggerTimelineComponent.builder()
        .coreComponent(application.coreComponent)
        .build()

    (application as TimelineComponentProvider).timelineComponent = timelineComponent

    return timelineComponent
}
