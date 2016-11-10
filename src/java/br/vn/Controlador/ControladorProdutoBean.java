/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vn.Controlador;

import br.vn.Model.Filtros.Filtro;
import br.vn.Model.ItemDeVenda;
import br.vn.Model.Produto;
import br.vn.Model.Repositorio.RepositorioProduto;
import br.vn.Model.TipoProduto;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Kleriston
 */
@ManagedBean(name = "ProdutoBean")
@ViewScoped

public class ControladorProdutoBean {

    private RepositorioProduto repProduto = null;
    private Produto produto;
    private List<ItemDeVenda> itens = null;

    private List<Produto> listaFiltros;
    private TipoProduto tipo = new TipoProduto();

    private Filtro filtro;
    private String desc;
    
   
    
    

    public ControladorProdutoBean() {
        produto = new Produto();
        repProduto = new RepositorioProduto();
        itens = new ArrayList<>();
        listaFiltros = repProduto.recuperarTodos();
        filtro = new Filtro();
        

    }

    public TipoProduto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProduto tipo) {
        this.tipo = tipo;
    }

    public List<ItemDeVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemDeVenda> itens) {
        this.itens = itens;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void inserir() throws IOException {

        produto.setTipoProduto(tipo);

        repProduto.inserir(produto);

        produto = new Produto();

    }

    public Produto recuperar(long id) {
        return repProduto.recuperar(id);
    }

    public void alterar() {

        repProduto.alterar(produto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Atenção! "
                + " Produto Alterado Com Sucesso!"));

    }

    public void excluir() {

        repProduto.excluir(produto);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Atenção! "
                + " Produto Excluído Com Sucesso!"));

    }

    public List<Produto> recuperarTodos() {
        return this.repProduto.recuperarTodos();
    }

    public List<Produto> getListaFiltros() {
        return listaFiltros;
    }

    public void setListaFiltros(List<Produto> listaFiltros) {
        this.listaFiltros = listaFiltros;
    }

    public RepositorioProduto getRepProduto() {
        return repProduto;
    }

    public void setRepProduto(RepositorioProduto repProduto) {
        this.repProduto = repProduto;
    }

    public Filtro getFiltro() {
        return filtro;
    }

    public void setFiltro(Filtro filtro) {
        this.filtro = filtro;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        
        if(!desc.equals(this.desc)){
             this.desc = desc;
        listaFiltros = repProduto.recuperaFiltros(desc);
        }
       
    }
    
      public List<Produto> recuperaBusca(String busca){
        
    return listaFiltros = repProduto.recuperaBusca(busca);
         
    }

      
    
  public String formatarFloat(double valor){
  String retorno = "";
  DecimalFormat formatter = new DecimalFormat("#0.00");
  try{
    retorno = formatter.format(valor);
  }catch(Exception ex){
    System.err.println("Erro ao formatar numero: " + ex);
  }
  return retorno;
}
}
