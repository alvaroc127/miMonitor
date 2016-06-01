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
    private ArrayList data;
    private byte end;
    private byte size[];
    private byte start;

    /**
     * this is a constructor of subtrama
     */
    public Subtrama() {
    }

    public ArrayList getData() {
        return data;
    }

    public void setData(ArrayList data) {
        this.data = data;
    }

     

    public byte getEnd() {
        return end;
    }

    public void setEnd(byte end) {
        this.end = end;
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
    
    return 0;
    }
    
}
