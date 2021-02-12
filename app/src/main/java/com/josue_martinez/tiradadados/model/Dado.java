package com.josue_martinez.tiradadados.model;

public class Dado {
    //esta clase solo la creo para generar un numero aleatorio dependiendo de las caras del dado
    public Integer numCaras;

    public Dado(Integer numCaras){
        this.numCaras = numCaras;
    }

    public Integer TirarDado(){
        Integer numero;
        numero = (int) (Math.random() * this.numCaras) + 1;
        return  numero;
    }
}
