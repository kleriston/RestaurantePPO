/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vn.Controlador;

import br.vn.Model.Cliente;
import br.vn.Model.Filtros.Email;
import br.vn.Model.Repositorio.RepositorioCliente;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;

/**
 *
 * @author Kleriston
 */
@ManagedBean(name = "RecuperaSenhaBean")
@SessionScoped
public class RecuperaSenhaBean {

    String emailCliente;
    List<Cliente> c = null;

    RepositorioCliente repCliente = null;

    public RecuperaSenhaBean() {
        this.emailCliente = emailCliente;
        repCliente = new RepositorioCliente();
        c = new ArrayList<>();

    }

//    public String envia(String emailCliente) {
//
//        c = repCliente.recuperaEmail();
//        for (int i = 0; i < c.size(); i++) {
//            if (c.get(i).getEmail().equals(emailCliente)) {
//
//                try {
//                    SimpleEmail email = new SimpleEmail();
//                    email.setHostName("smtp.googlemail.com");
//                    email.setSmtpPort(465);
//                    email.setAuthentication("Kleriston.firmino@gmail.com", "cavalo15");
//                    email.setSSLOnConnect(true);
//                    email.setFrom("teste.ads@gmail.com");
//                    email.setSubject("Recuperar Senha");
//                    email.setMsg("Atenção: " + c.get(i).getNome() + " Para Recuperar Sua Senha Click Aqui ===>" + "http://localhost:8084/Restaurante/faces/faces/RecuperarSenha.xhtml");
//                    email.addTo(emailCliente);
//
//                    email.send();
//                } catch (EmailException ex) {
//                    Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Atenção! "
//                        + " Em Instantes Você Receberá Um Email Com as Instruções Para Recuperar Sua Senha!"));
//                return "/index.xhtml";
//            }
//
//        }
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Atenção! "
//                + " Email Incorreto!"));
//        return "/EnviarEmail.xhtml";
//    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    

}
