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
    private byte size[];
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
    
    public int findstart(int pos, ArrayList array){
    start=(byte)array.get(pos);
    return ++pos;
    }
    
    public int findSize(int pos,ArrayList array){
    size[0]=(byte)array.get(pos);
    size[1]=(byte)array.get(++pos);
    return ++pos;
    }
    
    public int findEndh(int pos,ArrayList array){    
    endH=(byte)array.get(pos);
    return ++pos;
    }
    
    public int findtoNewSub(int pos,ArrayList array){
        boolean ban=true;
        int sali=-1;
        for(int i=pos;i<array.size()&&ban==true;i++){
            if((int)array.get(i)<10){
                ban=false;
                sali=i;
            }
        }
    return sali;
    }
    
    
    public int addData(int posI,int fin,ArrayList array){
        for(int j=posI;j<fin;j++){
               data.add(array.get(j));
        }
        return fin;
    }
    
    
}
