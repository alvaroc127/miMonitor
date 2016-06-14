/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snifer2;

/**
 *
 * @author ELECTRONICA
 */



/**
 *
 * @author ELECTRONICA
 */
public class Snifer2 {

    /**
     * @param args the command line arguments
     */
    
    private static CapturaRed capturador=new CapturaRed();
    public static void main(String[] args) {
        // TODO code application logic here
        capturador.obteneDispo();
        capturador.listarDispositivos();
    }
    
}
