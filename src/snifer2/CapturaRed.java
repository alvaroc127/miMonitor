/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snifer2;


import java.util.ArrayList;
import java.util.Date;
import org.jnetpcap.*;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;


/**
 * clase de captura de red.
 * @author ELECTRONICA
 * @version 1.0
 * @created 21/06/2016
 */
public class CapturaRed extends Thread{
    /**
     *atributos de captura de red 
     */
    private ArrayList<PcapIf> dispositivos;
    private ArrayList<String> disposti;
    private StringBuilder error=new StringBuilder();
    private ArrayList packetes;
    private PcapIf dispositivo=null;
    private int ban,tamn;
    private MindrayPacket MP;
    private   boolean band=true;
     private boolean ban2=true;
     private int []vectorGu;
    private ArrayList<Trama> packets;
    private String ip1;
    
    /**
     * constructor de captura de red
     */
    public CapturaRed() {
        dispositivos=new ArrayList<PcapIf>();
        disposti=new ArrayList();
        packetes=new ArrayList();
        MP=new MindrayPacket();
        vectorGu=new int[2];
        packets=new ArrayList();
        ip1=null;
    }
    
    
     public ArrayList<PcapIf> getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(ArrayList<PcapIf> dispositivos) {
        this.dispositivos = dispositivos;
    }

    public PcapIf getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(PcapIf dispositivo) {
        this.dispositivo = dispositivo;
    }
    
    
    
    
    /**
     * metodo que obtiene los dispositivos de la red
     */
    public void obteneDispo(){
    ban=Pcap.findAllDevs(dispositivos, error);
        if(ban==Pcap.NOT_OK || dispositivos.isEmpty()){
            System.out.println("error leyendo los dispos");
            System.out.println(error.toString());
        }
    }
    
    /**
     * metodo que lista los dispositivos de la red
     */
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
            //FrameVisual vs=new FrameVisual(dispositivo);
            //Thread hhiloNuevo=new Thread(vs);
            //hhiloNuevo.setPriority(Thread.MAX_PRIORITY);
            //hhiloNuevo.start();
            //vs.setVisible(true);
        }
    }
        
    /**
     * metodo que lista los dispositivos enlazados a la tarjeta de red
     * @param indice se refiere al indice de las tarjetas de red.
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
      * metodo que permite comenzar la captura de los paquetes de red
      * con el dipositivo indicado
      * @param dispo hace referencia al dispositivo de red
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
                
            @Override
            public void nextPacket(PcapPacket paqute, String user) {
                ArrayList packetDat=new ArrayList();
                 int auxiliar[]=new int[2];
                 int tama;
                 ip1="";
                 
                 byte []dip=new byte[4],fip=new byte[4];
                    if(paqute.hasHeader(ip)){
                        fip=paqute.getHeader(ip).source();
                        String fuenteIp= FormatUtils.ip(fip);
                        ip1=fuenteIp;
                       
                    if(buscarIp(fuenteIp)==false&&cargarDirecLoca().contains(fuenteIp)==false){
                            synchronized(disposti){
                            disposti.add(fuenteIp);
                            disposti.notify();
                            }
                        }
                    }
                   if(paqute.hasHeader(TCP.ID)){
                        tama=paqute.size();
                       paqute.getHeader(TCP);
                       if(TCP.getPayloadLength()<9){
                            Thread.yield();
                        }
                       //JBuffer buffer= paqute;
                       if(TCP.getPayloadLength()!=0){
                       byte array[]=TCP.getPayload();
                       
                       System.out.println("fuente :"+TCP.source());
                       System.out.println("Destinio :"+TCP.destination());
                       System.out.println("Paquetes recibido el: "+new Date(paqute.getCaptureHeader().timestampInMillis()));
                       System.out.println("el tamaño del paqute capturado  :"+paqute.getCaptureHeader().caplen());
                       System.out.println("el tamño del paquete original :"+paqute.getCaptureHeader().wirelen());
                       //System.out.println(paqute);
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
                                  // System.out.printf("0x%02X",packetDat.get(i));
                               }
                               //System.out.println("tamaño :"+packetDat.size());
                               packetDat=(ArrayList)packetes.get(0);
                               //System.out.println("tamaño :"+packetDat.size());
                               packetes.remove(0);                            
                               //System.out.println("tamaño almacenado de paquetes :"+packetes.size());
                              // System.out.println("Sepaso a crear");
                               crearPacket(tamn,packetDat);
                                }
                           }
                       }else{
                           if(band==true&&tama!=0){
                               //System.out.println("SE PASO A CREAR Paquetes");
                               crearPacket(tama, packetDat);
                           }
                       }
                       //System.out.println("\n cantidad de paquetes en la trama :"+tama);
                       //System.out.println("este es el tmanio del Array :"+packetDat.size());
                       //System.out.println("cantaidad de datos en plkay"+TCP.getPayloadLength());
                       System.out.println("########################");
                       //System.out.println(user);
                       //System.out.println(paqute.toHexdump());
                   }else{
                    Thread.yield();
                    }
                  }    
                }
            };
           pcap.loop(Pcap.LOOP_INFINITE, jpacketHandler, "useiro Yo");
           pcap.close();     
     }
       
  /**
   * permite contar los paquetes de red que viajan en una de red capturada
   * @param array hace referencia a la carga util de la trama TCP
   * @return el indice de la posicion en la carga util
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
        * permite obtener el tamaño de un paquete Mindray
        * @param array hace referencia a los datos de la carga util 
        * @return un array con el tamaño de paquetes y la cantidad de bytes restantes
        * de la carga util.
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
     * metodo que permite crear los paquetes Mindray que viajen dentro de la carga util del TCP
     * @param numPackets hace referencia al numero de paquetes que se desea crear
     * @param datas son la carga util del fragmento TCP
     * @return una lsita de los paquetes creados
     */
    public ArrayList<Trama> crearPacket(int numPackets,ArrayList datas){
    int pos=0;
    for(int i=0;i<numPackets;i++){
        MindrayPacket packt=new MindrayPacket();
        packt.setFuente(ip1);
        pos=packt.clasifydata(datas,pos);
        synchronized(packets){
            packets.add(packt);
            packets.notify();
            System.out.println("cabezas creadas "+packets.size());
            }
        }
    return packets;
    }
    
    /**
     * metodos que retorna los paquetes mindray creados
     * @return un paquete Mindray
     */
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
    
    
    /**
     * retornas una lista de las ip de los dispositivos 
     * de las Ip
     * @return listas de dispositivos de red
     */

    public ArrayList<String> ipDisposit(){
        if(disposti==null){
        try{
           synchronized(disposti){
            disposti.wait();
           }
            }catch(InterruptedException ie){
            ie.printStackTrace();
            }
        }
    return disposti;
    }
    /**
     * Metodo que busca una Ip dentro de una lista de Ip
     * @param Ip hace referencia a la Ip que se esta buscando
     * @return  un booleano que nos indicara el exito o fracaso de un buscado.
     */
    public boolean buscarIp(String Ip){
        boolean ban=false;
        for(int i=0;i<disposti.size()&&ban==false;i++){
            if(disposti.get(i).equals(Ip)){
            ban=true;
            }
        }
    return ban;
    }
    
   /**
    * entrega un lista de las tarjetas de red que se pueden escanear
    * @return lista de tarjetas de red
    */
   public ArrayList<String> dispositivosDeRed(){
       ArrayList<String> dispsoti=new ArrayList();
       for(int i=0;i<dispositivos.size();i++){
           dispsoti.add(dispositivos.get(i).getDescription());
       }
       return dispsoti;
   }
   /**
    * retorna el dispositivo con la descripcion solicitada
    * @param descrip hace referencia a la descripcion del dispositivo
    * @return  el dispositivo con la descripcion indicada
    */
   public PcapIf buscaDisp(String descrip){
        for(int i=0;i<dispositivos.size();i++){
            if(dispositivos.get(i).getDescription().equals(descrip)){
            dispositivo=dispositivos.get(i);
            }
        }
        return dispositivo;
   }
   /**
    * metodo que retorna la direccion de la maquina local
    * @return un string con la direccion local.
    */
   public String cargarDirecLoca(){
       ArrayList<PcapAddr> actual=(ArrayList<PcapAddr>) dispositivo.getAddresses();
       return actual.get(0).getAddr().toString();
   }
   
   /**
    * metodo que re inserta los paquetes en un lista de paquetes creados
     * @param  mp los paquetes MIndray creados
     */
   public  void insertaPaquete(MindrayPacket mp){
       synchronized(packets){
       packets.notify();
       packets.add(mp);
               }
   }
   
    @Override
    public void run() {
        capturarDatosDeRed(dispositivo);
    }
   
    
    
   
    
}
