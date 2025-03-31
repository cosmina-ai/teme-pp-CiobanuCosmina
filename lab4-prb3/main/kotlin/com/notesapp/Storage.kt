package com.notesapp

interface Storage {
    fun save(note: Note)
    fun load(title: String): Note?
    fun delete(title: String)
    fun listAll(): List<String>
}