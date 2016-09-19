/**
 * Componente Curricular: Módulo Integrado de Programação
 * Autor: Antonio Raian e Milena Melo
 * Data:  19/09/2016
 *
 * Declaro que este código foi elaborado em dupla e não contém nenhum trecho 
 * de código de outro colega ou de outro autor, 
 * tais como provindos de livros e apostilas, e páginas ou documentos 
 * eletrônicos da Internet. Qualquer trecho de código de outra autoria que
 * uma citação para o  não a nossa está destacado com  autor e a fonte do
 * código, e estou ciente que estes trechos não serão considerados para fins
 * de avaliação. Alguns trechos do código podem coincidir com de outros
 * colegas pois estes foram discutidos em sessões tutorias.
 */

package br.uefs.repository.controller;
/**
 * Atraves dessa classe, é feito todo controle de chamadas de funções através dos dados recebidos do menu
 * com objetivo de integrar as partes internas do programa com a de fácil acesso ao usuário.
 * Desta forma, o controlador maneja todas as interações entre usuário e programador, com foco em obter-se o resultado esperado.
 * 
 * @author Antonio Raian e Milena Melo
 */

//Importação das classes utilizadas, incluindo os Exceptions a serem utilizadas nos métodos.
import br.uefs.repository.exceptions.ArquivoNaoEncontradoException;
import br.uefs.repository.exceptions.CelulaNaoEncontradaException;
import br.uefs.repository.exceptions.NaoEhPastaException;
import br.uefs.repository.exceptions.PastaNaoEncontradaException;
import br.uefs.repository.exceptions.TipoNaoEncontradoException;
import br.uefs.repository.util.Elemento;
import br.uefs.repository.util.Iterador;
import br.uefs.repository.util.ipl.ArvoreGenerica;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Antonio
 */
public class Controller {
    private ArvoreGenerica arvoreRepositorio;
    
    public Controller() {
        arvoreRepositorio = new ArvoreGenerica();
    }
    
    public void geraArvore (String str) throws NaoEhPastaException, PastaNaoEncontradaException, CelulaNaoEncontradaException{
        File f = new File(str);
        if(f.exists()){
            int altura = 0;
            arvoreRepositorio.addSon(str, null,altura);    
            
            if(f.isDirectory()){
                altura++;
                File[] vF= f.listFiles();
                String pai = str;
                adicionaFilho(vF, pai, altura);

            }else{
                throw new NaoEhPastaException();
            }
        }else{
            throw new PastaNaoEncontradaException();
        }
    }
    
    private void adicionaFilho(File[] filhos, String pai, int altura) throws CelulaNaoEncontradaException{
        for(File file:filhos){
            if(file.isDirectory()){
                arvoreRepositorio.addSon("\\"+file.getName(), pai, altura);
                altura++;
                String dad = "\\"+file.getName();
                File[] aux = file.listFiles();
                adicionaFilho(aux, dad, altura);
                altura--;
            }else{
                arvoreRepositorio.addSon(file.getName(), pai, altura);
            }
        }
    }
    
    public String[] mostraArvore(){
        Iterador it = arvoreRepositorio.iterator();
        String[] str = new String[arvoreRepositorio.size()];
        int i=0;
        while(it.temProximo()){
            str[i] = (String) it.obterProximo().toString();
            i++;
        }
        return str;
    }
    
    public String[] buscaArquivo(String nome, int nivel) throws ArquivoNaoEncontradoException{
        Iterador it = arvoreRepositorio.iterator();//Iterador para percorrer toda a arvore 
        String[] string = new String[arvoreRepositorio.size()];
        Elemento[] aux = new Elemento[arvoreRepositorio.size()];
        int i = 0;
        if(nivel==0 || nivel>=arvoreRepositorio.height()){
            while(it.temProximo()){
                Elemento e = (Elemento)it.obterProximo();
                if(e.getObj().equals(nome)){
                    aux[i] = e;
                    i++;
                }
            }
        }else{
            while(it.temProximo()){
                Elemento e = (Elemento)it.obterProximo();
                if(e.getObj().equals(nome)&& e.getAltura()<=nivel){
                    aux[i] = e;
                    i++;
                }
            }
        }
        if(aux[0]!=null){
            i=0;
            while(aux[i]!=null){
                string[i]=arvoreRepositorio.caminho(aux[i]);
                i++;
            }

            return string;
        }else{
            throw new ArquivoNaoEncontradoException();
        }
    }
    
    public String[] buscaPasta(String nome, int nivel) throws PastaNaoEncontradaException{
        Iterador it = arvoreRepositorio.iterator();//Iterador para percorrer toda a arvore 
        String[] string = new String[arvoreRepositorio.size()];
        Elemento[] aux = new Elemento[arvoreRepositorio.size()];
        int i = 0;
        if(nivel==0 || nivel>=arvoreRepositorio.height()){
            while(it.temProximo()){
                Elemento e = (Elemento)it.obterProximo();
                if(e.getObj().equals("\\"+nome)){
                    aux[i] = e;
                    i++;
                }
            }
        }else{
            while(it.temProximo()){
                Elemento e = (Elemento)it.obterProximo();
                if(e.getObj().equals("\\"+nome)&& e.getAltura()<=nivel){
                    aux[i] = e;
                    i++;
                }
            }
        }
        if(aux[0]!=null){
            i=0;
            while(aux[i]!=null){
                string[i]=arvoreRepositorio.caminho(aux[i]);
                i++;
            }

            return string;
        }else{
            throw new PastaNaoEncontradaException();
        }
    }
     
    public String[] buscaTipo(String tipo, int nivel) throws TipoNaoEncontradoException{
        Iterador it = arvoreRepositorio.iterator();//Iterador para percorrer toda a arvore 
        String[] string = new String[arvoreRepositorio.size()];
        Elemento[] aux = new Elemento[arvoreRepositorio.size()];
        int i = 0;
        if(nivel==0 || nivel>=arvoreRepositorio.height()){
            while(it.temProximo()){
                Elemento e = (Elemento)it.obterProximo();
                if(!e.getObj().toString().startsWith("\\"))
                    if(e.getObj().toString().contains("."+tipo)){
                        aux[i] = e;
                        i++;
                    }
            }
        }else{
            while(it.temProximo()){
                Elemento e = (Elemento)it.obterProximo();
                if(!e.getObj().toString().startsWith("\\"))
                    if(e.getObj().toString().contains("."+tipo)&& e.getAltura()==nivel){
                        aux[i] = e;
                        i++;
                    }
            }
        }
        if(aux[0]!=null){
            i=0;
            while(aux[i]!=null){
                string[i]=arvoreRepositorio.caminho(aux[i]);
                i++;
            }

            return string;
        }else{
            throw new TipoNaoEncontradoException();
        }
    }
  
    public void geraArquivo(String diretorio, String arquivo, int nivel) throws PastaNaoEncontradaException, IOException{
        String replace = diretorio.replace("\\", "/");
        String [] str = replace.split("/");
        String [] caminhos = new String[arvoreRepositorio.size()];
        Iterador it = arvoreRepositorio.iterator();
        Elemento e = null;
        
        while(it.temProximo()){
            Elemento aux = (Elemento) it.obterProximo();
            if(arvoreRepositorio.caminho(aux).equals(diretorio)){
                e = aux;
                break;
            }
        }
        if(e!=null){
            Iterador mapa = arvoreRepositorio.mapa(e);
            Elemento aux, primeiro;
            int i = 0;
            primeiro = (Elemento) mapa.obterProximo();
            while(mapa.temProximo()){
                aux = (Elemento) mapa.obterProximo();
                if(nivel==0 || nivel>=arvoreRepositorio.size()){
                    caminhos[i] = arvoreRepositorio.caminho(aux);
                    i++;
                }else if(aux.getAltura()<=(nivel+primeiro.getAltura())){
                    caminhos[i] = arvoreRepositorio.caminho(aux);
                    i++;
                }
            }

            i=0;
            File f = new File(arquivo);
            if(f.exists()){
                if(f.canWrite()){
                    FileWriter arq = new FileWriter(f);
                    PrintWriter escrever = new PrintWriter(arq);

                    while(caminhos[i]!=null){
                        escrever.printf("%s%n",caminhos[i]);
                        i++;
                    }
                    arq.close();
                }
            }else{
                f.createNewFile();
                FileWriter arq = new FileWriter(f);
                PrintWriter escrever = new PrintWriter(arq);

                while(caminhos[i]!=null){
                    escrever.printf("%s %n",caminhos[i]);
                    i++;
                }
                arq.close();
            }
        }else{
            throw new PastaNaoEncontradaException();
        }
        
    }
}