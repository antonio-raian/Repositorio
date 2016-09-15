/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.repository.util.ipl;

import br.uefs.repository.util.Elemento;
import br.uefs.repository.util.Iterador;

/**
 *
 * @author Antonio
 */
public class IteradorArvore implements Iterador{

    private Fila filaArvore = new Fila();

    public IteradorArvore(CelulaArvore root) {
        filaArvore.inserirFinal(root);
    }
    
    @Override
    public boolean temProximo() {
        return !filaArvore.estaVazia();
    }

    @Override
    public Elemento obterProximo() {
        CelulaArvore c = (CelulaArvore)filaArvore.removerInicio();
        if(c.getFilho()!=null){
            CelulaArvore aux = c.getFilho();
            while(aux!=null){
                filaArvore.inserirFinal(aux);
                aux = aux.getIrmao();
            }
        }
        return c;
    }
    
    @Override
    public String toString() {
        return filaArvore.recuperarInicio().toString();
    }
}
