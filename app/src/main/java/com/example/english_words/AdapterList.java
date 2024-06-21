package com.example.english_words;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class AdapterList extends ArrayAdapter<String> {
    private final Context context;
    private final List<String> words;
    private final List<String> transcription;
    private final List<String> translate;

    public AdapterList(Context context, List<String> words, List<String> transcription, List<String> translate) {
        super(context, R.layout.adapter_list, words);
        this.context = context;
        this.words = words;
        this.transcription = transcription;
        this.translate = translate;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.adapter_list, parent, false);

        TextView text1 = convertView.findViewById(R.id.tv_row1);
        TextView text2 = convertView.findViewById(R.id.tv_row2);
        TextView text3 = convertView.findViewById(R.id.tv_row3);

        text1.setText(words.get(position));
        text2.setText(transcription.get(position));
        text3.setText(translate.get(position));

        return convertView;
    }
}
