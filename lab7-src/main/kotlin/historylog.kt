import java.text.SimpleDateFormat

data class HistoryLogRecord(
    val timestamp: Long,
    val commandLine: String
): Comparable<HistoryLogRecord> {
    override fun compareTo(other: HistoryLogRecord): Int {
        return this.timestamp.compareTo(other.timestamp)
    }

    companion object{

        fun fromStrings(startDate: String, commandLine: String): HistoryLogRecord {
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val date = formatter.parse(startDate)
            return HistoryLogRecord(date.time, commandLine)
        }
    }
}