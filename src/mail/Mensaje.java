/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import java.util.ArrayList;

/**
 *
 * @author juliansantaana
 */
public abstract class Mensaje {
    
    private Usuario remitente;
    private ArrayList<UsuarioComponente> destinatarios;
    private String asunto;
    private String cuerpo;
    private Category categoria;
    private boolean urgent;
    private MensajeState readState;
    
    public Mensaje(){
        this.readState = new NoLeidoState();
    }
    
    public abstract String getContenido();
    
    public boolean canDelete(){
        return this.readState.canDelete();
    }
    
    public MensajeState getReadState() {
        return readState;
    }

    public void setReadState(MensajeState readState) {
        this.readState = readState;
    }

    public Usuario getRemitente() {
        return remitente;
    }

    public void setRemitente(Usuario remitente) {
        this.remitente = remitente;
    }

    public ArrayList<UsuarioComponente> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(ArrayList<UsuarioComponente> destinatarios) {
        this.destinatarios = destinatarios;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Category getCategoria() {
        return categoria;
    }

    public void setCategoria(Category categoria) {
        this.categoria = categoria;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }
    
}
