package Server;

import Client.LoginGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ServerWorker implements Runnable {

    HashMap<String, String> userPass = new HashMap<>();
    List<String> onlineUsers = new ArrayList<>();

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
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {

            loginHandelar(output, input);

            String messageOut = "";

            // Server.buttonAction(output);
            String messageIn = "";
            while (true) {
                messageIn = input.readUTF();
                Server.jTextArea_Server.setText(Server.jTextArea_Server.getText() + "\nClient: " + messageIn);
                output.writeUTF("Echo From Server: " + messageIn);
                // Server.buttonAction(output);
                System.out.println(Thread.currentThread().getName());
                Server.setLastSocket(this.socket);
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


    public void loginHandelar(DataOutputStream outputStream, DataInputStream inputStream) throws IOException {
        String user = inputStream.readUTF();
        String[] users = user.split(":");
        if (userPass.containsKey(users[0]) && userPass.get(users[0]).equals(users[1])) {

            outputStream.writeBoolean(true);
            onlineUsers.add(users[0]);

        } else {
            outputStream.writeBoolean(false);
        }


    }

}// end of class
