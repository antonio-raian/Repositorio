package br.uefs.repository.testes;

import br.uefs.repository.controller.Controller;
import br.uefs.repository.exceptions.*;
import java.io.BufferedReader;
import java.io.FileReader;
import static org.junit.Assert.*;
import java.io.IOException;
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
        ctr.geraArvore("D:\\Desktop\\Respositorio");
        String[] array = {"D:\\Desktop\\Respositorio","ArquivoTeste1.txt","\\Teste2","\\Teste3","ArquivoTeste2.txt","\\Teste2",
                            "\\Teste4","ArquivoTeste3.txt","ArquivoTeste2Teste2.txt","ArquivoTeste4.txt"};
        assertArrayEquals(array, ctr.mostraArvore());
    }
    
    @Test(expected = PastaNaoEncontradaException.class)
    public void testaCriacaoRepositorioNaoExistente() throws CelulaNaoEncontradaException, NaoEhPastaException, PastaNaoEncontradaException{
        ctr.geraArvore("D:\\Desktop\\Respositor");
    }
    
    @Test(expected = NaoEhPastaException.class)
    public void testaCriacaoRepositorioNaoEhPasta() throws CelulaNaoEncontradaException, NaoEhPastaException, PastaNaoEncontradaException{
        ctr.geraArvore("D:\\Desktop\\Respositorio\\ArquivoTeste1.txt");
    }
    
    //--------------------
    //Coloque aq os testes de busca
    //--------------------
    
    @Test
    public void testaCriacaoDeArquivoSecesso() throws NaoEhPastaException, CelulaNaoEncontradaException, PastaNaoEncontradaException, IOException{
        ctr.geraArvore("D:\\Desktop\\Respositorio");
        ctr.geraArquivo("D:\\Desktop\\Respositorio\\Teste2", "D:\\Desktop\\Respositorio\\ArquivoTeste1.txt", 0);
        
        FileReader arq = new FileReader("D:\\Desktop\\Respositorio\\ArquivoTeste1.txt");
        BufferedReader ler = new BufferedReader(arq);
        String[] esperado = {"D:\\Desktop\\Respositorio\\Teste2\\ArquivoTeste2.txt","D:\\Desktop\\Respositorio\\Teste2\\Teste2",
        "D:\\Desktop\\Respositorio\\Teste2\\Teste4","D:\\Desktop\\Respositorio\\Teste2\\Teste2\\ArquivoTeste2Teste2.txt",
        "D:\\Desktop\\Respositorio\\Teste2\\Teste4\\ArquivoTeste4.txt"};
        
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
        ctr.geraArvore("D:\\Desktop\\Respositorio");
        ctr.geraArquivo("D:\\Desktop\\Respositorio\\Teste2", "D:\\Desktop\\Respositor\\ArquivoTeste1.txt", 0);
    }
    
    @Test (expected = PastaNaoEncontradaException.class)
    public void testaCriacaoDeArquivoCaminhoInexisteteNaArvore() throws NaoEhPastaException, PastaNaoEncontradaException, CelulaNaoEncontradaException, IOException{
        ctr.geraArvore("D:\\Desktop\\Respositorio");
        ctr.geraArquivo("D:\\Desktop\\Respositor\\Teste2", "D:\\Desktop\\Respositorio\\ArquivoTeste1.txt", 0);
    }
}
