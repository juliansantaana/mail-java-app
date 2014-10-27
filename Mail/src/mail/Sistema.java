/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author juliansantaana
 */
public class Sistema {
    
    private static final Sistema instance = new Sistema();
    
    private Timer checkRemindersTimer;
    
    private ArrayList<Usuario> Users = new ArrayList<Usuario>();
    private ArrayList<Category> Categories = new ArrayList<Category>();
    private ArrayList<UserGroup> Sections = new ArrayList<UserGroup>();
    private ArrayList<Recordatorio> Reminders =  new ArrayList<Recordatorio>();
    
    private int messageIdCounter;
   
    private Usuario currentUser;
    
    private Sistema(){
        this.messageIdCounter = 0;
        
        checkRemindersTimer = new Timer();
        checkRemindersTimer.schedule(new CheckReminders(), 0, 5000);
    }
    
    public static Sistema getInstance(){
        return instance;
    }
    
    public Usuario Login(String username, String password){
        for (Usuario user : this.Users) {
            if (user.getNick().toUpperCase().equals(username.toUpperCase())){
                if (user.getPassword().equals(password)){
                    return user;
                }
                break;
            }
        }
        return null;
    }
    
    public Integer getNextMessageId(){
        return ++this.messageIdCounter;
    }
    
    public void setCurrentUser(Usuario user){
        this.currentUser = user;
    }
    
    public Usuario getCurrentUser(){
        return this.currentUser;
    }

    public ArrayList<Usuario> getUsers() {
        return Users;
    }

    public void setUsers(ArrayList<Usuario> Users) {
        this.Users = Users;
    }

    public ArrayList<Category> getCategories() {
        return Categories;
    }

    public void setCategories(ArrayList<Category> Categories) {
        this.Categories = Categories;
    }

    public ArrayList<UserGroup> getSections() {
        return Sections;
    }

    public void setSections(ArrayList<UserGroup> Sections) {
        this.Sections = Sections;
    }
    
    public ArrayList<String> getUsersNicks(){
        ArrayList<String> allNicks = new ArrayList<String>();
        for (Usuario user : this.Users){
            allNicks.add(user.getNick());
        }
        
        return allNicks;
    }
    
    public ArrayList<String> getSectionNames(){
        ArrayList<String> allSections = new ArrayList<String>();
        for (UserGroup ug : this.Sections){
            allSections.add(ug.getName());
        }
        return allSections;
    }
    
    public ArrayList<UsuarioComponente> getUsersFromMailString(String tousers){
        ArrayList<UsuarioComponente> users = new ArrayList<UsuarioComponente>();
        ArrayList<String> splitted = new ArrayList<String>(Arrays.asList(tousers.split(";")));
        
        for (String str : splitted){
            str = str.trim();
            str = str.toUpperCase();
            if (str.startsWith("#")){
                //then is a section
                str = str.substring(1, str.length());
                for (UserGroup group : this.Sections){
                    if (group.getName().toUpperCase().equals(str)){
                        users.add(group);
                        break;
                    }
                }
            } if (str.startsWith("*")){
                //then is subordinates of
                str = str.substring(1, str.length());
                for (Usuario user : this.Users){
                    if (user.getNick().toUpperCase().equals(str)){
                        users.addAll(user.getSubordinados());
                        break;
                    }
                }
            }
            else{
                //then is a user or special keyword
                switch (str) {
                    case "SUBORDINADOS":
                        ArrayList<Usuario> subs = this.currentUser.getSubordinados();
                        users.addAll(subs);
                        break;
                    case "SUPERIORES":
                        for (Usuario user : this.Users){
                            if (user.getSubordinados().contains(this.currentUser)){
                                users.add(user);
                            }
                        }
                        break;
                    default:
                        for (Usuario user : this.Users){
                            if (user.getNick().toUpperCase().equals(str)){
                                users.add(user);
                                break;
                            }
                        }
                        break;
                }
            }
        }
        
        return users;
    }
    
    public String getMailStringFromUsers(ArrayList<UsuarioComponente> users){
        String tousers = "";
        for (UsuarioComponente user : users){
            if (user instanceof Usuario){
                tousers = tousers.concat(((Usuario)user).getNick() + ";");
            }else if (user instanceof UserGroup){
                UserGroup group = (UserGroup) user;
                //tousers = tousers.concat(group.getNicks(";"));
                tousers = tousers.concat("#" + group.getName() + ";");
            }
        }
        
        return tousers;
    }
    
    public void addReminder(Recordatorio rem){
        this.Reminders.add(rem);
    }
    
    private void removeReminder(Recordatorio rem){
        this.Reminders.remove(rem);
    }
    
    private ArrayList<Recordatorio> getRemindersForTime(Date d){
        ArrayList<Recordatorio> reminders = new ArrayList<Recordatorio>();
        for (Recordatorio r : this.Reminders){
            if (r.getAutosendDatetime().equals(d) || r.getAutosendDatetime().before(d)){
                reminders.add(r);
            }
        }
        
        return reminders;
    }
    
    private void executeReminders(Date d){
        ArrayList<Recordatorio> reminders = getRemindersForTime(d);
        for (Recordatorio r : reminders){
            System.out.println("[" + new Date().toString() + "] New reminder found: sending reminder...");
            r.getRemitente().sendEmail(r);
            removeReminder(r);
        }
    }
    
    class CheckReminders extends TimerTask{
        @Override
        public void run() {
            executeReminders(new Date());
        }
    }
    
}
