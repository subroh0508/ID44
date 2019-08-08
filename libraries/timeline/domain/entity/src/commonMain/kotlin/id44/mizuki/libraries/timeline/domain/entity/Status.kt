package id44.mizuki.libraries.timeline.domain.entity

import com.soywiz.klock.DateTimeTz

class Status(
    val id: String,
    val content: String,
    val createdAt: DateTimeTz,
    val favouriteCount: Int,
    val reblogCount: Int,
    val tooter: Tooter
)
