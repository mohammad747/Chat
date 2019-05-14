package Client;

import Client.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MultiClient implements Runnable {

    Socket socket;

    MultiClient (Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {

        try{
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            //call buttonAction method of Client Class
            Client.buttonAction(output);
            while (true) {

                // assign value of input to a string variable
                String messageIn = input.readUTF();
                Client.jTextArea_client.setText(Client.jTextArea_client.getText() + "\nServer: " + messageIn);
                System.out.println(Thread.currentThread().getName());

                if(messageIn.equals("exit")){
                    break;
                }
            }
        }catch (IOException e){

        }finally {
            try {
                socket.close();
            }catch (IOException e){

            }
        }

    }
}
