package chat.genie.messenger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket {
    ClientGUI ref;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    //communication vars
    String msgOut = "";
    String msgIn = "";


    public ClientSocket(ClientGUI src, String ipAdd, int port) {
        try {
            ref = src;
            socket = new Socket(ipAdd, port);
            System.out.println("Client connected at port " + port + " to " + ipAdd);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            Thread t = new Thread(this::startC2S);
            t.start();

            startS2C();

            dataOutputStream.close();
            dataInputStream.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startS2C() {
        while(!msgOut.equalsIgnoreCase("quit") && !msgIn.equalsIgnoreCase("quit")) {
            try {
                msgIn = dataInputStream.readUTF();
                System.out.println(msgIn);
                if(msgIn.length() > 0)
                    ref.handler.recieve(msgIn);
                msgIn = "";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void startC2S() {
        while(!msgOut.equalsIgnoreCase("quit") && !msgIn.equalsIgnoreCase("quit")) {
            try {
                if(ref.handler.doAction)
                    msgOut = ref.handler.send();
                
                System.out.println(msgOut);
                if(msgOut.length() > 0)
                    dataOutputStream.writeUTF(ref.username + ": " + msgOut);
                msgOut = "";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
