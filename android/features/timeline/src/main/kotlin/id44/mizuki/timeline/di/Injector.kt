package id44.mizuki.timeline.di

import id44.mizuki.components.timeline.buildTimelineComponent
import id44.mizuki.timeline.presentation.ui.TimelineActivity

fun TimelineActivity.inject() {
    timelineActivityComponent = DaggerTimelineActivityComponent.builder()
        .timelineComponent(buildTimelineComponent())
        //.timelineActivity(this)
        .build()

    timelineActivityComponent.inject(this)
}
