/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.repository.util;

import br.uefs.repository.model.CelulaArvore;

/**
 *
 * @author Antonio
 */
public interface IGenericTree{
    
    public void addRoot(Object o);
    public Object getRoot();
    public void addSon(Object o, CelulaArvore pai);
    public Object[] getSons(CelulaArvore pai);
    public void set(Object o, CelulaArvore celula);
    public void remove(CelulaArvore celula);
    public int size();
    public int height (CelulaArvore celula);
    public Iterador iterator();   
}