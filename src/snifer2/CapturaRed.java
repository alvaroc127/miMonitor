/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snifer2;

import java.util.ArrayList;
import java.util.Date;
import org.jnetpcap.*;
import org.jnetpcap.nio.JBuffer;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.JPacketHandler;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;


/**
 *
 * @author ELECTRONICA
 */
public class CapturaRed {
    private ArrayList<PcapIf> dispositivos=new ArrayList<PcapIf>();
    private StringBuilder error=new StringBuilder();
    private PcapIf dispositivo=null;
    private int ban;
    
    
    
    
    
    
    
    public void obteneDispo(){
    ban=Pcap.findAllDevs(dispositivos, error);
        if(ban==Pcap.NOT_OK || dispositivos.isEmpty()){
            System.out.println("error leyendo los dispos");
            System.out.println(error.toString());
        }
    }
    
    public void listarDispositivos(){
        String descripcion=null;
        for(int j=0;j<dispositivos.size();j++){
            dispositivo=dispositivos.get(j);
            descripcion=dispositivo.getDescription();
            if(descripcion !=null){
                System.out.println("Nombre del dispositivo :"+dispositivo.getName());
                System.out.println("No :"+j);
                System.out.println("Descripcion del Dispositivo :"+descripcion);
            }
            dispositivosPorEnlace(j);
        }
        capturarDatosDeRed(dispositivos.get(1));
    }
        
       public void dispositivosPorEnlace(int indice){
           ArrayList<PcapAddr> dispo=(ArrayList<PcapAddr>) dispositivos.get(indice).getAddresses();
           PcapAddr dis=null;
           for(int j=0;j<dispo.size();j++){
           dis=dispo.get(j);
            System.out.println("Direccion IP: "+dis.getAddr().toString());
            System.out.println("Mascara de sub red: "+dis.getNetmask().toString());
            }  
       }
       
       
       public void capturarDatosDeRed(PcapIf dispo){
           int alcanzeCaptura= 64*1024;
           int bandera =Pcap.MODE_PROMISCUOUS;
           int tiemposalida= 10*1000;
           Pcap pcap=Pcap.openLive(dispo.getName(),alcanzeCaptura, bandera, tiemposalida, error);
           if(pcap==null){
               System.out.println("Error abriendo dispositivo de captura"+
                       error.toString());
           }
           System.out.println("datos optenidos del dispositivo");
           System.out.println("des: "+dispo.getDescription());
           PcapPacketHandler<String> jpacketHandler=new PcapPacketHandler<String>() {;
                Tcp TCP=new Tcp();
                Ip4 ip=new Ip4();
                Udp UDP=new Udp();
                MindrayPacket MP=new MindrayPacket();
                
              
            @Override
            public void nextPacket(PcapPacket paqute, String user) {
                ArrayList packetDat=new ArrayList();
                    if(paqute.hasHeader(TCP.ID)){
                        int tama=paqute.size();
                       paqute.getHeader(TCP); 
                       JBuffer buffer= paqute;
                       byte array[]=buffer.getByteArray(0, tama);
                       System.out.println("fuente :"+TCP.source());
                       System.out.println("Destinio :"+TCP.destination());
                       System.out.println("Paquetes recibido el: "+new Date(paqute.getCaptureHeader().timestampInMillis()));
                       System.out.println("el tamaño del paqute capturado  :"+paqute.getCaptureHeader().caplen());
                       System.out.println("el tamño del paquete original :"+paqute.getCaptureHeader().wirelen());
                       System.out.println(paqute);
                       for(int i=0;i<array.length;i++){
                           packetDat.add(array[i]);
                       }
                       MP.clasifydata(packetDat);
                        System.out.println("########################");
                       //System.out.println(user);
                       //System.out.println(paqute.toHexdump());
                   }
                }
            };
           pcap.loop(Pcap.LOOP_INFINITE, jpacketHandler, "useiro Yo");
           pcap.close();     
     }
       
       
       
       
}
