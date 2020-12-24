package chat.genie.messenger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    ServerSocket serverSocket;
    Socket soc1, soc2;
    DataInputStream in1, in2;
    DataOutputStream out1, out2;
    //communication vars
    Boolean continueFlag = true;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server listening to port " + port + "...");
            soc1 = serverSocket.accept();
            System.out.println("Client 1 connected at port " + port + ", waiting for client 2...");
            soc2 = serverSocket.accept();
            System.out.println("Client 2 connected at port " + port + ". Communication Start!");

            in1 = new DataInputStream(soc1.getInputStream());
            in2 = new DataInputStream(soc2.getInputStream());
            out1 = new DataOutputStream(soc1.getOutputStream());
            out2 = new DataOutputStream(soc2.getOutputStream());

            Thread temp = new Thread(this::msgFromClient1);
            temp.start();
            msgFromClient2();

            in1.close();
            in2.close();
            out1.close();
            out2.close();
            soc1.close();
            soc2.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void msgFromClient1() {
        while (continueFlag) {
            try {
                String msg = in1.readUTF();
                if (!msg.equalsIgnoreCase("quit") && msg.length() > 0) {
                    out2.writeUTF(msg);
                } else {
                    continueFlag = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void msgFromClient2() {
        while (continueFlag) {
            try {
                String msg = in2.readUTF();
                if (!msg.equalsIgnoreCase("quit") && msg.length() > 0) {
                    out1.writeUTF(msg);
                } else {
                    continueFlag = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
