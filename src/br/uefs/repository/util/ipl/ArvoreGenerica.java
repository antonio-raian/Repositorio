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
    public void addSon(Object obj, Object pai) throws CelulaNaoEncontradoException{
        CelulaArvore filho = new CelulaArvore(obj);
        if(pai==null){
            root = filho;
            root.setIrmao(null);
            root.setPai(null);
            root.setAltura(0);
        }else{
            CelulaArvore aux = (CelulaArvore)encontra(pai);
            if(aux == null){
                throw new CelulaNaoEncontradoException("error!");
            }
            filho.setPai(aux);
            filho.setAltura(aux.getAltura()+1);
            if(aux.getFilho()==null){
                aux.setFilho(filho);
            }else{
                CelulaArvore aux2 = aux.getFilho();
                while(aux2!=null){
                    if(aux2.getIrmao()==null){
                        aux2.setIrmao(filho);
                        break;
                    }
                    aux2 = aux2.getIrmao();
                }
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
        Iterador it = new IteradorArvore(root);
        return it;
    }
    
    private Object encontra(Object o){
        Fila filaArvore = new Fila();
        filaArvore.inserirFinal(root);
        while (!filaArvore.estaVazia()){
            CelulaArvore aux = (CelulaArvore)filaArvore.removerInicio();
            if(aux.getObj().equals(o)){
                return aux;
            }else if(aux.getFilho()!=null){
                CelulaArvore aux2 = aux.getFilho();
                while(aux2!=null){
                    filaArvore.inserirFinal(aux2);
                    aux2 = aux2.getIrmao();
                }
            }
        }
        return null;
    }
}