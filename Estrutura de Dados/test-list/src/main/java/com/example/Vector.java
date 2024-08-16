package com.example;

public class Vector {
    int idx;
    Integer valor;
    Vector prox;

    Vector() {}

    Vector(int valor) {
        this.valor = valor;
    }

    Vector(int valor, Vector prox) {
        this(valor);
        this.prox = prox;
    }

    public void add(int num) {
        if (this.valor != null) {
            Vector newVector = new Vector(this.valor, this.prox);
            this.prox = newVector;
        }
        this.idx++;
        this.valor = num;
    }

    public void show() {
        System.out.println(toString());
    }
    
    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Vector getProx() {
        return prox;
    }

    public void setProx(Vector prox) {
        this.prox = prox;
    }
    
    @Override
    public String toString() {
        return "Vector: { valor: %d, prox: %s }".formatted(this.valor,  this.prox);
    }

}
