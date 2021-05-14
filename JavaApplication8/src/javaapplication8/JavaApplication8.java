/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication8;


import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;

import java.security.NoSuchAlgorithmException;

import java.security.SecureRandom;

import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;


public class JavaApplication8 {

 

    

    public static String base64Encode(byte[] array) {

     return Base64.getEncoder().encodeToString(array);
    } 
        

    public static byte[] base64Decode(String buffer) throws Exception {
        return Base64.getDecoder().decode(buffer);
    }
   
    /**
     * @param args the command line arguments
     */

    /**
     * Genera una chiave simmetrica
     *
     * @return La chiave simmetrica generata
     * @throws NoSuchAlgorithmException
     */
    public static SecretKey generateSymmetricKey() throws NoSuchAlgorithmException {
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(128);
        SecretKey key = generator.generateKey();
        return key;
    }

    /**
     * Genera il vettore di inizializzazione
     *
     * @return Il vettore generato
     */
    public static byte[] generateInitVector() {
        SecureRandom random = new SecureRandom();
        byte[] iv = new byte[128 / 8];
        random.nextBytes(iv);
        return iv;
    }

    /**
     * Esegue la criptazione del messaggio
     *
     * @param key Chiave simmetrica
     * @param iv Vettore di inizializzazione
     * @param plaintext testo in chiaro
     * @return testo cifrato
     * @throws java.security.NoSuchAlgorithmException
     * @throws javax.crypto.NoSuchPaddingException
     * @throws java.security.InvalidKeyException
     * @throws javax.crypto.IllegalBlockSizeException
     * @throws javax.crypto.BadPaddingException
     * @throws InvalidAlgorithmParameterException
     */
    public static byte[] encrypt(Key key, byte[] iv, byte[] plaintext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(key.getAlgorithm() + "/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        return cipher.doFinal(plaintext);
    }

    /**
     * Esegue la decriptazione del messaggio
     *
     * @param key Chiave simmetrica
     * @param iv Vettore di inizializzazione
     * @param ciphertext testo cifrato
     * @return Testo in chiaro
     * @throws java.security.NoSuchAlgorithmException
     * @throws javax.crypto.NoSuchPaddingException
     * @throws java.security.InvalidKeyException
     * @throws javax.crypto.IllegalBlockSizeException
     * @throws javax.crypto.BadPaddingException
     * @throws InvalidAlgorithmParameterException
     */
    public static byte[] decrypt(Key key, byte[] iv, byte[] ciphertext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(key.getAlgorithm() + "/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
        return cipher.doFinal(ciphertext);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, Exception {
        String msg = "Questo è il testo da proteggere";

        // genera chiave
        SecretKey k = generateSymmetricKey();
          
        //crypta
        byte[] cryptato=encrypt(k,generateInitVector(),base64Decode(msg));
        
        
        //decrypta
        
        System.out.println(decrypt(k,generateInitVector(),cryptato));
       
        

    }
}
