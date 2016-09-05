/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.repository.controller;

import br.uefs.repository.exceptions.ArquivoNaoEncontradoException;
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
    private String caminho;
    
    public Controller() {
        arvoreRepositorio = new ArvoreGenerica();
    }
    
    public void geraArvore (String str) throws CelulaNaoEncontradoException, NaoEhPastaException{
        File f = new File(str);
        caminho = str;
        arvoreRepositorio.addSon(str, null);
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
    public String[] buscaArquivo(String nome, int nivel) throws ArquivoNaoEncontradoException, CelulaNaoEncontradoException{
        Iterador it = arvoreRepositorio.iterator();
        String[] string = new String[arvoreRepositorio.size()];
        int condicao = 0;
        int i=0;
        File file = new File(caminho+"\\"+nome);
        if(file.isFile()){
            if(nivel == 0){
               return mostraArvore();
            }
            else{
                    while(it.temProximo()){
                        Object obj = (Object)it.obterProximo();
                        int altura = arvoreRepositorio.height(obj);
                        if(altura<=nivel){ 
                            string[i] = (String) obj;
                            if(nivel == altura){  
                                  if(nome.equals(obj)){
                                         condicao = 1;
                                  }
                            }i++;

                       }
                   }
                   if(condicao == 1)
                        return string;
                   throw new ArquivoNaoEncontradoException();
           }
       }
       throw new ArquivoNaoEncontradoException();
    }
    
    
    public String[] buscaPasta(String nome, int nivel) throws ArquivoNaoEncontradoException, CelulaNaoEncontradoException{
        Iterador it = arvoreRepositorio.iterator();
        String[] string = new String[arvoreRepositorio.size()];
        int condicao = 0;
        int i=0;
        File file = new File(caminho+"\\"+nome);
        if(file.isDirectory()){
            if(nivel == 0){
               return mostraArvore();
            }
            else{
                    while(it.temProximo()){
                        Object obj = (Object)it.obterProximo();
                        int altura = arvoreRepositorio.height(obj);
                        if(altura<=nivel){ 
                            string[i] = (String) obj;
                            if(nivel == altura){  
                                  if(nome.equals(obj)){
                                         condicao = 1;
                                  }
                            }i++;

                       }
                   }
                   if(condicao == 1)
                        return string;
                   throw new ArquivoNaoEncontradoException();
           }
       }
       throw new ArquivoNaoEncontradoException();
    }
  }