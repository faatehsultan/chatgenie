package chat.genie.messenger;

import java.util.Random;

public class Driver {

    static int curPort = (new Random().nextInt(65535 - 1024)) + 1024;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Thread(() -> {
            new Server(9999);
        }).start();
        new Thread(() -> {
            new ClientSocket(new ClientGUI("AmirLiaqat"), "localhost", 9999);
        }).start();
        new Thread(() -> {
            new ClientSocket(new ClientGUI("Bhola0000"), "localhost", 9999);
        }).start();
    }

}
