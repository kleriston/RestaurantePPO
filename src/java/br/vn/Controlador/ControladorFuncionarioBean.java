/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vn.Controlador;

import br.vn.Model.Repositorio.RepositorioFuncionario;
import br.vn.Model.Funcionario;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Kleriston
 */

@ManagedBean(name="FuncionarioBean")
@SessionScoped
public class ControladorFuncionarioBean {
    
    
    private RepositorioFuncionario repFuncionario = null;
    
    private Funcionario funcionario;
    
    public ControladorFuncionarioBean(){
        funcionario= new Funcionario();
        repFuncionario = new RepositorioFuncionario();
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    
    public void inserir(){
       
        repFuncionario.inserir(funcionario);
        
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Parabéns! " 
             +" Funcionário Cadastrado Com Sucesso!"));
        funcionario = new Funcionario();
        
        
    }
    
    public Funcionario recuperar(String email,String senha){
        return repFuncionario.recuperar(email, senha);
    }
    
    public void alterar(){
        
        repFuncionario.alterar(funcionario);
      
      
    }
    
    public void excluir(){
        repFuncionario.excluir(funcionario);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Atenção! " 
             +" Funcionário Excluído Com Sucesso!"));
        
    }
    
    public List<Funcionario> recuperarTodos(){
        return repFuncionario.recuperarTodos();
    }
}
