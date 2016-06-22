/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snifer2;

import Vista.PrincipalPre;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.beans.binding.Bindings;

/**
 *
 * @author ELECTRONICA
 */
public class Snifer2 {

    /**
     * @param args the command line arguments
     */
    
    //private static CapturaRed capturador=new CapturaRed();
    public static void main(String[] args) {
        //mostrar el frame principal
        //antes del mostrar el frame iniciar la captura de red mostrar
        // sacar los dispositivos de captura de red
        //llevar  el frame lo distribuye a un nuevo frame
        //capturador.obteneDispo();
        //capturador.listarDispositivos();
        PrincipalPre prP=new PrincipalPre();
        //Thread hiloDos=new Thread(prP);
        //hiloDos.start();
        prP.setVisible(true);
    }
    
}
