/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vn.Model.Filtros;

import br.vn.Model.Repositorio.RepositorioTipoProduto;
import br.vn.Model.TipoProduto;
import br.vn.Model.Venda;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/** 
 *
 * @author Kleriston
 */
@ManagedBean(name = "FiltroBean")
@SessionScoped
public class Filtro {

    @Temporal(value = TemporalType.DATE)
    private Date inicio;
     @Temporal(value = TemporalType.DATE)
    private Date fim;
   
    public Filtro() {
//     
        inicio = new Date();
        fim = new Date();

    }

    public Filtro(Date inicio, Date fim) {
        this.inicio = inicio;
        this.fim = fim;

    }


    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

  

    
}
