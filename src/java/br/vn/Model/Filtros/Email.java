/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vn.Model.Filtros;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Kleriston
 */
public class Email {

    public Email() {

    }

    public void envia(String emailcliente) {

        try {
            SimpleEmail email = new SimpleEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthentication("Kleriston.firmino@gmail.com", "cavalo15");
            email.setSSLOnConnect(true);
            email.setFrom("teste.ads@gmail.com");
            email.setSubject("Recuperar Senha");
            email.setMsg(" [url=\"www.google.com.br\"][img]https://www.google.com.br/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwig17C98bnOAhVClZAKHTIqCEkQjRwIBw&url=http%3A%2F%2Fwww.gigabytetls.com%2F%23!servicos%2Fcjg9&psig=AFQjCNFe4nf3QiYJl9hhHxXmvVESfqvE4g&ust=1471022895224204[/img][/url]");
            email.addTo(emailcliente);
            email.send();
        } catch (EmailException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void enviarHatml(String emailcliente) throws MalformedURLException {

        try {
            // Criar a mensagem de e-mail
        HtmlEmail email = new HtmlEmail();
  email.setHostName("org.apache.commons");

  
  email.addTo("jdoe@somewhere.org", "John Doe");
  email.setFrom("me@apache.org", "Me");
  email.setSubject("Test email with inline image");

//incorporar a imagem e obter o ID de conteúdo
            URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
            String cid = email.embed(url, "Apache logo");

            // definir a mensagem HTML
            email.setHtmlMsg("<html>The apache logo - <img src=\"cid:" + cid + "\"></html>");

            // definir a mensagem alternativa
            email.setTextMsg("Your email client does not support HTML messages");

            // enviar o e-mail
            email.send();
        } catch (EmailException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
