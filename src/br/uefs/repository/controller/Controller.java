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

package br.uefs.repository.controller;
/**
 * Através dessa classe, é feito todo controle de chamadas de funções a partir dos dados recebidos do menu,
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

// Inicialização da classe
public class Controller {
    private ArvoreGenerica arvoreRepositorio;
    
    public Controller() { //Construtor da classe Controller
        arvoreRepositorio = new ArvoreGenerica();
    }
    
    // Método ao qual recebe o caminho (String str) do diretório onde, de acordo os componentes deste diretório, 
    // gerará um conjunto de elementos estruturada numa árvore para melhor organização dos objetos.
    public void geraArvore (String str) throws NaoEhPastaException, PastaNaoEncontradaException, CelulaNaoEncontradaException{
        File f = new File(str);
        if(f.exists()){ // Verifica se o caminho do diretório existe
            int altura = 0;
            arvoreRepositorio.addSon(str, null,altura);    
            
            if(f.isDirectory()){ // Verifica se o caminho é um diretório
                altura++;
                File[] vF= f.listFiles();
                String pai = str;
                adicionaFilho(vF, pai, altura);

            }else{ // Caso não seja diretório, o programa emite um "exception"
                throw new NaoEhPastaException();
            }
        }else{// Caso não seja encontrado o caminho do diretório, o programa emite um "exception"
            throw new PastaNaoEncontradaException();
        }
    }
    // Método de auxílio para a construção da árvore.
    // Mérodo utilizado de forma recursiva. 
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
    // Método ao qual percorre toda árvore, imprimindo cada objeto contido nela.
    public String[] mostraArvore(){
        Iterador it = arvoreRepositorio.iterator();
        String[] str = new String[arvoreRepositorio.size()];
        int i=0;
        while(it.temProximo()){ // Verifica, através do iterador da árvore, se tem próximo e percorre num laço while.
            str[i] = (String) it.obterProximo().toString();
            i++;
        }
        return str;
    }
    // Busca Arquivo procura o arquivo pelo nome deste, digitado pelo usuário.
    // Além disso, o usuáio insere a profundidade de verificação dos elementos na árvore, para, desta forma,
    // poder recuperar o arquivo de acordo o nível indicado.
    // Este método retorna o caminho do arquivo desejado.
    public String[] buscaArquivo(String nome, int nivel) throws ArquivoNaoEncontradoException{
        Iterador it = arvoreRepositorio.iterator();//Iterador para percorrer toda a arvore 
        String[] string = new String[arvoreRepositorio.size()];
        Elemento[] aux = new Elemento[arvoreRepositorio.size()];
        int i = 0;
        if(nivel==0 || nivel>=arvoreRepositorio.height()){ // Se o nível for 0, o iterador irá percorrer de forma 
                                                          //  mais abrangente na árvore
            while(it.temProximo()){
                Elemento e = (Elemento)it.obterProximo();
                if(e.getObj().equals(nome)){// Compara o nome do arquivo inserido pelo usuário com os dos objetos encontrados na árvore.
                    aux[i] = e;// Se encontrado, este vai sendo colocado numa array para formar o caminho final do arquivo.
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
            while(aux[i]!=null){// O array preenchido é enviado para árvoreRepositório no intuito de pegar os pais 
                                // deste arquivo para compor o caminho do mesmo.
                string[i]=arvoreRepositorio.caminho(aux[i]);
                i++;
            }

            return string;
        }else{// Caso o arquivo não for encontrado, é lançado um exception de "Arquivo Nao Encontrado"
            throw new ArquivoNaoEncontradoException();
        }
    }
    // Busca Pasta procura o diretório pelo nome deste, digitado pelo usuário.
    // Além disso, o usuáio insere a profundidade de verificação dos elementos na árvore, para, desta forma,
    // poder recuperar de acordo o nível indicado.
    // Este método retorna o caminho da pasta desejada. 
    public String[] buscaPasta(String nome, int nivel) throws PastaNaoEncontradaException{
        Iterador it = arvoreRepositorio.iterator();//Iterador para percorrer toda a arvore 
        String[] string = new String[arvoreRepositorio.size()];// Inicialização dos arrays com o tamnho total que possuem
        Elemento[] aux = new Elemento[arvoreRepositorio.size()];
        int i = 0;
        // Percorre a árvore guardando os dados necessários para a composição do caminho, quando encontrado o diretório.
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
                if(e.getObj().equals("\\"+nome)&& e.getAltura()<=nivel){// É inserido duas barras para diferenciar pastas de arquivos
                                                                        // no momento da geração da árvore                                         
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
        }else{// Caso não encontrada a pasta, é lançado  um exception de: "Pasta Nao Encontrada".
            throw new PastaNaoEncontradaException();
        }
    }
    // Busca Pasta procura todos os arquivos com o tipo de extensão determinada pelo usuário.
    // Além disso, o usuáio insere a profundidade de verificação dos elementos na árvore, para, desta forma,
    // poder recuperar a quantidade os elementos(caminhos)  de acordo o nível indicado.
    // Este método retorna o caminho dos arquivos com o tipo de extensão desejada. 
    public String[] buscaTipo(String tipo, int nivel) throws TipoNaoEncontradoException{
        Iterador it = arvoreRepositorio.iterator();// Iterador para percorrer toda a arvore 
        String[] string = new String[arvoreRepositorio.size()];
        Elemento[] aux = new Elemento[arvoreRepositorio.size()];
        int i = 0;
        if(nivel==0 || nivel>=arvoreRepositorio.height()){
            while(it.temProximo()){
                Elemento e = (Elemento)it.obterProximo();
                if(!e.getObj().toString().startsWith("\\"))// Este método verifica se o elemnto inicia com os caracteres inseridos como parâmetro
                    if(e.getObj().toString().contains("."+tipo)){// Logo depois, verifica se contém o ponto, tendo como parâmetro de distinção 
                                                                 // de tipo para outros tipos de arquivo ou pastas.
                        aux[i] = e;
                        i++;
                    }
            }
        }else{
            while(it.temProximo()){
                Elemento e = (Elemento)it.obterProximo();
                if(!e.getObj().toString().startsWith("\\"))// Caso inicie com "\\" e contenha o "." precedido do tipo da extensão do arquivo,
                                                           // é igualado e verificado a profundidade para ser posto no array auxiliar.
                    if(e.getObj().toString().contains("."+tipo)&& e.getAltura()==nivel){
                        aux[i] = e;
                        i++;
                    }
            }
        }
        if(aux[0]!=null){
            i=0;
            while(aux[i]!=null){// Insere no array ao qual envia como parâmetro para ordenação do array de caminho final do elementos
                string[i]=arvoreRepositorio.caminho(aux[i]);
                i++;
            }

            return string;
        }else{// Caso não for encontrado nenhum arquivo com o tipo inserido, o programa envia um exception de "Tipo não encontrado".
            throw new TipoNaoEncontradoException();
        }
    }
    // O método "Gera Arquivo" cria uma arquivo com todos os caminhos de arquivos e pastas pertencentes a um diretório
    // informado pelo usuário.
    // Além disso, tabém será informado pelo usuário a profundidade da árvore a qual ele deseja receber os elementos até ali compostos.
    // Desta forma, ele receberá a quantidade de caminhos de arquivos e pastas levando em consideração o nível de verificação destes.
    public void geraArquivo(String diretorio, String arquivo, int nivel) throws PastaNaoEncontradaException, IOException{
        String replace = diretorio.replace("\\", "/");// Replace substitui os "\\" por "/" para verificação do diretório.
        String [] str = replace.split("/");// Após a substituição, é feito o split para repartir cada elemento num vetor de strings.
        String [] caminhos = new String[arvoreRepositorio.size()];
        Iterador it = arvoreRepositorio.iterator();
        Elemento e = null;
        // É feito a verificação dos caminhos no diretório informado, para que estes pertençam a pasta inserida pelo usuário.
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