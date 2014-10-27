/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import java.awt.List;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juliansantaana
 */
public class Usuario implements UsuarioComponente{
    
    private String nick;
    private String password;
    private String telefono;
    private String interno;
    private String celular;
    private String mail;
    private ArrayList<Usuario> subordinados;
    private ArrayList<Mensaje> mensajes;
    
    public Usuario(){
        this.subordinados = new ArrayList<>();
        this.mensajes = new ArrayList<>();
    }
    
    public Usuario(String nick, String password){
        this.subordinados = new ArrayList<>();
        this.mensajes = new ArrayList<>();
        
        this.nick = nick;
        //setting default password
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Usuario(String nick){
        this(nick, "password");
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getInterno() {
        return interno;
    }

    public void setInterno(String interno) {
        this.interno = interno;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public ArrayList<Usuario> getSubordinados() {
        return subordinados;
    }

    public void setSubordinados(ArrayList<Usuario> subordinados) {
        this.subordinados = subordinados;
    }
    
    @Override
    public void addMessage(Mensaje message){
        Mensaje cloned = message.deepCopy();
        this.mensajes.add(cloned);
    }
    
    public void deleteMessage(Mensaje message) throws Exception{
        if (message.canDelete()){
            if (this.mensajes.contains(message)){
                this.mensajes.remove(message);
            }
        }else{
            throw new Exception("Message can't be deleted.");
        }
    }
    
    public void scheduleReminder(Mensaje message){
        Sistema.getInstance().addReminder((Recordatorio) message);
    }
    
    public void sendEmail(Mensaje message){
        message.setDatetime(new Date());
        message.setId(Sistema.getInstance().getNextMessageId());
        
        //this.addMessage(message);
        for (UsuarioComponente user : message.getDestinatarios()){
            user.addMessage(message);
        }
    }

    public ArrayList<Mensaje> getMensajes() {
        this.mensajes.sort(new Comparator<Mensaje>(){
            @Override
            public int compare(Mensaje o1, Mensaje o2) {
                return - (o1.getDatetime().compareTo(o2.getDatetime()));
            }
        });
        
        return this.mensajes;
    }
    
    public Mensaje getMensaje(int id){
        Mensaje msg = null;
        
        for (Mensaje m : this.mensajes){
            if (m.getId() == id){
                msg = m;
                break;
            }
        }
        
        return msg;
    }
    
    public enum FIND_MODE{
        BODY, 
        SUBJECT, 
        TO, 
        DATE
    }
    
    public ArrayList<Mensaje> findMessages(FIND_MODE mode, String query){
        ArrayList<Mensaje> messages = new ArrayList<>();
        query = query.toUpperCase();
        
        ArrayList<UsuarioComponente> users = new ArrayList<>();
        if (mode == FIND_MODE.TO){
            users = Sistema.getInstance().getUsersFromMailString(query);
        }
        
        for (Mensaje m : this.mensajes){
            
            if (mode == FIND_MODE.BODY){
                if (m.getCuerpo().toUpperCase().contains(query)){
                    messages.add(m);
                }
            }else if (mode == FIND_MODE.SUBJECT){
                if (m.getAsunto().toUpperCase().contains(query)){
                    messages.add(m);
                }
            }else if (mode == FIND_MODE.TO){
                if (users.size() > 0){
                    UsuarioComponente user = users.get(0);
                    if (m.getDestinatarios().contains(user)){
                        messages.add(m);
                    }
                }
            }else if (mode == FIND_MODE.DATE){
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date date = formatter.parse(query);
                    
                    Calendar cal1 = Calendar.getInstance();
                    Calendar cal2 = Calendar.getInstance();
                    cal1.setTime(date);
                    cal2.setTime(m.getDatetime());
                    boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                                      cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
                    
                    if (sameDay) messages.add(m);
                    
                } catch (ParseException e) {
                        e.printStackTrace();
                }
            }
        }
        
        return messages;
    }
    
}
