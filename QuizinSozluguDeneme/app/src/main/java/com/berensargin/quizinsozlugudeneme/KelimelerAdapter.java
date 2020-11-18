package com.berensargin.quizinsozlugudeneme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class KelimelerAdapter extends RecyclerView.Adapter<KelimelerAdapter.CardTasarimTutucu>{
    private Context mContext;
    private List<Yanlislar> yanlislarListem;

    public KelimelerAdapter(Context mContext, List<Yanlislar> yanlislarListem) {
        this.mContext = mContext;
        this.yanlislarListem = yanlislarListem;
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim, parent,
                false);

        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {
        Yanlislar yanlis = yanlislarListem.get(position);

        holder.textViewIngilizce.setText(yanlis.getIngilizce());
        holder.textViewTurkce.setText(yanlis.getTurkce());
    }

    @Override
    public int getItemCount() {
        return yanlislarListem.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder{
        private TextView textViewIngilizce;
        private TextView textViewTurkce;
        private CardView kelime_card;

        public CardTasarimTutucu(@NonNull View itemView) {
            super(itemView);
            textViewIngilizce = itemView.findViewById(R.id.textViewIngilizce);
            textViewTurkce = itemView.findViewById(R.id.textViewTurkce);
            kelime_card = itemView.findViewById(R.id.kelime_card);
        }
    }


}
