package br.uefs.repository.testes;

import br.uefs.repository.controller.Controller;
import br.uefs.repository.exceptions.*;
import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

public class Teste {
    private Controller ctr;

    @Before
    public void setUp() {
            ctr  = new Controller();
    }

    @Test
    public void testaCriacao() throws CelulaNaoEncontradoException, NaoEhPastaException{
        ctr.geraArvore("D:\\Desktop\\Teste");
        String[] array = {"D:\\Desktop\\Teste","PorraDeNada.txt","Teste2","Teste3",
            "Carai.txt","teste4","Nada.txt","Pensando.txt"};
        assertArrayEquals(array, ctr.mostraArvore());
    }
}
