package com.berensargin.quizinsozlugudeneme;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static com.berensargin.quizinsozlugudeneme.MainActivity.kategoriAdi;

public class KelimelerDao {

    public ArrayList<Kelimeler> rasgele10getir(Veritabani vt){
        ArrayList<Kelimeler> kelimelerArrayList = new ArrayList<>();
        SQLiteDatabase db = vt.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM kelimeler WHERE category='"+kategoriAdi+"' " +
                "ORDER BY RANDOM()  LIMIT 10",null);
        while (c.moveToNext()){
            Kelimeler b = new Kelimeler(c.getInt(c.getColumnIndex("kelime_id"))
                    , c.getString(c.getColumnIndex("ingilizce"))
                    , c.getString(c.getColumnIndex("turkce"))
            ,c.getString(c.getColumnIndex("category")));

            kelimelerArrayList.add(b); }
        return kelimelerArrayList; }

    public ArrayList<Kelimeler> rasgele3YanlisSecenekGetir(Veritabani vt,int kelime_id){
        ArrayList<Kelimeler> kelimelerArrayList = new ArrayList<>();
        SQLiteDatabase db = vt.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM kelimeler WHERE kelime_id != "+kelime_id+" " +
                "AND category='"+kategoriAdi+"' ORDER BY RANDOM()  LIMIT 3",null);
        while (c.moveToNext()){
            Kelimeler b = new Kelimeler(c.getInt(c.getColumnIndex("kelime_id"))
                    ,c.getString(c.getColumnIndex("ingilizce"))
                    ,c.getString(c.getColumnIndex("turkce"))
            ,c.getString(c.getColumnIndex("category")));

            kelimelerArrayList.add(b); }
        return kelimelerArrayList;
    }
    //
    //
    //
    //
    //Bu kısım tamamen test amaçlıdır.
  /*  public ArrayList<Kelimeler> eklenenKelimeler(Veritabani vt2, int newKelimeID, String newIngilizce, String newTurkce, String newCategory) {
        ArrayList<Kelimeler> kelimelerArrayList3 = new ArrayList<>();

        SQLiteDatabase db3 = vt2.getWritableDatabase();
        db3.rawQuery("INSERT INTO kelimeler(kelime_id, ingilizce, turkce, category) VALUES (" + newKelimeID + "," + newIngilizce + "," + newTurkce + ","+newCategory+")", null);

    return kelimelerArrayList3;
    }*/
    //
    //
    //
    //
    //

    public ArrayList<Kelimeler> tumKelimeler(Veritabani vt){
        ArrayList<Kelimeler> kelimelerArrayList2 = new ArrayList<>();

        SQLiteDatabase db2 = vt.getWritableDatabase();
        Cursor cur = db2.rawQuery("SELECT * FROM kelimeler ORDER BY ingilizce COLLATE NOCASE ASC", null);

        while(cur.moveToNext()){
            Kelimeler w = new Kelimeler(cur.getInt(cur.getColumnIndex("kelime_id"))
            , cur.getString(cur.getColumnIndex("ingilizce"))
                    ,cur.getString(cur.getColumnIndex("turkce"))
                    ,cur.getString(cur.getColumnIndex("category")));
            kelimelerArrayList2.add(w);
        }
        return kelimelerArrayList2;
    }

    public ArrayList<Kelimeler> kelimeAra(Veritabani vt, String arananKelime){
        ArrayList<Kelimeler> kelimelerArrayList2 = new ArrayList<>();

        SQLiteDatabase db2 = vt.getWritableDatabase();
        Cursor cur = db2.rawQuery("SELECT * FROM kelimeler WHERE ingilizce like '%"
                +arananKelime+"%' ORDER BY ingilizce COLLATE NOCASE ASC", null);
        while(cur.moveToNext()){
            Kelimeler w = new Kelimeler(cur.getInt(cur.getColumnIndex("kelime_id"))
                    , cur.getString(cur.getColumnIndex("ingilizce"))
                    ,cur.getString(cur.getColumnIndex("turkce"))
                    ,cur.getString(cur.getColumnIndex("category")));
            kelimelerArrayList2.add(w); }
        return kelimelerArrayList2; }

}