package id44.mizuki.api.streaming.json

@Suppress("EnumEntryName")
enum class EventType(val realValue: String) {
    update("update"),
    notification("notification"),
    delete("delete"),
    filtersChanged("filters_changed");

    companion object {
        fun realValueOf(realValue: String) = values().find { it.realValue == realValue } ?: throw IllegalArgumentException("$realValue is not found")
    }
}
