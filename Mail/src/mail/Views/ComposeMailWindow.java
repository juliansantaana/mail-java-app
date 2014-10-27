/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail.Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;
import javax.swing.SpinnerDateModel;
import mail.Category;
import mail.FirmaDecorator;
import mail.Mensaje;
import mail.MensajeComun;
import mail.MensajeDecorator;
import mail.PlantillaCopiaASiMismoDecorator;
import mail.Recordatorio;
import mail.Sistema;
import vendor.Autocomplete;

/**
 *
 * @author juliansantaana
 */
public class ComposeMailWindow extends javax.swing.JFrame {

    /**
     * Creates new form ComposeMail
     */
    private static final String COMMIT_ACTION = "commit";
    private COMPOSE_MODE mode;
    private Mensaje message;

    public enum COMPOSE_MODE{
        REMINDER, 
        MESSAGE,
    }
    
    //sub views
    private AddCategoryHelperWindow categoryHelperWindow;
    private AddTemplateHelperWindow templateHelperWindow;
    
    public ComposeMailWindow(Mensaje msg, COMPOSE_MODE mode) {
        initComponents();

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initAutocomplete();

        lblReminder.setVisible(false);
        spinnerTime.setVisible(false);
        
        this.mode = mode;
        
        if (this.mode == COMPOSE_MODE.MESSAGE){
            this.message = new MensajeComun();
        }else if (this.mode == COMPOSE_MODE.REMINDER){
            lblReminder.setVisible(true);
            spinnerTime.setVisible(true);
            
            this.message = new Recordatorio();
        }
        
        if (msg != null) this.message = msg;

        categoryHelperWindow = new AddCategoryHelperWindow();
        categoryHelperWindow.addActionListenerAceptar(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // Your action handling code in here
                Category cat = categoryHelperWindow.getSelectedCategory();
                message.setCategoria(cat);

                categoryHelperWindow.setVisible(false);

                updateCategoriaLabel();
            }
        });
        
        templateHelperWindow = new AddTemplateHelperWindow();
        templateHelperWindow.addActionListenerAceptar(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // Your action handling code in here
                Class c = templateHelperWindow.getSelectedTemplate();
                
                try {
                    Constructor cons = c.getConstructor(new Class[]{ Mensaje.class });
                    message = (Mensaje) cons.newInstance(message);
                } catch (NoSuchMethodException ex) {
                    Logger.getLogger(ComposeMailWindow.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SecurityException ex) {
                    Logger.getLogger(ComposeMailWindow.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(ComposeMailWindow.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(ComposeMailWindow.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(ComposeMailWindow.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(ComposeMailWindow.class.getName()).log(Level.SEVERE, null, ex);
                }

                templateHelperWindow.setVisible(false);
            }
        });
        
        spinnerTime.setModel(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spinnerTime);
        spinnerTime.setEditor(timeEditor);
        spinnerTime.setValue(new Date());
        
        refreshViewFromMessage();
    }

    public ComposeMailWindow(Mensaje message) {
        this(message, COMPOSE_MODE.MESSAGE);
    }
    
    public ComposeMailWindow(COMPOSE_MODE mode){
        this(null, mode);
    }
    
    public ComposeMailWindow(){
        this(null, COMPOSE_MODE.MESSAGE);
    }

    public void refreshViewFromMessage() {
        txtToField.setText(Sistema.getInstance().getMailStringFromUsers(this.message.getDestinatarios()));
        txtAsunto.setText(this.message.getAsunto());
        updateCategoriaLabel();
        updateSignatureField();
        chkboxUrgent.setSelected(this.message.isUrgent());
    }

    private void initAutocomplete() {
        txtToField.setFocusTraversalKeysEnabled(false);

        ArrayList<String> keywords = new ArrayList<String>();

        for (String s : Sistema.getInstance().getUsersNicks()) {
            keywords.add(s.toLowerCase());
        }
        
        //keyword for subordinates of
        for (String s : Sistema.getInstance().getUsersNicks()) {
            keywords.add("*" + s.toLowerCase());
        }

        //using # to specify is a user group
        for (String s : Sistema.getInstance().getSectionNames()) {
            keywords.add("#" + s.toLowerCase());
        }

        //adding keyword for subordinates and superiors
        keywords.add("subordinados");
        keywords.add("superiores");

        Autocomplete autoComplete = new Autocomplete(txtToField, keywords);
        txtToField.getDocument().addDocumentListener(autoComplete);

        // Maps the tab key to the commit action, which finishes the autocomplete
        // when given a suggestion
        txtToField.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT_ACTION);
        txtToField.getActionMap().put(COMMIT_ACTION, autoComplete.new CommitAction());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblNewMessage = new javax.swing.JLabel();
        lblTo = new javax.swing.JLabel();
        txtToField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMessage = new javax.swing.JTextArea();
        btnSend = new javax.swing.JButton();
        lblTo1 = new javax.swing.JLabel();
        txtAsunto = new javax.swing.JTextField();
        chkboxUrgent = new javax.swing.JCheckBox();
        btnAddSignature = new javax.swing.JButton();
        btnAddCategory = new javax.swing.JButton();
        btnAddPlantilla = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtSignature = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblCategory = new javax.swing.JLabel();
        lblPlantilla = new javax.swing.JLabel();
        spinnerTime = new javax.swing.JSpinner();
        lblReminder = new javax.swing.JLabel();

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblNewMessage.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        lblNewMessage.setText("Nuevo Mensaje");

        lblTo.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        lblTo.setText("Para");

        txtToField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtToFieldActionPerformed(evt);
            }
        });

        txtMessage.setColumns(20);
        txtMessage.setRows(5);
        txtMessage.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMessageFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(txtMessage);

        btnSend.setText("Enviar");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        lblTo1.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        lblTo1.setText("Asunto");

        txtAsunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAsuntoActionPerformed(evt);
            }
        });

        chkboxUrgent.setText("Urgente");
        chkboxUrgent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkboxUrgentActionPerformed(evt);
            }
        });

        btnAddSignature.setText("Agregar Firma");
        btnAddSignature.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSignatureActionPerformed(evt);
            }
        });

        btnAddCategory.setText("Categoria");
        btnAddCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCategoryActionPerformed(evt);
            }
        });

        btnAddPlantilla.setText("Agregar Plantilla");
        btnAddPlantilla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPlantillaActionPerformed(evt);
            }
        });

        txtSignature.setEditable(false);
        txtSignature.setBackground(new java.awt.Color(204, 204, 204));
        txtSignature.setColumns(20);
        txtSignature.setRows(5);
        jScrollPane3.setViewportView(txtSignature);

        jLabel1.setText("Categoria: ");

        jLabel2.setText("Plantilla:");

        lblReminder.setText("Programado:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblNewMessage)
                        .addGap(234, 234, 234)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPlantilla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chkboxUrgent)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddSignature)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddCategory)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddPlantilla)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSend))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jSeparator2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblTo)
                                    .addComponent(lblTo1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtToField)
                                    .addComponent(txtAsunto)))
                            .addComponent(jScrollPane3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblReminder)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spinnerTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNewMessage)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lblCategory))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lblPlantilla))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTo)
                    .addComponent(txtToField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTo1)
                    .addComponent(txtAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblReminder))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSend)
                    .addComponent(chkboxUrgent)
                    .addComponent(btnAddSignature)
                    .addComponent(btnAddCategory)
                    .addComponent(btnAddPlantilla))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtToFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtToFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtToFieldActionPerformed

    private void txtAsuntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAsuntoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAsuntoActionPerformed

    private void btnAddSignatureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSignatureActionPerformed
        // TODO add your handling code here:
        String firma = JOptionPane.showInputDialog("Agregar firma: ");

        Mensaje mm = this.message;
        if (mm instanceof MensajeDecorator) {
            while ((mm instanceof FirmaDecorator) == false || (mm instanceof MensajeDecorator) == false) {
                mm = ((MensajeDecorator) mm).getMensaje();
            }
            if (mm instanceof FirmaDecorator) {
                ((FirmaDecorator) mm).setFirma(firma);
            } else {
                this.message = new FirmaDecorator(this.message);
                ((FirmaDecorator) this.message).setFirma(firma);
            }
        } else {
            this.message = new FirmaDecorator(this.message);
            ((FirmaDecorator) this.message).setFirma(firma);
        }

        updateSignatureField();
        //System.out.println(this.message.getContenido());
    }//GEN-LAST:event_btnAddSignatureActionPerformed

    private void updateSignatureField() {
        Mensaje mm = this.message;
        if (mm instanceof MensajeDecorator) {
            while ((mm instanceof FirmaDecorator) == false || (mm instanceof MensajeDecorator) == false) {
                mm = ((MensajeDecorator) mm).getMensaje();
            }
            if (mm instanceof FirmaDecorator) {
                txtSignature.setText(((FirmaDecorator) mm).getFirma());
            }
        }
    }

    private void updateCategoriaLabel() {
        lblCategory.setText(this.message.getCategoria().getName());
    }
    
    private void setReminderTime(){
        Date d = (Date) spinnerTime.getValue();
        Mensaje reminder = this.message;
        while (reminder instanceof MensajeDecorator){
            reminder = ((MensajeDecorator) reminder).getMensaje();
        }
        
        if (reminder instanceof Recordatorio){
            ((Recordatorio)reminder).setAutosendDatetime(d);
        }
    }

    private void txtMessageFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMessageFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMessageFocusLost

    private void btnAddCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCategoryActionPerformed
        // TODO add your handling code here:
        categoryHelperWindow.setVisible(true);
    }//GEN-LAST:event_btnAddCategoryActionPerformed

    private void chkboxUrgentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkboxUrgentActionPerformed
        // TODO add your handling code here:
        this.message.setUrgent(chkboxUrgent.isSelected());
    }//GEN-LAST:event_chkboxUrgentActionPerformed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        // TODO add your handling code here:
        this.message.setCuerpo(txtMessage.getText());
        this.message.setAsunto(txtAsunto.getText());
        this.message.setRemitente(Sistema.getInstance().getCurrentUser());
        this.message.setDestinatarios(Sistema.getInstance().getUsersFromMailString(txtToField.getText()));
        
        if (mode == COMPOSE_MODE.MESSAGE){
            Sistema.getInstance().getCurrentUser().sendEmail(message);
        }else if (mode == COMPOSE_MODE.REMINDER){
            setReminderTime();
            Sistema.getInstance().getCurrentUser().scheduleReminder(message);
        }
        
        
        this.dispose();
    }//GEN-LAST:event_btnSendActionPerformed

    private void btnAddPlantillaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPlantillaActionPerformed
        // TODO add your handling code here:
        templateHelperWindow.setVisible(true);
    }//GEN-LAST:event_btnAddPlantillaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ComposeMailWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ComposeMailWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ComposeMailWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ComposeMailWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ComposeMailWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCategory;
    private javax.swing.JButton btnAddPlantilla;
    private javax.swing.JButton btnAddSignature;
    private javax.swing.JButton btnSend;
    private javax.swing.JCheckBox chkboxUrgent;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblNewMessage;
    private javax.swing.JLabel lblPlantilla;
    private javax.swing.JLabel lblReminder;
    private javax.swing.JLabel lblTo;
    private javax.swing.JLabel lblTo1;
    private javax.swing.JSpinner spinnerTime;
    private javax.swing.JTextField txtAsunto;
    private javax.swing.JTextArea txtMessage;
    private javax.swing.JTextArea txtSignature;
    private javax.swing.JTextField txtToField;
    // End of variables declaration//GEN-END:variables

}
