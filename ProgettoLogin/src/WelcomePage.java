
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import org.apache.poi.hpsf.Section;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class WelcomePage extends JFrame implements ActionListener {

    JFrame frame = new JFrame();
    JMenuBar menubar;
    JMenu menu;
    JMenu menu2;

    JMenuItem load;
    JMenuItem download;
    JMenuItem nuovo;
    
    JMenuItem disconnetti;

    int cont = 1;

    String[] es = new String[]{"--- Cerca ---"};

    String ID = "";

    String key = Seriale.getKey();

    JTextField NomeFile = new JTextField();
    JTextField CercaFile = new JTextField();

    JLabel NomeFileLabel = new JLabel("Inserisci il nome del file");
    JLabel CercaFileLabel = new JLabel("Nome File da caricare");

    ImageIcon icon = new ImageIcon(getClass().getResource("new.png"));
    ImageIcon icon2 = new ImageIcon(getClass().getResource("load.png"));
    ImageIcon icon3 = new ImageIcon(getClass().getResource("download.png"));
    ImageIcon icon4 = new ImageIcon(getClass().getResource("disconnect-icon.png"));

    JTextArea jt = new JTextArea("Inserire il testo QUI...");
    JComboBox<String> ricerche = new JComboBox<>(es);

    String pathGenerale = "D:/Scuola/Tecnologie/ProgettoLogin/";
    WelcomePage(String nomeUtente) {

        ID = nomeUtente;
        pathGenerale += ID + "/";
        
        File folder = new File(pathGenerale);
        File[] listOfFiles = folder.listFiles();
        for (File f : listOfFiles) {
            if (f.isFile()) {
                ricerche.addItem(f.getName());
            }
        }
        
        
       
       
        System.out.println(nomeUtente);

        ricerche.setBounds(400, 80, 200, 25);
        NomeFile.setBounds(400, 20, 200, 25);

        NomeFileLabel.setBounds(620, 20, 150, 25);
        CercaFileLabel.setBounds(620, 80, 150, 25);

        menubar = new JMenuBar();

        menu = new JMenu("File");
       
        menubar.add(menu);
 
        

        nuovo = new JMenuItem("New File", icon);
        menu.add(nuovo);
        // caricare file da modificare

        load = new JMenuItem("Load", icon2);
        menu.add(load);

        // scarica il file sul pc
        download = new JMenuItem("Download", icon3);
        menu.add(download);
        
        disconnetti = new JMenuItem("Disconnetti", icon4);
        menu.add(disconnetti);
   


        nuovo.addActionListener(this);
        load.addActionListener(this);
        download.addActionListener(this);
        disconnetti.addActionListener(this);
   

        jt.setBounds(20, 20, 350, 400);
        jt.setBorder(new LineBorder(Color.BLACK));
        frame.add(ricerche);
        frame.add(NomeFileLabel);
        frame.add(CercaFileLabel);
        frame.add(jt);
        frame.add(NomeFile);
        frame.add(CercaFile);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setJMenuBar(menubar);
        jt.setLineWrap(true);
    }

    public void AggiornaComboBox(String nome) {

        

        for (int i = 0; i < cont; i++) {
            es[i] = nome + ".txt";
            ricerche.addItem(es[i]);
        }

        frame.add(ricerche);
    }

    // aggiungere elenco file personali
    // criptare
    // possibilita di impostare il nome del  ( TEXTBOX )
    // creare stringa presa da textbox per ricercare il file
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == nuovo) {
            jt.setText("");
        }
        if (e.getSource() == download) {

            // aggiungere il selezionamento della cartella tramite il passaggio UTENTE
            if (!"".equals(NomeFile.getText())) {

                this.AggiornaComboBox(NomeFile.getText());
                String path = pathGenerale;
                path += NomeFile.getText() + ".txt";

                try {
                    File file = new File(path);
                    FileWriter fw = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(fw);
                    String testo = jt.getText();
                    bw.append(testo);
                    bw.flush();
                    bw.close();
                    execCryptDecrypt(Cipher.ENCRYPT_MODE, key, file, file);
                    System.out.println("File txt creato!");
                } catch (IOException event) {
                }
            } else {
                System.out.println("INSERIRE IL NOME DEL FILE");
            }
        }
        if (e.getSource() == load) {

            if (ricerche.getSelectedIndex() != 0) {
                String path = pathGenerale;
                path += ricerche.getSelectedItem();
                char[] in = new char[1100];
                int size = 0;
                try {
                    File file = new File(path);
                    execCryptDecrypt(Cipher.DECRYPT_MODE, key, file, file);
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);
                    size = br.read(in);

                    System.out.print("Caratteri presenti: " + size + " ");
                    System.out.print("Il contenuto del file è il seguente: ");
                    String testo = "";
                    for (int i = 0; i < size; i++) {
                        
                        testo += in[i];
                    }
                    jt.setText(testo);
                    br.close();

                    
                } catch (IOException ev) {

                }
            } else {
                System.out.println("INSERIRE IL NOME DEL FILE DA CERCARE");
            }
        }
        
        if (e.getSource() == disconnetti) {
        
            frame.dispose();
            IDePassword idpassword = new IDePassword();
            LoginWindow LoginPage = new LoginWindow(idpassword.getLoginInfo());
        }
        
        
    }

    private static void execCryptDecrypt(int cipherMode, String key, File inputFile, File outputFile) {
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(cipherMode, secretKey);
            byte[] inputBytes;
            try (FileInputStream inputStream = new FileInputStream(inputFile)) {
                inputBytes = new byte[(int) inputFile.length()];
                inputStream.read(inputBytes);
            }
            byte[] outputBytes = cipher.doFinal(inputBytes);
            try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
                outputStream.write(outputBytes);
            }
        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException | IOException e) {
            e.printStackTrace();
        }
    }
}
