package GUI;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Mozen
 */
public class JFrame_client extends javax.swing.JFrame {
    /**
     * declare socket, input ,output for connectin
     */
    // declare streams and socket
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;

    /**
     * Creates new form NewJFrame
     */
    public JFrame_client() {
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
        jTextArea_client = new javax.swing.JTextArea();
        jTextField_client = new javax.swing.JTextField();
        jButton_client = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea_client.setEditable(false);
        setTitle("Client");
        setjButton_client_action();

        jTextArea_client.setColumns(20);
        jTextArea_client.setRows(5);
        jScrollPane1.setViewportView(jTextArea_client);

        jTextField_client.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        jButton_client.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton_client.setText("Send");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jTextField_client, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton_client, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField_client)
                                        .addComponent(jButton_client, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>

    // Send message from client to server
    private static void setjButton_client_action(){
        jButton_client.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msgout = "";
                //jTextArea_client.setText();
                msgout = jTextField_client.getText().trim();
                jTextArea_client.append("\nClient: " + msgout);
                try {
                    dout.writeUTF(msgout);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }// end of action button

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
            java.util.logging.Logger.getLogger(JFrame_client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrame_client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrame_client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrame_client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrame_client().setVisible(true);
            }
        });

        try{
            // initialize streams and socket for connect server
            s = new Socket("localhost",1201);
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

            // print message in text area
            String msgin = "";
            while (!msgin.equals("exit")){
                msgin = din.readUTF();
                jTextArea_client.setText(jTextArea_client.getText().trim() +
                        "\nServer: " + msgin);

            }


        }catch (Exception e){
            e.getMessage();
            e.getStackTrace();
        }
    }

    // Variables declaration - do not modify
    private static javax.swing.JButton jButton_client;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea jTextArea_client;
    private static javax.swing.JTextField jTextField_client;
    // End of variables declaration
}// end of JFrame client class


