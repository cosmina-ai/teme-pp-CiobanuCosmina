import java.text.SimpleDateFormat
import java.util.*

fun main() {
    val filePath = "/home/cosmina/history.log"
    val records = historylogparser(filePath)

    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    println("Ultimele ${records.size} inregistrari: ")
    records.forEach{
        val formattedDate = dateFormat.format(Date(it.timestamp))
        println("[$formattedDate] Command: ${it.commandLine}")
    }
}