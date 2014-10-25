/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author juliansantaana
 */
public class Usuario implements UsuarioComponente{
    
    private String nick;
    private String password;
    private String telefono;
    private String interno;
    private String celular;
    private String mail;
    private ArrayList<Usuario> subordinados;
    
    public Usuario(){
        this.subordinados = new ArrayList<>();
    }
    
    public Usuario(String nick, String password){
        this.subordinados = new ArrayList<>();
        this.nick = nick;
        //setting default password
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Usuario(String nick){
        this(nick, "password");
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getInterno() {
        return interno;
    }

    public void setInterno(String interno) {
        this.interno = interno;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public ArrayList<Usuario> getSubordinados() {
        return subordinados;
    }

    public void setSubordinados(ArrayList<Usuario> subordinados) {
        this.subordinados = subordinados;
    }
    
}
