/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.repository.view;

import br.uefs.repository.controller.Controller;
import br.uefs.repository.exceptions.ArquivoNaoEncontradoException;
import br.uefs.repository.exceptions.CelulaNaoEncontradoException;
import br.uefs.repository.exceptions.NaoEhPastaException;
import br.uefs.repository.util.Console;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Antonio
 */
public class Menu {
    public static void main(String[] args) throws IOException, ArquivoNaoEncontradoException, CelulaNaoEncontradoException {
        int opcao;
        boolean montado = false;
        Controller controller = new Controller();
        do{
            System.out.println("Sistema de Gerenciamento de Arquivos e Diretório");
            if(!montado){
                System.out.println("1 - Montar Hieraquia de Arquivos e Diretorios");
            }else{  
                System.out.println("2 - Pesquisar um Arquivo");
                System.out.println("3 - Pesquisar Pasta");
                System.out.println("4 - Pesquisar um Arquivo por Tipo");
                System.out.println("5 - Gerar um Arquivo de Informações de Pastas e Arquivos");
                System.out.println("0 - Mostrar");
            }
            System.out.println("6 - Encerrar Programa");
            System.out.println("Digite o numero correspondente a opção que deseja:");
            
            opcao = Console.readInt();
            switch (opcao){
                case 0: 
                    if(montado){
                        System.out.println("\nElementos da Árvore:");
                        String[] str=controller.mostraArvore();
                        for(String s:str){
                            System.out.println(s);
                        }
                    }
                    break;
                case 1:
                    if(!montado){
                        System.out.println("Insira o Caminho da Pasta para Montar Hierarquia");
                        String caminho = Console.readString();
                        try {
                            controller.geraArvore(caminho);
                            montado = true;
                            System.out.println("Repositório Montado!\n \n");
                        } catch (NaoEhPastaException ex) {
                            System.out.println("Não é um diretório Válido!");
                        }
                    }else{
                        System.out.println("Opção invalida!\n \n");
                    }
                    break;
                case 2:
                    if(montado){
                        System.out.println("Insira o Nome do Arquivo: ");
                        String nome = Console.readString();
                        System.out.println("Insira o Nível da Profundidade: ");
                        int nivel = Console.readInt();
                        try {
                            if(nivel==0){
                                String[] arv = controller.mostraArvore();
                                for(String s:arv){
                                    if(s != null)
                                        System.out.print(s);
                                }
                            }else{
                                String str = controller.buscaArquivo(nome, nivel);
                                System.out.println("\nCaminho do Arquivo:");
                                System.out.println(str);
                            }
                        } catch (ArquivoNaoEncontradoException ex) {
                            System.out.println("Não é um Arquivo Válido!");
                        }   
                    }else{
                        System.out.println("Opção invalida!\n \n");
                    }
                    break;
                case 3:
                    if(montado){
                        System.out.println("Insira o Nome e o Nível da Profundidade de Busca da Pasta, Respectivamente");
                        String nome = Console.readString();
                        int nivel = Console.readInt();
                        try {
                             String[] str = controller.buscaPasta(nome, nivel);
                             System.out.println("\nCsminho da Pasta:");
                             for(String s:str){
                                if(s != null)
                                    System.out.println(s);
                            }
                        } catch (ArquivoNaoEncontradoException ex) {
                            System.out.println("Não é uma Pasta Válido!");
                        }   
                    }else{
                        System.out.println("Opção invalida!\n \n");
                    }
                    break;
            }
     }while(opcao!=6);
     
  }
}