/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.util.ArrayList;
import org.jnetpcap.PcapIf;
import snifer2.CapturaRed;
import snifer2.MindrayPacket;

/**
 *
 * @author ELECTRONICA
 */
public class ControladorCapRed {
    
    private static CapturaRed capr;
    private ArrayList<String> Ips;
    private ArrayList<String> dispostiRed;

    public ControladorCapRed(){
        capr=new CapturaRed();
        //capr.obteneDispo();
        //capr.listarDispositivos();
        //capr.start();
    }

    public CapturaRed getCapr() {
        return capr;
    }

    public void setCapr(CapturaRed capr) {
        this.capr = capr;
    }

    public ArrayList<String> getIps() {
        return Ips;
    }

    public void setIps(ArrayList<String> Ips) {
        this.Ips = Ips;
    }

    
    
    public void cargarDispositivos(){
        Ips=capr.ipDisposit();
    }
    
    public String dispositivo(int indice){
        if(Ips!=null){
            return Ips.get(indice);
        }else{
        return null;
        }
    }
    
    public synchronized static MindrayPacket Rpacket(){
    return capr.returnPack();
    }

    public ControladorCapRed(CapturaRed capr, ArrayList<String> Ips) {
        this.capr = capr;
        this.Ips = Ips;
    }
    public void iniciarCapt(){
    capr.obteneDispo();
    capr.listarDispositivos();
    dispoTRed();
    }
    public void dispoTRed(){
        this.dispostiRed=capr.dispositivosDeRed();
    }

    public ArrayList<String> getDispostiRed() {
        return dispostiRed;
    }

    public void setDispostiRed(ArrayList<String> dispostiRed) {
        this.dispostiRed = dispostiRed;
    }
    
    public PcapIf buscaDisp(String descrip){
    return capr.buscaDisp(descrip);
    }
    
    public void capturararDe(PcapIf dispo){
        capr.setDispositivo(dispo);
        capr.start();
    }
    
}
