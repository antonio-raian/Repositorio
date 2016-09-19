/**
 * Componente Curricular: Módulo Integrado de Programação
 * Autor: Antonio Raian e Milena Melo
 * Data:  19/09/2016
 *
 * Declaro que este código foi elaborado em dupla e não contém nenhum trecho 
 * de código de outro colega ou de outro autor, 
 * tais como provindos de livros e apostilas, e páginas ou documentos 
 * eletrônicos da Internet. Qualquer trecho de código de outra autoria que
 * uma citação para o não a nossa está destacado com  autor e a fonte do
 * código, e estou ciente que estes trechos não serão considerados para fins
 * de avaliação. Alguns trechos do código podem coincidir com de outros
 * colegas pois estes foram discutidos em sessões tutorias.
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
 * Através dessa classe, é feito o recebimento dos dados aos quais são inseridos pelo usuário, sendo enviados ao controller.
 * Desta forma, o menu maneja todas as inserções de dados, tendo os prints para visualização na interface da mesma.
 * Além disso, é retornado de forma visual a resposta esperada pelo usuários de acordo a programação do projeto.
 * 
 * @author Antonio Raian e Milena Melo
 */

public class Menu {
    public static void main(String[] args) throws IOException, ArquivoNaoEncontradoException, CelulaNaoEncontradaException {
        int opcao;
        boolean montado = false;// Boolean para verificação de arvore montada
        Controller controller = new Controller();// Inserre controller através de sua instância.
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
            
            opcao = Console.readInt();// Recebe a variável de opção advinda do usuário
            switch (opcao){
                case 0:{// Este método mostra todos os elementos pertencentes a árvore montada.
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
                        try {// Envia o caminho como parâmetro para construção da árvore.
                            controller.geraArvore(caminho);
                            montado = true;// Caso verdadeiro, "Repositório Montado!".
                            System.out.println("Repositório Montado!");
                        } catch (NaoEhPastaException ex) {// Caso contrário, é feito o exceptios.
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
                    if(montado){// Busca por arquivo 
                        System.out.println("Insira o nome do arquivo: ");
                        String nome = Console.readString();
                        System.out.println("Insira o nível da profundidade: ");
                        int nivel = Console.readInt();
                        try {
                                String[] str = controller.buscaArquivo(nome, nivel);
                                System.out.println("\nCaminho do arquivo:");
                                int i = 0;
                                for(String s:str){// Printa os caminhos identificados do(s) arquivos(s) encontrado(s)
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
                case 3:{// Busca por pasta 
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
                 case 4:{// Busca por arquivos de um tipo determinado e de acordo à profundidade
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
                case 5:{// Gera arquivo com todos os caminhos da árvore, de acordo a profundidade, 
                       // num diretório determinado, como o nome inserido
                    if(montado){
                        System.out.println("Insira o caminho da pasta que deseja mapear:");
                        String diretorio = Console.readString();
                        System.out.println("Insira o nível até onde deseja mapear:");
                        int nivel = Console.readInt();
                        System.out.println("Insira o caminho para o arquivo:");
                        String nome = Console.readString();
                        try {// Chama função de gerar arquivo
                            controller.geraArquivo(diretorio, nome, nivel);
                            System.out.println("Arquivo criado com Sucesso!");
                        } catch (PastaNaoEncontradaException ex) {// Exceptions se caso não for acriado
                            System.out.println("Pasta não encontrada!");
                        } catch (IOException e){
                            System.out.println("Não foi possível gerar o arquivo");
                        }
                    }else{
                        System.out.println("Opção invalida!");
                    }
                }
            }
     }while(opcao!=6);// Caso a opção seja "6", o programa encerra.
     
  }
}