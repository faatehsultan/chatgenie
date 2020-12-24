package chat.genie.messenger;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.*;

public class ClientGUI extends JFrame {

    JPanel container, screen;
    JTextArea msgBox;
    JButton submit;
    JLabel userLabel;
    Color curColorTheme;
    String username;
    ClientHandler handler;
    Socket soc;
    DataOutputStream out;
    JScrollPane scrollpane;
    GridBagConstraints screenLayoutControl = new GridBagConstraints();

    public ClientGUI(String username_src) {
        username = username_src;
        curColorTheme = RandomColorGenerator.getRandColor();
        initGUI();
    }

    public void initGUI() {
        handler = new ClientHandler(this);

        this.setTitle("Chat Genie 1.0 - (" + username + ")");
        this.setSize(500, 750);
        this.setLayout(null);
        this.setResizable(false);
        //set appropriate location
        if (WelcomeScreen.usersLoggedIn == 1) {
            this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 4 - this.getWidth() / 2,
                    Toolkit.getDefaultToolkit().getScreenSize().height / 2 - this.getHeight() / 2);
        } else {
            this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 4
                    + Toolkit.getDefaultToolkit().getScreenSize().width / 2 - this.getWidth() / 2,
                    Toolkit.getDefaultToolkit().getScreenSize().height / 2 - this.getHeight() / 2);

        }
        container = new JPanel();
        container.setSize(this.getSize());
        container.setBackground(curColorTheme);
        container.setLayout(null);
        this.add(container);

        //top username display
        userLabel = new JLabel(username + " logged in!");
        userLabel.setLocation(25, 0);
        userLabel.setSize(this.getWidth(), 40);
        userLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        userLabel.setForeground(Color.white);
        container.add(userLabel);

        //display screen
        screen = new JPanel();
        screen.setSize(450, 500);
        screen.setLayout(new GridBagLayout());
        screen.setBackground(Color.white);
        screenLayoutControl.insets = new Insets(10, 10, 10, 10);
        screenLayoutControl.fill = GridBagConstraints.HORIZONTAL;
        screenLayoutControl.gridwidth = 1;
        scrollpane = new JScrollPane(screen);
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollpane.setSize(450, 500);
        scrollpane.setLocation(15, 40);
        container.add(scrollpane);

        //msg input box
        msgBox = new JTextArea();
        msgBox.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        msgBox.setBorder(BorderFactory.createLineBorder(Color.white, 8));
        msgBox.setSize(450, 80);
        msgBox.setLocation(15, 555);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                msgBox.requestFocus();
            }
        });
        container.add(msgBox);

        //submit button (or pressing enter will do the same)
        submit = new JButton("SEND");
        submit.setSize(110, 40);
        submit.setFont(new Font("Segoe UI", Font.BOLD, 18));
        submit.setBorder(BorderFactory.createLineBorder(Color.white, 4));
        submit.setLocation(355, 650);
        submit.setBackground(curColorTheme);
        submit.setForeground(Color.white);
        submit.setFocusPainted(false);
        submit.addActionListener(handler);
        container.add(submit);

        //setting visible
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
