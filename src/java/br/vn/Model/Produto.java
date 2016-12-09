/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vn.Model;

import java.io.Serializable;
import java.text.DecimalFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Kleriston
 */

@Entity(name="produto")
@Table
public class Produto  implements Serializable{

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 6, unique = true)
    private long codigo;
    @Column(length = 40)
    private String nome;
    @Column(length = 5)
    private double preço;
    @Column
    private String descricao;
      
    @OneToOne
    private TipoProduto tipoProduto;
    
   
    private String foto;
  

    public Produto(long codigo, String nome, double preço, String descricao, String foto) {
      
        this.nome = nome;
        this.preço = preço;
        this.codigo = codigo;
        this.descricao = descricao;
        this.foto = foto;
        
    }
    
    @Deprecated
    public Produto(){
        
        
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }




    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreço() {
     
        return preço;
    }

    public void setPreço(double preço) {
        this.preço = preço;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

   
    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


    
}
