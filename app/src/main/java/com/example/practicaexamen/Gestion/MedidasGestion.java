package com.example.practicaexamen.Gestion;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.data.View;
import com.example.practicaexamen.Data.AdminDB;
import com.example.practicaexamen.model.Medidas;

import java.util.ArrayList;
import java.util.List;

public class MedidasGestion {

    private static AdminDB data=null;  //Enlace con la base de datos fisica
    private static SQLiteDatabase conexion=null;

    public static void init(AdminDB data) {
        MedidasGestion.data=data;
    }

    public static boolean insertar(Medidas medidas) {
        long resultado = -1;
        if (medidas !=null) {
            ContentValues registro = new ContentValues();
            registro.put("id", medidas.getId());
            registro.put("mes", medidas.getMes());
            registro.put("peso", medidas.getPeso());
            registro.put("masamuscular", medidas.getMasaMuscular());
            registro.put("masagrasa", medidas.getMasaGrasa());
            registro.put("imc", medidas.getIMC());
            registro.put("grasacoorporal", medidas.getGrasaCoorporal());
            registro.put("viceral", medidas.getViceral());
            //Establezco la conexion con BD
            conexion = data.getWritableDatabase();
            //Realizo el insert
            resultado=conexion.insert("Fitness",null,registro);
            conexion.close();
        }
        return resultado!=-1;
    }

    public static Medidas buscar(int id){
        Medidas medidas =null;
        conexion=data.getReadableDatabase();

        Cursor datos=conexion.rawQuery("select * from Fitness where id=?",
                new String[]{""+id+""});

        if (datos.moveToFirst()) {
            medidas = new Medidas(id,
                    datos.getString(1),
                    datos.getDouble(2),
                    datos.getDouble(3),
                    datos.getDouble(4),
                    datos.getDouble(5),
                    datos.getDouble(6),
                    datos.getInt(7));
        }
        conexion.close();
        return medidas;
    }

    public static boolean modificar(Medidas medidas) {
        long resultado = -1;
        if (medidas !=null) {
            ContentValues registro = new ContentValues();
            registro.put("id", medidas.getId());
            registro.put("mes", medidas.getMes());
            registro.put("peso", medidas.getPeso());
            registro.put("masamuscular", medidas.getMasaMuscular());
            registro.put("masagrasa", medidas.getMasaGrasa());
            registro.put("imc", medidas.getIMC());
            registro.put("grasacoorporal", medidas.getGrasaCoorporal());
            registro.put("viceral", medidas.getViceral());
            //Establezco la conexion con BD
            conexion = data.getWritableDatabase();
            //Realizo el update
            resultado=conexion.update("Fitness",registro,
                    "id=?",new String[]{""+ medidas.getId()+""});
            conexion.close();
        }

        return resultado==1;
    }

    public static boolean eliminar(int id){
        conexion = data.getWritableDatabase();
        long resultado = conexion.delete("Fitness",
                "id=?",new String[]{""+id+""});
        conexion.close();
        return resultado==1;
    }

    public static ArrayList<Medidas> getMedidas(){
        ArrayList<Medidas> lista=new ArrayList<>();

        conexion=data.getReadableDatabase();

        Cursor datos=conexion.rawQuery("select * from Fitness",
                null);

        while (datos.moveToNext()) {
            lista.add(new Medidas(datos.getInt(0),
                    datos.getString(1),
                    datos.getDouble(2),
                    datos.getDouble(3),
                    datos.getDouble(4),
                    datos.getDouble(5),
                    datos.getDouble(6),
                    datos.getInt(7)));
        }
        conexion.close();
        return lista;
    }

    public static ArrayList<DataEntry> getPesos(){


        conexion=data.getReadableDatabase();
        Cursor datos=conexion.rawQuery("select mes,peso from Fitness",
                null);

        ArrayList<DataEntry> listaPesos = new ArrayList<>();
        while (datos.moveToNext()) {
            listaPesos.add(
                    new ValueDataEntry(
                        datos.getString(0),
                        ((int)datos.getDouble(1))
                    ));
        }
        conexion.close();
        return listaPesos;
    }

    public static ArrayList<DataEntry> getMasas(){


        conexion=data.getReadableDatabase();
        Cursor datos=conexion.rawQuery("select masamuscular,masagrasa from Fitness",
                null);

        ArrayList<DataEntry> Masas = new ArrayList<>();
        while (datos.moveToLast()) {
            Masas.add(
                    new ValueDataEntry(
                            "Masa Muscular",
                            ((int)datos.getDouble(0))
                    ));
            Masas.add(
                    new ValueDataEntry(
                            "Masa Grasa",
                            ((int)datos.getDouble(1))
                    ));
        }
        conexion.close();
        return Masas;
    }
}
