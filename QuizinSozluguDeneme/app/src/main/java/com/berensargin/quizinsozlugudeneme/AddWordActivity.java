package com.berensargin.quizinsozlugudeneme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class AddWordActivity extends AppCompatActivity {

    private EditText addedIng, addedTurkce, addedCategory;
    private Button buttonKaydet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        addedIng = findViewById(R.id.et_ing);
        addedTurkce = findViewById(R.id.et_tr);
        addedCategory = findViewById(R.id.et_cat);
        buttonKaydet = findViewById(R.id.btn_kaydet);

        buttonKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Kayıt eklemeye yarayan kodlar buraya gelecek

            }
        });

    }

    public void veriTabaniKopyalaVeKelimeEkle(){
        DatabaseCopyHelper databaseCopyHelper3 = new DatabaseCopyHelper(this);
        try {
            databaseCopyHelper3.createDataBase();

        } catch (IOException e) {
            e.printStackTrace();
        }

        databaseCopyHelper3.openDataBase();
    }
}
