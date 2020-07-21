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
            case R.id.settings :
                Toast.makeText(this, "settings selected", Toast.LENGTH_SHORT).show();
                showDialog();
                return true;

            case R.id.help :
                Toast.makeText(this, "help", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return false;
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notesLV = findViewById(R.id.notesLV);

        if(notesTitle.size() == 0) {
            notesTitle.add("Start here...");
            notesContents.add("");
        }

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notesTitle);
        notesLV.setAdapter(arrayAdapter);

        notesLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this , NoteEditorActivity.class);
                intent.putExtra("noteId", i);
                startActivity(intent);
            }
        });

    }

    private void showDialog(){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_btn_speak_now)
                .setTitle("Select a language")
                .setMessage("Choose a language in which you want to see the app contents")
                .setPositiveButton("English", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //setLanguage("English");
                    }
                })
                .setNegativeButton("Hindi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //setLanguage("Hindi");
                    }
                })
                .show();
    }
}