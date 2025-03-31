class XmlParser : ResponseParser {
    override fun parse(response: String): String {
        return "Parsed XML: $response"
    }
}