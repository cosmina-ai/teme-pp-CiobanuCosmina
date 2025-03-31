class YamlParser : ResponseParser {
    override fun parse(response: String): String {
        return "Parsed YAML: $response"
    }
}