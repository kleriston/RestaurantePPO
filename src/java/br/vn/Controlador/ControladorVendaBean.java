/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vn.Controlador;

import br.vn.Model.Cliente;
import br.vn.Model.Filtros.Filtro;
import br.vn.Model.ItemDeVenda;
import br.vn.Model.Produto;
import br.vn.Model.Repositorio.RepositorioVenda;
import br.vn.Model.Venda;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kleriston
 */
@ManagedBean(name = "vendaBean")
@SessionScoped
public class ControladorVendaBean {

    private List<Venda> listadeVendas;

    private Filtro filtro;

    private double soma = 0;

    private RepositorioVenda repVenda = null;

    private List<ItemDeVenda> listaItens = null;

    private Venda vendaCadastro = null;

    private Cliente cliente = null;

    private ControladorLogin controleLogin = null;
    
    private int somaCarrinho = 0;
    private String teste = "";
    
    private String [] listaImagens = new String[3];
    
 
    public ControladorLogin getControleLogin() {
        return controleLogin;
    }

    public ControladorVendaBean() {
        this.teste = teste;
       
        
        repVenda = new RepositorioVenda();
        vendaCadastro = new Venda();
        this.cliente = new Cliente();
        this.controleLogin = (ControladorLogin) ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("controleLogin");
        if (this.controleLogin == null) {
            controleLogin = new ControladorLogin();
            ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute("controleLogin", this.controleLogin);
        }
        if (listaItens == null) {
            this.listaItens = new ArrayList<>();
        }
        filtro = new Filtro();
        listadeVendas = new ArrayList<>();
        

     
    }

    public double getSoma() {

        return soma;
    }

    public void setSoma(double soma) {
        this.soma = soma;
    }

    public Filtro getFiltro() {

        return filtro;
    }

    public void setFiltro(Filtro filtro) {
        this.filtro = filtro;
    }

    public Venda getVendaCadastro() {
        return vendaCadastro;
    }

    public void setVendaCadastro(Venda vendaCadastro) {
        this.vendaCadastro = vendaCadastro;
    }

    public List<ItemDeVenda> getListaItens() {
        return listaItens;
    }

    public void setListaItens(List<ItemDeVenda> listaItens) {
        this.listaItens = listaItens;
    }

    public List<Venda> getListadeVendas() throws ParseException {
        return listadeVendas;
    }

    public void setListadeVendas(List<Venda> listadeVendas) {
        this.listadeVendas = listadeVendas;
    }

    public void adicionar(Produto produto) {
        
  controleLogin.setSomaCarrin(controleLogin.getSomaCarrin()+1);
        int posicaoEncontrada = -1;
        setSomaCarrinho(somaCarrinho +1);

        for (int i = 0; i < listaItens.size() && posicaoEncontrada < 0; i++) {
            ItemDeVenda temp = listaItens.get(i);

            if (temp.getProduto().getId() == produto.getId()) {
                posicaoEncontrada = i;
            }
        }

        ItemDeVenda item = new ItemDeVenda();
        item.setProduto(produto);
        

        if (posicaoEncontrada < 0) {
            item.setQuantidade(1);
            item.setPrecoVenda(produto.getPreço());
            listaItens.add(item);

        } else {
            ItemDeVenda temp = listaItens.get(posicaoEncontrada);
            item.setQuantidade(temp.getQuantidade() + 1);
            item.setPrecoVenda(produto.getPreço() * item.getQuantidade());

            listaItens.set(posicaoEncontrada, item);
        }
   
    
        vendaCadastro.setValorTotal(item.getProduto().getPreço() + vendaCadastro.getValorTotal());
  
    }

    public void remover(ItemDeVenda item) {

         controleLogin.setSomaCarrin(controleLogin.getSomaCarrin()- item.getQuantidade());
        int posicaoEncontrada = -1;
          setSomaCarrinho(somaCarrinho - item.getQuantidade());

        for (int i = 0; i < listaItens.size() && posicaoEncontrada < 0; i++) {
            ItemDeVenda temp = listaItens.get(i);

            if (temp.getProduto().getId() == item.getProduto().getId()) {
                posicaoEncontrada = i;
            }
        }
        if (posicaoEncontrada > -1) {
            listaItens.remove(posicaoEncontrada);

          
            vendaCadastro.setValorTotal(vendaCadastro.getValorTotal() - item.getPrecoVenda());
        }
    }

    public void carregarDadosVenda() throws ParseException {

        SimpleDateFormat d = new SimpleDateFormat("HH:mm:ss");
        
        
        Date hora = new Date();

        vendaCadastro.setHoravenda(d.format(hora));

        Date data = new Date();

        vendaCadastro.setDataVenda(data);

    }

    public String inserirVendaCliente() {
      
             
            vendaCadastro.setItens(listaItens);
            vendaCadastro.setCliente(controleLogin.getClientelogado());
             repVenda.inserir(vendaCadastro);
             listaItens = new ArrayList<>();
            
       
           
                    
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Parabéns! "
                    + " Pedido Efetuado Com Sucesso!"));
            setSomaCarrinho(0);
           
      
        vendaCadastro.setValorTotal(0);
         controleLogin.setSomaCarrin(0);
        return "VendaCliente.xhtml";
    }

     public String inserirVendaBalcao(){
       vendaCadastro.setItens(listaItens);
       vendaCadastro.setMesa("Balcão");
       
      repVenda.inserir(vendaCadastro);
       
       vendaCadastro = new Venda();
       listaItens = new ArrayList<>();
       cliente = new Cliente();
 
             vendaCadastro = new Venda();
       
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Atenção! " 
             +" Venda Efetuada Com Sucesso!"));
       vendaCadastro.setValorTotal(0);
      return "VendaCadastro.xhtml";
   }
    
    
    public List<Venda> recuperarTodas() {
        List<Venda> ref = repVenda.recuperaTodos();
        List<Venda> clone = new ArrayList<>();
        List<Venda> teste = new ArrayList<>();
        Venda vnovo = null;
        ItemDeVenda ivnovo = null;
        for (Venda v : ref) {
            vnovo = new Venda();
            vnovo.setId(v.getId());
            vnovo.setDataVenda(v.getDataVenda());
            vnovo.setMesa(v.getMesa());
            vnovo.setValorTotal(v.getValorTotal());
            vnovo.setHoravenda(v.getHoravenda());
            vnovo.setCliente(v.getCliente());

            for (ItemDeVenda iv : v.getItens()) {
                ivnovo = new ItemDeVenda();
                ivnovo.setId(iv.getId());
                ivnovo.setPrecoVenda(iv.getPrecoVenda());
                ivnovo.setProduto(iv.getProduto());
                ivnovo.setQuantidade(iv.getQuantidade());

                vnovo.addItemVenda(ivnovo);

            }

            clone.add(vnovo);

        }

        return clone;

    }

    public List<Venda> recuperaInvertido() {

        List<Venda> t = new ArrayList<>(recuperarTodas().size());

        for (int i = recuperarTodas().size() - 1; i >= 0; i--) {
            t.add(recuperarTodas().get(i));
        }
        return t;
    }

    public void excluir() {

        repVenda.excluir(vendaCadastro);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Atenção! "
                + " Venda Excluída Com Sucesso!"));
        vendaCadastro = new Venda();

    }

    public List<ItemDeVenda> vendasSelecionadas() {
        return this.vendaCadastro.getItens();
    }

    public Cliente clienteselecionado() {
        return this.vendaCadastro.getCliente();
    }

    public void setControleLogin(ControladorLogin controleLogin) {
        this.controleLogin = controleLogin;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Venda> recuperarTodasCliente() {
        List<Venda> ref = repVenda.recuperaVendaCliente(controleLogin);

        List<Venda> clone = new ArrayList<>();
        List<Venda> teste = new ArrayList<>();
        Venda vnovo = null;
        ItemDeVenda ivnovo = null;
        for (Venda v : ref) {
            vnovo = new Venda();
            vnovo.setId(v.getId());
            vnovo.setDataVenda(v.getDataVenda());
            vnovo.setMesa(v.getMesa());
            vnovo.setValorTotal(v.getValorTotal());
            vnovo.setHoravenda(v.getHoravenda());
            vnovo.setCliente(v.getCliente());

            for (ItemDeVenda iv : v.getItens()) {
                ivnovo = new ItemDeVenda();
                ivnovo.setId(iv.getId());
                ivnovo.setPrecoVenda(iv.getPrecoVenda());
                ivnovo.setProduto(iv.getProduto());
                ivnovo.setQuantidade(iv.getQuantidade());

                vnovo.addItemVenda(ivnovo);

            }

            clone.add(vnovo);

        }

        return clone;

    }

    public List<Venda> recuperaVendaCliente() {
        List<Venda> t = new ArrayList<>(recuperarTodasCliente().size());

        for (int i = recuperarTodasCliente().size() - 1; i >= 0; i--) {
            t.add(recuperarTodasCliente().get(i));
        }
        return t;
    }

    public void buscaRelatorio() throws ParseException {

        this.listadeVendas = repVenda.buscar(filtro);

        this.soma = 0;
        for (int i = 0; i < listadeVendas.size(); i++) {
            soma += listadeVendas.get(i).getValorTotal();
        }

    }

    public int getSomaCarrinho() {
        return somaCarrinho;
    }

    public void setSomaCarrinho(int somaCarrinho) {
        this.somaCarrinho = somaCarrinho;
    }

   
public String formatarFloat(double numero){
  String retorno = "";
  DecimalFormat formatter = new DecimalFormat("#0.00");
  try{
    retorno = formatter.format(numero);
  }catch(Exception ex){
    System.err.println("Erro ao formatar numero: " + ex);
  }
  return retorno;
}

    public String getTeste() {
        return teste;
    }

    public void setTeste(String teste) {
        this.teste = teste;
    }

    public String[] getListaImagens() {
        return listaImagens;
    }

    public void setListaImagens(String[] listaImagens) {
        this.listaImagens = listaImagens;
    }


    

    
    
}
