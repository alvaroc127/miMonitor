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
    
    public int FindStart(ArrayList array,int post){
        String val="15015700";
        String cad=new String();
        byte aux[]=new byte[6];
        boolean ban=true;
        int sali=-1,pos=0,valor,cont=0;
        for(int i=post;i<array.size()&&ban==true;i++){
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

    
    /**
     * This method load the size of packet mindray
     * @param pos is a pos the end start header segment mindray
     * @param array of datas in the packet
     * @return pos the array for datas packet start hi_zero
     */
    public int Findsize(int pos,ArrayList array){
        this.size[0]=(byte)array.get(pos);
        this.size[1]=(byte)array.get(pos+1);  
       // System.out.printf("0x%02X",size[0]);
        //System.out.println("");
        //System.out.printf("0x%02X",size[1]);
    return pos++;
    }
    
    /**
     * This method load the segment cero for header
     * @param pos this actual index the array posicion
     * @param array datas from packet TCP
     * @return  posicion of array for dtas packet crc 
     */
    public int FindHi_zero(int pos,ArrayList array){
        for(int i=0;i<Hi_ze.length;i++){
            Hi_ze[i]=(byte)array.get(pos);
            //System.out.printf("0x%02X",Hi_ze[i]);
            //System.out.println("");
            pos++;
        }
    return pos++;
    }
    
    /**
     * This method load the segment for header,
     * crc is unknowed the funcion
     * @param pos is the posicion actual in array of datas from packet 
     * @param array are datas from packet
     * @return pos actual of array datas in packet
     */
    public int FindCrc(int pos, ArrayList array){
        this.crc[0]=(byte)array.get(pos);
        this.crc[1]=(byte)array.get(++pos);
       //System.out.printf("0x%02X",crc[0]);
       //System.out.printf("0x%02X",crc[1]);
       //System.out.println("---------");
    return pos++;
    }
    
    /**
     * This Method load the zeros low, from packet mindray
     * @param pos
     * @param array
     * @return 
     */
    public int Findlow_zer(int pos, ArrayList array){
        for(int j=0;j<this.low_zer.length;j++){
        low_zer[j]=(byte)array.get(pos);
           //System.out.printf("0x%02X",low_zer[j]);
          //System.out.println("");
        pos++;
        }
       return pos;
    }
    
    /**
     * This methos load the code of function use mindray
     * @param pos this index a posicion actual
     * @param array witch the datas from packet
     * @return  posicion current from array.
     */
    
    public int FindCode1(int pos,ArrayList array){
    this.code1[0]=(byte)array.get(pos);
    this.code1[1]=(byte)array.get(++pos);
    //System.out.printf("0x%02X",code1[0]);
          //System.out.println("");
    //System.out.printf("0x%02X",code2[1]);
    return pos++;
    }
    
    /**
     * 
     * 
     * @param pos
     * @param array
     * @return 
     */
    public int FindConst1(int pos,ArrayList array){
        this.conts1[0]=(byte)array.get(++pos);
        this.conts1[1]=(byte)array.get(++pos);
        this.conts1[2]=(byte)array.get(++pos);
        //System.out.printf("0x%02X",conts1[0]);
          //System.out.println("");
        //System.out.printf("0x%02X",conts1[1]);
        //System.out.println("");
        //System.out.printf("0x%02X",conts1[2]);
        return pos++;
    }
    
    /**
     * 
     * @param pos
     * @param array
     * @return 
     */
    
    public int FindCode2(int pos,ArrayList array){
        this.code2[0]=(byte)array.get(++pos);
        this.code2[1]=(byte)array.get(++pos);
        //System.out.printf("0x%02X",code2[0]);
        //System.out.println("");
        //System.out.printf("0x%02X",code2[1]);
    return pos;
    }
    
    /**
     * 
     * @return 
     */
    public int sizePacket(){
        String var=new String();
        var=String.format("%02X",size[0]);
        var=var.concat(String.format("%02X",size[1]));
        return Integer.parseInt(var, 16);
    }
    
    /**
     *
     * 
     * 
     */
    public void mostrarStart(){
        for(int i=0;i<start.length;i++){
        System.out.printf("0x%02X",start[i]);
        }
        System.out.println("-*-*-*-*-*-*-*-*-*-*");
    }
    
    /**
     * 
     * 
     */
    public void mostrarSize(){
        for(int i=0;i<size.length;i++){
        System.out.printf("0x%02X",size[i]);
        }
        System.out.println("-*-*-*-*-*-*-*-*-*-*");
    }
    
    /**
     * 
     * 
     */
    public void mostrarHi_zero(){
        for(int i=0;i<Hi_ze.length;i++){
        System.out.printf("0x%02X",Hi_ze[i]);
        }
        System.out.println("-*-*-*-*-*-*-*-*-*-*");
    }
    
    /**
     * 
     * 
     * 
     */
    public void mostrarCrc(){
        for(int i=0;i<crc.length;i++){
        System.out.printf("0x%02X",crc[i]);
        }
        System.out.println("-*-*-*-*-*-*-*-*-*-*");
    }
    /**
     * 
     * 
     * 
     */
    public void mostrarLowZero() {
        for(int i=0;i<low_zer.length;i++){
        System.out.printf("0x%02X",low_zer[i]);
        }
        System.out.println("-*-*-*-*-*-*-*-*-*-*");
    }
    
    /**
     * 
     * 
     */
    public void mostrarCode1(){
        for(int i=0;i<code1.length;i++){
        System.out.printf("0x%02X",code1[i]);
        }
        System.out.println("-*-*-*-*-*-*-*-*-*-*");
    }
    /**
     * 
     * 
     */
    
    public void mostrarConst(){
        for(int i=0;i<conts1.length;i++){
        System.out.printf("0x%02X",conts1[i]);
        }
        System.out.println("-*-*-*-*-*-*-*-*-*-*");
    }
    
    /**
     * 
     * 
     */
    public void mostrarCode2(){
        for(int i=0;i<code2.length;i++){
        System.out.printf("0x%02X",code2[i]);
        }
        System.out.println("-*-*-*-*-*-*-*-*-*-*");
    }
    
    /**
     * 
     * 
    */
    
    public int cantSize(){
       int  sie=0;
       sie+=start.length+size.length+Hi_ze.length+
               crc.length+low_zer.length+code1.length+
                conts1.length+code2.length;
       return sie;
    }   
    
    /**
     * 
     * 
     */
    public void printHeader(){
    mostrarStart();
    mostrarSize();
    mostrarHi_zero();
    mostrarCrc();
    mostrarLowZero();
    mostrarCode1();
    mostrarConst();
    mostrarCode2();
    }
    
    
    
    
}
