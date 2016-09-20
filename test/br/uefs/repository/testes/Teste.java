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

package br.uefs.repository.testes;

import br.uefs.repository.controller.Controller;
import br.uefs.repository.exceptions.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
/**
 * Através dessa classe, é feito todos os testes necessários para o melhor funcionamento do projeto criado.
 * 
 * @author Antonio Raian e Milena Melo
 */
public class Teste {
    private Controller ctr; // Chama o controller para verificação dos testes.
    private String path;

    @Before
    public void setUp() {// Instancia o controller para a chamada das funções.
            ctr  = new Controller();
            File f = new File("src/test/Repositorio");
            path = f.getAbsolutePath().replace("\\", "/");
    }
    // Testes fundamentais de cada função do projeto, sendo feita comparações de resultados esperados com os realemnte estão sendo expedidos.
    @Test
    public void testaCriacaoSucessoRepositorioExistente() throws CelulaNaoEncontradaException, NaoEhPastaException, PastaNaoEncontradaException{
        ctr.geraArvore("src/test/Repositorio");
        String[] array = {path,"ArquivoTeste1.txt","/Teste2","/Teste3","ArquivoTeste2.txt","/Teste2",
                            "/Teste4","ArquivoTeste3.txt","ArquivoTeste2Teste2.txt","ArquivoTeste4.txt"};
        assertArrayEquals(array, ctr.mostraArvore());
    }
    
    @Test(expected = PastaNaoEncontradaException.class)
    public void testaCriacaoRepositorioNaoExistente() throws CelulaNaoEncontradaException, NaoEhPastaException, PastaNaoEncontradaException{
        ctr.geraArvore("src/test/Repositor");
    }
    
    @Test(expected = NaoEhPastaException.class)
    public void testaCriacaoRepositorioNaoEhPasta() throws CelulaNaoEncontradaException, NaoEhPastaException, PastaNaoEncontradaException{
        ctr.geraArvore("src/test/Repositorio/ArquivoTeste1.txt");
    }
    
    @Test
    public void testaPesquisaDeArquivoComSucesso() throws ArquivoNaoEncontradoException, NaoEhPastaException, PastaNaoEncontradaException, CelulaNaoEncontradaException{
        ctr.geraArvore("src/test/Repositorio");
        String[] array = ctr.buscaArquivo("ArquivoTeste1.txt", 1);
        String[] str = {path+"/ArquivoTeste1.txt"};
        assertArrayEquals(str, array);
        
    }
    
    @Test
    public void testaPesquisaDeArquivoComNivelZero() throws ArquivoNaoEncontradoException, NaoEhPastaException, PastaNaoEncontradaException, CelulaNaoEncontradaException{
        ctr.geraArvore("src/test/Repositorio");
        String[] string =  ctr.buscaArquivo("ArquivoTeste2.txt", 0);
        String[] str = {path+"/Teste2/ArquivoTeste2.txt"};
        assertArrayEquals(str, string);
    }
    
    @Test(expected = ArquivoNaoEncontradoException.class)
    public void testaPesquisaDeArquivoCNaoEncontrado() throws ArquivoNaoEncontradoException, NaoEhPastaException, PastaNaoEncontradaException, CelulaNaoEncontradaException{
        ctr.geraArvore("src/test/Repositorio");
        ctr.buscaArquivo("Arquivo.txt", 0);
    }
   
    @Test
    public void testaPesquisaDePastaComSucesso() throws PastaNaoEncontradaException, NaoEhPastaException, CelulaNaoEncontradaException{
        ctr.geraArvore("src/test/Repositorio");
        String[] array = ctr.buscaPasta("Teste2", 1);
        String[] str = {path+"/Teste2"};
        assertArrayEquals(str, array);
    }
    
    @Test
    public void testaPesquisaDePastaComNivelZero() throws PastaNaoEncontradaException, NaoEhPastaException, CelulaNaoEncontradaException{
         ctr.geraArvore("src/test/Repositorio");
        String[] array = ctr.buscaPasta("Teste2", 0);
        String[] str = {path+"/Teste2", path+"/Teste2/Teste2"};
        assertArrayEquals(str, array);
    }
    
    @Test(expected = PastaNaoEncontradaException.class)
    public void testaPesquisaDePastaCNaoEncontrado() throws PastaNaoEncontradaException, NaoEhPastaException, CelulaNaoEncontradaException{
        ctr.geraArvore("src/test/Repositorio");
        ctr.buscaPasta("Pasta", 0);
    }
   
    @Test
    public void testaPesquisaPorTipoComSucesso() throws TipoNaoEncontradoException, NaoEhPastaException, PastaNaoEncontradaException, CelulaNaoEncontradaException{
        ctr.geraArvore("src/test/Repositorio");
        String[] array = ctr.buscaTipo("txt", 1);
        String[] str = {path+"/ArquivoTeste1.txt"};
        assertArrayEquals(str, array);
    }
    
     @Test
    public void testaPesquisaPorTipoComNivelZero() throws TipoNaoEncontradoException, NaoEhPastaException, PastaNaoEncontradaException, CelulaNaoEncontradaException{
        ctr.geraArvore("src/test/Repositorio");
        String[] string =  ctr.buscaTipo("txt", 0);
        String[] str = {path+"/ArquivoTeste1.txt", path+"/Teste2/ArquivoTeste2.txt",path+"/Teste3/ArquivoTeste3.txt", 
            path+"/Teste2/Teste2/ArquivoTeste2Teste2.txt",path+"/Teste2/Teste4/ArquivoTeste4.txt"};
        assertArrayEquals(str, string);
    }
    @Test(expected = TipoNaoEncontradoException.class)
    public void testaPesquisaPorTipoNaoEncontrado() throws TipoNaoEncontradoException, NaoEhPastaException, PastaNaoEncontradaException, CelulaNaoEncontradaException{
        ctr.geraArvore("src/test/Repositorio");
        ctr.buscaTipo("mp3", 0);
    }
 
    
    @Test
    public void testaCriacaoDeArquivoSucesso() throws NaoEhPastaException, CelulaNaoEncontradaException, PastaNaoEncontradaException, IOException{
        ctr.geraArvore("src/test/Repositorio");
        ctr.geraArquivo(path+"/Teste2", path+"/ArquivoTeste1.txt", 0);
        
        FileReader arq = new FileReader(path+"/ArquivoTeste1.txt");
        BufferedReader ler = new BufferedReader(arq);
        String[] esperado = {path+"/Teste2/ArquivoTeste2.txt",path+"/Teste2/Teste2",path+"/Teste2/Teste4",
            path+"/Teste2/Teste2/ArquivoTeste2Teste2.txt",path+"/Teste2/Teste4/ArquivoTeste4.txt"};
        
        String[] leitura = new String[esperado.length];
        String leia = ler.readLine();
        int i = 0;
        while(leia!=null){
            leitura[i]=leia;
            leia = ler.readLine();
            i++;
        }
        assertArrayEquals(esperado, leitura);
    }
    
    @Test (expected = IOException.class)
    public void testaCriacaoDeArquivoCaminhoInexisteteParaArquivo() throws NaoEhPastaException, PastaNaoEncontradaException, CelulaNaoEncontradaException, IOException{
        ctr.geraArvore("src/test/Repositorio");
        ctr.geraArquivo(path+"/Teste2", path+"Repositorio/ArquivoTeste1.txt", 0);
    }
    
    @Test (expected = PastaNaoEncontradaException.class)
    public void testaCriacaoDeArquivoCaminhoInexisteteNaArvore() throws NaoEhPastaException, PastaNaoEncontradaException, CelulaNaoEncontradaException, IOException{
        ctr.geraArvore("src/test/Repositorio");
        ctr.geraArquivo(path+"/Teste20", path+"/ArquivoTeste1.txt", 0);
    }
}
