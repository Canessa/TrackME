package com.example.practicaexamen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.practicaexamen.Gestion.PeliculasGestion;
import com.example.practicaexamen.model.Pelicula;

public class ActivityPelicula extends AppCompatActivity {

    private EditText etId;
    private EditText etNombre;
    private EditText etDirector;
    private EditText etGenero;
    private EditText etDuracion;
    private Button boton1;
    private Button boton2;

    private int tipo;
    private Pelicula pelicula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelicula);

        etId = findViewById(R.id.etId);
        etNombre = findViewById(R.id.etNombre);
        etDirector = findViewById(R.id.etApellidos);
        etGenero = findViewById(R.id.etGenero);
        etDuracion = findViewById(R.id.etProvincia);
        boton1 = findViewById(R.id.button1);
        boton2 = findViewById(R.id.button2);

        tipo = getIntent().getIntExtra("tipo",0);
        if(tipo == 1){//estaba en el recycler y voy a modificar
            pelicula = (Pelicula) getIntent().getSerializableExtra("Pelicula");
            etId.setText(""+ pelicula.getId());
            etNombre.setText(pelicula.getNombre());
            etDirector.setText(pelicula.getDirector());
            etGenero.setText(pelicula.getGenero());
            etDuracion.setText(""+pelicula.getDuracion());
            boton1.setText("Modificar");
            boton2.setText("Eliminar");
        }else{//di click en el boton nuevo

            etId.setText("");
            etNombre.setText("");
            etDirector.setText("");
            etGenero.setText("");
            etDuracion.setText("");
            boton1.setText("Crear");
            boton2.setText("Cancelar");
        }
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceso1();
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceso2();
            }
        });
    }

    private void proceso2() {
        if(tipo==1){
            int id = Integer.parseInt(etId.getText().toString());
            if(PeliculasGestion.eliminar(id)){
                Toast.makeText(getApplicationContext(),"Eliminado", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }

    private void proceso1() {
        int id = Integer.parseInt(etId.getText().toString());
        String nombre = etNombre.getText().toString();
        String director = etDirector.getText().toString();
        String genero = etGenero.getText().toString();
        Double duracion = Double.parseDouble(etDuracion.getText().toString());
        pelicula = new Pelicula(id,nombre,director,genero,duracion);

        boolean resultado = (tipo == 1) ? PeliculasGestion.modificar(pelicula):
                PeliculasGestion.insertar(pelicula);
        if(resultado){
            Toast.makeText(getApplicationContext(),"Realizado", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}