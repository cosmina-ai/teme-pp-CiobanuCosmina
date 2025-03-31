package com.notesapp

class NoteManager(private val storage: Storage) {
    fun createNote(title: String, content: String, author: User): Note {
        val note = Note(title, content, author = author)
        storage.save(note)
        return note
    }

    fun loadNote(title: String): Note? {
        return storage.load(title)
    }

    fun deleteNote(title: String) {
        storage.delete(title)
    }

    fun listNotes(): List<String> {
        return storage.listAll()
    }
}