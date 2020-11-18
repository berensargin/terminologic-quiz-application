package com.berensargin.quizinsozlugudeneme;

import java.io.Serializable;

public class Yanlislar implements Serializable {
    public String turkce;
    public String ingilizce;
    //public int soruSayisi;

    //Duruma göre  soru sayısı - ingilizce yapıp cardview'da üstüne tıklandığında kullanıcıya
    // yanlışların ing ve türkçesini gösteririz.

    public Yanlislar() {
    }

    public Yanlislar(String turkce, String ingilizce) {
        this.turkce = turkce;
        this.ingilizce = ingilizce;
    }

    public String getTurkce() {
        return turkce;
    }

    public void setTurkce(String turkce) {
        this.turkce = turkce;
    }

    public String getIngilizce() {
        return ingilizce;
    }

    public void setIngilizce(String ingilizce) {
        this.ingilizce = ingilizce;
    }
}
