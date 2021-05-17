/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication9;

import arduino.*;
/**
 *
 * @author gero
 */
public class JavaApplication9 {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Arduino a= new Arduino("COM3",9600);
        String key="";
        
        String c=a.serialRead(1);
        if(c=="0"){
            //generakey
            a.serialWrite(key);           
        }
        else
            key=a.serialRead();
        
        
    }
    
}
