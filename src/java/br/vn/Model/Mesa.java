/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vn.Model;

import java.beans.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.ManyToAny;

/**
 *
 * @author Kleriston
 */
@Entity (name="mesa")
public class Mesa implements Serializable {
    
    @Id
    @GeneratedValue
    private long id;
    
    private String nome;
    private String status;
    
    private double valorTotal;
    
    
   
    
    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ItemDeVenda> listaItens;

    @Deprecated
    public Mesa() {
    }
    
    public Mesa(String nome, String status){
        this.nome = nome;
     
        this.listaItens = new ArrayList<>();
        this.status = status;
        this.valorTotal = valorTotal;
    
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



    public List<ItemDeVenda> getListaItens() {
        return listaItens;
    }

    public void setListaItens(List<ItemDeVenda> listaItens) {
        this.listaItens = listaItens;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

  
    
    
}
