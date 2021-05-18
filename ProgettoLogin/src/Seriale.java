
import arduino.Arduino;
import java.security.SecureRandom;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author christian
 */
public class Seriale {

    private static String key = generaKey();

    public Seriale() {
        Arduino a = new Arduino("COM3", 9600);
        if (a.openConnection()) {

            String c = a.serialRead(1);
            if (c == "0") {

                key = generaKey();

            } else {
                key = a.serialRead();

            }
        } else {
            System.out.println("COLLEGARE ARDUINO");
            
        }
    }

    public static String generaKey() {

        String lower = "abcdefghijklmnopqrstuvwxyz";

        String perRandom = lower;
        int lunghezzaRandom = 24;

        SecureRandom sr = new SecureRandom();
        StringBuilder sb = new StringBuilder(lunghezzaRandom);

        for (int b = 0; b < lunghezzaRandom; b++) {
            int randomInt = sr.nextInt(perRandom.length());
            char randomChar = perRandom.charAt(randomInt);
            sb.append(randomChar);

        }

        String s = sb.toString();
        return s;
    }

    public static String getKey() {
        return key;
    }
}
