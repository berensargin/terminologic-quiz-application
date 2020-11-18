package com.berensargin.quizinsozlugudeneme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.util.ArrayList;

public class DictionaryActivity extends UpperClass implements SearchView.OnQueryTextListener {

    private Toolbar toolbar;
    private RecyclerView rv1;
    private ArrayList<Kelimeler> kelimeListe;
    private KelimelerAdapter2 adapter2;
    private Veritabani vt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        toolbar = findViewById(R.id.toolbar);
        rv1 = findViewById(R.id.rv1);

        toolbar.setTitle("Terminoloji Quiz Uygulaması");
        setSupportActionBar(toolbar);

        vt2 = new Veritabani(this);

        veriTabaniKopyala2();

        kelimeListe = new KelimelerDao().tumKelimeler(vt2);

        rv1.setHasFixedSize(true);
        rv1.setLayoutManager(new LinearLayoutManager(this));



        adapter2 = new KelimelerAdapter2(this, kelimeListe);
        rv1.setAdapter(adapter2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem item = menu.findItem(R.id.action_ara);

        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(this);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.e("Gönderilen arama", query);
        aramaYap(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.e("Harf girdikçe", newText);
        aramaYap(newText);
        return false;
    }

    public void veriTabaniKopyala2(){
        DatabaseCopyHelper databaseCopyHelper2 = new DatabaseCopyHelper(this);
        try {
            databaseCopyHelper2.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        databaseCopyHelper2.openDataBase();
    }

    public void aramaYap(String arananKelime){
        kelimeListe = new KelimelerDao().kelimeAra(vt2, arananKelime);

        adapter2 = new KelimelerAdapter2(this, kelimeListe);

        rv1.setAdapter(adapter2);
    }
}
