/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;


import java.awt.GridLayout;
import java.util.ArrayList;
import snifer2.CapturaPorDispositivo;
import javax.swing.JFrame;
import org.jnetpcap.PcapIf;
import snifer2.MindrayPacket;

/**
 *
 * @author ELECTRONICA
 */
public class FrameVisual extends JFrame implements Runnable{
    private final static int COLUMNAS =1;
    private final static int FILAS=7;
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
          PanelVisual pa=new PanelVisual(i);
          Thread hiloP=new Thread(pa);
          hiloP.start();
          panels.add(pa);
          getContentPane().add(pa);
        }
    }
    
    public void ClasifiData(MindrayPacket mp){
        final String h1="4353";
        final String h2="8449";
        final String h3="28929";
        final String h4="1705985";
        final String h5="102657";
        final String h6="2033669";
        final String h7="2099205";
        final String h8="475251";
        final String h9="73746";
        if(mp!=null){
        for(int i=0;i<mp.getSubtramas().size();i++){
            System.out.println("\n valor subtrama :"+mp.getSubtramas().get(i).joinheader());
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
                    
                case(h8):
                    panels.get(7).loadGrafic(mp.getSubtramas().get(i).getData());    
                    break;
                    
                  case(h9):
                    panels.get(8).loadGrafic(mp.getSubtramas().get(i).getData());      
                   break;
                      
                   default: 
                    break;
            }
            
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
