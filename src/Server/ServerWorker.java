package Server;

import Client.LoginGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;


public class ServerWorker implements Runnable {

    HashMap<String, String> userPass = new HashMap<>();

    private Socket socket;
    private LoginGUI loginGUI = new LoginGUI();

    public ServerWorker(Socket socket) {

        this.socket = socket;
        //add user pass to the hash map
        userPass.put("ali", "123");
        userPass.put("amir", "123");
        userPass.put("reza", "123");

    }


    @Override
    public void run() {

        try (
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        ) {

            loginHandelar(output,input);

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


    public void loginHandelar(DataOutputStream outputStream , DataInputStream inputStream) throws IOException{
        String user = inputStream.readUTF();
        String [] u = user.split(":");
        if(userPass.containsKey(u[0])){
            if(userPass.containsValue(u[1])){

                outputStream.writeBoolean(true);
                System.out.println("adfa");

            }else {
                outputStream.writeBoolean(false);
                System.out.println("af");
            }

        }else {
            outputStream.writeBoolean(false);
        }


    }

}// end of class
