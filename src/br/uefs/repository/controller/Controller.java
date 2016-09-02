/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.repository.controller;

import br.uefs.repository.exceptions.CelulaNaoEncontradoException;
import br.uefs.repository.exceptions.NaoEhPastaException;
import br.uefs.repository.util.Iterador;
import br.uefs.repository.util.ipl.ArvoreGenerica;
import java.io.File;

/**
 *
 * @author Antonio
 */
public class Controller {
    private ArvoreGenerica arvoreRepositorio;

    public Controller() {
        arvoreRepositorio = new ArvoreGenerica();
    }
    
    public void geraArvore (String str) throws CelulaNaoEncontradoException, NaoEhPastaException{
        File f = new File(str);
        arvoreRepositorio.addRoot(str);
        if(f.isDirectory()){
            File[] vF= f.listFiles();
            String pai = str;
            adicionaFilho(vF, pai);
            
        }else{
            throw new NaoEhPastaException();
        }
    }
    
    private void adicionaFilho(File[] filhos, String pai) throws CelulaNaoEncontradoException{
        for(File file:filhos){
            arvoreRepositorio.addSon(file.getName(), pai);
            if(file.isDirectory()){
                String dad = file.getName();
                File[] aux = file.listFiles();
                adicionaFilho(aux, dad);
            }

        }
    }
    
    public String[] mostraArvore(){
        Iterador it = arvoreRepositorio.iterator();
        String[] str = new String[arvoreRepositorio.size()];
        int i=0;
        while(it.temProximo()){
            str[i] = (String) it.obterProximo();
            i++;
        }
        return str;
    }
}
