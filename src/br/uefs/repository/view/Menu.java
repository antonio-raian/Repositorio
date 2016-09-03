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
        Controller controller = new Controller();
        do{
        System.out.print("Sistema de Gerenciamento de Arquivos e Diretório");
        System.out.print("Opcao 1 - Montar Hieraquiade Arquivos e Diretorios");
        System.out.print("Opcao 2 - Pesquisar um Arquivo");
        System.out.print("Opcao 3 - Pesquisar Pasta");
        System.out.print("Opcao 4 - Pesquisar um Arquivo por Tipo");
        System.out.print("Opcao 5 - Gerar um Arquivo de Informações de Pastas e Arquivos");
        System.out.print("Opcao 6 - Encerrar Programa");
        
        opcao = Console.readInt();
             switch (opcao){
                  case 1:
                       System.out.print("Insira o Caminho da Pasta para Montar Hierarquia");
                       String caminho = Console.readString();
                       {
                            try {
                                controller.geraArvore(caminho);
                                String[] arv = controller.mostraArvore();
                                  for(String s:arv){
                                      System.out.println(s);
                                  }
                            } catch (CelulaNaoEncontradoException | NaoEhPastaException ex) {
                                System.out.println("Não encontrou alguma celula pai");
                                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                            }
        
                       }
                        break;
        
            }
       }while(opcao!=6);
   }
}