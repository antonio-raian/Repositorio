/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.repository.util.ipl;

import br.uefs.repository.model.CelulaArvore;
import br.uefs.repository.util.Iterador;

/**
 *
 * @author Antonio
 */
public class IteradorArvore implements Iterador{

    private Fila arvore;

    public IteradorArvore(CelulaArvore root) {
        arvore.inserirFinal(root);
    }
    
    @Override
    public boolean temProximo() {
        return !arvore.estaVazia();
    }

    @Override
    public Object obterProximo() {
        CelulaArvore c = (CelulaArvore)arvore.removerInicio();
        if(c.getFilho()!=null){
            CelulaArvore aux = c.getFilho();
            while(aux!=null){
                arvore.inserirFinal(aux);
                aux = aux.getIrmao();
            }
        }
        return c.getObj();
    }

    @Override
    public String toString() {
        return arvore.recuperarInicio().toString();
    }
}
