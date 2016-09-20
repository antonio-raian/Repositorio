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

import br.uefs.repository.exceptions.CelulaNaoEncontradaException;
import br.uefs.repository.util.Elemento;
import br.uefs.repository.util.IGenericTree;
import br.uefs.repository.util.Iterador;

/**
 * Através dessa classe, é implementado a classe IGenericTree para a obtenção da classe genérica da árvore
 * Com isso, é possível manipular as células, o root da árvore para a criação, verificação e visualização da mesma.
 * 
 * @author Antonio Raian e Milena Melo
 */

public class ArvoreGenerica implements IGenericTree{

    private CelulaArvore root;
    private int tam;
    private int height = 0;
    
    // Método de composição da árvore, sendo organizado o root, os pais e filhos pertencentes a esta.
    @Override
    public void addSon(Object obj, Object pai, int altura) throws CelulaNaoEncontradaException{
        CelulaArvore filho = new CelulaArvore(obj);
        if(pai==null){// Se o pai for nulo, seta, nesta condição, os filhos como nulos e a altura
                      // tendo o root como filho, para a criação de um novo elemnto na árvore.
            filho.setIrmao(null);
            filho.setPai(null);
            filho.setAltura(altura);
            root = filho;
        }else{// Caso já exista um pai na árvore, este é verificado através do método private "encontra", enviando também sua altura.
              // A partir daí, é atribuiído a uma célula auxiliar utilizado como parâmetro para inserção dos elementos na árvore.
            CelulaArvore aux = (CelulaArvore)encontra(pai,(altura-1));
            if(aux == null){// Caso não seja encontrado a célula, é emitido uma exceção.
                throw new CelulaNaoEncontradaException("error!");
            }
            // Atribui-se as células a cada ramo da árvore.
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
    // Este método recebe elemento e, a partir da célula inserida através do casta, é recuperado o pai desta célula.
    // Neste momento, é concatenado os pais do caminho inserido a partir de elemnto para retornar o caminho, como string, do elemento 
    // esperado.
    public String caminho(Elemento e){
        String str ="";
        Pilha pilhaCaminho = new Pilha();// É criado uma pilha de caminhos para a manipulação da mesma.
        CelulaArvore cel = (CelulaArvore) e;
        pilhaCaminho.inserirTopo(cel);
        CelulaArvore aux = cel.getPai();
        while(aux!=null){
            pilhaCaminho.inserirTopo(aux);
            aux=aux.getPai();
        }
        // Enquanto estiver no laço, os caminhos vão sendo concatenados a partir da pilha criada.
        while(!pilhaCaminho.estaVazia()){
            if(pilhaCaminho.recuperarTopo().toString().contains("/")){
                str+=pilhaCaminho.removerTopo().toString();
            }else{
                str+="/"+pilhaCaminho.removerTopo().toString();
            }
        }
        return str;
    }
    
    @Override
    public int size() {
        return tam;
    }
    // Iterador percorre toda a árvore, tendo como parâmetro o root.
    @Override
    public Iterador iterator() {
        return new IteradorArvore(root);
    }
    // Método insrido para mapear toda a árvore, retornando um iterador.
    public Iterador mapa(Elemento e){
        CelulaArvore aux = (CelulaArvore) encontra(e.getObj(), e.getAltura());
        return new IteradorArvore(aux);
    }
    // Método "encontra" serve para auxiliar outros métodos desta classe.
    // Através da inserção do caminho e sua altura, este retorna o objeto desejado a ser 
    // encontrado na árvore, manejando a partir de uma fila.
    private Object encontra(Object o, int altura){
        Fila filaArvore = new Fila();
        filaArvore.inserirFinal(root);
        while (!filaArvore.estaVazia()){// Percorre a fila até encontrar o objeto
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
        return null;// Se não encontrar, retorna nulo.
    }
    // Retorna tamanho.
    @Override
    public int height() {
        return height;
    }
}