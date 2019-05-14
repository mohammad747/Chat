package Server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server extends javax.swing.JFrame {


    // constructor
    public Server() {
        initComponents();

    }


    //init component
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_Server = new javax.swing.JTextArea();
        jTextField_Server = new javax.swing.JTextField();
        jButton_Server = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        setTitle("Server");

        jTextArea_Server.setColumns(20);
        jTextArea_Server.setRows(5);
        jScrollPane1.setViewportView(jTextArea_Server);

        jTextField_Server.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        jButton_Server.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton_Server.setText("Send");

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
                                                    .addComponent(jTextField_Server, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jButton_Server, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)))
                                    .addContainerGap())
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField_Server)
                                            .addComponent(jButton_Server, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                                    .addContainerGap())
            );
        }

        pack();
    }// </editor-fold>

    //added action to button
//    public static void  buttonAction(DataOutputStream output){
//
//        jButton_Server.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                new Thread() {
//
//                    public void run() {
//
//                        String messageOut = jTextField_Server.getText();
//                        jTextArea_Server.append("\nServer: " + messageOut);
//
//
//                        try {
//                            output.writeUTF(messageOut);
//
//                        } catch (
//                                IOException ex) {
//                            ex.printStackTrace();
//                        }
//                    }
//                }.start();
//            }
//
//        });
//
//
//    }

    private static Socket lastSocket;

    public static Socket getLastSocket() {
        return lastSocket;
    }

    public static void setLastSocket(Socket socket) {
        lastSocket = socket;
    }

    //main method
    public static void main(String args[]) {

        //create object from server class and set visible true
        Server server = new Server();
        server.setVisible(true);
        setListener();


            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try (ServerSocket serverSocket = new ServerSocket(8818)) {
                        //infinite loop for listening for ever
                        while (true) {
                            Socket socket = null;
                            try {
                                socket = serverSocket.accept();
                                new Thread(new ServerWorker(socket)).start();
                                //jTextArea_Server.append("\nClient Connected");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            lastSocket = socket;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });


            thread.start();

} //end of main method


    private static void setListener() {
        jButton_Server.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String messageOut = Server.jTextField_Server.getText();
                Server.jTextArea_Server.append("\nServer: " + messageOut);

                try (
                        DataOutputStream output = new DataOutputStream(lastSocket.getOutputStream())
                ) {
                    output.writeUTF(messageOut);
                    output.flush();

                } catch (
                        IOException ex) {
                    ex.printStackTrace();
                }
            }

        });

    }

    // Variables declaration - do not modify
    static javax.swing.JButton jButton_Server;
    static javax.swing.JScrollPane jScrollPane1;
    static javax.swing.JTextArea jTextArea_Server;
    static javax.swing.JTextField jTextField_Server;
    // End of variables declaration
}


