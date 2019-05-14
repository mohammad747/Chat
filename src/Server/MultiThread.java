package Server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class MultiThread implements Runnable {

    private Socket socket;

    public MultiThread(Socket socket) {

        this.socket = socket;
    }


    @Override
    public void run() {
        int port = socket.getPort();
        try (
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        ) {


            Server.jButton_Server.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String messageOut = Server.jTextField_Server.getText();
                    Server.jTextArea_Server.append("\nServer: " + messageOut);


                    try {
                        output.writeUTF(messageOut);
                        output.flush();

                    } catch (
                            IOException ex) {
                        ex.printStackTrace();
                    }
                }

            });


            String messageOut = "";

            // Server.buttonAction(output);
            String messageIn = "";
            while (true) {
                messageIn = input.readUTF();
                Server.jTextArea_Server.setText(Server.jTextArea_Server.getText() + "\nClient: " + messageIn);
                output.writeUTF("Echo From Server: " + messageIn);
                // Server.buttonAction(output);
                System.out.println(Thread.currentThread().getName());

                if (messageIn.equals("exit")) {
                    break;
                }

            }

        } catch (IOException e) {

            //e.printStackTrace();
            Server.jTextArea_Server.append("\nClient disconnected");

        } /*catch (InterruptedException e) {
            e.printStackTrace();
        }*/ finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }// end of run method

}// end of class
