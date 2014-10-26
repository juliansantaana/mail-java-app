/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author juliansantaana
 */
public class Sistema {
    
    private static final Sistema instance = new Sistema();
    
    private ArrayList<Usuario> Users = new ArrayList<>();
    private ArrayList<Category> Categories = new ArrayList<>();
    private ArrayList<UserGroup> Sections = new ArrayList<>();
    private int messageIdCounter;
   
    private Usuario currentUser;
    
    private Sistema(){
        this.messageIdCounter = 0;
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
        ArrayList<String> allNicks = new ArrayList<>();
        for (Usuario user : this.Users){
            allNicks.add(user.getNick());
        }
        
        return allNicks;
    }
    
    public ArrayList<String> getSectionNames(){
        ArrayList<String> allSections = new ArrayList<>();
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
            }else{
                //then is a user or special keyword
                switch (str) {
                    case "SUBORDINADOS":
                        ArrayList<Usuario> subs = this.currentUser.getSubordinados();
                        users.addAll(subs);
                        break;
                    case "SUPERIORES":
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
    
}
