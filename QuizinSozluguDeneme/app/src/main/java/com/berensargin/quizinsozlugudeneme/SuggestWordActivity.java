package com.berensargin.quizinsozlugudeneme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SuggestWordActivity extends AppCompatActivity {

    private EditText oneriIng, oneriTr, oneriCat, oneriEmail, oneriIsim;
    private Button buttonOneriGonder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_word);

        oneriIng = findViewById(R.id.oneriIng);
        oneriTr = findViewById(R.id.oneriTr);
        oneriCat = findViewById(R.id.oneriCat);
        oneriEmail = findViewById(R.id.oneriEmail);
        oneriIsim = findViewById(R.id.oneriIsim);
        buttonOneriGonder = findViewById(R.id.buttonOneriGonder);

        oneriEmail.setVisibility(View.INVISIBLE);

        buttonOneriGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(oneriIsim.getText().toString()!="" && oneriCat.getText().toString()!=""
                        && oneriTr.getText().toString()!="" && oneriIng.getText().toString()!=""
                        && oneriEmail.getText().toString()!="") {
                    String to = "terminologyquizapp@gmail.com";
                    String subject = "Yeni terim önerisi!";
                    String body = oneriIsim.getText().toString() + ", size " +
                            oneriCat.getText().toString() + " kategorisinden " +
                            oneriIng.getText().toString() + "(" + oneriTr.getText().toString()
                            + ") kelimesini önerdi.";
                    //

                    //
                    String mailTo = "mailto:" + to +
                            "?&subject=" + Uri.encode(subject) +
                            "&body=" + Uri.encode(body);
                    Intent emailIntent = new Intent(Intent.ACTION_VIEW);
                    emailIntent.setData(Uri.parse(mailTo));
                    startActivity(emailIntent);
                }else{
                    Toast.makeText(getApplicationContext(), "Lütfen alanları doldurunuz.", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
