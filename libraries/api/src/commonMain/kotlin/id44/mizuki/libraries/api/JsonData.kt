package id44.mizuki.libraries.api

interface JsonData {
    val raw: RawJson

    operator fun get(key: String) = raw[key]
}
