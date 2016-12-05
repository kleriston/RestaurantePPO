/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vn.Model.Repositorio;


import br.vn.Model.Dao.DaoManagerHiber;
import br.vn.Model.Funcionario;
import java.util.List;

/**
 *
 * @author Kleriston
 */
public class RepositorioFuncionario{
    
    
    public void inserir(Funcionario funcionario){
        DaoManagerHiber.getInstance().persist(funcionario);
    }
    
    public Funcionario recuperar(String email, String senha){
        Funcionario user = null;
        List<Funcionario> lista = ((List<Funcionario>)DaoManagerHiber.getInstance().recover("from funcionario where email='"+email+"' and "
                + "senha='"+senha+"'"));
        
        if(lista.size()>0){
         return user = lista.get(0);
        }
        
       return null;
    }
    
    public void alterar(Funcionario funcionario){
        DaoManagerHiber.getInstance().update(funcionario);
    }
    
    public void excluir(Funcionario funcionario){
        DaoManagerHiber.getInstance().delete(funcionario);
    }
    
    public List<Funcionario> recuperarTodos(){
        
        return DaoManagerHiber.getInstance().recover("from funcionario");
    }
    
 

  
    
   
}
