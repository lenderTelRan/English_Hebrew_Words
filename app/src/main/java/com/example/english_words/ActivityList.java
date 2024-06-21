package com.example.english_words;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ActivityList extends AppCompatActivity {
    private ListView lv_wordsList;
    ArrayList<String> words;
    ArrayList<String> transcription;
    ArrayList<String> translate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        lv_wordsList = findViewById(R.id.lv_text);

        Intent intent = getIntent();

        words = getIntent().getStringArrayListExtra("words");
        transcription = getIntent().getStringArrayListExtra("transcription");
        translate = getIntent().getStringArrayListExtra("translate");

        if (words != null && transcription != null && translate != null) {
            AdapterList adapterList = new AdapterList(this, words, transcription, translate);
            lv_wordsList.setAdapter(adapterList);
        }
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
