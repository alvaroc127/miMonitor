/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenando_prueba;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author ELECTRONICA
 */
public class Conecxion_Envio extends Thread{
    
    
    private Socket clien;
    private DataOutputStream SALIDA;
    private DataInputStream ENTRADA;
    private boolean salid=true;
    private ArrayList lista=new ArrayList();
    private ArrayList ecg1=new ArrayList();
    private int pos1=0;
    private String band=new String();
    public Conecxion_Envio() {
    }
    
    
    public void conectar() throws IOException{
        clien=new Socket("192.168.0.150", 3500);
        System.out.println("conecxion exitosa con"+clien.getInetAddress().getHostAddress());
    }
    
    public void obertenerFlujos() throws IOException{
    SALIDA=new DataOutputStream(clien.getOutputStream());
    SALIDA.flush();
    ENTRADA=new DataInputStream(clien.getInputStream());
    }
    
    
    public   void  procesarConecxion() throws IOException{
        int cont=0;
        int aux=0;
        boolean ban=false;
        ArrayList auxilia=new ArrayList();
    do{
        if(ban==false){
            if(band.length()==7){
                String nueva=new String();
                for(int i=0;i<band.length()-1;i++){
                    nueva+=band.charAt(i+1);
                }
                band=nueva;
            }
        }else{
            if(band.length()==4){
                String nueva=new String();
                for(int i=0;i<band.length()-1;i++){
                    nueva+=band.charAt(i+1);
                }
                band=nueva;
            }
        }
        int a=ENTRADA.readUnsignedByte();
       
        if(a>9&&ban==false){
            cont=a;
        while(cont>0){
            aux=cont%10;
            cont=cont/10;
            auxilia.add(aux);
            }
        for(int j=auxilia.size()-1;j>=0;j--){
            ban=validaBnadera((int)auxilia.get(j),ban);
            if(ban==false){
            if(band.length()==7){
                String nueva=new String();
                for(int i=0;i<band.length()-1;i++){
                    nueva+=band.charAt(i+1);
                    }
                 band=nueva;
                }
              }
            }
        auxilia.clear();
        }else{
            if(a>9&&ban==true){
                cont=a;
                while(cont>0){
                aux=cont%10;
                cont=cont/10;
                auxilia.add(aux);
                }
                for(int p=auxilia.size()-1;p>=0;p--){
                ban=validaBnadera((int)auxilia.get(p),ban);
                if(ban==true){
                    if(band.length()==4){
                        String nueva=new String();
                             for(int i=0;i<band.length()-1;i++){
                    nueva+=band.charAt(i+1);
                    }
                 band=nueva;
                }
              }
             }
                auxilia.clear();
            }else{
              if(a<9){
              ban=validaBnadera(a,ban);
              }
            }
        }
        if(ban==true){
            synchronized(lista){
            lista.add(a);
            lista.notify();
            }
        }
        }while(salid);
    }

    @Override
    public void run() {
       do{
       try{
       sleep(1000);
       enviar();
       }catch(InterruptedException ie){
           System.out.println("se diaparo la exexepcion");
           salid=false;
       }
       catch(IOException ex){
       System.out.println("problemas de envio");
       salid=false;
       }
       }while(salid); 
    }
    
    
    
    public void enviar() throws IOException{
        try{
           byte  data[];
           /*
                data[0] = 1;
                data[1] = 5;
                data[2] = 0;
                data[3] = 0x6a;
                data[4] = 0;
                data[5] = 0;
                data[6] = 0;
                data[7] = 0x18;
                data[8] = 0;
                data[9] = 0;
                data[10] = 0;
                data[11] = 0;
                data[12] = 0;
                data[13] = 0;
                data[14] = 0;
                data[15] = 0;
                data[16] = (byte) 0xfe;
                data[17] = 0x78;
                data[18] = 0;
                data[19] = 0;
                data[20] = 0;
                data[21] = 0;
                data[22] = 0;
                data[23] = 0;
                   */
            SALIDA.write(0);
           SALIDA.flush();
        }catch(IOException ex){
             this.salid=false;
            System.out.println("se disparo la salida");
        }
    
    }
    
    public void ejecutarCliete(){
    try{
    conectar();
    obertenerFlujos();
    start();
    procesarConecxion();
    }catch(IOException ex){
       ex.printStackTrace();
    }finally{
    cerrarConecxion();
    }
    }
    
    public void cerrarConecxion(){
        try{
    clien.close();
    SALIDA.close();
    ENTRADA.close();
        }catch(IOException EX){
            System.out.println("error cerrando");
            EX.printStackTrace();
        }
    }
/**
public  synchronized int procesarDatosecg1(){
         int aux;
    if(limpiarVector2()==true){
      limpiarlstia();
      aux=pos1;
        //System.out.println(ecg1.get(pos1));
        if(pos1>0){
            ecg1.remove(aux--);
            //System.out.println(ecg1.size());
        }
        if(pos1+1==ecg1.size()){
        pos1=0;
        }
        pos1++;
        return  (int)ecg1.get(aux++);
        
    for(int j=pos1;j<ecg1.size();j++){   
         //Esto adiciona a la serie 
         pos1=j;
        }
        *
    }else{
    return 0;
    }
   }
    **/
    /**
    public boolean limpiarVector2(){
        String bandera1="1101";
        String bandera2="2101";
        String bandera3="7101";
        String bandera4="0000";
        int b1=buscarCadena(bandera1);
        int b2=buscarCadena(bandera2);
        //System.out.println(b1+"xxx"+b2);
        if(buscarCadena(bandera1)!=-1&&buscarCadena(bandera2)!=-1&&buscarCadena(bandera3)!=-1&&buscarCadena(bandera4)!=-1){
        if(b1!=-1&&b2!=-1){
            con_Aux(b1, b2);
            //b1=buscarCadena(bandera3);
            if(b2!=-1&&b1!=-1){
            //con_Aux1(b2,b1);
               //b2=buscarCadena(bandera4);
               if(b1!=-1&&b2!=-1){
                   //con_Aux2(b1, b2);
                   
                    }
                }
            }
        }
        if(buscarCadena(bandera1)!=-1&&buscarCadena(bandera2)!=-1&&buscarCadena(bandera3)!=-1&&buscarCadena(bandera4)!=-1){
            return true;
        }else{
        return false;
        }
    }
    
    */
    /**
     public void con_Aux(int inicio,int fin){
         ecg1.clear();
        for(int k=inicio;k<fin;k++){
            ecg1.add(lista.get(k));
        }
    }
    * */
    
      public void buscarCadena(String band){
        String aux;
        boolean ban=true;
        int j=0;
        for(j=0;j<lista.size()&&ban==true;j++){
            if(j+4<lista.size()-1){
                aux=String.valueOf(lista.get(j))+
                String.valueOf(lista.get(j+1))+
                String.valueOf(lista.get(j+2))+
                String.valueOf(lista.get(j+3));
                if(band.equals(aux)){
                    ban=false;
                    //eliminar datos
                }else{
                aux="";
                }
            }
        }
    }
     
      
    
      public boolean validaBnadera(int dato,boolean estado){
          String bandera1="3121101";
          String bandera2="2101";
          if(estado==false){
              band=band.concat(String.valueOf(dato));
                if(band.length()==7){
                 if(band.equals(bandera1)){
                  estado=true;
                  band="";
                 }
            }
          }else{
              if(estado==true){
                   band=band.concat(String.valueOf(dato));
                  if(band.length()==4){
                  if(band.equals(bandera2)){
                    estado=false;
                    System.out.println(band+"xx"+bandera2);
                    band="";
                     }
                  }
              }
          }
          return estado;
      }
      
    
      public synchronized int datosLista(){
          int aux=pos1,p=0;
          if(pos1>0&&pos1<lista.size()){
          lista.remove(aux--);
          }
          
          if(pos1==lista.size()){
              limpiarlstia();
              pos1=0;
              synchronized(lista){
              try{
              lista.wait();
              }catch(InterruptedException ex){
                  System.out.println("interrupcion lanzada");
                }
              }
          }
          p=(int)lista.get(pos1);
          pos1++;
      return p;
      }
      
     public void limpiarlstia(){
    lista.clear();
         System.out.println(lista.size());
    }

    public ArrayList getLista() {
        return lista;
    }

    public void setLista(ArrayList lista) {
        this.lista = lista;
    }

    public ArrayList getEcg1() {
        return ecg1;
    }

    public void setEcg1(ArrayList ecg1) {
        this.ecg1 = ecg1;
    }

    public int getPos1() {
        return pos1;
    }

    public void setPos1(int pos1) {
        this.pos1 = pos1;
    }
     
     
}
