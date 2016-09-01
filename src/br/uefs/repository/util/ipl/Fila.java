/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.repository.util.ipl;

import br.uefs.repository.model.Celula;
import br.uefs.repository.util.IFila;

/**
 *
 * @author Antonio
 */
public class Fila implements IFila{

    private Celula primeiro, ultimo;
    private int tam;

    public Fila() {
        primeiro = ultimo = null;
    }
    
    @Override
    public boolean estaVazia() {
        return primeiro == null;
    }

    @Override
    public int obterTamanho() {
        return tam;
    }

    @Override
    public void inserirFinal(Object o) {
        Celula c = new Celula(o);
        c.setProx(null);
        if(estaVazia()){
            primeiro = ultimo = c;
        }else{
            ultimo.setProx(c);
            ultimo = c;
        }
        tam++;
    }

    @Override
    public Object removerInicio() {
        Object c = null;
        if(estaVazia()){
            return c;
        }
        if(primeiro == ultimo){
            c = primeiro.getObj();
            primeiro = null;
            ultimo = null;
        }else{
            c = primeiro.getObj();
            primeiro = primeiro.getProx();
        }
        tam--;
        return c;
    }

    @Override
    public Object recuperarInicio() {
        if(estaVazia()){
            return null;
        }
        return primeiro.getObj();
    }
    
}