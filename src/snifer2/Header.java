/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snifer2;

import java.util.ArrayList;

/**
 * @author UCDIT
 * @created 27/05/2016
 * @version 1.0
 */
public class Header {
    
    /**
     * Atribute of header
     */
    private byte[] start=new byte[6];
    private byte[] size=new byte[2];
    private byte[] Hi_ze=new byte[8];
    private byte[] crc=new byte[2];
    private byte[] low_zer=new byte[6];
    private byte[] code1=new byte[2];
    private byte[] conts1=new byte[3];
    private byte[] code2=new byte[2];

    
    /**
     * constructor of header overload
     */
    public Header() {
    }
    
    public byte[] getHi_ze() {
        return Hi_ze;
    }

    public void setHi_ze(byte[] Hi_ze) {
        this.Hi_ze = Hi_ze;
    }

    public byte[] getCrc() {
        return crc;
    }

    public void setCrc(byte[] crc) {
        this.crc = crc;
    }

    public byte[] getLow_zer() {
        return low_zer;
    }

    public void setLow_zer(byte[] low_zer) {
        this.low_zer = low_zer;
    }
    public byte[] getCode2() {
        return code2;
    }

    public void setCode2(byte[] code2) {
        this.code2 = code2;
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
     * This method permited the search start,of val from header segment of packet
     *  myndray. in the datas from networck
     * @param array be datas fron networck
     * @return sali is a posicion in the vector.
     */
    
    public int FindStart(ArrayList array){
        String val="15015700";
        String cad=new String();
        byte aux[]=new byte[6];
        boolean ban=true;
        int sali=-1,pos=0,valor;
        for(int i=0;i<array.size()&&ban==true;i++){
            valor=Byte.toUnsignedInt((byte)array.get(i));
            aux[pos]= (byte)array.get(i);
            pos++;
            if(valor>9){
               ArrayList lista=descomposeNum(valor);
                for(int x=lista.size()-1;x>=0;x--){
                    cad=cad.concat(String.valueOf((int)lista.get(x)));
                       if(cad.length()==val.length()){
                   if(cad.equals(val)){
                       //System.out.println("Exito##&&&////&&&&//&&&&&");
                       ban=false;
                       sali=i;
                       this.start=aux;
                       //cargar el resto de segmentos de cabezera
                   }else{
                       String cad1=new String();
                       for(int p=1;p<cad.length();p++){
                           cad1+=cad.charAt(p);
                        }
                       if(pos==6){
                           pos=pos-1;
                       for(int a=0;a+1<aux.length;a++){
                           aux[a]=aux[a+1];
                            }
                       }
                       cad=cad1;
                 }
               }       
             }   
            }else{
               cad=cad.concat(String.valueOf(Byte.toUnsignedInt((byte)array.get(i))));
               if(cad.length()==val.length()){
                   if(cad.equals(val)){
                       ban=false;
                       sali=i;
                       this.start=aux;
                       //System.out.println("Exito");
                   }else{
                      
                       String cad1=new String();
                       for(int p=1;p<cad.length();p++){
                           cad1+=cad.charAt(p);
                        }
                        if(pos==6){
                           pos=pos-1;
                       for(int a=0;a+1<aux.length;a++){
                           aux[a]=aux[a+1];
                                }
                            }
                       cad=cad1;
                 }
               }
            }
        }
    return sali;
    }

  
    
    
    
}
