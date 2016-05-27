/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snifer2;

import java.util.ArrayList;

/**
 *
 * @author ELECTRONICA
 */
public class Encabezado {
    private byte[] start=new byte[6];
    private byte[] size=new byte[2];
    private byte[] Hi_ze=new byte[8];
    private byte[] crc=new byte[2];
    private byte[] low_zer=new byte[6];
    private byte[] code1=new byte[2];
    private byte[] conts1=new byte[3];
    private byte[] code2=new byte[2];
 

    

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

  

  
    
    
    
}
