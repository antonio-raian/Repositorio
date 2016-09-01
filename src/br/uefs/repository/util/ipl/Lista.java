/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.repository.util.ipl;

import br.uefs.repository.model.Celula;
import br.uefs.repository.util.ILista;
import br.uefs.repository.util.Iterador;


/**
 *
 * @author Antonio
 */
public class Lista implements ILista{

    private int tam = 0;
    private Celula primeiro,ultimo;
    
    @Override
    public boolean estaVazia() {
        return tam == 0 || primeiro == null;
    }

    @Override
    public int obterTamanho() {
        return tam;
    }
@Override
    public void inserirInicio(Object o) { 
        Celula aux = new Celula(o);
        if(estaVazia()){
            primeiro= ultimo = aux; //Faz com que a o primeiro e o ultimo sejam a mesma célula
            primeiro.setProx(ultimo);
        }else{
            //faz a troca do primeiro pela nova célula que está sendo inserida
            aux.setProx(primeiro);
            primeiro = aux;
        }
        tam++; //aumenta o tamanha da lista
    }

    @Override
    public void inserirFinal(Object o) {
        Celula aux = new Celula(o);
        if(estaVazia()){
            primeiro = ultimo = aux; //Faz com que a o primeiro e o ultimo sejam a mesma célula
            primeiro.setProx(null);
        }else{ 
            //faz a troca do ultimo pela nova célula que está sendo inserida
            aux.setProx(null);
            ultimo.setProx(aux);
            ultimo = aux;
        }
        tam++; //aumenta o tamanha da lista
    }

    @Override
    public void inserir(Object o,int index) { //metodo responsavel por inserir uma celula em uma determinada posição
        Celula aux=primeiro, nova = new Celula(o);
        int i = 1;
        if(index==1 || estaVazia()) //verifica se é na primeira posição
            inserirInicio(o);
        if(index>1 && index<tam){
            do{ //percorre a lista até encontrar a posição desejada
                if(i == (index-1)){
                    //insere a celula na posição desejada
                    nova.setProx(aux.getProx());
                    aux.setProx(nova);
                    break;
                }
                aux = aux.getProx();
                i++;
            }while(aux!=null);
        }else if(index==tam){ //verifica se é na ultima posição
            inserirFinal(o);
        }
        tam++;//aumenta o tamanha da lista
    }

    @Override
    public Object removerInicio() { //Remove a cabeça da lista
        Object o = primeiro.getObj();
        if(!estaVazia()){ //verifica se tem celulas
            primeiro = primeiro.getProx(); //Faz o primeiro apontar pro próximo
        }
        tam--;//Diminui o tamanha da lista
        return o; // retorna objeto q foi removido
    }

    @Override
    public Object removerFinal() { //remove o Tail da lista
        Object o = ultimo.getObj();
        if(!estaVazia()){
            Celula c = primeiro,aux = c;
            while(c!=null){ //percorre a lista pra encontrar o penultimo
                if(c.getProx() == null){
                    aux.setProx(null);
                    ultimo = aux; //Faz o ultimo aportar pro penultimo da lista
                }
                aux = c;
                c = c.getProx();
            }
        }
        tam--;//Diminui o tamanha da lista
        return o; // retorna objeto q foi removido
    }

    @Override
    public Object remover(int index){ //Remove em uma determinada posição da lista
        Celula aux = primeiro;
        Object o = null;
        
        if(!estaVazia()){
            if(index==1) //verifica se não é no inicio da lista
                o = removerInicio();
            else if(index>0 && index<tam){
                int i = 1;
                do{ //percorre pra encontrar a posição
                    if(index-1== i){
                        o = aux.getProx().getObj();
                        aux.setProx(aux.getProx().getProx()); //remove da lista subistituindo a referencia da celula anterior
                        tam--;//Diminui o tamanho da lista
                        break;
                    }
                    i++;
                    aux = aux.getProx();
                }while(aux!=null);
            }else if(index == tam) //verifica se não é no final da lista
                o = removerFinal();
        }
        
        return o; // retorna objeto q foi removido
    }
    
    @Override
    public Object recuperar(int index) { //encontra um item em determina da posição
        //Iterador it = iterador();
        Celula aux = primeiro;
        Object o = null;
        int i = 1;
        do{ //percorre a lista pra encontrar a posição
            if(!estaVazia()){
                if(index>0 && index<=tam){
                    if(index == i){ //verifica se a posição atual é a mesma posição solicitada
                        o = aux.getObj(); //retorna o item encontrado
                        break;
                    }
                    //aux.getObj().toString();
                    i++;
                    aux = aux.getProx();
                }else
                    return null;
            }
        }while(aux!=null);
        
        return o; 
    }

    @Override
    public Iterador iterador() { //Iterador, responsável por retornar a cabeça da lista
        return new IteradorLista(primeiro);
    }
    
    public void swap(int origem, int destino){ //metodo q muda celulas de lugar, indo da origem pro destino
        Celula aux, aux2; //auxiliares para a posição anterior às selecionadas
        Celula de, para; //celulas para troca; de = celula que vai mudar, para=celula que vai ficar a frente de "de" na troca;
        if(!estaVazia()&& origem>destino){ //verifica se a lista não esta vazia e se a origem é maior q o destino
            de = getCelula(origem); //encontra a celula q vai mudar de posiçao;
            para = getCelula(destino); //encontra a celula q vai dar o lugar pra outra;
            aux = getCelula(origem-1); //encontra a celula anterior a origem
            aux2 = getCelula(destino-1);//encontra a celula anterior ao destino;
            if(destino==1){
                aux.setProx(de.getProx());
                de.setProx(para);
                primeiro = de;
            }else if(destino>0 && destino<tam) {
                aux.setProx(de.getProx());
                aux2.setProx(de);
                de.setProx(para);
                
            }
            if(origem==tam){
                ultimo = para;
            }
        }
    }
    private Celula getCelula(int index){ //Metodo q econtra a celula dada um indice
        Celula aux = primeiro;
        if(!estaVazia()){
            if(index>0 && index<=tam){
                int i = 1;
                do{ //percorre pra encontrar a posição
                    if(index== i){
                        break;
                    }
                    i++;
                    aux = aux.getProx();
                }while(aux!=null);
            }
        }
        
        return aux; // retorna objeto q encontrado;
    }
}
