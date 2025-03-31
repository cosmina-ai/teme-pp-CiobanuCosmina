class JsonParser : ResponseParser {
    override fun parse(response: String): String {
        return "Parsed JSON: $response"
    }
}