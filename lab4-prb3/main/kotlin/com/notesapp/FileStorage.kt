package com.notesapp

import java.io.File
import java.time.LocalDate

class FileStorage : Storage {
    private val notesDir = File("notes").apply { mkdir() }

    override fun save(note: Note) {
        File(notesDir, "${note.title}.txt").writeText(
            """
        Author: ${note.author.username}
        Date: ${note.date}
        Time: ${note.time}  // Adaugă timpul
        Content:
        ${note.content}
        """.trimIndent()
        )
    }

    override fun load(title: String): Note? {
        return File(notesDir, "$title.txt").takeIf { it.exists() }?.readText()?.let { fileContent ->
            val lines = fileContent.lines()
            if (lines.size >= 4) {
                Note(
                    title = title,
                    content = lines.drop(4).joinToString("\n"),
                    date = LocalDate.parse(lines[1].removePrefix("Date: ").trim()),
                    time = lines[2].removePrefix("Time: ").trim(), // Păstrează ca String
                    author = User(lines[0].removePrefix("Author: ").trim(), "")
                )
            } else {
                null
            }
        }
    }

    override fun delete(title: String) {
        File(notesDir, "$title.txt").delete()
    }

    override fun listAll(): List<String> {
        return notesDir.listFiles()?.map { it.nameWithoutExtension } ?: emptyList()
    }
}