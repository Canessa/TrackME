package com.example.practicaexamen.Gestion;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.practicaexamen.Data.AdminDB;
import com.example.practicaexamen.model.Pelicula;

import java.util.ArrayList;

public class PeliculasGestion {

    private static AdminDB data=null;  //Enlace con la base de datos fisica
    private static SQLiteDatabase conexion=null;

    public static void init(AdminDB data) {
        PeliculasGestion.data=data;
    }

    public static boolean insertar(Pelicula pelicula) {
        long resultado = -1;
        if (pelicula !=null) {
            ContentValues registro = new ContentValues();
            registro.put("id", pelicula.getId());
            registro.put("nombre", pelicula.getNombre());
            registro.put("director", pelicula.getDirector());
            registro.put("genero", pelicula.getGenero());
            registro.put("duracion", pelicula.getDuracion());
            //Establezco la conexion con BD
            conexion = data.getWritableDatabase();
            //Realizo el insert
            resultado=conexion.insert("Pelicula",null,registro);
            conexion.close();
        }
        return resultado!=-1;
    }

    public static Pelicula buscar(int id){
        Pelicula pelicula =null;
        conexion=data.getReadableDatabase();

        Cursor datos=conexion.rawQuery("select * from Pelicula where id=?",
                new String[]{""+id+""});

        if (datos.moveToFirst()) {
            pelicula = new Pelicula(id,
                    datos.getString(1),
                    datos.getString(2),
                    datos.getString(3),
                    datos.getDouble(4));
        }
        conexion.close();
        return pelicula;
    }

    public static boolean modificar(Pelicula pelicula) {
        long resultado = -1;
        if (pelicula !=null) {
            ContentValues registro = new ContentValues();
            registro.put("id", pelicula.getId());
            registro.put("nombre", pelicula.getNombre());
            registro.put("director", pelicula.getDirector());
            registro.put("genero", pelicula.getGenero());
            registro.put("duracion", pelicula.getDuracion());
            //Establezco la conexion con BD
            conexion = data.getWritableDatabase();
            //Realizo el update
            resultado=conexion.update("Pelicula",registro,
                    "id=?",new String[]{""+ pelicula.getId()+""});
            conexion.close();
        }

        return resultado==1;
    }

    public static boolean eliminar(int id){
        conexion = data.getWritableDatabase();
        long resultado = conexion.delete("Pelicula",
                "id=?",new String[]{""+id+""});
        conexion.close();
        return resultado==1;
    }

    public static ArrayList<Pelicula> getPeliculas(){
        ArrayList<Pelicula> lista=new ArrayList<>();

        conexion=data.getReadableDatabase();

        Cursor datos=conexion.rawQuery("select * from Pelicula",
                null);

        while (datos.moveToNext()) {
            lista.add(new Pelicula(datos.getInt(0),
                    datos.getString(1),
                    datos.getString(2),
                    datos.getString(3),
                    datos.getDouble(4)));
        }
        conexion.close();
        return lista;
    }
}
