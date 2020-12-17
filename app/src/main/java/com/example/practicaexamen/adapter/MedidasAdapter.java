package com.example.practicaexamen.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicaexamen.R;
import com.example.practicaexamen.model.Medidas;

import java.util.List;

public class MedidasAdapter
        extends
        RecyclerView.Adapter<MedidasAdapter.MedidasViewHolder>
        implements View.OnClickListener {

    private View.OnClickListener listener;
    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null) {
            listener.onClick(v);
        }
    }

    private List<Medidas> lista;
    public MedidasAdapter(List<Medidas> lista) {
        this.lista = lista;
    }

    public void Actualiza (List<Medidas>lista){
        this.lista = lista;
    }

    @NonNull
    @Override
    public MedidasAdapter.MedidasViewHolder onCreateViewHolder(
        @NonNull ViewGroup parent, int viewType) {
            View vista = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.card_medida,parent,false);
            vista.setOnClickListener(this);
            return new MedidasViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull MedidasAdapter.MedidasViewHolder holder,
                                 int position) {
        holder.tvId.setText(""+lista.get(position).getId());
        holder.tvMes.setText(lista.get(position).getMes());
        holder.tvPeso.setText(""+lista.get(position).getPeso());
        holder.tvMM.setText(""+lista.get(position).getMasaMuscular());
        holder.tvMG.setText(""+lista.get(position).getMasaGrasa());
    }

    @Override
    public int getItemCount() {

        return lista.size();
    }

    public class MedidasViewHolder extends RecyclerView.ViewHolder {
        public TextView tvId;
        public TextView tvMes;
        public TextView tvPeso;
        public TextView tvMM;
        public TextView tvMG;

        public MedidasViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId=itemView.findViewById(R.id.tvId);
            tvMes=itemView.findViewById(R.id.tvMes);
            tvPeso=itemView.findViewById(R.id.tvPeso);
            tvMM=itemView.findViewById(R.id.tvMM);
            tvMG=itemView.findViewById(R.id.tvMG);
        }
    }
}
