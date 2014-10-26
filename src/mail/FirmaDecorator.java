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
public class FirmaDecorator extends MensajeDecorator {

    private String firma;

    public FirmaDecorator(){
    }
    
    public FirmaDecorator(Mensaje mensaje) {
        super(mensaje);
    }
    
    public String getContenido() {
        return this.getMensaje().getContenido() + "\n" + this.firma;
    }
    
    public void setFirma(String firma){
        this.firma = firma;
    }
    
    public String getFirma(){
        return this.firma;
    }

    @Override
    public Mensaje deepCopy() {
        return new FirmaDecorator(this);
    }
    
}
