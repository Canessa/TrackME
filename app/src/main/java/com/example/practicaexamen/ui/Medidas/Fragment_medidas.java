package com.example.practicaexamen.ui.Medidas;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.practicaexamen.ActivityMedida;
import com.example.practicaexamen.Gestion.MedidasGestion;
import com.example.practicaexamen.R;
import com.example.practicaexamen.adapter.MedidasAdapter;
import com.example.practicaexamen.model.Medidas;

import java.util.List;

public class Fragment_medidas extends Fragment {

    //Los atributos para mostrar el recycler view
    private RecyclerView recyclerView;
    private MedidasAdapter medidasAdapter;
    private RecyclerView.LayoutManager layoutManager;

    //Para modificar en varios metodoso
    private List<Medidas> lista;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, 
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_medidas, container, false);


        recyclerView = root.findViewById(R.id.reciclador);
        recyclerView.setHasFixedSize(true);


        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        lista = MedidasGestion.getMedidas();
        final List<Medidas> listaFinal=lista;
        medidasAdapter = new MedidasAdapter(lista);
        medidasAdapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.getChildAdapterPosition(v);
                Medidas medidas = lista.get(position);
                Intent intent = new Intent(getContext(), ActivityMedida.class);
                intent.putExtra("tipo",1); //parametro 1 modificar/ eliminar
                intent.putExtra("Medidas", medidas);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(medidasAdapter);

        return root;
    }

    @Override
    public void onResume(){
        super.onResume();
        lista = MedidasGestion.getMedidas();
        medidasAdapter.Actualiza(lista);
        medidasAdapter.notifyDataSetChanged();
    }


}