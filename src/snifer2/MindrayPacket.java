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
    private Header enca;
    private ArrayList<Subtrama> subtramas;
    private int tam;
            
    /**
     * Constructor overload
     */
    public MindrayPacket() {
        enca=new Header();
        subtramas=new ArrayList();
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
     * this is metod of clasification the diferent datas
     * of the packet.
     * @param dat are the datas from trama.
     * @return null.
     */
    
    @Override
    public int clasifydata(ArrayList data, int post) {
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
                pos++;
               int tam1_aux=enca.sizePacket();
               tam=enca.cantSize();
                do{
                     System.out.println(tam1_aux+" esto es ta "+tam);
                    pos=cargarSubTram(data, pos);
                }while(tam<tam1_aux);
                tam=0;
                //la subtrama se debe sacar en cualquier circustancia, pero si 
                
            }
        return pos;
    }

    @Override
    public int cargarSubTram(ArrayList data, int pos) {
        final String h6="2033669";
        final String h7="2099205";
        Subtrama sub=new Subtrama();
        pos=sub.findstart(pos, data);
        pos=sub.findSize(pos, data);
        pos=sub.findEndh(pos, data);
        int tama=sub.sizePSubtram();
        //SI cabeza es ECG6 O ECG 7 duplicar la cantidad quelee
        switch(sub.joinheader()){
            
        case(h6):
            pos=sub.addData(pos, tama*2, data);
            tam+=(tama*2)+sub.sizeSub();
            break;
            
        case(h7):
            pos=sub.addData(pos, tama*2, data);
                tam+=(tama*2)+sub.sizeSub();
             break;
                
           
        default:
            pos=sub.addData(pos, tama, data);
               tam+=tama+sub.sizeSub(); 
            break;
        }
        System.out.println("valor de  "+tam);
        subtramas.add(sub);
        return ++pos;
        //Adicionar la  subtrama
        /**
        int fin=sub.findtoNewSub(pos, data);
        if(fin!=-1){
        sub.addData(pos, fin, data);
        }
        **/
    }
    
   
    
    
}
