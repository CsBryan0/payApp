package com.casotti.payapp.entities;

public enum UserType {
    COMMON(1),
    MERCHANT(2);

    private final int valor;

    UserType(int valor){
        this.valor = valor;
    }

    public int getValor(){
        return valor;
    }
}
