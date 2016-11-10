/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vn.Controlador;

import br.vn.Model.Endereco;
import br.vn.Model.Repositorio.RepositorioEndereco;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Kleriston
 */
@ManagedBean(name = "EndBean")
@SessionScoped
public class ControladorEnderecoBean {
    
    private Endereco endereco;
    private RepositorioEndereco repEnd = null;

    public ControladorEnderecoBean() {
        endereco = new Endereco();
        repEnd = new RepositorioEndereco();
        
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public void inserir(){
        repEnd.inserir(endereco);
        
    }
    
}
