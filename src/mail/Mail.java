/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import java.util.ArrayList;
import mail.Views.InboxWindow;
import mail.Views.MainWindow;

/**
 *
 * @author juliansantaana
 */
public class Mail {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Boolean debug = true;
        
        Usuario juan, pedro, pepe, julian, zhou, nicolas, mafalda, camila, carolina;
        juan = new Usuario("Juan");
        pedro = new Usuario("Pedro");
        pepe = new Usuario("Pepe");
        julian = new Usuario("Julian");
        zhou = new Usuario("Zhou");
        nicolas = new Usuario("Nicolas");
        mafalda = new Usuario("Mafalda");
        camila = new Usuario("Camila");
        carolina = new Usuario("Carolina");
        
        //hardcoding some data
        Sistema.getInstance().getUsers().add(juan);
        Sistema.getInstance().getUsers().add(pedro);
        Sistema.getInstance().getUsers().add(pepe);
        Sistema.getInstance().getUsers().add(julian);
        Sistema.getInstance().getUsers().add(zhou);
        Sistema.getInstance().getUsers().add(nicolas);
        Sistema.getInstance().getUsers().add(mafalda);
        Sistema.getInstance().getUsers().add(camila);
        Sistema.getInstance().getUsers().add(carolina);
        
        UserGroup sector1 = new UserGroup("plantabaja");
        ArrayList<UsuarioComponente> sector1usuarios = new ArrayList<>();
        sector1usuarios.add(juan);
        sector1usuarios.add(julian);
        sector1usuarios.add(mafalda);
        
        sector1.setUsers(sector1usuarios);
        Sistema.getInstance().getSections().add(sector1);
        
        UserGroup sector2 = new UserGroup("plantaalta");
        ArrayList<UsuarioComponente> sector2usuarios = new ArrayList<>();
        sector2usuarios.add(pedro);
        sector2usuarios.add(camila);
        sector2usuarios.add(carolina);
        sector2usuarios.add(zhou);
        
        sector2.setUsers(sector2usuarios);
        Sistema.getInstance().getSections().add(sector2);
        
        //categories
        Sistema.getInstance().getCategories().add(new Category(1, "Reportes", "Reportes1"));
        Sistema.getInstance().getCategories().add(new Category(2, "Cobros", "Cobros1"));
        Sistema.getInstance().getCategories().add(new Category(3, "Ventas", "Ventas1"));
        
        if (debug == false){
            MainWindow mw = new MainWindow();
            mw.setVisible(true);
        }else{
            Usuario user = Sistema.getInstance().Login("pepe", "password");
            if (user != null){
                Sistema.getInstance().setCurrentUser(user);
                InboxWindow iw = new InboxWindow();
                iw.setVisible(true);
                
                iw.refreshMessageList();
            }
        }
        
    }
    
}
