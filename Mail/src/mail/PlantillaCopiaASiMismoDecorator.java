/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import java.util.ArrayList;

/**
 *
 * @author juliansantaana
 */
public class PlantillaCopiaASiMismoDecorator extends MensajeDecorator {

    public PlantillaCopiaASiMismoDecorator(Mensaje mensaje) {
        super(mensaje);
    }
    
    public PlantillaCopiaASiMismoDecorator(PlantillaCopiaASiMismoDecorator decorator) {
        super(decorator);
    }

    @Override
    public ArrayList<UsuarioComponente> getDestinatarios() {
        ArrayList<UsuarioComponente> destinatarios = new ArrayList<UsuarioComponente>();
        for (UsuarioComponente uc : super.getDestinatarios()){
            destinatarios.add(uc);
        }
        //adding the current user to send a copy to self
        destinatarios.add(Sistema.getInstance().getCurrentUser());
        
        return destinatarios;
    }
    
    public String getContenido() {
        return this.getMensaje().getContenido();
    }

    @Override
    public Mensaje deepCopy() {
        return new PlantillaCopiaASiMismoDecorator(this);
    }
    
}
