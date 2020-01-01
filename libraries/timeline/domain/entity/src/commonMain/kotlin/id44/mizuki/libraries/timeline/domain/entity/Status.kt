package id44.mizuki.libraries.timeline.domain.entity

import com.soywiz.klock.DateTimeTz
import id44.mizuki.libraries.shared.serializers.DateTimeTzSerializer
import kotlinx.serialization.Serializable
import kotlin.js.JsName

@Serializable
@JsName("Status")
class Status(
    val id: String,
    val content: String,
    @Serializable(with = DateTimeTzSerializer::class)
    val createdAt: DateTimeTz,
    val favouriteCount: Int,
    val reblogCount: Int,
    val tooter: Tooter
)
