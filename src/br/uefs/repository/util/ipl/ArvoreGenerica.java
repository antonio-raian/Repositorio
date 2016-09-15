/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.repository.util.ipl;

import br.uefs.repository.exceptions.CelulaNaoEncontradoException;
import br.uefs.repository.util.Elemento;
import br.uefs.repository.util.IGenericTree;
import br.uefs.repository.util.Iterador;

/**
 *
 * @author Antonio
 */
public class ArvoreGenerica implements IGenericTree{

    private CelulaArvore root;
    private int tam;
    private int height = 0;
   
    @Override
    public void addSon(Object obj, Object pai, int altura) throws CelulaNaoEncontradoException{
        CelulaArvore filho = new CelulaArvore(obj);
        if(pai==null){            
            filho.setIrmao(null);
            filho.setPai(null);
            filho.setAltura(altura);
            root = filho;
        }else{
            CelulaArvore aux = (CelulaArvore)encontra(pai,(altura-1));
            if(aux == null){
                throw new CelulaNaoEncontradoException("error!");
            }
            filho.setPai(aux);
            filho.setAltura(altura);
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
        if(altura>height){
            height = altura;
        }
    }

    public String caminho(Elemento e){
        String str ="";
        Pilha pilhaCaminho = new Pilha();
        CelulaArvore cel = (CelulaArvore) e;
        pilhaCaminho.inserirTopo(cel);
        CelulaArvore aux = cel.getPai();
        while(aux!=null){
            pilhaCaminho.inserirTopo(aux);
            aux=aux.getPai();
        }
        while(!pilhaCaminho.estaVazia()){
            if(pilhaCaminho.recuperarTopo().toString().contains("\\")){
                str+=pilhaCaminho.removerTopo().toString();
            }else{
                str+="\\"+pilhaCaminho.removerTopo().toString();
            }
        }
        return str;
    }
    
    @Override
    public int size() {
        return tam;
    }
    
    @Override
    public Iterador iterator() {
        return new IteradorArvore(root);
    }
    
    public Iterador mapa(Elemento e){
        CelulaArvore aux = (CelulaArvore) encontra(e.getObj(), e.getAltura());
        return new IteradorArvore(aux);
    }
    
    private Object encontra(Object o, int altura){
        Fila filaArvore = new Fila();
        filaArvore.inserirFinal(root);
        while (!filaArvore.estaVazia()){
            CelulaArvore aux = (CelulaArvore)filaArvore.removerInicio();
            if(aux.getObj().equals(o)&&aux.getAltura()==altura){
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

    @Override
    public int height() {
        return height;
    }
}