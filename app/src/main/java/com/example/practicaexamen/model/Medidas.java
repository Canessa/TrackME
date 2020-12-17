package com.example.practicaexamen.model;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.sql.Blob;

public class Medidas implements Serializable {
    private int id;
    private String Mes;
    private Double Peso;
    private Double MasaMuscular ;
    private Double MasaGrasa ;
    private Double IMC ;
    private Double GrasaCoorporal;
    private int Viceral;

    public Medidas(String mes, Double peso) {
        Mes = mes;
        Peso = peso;
    }

    public Medidas(Double masaMuscular, Double masaGrasa) {
        MasaMuscular = masaMuscular;
        MasaGrasa = masaGrasa;
    }

    public Medidas(int id, String mes, Double peso, Double masaMuscular, Double masaGrasa, Double IMC, Double grasaCoorporal, int viceral) {
        this.id = id;
        Mes = mes;
        Peso = peso;
        MasaMuscular = masaMuscular;
        MasaGrasa = masaGrasa;
        this.IMC = IMC;
        GrasaCoorporal = grasaCoorporal;
        Viceral = viceral;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMes() {
        return Mes;
    }

    public void setMes(String mes) {
        Mes = mes;
    }

    public Double getPeso() {
        return Peso;
    }

    public void setPeso(Double peso) {
        Peso = peso;
    }

    public Double getMasaMuscular() {
        return MasaMuscular;
    }

    public void setMasaMuscular(Double masaMuscular) {
        MasaMuscular = masaMuscular;
    }

    public Double getMasaGrasa() {
        return MasaGrasa;
    }

    public void setMasaGrasa(Double masaGrasa) {
        MasaGrasa = masaGrasa;
    }

    public Double getIMC() {
        return IMC;
    }

    public void setIMC(Double IMC) {
        this.IMC = IMC;
    }

    public Double getGrasaCoorporal() {
        return GrasaCoorporal;
    }

    public void setGrasaCoorporal(Double grasaCoorporal) {
        GrasaCoorporal = grasaCoorporal;
    }

    public int getViceral() {
        return Viceral;
    }

    public void setViceral(int viceral) {
        Viceral = viceral;
    }
}
