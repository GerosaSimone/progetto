
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author christian
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                
      
        String pathDir = "../ProgettoLogin/ARCHIVIO";
        try {
            Files.createDirectories(Paths.get(pathDir));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Cartella utente creata\n");
       
        
        IDePassword idpassword = new IDePassword();
	LoginWindow LoginPage = new LoginWindow(idpassword.getLoginInfo());

	

    }   

}
    

