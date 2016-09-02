/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.repository.util.ipl;

import br.uefs.repository.exceptions.CelulaNaoEncontradoException;
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
        if(root!=null){
            return root.getObj();
        }
        return null;
    }

    /**
     *
     * @param obj
     * @param pai
     * @throws CelulaNaoEncontradoException
     */
    @Override
    public void addSon(Object obj, Object pai) throws CelulaNaoEncontradoException{
        CelulaArvore filho = new CelulaArvore(obj);
        CelulaArvore aux = (CelulaArvore)encontra(pai);
        if(aux == null){
            throw new CelulaNaoEncontradoException("error!");
        }
        if(aux.getFilho()==null){
            filho.setPai(aux);
            aux.setFilho(filho);
        }else{
            CelulaArvore aux2 = aux.getFilho();
            while(aux2!=null){
                if(aux2.getIrmao()==null){
                    filho.setPai(aux);
                    aux2.setIrmao(filho);
                    break;
                }
                aux2 = aux2.getIrmao();
            }
        }
        tam++;
    }

    @Override
    public Object[] getSons(Object pai) throws CelulaNaoEncontradoException{
        CelulaArvore aux = (CelulaArvore)encontra(pai);
        if(aux == null){
            throw new CelulaNaoEncontradoException("error!");
        }
        
        CelulaArvore aux2 = aux.getFilho();
        int cont=0;
        while (aux2 !=null){
            cont++;
            aux2 = aux2.getIrmao();
        }
        
        Object[] filhos = new Object[cont];
        aux2 = aux.getFilho();
        int i =0;
        while(aux2!=null){
            filhos[i] = aux2.getObj();
            i++;
            aux2 = aux2.getIrmao();
        }
        
        return filhos;
    }

    @Override
    public void set(Object o, Object celula) throws CelulaNaoEncontradoException{
        CelulaArvore aux = (CelulaArvore)encontra(celula);
        if(aux == null){
            throw new CelulaNaoEncontradoException("error!");
        }
        aux.setObj(o);
    }

    @Override
    public void remove(Object celula) throws CelulaNaoEncontradoException{
        CelulaArvore aux = (CelulaArvore)encontra(celula);
        if(aux == null){
            throw new CelulaNaoEncontradoException("error!");
        }
        
        if(aux.getFilho()!=null){
            aux.setObj(aux.getFilho().getObj());
            remove(aux.getFilho().getObj());
        }else{
            CelulaArvore pai = aux.getPai();
            if(pai==null){
                root = null;
            }else{
                pai.setFilho(aux.getIrmao());
            }
        }
        tam--;

    }

    @Override
    public int size() {
        return tam;
    }

    @Override
    public int height(Object celula) throws CelulaNaoEncontradoException{
        CelulaArvore aux = (CelulaArvore)encontra(celula);
        if(aux == null){
            throw new CelulaNaoEncontradoException("error!");
        }
        
        int cont= 0;
        if(aux != root){
            CelulaArvore aux2 = aux.getPai();
            while(aux2!=null){
                aux2 = aux2.getPai();
                cont++;
            }
        }
        return cont;
    }

    @Override
    public Iterador iterator() {
        return new IteradorArvore(root);
    }
    
    private Object encontra(Object o){
        Iterador it = iterator();
        
        while (it.temProximo()){
            CelulaArvore aux = (CelulaArvore) it.obterProximo();
            if(aux.getObj().equals(o)){
                return aux;
            }
        }
        return null;
        
    }
}
