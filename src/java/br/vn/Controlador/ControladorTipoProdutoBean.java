/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vn.Controlador;

import br.vn.Model.Repositorio.RepositorioTipoProduto;
import br.vn.Model.TipoProduto;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Kleriston
 */
@ManagedBean(name = "TipeBean")
@ViewScoped
public class ControladorTipoProdutoBean {
    
    private TipoProduto tipo;
    private RepositorioTipoProduto repTipo;

    public ControladorTipoProdutoBean() {
       tipo = new TipoProduto();
        repTipo = new RepositorioTipoProduto();
                
    }

    public TipoProduto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProduto tipo) {
        this.tipo = tipo;
    }
    
    public void inserir(){
        repTipo.inserir(tipo);
        tipo = new TipoProduto();
    }
    
    public List<TipoProduto> recuperarTodos(){
        return repTipo.recuperarTodos();
    }
    
    public void excluir(){
        repTipo.excluir(tipo);
    }
    public void alterar(){
        repTipo.alterar(tipo);
    }
    
}
