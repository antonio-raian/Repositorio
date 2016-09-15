/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.repository.util.ipl;

/**
 *
 * @author Antonio
 */
public class Celula {
    private Object obj;
    private Celula prox; // referencia para a próxima celula
    private Celula ant; // referencia para a celula anterior

    public Celula(Object obj) {
        this.obj = obj;
    }

    public Object getObj() {
        return obj;
    }

    public Celula getProx() {
        return prox;
    }

    public void setProx(Celula prox) {
        this.prox = prox;
    }

    public Celula getAnt() {
        return ant;
    }

    public void setAnt(Celula ant) {
        this.ant = ant;
    }

    @Override
    public String toString() {
        return obj.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}
