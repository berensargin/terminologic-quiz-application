package com.berensargin.quizinsozlugudeneme;

import java.io.Serializable;

public class Kelimeler implements Serializable {
    private int kelime_id;
    private String ingilizce;
    private String turkce;
    private String category;

    public Kelimeler() {
    }

    public Kelimeler(int kelime_id, String ingilizce, String turkce, String category) {
        this.kelime_id = kelime_id;
        this.ingilizce = ingilizce;
        this.turkce = turkce;
        this.category = category;
    }

    public int getKelime_id() {
        return kelime_id;
    }

    public void setKelime_id(int kelime_id) {
        this.kelime_id = kelime_id;
    }

    public String getIngilizce() {
        return ingilizce;
    }

    public void setIngilizce(String ingilizce) {
        this.ingilizce = ingilizce;
    }

    public String getTurkce() {
        return turkce;
    }

    public void setTurkce(String turkce) {
        this.turkce = turkce;
    }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }
}