/**
 * Componente Curricular: Módulo Integrado de Programação
 * Autor: Antonio Raian e Milena Melo
 * Data:  19/09/2016
 *
 * Declaro que este código foi elaborado em dupla e não contém nenhum trecho 
 * de código de outro colega ou de outro autor, 
 * tais como provindos de livros e apostilas, e páginas ou documentos 
 * eletrônicos da Internet. Qualquer trecho de código de outra autoria que
 * uma citação para o não a nossa está destacado com  autor e a fonte do
 * código, e estou ciente que estes trechos não serão considerados para fins
 * de avaliação. Alguns trechos do código podem coincidir com de outros
 * colegas pois estes foram discutidos em sessões tutorias.
 */
package br.uefs.repository.util.ipl;

import br.uefs.repository.util.IFila;
/**
 * Através dessa classe é implementada a classe fila.
 * 
 * @author Antonio Raian e Milena Melo
 */
public class Fila implements IFila{

    private Celula primeiro, ultimo;
    private int tam;

    public Fila() {
        primeiro = ultimo = null;
    }
    
    @Override
    public boolean estaVazia() {
        return primeiro == null;
    }

    @Override
    public int obterTamanho() {
        return tam;
    }

    @Override
    public void inserirFinal(Object o) {
        Celula c = new Celula(o);
        c.setProx(null);
        if(estaVazia()){
            primeiro = ultimo = c;
        }else{
            ultimo.setProx(c);
            ultimo = c;
        }
        tam++;
    }

    @Override
    public Object removerInicio() {
        Object c = null;
        if(estaVazia()){
            return c;
        }
        if(primeiro == ultimo){
            c = primeiro.getObj();
            primeiro = null;
            ultimo = null;
        }else{
            c = primeiro.getObj();
            primeiro = primeiro.getProx();
        }
        tam--;
        return c;
    }

    @Override
    public Object recuperarInicio() {
        if(estaVazia()){
            return null;
        }
        return primeiro.getObj();
    }
    
}