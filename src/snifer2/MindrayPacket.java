/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snifer2;

import java.util.ArrayList;

/**
 * class mindray Packet, this is a references of model packet mindray from conection,
 * witch system monitor control(Hypervisor)
 * this class permite identify the compnents of packet. 
 * @author UCDIT
 * @created 27/05/2016
 * @version 1.0
 */
public class MindrayPacket implements Trama{
    /**
     * Atribute of class mindrayPacket
     */
    private Header enca=new Header();
    private ArrayList<Subtrama> subtramas=new ArrayList();
            
    /**
     * Constructor overload
     */
    public MindrayPacket() {
    }
    
    public Header getEnca() {
        return enca;
    }

    public void setEnca(Header enca) {
        this.enca = enca;
    }

    public ArrayList<Subtrama> getSubtramas() {
        return subtramas;
    }

    public void setSubtramas(ArrayList<Subtrama> subtramas) {
        this.subtramas = subtramas;
    }

    
      
    /**
     * this is metod o clasification the diferent datas
     * of the packet.
     * @param dat are the datas from trama.
     * @return null.
     */
    
    @Override
    public void clasifydata(ArrayList data) {
        int pos=enca.FindStart(data);
        if(pos!=-1){
            pos++;
            //System.out.println(pos);
            //System.out.println(data.subList(pos-6, pos).toString());
                pos=enca.Findsize(pos, data);
                pos=pos+2;
                pos=enca.FindHi_zero(pos, data);
                pos=enca.FindCrc(pos, data);
                ++pos;
                pos=enca.Findlow_zer(pos, data);
                pos=enca.FindCode1(pos, data);
                pos=enca.FindConst1(pos, data);
                pos=enca.FindCode2(pos, data);
                enca.sizePacket();
            }
    }

  
  
    
    
    
   

    
    


}
