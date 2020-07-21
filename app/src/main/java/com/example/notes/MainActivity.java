package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    TextView textView;
    String language;

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

    private void setLanguage(String lang){
        preferences.edit().putString("Language", lang).apply();
        language = lang;
        textView.setText(language);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        preferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        language = preferences.getString("Language", "Default");

        if(language.equals("Default"))
            showDialog();

        textView.setText(language);
    }

    private void showDialog(){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_btn_speak_now)
                .setTitle("Select a language")
                .setMessage("Choose a language in which you want to see the app contents")
                .setPositiveButton("English", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setLanguage("English");
                    }
                })
                .setNegativeButton("Hindi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setLanguage("Hindi");
                    }
                })
                .show();
    }
}