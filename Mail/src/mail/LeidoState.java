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
public class LeidoState implements MensajeState{

    @Override
    public boolean canDelete() {
        //if a message was already read, then it can be deleted
        return true;
    }

    @Override
    public boolean isRead() {
        return true;
    }

    @Override
    public void openMessage(Mensaje ctx) {
        ctx.setReadState(new LeidoState());
    }
    
}
