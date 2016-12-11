/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vn.Model.Repositorio;
import br.vn.Model.Dao.DaoManagerHiber;
import br.vn.Model.Filtros.Filtro;
import br.vn.Model.Produto;
import java.util.List;

/**
 *
 * @author Kleriston
 */
public class RepositorioProduto {
    
    
    
    public void inserir(Produto produto){
        DaoManagerHiber.getInstance().persist(produto);
    }
    
    public Produto recuperar(Long id){
        return (Produto) DaoManagerHiber.getInstance().recoverSQL("from Produto where");
    }
    
    public void alterar(Produto produto){
        DaoManagerHiber.getInstance().update(produto);
    }
    
    public void excluir(Produto produto){
        DaoManagerHiber.getInstance().delete(produto);
    }
    
    public List<Produto> recuperarTodos(){
        return  DaoManagerHiber.getInstance().recover("from produto" );
    }
   
    
    public List<Produto> recuperaFiltros(String desc){
        List<Produto> lista = null;
        
        if(desc.equals("Todos")){
            lista = (List<Produto>) DaoManagerHiber.getInstance().recover("from produto");
       
        }else{
             lista = (List<Produto>) DaoManagerHiber.getInstance().recover("from produto where tipoProduto_id = " + "'" + desc+"'");
        }
       
       
        return lista;
    }
    
        public List<Produto> recuperaBusca(String busca){
            List<Produto> lista = null;
            if(busca.equals("")){
                lista = (List<Produto>) DaoManagerHiber.getInstance().recover("from produto");
            }else{
                
          
        lista = (List<Produto>) DaoManagerHiber.getInstance().recover("from produto where nome like" + "%'" + busca+"$'");
            }
            return lista;
    }
             
    
} 
