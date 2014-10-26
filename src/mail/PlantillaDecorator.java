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
public class PlantillaDecorator extends MensajeDecorator {

    public PlantillaDecorator(){
    }
    
    public PlantillaDecorator(Mensaje mensaje) {
        super(mensaje);
    }
    
    public String getContenido() {
        return this.getMensaje().getContenido();
    }

    @Override
    public Mensaje deepCopy() {
        return new PlantillaDecorator(this);
    }
    
}
