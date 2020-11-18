package com.berensargin.quizinsozlugudeneme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SozlukDetayActivity extends UpperClass {

    private TextView textViewDetayIng, textViewDetayTur, textViewDetayCategory;
    private Kelimeler word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sozluk_detay);

        textViewDetayIng = findViewById(R.id.textViewDetayIng);
        textViewDetayTur = findViewById(R.id.textViewDetayTur);
        textViewDetayCategory = findViewById(R.id.textViewDetayCategory);

        word = (Kelimeler) getIntent().getSerializableExtra("nesne");

        textViewDetayIng.setText(word.getIngilizce());
        textViewDetayTur.setText(word.getTurkce());
        textViewDetayCategory.setText(word.getCategory());

    }
}
