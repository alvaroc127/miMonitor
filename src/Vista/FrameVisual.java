/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import snifer2.CapturaPorDispositivo;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jnetpcap.PcapIf;
import snifer2.MindrayPacket;

/**
 *
 * @author ELECTRONICA
 */
public class FrameVisual extends JFrame implements Runnable{
    private final static int COLUMNAS =1;
    private final static int FILAS=3;
    private CapturaPorDispositivo cpd;
    private Thread hiloDispo;
    private ArrayList<PanelVisual> panels=new ArrayList();


    public FrameVisual(PcapIf dispo){
       cpd=new CapturaPorDispositivo(dispo);
       hiloDispo=new Thread(cpd);
       hiloDispo.setPriority(Thread.NORM_PRIORITY);
       hiloDispo.start();
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       getContentPane().setLayout(new GridLayout(FILAS, COLUMNAS));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        for(int i=0;i<FILAS;i++){
          PanelVisual pa=new PanelVisual();
          panels.add(pa);
          getContentPane().add(pa);
        }
    }
    
    public void ClasifiData(MindrayPacket mp){
        final String h1="1101";
        final String h2="2101";
        final String h3="7101";
        final String h4="26081";
        final String h5="19101";
        final String h6="31085";
        final String h7="2085";
        for(int i=0;i<mp.getSubtramas().size();i++){
            switch(mp.getSubtramas().get(i).joinheader()){
                case(h1):
                    panels.get(0).loadGrafic(mp.getSubtramas().get(i).getData());
                break;
                        
                case(h2):
                    panels.get(1).loadGrafic(mp.getSubtramas().get(i).getData());    
                break;
                
                case (h3):
                    panels.get(2).loadGrafic(mp.getSubtramas().get(i).getData());    
                    
                break;
                    
                    
                case(h4):
                    panels.get(3).loadGrafic(mp.getSubtramas().get(i).getData());    
                break;
                    
                case(h5):
                    panels.get(4).loadGrafic(mp.getSubtramas().get(i).getData());    
                    
                break;
            
                case(h6):
                    panels.get(5).loadGrafic(mp.getSubtramas().get(i).getData());    
                break;
                    
                case(h7):
                    panels.get(6).loadGrafic(mp.getSubtramas().get(i).getData());    
                break;  
            }
            
        }
    }
    
    
    
    @Override
    public void run() {
        do{
        ClasifiData(cpd.returnPack());
        }while(true);
    }
}
