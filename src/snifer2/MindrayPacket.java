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
 * @version 2.0
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
    public int clasifydata(ArrayList data,int post) {
        int pos=enca.FindStart(data,post);
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
                //la subtrama se debe sacar en cualquier circustancia, pero si 
                //la trama no tiene el tamaño suficiente la subtrama se cargara con el restane de la subtrama
                
            }
        return ++pos;
    }

    @Override
    public void cargarSubTram(ArrayList data, int pos) {
        Subtrama sub=new Subtrama();
        sub.findstart(pos, data);
        sub.findSize(pos, data);
        sub.findEndh(pos, data);
        int fin=sub.findtoNewSub(pos, data);
        if(fin!=-1){
        sub.addData(pos, fin, data);
        }
    }
    
    
    
    
}
