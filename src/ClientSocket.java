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
    boolean continueFlag = true;

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
        while (continueFlag) {
            try {
                Message msg = new Message(dataInputStream.readUTF());
                if (msg.msg.length() > 0) {
                    if (msg.msg.equalsIgnoreCase("quit")) {
                        continueFlag = false;
                    }

                    ref.handler.recieve(msg.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void startC2S() {
        while (continueFlag) {
            try {
                if (ref.handler.doAction) {
                    Message msg = new Message(ref.handler.send());

                    if (msg.msg.length() > 0) {
                        dataOutputStream.writeUTF(msg.toString());
                    }
                }
                System.out.println();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
