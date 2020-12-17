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

import com.example.practicaexamen.Gestion.MedidasGestion;
import com.example.practicaexamen.R;
import com.example.practicaexamen.model.Medidas;

public class SlideshowFragment extends Fragment {
    private EditText etId;
    private EditText etMes;
    private EditText etPeso;
    private EditText etMM;
    private EditText etMG;
    private EditText etIMC;
    private EditText etGC;
    private EditText etViceral;

    private Button btInserta;
    private Button btConsulta;
    private Button btModifica;
    private Button btElimina;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        etId=root.findViewById(R.id.etId);
        etMes=root.findViewById(R.id.etMes);
        etPeso=root.findViewById(R.id.etPeso);
        etMM=root.findViewById(R.id.etMM);
        etMG=root.findViewById(R.id.etMG);
        etIMC=root.findViewById(R.id.etIMC);
        etGC=root.findViewById(R.id.etGC);
        etViceral=root.findViewById(R.id.etViceral);

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
        etMes.setText("");
        etPeso.setText("");
        etMM.setText("");
        etMG.setText("");
    }

    //Presenta la informacion de un estudiante en los controles EditText
    private void dibuja(Medidas medidas) {
        if (medidas !=null) {
            etId.setText(""+ medidas.getId());
            etMes.setText(medidas.getMes());
            etPeso.setText(""+medidas.getPeso());
            etMM.setText(""+medidas.getMasaMuscular());
            etMG.setText(""+ medidas.getMasaGrasa());
            etIMC.setText(""+medidas.getIMC());
            etGC.setText(""+medidas.getGrasaCoorporal());
            etViceral.setText(""+medidas.getViceral());
        } else {
            limpia();
        }
    }

    private Medidas getMedida() {
        Medidas medidas =null;
        String id= etId.getText().toString();
        String mes = etMes.getText().toString();
        String peso = etPeso.getText().toString();
        String masamuscular = etMM.getText().toString();
        String masagrasa = etMG.getText().toString();
        String imc = etIMC.getText().toString();
        String grasacoorporal = etGC.getText().toString();
        String viceral = etViceral.getText().toString();

        if (!id.isEmpty() && !mes.isEmpty() && !peso.isEmpty() && !masamuscular.isEmpty() && !masagrasa.isEmpty()) {
            medidas = new Medidas(
                    Integer.parseInt(id),
                    mes,
                    Double.parseDouble(peso),
                    Double.parseDouble(masamuscular),
                    Double.parseDouble(masagrasa),
                    Double.parseDouble(imc),
                    Double.parseDouble(grasacoorporal),
                    Integer.parseInt(viceral)
            );
        } else {
            Toast.makeText(getContext(), "Debe llenar toda la info", Toast.LENGTH_SHORT).show();
        }
        return medidas;
    }

    private void eliminar() {
        String id=etId.getText().toString();
        if (!id.isEmpty()) {  //Si el control id tiene info lo puedo buscar...
            int clave = Integer.parseInt(id);
            if (MedidasGestion.eliminar(clave)) {
                Toast.makeText(getContext(), "Medidas eliminado", Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(getContext(), "Medidas no encontrado", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Debe proporcionar el ID", Toast.LENGTH_SHORT).show();
        }
    }

    private void modificar() {
        if (MedidasGestion.modificar(getMedida())) {
            Toast.makeText(getContext(), "Medidas modificado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Error modificando medida", Toast.LENGTH_SHORT).show();
        }
    }

    private void consultar() {
        String id=etId.getText().toString();
        if (!id.isEmpty()) {
            int clave = Integer.parseInt(id);
            Medidas medidas = MedidasGestion.buscar(clave);
            if (medidas !=null) {
                dibuja(medidas);
            } else{
                Toast.makeText(getContext(), "Medidas no encontrado", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Debe proporcionar el ID", Toast.LENGTH_SHORT).show();
        }

    }

    private void insertar() {
        if (MedidasGestion.insertar(getMedida())) {
            Toast.makeText(getContext(), "Se introdujo una medida", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Error insertando medida", Toast.LENGTH_SHORT).show();
        }
    }
}