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
public class MensajeComun extends Mensaje {

    @Override
    public String getContenido() {
        return this.getCuerpo();
    }
    
}
