/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.repository.exceptions;

/**
 *
 * @author Antonio
 */
public class CelulaNaoEncontradaException extends Exception{
    private static final long serialVersionUID = 1L;

    public CelulaNaoEncontradaException() {
        super();
    }
    
    public CelulaNaoEncontradaException(String str){
        super(str);
    }
    
    public CelulaNaoEncontradaException(String str, Throwable motivo){
        super(str, motivo);
    }
    
}
