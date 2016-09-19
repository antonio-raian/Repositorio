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
/**
 * Através dessa classe, é criado uma célula.
 * 
 * @author Antonio Raian e Milena Melo
 */

public class Celula {
    private Object obj;
    private Celula prox; // referencia para a próxima celula
    private Celula ant; // referencia para a celula anterior

    public Celula(Object obj) {// Construtor de Célula.
        this.obj = obj;
    }
    //Geters e Seters necessários.
    public Object getObj() {
        return obj;
    }

    public Celula getProx() {
        return prox;
    }

    public void setProx(Celula prox) {
        this.prox = prox;
    }

    public Celula getAnt() {// Obtém célula anterior
        return ant;
    }

    public void setAnt(Celula ant) {
        this.ant = ant;
    }

    @Override
    public String toString() {
        return obj.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}
