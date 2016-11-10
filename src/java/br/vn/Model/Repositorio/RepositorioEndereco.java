/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vn.Model.Repositorio;

import br.vn.Model.Dao.DaoManagerHiber;
import br.vn.Model.Endereco;

/**
 *
 * @author Kleriston
 */
public class RepositorioEndereco {
    
    public void inserir(Endereco endereco){
        DaoManagerHiber.getInstance().persist(endereco);
    }
    
}
