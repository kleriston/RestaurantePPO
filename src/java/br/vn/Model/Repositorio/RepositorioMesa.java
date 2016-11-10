/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vn.Model.Repositorio;

import br.vn.Model.Dao.DaoManagerHiber;
import br.vn.Model.Mesa;
import java.util.List;

/**
 *
 * @author Kleriston
 */
public class RepositorioMesa {
    
    public void inserir(Mesa mesa ){
        DaoManagerHiber.getInstance().persist(mesa);
    }
    
    public List<Mesa> recuperarTodos(){
     return   DaoManagerHiber.getInstance().recover("from mesa");
    }
    
    public void altera(Mesa mesa){
       DaoManagerHiber.getInstance().update(mesa);
    }
}
