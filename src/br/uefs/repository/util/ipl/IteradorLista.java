/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.repository.util.ipl;

import br.uefs.repository.model.Celula;
import br.uefs.repository.util.Iterador;

/**
 *
 * @author Antonio
 */
public class IteradorLista implements Iterador{

    private Celula cel;
   
    public IteradorLista(Celula cel) {
        this.cel = cel;
    }
    
    @Override
    public boolean temProximo() {
        return cel != null;
    }

    @Override
    public Object obterProximo() {
        Celula c = cel;
        if(cel!=null){
            cel = cel.getProx();
        }
        return c.getObj();
    }

    @Override
    public String toString() {
        return cel.toString(); 
    }
}