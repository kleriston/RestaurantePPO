/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vn.Model.Repositorio;

import br.vn.Controlador.ControladorLogin;
import br.vn.Model.Cliente;
import br.vn.Model.Dao.DaoManagerHiber;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Kleriston
 */
public class RepositorioCliente {
    
    public void inserir(Cliente cliente){
        DaoManagerHiber.getInstance().persist(cliente);
       
    }
    
    public void alterar(Cliente cliente){
        DaoManagerHiber.getInstance().update(cliente);
    }
    
    public void excluir(Cliente cliente){
        DaoManagerHiber.getInstance().delete(cliente);
    }
    
    public List<Cliente> recuperarTodos(){
        return DaoManagerHiber.getInstance().recover("from cliente");
    }
    
    public Cliente recuperar(String email, String senha) throws NoSuchAlgorithmException{
        Cliente cliente = null;
         
        
          List<Cliente> lista = ((List<Cliente>)DaoManagerHiber.getInstance().recover("from cliente where email='"+email+"' and "
                + "senha='"+senha+"'"));
          
          if(lista.size()>0){
            return  cliente = lista.get(0);
          }
          return null;
    }
    
    public List<Cliente> recuperarSenha(long cpf, long rg){
        List<Cliente> cliente = new ArrayList<>();
        
         List<Cliente> lista = ((List<Cliente>)DaoManagerHiber.getInstance().recover("from cliente where cpf='"+cpf+"' and "
                + "rg='"+rg+"'"));
         
          return lista;
    }
    
    
    public void alterarSenha(String senha, long cpf) throws NoSuchAlgorithmException{
        Cliente c = new Cliente();
        List<Cliente> lista = ((List<Cliente>)DaoManagerHiber.getInstance().recover("from cliente where cpf="+cpf));
        
       c = lista.get(0);
       
        c.setSenha(senha);
      
        
        DaoManagerHiber.getInstance().update(c);
    }
    
     public List<Cliente> recuperaEmail(){
        return DaoManagerHiber.getInstance().recover("from cliente" );
    }
     
   

    public void alteraClienteLogado(Cliente c) {
        DaoManagerHiber.getInstance().update(c);
    }
}
