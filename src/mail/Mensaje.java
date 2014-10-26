/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author juliansantaana
 */
public abstract class Mensaje {
    
    private Integer id;
    private Usuario remitente;
    private ArrayList<UsuarioComponente> destinatarios;
    private String asunto;
    private String cuerpo;
    private Category categoria;
    private Date datetime = new Date();
    private boolean urgent;
    private MensajeState readState;
    private Mensaje replyTo = null;
    
    public Mensaje(){
        this.id = -1;
        this.remitente = new Usuario();
        this.destinatarios = new ArrayList<UsuarioComponente>();
        this.asunto = "";
        this.cuerpo = "";
        this.categoria = new Category();
        this.urgent = false;
        this.readState = new NoLeidoState();
    }
    
    public Mensaje(Mensaje msg){
        this.id = msg.id;
        this.remitente = msg.remitente;
        this.destinatarios = msg.destinatarios;
        this.asunto = msg.asunto;
        this.cuerpo = msg.cuerpo;
        this.categoria = msg.categoria;
        this.datetime = msg.datetime;
        this.urgent = msg.urgent;
        this.readState = msg.readState;
        this.replyTo = msg.replyTo;
    }
    
    public abstract Mensaje deepCopy();
    
    public abstract String getContenido();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Mensaje getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(Mensaje replyTo) {
        this.replyTo = replyTo;
    }
    
    public boolean canDelete(){
        return this.readState.canDelete();
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
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
