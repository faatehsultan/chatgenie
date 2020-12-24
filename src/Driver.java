package chat.genie.messenger;

import java.util.Random;

public class Driver {

    static final int curPort = (new Random().nextInt(65535 - 1024)) + 1024;
    static final String curAdd = "localhost";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Thread(() -> {
            new Server(curPort);
        }).start();
        new WelcomeScreen();
    }

}
