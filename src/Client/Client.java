package Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Client extends javax.swing.JFrame {


    // constructor
    public Client() {
        initComponents();
        setVisible(true);
    }

    //constructor
    public Client(Socket socket) {

    }


    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_client = new javax.swing.JTextArea();
        jTextField_client = new javax.swing.JTextField();
        jButton_client = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        setTitle("Client");

        jTextArea_client.setColumns(20);
        jTextArea_client.setRows(5);
        jScrollPane1.setViewportView(jTextArea_client);

        jTextField_client.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        jButton_client.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton_client.setText("Send");

        {
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
        }

        pack();
    }// </editor-fold>

    // added action to button
    static void buttonAction(DataOutputStream output) {
        jButton_client.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String messageOut = jTextField_client.getText();
                jTextArea_client.append("\nClient: " + messageOut);

                try {
                    output.writeUTF(messageOut);

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    // main method
    public static void main(String args[]) {


    } // end of main method

    public void sendMessage(Socket socket, DataInputStream input, DataOutputStream output) throws IOException {

        //call buttonAction method of Client Class
        this.buttonAction(output);
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    while (input.available() > 0) {

                        // assign value of input to a string variable
                        String messageIn = null;

                        messageIn = input.readUTF();
                        Thread.sleep(2000);

                        if (messageIn != null) {
                            jTextArea_client.setText(jTextArea_client.getText() + "\nServer: " + messageIn);
                            System.out.println(Thread.currentThread().getName());

                            if (messageIn.equals("exit")) {
                                break;
                            }
                        }
                    }
                } catch (IOException | InterruptedException e) {
                    //e.printStackTrace();
                }
            }
        });
        thread.start();
    }

                // Variables declaration - do not modify
        static javax.swing.JButton jButton_client;
        static javax.swing.JScrollPane jScrollPane1;
        static javax.swing.JTextArea jTextArea_client;
        static javax.swing.JTextField jTextField_client;
        // End of variables declaration
    }

