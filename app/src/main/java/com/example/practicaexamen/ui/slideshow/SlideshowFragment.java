package com.example.practicaexamen.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.practicaexamen.Gestion.PeliculasGestion;
import com.example.practicaexamen.R;
import com.example.practicaexamen.model.Pelicula;

public class SlideshowFragment extends Fragment {
    private EditText etId;
    private EditText etNombre;
    private EditText etDirector;
    private EditText etGenero;
    private EditText etDuracion;

    private Button btInserta;
    private Button btConsulta;
    private Button btModifica;
    private Button btElimina;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        etId=root.findViewById(R.id.etId);
        etNombre=root.findViewById(R.id.etNombre);
        etDirector=root.findViewById(R.id.etApellidos);
        etGenero=root.findViewById(R.id.tvGenero);
        etDuracion=root.findViewById(R.id.etProvincia);

        //Mapeo los controles Button
        btInserta=root.findViewById(R.id.btInserta);
        btConsulta=root.findViewById(R.id.btConsulta);
        btModifica=root.findViewById(R.id.btModifica);
        btElimina=root.findViewById(R.id.btElimina);


        btInserta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertar();
            }
        });

        btConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultar();
            }
        });

        btModifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificar();
            }
        });

        btElimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar();
            }
        });
        return root;
    }
    //limpia los controles de los editText
    private void limpia() {
        etId.setText("");
        etNombre.setText("");
        etDirector.setText("");
        etGenero.setText("");
        etDuracion.setText("");
    }

    //Presenta la informacion de un estudiante en los controles EditText
    private void dibuja(Pelicula pelicula) {
        if (pelicula !=null) {
            etId.setText(""+ pelicula.getId());
            etNombre.setText(pelicula.getNombre());
            etDirector.setText(pelicula.getDirector());
            etGenero.setText(pelicula.getGenero());
            etDuracion.setText(""+pelicula.getDuracion());
        } else {
            limpia();
        }
    }

    private Pelicula getUsuario() {
        Pelicula pelicula =null;
        String id= etId.getText().toString();
        String nombre = etNombre.getText().toString();
        String director = etDirector.getText().toString();
        String genero = etGenero.getText().toString();
        String duracion = etDuracion.getText().toString();

        if (!id.isEmpty() && !nombre.isEmpty() && !director.isEmpty() && !genero.isEmpty() && !duracion.isEmpty()) {
            pelicula = new Pelicula(
                    Integer.parseInt(id),
                    nombre,
                    director,
                    genero,
                    Double.parseDouble(duracion)
            );
        } else {
            Toast.makeText(getContext(), "Debe llenar toda la info", Toast.LENGTH_SHORT).show();
        }
        return pelicula;
    }

    private void eliminar() {
        String id=etId.getText().toString();
        if (!id.isEmpty()) {  //Si el control id tiene info lo puedo buscar...
            int clave = Integer.parseInt(id);
            if (PeliculasGestion.eliminar(clave)) {
                Toast.makeText(getContext(), "Pelicula eliminado", Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(getContext(), "Pelicula no encontrado", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Debe proporcionar el ID", Toast.LENGTH_SHORT).show();
        }
    }

    private void modificar() {
        if (PeliculasGestion.modificar(getUsuario())) {
            Toast.makeText(getContext(), "Pelicula modificado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Error modificando usuario", Toast.LENGTH_SHORT).show();
        }
    }

    private void consultar() {
        String id=etId.getText().toString();
        if (!id.isEmpty()) {
            int clave = Integer.parseInt(id);
            Pelicula pelicula = PeliculasGestion.buscar(clave);
            if (pelicula !=null) {
                dibuja(pelicula);
            } else{
                Toast.makeText(getContext(), "Pelicula no encontrado", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Debe proporcionar el ID", Toast.LENGTH_SHORT).show();
        }

    }

    private void insertar() {
        if (PeliculasGestion.insertar(getUsuario())) {
            Toast.makeText(getContext(), "Se introdujo una pelicula", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Error insertando pelicula", Toast.LENGTH_SHORT).show();
        }
    }
}