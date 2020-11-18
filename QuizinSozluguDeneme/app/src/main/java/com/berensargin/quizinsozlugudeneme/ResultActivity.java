package com.berensargin.quizinsozlugudeneme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ResultActivity extends UpperClass {

    private int dogruSayac, yanlisSayac, yuzdeBasari;
    private RecyclerView rv;
    private KelimelerAdapter adapter;
    Button geri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        dogruSayac = getIntent().getIntExtra("ileriDogruSayac",0);
        yanlisSayac = getIntent().getIntExtra("ileriYanlisSayac", 0);
        yuzdeBasari = getIntent().getIntExtra("ileriYuzdeBasari", 0);


        rv = findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        geri = findViewById(R.id.geri);

        adapter = new KelimelerAdapter(this, yanlislarArrayList);
        rv.setAdapter(adapter);

        geri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, Result1Activity.class);
                intent.putExtra("dogruSayac", dogruSayac);
                intent.putExtra("yanlisSayac", yanlisSayac);
                intent.putExtra("yuzdeBasari", yuzdeBasari);
                startActivity(intent);
                finish();
            }
        });
    }
}
