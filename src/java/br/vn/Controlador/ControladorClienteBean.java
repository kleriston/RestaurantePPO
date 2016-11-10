/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vn.Controlador;

import br.vn.Model.Cliente;
import br.vn.Model.Endereco;
import br.vn.Model.Repositorio.RepositorioCliente;
import br.vn.Model.Repositorio.RepositorioEndereco;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Kleriston
 */
@ManagedBean(name = "ClienteBean")
@ViewScoped
public class ControladorClienteBean {

    private RepositorioCliente repCliente = null;
    private RepositorioEndereco repEnd = null;
    private String confirma;
    private String verifica;
    private long teste;

    public long getTeste() {
        return teste;
    }

    public void setTeste(long teste) {
        this.teste = teste;
    }
    

    private Cliente cliente;
    private List<Cliente> recupera = new ArrayList<>();
    
    private Endereco endereco = null;

    public ControladorClienteBean() {
        cliente = new Cliente();
        repCliente = new RepositorioCliente();
        repEnd = new RepositorioEndereco();
        endereco = new Endereco();
        this.confirma = confirma;
        this.verifica = verifica;
       

    }

    public RepositorioCliente getRepCliente() {
        return repCliente;
    }

    public void setRepCliente(RepositorioCliente repCliente) {
        this.repCliente = repCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEndereco() {
        if (endereco == null) {
            endereco = new Endereco();
        }
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public RepositorioEndereco getRepEnd() {
        return repEnd;
    }

    public void setRepEnd(RepositorioEndereco repEnd) {
        this.repEnd = repEnd;
    }

    public String getConfirma() {
        return confirma;
    }

    public void setConfirma(String confirma) throws NoSuchAlgorithmException {
           MessageDigest md = MessageDigest.getInstance("MD5");
        
        BigInteger hash = new BigInteger(1, md.digest(confirma.getBytes()));
        this.confirma = String.format("%32x", hash);
     
        
    }

    public void inserir() {
        if (this.confirma.equals(this.cliente.getSenha())) {

            try {
                cliente.setEndereco(endereco);
                
                repCliente.inserir(cliente);
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Parabéns! "
                        + " Cliente Cadastrado Com Sucesso!"));
                
                Endereco e = new Endereco();
                endereco.setBairro(e.getBairro());
                endereco.setCidade(e.getCidade());
                endereco.setLogradouro(e.getLogradouro());
                endereco.setNumero(e.getNumero());
                
                RepositorioEndereco repEnd = new RepositorioEndereco();
                
                repEnd.inserir(endereco);
                
                  SimpleEmail email = new SimpleEmail();
                    email.setHostName("smtp.googlemail.com");
                    email.setSmtpPort(465);
                    email.setAuthentication("Kleriston.firmino@gmail.com", "cavalo15");
                    email.setSSLOnConnect(true);
                    email.setFrom("teste.ads@gmail.com");
                    email.setSubject("O Velho Nordestino");
                    email.setMsg("Bem Vindo: " + cliente.getNome() + ", Cadastro Efetuado Com Sucesso!");
                    email.addTo(cliente.getEmail());

                    email.send();
                
                Cliente c = new Cliente();
            } catch (EmailException ex) {
                Logger.getLogger(ControladorClienteBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Atenção! "
                    + " Senha Incorreta!"));
        }

        

    }

    public List<Cliente> recuperarTodos() {
        return repCliente.recuperarTodos();
    }

    public void altera() {
        repCliente.alterar(cliente);
    }
    
    public String alterarSenha() throws NoSuchAlgorithmException{

        if(verifica.equals(verifica)){
            
        
        long cpf = teste;
     
        
        
        repCliente.alterarSenha(verifica, cpf);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Parabéns! "
                    + " Senha Alterada Com Sucesso!"));
        return "/index.xhtml";
        
        }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Atenção! "
                    + " Senha Incorreta!"));
        }
        return null;
    }

    public void excluir() {
        repCliente.excluir(cliente);

    }

    public Cliente recuperar(long cpf, String senha) throws NoSuchAlgorithmException {
        return repCliente.recuperar(cpf, senha);
    }

    public void acharCliente(long cpf, long rg) {
        teste = cpf;
    
   recupera =  repCliente.recuperarSenha(cpf,rg);
    
    }

    public List<Cliente> getRecupera() {
        return recupera;
    }

    public void setRecupera(List<Cliente> recupera) {
        this.recupera = recupera;
    }

    public String getVerifica() {
        return verifica;
    }

    public void setVerifica(String verifica) {
          this.verifica = verifica;
        
    }

   
public void alterarCliente(Cliente c){
    
    repCliente.alteraClienteLogado(c);
}
   
}
