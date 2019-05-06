package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.imageio.IIOException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author mohammad Hashemi
 */
public class JFrame_server extends javax.swing.JFrame {

    /**
     * Defining needed classes
     */
    static ServerSocket ss;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;



    /**
     * Creates new form JFrame_server
     */
    public JFrame_server() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_server = new javax.swing.JTextArea();
        jTextField_server = new javax.swing.JTextField();
        jButton_server = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);


        jTextArea_server.setColumns(20);
        jTextArea_server.setRows(5);
        jScrollPane1.setViewportView(jTextArea_server);

        jTextField_server.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        jButton_server.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton_server.setText("Send");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jTextField_server, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton_server, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
                                        .addComponent(jScrollPane1))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField_server)
                                        .addComponent(jButton_server, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>

    /**
     * Sending the server message to the client
     * 
     */
    private static void jButton_server_action(){
        jButton_server.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msout = "";
                msout = jTextField_server.getText().trim();
                try {
                    dout.writeUTF(msout);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


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
            java.util.logging.Logger.getLogger(JFrame_server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrame_server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrame_server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrame_server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrame_server().setVisible(true);
            }
        });

        //The message
        String msgin = "";


        try{
            //Listen for client to connect
            ss = new ServerSocket(1201);
            s = ss.accept();
            //Initialize objects with socket input\outPut stream
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            //Chat will continue until someone type 'exit'
            while (!msgin.equals("exit")){
                //get the client message
                msgin = din.readUTF();
                //Added server's message and client's message to the text area
                jTextArea_server.setText(jTextField_server.getText().trim()+"\n");
                jTextArea_server.setText(jTextArea_server.getText().trim()+"\n"+msgin);
            }
        } catch (IIOException e){
            e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }//End of main

    // Variables declaration - do not modify
    private static javax.swing.JButton jButton_server;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea jTextArea_server;
    private static javax.swing.JTextField jTextField_server;
    // End of variables declaration
}//End of class
