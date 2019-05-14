package GUI;

import GUI.JFrame_server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static GUI.JFrame_server.*;


public class Listener extends Thread{
    private Socket socket;


    public Listener(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            jTextArea_server.setText("Client Connected");
            DataInputStream din = new DataInputStream(socket.getInputStream());
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());

            String msgin = din.readUTF();
            //Added server's message and client's message to the text area
            jTextArea_server.setText(jTextArea_server.getText().trim() + "\nClient: " + msgin);

            //
            jButton_server.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String msout = "";
                    msout = jTextField_server.getText().trim();
                    jTextArea_server.append("\nServer: " + msout);
                    try {
                        dout.writeUTF(msout);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }catch (IOException e){
            System.out.println("Ooops" + e.getMessage());
        }finally {
            try {
                socket.close();
            }catch (IOException e){
                //TO DO!!!!
            }
        }
    }
}
