package com.notesapp
import java.time.LocalDate

data class Note(
    val title: String,
    val content: String,
    val date: LocalDate = LocalDate.now(),
    val time: String = "",  // sau folosește un format "HH:mm"
    val author: User
)