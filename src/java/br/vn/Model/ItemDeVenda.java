/****
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vn.Model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Marcia
 */
@Entity(name ="ItemDeVenda")

public class ItemDeVenda implements Serializable {
    
    @Id
    @GeneratedValue
    private long id;
    @Column
    private int quantidade;
    @Column
    private double precoVenda;
    
    @ManyToOne ()
    @JoinColumn(name="produto_id")
    private Produto produto;
    
    

    
    public ItemDeVenda() {
    }

    public ItemDeVenda(int quantidade, Produto produto, double precoVenda) {
        this.quantidade = quantidade;
        this.produto = produto;
        this.precoVenda = precoVenda;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
