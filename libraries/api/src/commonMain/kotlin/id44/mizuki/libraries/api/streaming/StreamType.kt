package id44.mizuki.libraries.api.streaming

enum class StreamType(val realValue: String) {
    USER("user"),
    PUBLIC("public"),
    PUBLIC_LOCAL("public:local"),
    HASHTAG("hashtag"),
    HASHTAG_LOCAL("hashtag:local"),
    LIST("list"),
    DIRECT("direct")
}