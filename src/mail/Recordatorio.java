/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import java.util.Date;

/**
 *
 * @author juliansantaana
 */
public class Recordatorio extends MensajeComun{
    
    private Date autosendDatetime = null;

    public Date getAutosendDatetime() {
        return autosendDatetime;
    }

    public void setAutosendDatetime(Date autosendDatetime) {
        this.autosendDatetime = autosendDatetime;
    }
    
}
