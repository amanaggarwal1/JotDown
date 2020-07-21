package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

public class NoteEditorActivity extends AppCompatActivity {

    private EditText titleET, contentET;
    private int noteId;

    private void updateUI(){
        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId", -1);

        if(noteId != -1) {
            if (MainActivity.notesTitle.get(noteId).equals("Start here..."))
                titleET.setText("");
            else
                titleET.setText(MainActivity.notesTitle.get(noteId));

            contentET.setText(MainActivity.notesContents.get(noteId));
        }
    }

    private void updateNote(){
        titleET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity.notesTitle.set(noteId, charSequence.toString());
                MainActivity.arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        contentET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity.notesContents.set(noteId, charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        titleET = findViewById(R.id.editorTitleET);
        contentET = findViewById(R.id.editorContentET);

        updateUI();
        updateNote();
        
    }
}