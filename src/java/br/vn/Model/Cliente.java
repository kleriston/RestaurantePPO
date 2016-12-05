/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vn.Model;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Kleriston
 */
@Entity(name = "cliente")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    @Column(length = 150)
    private String nome;

    @Column(length = 16)
    private String fone1;
    @Column(length = 16)
    private String fone2;
    @Column(length = 40, unique = true)
    private String email;
    @Column
    private String senha;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

    @Deprecated
    public Cliente() {
    }

    public Cliente(String nome, Endereco endereco,
            String fone1, String fone2, String email, String senha) {
        this.nome = nome;
      
        this.endereco = endereco;
        this.fone1 = fone1;
        this.fone2 = fone2;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");

        BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
        this.senha = String.format("%32x", hash);

    }

    public Endereco getEndereco() {
        if (endereco == null) {
            endereco = new Endereco();
        }
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    

    public String getFone1() {
        return fone1;
    }

    public void setFone1(String fone1) {
        this.fone1 = fone1;
    }

    public String getFone2() {
        return fone2;
    }

    public void setFone2(String fone2) {
        this.fone2 = fone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
