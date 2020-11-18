package com.berensargin.quizinsozlugudeneme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends UpperClass {

    private Button buttonBasla;
    private Button buttonSozluk;
    public static String kategoriAdi;
    private String cat;
    private Spinner categorySelectSpinner;
    //Test amaçlı
    private TextView buttonKelimeOner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonBasla = findViewById(R.id.buttonBasla);
        buttonSozluk = findViewById(R.id.buttonSozluk);
        categorySelectSpinner = findViewById(R.id.categorySelectSpinner);
        //
        buttonKelimeOner = findViewById(R.id.buttonKelimeOner);
        //buttonKelimeEkle.setVisibility(View.INVISIBLE);


        //Spinner kodu
        String[] kategoriler = {"Kategori seçiniz","Hepsi","Bilişim","Elektrik","İnşaat",
                "İstatistik","Mekanik","Makine","Matematik"};
        ArrayAdapter<String> kategoriAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, kategoriler);
        categorySelectSpinner.setAdapter(kategoriAdapter);

        categorySelectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0: cat = "none";                   break;
                    case 1: cat = "elektrik' OR 'matematik' OR 'bilişim' OR 'mekanik' OR 'makine' " +
                            "OR 'istatistik' OR 'inşaat";   break;
                    case 2: cat = "bilişim";                break;
                    case 3: cat = "elektrik";               break;
                    case 4: cat = "inşaat";                 break;
                    case 5: cat = "istatistik";             break;
                    case 6: cat = "mekanik";                break;
                    case 7: cat = "makine";                 break;
                    case 8: cat = "matematik";              break; } }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //

        veritabaniKopyala();

buttonKelimeOner.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, SuggestWordActivity.class);
        startActivity(intent);
    }
});

        buttonBasla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cat.equals("none")){
                    Toast.makeText(getApplicationContext(),
                            "Uygulamaya devam edebilmek için bir kategori seçmeniz gerekmektedir.",
                            Toast.LENGTH_LONG).show();
                }else if(cat.equals("elektrik")){
                    Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                    kategoriAdi = "elektrik";startActivity(intent);
                }else if(cat.equals("bilişim")){
                    Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                    kategoriAdi = "bilişim";startActivity(intent);
                }else if(cat.equals("matematik")){
                    Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                    kategoriAdi = "matematik";startActivity(intent);
                }else if(cat.equals("makine")){
                    Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                    kategoriAdi = "makine";startActivity(intent);
                }else if(cat.equals("mekanik")){
                    Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                    kategoriAdi = "mekanik";startActivity(intent);//finish();
                }else if(cat.equals("inşaat")){
                    Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                    kategoriAdi = "inşaat";startActivity(intent);//finish();
                }else if(cat.equals("istatistik")){
                    Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                    kategoriAdi = "istatistik";startActivity(intent);//finish();
                }else if(cat.equals("elektrik' OR 'matematik' OR 'bilişim' OR 'mekanik' OR 'makine'" +
                        " OR 'istatistik' OR 'inşaat")){
                    Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                    kategoriAdi = "elektrik' OR 'matematik' OR 'bilişim' OR 'mekanik' OR 'makine' OR" +
                            " 'istatistik' OR 'inşaat";
                    startActivity(intent);//finish();
                }else{ Toast.makeText(getApplicationContext(), "Lütfen geçerli bir kategori" +
                            " giriniz: Elektrik, Matematik, Bilişim", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonSozluk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DictionaryActivity.class);
                startActivity(intent);
            }
        });
    }

    public void veritabaniKopyala(){
        DatabaseCopyHelper copyHelper = new DatabaseCopyHelper(this);

        try {
            copyHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        copyHelper.openDataBase();
    }

    public void onBackPressed(){

        moveTaskToBack(true);  }

}