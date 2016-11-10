/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vn.Controlador;

import br.vn.Controlador.ControladorFuncionarioBean;
import br.vn.Model.Cliente;
import br.vn.Model.Funcionario;
import br.vn.Model.Venda;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 1860915
 */
@ManagedBean(name = "controleLogin")
@SessionScoped
public class ControladorLogin {

    private Funcionario funcionarioLogado = null;
    private ControladorFuncionarioBean controleFuncionario = null;
    private Cliente clientelogado = null;
    private ControladorClienteBean controleCliente = null;
    
    private int somaCarrin;
    
    private Venda vend;

    public ControladorLogin() {
        vend = new Venda();

        HttpSession session = ((HttpSession) FacesContext.getCurrentInstance().getExternalContext()
                .getSession(true));

        controleFuncionario = (ControladorFuncionarioBean) session.getAttribute("controleFuncionario");
        controleCliente = (ControladorClienteBean) session.getAttribute("controleCliente");

        if (controleFuncionario == null) {
            controleFuncionario = new ControladorFuncionarioBean();
            session.setAttribute("controleFuncionario", controleFuncionario);
        }
        if (controleCliente == null) {
            controleCliente = new ControladorClienteBean();
            session.setAttribute("controleCliente", controleCliente);
        }

    }

    public String realizarLogin(long cpf, String senha) throws NoSuchAlgorithmException {
        logarFuncionario(cpf, senha);
        logarCliente(cpf, senha);

        if ((funcionarioLogado == null) && (clientelogado == null)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Atenção! "
                    + "Usuário Não Cadastrado"));
            return "/index.xhtml";
        } else if ((funcionarioLogado != null) && (clientelogado == null)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Bem Vindo! " + funcionarioLogado.getNome()));

            return "/VendaCadastro.xhtml";
        } else if ((clientelogado != null && (funcionarioLogado == null))) {
            if(this.somaCarrin >0){
                  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Bem Vindo! "
                    + clientelogado.getNome()));
            return "/Carrinho.xhtml";
            }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Bem Vindo! "
                    + clientelogado.getNome()));
            return "/VendaCliente.xhtml";

        }
        }

        return null;
    }

    public void logarFuncionario(long cpf, String senha) throws NoSuchAlgorithmException {
        Funcionario u = controleFuncionario.recuperar(cpf, converte(senha));
        if (u != null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("controleFuncionario", u);
            funcionarioLogado = u;
        } else {
            funcionarioLogado = null;
        }
    }

    public void logarCliente(long cpf, String senha) throws NoSuchAlgorithmException {
        Cliente c = controleCliente.recuperar(cpf, converte(senha));
        if (c != null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("controleCliente", c);
            clientelogado = c;
        } else {
            clientelogado = null;
        }
    }

    public Funcionario getFuncionarioLogado() {
        return funcionarioLogado;
    }

    public void setFuncionarioLogado(Funcionario funcionarioLogado) {
        this.funcionarioLogado = funcionarioLogado;
    }

    public Cliente getClientelogado() {
        return clientelogado;
    }

    public void setClientelogado(Cliente clientelogado) {
        this.clientelogado = clientelogado;
    }

    public String sair() {
            this.clientelogado = null;
        
      
            this.funcionarioLogado = null;
        

        ((HttpSession) (FacesContext.getCurrentInstance().getExternalContext().getSession(true))).removeAttribute("controleCliente");
        //((HttpSession) (FacesContext.getCurrentInstance().getExternalContext().getSession(true))).removeAttribute("controleLogin");
        ((HttpSession) (FacesContext.getCurrentInstance().getExternalContext().getSession(true))).removeAttribute("vendaBean");
          ((HttpSession) (FacesContext.getCurrentInstance().getExternalContext().getSession(true))).removeAttribute("ControleFuncionario");
        
        
        return "/index.xhtml";

    }
    public String converte(String senha) throws NoSuchAlgorithmException{
        
         MessageDigest md = MessageDigest.getInstance("MD5");
 
        BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
 
        return String.format("%32x", hash);
    }

    public Venda getVend() {
        return vend;
    }

    public void setVend(Venda vend) {
        this.vend = vend;
    }

    public int getSomaCarrin() {
        return somaCarrin;
    }

    public void setSomaCarrin(int somaCarrin) {
        this.somaCarrin = somaCarrin;
    }
    
    

}
