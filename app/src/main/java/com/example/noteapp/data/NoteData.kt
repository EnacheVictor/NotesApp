package com.example.noteapp.data

import com.example.noteapp.model.Note

class NotesDataSource(){
    fun loadNotes(): List <Note> {
        return listOf(
            Note(title = "Meeting", description = "Team sync at 10 AM"),
        Note(title = "Shopping", description = "Buy milk and eggs"),
        Note(title = "Workout", description = "Gym session at 6 PM"),
        Note(title = "Read", description = "Read Clean Code chapter 3"),
        Note(title = "Dentist", description = "Appointment at 3 PM"),
        Note(title = "Birthday", description = "Call John for birthday"),
        Note(title = "Groceries", description = "List: apples, bread, coffee"),
        Note(title = "Idea", description = "Build a note taking app"),
        Note(title = "Chores", description = "Vacuum and do laundry")
        )
    }
}