package com.example.practicaexamen.ui.Pelicula;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.practicaexamen.ActivityPelicula;
import com.example.practicaexamen.Gestion.PeliculasGestion;
import com.example.practicaexamen.R;
import com.example.practicaexamen.adapter.PeliculaAdapter;
import com.example.practicaexamen.model.Pelicula;

import java.util.List;

public class Fragment_pelicula extends Fragment {

    //Los atributos para mostrar el recycler view
    private RecyclerView recyclerView;
    private PeliculaAdapter peliculaAdapter;
    private RecyclerView.LayoutManager layoutManager;

    //Para modificar en varios metodoso
    private List<Pelicula> lista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_pelicula, container, false);


        recyclerView = root.findViewById(R.id.reciclador);
        recyclerView.setHasFixedSize(true);


        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        lista = PeliculasGestion.getPeliculas();
        final List<Pelicula> listaFinal=lista;
        peliculaAdapter = new PeliculaAdapter(lista);
        peliculaAdapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.getChildAdapterPosition(v);
                Pelicula pelicula = lista.get(position);
                Intent intent = new Intent(getContext(), ActivityPelicula.class);
                intent.putExtra("tipo",1); //parametro 1 modificar/ eliminar
                intent.putExtra("Pelicula", pelicula);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(peliculaAdapter);

        return root;
    }

    @Override
    public void onResume(){
        super.onResume();
        lista = PeliculasGestion.getPeliculas();
        peliculaAdapter.Actualiza(lista);
        peliculaAdapter.notifyDataSetChanged();
    }


}