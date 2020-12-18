package chat.genie.messenger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler implements ActionListener {

    ClientGUI ref;
    public boolean doAction = false;

    public ClientHandler(ClientGUI src) {
        ref = src;
    }

    public String send() {
        String msg = ref.msgBox.getText();
        if (msg.length() > 0) {
            ref.screen.setText(ref.screen.getText() + ref.username + ": " + msg + "\n");
            ref.msgBox.setText("");
            ref.msgBox.requestFocus();
        }
        doAction = false;
        return msg; //returned value will be used by socket to send message to client
    }

    public void recieve(String recievedMsg) {
        ref.screen.setText(ref.screen.getText() + recievedMsg + "\n");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("SEND")) {
            doAction = true;
        }
    }
}
