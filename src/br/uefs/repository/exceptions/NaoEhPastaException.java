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
package br.uefs.repository.exceptions;
/**
 * Através dessa classe, é expedido a exceção "NaoEhPastaException", caso o caminho indicado pelo usuário
 * não seja um diretório.
 * 
 * @author Antonio Raian e Milena Melo
 */

public class NaoEhPastaException extends Exception{
    private static final long serialVersionUID = 1L;

    public NaoEhPastaException() {
        super();
    }
    
    public NaoEhPastaException(String str){
        super(str);
    }
    
    public NaoEhPastaException(String str, Throwable motivo){
        super(str, motivo);
    }
    
}
