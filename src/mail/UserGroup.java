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
public class UserGroup implements UsuarioComponente {
    
    private int id;
    private String name;
    private String description;
    private ArrayList<UsuarioComponente> users;
    
    public UserGroup(String name){
        this.users = new ArrayList<>();
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<UsuarioComponente> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<UsuarioComponente> users) {
        this.users = users;
    }

    @Override
    public void addMessage(Mensaje message) {
        for(UsuarioComponente user : this.getUsers()){
            user.addMessage(message);
        }
    }
    
}
