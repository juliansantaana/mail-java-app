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
public class NoLeidoState implements MensajeState{

    @Override
    public boolean canDelete() {
        //no message can be deleted unless its been read first.
        return false;
    }

    @Override
    public boolean isRead() {
        return false;
    }

    @Override
    public void openMessage(Mensaje ctx) {
        ctx.setReadState(new LeidoState());
    }
    
}
