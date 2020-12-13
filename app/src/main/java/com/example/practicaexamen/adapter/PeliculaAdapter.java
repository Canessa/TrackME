package com.example.practicaexamen.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicaexamen.R;
import com.example.practicaexamen.model.Pelicula;

import java.util.List;

public class PeliculaAdapter
        extends
        RecyclerView.Adapter<PeliculaAdapter.UsuarioViewHolder>
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

    private List<Pelicula> lista;
    public PeliculaAdapter(List<Pelicula> lista) {
        this.lista = lista;
    }

    public void Actualiza (List<Pelicula>lista){
        this.lista = lista;
    }

    @NonNull
    @Override
    public PeliculaAdapter.UsuarioViewHolder onCreateViewHolder(
        @NonNull ViewGroup parent, int viewType) {
            View vista = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.card_pelicula,parent,false);
            vista.setOnClickListener(this);
            return new UsuarioViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaAdapter.UsuarioViewHolder holder,
                                 int position) {
        holder.tvId.setText(""+lista.get(position).getId());
        holder.tvNombre.setText(lista.get(position).getNombre());
        holder.tvApellidos.setText(lista.get(position).getDirector());
        holder.tvGenero.setText(lista.get(position).getGenero());
        holder.tvProvincia.setText(""+lista.get(position).getDuracion());
    }

    @Override
    public int getItemCount() {

        return lista.size();
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder {
        public TextView tvId;
        public TextView tvNombre;
        public TextView tvApellidos;
        public TextView tvGenero;
        public TextView tvProvincia;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId=itemView.findViewById(R.id.tvId);
            tvNombre=itemView.findViewById(R.id.tvNombre);
            tvApellidos=itemView.findViewById(R.id.tvApellidos);
            tvGenero=itemView.findViewById(R.id.tvGenero);
            tvProvincia=itemView.findViewById(R.id.tvProvincia);
        }
    }
}
