/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.repository.util;

import br.uefs.repository.exceptions.CelulaNaoEncontradoException;
import br.uefs.repository.model.CelulaArvore;

/**
 *
 * @author Antonio
 */
public interface IGenericTree{
    
    public void addRoot(Object o);
    public Object getRoot();
    public void addSon(Object o, Object pai)throws CelulaNaoEncontradoException;
    public Object[] getSons(Object pai) throws CelulaNaoEncontradoException;
    public void set(Object o, Object celula) throws CelulaNaoEncontradoException;
    public void remove(Object celula) throws CelulaNaoEncontradoException;
    public int size();
    public int height (Object celula)throws CelulaNaoEncontradoException;
    public Iterador iterator();   
}