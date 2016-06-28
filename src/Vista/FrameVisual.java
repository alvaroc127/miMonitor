/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;



import Controlador.ControladorCapRed;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import org.jnetpcap.PcapIf;
import snifer2.CalculadorParametros;
import snifer2.MindrayPacket;

/**
 *
 * @author ELECTRONICA
 */
public class FrameVisual extends JFrame implements Runnable,ActionListener{
    private final static int COLUMNAS =1;
    private final static int FILAS=8;
    private String ip;
    private ArrayList<PanelVisual> panels;
    private ArrayList<CalculadorParametros> calPs;
    private JButton btn;
    
    
 

    public FrameVisual(String ip){
        this.ip=ip;
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       getContentPane().setLayout(new GridLayout(FILAS, COLUMNAS));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        panels=new ArrayList();
        calPs=new ArrayList();
        for(int i=0;i<FILAS-1;i++){
          PanelVisual pa=new PanelVisual(i);
          CalculadorParametros cp=new CalculadorParametros();
          pa.setVisible(false);
          panels.add(pa);
          calPs.add(cp);
          getContentPane().add(pa);
        }
        btn=new JButton("Salida");
        btn.addActionListener(this);
        getContentPane().add(btn);
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
            //System.out.println("\n valor subtrama :"+mp.getSubtramas().get(i).joinheader());
            switch(mp.getSubtramas().get(i).joinheader()){
                case(h1):
                    panels.get(0).loadGrafic(mp.getSubtramas().get(i).getData());
                    calPs.get(0).alamacenarlistaDat(mp.getSubtramas().get(i).getData());
                    calPs.get(0).setValCam(400);
                    if(calPs.get(0).getListaDat().size()>=1536){
                       calPs.get(0).generaPrimFiltroDig();
                      calPs.get(0).getListaDat().clear();
                       calPs.get(0).getDeltas().clear();
                    }
                  panels.get(0).cargaFrecuen(calPs.get(0).getFr());
                  panels.get(0).setVisible(true);
                break;
                        
                case(h2):
                    panels.get(1).loadGrafic(mp.getSubtramas().get(i).getData());
                    panels.get(1).setVisible(true);
                break;
                
                case (h3):
                    panels.get(2).loadGrafic(mp.getSubtramas().get(i).getData());    
                    panels.get(2).setVisible(true);
                break;
                    
                    
                case(h4):
                    panels.get(3).loadGrafic(mp.getSubtramas().get(i).getData());
                    panels.get(3).setVisible(true);
                break;
                    
                case(h5):
                    panels.get(4).loadGrafic(mp.getSubtramas().get(i).getData());
                     calPs.get(4).alamacenarlistaDat(mp.getSubtramas().get(i).getData());
                    calPs.get(4).setValCam(168);
                    if(calPs.get(4).getListaDat().size()>=1536){
                       calPs.get(4).generaPrimFiltroDig();
                       calPs.get(4).buscaMay();
                      calPs.get(4).getListaDat().clear();
                       calPs.get(4).getDeltas().clear();
                    }
                  panels.get(4).cargarMay(calPs.get(4).getMay());
                  panels.get(4).cargaFrecuen(calPs.get(4).getFr());
                  panels.get(4).setVisible(true);
                break;
            
                case(h6):
                    panels.get(5).loadGrafic(mp.getSubtramas().get(i).getData());
                     calPs.get(5).alamacenarlistaDat(mp.getSubtramas().get(i).getData());
                    //calPs.get(5).setValCam(164);
                    if(calPs.get(5).getListaDat().size()>=1536){
                      // calPs.get(5).generaPrimFiltroDig();
                       calPs.get(5).buscaMay();
                       calPs.get(5).buscaMen();
                      calPs.get(5).getListaDat().clear();
                      //calPs.get(5).getDeltas().clear();
                    }
                  panels.get(5).cargarMay(calPs.get(5).getMay());
                  panels.get(5).cargaMen(calPs.get(5).getMen());
                  panels.get(5).setVisible(true);
                break;
                    
                case(h7):
                    panels.get(6).loadGrafic(mp.getSubtramas().get(i).getData());
                    calPs.get(6).alamacenarlistaDat(mp.getSubtramas().get(i).getData());
                    if(calPs.get(6).getListaDat().size()>=1536){
                    calPs.get(6).buscaMay();
                    calPs.get(6).buscaMen();
                    calPs.get(6).getListaDat().clear();
                    //calPs.get(5).getDeltas().clear();
                    }
                    panels.get(6).cargaMen(calPs.get(6).getMen());
                    panels.get(6).cargarMay(calPs.get(6).getMay());
                    panels.get(6).setVisible(true);
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public ArrayList<PanelVisual> getPanels() {
        return panels;
    }

    public void setPanels(ArrayList<PanelVisual> panels) {
        this.panels = panels;
    }

   
    
    
    
    
    
    @Override
    public void run() {
        do{
            //System.out.println("inicio el hilo");
            MindrayPacket mp= ControladorCapRed.Rpacket();
            //System.out.println(mp.getFuente());
            //System.out.println(ip+"la ip");
            if(mp!=null){
            if(ip.equals(mp.getFuente())){
                ClasifiData(mp);
            }else{
                ControladorCapRed.adicionarPacket(mp);
                }
            }
        }while(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn){
            this.setVisible(false);
        }
    }
}
