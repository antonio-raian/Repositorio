/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.repository.view;

import br.uefs.repository.controller.Controller;
import br.uefs.repository.exceptions.CelulaNaoEncontradoException;
import br.uefs.repository.exceptions.NaoEhPastaException;
import br.uefs.repository.util.Console;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Antonio
 */
public class Menu {
    public static void main(String[] args) throws IOException {
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
                System.out.println("0 - mostrar");
            }
            System.out.println("6 - Encerrar Programa");
            System.out.println("Digite o numero correspondente a opção que deseja:");
            
            opcao = Console.readInt();
            switch (opcao){
                case 0: 
                    if(montado){
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
                        } catch (CelulaNaoEncontradoException | NaoEhPastaException ex) {
                            System.out.println("Não é um diretório Válido!");
                        }
                    }else{
                        System.out.println("Opção invalida!\n \n");
                    }
                    break;
            }
        }while(opcao!=6);
   }
}