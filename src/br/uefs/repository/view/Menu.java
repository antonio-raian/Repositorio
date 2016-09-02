/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.repository.view;

import br.uefs.repository.controller.Controller;
import br.uefs.repository.exceptions.CelulaNaoEncontradoException;
import br.uefs.repository.exceptions.NaoEhPastaException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Antonio
 */
public class Menu {
    public static void main(String[] args) {
        Controller ctrl = new Controller();
        
        String str = "D:\\Desktop\\Teste";
        
        try {
            ctrl.geraArvore(str);
            String[] arv = ctrl.mostraArvore();
            
            for(String s:arv){
                System.out.println(s);
            }
        } catch (CelulaNaoEncontradoException ex) {
            System.out.println("Não encontrou alguma celula pai");
        } catch (NaoEhPastaException ex) {
            System.out.println("O caminho especificado não é pasta");
        }
        
        
    }
}
