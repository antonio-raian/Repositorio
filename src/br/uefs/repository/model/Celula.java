/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.repository.model;

import br.uefs.repository.util.Iterador;
import br.uefs.repository.util.Lista;
import com.sun.org.apache.bcel.internal.generic.AALOAD;

/**
 *
 * @author Antonio
 */
public class Celula {
    
    private Object obj;
    private Celula pai;
    private Lista filhos;
    private Celula irmao;

    public Celula(Object o) {
        this.obj = o;
        filhos = new Lista();
    }

    public Object getObj() {
        return obj;
    }

    public Celula getPai() {
        return pai;
    }

    public void setPai(Celula pai) {
        this.pai = pai;
    }

    public Iterador getFilhos() {
        return filhos.iterador();
    }

    public void setFilho(Object filho) {
        filhos.inserirFinal(filho);
    }

    public Celula getIrmao() {
        return irmao;
    }

    public void setIrmao(Celula irmao) {
        this.irmao = irmao;
    }
    
}
