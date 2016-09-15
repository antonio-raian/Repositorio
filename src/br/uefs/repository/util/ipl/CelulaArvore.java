/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.repository.util.ipl;

import br.uefs.repository.util.Elemento;

/**
 *
 * @author Antonio
 */
public class CelulaArvore implements Elemento{
    
    private Object obj;
    private CelulaArvore pai;
    private CelulaArvore filho;
    private CelulaArvore irmao;
    private int altura;

    public CelulaArvore(Object o) {
        this.obj = o;
    }

    @Override
    public Object getObj() {
        return obj;
    }
    
    public void setObj(Object o){
        this.obj = o;
    }

    public CelulaArvore getPai() {
        return pai;
    }

    public void setPai(CelulaArvore pai) {
        this.pai = pai;
    }

    public CelulaArvore getFilho() {
        return filho;
    }

    public void setFilho(CelulaArvore filho) {
        this.filho = filho;
    }

    public CelulaArvore getIrmao() {
        return irmao;
    }

    public void setIrmao(CelulaArvore irmao) {
        this.irmao = irmao;
    }
    
    @Override
    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    @Override
    public String toString() {
        return obj.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof CelulaArvore){
            CelulaArvore c = (CelulaArvore) obj;
            
            if(this.obj == c.obj && this.pai == c.pai && this.altura == c.altura){
                return true;
            }
        }
        return false;
    }
}
