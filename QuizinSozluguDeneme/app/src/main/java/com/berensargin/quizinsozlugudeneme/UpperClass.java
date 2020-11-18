package com.berensargin.quizinsozlugudeneme;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UpperClass extends AppCompatActivity {

    public static int newKelimeID;
    public static String newIngilizce, newTurkce, newCategory;
    public static ArrayList<Yanlislar> yanlislarArrayList = new ArrayList<>();

    public static void yanlisinDogrusunuGoster(String turkce, String ingilizce) {
        //
        Yanlislar yanlislar = new Yanlislar(turkce, ingilizce);
        yanlislarArrayList.add(yanlislar);
        //
    }


}
