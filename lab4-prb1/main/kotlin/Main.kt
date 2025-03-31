fun main() {
    val jsonCrawler = WebCrawler(JsonParser())
    val xmlCrawler = WebCrawler(XmlParser())
    val yamlCrawler = WebCrawler(YamlParser())

    val url = "https://jsonplaceholder.typicode.com/todos/1" // Un API de test care returnează JSON

    println(jsonCrawler.fetchAndParse(url))
    println(xmlCrawler.fetchAndParse(url))
    println(yamlCrawler.fetchAndParse(url))
}