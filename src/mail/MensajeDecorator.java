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
public abstract class MensajeDecorator extends Mensaje{
    
    private Mensaje _mensaje;
    
    public MensajeDecorator(){
        
    }
    
    public MensajeDecorator(MensajeDecorator decorator){
        this._mensaje = decorator._mensaje.deepCopy();
    }

    @Override
    public void setUrgent(boolean urgent) {
        this.getMensaje().setUrgent(urgent); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isUrgent() {
        return this.getMensaje().isUrgent(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCategoria(Category categoria) {
        this.getMensaje().setCategoria(categoria); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Category getCategoria() {
        return this.getMensaje().getCategoria(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCuerpo(String cuerpo) {
        this.getMensaje().setCuerpo(cuerpo); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCuerpo() {
        return this.getMensaje().getCuerpo(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAsunto(String asunto) {
        this.getMensaje().setAsunto(asunto); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAsunto() {
        return this.getMensaje().getAsunto(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDestinatarios(ArrayList<UsuarioComponente> destinatarios) {
        this.getMensaje().setDestinatarios(destinatarios); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<UsuarioComponente> getDestinatarios() {
        return this.getMensaje().getDestinatarios(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setRemitente(Usuario remitente) {
        this.getMensaje().setRemitente(remitente); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario getRemitente() {
        return this.getMensaje().getRemitente(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setReadState(MensajeState readState) {
        this.getMensaje().setReadState(readState); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MensajeState getReadState() {
        return this.getMensaje().getReadState(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDatetime(Date datetime) {
        this.getMensaje().setDatetime(datetime); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getDatetime() {
        return this.getMensaje().getDatetime(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setReplyTo(Mensaje replyTo) {
        this.getMensaje().setReplyTo(replyTo); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Mensaje getReplyTo() {
        return this.getMensaje().getReplyTo(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setId(Integer id) {
        this.getMensaje().setId(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getId() {
        return this.getMensaje().getId(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public abstract String getContenido();
    
    public MensajeDecorator(Mensaje mensaje){
        this._mensaje = mensaje;
    }
    
    public Mensaje getMensaje() {
        return _mensaje;
    }

    public void setMensaje(Mensaje _mensaje) {
        this._mensaje = _mensaje;
    }
    
}
