package com.berensargin.quizinsozlugudeneme;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class KelimelerAdapter2 extends RecyclerView.Adapter<KelimelerAdapter2.CardTasarimTutucu2>{
    private Context mContext2;
    private List<Kelimeler> kelimelerListem;

    public KelimelerAdapter2(Context mContext, List<Kelimeler> kelimelerListem) {
        this.mContext2 = mContext;
        this.kelimelerListem = kelimelerListem;
    }

    @NonNull
    @Override
    public CardTasarimTutucu2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim2, parent,
                false);

        return new CardTasarimTutucu2(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu2 holder, int position) {
        final Kelimeler kelime = kelimelerListem.get(position);
        holder.textViewIngilizce2.setText(kelime.getIngilizce());
        holder.textViewTurkce2.setText(kelime.getTurkce());
        holder.kelime_card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext2, SozlukDetayActivity.class);
                intent.putExtra("nesne", kelime);
                mContext2.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return kelimelerListem.size();
    }

    public class CardTasarimTutucu2 extends RecyclerView.ViewHolder{
        private TextView textViewIngilizce2;
        private TextView textViewTurkce2;
        private CardView kelime_card2;

        public CardTasarimTutucu2(@NonNull View itemView) {
            super(itemView);
            textViewIngilizce2 = itemView.findViewById(R.id.textViewIngilizce2);
            textViewTurkce2 = itemView.findViewById(R.id.textViewTurkce2);
            kelime_card2 = itemView.findViewById(R.id.kelime_card2);
        }
    }

}
