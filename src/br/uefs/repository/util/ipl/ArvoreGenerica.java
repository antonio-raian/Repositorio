/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.repository.util.ipl;

import br.uefs.repository.model.CelulaArvore;
import br.uefs.repository.util.IGenericTree;
import br.uefs.repository.util.Iterador;

/**
 *
 * @author Antonio
 */
public class ArvoreGenerica implements IGenericTree{

    private CelulaArvore root;
    private int tam;
    
    @Override
    public void addRoot(Object o) {
        root = new CelulaArvore(o);
        root.setPai(null);
        root.setIrmao(null);
        tam++;
    }

    @Override
    public Object getRoot() {
        return root.getObj();
    }

    @Override
    public void addSon(Object o, CelulaArvore pai) {
        CelulaArvore filho = new CelulaArvore(o);
        if(pai.getFilho()==null){
            filho.setPai(pai);
            pai.setFilho(filho);
        }else{
            CelulaArvore aux = pai.getFilho();
            while(aux!=null){
                if(aux.getIrmao()==null){
                    filho.setPai(pai);
                    aux.setIrmao(filho);
                    break;
                }
                aux = aux.getIrmao();
            }
        }
        tam++;
    }

    @Override
    public Object[] getSons(CelulaArvore pai) {
        CelulaArvore aux = pai.getFilho();
        int cont=0;
        while (aux !=null){
            cont++;
            aux = aux.getIrmao();
        }
        
        Object[] filhos = new Object[cont];
        aux = pai.getFilho();
        int i =0;
        while(aux!=null){
            filhos[i] = aux.getObj();
            i++;
            aux = aux.getIrmao();
        }
        
        return filhos;
    }

    @Override
    public void set(Object o, CelulaArvore celula) {
        if(celula!=null)
            celula.setObj(o);
    }

    @Override
    public void remove(CelulaArvore celula) {
        if(celula.getFilho()!=null){
            celula.setObj(celula.getFilho().getObj());
            remove(celula.getFilho());
        }else{
            CelulaArvore pai = celula.getPai();
            if(pai==null){
                root = null;
            }else{
                pai.setFilho(celula.getIrmao());
            }
        }
        tam--;

    }

    @Override
    public int size() {
        return tam;
    }

    @Override
    public int height(CelulaArvore celula) {
        int cont= 0;
        if(celula != root){
            CelulaArvore aux = celula.getPai();
            while(aux!=null){
                aux = aux.getPai();
                cont++;
            }
        }
        return cont;
    }

    @Override
    public Iterador iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
