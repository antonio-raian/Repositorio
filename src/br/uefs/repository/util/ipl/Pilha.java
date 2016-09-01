/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.repository.util.ipl;

import br.uefs.repository.model.Celula;
import br.uefs.repository.util.IPilha;

/**
 *
 * @author Antonio
 */
public class Pilha implements IPilha{
    
    private Celula topo;
    private int tam =0;
    
    @Override
    public int obterTamanho() {
        return tam;
    }

    @Override
    public boolean estaVazia() {
        return topo==null;
    }

    @Override
    public Object removerTopo() {
        Object o = null;
        if(!estaVazia()){
            o = topo.getObj();
            topo = topo.getAnt();
        }
        tam--;
        return o;
    }

    @Override
    public void inserirTopo(Object obj) {
        Celula nova = new Celula(obj);
        nova.setAnt(topo);
        topo = nova;
        tam++;
    }

    @Override
    public Object recuperarTopo() {
        return topo.getObj();
    }
}