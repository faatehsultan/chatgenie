package chat.genie.messenger;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;
import javax.swing.*;

public class WelcomeScreen {

    JFrame frame;
    JPanel container;
    JTextField usernameLogin, usernameSignup;
    JPasswordField passwordLogin, passwordSignup;
    JButton login, signup;
    JLabel loginError, signupError;
    WelcomeScreenHandler handle;
    //user database object
    UserDAO userDAO;
    public static int usersLoggedIn = 0;

    public WelcomeScreen() {
        userDAO = new UserDAO();
        initGUI();
    }

    public void initGUI() {
        Color c = RandomColorGenerator.getRandColor();

        handle = new WelcomeScreenHandler(this);
        frame = new JFrame("Chat Genie 1.0 - Login or Signup");
        frame.setSize(1000, 600);
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

        //header
        JLabel header = new JLabel("CHAT GENIE 1.0");
        header.setFont(new Font("Trebuchet MS", Font.PLAIN, 50));
        header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setForeground(Color.white);
        header.setSize(container.getWidth(), 50);
        header.setLocation(0, 30);
        container.add(header);

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
        JLabel usernameLabel1 = new JLabel("Username");
        usernameLabel1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        usernameLabel1.setForeground(Color.white);
        usernameLabel1.setSize(120, 50);
        usernameLabel1.setLocation(120, 165);
        container.add(usernameLabel1);
        usernameLogin = new JTextField();
        usernameLogin.setSize(300, 60);
        usernameLogin.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        usernameLogin.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
        usernameLogin.setLocation(120, 200);
        container.add(usernameLogin);

        //password login
        JLabel passwordLabel1 = new JLabel("Password");
        passwordLabel1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        passwordLabel1.setForeground(Color.white);
        passwordLabel1.setSize(120, 50);
        passwordLabel1.setLocation(120, 245);
        container.add(passwordLabel1);
        passwordLogin = new JPasswordField();
        passwordLogin.setSize(300, 60);
        passwordLogin.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        passwordLogin.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
        passwordLogin.setLocation(120, 280);
        container.add(passwordLogin);

        //username Signup
        JLabel usernameLabel2 = new JLabel("Username");
        usernameLabel2.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        usernameLabel2.setForeground(Color.white);
        usernameLabel2.setSize(120, 50);
        usernameLabel2.setLocation(570, 165);
        container.add(usernameLabel2);
        usernameSignup = new JTextField();
        usernameSignup.setSize(300, 60);
        usernameSignup.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        usernameSignup.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
        usernameSignup.setLocation(570, 200);
        container.add(usernameSignup);

        //password signup
        JLabel passwordLabel2 = new JLabel("Password");
        passwordLabel2.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        passwordLabel2.setForeground(Color.white);
        passwordLabel2.setSize(120, 50);
        passwordLabel2.setLocation(570, 245);
        container.add(passwordLabel2);
        passwordSignup = new JPasswordField();
        passwordSignup.setSize(300, 60);
        passwordSignup.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        passwordSignup.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
        passwordSignup.setLocation(570, 280);
        container.add(passwordSignup);

        //login button
        login = new JButton("LOGIN");
        login.setSize(120, 50);
        login.setBorder(BorderFactory.createLineBorder(Color.white, 4));
        login.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
        login.setBackground(c);
        login.setForeground(Color.white);
        login.setLocation(210, 360);
        login.setFocusPainted(false);
        login.setToolTipText("Click here to Login");
        login.addActionListener(handle);
        container.add(login);

        //signup button
        signup = new JButton("SIGNUP");
        signup.setSize(120, 50);
        signup.setBorder(BorderFactory.createLineBorder(Color.white, 4));
        signup.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
        signup.setBackground(c);
        signup.setForeground(Color.white);
        signup.setLocation(660, 360);
        signup.setFocusPainted(false);
        signup.setToolTipText("Click here to Signup");
        signup.addActionListener(handle);
        container.add(signup);

        //login error message label
        loginError = new JLabel("Login Error!");
        loginError.setSize(200, 50);
        loginError.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        loginError.setBackground(c);
        loginError.setForeground(Color.white);
        loginError.setLocation(210, 450);
        loginError.setToolTipText("Verify the credentials!");
        loginError.setVisible(false);
        container.add(loginError);

        //signup error message label
        //login error message label
        signupError = new JLabel("Signup Error!");
        signupError.setSize(200, 50);
        signupError.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        signupError.setBackground(c);
        signupError.setForeground(Color.white);
        signupError.setLocation(660, 450);
        signupError.setToolTipText("Verify the credentials!");
        signupError.setVisible(false);
        container.add(signupError);

        //setting visibility
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
