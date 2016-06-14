/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snifer2;

import java.util.ArrayList;

/**
 * this class is a references to datas fron  trama
 * @author UCDIT
 * @created 17/05/2016
 * @version 1.0
 */
public class Subtrama {
    /**
     * this are attribs fron subtrama
     */
    private ArrayList data=new ArrayList();
    private byte endH;
    private byte size[]=new byte[2];
    private byte start;

    /**
     * this is a constructor of subtrama
     */
    public Subtrama(){
    }

    public ArrayList getData() {
        return data;
    }
    
    public void setData(ArrayList data) {
        this.data = data;
    }

     

    public byte getEnd() {
        return endH;
    }

    public void setEnd(byte end) {
        this.endH = end;
    }

    public byte[] getSize() {
        return size;
    }

    public void setSize(byte size[]) {
        this.size = size;
    }

    public byte getStart() {
        return start;
    }
    
    public void setStart(byte start) {
        this.start = start;
    }
    /**
     * 
     * 
     * @param pos
     * @param array
     * @return 
     */
    public int findstart(int pos, ArrayList array){
    start=(byte)array.get(pos);
    return ++pos;
    }
    /**
     * 
     * 
     * @param pos
     * @param array
     * @return 
     */
    public int findSize(int pos,ArrayList array){
    size[0]=(byte)array.get(pos);
    size[1]=(byte)array.get(++pos);
    return ++pos;
    }
    /**
     * 
     * 
     * @param pos
     * @param array
     * @return 
     */
    public int findEndh(int pos,ArrayList array){    
    endH=(byte)array.get(pos);
    return ++pos;
    }
    /**
     * 
     * 
     * @param posI
     * @param cant
     * @param array
     * @return 
     */
    public int addData(int posI,int cant,ArrayList array){
        int cont=0;
        for(int j=posI;j<array.size()&&cont<cant;j++){
               data.add(array.get(j));
               cont++;
               posI=j;
        }
        return posI;
    }
    
    
    /**
     * 
     */
    public String joinheader(){
        String cad=new String();
        cad=String.format("%01X", start);
        cad=cad.concat(String.format("%01X",size[0]));
        cad=cad.concat(String.format("%01X",size[1]));
        cad=cad.concat(String.format("%01X",endH));
        return String.valueOf(Integer.parseInt(cad, 16));
    }
    /**
     * 
     * 
     * 
     * @return 
     */
    public int sizePSubtram(){
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
    public void printStart(){
    System.out.printf("0x%02X",start);
    System.out.println("--------");
    }
    
    /**
     * 
     * 
     * 
     */
    public void printSize(){
        System.out.printf("0x%02X",size[0]);
        System.out.printf("0x%02X",size[1]);
        System.out.println("----------");
    }
    
    /**
     * 
     * 
     * 
     */
    public void printEnd(){
        System.out.printf("0x%02X",endH);
        System.out.println("-------------");
    }
    
    
    /**
     * 
     * 
     */
    public void printData(){
        for(int j=0;j<data.size();j++){
           System.out.printf("0x%02X",data.get(j));
        }
        System.out.println("--------------");
    }
    
    /**
     * 
     * 
     */
    public void printSub(){
        printStart();
        printSize();
        printEnd();
        printData();
    }

    /**
     * 
     * 
     */
      public int sizeSub(){
      return size.length+2;
      }
      
      /**
       * 
       * 
       */
      
    

}
