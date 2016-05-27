/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snifer2;

/**
 *
 * @author ELECTRONICA
 */
public class Subtrama {
    
    private byte data[];
    private byte end[];
    private int size;
    private byte start[];

    public Subtrama() {
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public byte[] getEnd() {
        return end;
    }

    public void setEnd(byte[] end) {
        this.end = end;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public byte[] getStart() {
        return start;
    }

    public void setStart(byte[] start) {
        this.start = start;
    }
    
    
}
