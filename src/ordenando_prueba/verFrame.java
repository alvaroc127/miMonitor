/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenando_prueba;


import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author ELECTRONICA
 */
public class verFrame extends JFrame{
    private Thread hiloSocket;
    
    public verFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(800, 600);
        panel_Visual pa=new panel_Visual();
        hiloSocket=new Thread(pa);
        hiloSocket.start();
        add(pa);
    }
    
}
