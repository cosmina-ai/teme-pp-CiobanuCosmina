import okhttp3.OkHttpClient
import okhttp3.Request

class WebCrawler(private val parser: ResponseParser) {
    private val client = OkHttpClient()

    fun fetchAndParse(url: String): String {
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute()
        val responseBody = response.body?.string() ?: "No response"

        return parser.parse(responseBody)
    }
}
