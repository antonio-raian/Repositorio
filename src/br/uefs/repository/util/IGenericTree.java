/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.repository.util;

import br.uefs.repository.exceptions.CelulaNaoEncontradaException;
/**
 *
 * @author Antonio
 */
public interface IGenericTree{
   
    public void addSon(Object o, Object pai, int altura)throws CelulaNaoEncontradaException;
    public int size();
    public int height();
    public Iterador iterator();   
}