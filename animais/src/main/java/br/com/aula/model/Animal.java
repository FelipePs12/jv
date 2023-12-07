package br.com.aula.model;

public class Animal {
    private static int contadorId = 1;

    private int id;
    private String nome;
    private String especie;
    private double peso;

    public static int getContadorId() {
        return contadorId;
    }

    public static void setContadorId(int contadorId) {
        Animal.contadorId = contadorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Animal(String nome, String especie, double peso) {
        this.id = contadorId++;
        this.nome = nome;
        this.especie = especie;
        this.peso = peso;
    }
}