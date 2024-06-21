package com.example.english_words;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView wordEng;
    private TextView wordTranslate;
    private TextView transcription;
    private ImageButton btnNextWord;
    private ImageButton btnList;
    private ImageButton btnLang;
    private final List<String> wordsList = new ArrayList<>();
    private final List<String> transcriptionList = new ArrayList<>();
    private final List<String> translateList = new ArrayList<>();

    private String path = "words_hebrew.csv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wordEng = findViewById(R.id.tv_word_eng);
        wordTranslate = findViewById(R.id.tv_word_translate);
        btnNextWord = findViewById(R.id.btn_next);
        btnList = findViewById(R.id.btn_list);
        btnLang = findViewById(R.id.btn_lang);
        transcription = findViewById(R.id.tv_word_transcription);

        getWordsList();

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextWord();
            }
        };
        btnNextWord.setOnClickListener(listener);
    }

    private void getWordsList() {
        try (CSVReader reader = new CSVReaderBuilder(
                new InputStreamReader(getAssets().open(path))).build()) {
            String[] line;

            while ((line = reader.readNext()) != null) {
                wordsList.add(line[0]);
                transcriptionList.add(line[1]);
                translateList.add(line[2]);
            }

        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    private void showNextWord() {
        int index =  new Random().nextInt(wordsList.size());
        wordEng.setText(wordsList.get(index));
        transcription.setText(transcriptionList.get(index));
        wordTranslate.setText(translateList.get(index));
    }

    public void showAllWords(View view) {
        Intent intent = new Intent(this, ActivityList.class);
        intent.putStringArrayListExtra("words", (ArrayList<String>) wordsList);
        intent.putStringArrayListExtra("transcription", (ArrayList<String>) transcriptionList);
        intent.putStringArrayListExtra("translate", (ArrayList<String>) translateList);
        startActivity(intent);
    }

    public void switchLanguage(View view) {
        if(path.equals("words_hebrew.csv")) {
            path = "words_english.csv";
            clearList();
            getWordsList();
            Toast.makeText(this, "English", Toast.LENGTH_SHORT).show();
        } else {
            path = "words_hebrew.csv";
            clearList();
            getWordsList();
            Toast.makeText(this, "Hebrew", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearList() {
        wordsList.clear();
        transcriptionList.clear();
        translateList.clear();
    }
}