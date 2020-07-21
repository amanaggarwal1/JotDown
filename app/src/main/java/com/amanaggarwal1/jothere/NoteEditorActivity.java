package com.amanaggarwal1.jothere;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.ArrayList;

public class NoteEditorActivity extends AppCompatActivity {

    SharedPreferences preferences;

    private EditText titleET, contentET;
    private int noteId;

    public void saveArrayList(ArrayList<String> list, String key){
        SharedPreferences prefs = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    private void updateUI(){
        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId", -1);

        if(noteId == -1) {
            MainActivity.createNewNote();
            noteId = MainActivity.notesTitle.size() - 1;
        }

        if (MainActivity.notesTitle.get(noteId).equals("Start here..."))
            titleET.setText("");
        else
            titleET.setText(MainActivity.notesTitle.get(noteId));

        contentET.setText(MainActivity.notesContents.get(noteId));

    }

    private void updateNote(){
        preferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);

        titleET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity.notesTitle.set(noteId, charSequence.toString());
                MainActivity.arrayAdapter.notifyDataSetChanged();
                saveArrayList((ArrayList<String>) MainActivity.notesTitle, "Titles");
                saveArrayList((ArrayList<String>) MainActivity.notesContents, "Contents");
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
                saveArrayList((ArrayList<String>) MainActivity.notesTitle, "Titles");
                saveArrayList((ArrayList<String>) MainActivity.notesContents, "Contents");
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