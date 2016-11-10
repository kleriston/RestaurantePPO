/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vn.Model.Repositorio;

import br.vn.Model.Dao.DaoManagerHiber;
import br.vn.Model.TipoProduto;
import java.util.List;

/**
 *
 * @author Kleriston
 */
public class RepositorioTipoProduto {
    
    public void inserir(TipoProduto tipo){
        DaoManagerHiber.getInstance().persist(tipo);
    }
    
    public List<TipoProduto> recuperarTodos(){
        return DaoManagerHiber.getInstance().recover("from tipoProduto");
    }
    
    public void excluir(TipoProduto tipo){
        DaoManagerHiber.getInstance().delete(tipo);
    }
    
    public void alterar(TipoProduto tipo){
        DaoManagerHiber.getInstance().update(tipo);
    }
}
