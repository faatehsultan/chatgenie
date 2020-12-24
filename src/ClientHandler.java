package chat.genie.messenger;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ClientHandler implements ActionListener{

    ClientGUI ref;
    public boolean doAction = false;

    public ClientHandler(ClientGUI src) {
        ref = src;
    }

    public String send() {
        String msg = ref.msgBox.getText();
        if (msg.length() > 0) {
            JLabel tempLabel = new JLabel(msg);
            tempLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            tempLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
            tempLabel.setForeground(Color.white);
            tempLabel.setOpaque(true);
            tempLabel.setLocation(0, 0);
            tempLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            tempLabel.setBackground(ref.curColorTheme);
            ref.screenLayoutControl.gridy++;
            ref.screen.add(tempLabel, ref.screenLayoutControl);
            ref.msgBox.setText("");
            ref.msgBox.requestFocus();
            ref.scrollpane.getVerticalScrollBar().setValue(ref.scrollpane.getVerticalScrollBar().getMaximum());
        }
        doAction = false;
        return new Message(msg, ref.curColorTheme).toString(); //returned value will be used by socket to send message to client
    }

    public void recieve(String recievedMsg) {
        Message msg = new Message(recievedMsg);
        JLabel tempLabel = new JLabel(msg.msg);
        tempLabel.setHorizontalAlignment(SwingConstants.LEFT);
        tempLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        tempLabel.setForeground(Color.white);
        tempLabel.setOpaque(true);
        tempLabel.setLocation(0, 0);
        tempLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        tempLabel.setBackground(msg.msgColor);
        ref.screenLayoutControl.gridy++;
        ref.screen.add(tempLabel, ref.screenLayoutControl);
        ref.scrollpane.getVerticalScrollBar().setValue(ref.scrollpane.getVerticalScrollBar().getMaximum());
        ref.revalidate();
        ref.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("SEND")) {
            doAction = true;
        }
    }
}
