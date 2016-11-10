/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vn.Model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Marcia
 */
@Entity(name ="venda")
@Table
public class Venda implements Serializable {
    
    @Id
    @GeneratedValue
    private int id;
    
    @Column(name="data_venda")
    @Temporal(value= TemporalType.DATE)
    private Date dataVenda;
    @Column
    private double valorTotal;
    @Column
    private String mesa;
    @Column
    private String horavenda;
    
    @OneToMany(cascade = CascadeType.ALL)
    
    private List<ItemDeVenda> itens; 
    
    @ManyToOne
    private Cliente cliente;
    


      
   
  
    @Deprecated
    public Venda() {
        
        this.itens = new ArrayList<>();
        dataVenda = new Date();
    }

   
    
    public Venda( Date dataVenda, List<ItemDeVenda> itens, double valorTotal, String mesa, String horavenda,
    Cliente cliente) {
        this.dataVenda = dataVenda;
        this.itens = itens;
        this.valorTotal = valorTotal;
        this.mesa = mesa;
        this.horavenda = horavenda;
        this.cliente = cliente;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }


    public String getDataString(){
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(this.dataVenda);
    }
 
    public List<ItemDeVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemDeVenda> itens) {
        this.itens = itens;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public String getHoravenda() {
        return horavenda;
    }

    public void setHoravenda(String horavenda) {
        this.horavenda = horavenda;
    }

  
    
    
    
    public void addItemVenda(ItemDeVenda itemDeVenda){
        this.itens.add(itemDeVenda);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    
    
    
}
