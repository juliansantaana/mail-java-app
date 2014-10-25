/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

/**
 *
 * @author juliansantaana
 */
public abstract class MensajeDecorator extends Mensaje{
    
    private Mensaje _mensaje;
    
    public MensajeDecorator(){
        
    }
    
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
