package com.berensargin.quizinsozlugudeneme;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

public class QuizActivity extends UpperClass {


    private CountDownTimer countDownTimer;
    private TextView textViewDogru, textViewYanlis, textViewSoruSayi, textViewSoruSure, question;
    private Button buttonA, buttonB, buttonC, buttonD;
    private ArrayList<Kelimeler> sorular;
    private ArrayList<Kelimeler> yanlisSecenekler;
    private Kelimeler dogruSoru;
    private Veritabani vt;
    //Soru sayaÃ§larÄ±
    private int soruSayac = 0 ;
    private int dogruSayac = 0;
    private int yanlisSayac = 0;
    private int bosSayac = 0;
    private int yuzdeBasari;
    private ArrayList<Kelimeler> secenekler = new ArrayList<>();
    private HashSet<Kelimeler> secenekleriKaristirmaListe = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        textViewSoruSure = findViewById(R.id.textViewSoruSure);
        textViewDogru = findViewById(R.id.textViewDogru);
        textViewYanlis = findViewById(R.id.textViewYanlis);
        textViewSoruSayi = findViewById(R.id.textViewSoruSayi);
        question = findViewById(R.id.question);
        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        buttonD = findViewById(R.id.buttonD);

        vt = new Veritabani(this);

        sorular = new KelimelerDao().rasgele10getir(vt);

        soruYukle();

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownTimer.cancel();
                dogruKontrol(buttonA);
                soruSayacKontrol();
                Log.e("SAYAC",String.valueOf(soruSayac));
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownTimer.cancel();
                dogruKontrol(buttonB);
                soruSayacKontrol();
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownTimer.cancel();
                dogruKontrol(buttonC);
                soruSayacKontrol();
            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownTimer.cancel();
                dogruKontrol(buttonD);
                soruSayacKontrol();
            }
        });
    }

    public void soruYukle(){
        //Sayac kodu başlangıç
        countDownTimer = new CountDownTimer(16000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished<10000){ textViewSoruSure.setText("Süre: 00:0"+millisUntilFinished/1000); }
                else{ textViewSoruSure.setText("Süre: 00:"+millisUntilFinished/1000); }
            }
            @Override
            public void onFinish() {
                textViewSoruSure.setText("Süre: 00:00");
                UpperClass.yanlisinDogrusunuGoster(dogruSoru.getTurkce(), dogruSoru.getIngilizce());
                Log.e("ArrayListElemanTurk", String.valueOf(yanlislarArrayList));
                bosSayac++;
                if(soruSayac+1 != /*15*/10){
                    AlertDialog.Builder builder = new AlertDialog.Builder(QuizActivity.this);
                    builder.setTitle("Soru boş bırakıldı.");
                    builder.setMessage("Devam tuşuna bastığınızda yeni soruya geçilecek.");
                    builder.setPositiveButton("Devam", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            soruSayacKontrol();
                        }
                    });
                    builder.show();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(QuizActivity.this);
                    builder.setTitle("Soru boş bırakıldı.");
                    builder.setMessage("Bu, testin son sorusuydu.Sonuç ekranına yönlendirilmek için Tamam'a basınız.");
                    builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            soruSayacKontrol();
                        }
                    });
                    builder.show();
                }



            }
        }.start();
        //Sayaç kodu bitiş

        textViewSoruSayi.setText((soruSayac+1)+". SORU");
        textViewDogru.setText("Doğru : "+(dogruSayac));
        textViewYanlis.setText("Yanlış : "+(yanlisSayac));

        dogruSoru = sorular.get(soruSayac);

        yanlisSecenekler = new KelimelerDao().rasgele3YanlisSecenekGetir(vt,dogruSoru.getKelime_id());

        question.setText(dogruSoru.getIngilizce());

        //Tüm secenekleri hashset yardımıyla karıştırma
        secenekleriKaristirmaListe.clear();
        secenekleriKaristirmaListe.add(dogruSoru);//Doğru secenek
        secenekleriKaristirmaListe.add(yanlisSecenekler.get(0));
        secenekleriKaristirmaListe.add(yanlisSecenekler.get(1));
        secenekleriKaristirmaListe.add(yanlisSecenekler.get(2));

        //Hashset ile butonlara dinamik şekilde yazı yazdıramadığımızdan arraylist dönüşümü yaptık.
        secenekler.clear();

        for(Kelimeler b: secenekleriKaristirmaListe){
            secenekler.add(b);
        }
        //Secenekleri buttonlara yerleştirdik.
        buttonA.setText("        "+secenekler.get(0).getTurkce());
        buttonB.setText("        "+secenekler.get(1).getTurkce());
        buttonC.setText("        "+secenekler.get(2).getTurkce());
        buttonD.setText("        "+secenekler.get(3).getTurkce());

    }

    public void dogruKontrol(Button button){

        String buttonYazi = button.getText().toString();
        String dogruCevap = dogruSoru.getTurkce();

        if(buttonYazi.equals("        "+dogruCevap)){
            dogruSayac++;
            //Test amaçlı
            Log.e("Verilen cevap : ", dogruSoru.getTurkce());
            Log.e("Kelime kategorisi : ", dogruSoru.getCategory());
            //
        }else{
            yanlisSayac++;
           /* Log.e("YanlisinDogrusuSoru", String.valueOf(soruSayac+1));
            Log.e("YanlisinDogrusuTurkce", dogruSoru.getTurkce());
            Log.e("YanlisinDogrusuIng", dogruSoru.getIngilizce());
*/
            // Test amaçlı yazdık
            Log.e("Cevabın dogrusu : ", dogruSoru.getTurkce());
            Log.e("Kelime kategorisi : ", dogruSoru.getCategory());
            //
            UpperClass.yanlisinDogrusunuGoster(dogruSoru.getTurkce(), dogruSoru.getIngilizce());
            Log.e("ArrayListElemanTurk", String.valueOf(yanlislarArrayList));
        }

        textViewDogru.setText("Doğru : "+(dogruSayac));
        textViewYanlis.setText("Yanlış : "+(yanlisSayac));
    }

    public void soruSayacKontrol(){

        soruSayac++;

        //soru sayÄ±sÄ± 10 olduysa sonuca git
        if(soruSayac != /*15*/10){
            soruYukle();
        }else{
            //yuzdeBasari = (dogruSayac*100)/15;
            yuzdeBasari = (dogruSayac*100)/10;
            Intent i = new Intent(QuizActivity.this,Result1Activity.class);
            i.putExtra("dogruSayac",dogruSayac);
            i.putExtra("yanlisSayac", yanlisSayac);
            i.putExtra("yuzdeBasari", yuzdeBasari);
            i.putExtra("bosSayac", bosSayac);
            startActivity(i);
            finish();
        }
    }

    public void onBackPressed(){

        moveTaskToBack(true);  }


}