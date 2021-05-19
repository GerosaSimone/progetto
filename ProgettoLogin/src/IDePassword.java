import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class IDePassword {

    // HashMap Collezione di oggetti che ho utilizzato per la gestione dei NomeUtente
    // e delle Password di tutte le persone registrate
    HashMap<String, String> loginInfo = new HashMap<String, String>();

    IDePassword() {

        // Percorso che viene utilizzato per caricare all'interno del programma
        // i dati degli utenti gia registrati in precedenza
        String path = "../ProgettoLogin/ARCHIVIO/registro.txt";
        File file = new File(path);

        // Creazione degli ArrayList che vengono utilizzati per La gestione e lo Split
        // dei dati
        ArrayList<String> Dati = new ArrayList<String>();
        ArrayList<String> Utenti = new ArrayList<String>();
        ArrayList<String> Password = new ArrayList<String>();

        Path p = Paths.get(path);
        // controllo che non ci sia un utente registrato con lo stesso Username
        // Vettore di caratteri che corrispone al numero massimo di caratteri
        // inseribili all'interno della TextArea ( circa... )
        char[] in = new char[1100];
        // corrisponde al numero di caratteri troviati all'interno del file di testo
        int size = 0;
        try {
            // Consentono la lettura del file
            FileReader fr = new FileReader(file); // prendo il file
            BufferedReader br = new BufferedReader(fr);
            size = br.read(in);

            // inserisco all'interno della variabile testo tutti i caratteri trovati
            // all'interno del file
            String testo = "";
            for (int i = 0; i < size; i++) {
                testo += in[i];
            }

            // Visualizzo in Console il Contenuto del Registro attuale
            System.out.println("Contenuto del registro:");
            System.out.println(testo + "\n");
            br.close();

            // Ciclo for utilizzato per la divisione degli utenti 
            // NomeUtente e Password
            String s = "";
            for (int i = 0; i < testo.length(); i++) {
                char c = testo.charAt(i);

                if (c != ';') {
                    s += c;
                } else {
                    // Aggiunta nell'ArrayList dei dati 
                    Dati.add(s);
                    s = "";
                }
            }
            // Visualizzo a schermo i dati attuali
            System.out.println("DATI:");
            for (int i = 0; i < Dati.size(); i++) {
                System.out.println(Dati.get(i));
            }

            // Ciclo for che viene utilizzato per il salvataggio del NomeUtente 
            s = "";
            for (int i = 0; i < Dati.size(); i++) {

                for (int j = 0; j < Dati.get(i).length(); j++) {
                    char c = Dati.get(i).charAt(j);

                    if (c != ',') {
                        s += c;
                    } else {
                        // Aggiunta del NomeUtente all'interno dell'ArrayList 
                        Utenti.add(s);
                        s = "";
                        break;
                    }
                }

            }

            // Visualizzo a schermo tutti gli UTENTI attuali
            System.out.println("UTENTI");
            for (int i = 0; i < Utenti.size(); i++) {
                System.out.println(Utenti.get(i));
            }

            
            // Ciclo for che viene utilizzato per il salvataggio delle Password dei rispettivi Utenti 
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
            
            // Visualizzo a schermo tutte le PASSWORD attuali
            System.out.println("Password");
            for (int i = 0; i < Password.size(); i++) {
                System.out.println(Password.get(i));
            }
            // Inserimento del NomeUtente e della Password all'interno dell'HASHMAP utilizzato per il LOGIN
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
