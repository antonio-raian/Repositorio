package br.uefs.repository.testes;

import br.uefs.repository.controller.Controller;
import br.uefs.repository.exceptions.*;
import java.io.BufferedReader;
import java.io.FileReader;
import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class Teste {
    private Controller ctr;

    @Before
    public void setUp() {
            ctr  = new Controller();
    }

    @Test
    public void testaCriacaoSucessoRepositorioExistente() throws CelulaNaoEncontradaException, NaoEhPastaException, PastaNaoEncontradaException{
        ctr.geraArvore("D:\\Documentos\\Repositorio");
        String[] array = {"D:\\Documentos\\Repositorio","ArquivoTeste1.txt","\\Teste2","\\Teste3","ArquivoTeste2.txt","\\Teste2",
                            "\\Teste4","ArquivoTeste3.txt","ArquivoTeste2Teste2.txt","ArquivoTeste4.txt"};
        assertArrayEquals(array, ctr.mostraArvore());
    }
    
    @Test(expected = PastaNaoEncontradaException.class)
    public void testaCriacaoRepositorioNaoExistente() throws CelulaNaoEncontradaException, NaoEhPastaException, PastaNaoEncontradaException{
        ctr.geraArvore("D:\\Documentos\\Repositor");
    }
    
    @Test(expected = NaoEhPastaException.class)
    public void testaCriacaoRepositorioNaoEhPasta() throws CelulaNaoEncontradaException, NaoEhPastaException, PastaNaoEncontradaException{
        ctr.geraArvore("D:\\Documentos\\Repositorio\\ArquivoTeste1.txt");
    }
    
    @Test
    public void testaPesquisaDeArquivoComSucesso() throws ArquivoNaoEncontradoException, NaoEhPastaException, PastaNaoEncontradaException, CelulaNaoEncontradaException{
        ctr.geraArvore("D:\\Documentos");
        String[] array = ctr.buscaArquivo("ArquivoTeste1.txt", 2);
        String str = "D:\\Documentos\\Repositorio\\ArquivoTeste1.txt";
        assertEquals(str, array[0] );
        
    }
    
    @Test
    public void testaPesquisaDeArquivoComNivelZero() throws ArquivoNaoEncontradoException, NaoEhPastaException, PastaNaoEncontradaException, CelulaNaoEncontradaException{
        ctr.geraArvore("D:\\Documentos");
        String[] string =  ctr.buscaArquivo("ArquivoTeste2.txt", 0);
        String[] str = {"D:\\Documentos\\Repositorio\\Teste2\\ArquivoTeste2.txt"};
        assertEquals(str[0], string[0]);
    }
    
    @Test(expected = ArquivoNaoEncontradoException.class)
    public void testaPesquisaDeArquivoCNaoEncontrado() throws ArquivoNaoEncontradoException, NaoEhPastaException, PastaNaoEncontradaException, CelulaNaoEncontradaException{
        ctr.geraArvore("D:\\Documentos");
        ctr.buscaArquivo("Arquivo.txt", 0);
    }
   
    @Test
    public void testaPesquisaDePastaComSucesso() throws PastaNaoEncontradaException, NaoEhPastaException, CelulaNaoEncontradaException{
        ctr.geraArvore("D:\\Documentos");
        String[] array = ctr.buscaPasta("Teste2", 2);
        String str = "D:\\Documentos\\Repositorio\\Teste2";
        assertEquals(str, array[0]);
    }
    
    @Test
    public void testaPesquisaDePastaComNivelZero() throws PastaNaoEncontradaException, NaoEhPastaException, CelulaNaoEncontradaException{
         ctr.geraArvore("D:\\Documentos");
        String[] array = ctr.buscaPasta("Teste2", 0);
        String[] str = {"D:\\Documentos\\Repositorio\\Teste2", "D:\\Documentos\\Repositorio\\Teste2\\Teste2"};
        assertEquals(str[0], array[0]);
        assertEquals(str[1], array[1]);
    }
    
    @Test(expected = PastaNaoEncontradaException.class)
    public void testaPesquisaDePastaCNaoEncontrado() throws PastaNaoEncontradaException, NaoEhPastaException, CelulaNaoEncontradaException{
        ctr.geraArvore("D:\\Documentos");
        ctr.buscaPasta("Pasta", 0);
    }
   
    @Test
    public void testaPesquisaPorTipoComSucesso() throws TipoNaoEncontradoException, NaoEhPastaException, PastaNaoEncontradaException, CelulaNaoEncontradaException{
        ctr.geraArvore("D:\\Documentos");
        String[] array = ctr.buscaTipo("txt", 2);
        String[] str = {"D:\\Documentos\\Repositorio\\ArquivoTeste1.txt"};
        assertEquals(str[0], array[0]);
    }
    
     @Test
    public void testaPesquisaPorTipoComNivelZero() throws TipoNaoEncontradoException, NaoEhPastaException, PastaNaoEncontradaException, CelulaNaoEncontradaException{
        ctr.geraArvore("D:\\Documentos");
        String[] string =  ctr.buscaTipo("txt", 0);
        String[] str = {"D:\\Documentos\\Repositorio\\ArquivoTeste1.txt", "D:\\Documentos\\Repositorio\\Teste2\\ArquivoTeste2.txt",
                        "D:\\Documentos\\Repositorio\\Teste3\\ArquivoTeste3.txt", "D:\\Documentos\\Repositorio\\Teste2\\Teste2\\ArquivoTeste2Teste2.txt",
                        "D:\\Documentos\\Repositorio\\Teste2\\Teste4\\ArquivoTeste4.txt"};
        assertEquals(str[0], string[0]);
        assertEquals(str[1], string[1]);
        assertEquals(str[2], string[2]);
        assertEquals(str[3], string[3]);
        assertEquals(str[4], string[4]);
    }
    @Test(expected = TipoNaoEncontradoException.class)
    public void testaPesquisaPorTipoNaoEncontrado() throws TipoNaoEncontradoException, NaoEhPastaException, PastaNaoEncontradaException, CelulaNaoEncontradaException{
        ctr.geraArvore("D:\\Documentos");
        ctr.buscaTipo("mp3", 0);
    }
 
    
    @Test
    public void testaCriacaoDeArquivoSucesso() throws NaoEhPastaException, CelulaNaoEncontradaException, PastaNaoEncontradaException, IOException{
        ctr.geraArvore("D:\\Documentos\\Repositorio");
        ctr.geraArquivo("D:\\Documentos\\Repositorio\\Teste2", "D:\\Documentos\\Repositorio\\ArquivoTeste1.txt", 0);
        
        FileReader arq = new FileReader("D:\\Documentos\\Repositorio\\ArquivoTeste1.txt");
        BufferedReader ler = new BufferedReader(arq);
        String[] esperado = {"D:\\Documentos\\Repositorio\\Teste2\\ArquivoTeste2.txt","D:\\Documentos\\Repositorio\\Teste2\\Teste2",
        "D:\\Documentos\\Repositorio\\Teste2\\Teste4","D:\\Documentos\\Repositorio\\Teste2\\Teste2\\ArquivoTeste2Teste2.txt",
        "D:\\Documentos\\Repositorio\\Teste2\\Teste4\\ArquivoTeste4.txt"};
        
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
        ctr.geraArvore("D:\\Documentos\\Repositorio");
        ctr.geraArquivo("D:\\Documentos\\Repositorio\\Teste2", "D:\\Desktop\\Repositor\\ArquivoTeste1.txt", 0);
    }
    
    @Test (expected = PastaNaoEncontradaException.class)
    public void testaCriacaoDeArquivoCaminhoInexisteteNaArvore() throws NaoEhPastaException, PastaNaoEncontradaException, CelulaNaoEncontradaException, IOException{
        ctr.geraArvore("D:\\Desktop\\Repositorio");
        ctr.geraArquivo("D:\\Desktop\\Repositor\\Teste2", "D:\\Desktop\\Repositorio\\ArquivoTeste1.txt", 0);
    }
}
