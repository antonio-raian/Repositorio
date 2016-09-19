/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.repository.view;

import br.uefs.repository.controller.Controller;
import br.uefs.repository.exceptions.ArquivoNaoEncontradoException;
import br.uefs.repository.exceptions.CelulaNaoEncontradaException;
import br.uefs.repository.exceptions.NaoEhPastaException;
import br.uefs.repository.exceptions.PastaNaoEncontradaException;
import br.uefs.repository.exceptions.TipoNaoEncontradoException;
import br.uefs.repository.util.Console;
import java.io.IOException;

/**
 *
 * @author Antonio
 */
public class Menu {
    public static void main(String[] args) throws IOException, ArquivoNaoEncontradoException, CelulaNaoEncontradaException {
        int opcao;
        boolean montado = false;
        Controller controller = new Controller();
        do{
            System.out.println("\n \n");
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
                        System.out.println("Insira o caminho da pasta para montar hierarquia");
                        String caminho = Console.readString();
                        try {
                            controller.geraArvore(caminho);
                            montado = true;
                            System.out.println("Repositório Montado!");
                        } catch (NaoEhPastaException ex) {
                            System.out.println("O diretorio informado não é uma pasta!");
                        }catch (PastaNaoEncontradaException ex){
                            System.out.println("O diretorio informado não existe!");
                        }
                    }else{
                        System.out.println("Opção invalida!");
                    }
                    break;
                }
                case 2:{
                    if(montado){
                        System.out.println("Insira o nome do arquivo: ");
                        String nome = Console.readString();
                        System.out.println("Insira o nível da profundidade: ");
                        int nivel = Console.readInt();
                        try {
                                String[] str = controller.buscaArquivo(nome, nivel);
                                System.out.println("\nCaminho do arquivo:");
                                int i = 0;
                                for(String s:str){
                                  if(s != null)
                                    System.out.println(s);
                                }
                            
                        } catch (ArquivoNaoEncontradoException ex) {
                            System.out.println("Arquivo não encontrado!");
                        }   
                    }else{
                        System.out.println("Opção invalida!");
                    }
                    break;
                }
                case 3:{
                    if(montado){
                        System.out.println("Insira o nome da pasta: ");
                        String nome = Console.readString();
                        System.out.println("Insira o nível da profundidade: ");
                        int nivel = Console.readInt();
                        try {
                             String[] str = controller.buscaPasta(nome, nivel);
                             System.out.println("\nCaminho da pasta:");
                             for(String s:str){
                                if(s != null)
                                    System.out.println(s);
                            }
                        }catch (PastaNaoEncontradaException ex) {
                            System.out.println("Pasta não encontrada!");
                        }   
                    }else{
                        System.out.println("Opção invalida!");
                    }
                    break;
                }
                 case 4:{
                    if(montado){
                        System.out.println("Insira o tipo do aquivo");
                        String tipo = Console.readString();
                        System.out.println("Insira o nível da profundidade: ");
                        int nivel = Console.readInt();
                        try {
                             String[] str = controller.buscaTipo(tipo, nivel);
                             System.out.println("\nCsminho do(s) aruivo(s) por tipo:");
                             for(String s:str){
                                if(s != null)
                                    System.out.println(s);
                            }
                        } catch (TipoNaoEncontradoException ex) {
                            System.out.println("Tipo não encontrado!");
                        }   
                    }else{
                        System.out.println("Opção invalida!");
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
                            controller.geraArquivo(diretorio, nome, nivel);
                            System.out.println("Arquivo criado com Sucesso!");
                        } catch (PastaNaoEncontradaException ex) {
                            System.out.println("Pasta não encontrada!");
                        } catch (IOException e){
                            System.out.println("Não foi possível gerar o arquivo");
                        }
                    }else{
                        System.out.println("Opção invalida!");
                    }
                }
            }
     }while(opcao!=6);
     
  }
}