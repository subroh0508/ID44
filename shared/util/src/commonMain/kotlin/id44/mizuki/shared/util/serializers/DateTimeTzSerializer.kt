package id44.mizuki.shared.util.serializers

import com.soywiz.klock.DateTimeTz
import id44.mizuki.shared.util.extensions.toDateTimeTz
import id44.mizuki.shared.util.extensions.toDateTimeTzFormat
import kotlinx.serialization.*

@Serializer(forClass = DateTimeTz::class)
class DateTimeTzSerializer : KSerializer<DateTimeTz> {
    override val descriptor = SerialDescriptor("DateTimeTz")

    override fun deserialize(decoder: Decoder): DateTimeTz =
        decoder.decodeString().toDateTimeTz()

    override fun serialize(encoder: Encoder, obj: DateTimeTz) =
        encoder.encodeString(obj.toDateTimeTzFormat())
}
