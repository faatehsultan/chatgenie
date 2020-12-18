package chat.genie.messenger;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

public class LoginSignupScreen {

    JFrame frame;
    JPanel container;
    JTextField usernameLogin, usernameSignup;
    JPasswordField passwordLogin, passwordSignup;
    JButton login, signup;

    public LoginSignupScreen() {
        initGUI();
    }

    public void initGUI() {
        Color c = RandomColorGenerator.getRandColor();
                
        frame = new JFrame("Chat Genie 1.0 - Login or Signup");
        frame.setSize(1000, 700);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("favicon.png").getImage());
        container = new JPanel();
        container.setLayout(null);
        container.setSize(frame.getSize());
        container.setBackground(c);
        container.setForeground(Color.white);
        frame.add(container);

        //login label
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 38));
        loginLabel.setForeground(Color.white);
        loginLabel.setSize(100, 50);
        loginLabel.setLocation(220, 130);
        container.add(loginLabel);

        //signup label
        JLabel signupLabel = new JLabel("Signup");
        signupLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 38));
        signupLabel.setForeground(Color.white);
        signupLabel.setSize(120, 50);
        signupLabel.setLocation(650, 130);
        container.add(signupLabel);

        //username login
        usernameLogin = new JTextField();
        usernameLogin.setSize(300, 60);
        usernameLogin.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        usernameLogin.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
        usernameLogin.setLocation(120, 200);
        container.add(usernameLogin);

        //password login
        passwordLogin = new JPasswordField();
        passwordLogin.setSize(300, 60);
        passwordLogin.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        passwordLogin.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
        passwordLogin.setLocation(120, 280);
        container.add(passwordLogin);

        //username Signup
        usernameSignup = new JTextField();
        usernameSignup.setSize(300, 60);
        usernameSignup.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        usernameSignup.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
        usernameSignup.setLocation(570, 200);
        container.add(usernameSignup);

        //password login
        passwordSignup = new JPasswordField();
        passwordSignup.setSize(300, 60);
        passwordSignup.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        passwordSignup.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
        passwordSignup.setLocation(570, 280);
        container.add(passwordSignup);
        

        //setting visibility
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    public static void main(String[] args) {
        new LoginSignupScreen();
    }
}
