/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vn.Controlador;

import br.vn.Model.ItemDeVenda;
import br.vn.Model.Mesa;
import br.vn.Model.Produto;
import br.vn.Model.Repositorio.RepositorioMesa;
import br.vn.Model.Repositorio.RepositorioVenda;
import br.vn.Model.Venda;
import com.sun.webkit.graphics.GraphicsDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.awt.Color;

/**
 *
 * @author Kleriston
 */
@ManagedBean(name = "MesaBean")
@SessionScoped
public class ControladorMesasBean {

    private Mesa mesas;
    private RepositorioMesa repMesa;

    private Venda vendaCadastro;
    
    private double teste;

    private RepositorioVenda repVenda;
    
    private List<ItemDeVenda> listaDeItens;

    public ControladorMesasBean() {
        mesas = new Mesa();
        repMesa = new RepositorioMesa();
        vendaCadastro = new Venda();
        listaDeItens =new ArrayList<>();
        this.repVenda = new RepositorioVenda();
        
      
    }

    public void inserir() {
        mesas.setStatus("Fechada");
        mesas.setValorTotal(0);
        repMesa.inserir(mesas);
    }

    public List<Mesa> recuperarTodos() {

           return repMesa.recuperarTodos();
    }


    
    

    public Mesa getMesas() {
       
        return mesas;

    }

    public void setMesas(Mesa mesas) {
        this.mesas = mesas;
    }

    public List<ItemDeVenda> getListaDeItens() {
      
        return listaDeItens;
    }

    public void setListaDeItens(List<ItemDeVenda> listaDeItens) {
        this.listaDeItens = listaDeItens;
    }

    public void adicionar(Produto produto) {
        int poscaoEncontrada = -1;

        for (int i = 0; i< mesas.getListaItens().size() && poscaoEncontrada < 0; i++) {
              ItemDeVenda temp = mesas.getListaItens().get(i);
            if (temp.getProduto().getId() == produto.getId()) {
                poscaoEncontrada = i;
            }

        }
        ItemDeVenda item = new ItemDeVenda();
        item.setProduto(produto);

        if (poscaoEncontrada < 0) {
            item.setQuantidade(1);
            item.setPrecoVenda(produto.getPreço());

            mesas.getListaItens().add(item);

        } else {
            ItemDeVenda temp = mesas.getListaItens().get(poscaoEncontrada);
            item.setQuantidade(temp.getQuantidade() + 1);
            item.setPrecoVenda(produto.getPreço() * item.getQuantidade());
         
            mesas.getListaItens().set(poscaoEncontrada, item);
            
          
        }
       
           
           mesas.setValorTotal(mesas.getValorTotal() + item.getProduto().getPreço());
           
          
         
        repMesa.altera(mesas);

    }

    public void remover(ItemDeVenda item) {
        int poscaoEncontrada = -1;

        for (int i = 0; i < mesas.getListaItens().size() && poscaoEncontrada < 0; i++) {
            ItemDeVenda temp = mesas.getListaItens().get(i);

            if (temp.getProduto().getId() == item.getProduto().getId()) {
                poscaoEncontrada = i;
            }

        }
        if (poscaoEncontrada > -1) {
            mesas.getListaItens().remove(poscaoEncontrada);

           
           mesas.setValorTotal(mesas.getValorTotal() - item.getPrecoVenda());
           
                     

            repMesa.altera(mesas);
              vendaCadastro.setItens(mesas.getListaItens());
     
             
        }
    }

    public void abrirMesa(Mesa m) {
       
        m.setStatus("Aberta");

    }

    public Venda getVendaCadastro() {

        if (vendaCadastro == null) {
            vendaCadastro = new Venda();
            vendaCadastro.setValorTotal(0);
        }
        return vendaCadastro;
    }

    public void setVendaCadastro(Venda vendaCadastro) {
        this.vendaCadastro = vendaCadastro;
    }

    public String formatarFloat(double numero) {
        String retorno = "";
        DecimalFormat formatter = new DecimalFormat("#0.00");
        try {
            retorno = formatter.format(numero);
        } catch (Exception ex) {
            System.err.println("Erro ao formatar numero: " + ex);
        }
        return retorno;
    }
    
    
    public String inserirVendaMesa(){

       
   
      vendaCadastro.setItens(listaDeItens);
      vendaCadastro.setMesa(mesas.getNome());
      vendaCadastro.setValorTotal(mesas.getValorTotal());
       vendaCadastro.setItens(mesas.getListaItens());

        repVenda.inserir(vendaCadastro);
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Atenção! " 
             +" Venda Efetuada Com Sucesso!"));
      
      listaDeItens = new ArrayList<>();
       
       vendaCadastro.setValorTotal(0);
   
       mesas.setListaItens(listaDeItens);
        mesas.setStatus("Fechada");
               mesas.setValorTotal(0);
       repMesa.altera(mesas);
      return "/Teste.xhtml";
      
      
   }
    
        
    
    public String botao(Mesa m){
         String visualizar="Visualizar";
        String abrir ="Abrir-Mesa";

    
      
        if(m.getStatus().equals("Aberta")){
         return  visualizar;
        }
        return abrir;
    }
}
