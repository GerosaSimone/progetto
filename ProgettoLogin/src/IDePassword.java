
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class IDePassword {

    // HashMap ????
    HashMap<String, String> loginInfo = new HashMap<String, String>();

    IDePassword() {

        String path = "D:/Scuola/Tecnologie/ProgettoLogin/registro.txt";
        File file = new File(path);

        ArrayList<String> Dati = new ArrayList<String>();
        ArrayList<String> Utenti = new ArrayList<String>();
        ArrayList<String> Password = new ArrayList<String>();

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
                testo += in[i];
            }

            System.out.println("Contenuto del registro:");
            System.out.println(testo + "\n");
            br.close();

            String s = "";
            for (int i = 0; i < testo.length(); i++) {
                char c = testo.charAt(i);

                if (c != ';') {
                    s += c;
                } else {
                    Dati.add(s);
                    s = "";
                }
            }
            System.out.println("DATI:");
            for (int i = 0; i < Dati.size(); i++) {
                System.out.println(Dati.get(i));
            }

            s = "";
            for (int i = 0; i < Dati.size(); i++) {

                for (int j = 0; j < Dati.get(i).length(); j++) {
                    char c = Dati.get(i).charAt(j);

                    if (c != ',') {
                        s += c;
                    } else {
                        Utenti.add(s);
                        s = "";
                        break;
                    }
                }

            }

            System.out.println("UTENTI");
            for (int i = 0; i < Utenti.size(); i++) {
                System.out.println(Utenti.get(i));
            }

            s = "";
            for (int i = 0; i < Dati.size(); i++) {

                for (int j = 0; j < Dati.get(i).length(); j++) {
                    char c = Dati.get(i).charAt(j);

                    // ciclo per prendere le password
                    if (c == ',') {
                        for (int k = 1; k < Dati.get(i).length() - j; k++) {
                            c = Dati.get(i).charAt(j + k);
                            s += c;
                        }
                        Password.add(s);
                        s = "";
                    }

                }

            }
            System.out.println("Password");
            for (int i = 0; i < Password.size(); i++) {
                System.out.println(Password.get(i));
            }
            for (int i = 0; i < Dati.size(); i++) {
                loginInfo.put(Utenti.get(i), Password.get(i));
            }

        } catch (IOException eve) {

        }

    }

    protected HashMap getLoginInfo() {
        return loginInfo;
    }
}
