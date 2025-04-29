import java.io.File
import java.text.SimpleDateFormat
import java.util.*

fun historylogparser(filePath: String, maxEntries: Int = 50) : List<HistoryLogRecord> {
    val lines = File(filePath).readLines()
    val records = mutableListOf<HistoryLogRecord>()

    var currentStartDate: String? = null
    var currentCommand: String? = null

    for(line in lines){
        when{
            line.startsWith("Start-Date:") -> {
                currentStartDate = line.removePrefix("Start-Date:").trim()
            }
            line.startsWith("Commandline:") -> {
                currentCommand = line.removePrefix("Commandline:").removePrefix("Commandline:").trim()
            }
            line.startsWith("End-Date:") -> {
                if(currentStartDate !=  null && currentCommand != null){
                    records.add(HistoryLogRecord.fromStrings(currentStartDate, currentCommand))
                    currentStartDate = null
                    currentCommand = null
                }
            }
        }
    }

    return records.takeLast(maxEntries)
}

fun main() {
    val filePath = System.getProperty("user.home") + "/history.log"
    val records = historylogparser(filePath)

    val map = mutableMapOf<Long, HistoryLogRecord>()

    records.forEach { record ->
        map[record.timestamp] = record
    }

    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    println("Ultimele ${map.size} înregistrări (din map):")
    map.toSortedMap().forEach { (timestamp, record) ->
        val formattedDate = dateFormat.format(Date(timestamp))
        println("[$formattedDate] Command: ${record.commandLine}")
    }
}
