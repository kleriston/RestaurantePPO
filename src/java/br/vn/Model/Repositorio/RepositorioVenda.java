/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vn.Model.Repositorio;

import br.vn.Controlador.ControladorLogin;
import br.vn.Model.Cliente;
import br.vn.Model.Dao.DaoManagerHiber;
import br.vn.Model.Filtros.Filtro;
import br.vn.Model.Venda;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Kleriston
 */
public class RepositorioVenda {

    public void inserir(Venda venda) {
        DaoManagerHiber.getInstance().persist(venda);
    }

    public List<Venda> recuperaTodos() {
        return DaoManagerHiber.getInstance().recover("from venda");
    }

    public void excluir(Venda venda) {
        DaoManagerHiber.getInstance().delete(venda);
    }

    public List<Venda> buscar(Filtro f) throws ParseException {

        SimpleDateFormat t = new SimpleDateFormat("yyyy-MM-dd");
        
        String inicio = t.format(f.getInicio());
        String fim =  t.format(f.getFim());
      
        List<Venda> lista = null;
        lista = (List<Venda>) DaoManagerHiber.getInstance().recover("from venda where data_venda between '"
                + inicio + "' and '" +fim + "'");
        return lista;
    }
    
    public List<Venda> recuperaVendaCliente(ControladorLogin cli){
        List<Venda> lista = null;
        lista = (List<Venda>) DaoManagerHiber.getInstance().recover("from venda where cliente_id = "+ cli.getClientelogado().getId());
        
        if(lista == null){
            return null;
        }
        return lista;
    }
}
