
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginWindow implements ActionListener { 

    boolean controllo = false;
    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Login");
    JButton registerButton = new JButton("Register");
    JButton resetButton = new JButton("Reset");
    JTextField UserID = new JTextField();
    JPasswordField Password = new JPasswordField();
    JLabel UserIDLabel = new JLabel("UserID:");
    JLabel UserPasswordLabel = new JLabel("Password:");
    JLabel Message = new JLabel();
  
    HashMap<String, String> logininfo = new HashMap<String, String>();
    
    LoginWindow(HashMap<String, String> loginInfo2) {

        logininfo = loginInfo2;

        UserIDLabel.setBounds(50, 100, 75, 25);
        UserPasswordLabel.setBounds(50, 150, 75, 25);

        Message.setBounds(160, 250, 250, 35);
        Message.setFont(new Font(null, Font.ITALIC, 15));

        UserID.setBounds(125, 100, 200, 25);
        Password.setBounds(125, 150, 200, 25);

        loginButton.setBounds(125, 200, 100, 25);
        loginButton.addActionListener(this);
        loginButton.setFocusable(false);

        resetButton.setBounds(225, 200, 100, 25);
        resetButton.addActionListener(this);
        resetButton.setFocusable(false);

        registerButton.setBounds(175, 300, 100, 25);
        registerButton.addActionListener(this);
        registerButton.setFocusable(false);

        frame.add(registerButton);
        frame.add(UserIDLabel);
        frame.add(UserPasswordLabel);
        frame.add(Message);
        frame.add(Password);
        frame.add(UserID);
        frame.add(loginButton);
        frame.add(resetButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ???
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == resetButton) {

            UserID.setText("");
            Password.setText("");

        }

        if (e.getSource() == registerButton) {

            String userID = UserID.getText();
            String password = String.valueOf(Password.getPassword());
            ArrayList<String> controlloRegistro = new ArrayList<String>();
            ArrayList<String> controlloUtenti = new ArrayList<String>();
            boolean controlloUtente = false;
            String pathDir = "D:/Scuola/Tecnologie/ProgettoLogin";
            String path = "D:/Scuola/Tecnologie/ProgettoLogin/registro.txt";

            if ((!"".equals(userID)) && (!"".equals(password))) {
                String registrazione = userID + "," + password + ";";
                System.out.println("Utente in registrazione:");
                System.out.println(registrazione);

                
                pathDir += "/" + userID;

                try {
                    Files.createDirectories(Paths.get(pathDir));
                    System.out.println("Cartella utente creata\n");
                } catch (IOException ex) {
                    Logger.getLogger(LoginWindow.class.getName()).log(Level.SEVERE, null, ex);
                }

                

                File file = new File(path);
                controllo = file.exists();

                try {
                    if (controllo) {
                        Path p = Paths.get(path);
                        // controllo che non ci sia un utente registrato con lo stesso Username
                        char[] in = new char[1100];
                        int size = 0;
                        try {
                            FileReader fr = new FileReader(file); // prendo il file
                            BufferedReader br = new BufferedReader(fr);
                            size = br.read(in);

                            String testo = "";
                            for (int i = 0; i < size; i++) {
                                // decripta
                                testo += in[i];
                            }

                            System.out.println("Contenuto del registro:");
                            System.out.println(testo + "\n");

                            int k = 0;
                            String s = "";
                            // inserisce nel vettore tutti i vecchi utenti
                            for (int i = 0; i < testo.length(); i++) {
                                char c = testo.charAt(i);

                                if (c != ';') {
                                    s += c;
                                } else {
                                    controlloRegistro.add(s);
                                    s = "";
                                }
                            }

                            String utente = "";
                            // prendo solo gli username creando un array contenente solo il nome degli utenti
                            for (int i = 0; i < controlloRegistro.size(); i++) {

                                String registro = controlloRegistro.get(i);
                                // stringa contenente ciao,password
                                for (int j = 0; j < registro.length(); j++) {
                                    char r = registro.charAt(j);
                                    if (r != ',') {
                                        utente += r;
                                    } else {
                                        controlloUtenti.add(utente);
                                        utente = "";
                                        break;
                                    }
                                }

                            }

                            br.close();

                        } catch (IOException ev) {

                        }

                        try {
                            FileReader fr = new FileReader(file);
                            BufferedReader br = new BufferedReader(fr);
                            size = br.read(in);

                            String testo = "";
                            for (int i = 0; i < size; i++) {
                                // decripta
                                testo += in[i];
                            }
                            ArrayList<String> split1 = new ArrayList<String>();

                            // inserisce il nuovo utente 
                            for (int i = 0; i < testo.length(); i++) {
                                char c = testo.charAt(i);
                                String s = "";
                                if (c != ';') {
                                    s += c;
                                } else {
                                    split1.add(s);
                                }
                            }
                            br.close();

                            System.out.println("STATO REGISTRO: ");
                            for (int i = 0; i < controlloRegistro.size(); i++) {
                                System.out.println(controlloRegistro.get(i));
                            }
                            System.out.println("UTENTI: ");
                            for (int i = 0; i < controlloUtenti.size(); i++) {
                                System.out.println(controlloUtenti.get(i));
                            }

                        } catch (IOException ev) {

                        }

                        for (int i = 0; i < controlloUtenti.size(); i++) {

                            if (controlloUtenti.get(i).equals(userID)) {
                                controlloUtente = true;
                                System.out.println("UTENTE GIA PRESENTE");
                                i = controlloUtenti.size() + 1;
                            }
                        }
                        if (controlloUtente == false) {
                            Files.write(p, registrazione.getBytes(), StandardOpenOption.APPEND);
                            System.out.println("File txt aggiornato!");
                        }

                    } else {
                        FileWriter wr = new FileWriter(path);
                        wr.write(registrazione);
                        wr.close();
                    }
                } catch (IOException event) {
                }
            } else {
                System.out.println("COMPILARE I CAMPI");
            }
            frame.dispose();
            IDePassword idpassword = new IDePassword();
            LoginWindow LoginPage = new LoginWindow(idpassword.getLoginInfo());
            
        }

        if (e.getSource() == loginButton) {

            String userID = UserID.getText();
            String password = String.valueOf(Password.getPassword());

            if (logininfo.containsKey(userID)) {

                if (logininfo.get(userID).equals(password)) {
                    Message.setForeground(Color.green);
                    Message.setText("Correct Login");
                    frame.dispose();
                   
                    WelcomePage welcomePage = new WelcomePage(userID);
                } else {
                    Message.setForeground(Color.red);
                    Message.setText("Wrong Password");
                }
            } else {
                Message.setForeground(Color.red);
                Message.setText("Username not found");
            }

        }
    }

    



}
