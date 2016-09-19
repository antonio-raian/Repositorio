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
/**
 * Através dessa classe, é criado uma célula pertencente a árvore.
 * 
 * @author Antonio Raian e Milena Melo
 */
public class CelulaArvore implements Elemento{
    // Atributos necessários, destacando os ramos de pai, filhos e irmãos da árvore, além da altura e o objeto atribuída a cada célula.
    private Object obj;
    private CelulaArvore pai;
    private CelulaArvore filho;
    private CelulaArvore irmao;
    private int altura;

    public CelulaArvore(Object o) {// Construtor de Célula Árvore
        this.obj = o;
    }
    // Geters e Seters
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
    // Método de printar toda célula
    @Override
    public String toString() {
        return obj.toString();
    }
    // Verifica se as células é igual ao objeto passado como parâmetro.
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof CelulaArvore){// Instancia uma célula
            CelulaArvore c = (CelulaArvore) obj;
            
            if(this.obj == c.obj && this.pai == c.pai && this.altura == c.altura){
                return true;
            }
        }
        return false;
    }
}
