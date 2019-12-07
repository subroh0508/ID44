package id44.mizuki.timeline.di

import id44.mizuki.bridges.timeline.di.buildTimelineComponent
import id44.mizuki.timeline.TimelineActivity

fun TimelineActivity.inject() {
    timelineActivityComponent = DaggerTimelineActivityComponent.builder()
        .timelineComponent(buildTimelineComponent())
        .timelineActivity(this)
        .build()

    timelineActivityComponent.inject(this)
}
