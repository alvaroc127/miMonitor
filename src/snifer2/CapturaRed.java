/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snifer2;

import Vista.FrameVisual;
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
public class CapturaRed  {
    private ArrayList<PcapIf> dispositivos=new ArrayList<PcapIf>();
    private StringBuilder error=new StringBuilder();
    private ArrayList packetes=new ArrayList();
    private PcapIf dispositivo=null;
    private int ban,tamn;
    private MindrayPacket MP=new MindrayPacket();
    private   boolean band=true;
     private boolean ban2=true;
     private int []vectorGu=new int[2];
    private ArrayList<Trama> packets=new ArrayList();
    
    
    
    /**
     * 
     */
    public void obteneDispo(){
    ban=Pcap.findAllDevs(dispositivos, error);
        if(ban==Pcap.NOT_OK || dispositivos.isEmpty()){
            System.out.println("error leyendo los dispos");
            System.out.println(error.toString());
        }
    }
    
    /**
     * 
     */
    public void listarDispositivos(){
        String descripcion=null;
        for(int j=1;j<dispositivos.size();j++){
            dispositivo=dispositivos.get(j);
            descripcion=dispositivo.getDescription();
            if(descripcion !=null){
                System.out.println("Nombre del dispositivo :"+dispositivo.getName());
                System.out.println("No :"+j);
                System.out.println("Descripcion del Dispositivo :"+descripcion);
            }
            dispositivosPorEnlace(j);
            FrameVisual vs=new FrameVisual(dispositivo);
            Thread hhiloNuevo=new Thread(vs);
            hhiloNuevo.setPriority(Thread.MAX_PRIORITY);
            hhiloNuevo.start();
            vs.setVisible(true);
        }
    }
        
    /**
     * 
     * @param indice 
     */
       public void dispositivosPorEnlace(int indice){
           ArrayList<PcapAddr> dispo=(ArrayList<PcapAddr>) dispositivos.get(indice).getAddresses();
           PcapAddr dis=null;
           for(int j=0;j<dispo.size();j++){
           dis=dispo.get(j);
            System.out.println("Direccion IP: "+dis.getAddr().toString());
            System.out.println("Mascara de sub red: "+dis.getNetmask().toString());
            }  
       }
       
     /**
      * 
      * 
      * @param dispo 
      */ 
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
        
               
                
                
              
            @Override
            public void nextPacket(PcapPacket paqute, String user) {
                ArrayList packetDat=new ArrayList();
                 int auxiliar[]=new int[2];
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
                       tama=contarPacket(packetDat);
                       if(tama!=0){
                       auxiliar=buscarTamPacket(packetDat);
                        if(auxiliar[0]!=0){
                        band=false;
                        }
                       }
                       if(band==false){
                           if(ban2==true){
                           packetes.add(packetDat);
                            //System.out.println("Julioooooo"+packetes.size());
                            ban2=false;
                            vectorGu=auxiliar;
                            tamn=tama;
                           }else{
                               //System.out.println("auxiliar[0] :"+auxiliar[0]+"auxiliar[1] :"+auxiliar[1]);
                              // System.out.println("se compara :"+(vectorGu[0]-vectorGu[1])+" con :"+TCP.getPayloadLength());
                           if((vectorGu[0]!=0)&&(vectorGu[0]-vectorGu[1])==TCP.getPayloadLength()&&ban2==false){
                              // System.out.println("ENTROOOOLOLOLOLOLOLOLO");
                               band=true;
                               ban2=true;
                               vectorGu=auxiliar;
                               for(int i=0;i<packetDat.size();i++){
                                  ((ArrayList)packetes.get(0)).add(packetDat.get(i));
                                   System.out.printf("0x%02X",packetDat.get(i));
                               }
                               System.out.println("tamaño :"+packetDat.size());
                               packetDat=(ArrayList)packetes.get(0);
                               System.out.println("tamaño :"+packetDat.size());
                               packetes.remove(0);                            
                               System.out.println("tamaño almacenado de paquetes :"+packetes.size());
                               System.out.println("Sepaso a crear");
                               crearPacket(tamn,packetDat);
                                }
                           }
                       }else{
                           if(band==true&&tama!=0){
                               System.out.println("SE PASO A CREAR Paquetes");
                               crearPacket(tama, packetDat);
                           }
                       }
                       //System.out.println("\n cantidad de paquetes en la trama :"+tama);
                       //System.out.println("este es el tmanio del Array :"+packetDat.size());
                       //System.out.println("cantaidad de datos en plkay"+TCP.getPayloadLength());
                       System.out.println("########################");
                       //System.out.println(user);
                       //System.out.println(paqute.toHexdump());
                   }
                }
            };
           pcap.loop(Pcap.LOOP_INFINITE, jpacketHandler, "useiro Yo");
           pcap.close();     
     }
       
     /**
      * 
      * @param array
      * @return 
      */
    public int contarPacket(ArrayList array) {
        String val="15015700";
        String cad=new String();
        int sali=0,valor;
        for(int i=0;i<array.size();i++){ 
            valor=Byte.toUnsignedInt((byte)array.get(i));
            if(valor>9){
               ArrayList lista=descomposeNum(valor);
                for(int x=lista.size()-1;x>=0;x--){
                    cad=cad.concat(String.valueOf((int)lista.get(x)));
                   if(cad.length()==val.length()){
                   if(cad.equals(val)){
                      sali++;
                      String cad1=new String();
                       for(int p=1;p<cad.length();p++){
                           cad1+=cad.charAt(p);
                        }
                       cad=cad1;
                   }else{
                       String cad1=new String();
                       for(int p=1;p<cad.length();p++){
                           cad1+=cad.charAt(p);
                        }
                       cad=cad1;
                 }
               }       
             }   
            }else{
               cad=cad.concat(String.valueOf(Byte.toUnsignedInt((byte)array.get(i))));
               if(cad.length()==val.length()){
                   if(cad.equals(val)){
                       sali++;
                       //System.out.println("Exito");
                        String cad1=new String();
                       for(int p=1;p<cad.length();p++){
                           cad1+=cad.charAt(p);
                        }
                       cad=cad1;
                   }else{
                       String cad1=new String();
                       for(int p=1;p<cad.length();p++){
                           cad1+=cad.charAt(p);
                        }
                       cad=cad1;
                 }
               }
            }
        } 
    return sali;   
    }

    /**
     * 
     * 
     * @param array
     * @return 
     */
     public int[] buscarTamPacket(ArrayList array) {
        String val="15015700";
        String cad=new String();
        int valor,aux,aux1;
        int valore[]=new int[2];
        for(int i=0;i<array.size();i++){
            valor=Byte.toUnsignedInt((byte)array.get(i));
            if(valor>9){
               ArrayList lista=descomposeNum(valor);
                for(int x=lista.size()-1;x>=0;x--){
                    cad=cad.concat(String.valueOf((int)lista.get(x)));
                       if(cad.length()==val.length()){
                   if(cad.equals(val)){
                       //System.out.println("Exito##&&&////&&&&//&&&&&");
                      String cad1=new String();
                       for(int p=1;p<cad.length();p++){
                           cad1+=cad.charAt(p);
                        }
                       cad=cad1;
                   }else{
                       String cad1=new String();
                       for(int p=1;p<cad.length();p++){
                           cad1+=cad.charAt(p);
                        }
                       cad=cad1;
                 }
               }       
             }   
            }else{
               cad=cad.concat(String.valueOf(Byte.toUnsignedInt((byte)array.get(i))));
               if(cad.length()==val.length()){
                   if(cad.equals(val)){
                       MP.getEnca().Findsize(i+1, array);
                       aux=MP.getEnca().sizePacket();
                       aux1=array.size()-((i+1)-6);
                       //System.out.println(aux+" tamanio paquete mas restante tamanio trama "+aux1);
                       //System.out.println(array.subList((i+1)-6, i).toString());
                        if(aux>aux1){
                       valore[0]=aux;
                       valore[1]=aux1;
                            //System.out.println(aux+" (aux)tmaaño packete mas (valore[1]) bytes restantes de la trama "+valore[1]);
                        }
                        String cad1=new String();
                       for(int p=1;p<cad.length();p++){
                           cad1+=cad.charAt(p);
                        }
                       cad=cad1;
                   }else{
                       String cad1=new String();
                       for(int p=1;p<cad.length();p++){
                           cad1+=cad.charAt(p);
                        }
                       cad=cad1;
                 }
               }
            }
        } 
    return  valore;
    }
     
    /**
     * This method return arrayList witch, the number descompose in untis.
     * @param number this is the number of descompose
     * @return  list is a list fron number descompose
     */
    public ArrayList descomposeNum(int number){
        ArrayList descoNum=new ArrayList();
        int aux=number,aux1;
        while(aux>0){
        aux1=aux%10;
        aux=aux/10;
        descoNum.add(aux1);
        }
    return descoNum;
    }  
       
    /**
     * 
     * @param numPackets
     * @param datas
     * @return 
     */
    public ArrayList<Trama> crearPacket(int numPackets,ArrayList datas){
    int pos=0;
    for(int i=0;i<numPackets;i++){
        MindrayPacket packt=new MindrayPacket();
        pos=packt.clasifydata(datas,pos);
        synchronized(packets){
            packets.add(packt);
            packets.notify();
            System.out.println("cabezas creadas "+packets.size());
            }
        }
    return packets;
    }
    
    public MindrayPacket returnPack(){
        if(packets.size()==0){
            synchronized(packets){
             try{
                packets.wait();
                Thread.yield();
                }catch(InterruptedException ie){
                ie.printStackTrace();
                }
            }
        }
        MindrayPacket mp1=(MindrayPacket)packets.get(0);
        packets.remove(0);
        return mp1;
    }
    
}
