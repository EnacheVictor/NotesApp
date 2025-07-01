package com.example.noteapp.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteapp.components.NoteButton
import com.example.noteapp.components.NoteInputText
import com.example.noteapp.data.NotesDataSource
import com.example.noteapp.model.Note
import com.example.noteapp.util.formatDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
) {
    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        TopAppBar(
            title = {
            Text(text = "Note App")
        }, actions = {
            Icon(
                imageVector = Icons.Rounded.Notifications,
                contentDescription = "Icon"
            )
        },
            colors = TopAppBarDefaults.topAppBarColors(Color(0xFFB0CFEA))
        )
        Column(
            modifier = Modifier.fillMaxWidth().background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = title, label = "Title", onTextChange = {
                    if(it.all { char ->
                        char.isLetter() || char.isWhitespace()
                        }) title = it
                })
            NoteInputText(modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = description, label = "Add a note", onTextChange = {
                    if(it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) description = it
                })

            NoteButton(text = "Save", onClick = {
                if(title.isNotEmpty() && description.isNotEmpty()){
                    onAddNote(Note(title = title,
                        description = description))
                    title = ""
                    description = ""
                    Toast.makeText(context, "Note Added Successfully",
                        Toast.LENGTH_SHORT).show()
                }
            })
        }
        HorizontalDivider(
            modifier = Modifier.padding(10.dp),

        )
        LazyColumn {
            items(notes) { note ->
                NoteRow(note =note,
                    onNoteClicked = {
                        onRemoveNote(note)
                    })
            }
        }
    }
}

@Composable
fun NoteRow(modifier: Modifier = Modifier,
            note: Note,
            onNoteClicked: (Note) -> Unit){

    Surface(modifier.padding(4.dp)
        .clip(RoundedCornerShape(topEnd = 32.dp, bottomStart = 22.dp))
        .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        tonalElevation = 6.dp) {

        Column(modifier.clickable{ onNoteClicked(note) }.padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start) {
            Text(text = note.title,
                style = MaterialTheme.typography.titleMedium)
            Text(text = note.description,
                style = MaterialTheme.typography.titleSmall)
            Text(text = formatDate(note.entryDate.time),
                style = MaterialTheme.typography.bodyMedium)
        }

    }

}


@Preview(showBackground = true)
@Composable
fun NotesScreenPreview(){
    NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}