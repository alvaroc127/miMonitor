/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.DynamicTimeSeriesCollection;
import org.jfree.data.time.Millisecond;


/**
 *
 * @author ELECTRONICA
 */
public class PanelVisual extends JPanel implements ActionListener,Runnable{
    private ArrayList grafica=new ArrayList();
    private DynamicTimeSeriesCollection contSer;
    private JFreeChart gaficaTiempo;
    private int segundo;
    private int minuto;
    private int hora;
    private int dia;
    private int mes;
    private int anio;
    private int indiceSerie;
    private Timer tiempo;
    private String nombreDeLaSerie;
    private int val;
                

    public PanelVisual(int val) {
        this.val=val;
       contSer=new DynamicTimeSeriesCollection(getCantidadDeSeries(),getCantidadPorSerie());
       contSer.setTimeBase(new Millisecond(0, segundo, minuto, hora, dia=1,mes=1,anio=2016));
       contSer.addSeries(valoresDeLaSerie(), indiceSerie=0, nombreDeLaSerie="SEÑAL");
       gaficaTiempo=ChartFactory.createTimeSeriesChart(                                 getTitulo(),
											getEtiquetaDelasX(),
											getEtiquetaDelasY(),
											contSer,
											tieneLeyenda(),
											tienToolTip(),
											tineUrl()
       );
       gaficaTiempo.setBackgroundPaint(new Color(0, 0, 0, 0));
        XYPlot plot=gaficaTiempo.getXYPlot();
        plot.setBackgroundPaint(Color.BLACK);
        XYLineAndShapeRenderer render=(XYLineAndShapeRenderer)plot.getRenderer();
         ChartPanel panelGraf=new ChartPanel(gaficaTiempo);
        setLayout(new BorderLayout());
       switch(this.val){
           
        case(0):
            plot.getRangeAxis().setRange(10,220);
            plot.getRendererForDataset(plot.getDataset(0)).setSeriesPaint(0, Color.GREEN);
            add(panelGraf, BorderLayout.CENTER);
        break;
           
        case(1):
            plot.getRangeAxis().setRange(100,200);
            plot.getRendererForDataset(plot.getDataset(0)).setSeriesPaint(0, Color.GREEN);
            add(panelGraf, BorderLayout.CENTER);
        break;
            
        case(2):
            plot.getRangeAxis().setRange(115,135);
            plot.getRendererForDataset(plot.getDataset(0)).setSeriesPaint(0, Color.GREEN);
            add(panelGraf, BorderLayout.CENTER);
        break;
            
            
        case(3):
            plot.getRangeAxis().setRange(90,190);
            plot.getRendererForDataset(plot.getDataset(0)).setSeriesPaint(0, Color.yellow);
            add(panelGraf, BorderLayout.WEST);
        break;
            
        case(4):
              plot.getRangeAxis().setRange(0,100);
            plot.getRendererForDataset(plot.getDataset(0)).setSeriesPaint(0, Color.CYAN);
            add(panelGraf, BorderLayout.WEST);
        break;
           
            
        case(5):
            plot.getRangeAxis().setRange(0,220);
            plot.getRendererForDataset(plot.getDataset(0)).setSeriesPaint(0, Color.RED);
            add(panelGraf, BorderLayout.WEST);
        break;
            
        case(6):
            plot.getRangeAxis().setRange(0,35);
            plot.getRendererForDataset(plot.getDataset(0)).setSeriesPaint(0, Color.YELLOW);
               add(panelGraf, BorderLayout.WEST); 
        break;
            
        case(7):
            plot.getRangeAxis().setRange(20,220);
            plot.getRendererForDataset(plot.getDataset(0)).setSeriesPaint(0, Color.YELLOW);
            add(panelGraf, BorderLayout.WEST);
        break;
            
       case(8):
           plot.getRangeAxis().setRange(20,220);
            plot.getRendererForDataset(plot.getDataset(0)).setSeriesPaint(0, Color.YELLOW);
           add(panelGraf, BorderLayout.WEST);
       break;
       }
    }
    
    
    /**
     * 
     * @param list 
     */
    public void loadGrafic(ArrayList list){
       synchronized(grafica){
        grafica.notify();
        grafica.addAll(list);
       }
       
    }
    
    
    /**
     * 
     * @return 
     */
    public int getElemnGrafic(){
        if(grafica.isEmpty()){
            synchronized(grafica){
                try{
                    Thread.yield();
               grafica.wait();
                }catch(InterruptedException ie){
                    ie.printStackTrace();
                }
            }
        }
        int re= Byte.toUnsignedInt((byte)grafica.get(0));
        //System.out.println("tamaño del Buffer : para panel "+val+"=="+grafica.size());
        grafica.remove(0);
        return re;
    }
    /**
     * 
     * 
     * @return 
     */
     private float[] valoresDeLaSerie() {
        float[] respuesta = new float[getCantidadPorSerie()];
            for (int i = 0; i < respuesta.length; i++) {
                    respuesta[i]=120;//crea un aleatorio;
        	}
           return respuesta;
        }
     /**
      * 
      * @return 
      */
     private boolean tineUrl() {
         return false;
     }
     
     /**
      * 
      * @return 
      */
     private boolean tienToolTip() {
         return true;
     }
     /**
      * 
      * @return 
      */
    private boolean tieneLeyenda() {
        return true;
    }
      /**
       * 
       * @return 
       */          
    private String getEtiquetaDelasY() {
	return "ECG";
    }
    /**
     * 
     * @return 
     */
    private String getEtiquetaDelasX() {
       return "hh:mm:ss";
    }
    /**
     * 
     * @return 
     */
    public String getTitulo() {
	return "ECG";
    }
    /**
     * 
     * @return 
     */
     private Millisecond enMiSecond() {
	return new Millisecond();
     }
    
    
    /**
     * 
     * @return 
     */
    public int getCantidadPorSerie() {
        return 700; 
    }
    /**
     * 
     * @return 
     */
    public int getCantidadDeSeries() {
        return 1;
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(this.val){ 
        case(0):
               if(grafica.isEmpty()==false){
             int resul;
             for(int i=0;i<grafica.size()/10;i++){
               resul=getElemnGrafic();
             contSer.advanceTime();//avansa el tiempo     
            contSer.appendData(new float[]{resul});
                }
              resul=getElemnGrafic();
             contSer.advanceTime();//avansa el tiempo     
            contSer.appendData(new float[]{resul});     
            }else{
            Thread.yield();
        }
            
        break;
           
        case(1):
               if(grafica.isEmpty()==false){
             int resul;
            for(int i=0;i<grafica.size()/8;i++){
               resul=getElemnGrafic();
             contSer.advanceTime();//avansa el tiempo     
            contSer.appendData(new float[]{resul});
                }
              resul=getElemnGrafic();
             contSer.advanceTime();//avansa el tiempo     
            contSer.appendData(new float[]{resul});     
            }else{
            Thread.yield();
        }
            
        break;
            
        case(2):
            if(grafica.isEmpty()==false){
            int resul;
                for(int i=0;i<grafica.size()/6;i++){
               resul=getElemnGrafic();
                contSer.advanceTime();//avansa el tiempo     
                contSer.appendData(new float[]{resul});
                }
                resul=getElemnGrafic();
                contSer.advanceTime();//avansa el tiempo     
                contSer.appendData(new float[]{resul});     
             }else{
                Thread.yield();
        }
            
        break;
            
            
        case(3):
               if(grafica.isEmpty()==false){
             int resul;
            for(int i=0;i<grafica.size()/20;i++){
               resul=getElemnGrafic();
             contSer.advanceTime();//avansa el tiempo     
             contSer.appendData(new float[]{resul});
                }
              resul=getElemnGrafic();
             contSer.advanceTime();//avansa el tiempo     
             contSer.appendData(new float[]{resul});     
            }else{
            System.err.println("NOhay3");
            Thread.yield();
        }
            
            
        break;
            
        case(4):
               if(grafica.isEmpty()==false){
             int resul;
            for(int i=0;i<grafica.size()/10;i++){
               resul=getElemnGrafic();
             contSer.advanceTime();//avansa el tiempo     
             contSer.appendData(new float[]{resul});
                }
              resul=getElemnGrafic();
             contSer.advanceTime();//avansa el tiempo     
             contSer.appendData(new float[]{resul});     
            }else{
            System.err.println("NOhay4");
            Thread.yield();
            }  
        break;
           
            
        case(5):
               if(grafica.isEmpty()==false){
             int resul=0;
            for(int i=0;i<grafica.size()/5;i++){
               resul=getElemnGrafic()+getElemnGrafic();
             contSer.advanceTime();//avansa el tiempo     
            contSer.appendData(new float[]{resul});
                }
              resul=getElemnGrafic()+getElemnGrafic();
             contSer.advanceTime();//avansa el tiempo     
            contSer.appendData(new float[]{resul});     
            }else{
            System.err.println("NOhay5");
            Thread.yield();
        }
           
            
        break;
            
        case(6):
               if(grafica.isEmpty()==false){
             int resul=0;
            for(int i=0;i<grafica.size()/5;i++){
               resul=getElemnGrafic()+getElemnGrafic();
             contSer.advanceTime();//avansa el tiempo     
            contSer.appendData(new float[]{resul});
           
                }
              resul=getElemnGrafic()+getElemnGrafic();
             contSer.advanceTime();//avansa el tiempo     
            contSer.appendData(new float[]{resul});     
            }else{
            System.err.println("NOhay6");
            Thread.yield();
        }
            
                
        break;
            
        case(7):
               if(grafica.isEmpty()==false){
             int resul;
            for(int i=0;i<grafica.size()/5;i++){
               resul=getElemnGrafic();
             contSer.advanceTime();//avansa el tiempo     
            contSer.appendData(new float[]{resul});
                }
              resul=getElemnGrafic();
             contSer.advanceTime();//avansa el tiempo     
            contSer.appendData(new float[]{resul});     
            }else{
            System.err.println("NOhay7");
            Thread.yield();
        }
            
        break;
            
       case(8):
              if(grafica.isEmpty()==false){
             int resul;
            for(int i=0;i<grafica.size()/10;i++){
               resul=getElemnGrafic();
             contSer.advanceTime();//avansa el tiempo     
            contSer.appendData(new float[]{resul});
                }
              resul=getElemnGrafic();
             contSer.advanceTime();//avansa el tiempo     
            contSer.appendData(new float[]{resul});     
            }else{
            System.err.println("NOhay8");
            Thread.yield();
        }  
       break;
       }
        
    }

    @Override
    public void run() {
        tiempo=new Timer(30,this);
        tiempo.start();
    }
}
