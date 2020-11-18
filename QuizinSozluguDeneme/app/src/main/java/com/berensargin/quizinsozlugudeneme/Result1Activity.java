package com.berensargin.quizinsozlugudeneme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Result1Activity extends UpperClass {


    private TextView textViewSonuc,textViewYuzdeSonuc;
    private Button buttonTekrar, yanlislariGor, anaSayfayaGit;
    private int dogruSayac, yanlisSayac, yuzdeBasari, bosSayac;
    private ArrayList<Yanlislar> yanlislarArrayList1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result1);

        textViewSonuc = findViewById(R.id.textViewSonuc);
        textViewYuzdeSonuc = findViewById(R.id.textViewYuzdeSonuc);
        buttonTekrar = findViewById(R.id.buttonTekrar);
        yanlislariGor = findViewById(R.id.yanlislariGor);
        anaSayfayaGit = findViewById(R.id.anaSayfayaGit);

        dogruSayac = getIntent().getIntExtra("dogruSayac",0);
        yanlisSayac = getIntent().getIntExtra("yanlisSayac", 0);
        yuzdeBasari = getIntent().getIntExtra("yuzdeBasari", 0);
        bosSayac = getIntent().getIntExtra("bosSayac", 0);
        // Bir sonraki activity'den geriye geldiğimizde sonuçları göstermek için yine aynı anahtarları
        // (dogruSayac, yanlisSayac, yuzdeBasari) kullandık.

        textViewSonuc.setText(dogruSayac+" DOĞRU "+yanlisSayac+" YANLIŞ");
        textViewYuzdeSonuc.setText("% "+yuzdeBasari+" Başarı");

        buttonTekrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Result1Activity.this, QuizActivity.class);
                yanlislarArrayList.clear();
                startActivity(intent);
                finish();
            }
        });

        if(bosSayac < 1 && yanlisSayac < 1){
            yanlislariGor.setVisibility(View.INVISIBLE);
        }

        else{
            yanlislariGor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Result1Activity.this, ResultActivity.class);
                    intent.putExtra("ileriDogruSayac", dogruSayac);
                    intent.putExtra("ileriYanlisSayac", yanlisSayac);
                    intent.putExtra("ileriYuzdeBasari", yuzdeBasari);
                    startActivity(intent);
                }
            });
        }

        anaSayfayaGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Result1Activity.this, MainActivity.class);
                yanlislarArrayList.clear();
                startActivity(intent);
            }
        });
    }

    public void onBackPressed(){

        moveTaskToBack(true);  }

}