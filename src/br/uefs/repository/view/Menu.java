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
import br.uefs.repository.exceptions.PastaNaoEncontradaException;
import br.uefs.repository.exceptions.TipoNaoEncontradoException;
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
                case 0:{
                    if(montado){
                        System.out.println("\nElementos da Árvore:");
                        String[] str=controller.mostraArvore();
                        for(String s:str){
                            System.out.println(s);
                        }
                    }
                    break;
                }
                case 1:{
                    if(!montado){
                        System.out.println("Insira o Caminho da Pasta para Montar Hierarquia");
                        String caminho = Console.readString();
                        try {
                            controller.geraArvore(caminho);
                            montado = true;
                            System.out.println("Repositório Montado!\n \n");
                        } catch (NaoEhPastaException ex) {
                            System.out.println("O diretorio informado não é uma pasta!");
                        }catch (PastaNaoEncontradaException ex){
                            System.out.println("O diretorio informado não existe!");
                        }
                    }else{
                        System.out.println("Opção invalida!\n \n");
                    }
                    break;
                }
                case 2:{
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
                                String[] str = controller.buscaArquivo(nome, nivel);
                                System.out.println("\nCaminho do Arquivo:");
                                int i = 0;
                                while(str[i]!=null){
                                    System.out.println(str[i]);
                                    i++;
                                }
                            }
                        } catch (ArquivoNaoEncontradoException ex) {
                            System.out.println("Não é um Arquivo Válido!");
                        }   
                    }else{
                        System.out.println("Opção invalida!\n \n");
                    }
                    break;
                }
                case 3:{
                    if(montado){
                        System.out.println("Insira o Nome do Arquivo: ");
                        String nome = Console.readString();
                        System.out.println("Insira o Nível da Profundidade: ");
                        int nivel = Console.readInt();
                        try {
                             String[] str = controller.buscaPasta(nome, nivel);
                             System.out.println("\nCaminho da Pasta:");
                             for(String s:str){
                                if(s != null)
                                    System.out.println(s);
                            }
                        }catch (PastaNaoEncontradaException ex) {
                            System.out.println("Pasta não encontrada!");
                        }   
                    }else{
                        System.out.println("Opção invalida!\n \n");
                    }
                    break;
                }
                 case 4:{
                    if(montado){
                        System.out.println("Insira o Tipo do Aquivo");
                        String tipo = Console.readString();
                        System.out.println("Insira o Nível da Profundidade: ");
                        int nivel = Console.readInt();
                        try {
                             String[] str = controller.buscaTipo(tipo, nivel);
                             System.out.println("\nCsminho da Pasta:");
                             for(String s:str){
                                if(s != null)
                                    System.out.println(s);
                            }
                        } catch (TipoNaoEncontradoException ex) {
                            System.out.println("Tipo não encontrado!");
                        }   
                    }else{
                        System.out.println("Opção invalida!\n \n");
                    }
                    break;
                }
                case 5:{
                    if(montado){
                        System.out.println("Insira o caminho da pasta que deseja mapear:");
                        String diretorio = Console.readString();
                        System.out.println("Insira o nível até onde deseja mapear:");
                        int nivel = Console.readInt();
                        System.out.println("Insira o caminho para o arquivo:");
                        String nome = Console.readString();
                        try {
                            controller.mapeamento(diretorio, nome, nivel);
                        } catch (PastaNaoEncontradaException ex) {
                            System.out.println("Pasta não encontrada!");
                        }
                    }
                }
            }
     }while(opcao!=6);
     
  }
}