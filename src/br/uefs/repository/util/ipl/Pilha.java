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

import br.uefs.repository.util.IPilha;
/**
 * Através dessa classe é implementada a classe pilha.
 * 
 * @author Antonio Raian e Milena Melo
 */
public class Pilha implements IPilha{
    
    private Celula topo;
    private int tam =0;
    
    @Override
    public int obterTamanho() {
        return tam;
    }

    @Override
    public boolean estaVazia() {
        return topo==null;
    }

    @Override
    public Object removerTopo() {
        Object o = null;
        if(!estaVazia()){
            o = topo.getObj();
            topo = topo.getAnt();
        }
        tam--;
        return o;
    }

    @Override
    public void inserirTopo(Object obj) {
        Celula nova = new Celula(obj);
        nova.setAnt(topo);
        topo = nova;
        tam++;
    }

    @Override
    public Object recuperarTopo() {
        return topo.getObj();
    }
}