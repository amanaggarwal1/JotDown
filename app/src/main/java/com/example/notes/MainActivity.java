package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView notesLV;
    static List<String> notesTitle = new ArrayList<>();
    static List<String> notesContents = new ArrayList<>();
    static ArrayAdapter arrayAdapter;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case R.id.addNote :
                Toast.makeText(this, "Your new note is ready!", Toast.LENGTH_SHORT).show();
                createNewNote();
                return true;


            default:
                return false;
        }

    }

    static void createNewNote(){
        notesTitle.add("Start here...");
        notesContents.add("");
        arrayAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notesLV = findViewById(R.id.notesLV);

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notesTitle);
        notesLV.setAdapter(arrayAdapter);

        if(notesTitle.size() == 0) {
           createNewNote();
        }


        notesLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this , NoteEditorActivity.class);
                intent.putExtra("noteId", i);
                startActivity(intent);
            }
        });

        notesLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDeleteDialog(i);
                return true;
            }
        });
    }

    private void showDeleteDialog(final int itemToBeDeleted){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_menu_delete)
                .setTitle("Delete Note")
                .setMessage("Do you really want to delete this note?\nYou will no longer able to access this note.")
                .setPositiveButton("Yes, Delete it", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        notesTitle.remove(itemToBeDeleted);
                        notesContents.remove(itemToBeDeleted);
                        arrayAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("No, Keep it", null)
                .show();
    }
}