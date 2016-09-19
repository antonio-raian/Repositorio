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

import br.uefs.repository.util.Elemento;
import br.uefs.repository.util.Iterador;

/**
 * Através dessa classe, é implementa a classe iterador, sendo manipulada na árvore.
 * Iterador Árvore percorre toda a estrutura da árvore.
 * @author Antonio Raian e Milena Melo
 */
public class IteradorArvore implements Iterador{

    private Fila filaArvore = new Fila();
    // Construtor
    public IteradorArvore(CelulaArvore root) {
        filaArvore.inserirFinal(root);
    }
    
    @Override
    public boolean temProximo() {
        return !filaArvore.estaVazia();
    }

    @Override
    public Elemento obterProximo() {
        CelulaArvore c = (CelulaArvore)filaArvore.removerInicio();
        if(c.getFilho()!=null){
            CelulaArvore aux = c.getFilho();
            while(aux!=null){
                filaArvore.inserirFinal(aux);
                aux = aux.getIrmao();
            }
        }
        return c;
    }
    
    @Override
    public String toString() {
        return filaArvore.recuperarInicio().toString();
    }
}
