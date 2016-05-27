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
 * @author ELECTRONICA
 */
public class MindrayPacket {
    /**
     * Atribute of class mindrayPacket
     * 
     */
    private Encabezado enca;
    private ArrayList<Subtrama> subtramas=new ArrayList();
            
    
    public MindrayPacket() {
    }
    
    public Encabezado getEnca() {
        return enca;
    }

    public void setEnca(Encabezado enca) {
        this.enca = enca;
    }

    public ArrayList<Subtrama> getSubtramas() {
        return subtramas;
    }

    public void setSubtramas(ArrayList<Subtrama> subtramas) {
        this.subtramas = subtramas;
    }
    
    

    
    


}
