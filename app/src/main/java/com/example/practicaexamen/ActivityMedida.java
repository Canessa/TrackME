package com.example.practicaexamen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.practicaexamen.Gestion.MedidasGestion;
import com.example.practicaexamen.model.Medidas;

public class ActivityMedida extends AppCompatActivity {

    private EditText etId;
    private EditText etMes;
    private EditText etPeso;
    private EditText etMM;
    private EditText etMG;
    private EditText etIMC;
    private EditText etGC;
    private EditText etViceral;

    private Button boton1;
    private Button boton2;

    private int tipo;
    private Medidas medidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medida);

        etId = findViewById(R.id.etId);
        etMes = findViewById(R.id.etMes);
        etPeso = findViewById(R.id.etPeso);
        etMM = findViewById(R.id.etMM);
        etMG = findViewById(R.id.etMG);
        etIMC = findViewById(R.id.etIMC);
        etGC = findViewById(R.id.etGC);
        etViceral = findViewById(R.id.etViceral);
        boton1 = findViewById(R.id.button1);
        boton2 = findViewById(R.id.button2);

        tipo = getIntent().getIntExtra("tipo",0);
        if(tipo == 1){//estaba en el recycler y voy a modificar
            medidas = (Medidas) getIntent().getSerializableExtra("Medidas");
            etId.setText(""+ medidas.getId());
            etMes.setText(medidas.getMes());
            etPeso.setText(""+medidas.getPeso());
            etMM.setText(""+medidas.getMasaMuscular());
            etMG.setText(""+ medidas.getMasaGrasa());
            etIMC.setText(""+ medidas.getIMC());
            etGC.setText(""+ medidas.getGrasaCoorporal());
            etViceral.setText(""+ medidas.getViceral());
            boton1.setText("Modificar");
            boton2.setText("Eliminar");
        }else{//di click en el boton nuevo

            etId.setText("");
            etMes.setText("");
            etPeso.setText("");
            etMM.setText("");
            etMG.setText("");
            etIMC.setText("");
            etGC.setText("");
            etViceral.setText("");
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
            if(MedidasGestion.eliminar(id)){
                Toast.makeText(getApplicationContext(),"Eliminado", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }

    private void proceso1() {
        int id = Integer.parseInt(etId.getText().toString());
        String mes = etMes.getText().toString();
        Double peso = Double.parseDouble(etPeso.getText().toString());
        Double masamuscular = Double.parseDouble(etMM.getText().toString());
        Double masagrasa = Double.parseDouble(etMG.getText().toString());
        Double imc = Double.parseDouble(etIMC.getText().toString());
        Double grasacoorporal = Double.parseDouble(etGC.getText().toString());
        int viceral = Integer.parseInt(etViceral.getText().toString());
        medidas = new Medidas(id,mes,peso,masamuscular,masagrasa,imc,grasacoorporal,viceral);

        boolean resultado = (tipo == 1) ? MedidasGestion.modificar(medidas):
                MedidasGestion.insertar(medidas);
        if(resultado){
            Toast.makeText(getApplicationContext(),"Realizado", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}