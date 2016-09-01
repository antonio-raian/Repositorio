/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.repository.util;

import br.uefs.repository.model.Celula;

/**
 *
 * @author Antonio
 */
public interface IGenericTree{
    
    public void addRoot(Object o);
    public Object getRoot();
    public void addSon(Object o, Celula pai);
    public Iterador getSon(Celula pai);
    public void set(Object o, Celula celula);
    public void remove(Celula celula);
    public int size();
    public int height (Celula celula);
    public Iterador iterator();
    
}