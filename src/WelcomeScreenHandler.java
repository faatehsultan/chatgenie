/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.genie.messenger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author faateh
 */
public class WelcomeScreenHandler implements ActionListener {

    public WelcomeScreen ref;

    public WelcomeScreenHandler(WelcomeScreen src) {
        ref = src;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "LOGIN") {
            if (ref.usernameLogin.getText().length() == 0 || ref.passwordLogin.getPassword().length == 0) {
                displayLoginError();
                return;
            }
            //check if user exist in the userData object in WelcomeScreen class
            if (ref.userDAO.isUserPresent(ref.usernameLogin.getText())
                    && ref.userDAO.verifyCredentials(ref.usernameLogin.getText(), new String(ref.passwordLogin.getPassword()))) {
                ref.usersLoggedIn++;
                new Thread(() -> {
                    new ClientSocket(new ClientGUI(ref.usernameLogin.getText()), Driver.curAdd, Driver.curPort);
                }).start();
                ref.frame.setVisible(true);

                if (ref.usersLoggedIn == 2) {
                    ref.frame.dispose();
                }
            } else {
                displayLoginError();
            }
            ref.usernameLogin.setText("");
            ref.passwordLogin.setText("");
        }
        if (e.getActionCommand() == "SIGNUP") {
            if (ref.usernameSignup.getText().length() == 0 || ref.passwordSignup.getPassword().length == 0) {
                displaySignupError();
                return;
            }
            if (!ref.userDAO.isUserPresent(ref.usernameSignup.getText())) {
                ref.userDAO.addUser(ref.usernameSignup.getText(), new String(ref.passwordSignup.getPassword()));
                displaySignupSuccess();
            } else {
                displaySignupError();
            }
            ref.usernameSignup.setText("");
            ref.passwordSignup.setText("");
        }
    }

    //display error messages for some time
    private void displaySignupSuccess() {
        new Thread(() -> {
            try {
                ref.signup.setText("DONE");
                Thread.sleep(1100);
                ref.signup.setText("SIGNUP");
            } catch (InterruptedException ex) {
                Logger.getLogger(WelcomeScreenHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }

    private void displayLoginError() {
        new Thread(() -> {
            try {
                ref.loginError.setVisible(true);
                Thread.sleep(1100);
                ref.loginError.setVisible(false);
            } catch (InterruptedException ex) {
                Logger.getLogger(WelcomeScreenHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }

    private void displaySignupError() {
        new Thread(() -> {
            try {
                ref.signupError.setVisible(true);
                Thread.sleep(1100);
                ref.signupError.setVisible(false);
            } catch (InterruptedException ex) {
                Logger.getLogger(WelcomeScreenHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }
}
