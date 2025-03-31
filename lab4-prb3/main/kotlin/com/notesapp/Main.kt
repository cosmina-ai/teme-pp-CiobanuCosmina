package com.notesapp

import java.time.LocalDate
import java.util.*

fun main() {
    val storage = FileStorage()
    val noteManager = NoteManager(storage)
    val currentUser = User("admin", "password")
    val scanner = Scanner(System.`in`)

    while (true) {
        println("\nNote App Menu:")
        println("1. List all notes")
        println("2. Create new note")
        println("3. View note")
        println("4. Delete note")
        println("5. Exit")
        print("Choose an option: ")

        when (scanner.nextLine()) {
            "1" -> {
                println("\nAll Notes:")
                noteManager.listNotes().forEachIndexed { i, title ->
                    println("${i + 1}. $title")
                }
            }
            "2" -> {
                print("Enter note title: ")
                val title = scanner.nextLine()
                print("Enter note content: ")
                val content = scanner.nextLine()
                noteManager.createNote(title, content, currentUser)
                println("Note created successfully!")
            }
            "3" -> {
                print("Enter note title to view: ")
                val title = scanner.nextLine()
                val note = noteManager.loadNote(title)
                if (note != null) {
                    println("\n--- ${note.title} ---")
                    println("Author: ${note.author.username}")
                    println("Date: ${note.date}")
                    println("\n${note.content}")
                } else {
                    println("Note not found!")
                }
            }
            "4" -> {
                print("Enter note title to delete: ")
                val title = scanner.nextLine()
                noteManager.deleteNote(title)
                println("Note deleted successfully!")
            }
            "5" -> {
                println("Exiting...")
                return
            }
            else -> println("Invalid option!")
        }
    }
}