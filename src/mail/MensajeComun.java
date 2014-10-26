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
public class MensajeComun extends Mensaje {
    
    public MensajeComun(){
        super();
    }
    
    public MensajeComun(MensajeComun msgc) {
        super(msgc);
    }

    @Override
    public String getContenido() {
        return this.getCuerpo();
    }

    @Override
    public MensajeComun deepCopy() {
        return new MensajeComun(this);
    }
    
}
