/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vn.Model.Filtros;

import java.net.MalformedURLException;

/**
 *
 * @author Kleriston
 */
public class Teste {
    public static void main(String[] args) throws MalformedURLException {
        String emailCliente = "kleriston_rodrigues@hotmail.com";
        Email e = new Email();
        e.enviarHatml(emailCliente);
       
    }
    
}
