/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snifer2;

import java.util.ArrayList;

/**
 * @author UCDIT
 * @version 1.0
 * @created 22/06/2016
 */
public class CalculadorParametros {
    
    /**
     * Atributos de la calculadora de parametros
     */
    private ArrayList listaDat;
    private int valCam;
    private ArrayList deltas;
    private int calProm;
    private int Fr;
    private int May;
    private int Men;
    
    /**
     * Constructor de la clase CalculadorParametros
     */
    public CalculadorParametros(ArrayList datas) {
        listaDat=datas;
    }

    public CalculadorParametros() {
         listaDat=new ArrayList();
         deltas=new ArrayList();
    }
    
    public CalculadorParametros(int val) {
        listaDat=new ArrayList();
        valCam=val;
    }

    public ArrayList getDeltas() {
        return deltas;
    }

    public void setDeltas(ArrayList deltas) {
        this.deltas = deltas;
    }

    public int getCalProm() {
        return calProm;
    }

    public void setCalProm(int calProm) {
        this.calProm = calProm;
    }

    public int getFr() {
        return Fr;
    }

    public void setFr(int Fr) {
        this.Fr = Fr;
    }
    
    
    public int getValCam() {
        return valCam;
    }

    public void setValCam(int valCam) {
        this.valCam = valCam;
    }
    
    public ArrayList getListaDat() {
        return listaDat;
    }

    public void setListaDat(ArrayList listaDat) {
        this.listaDat = listaDat;
    }

    public int getMay() {
        return May;
    }

    public void setMay(int May) {
        this.May = May;
    }

    public int getMen() {
        return Men;
    }

    public void setMen(int Men) {
        this.Men = Men;
    }
    
    
    
    
    /**
     * metodo que adiciona los datos al vector del calculo de datos
     * @param datos lista de datos
     */
    public void alamacenarlistaDat(ArrayList datos){
        this.listaDat.addAll(datos);
    }
    
    /**
     * Metodo que genera el primer  vector de signos
     * digitales
     */
    public void generaPrimFiltroDig(){
        int j=4;
        if(listaDat.isEmpty()==false){
            ArrayList filtroDig=new ArrayList();
            for(int i=0;j<listaDat.size()&&i<listaDat.size()&&listaDat.size()>5;i++){
                //System.out.println(Byte.toUnsignedInt((byte)listaDat.get(i)));
                filtroDig.add(Byte.toUnsignedInt((byte)listaDat.get(j))-Byte.toUnsignedInt((byte)listaDat.get(i)));
                j++;
            }
            if(filtroDig.isEmpty()==false){
                //System.out.println("esta aqui");
                aplicaFiltroDig(filtroDig);
            }
        }
    }
    
    /**
     * aplica el filtro digital sobre los datos del vector
     * @param filtroDig representa el array al aplicar el filtro digital
     */
    public void aplicaFiltroDig(ArrayList filtroDig){
        int num;
        ArrayList filtroN=new ArrayList();
        for(int p=0;p<9;p++){
            filtroN.add(0);
        }
        for(int i=4;i<filtroDig.size();i++){
                num= (int)filtroDig.get(i)+(4*(int)filtroDig.get(i-1))+
                (6*(int) filtroDig.get(i-2))+(4*(int) filtroDig.get(i-3))+
                (int)filtroDig.get(i-4);  
                filtroN.add(num);
        }
        marcarYBusc(filtroN);
    }
    
    
    /**
     * 
     * @param filtroDig 
     */
    public ArrayList marcarYBusc(ArrayList filtroDig){
        int may=(int)filtroDig.get(0);
        ArrayList listaValida=new ArrayList();
    for(int j=0;j<filtroDig.size();j++){
        System.out.println("valores del filtro "+(int)filtroDig.get(j));
        if(j+1<filtroDig.size()){
        if((int)filtroDig.get(j)>=valCam&&(int)filtroDig.get(j)>=may&&(int)filtroDig.get(j)!=(int)filtroDig.get(j+1)){
            may=(int)filtroDig.get(j);
            listaValida.add(1);
            }else{
                listaValida.add(0);
            }
        }
    }
        buscarUnos(listaValida);
    return listaValida;
    }
    /**
     * 
     * @param unosL 
     */
    public void buscarUnos(ArrayList unosL){
        int y=0;
        for(int x=0;x<unosL.size();x++){
            if((int)unosL.get(x)==1){
                System.out.println("posicion"+x);
                System.out.println("VALOR "+Byte.toUnsignedInt(((byte)listaDat.get(x))));
                if(x>y&&y>0){
                   System.out.println("se adiciona"+(x-y)+"valor de X "+x+" valore de Y"+ y);
                deltas.add(x-y);
                }
                y=x;
            }
        }
        if(deltas.isEmpty()==false){
    calcularProm();
    calculoFr();
        }else{
        Fr=0;
        }
    }
    /**
     * 
     */
    public void calcularProm(){
        int acum=0;
    for(int i=0;i<deltas.size();i++){
        acum+=(int)deltas.get(i);
        }
        //System.out.println(deltas.size());
        acum=acum/deltas.size();
        calProm=acum;
      // System.out.println("valor de acum"+calProm);
    }
    
    /**
     * 
     * 
     */
    public void calculoFr(){
       double fr1=(1/(double)calProm);
       fr1=fr1*256;
       fr1=fr1*60;
       if(fr1<1000){
       Fr=(int)fr1;
       }
       //System.out.println("valor de frecuencia "+fr1);
    }
    
    /**
     * 
     * 
     */
    public  void buscaMay(){
    int may=Byte.toUnsignedInt((byte)listaDat.get(0));
    for(int i=0;i<listaDat.size();i++){
        if(Byte.toUnsignedInt((byte)listaDat.get(i))>may){
            may=Byte.toUnsignedInt((byte)listaDat.get(i));
            //System.out.println("MAYOLRORLROLRORv "+may);
            }
        }
    May=may;
    }

    
    /**
     * 
     * 
     * 
     */
    
    public void buscaMen(){
    int men=Byte.toUnsignedInt((byte)listaDat.get(0))+Byte.toUnsignedInt((byte)listaDat.get(1));
    for(int j=0;j<listaDat.size();j++){
        if(j+1<listaDat.size()){
        if(Byte.toUnsignedInt((byte)listaDat.get(j))+Byte.toUnsignedInt((byte)listaDat.get(j+1))<men){
            men=Byte.toUnsignedInt((byte)listaDat.get(j))+Byte.toUnsignedInt((byte)listaDat.get(j+1));
            }
        }
    }
   Men=men;
    }
  
   
    
    
}
