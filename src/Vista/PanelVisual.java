/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JLabel;
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
public class PanelVisual extends JPanel implements ActionListener{
    private ArrayList grafica=new ArrayList();
    private DynamicTimeSeriesCollection contSer;
    private JFreeChart gaficaTiempo;
    private JLabel Frecuen;
    private JLabel Mayor;
    private JLabel Men;
    private JPanel pan;
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
        tiempo=new Timer(8,this);
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
        tiempo.start();
       switch(this.val){
           
        case(0):
            Frecuen=new JLabel();
            Frecuen.setFont(new Font("Tahoma",1,90));
             Frecuen.setForeground(Color.GREEN);
            add(Frecuen,BorderLayout.EAST);
            //plot.getRangeAxis().setRange(10,220);
            plot.getRendererForDataset(plot.getDataset(0)).setSeriesPaint(0, Color.GREEN);
            add(panelGraf, BorderLayout.CENTER);
        break;
           
        case(1):
            //plot.getRangeAxis().setRange(10,200);
            plot.getRendererForDataset(plot.getDataset(0)).setSeriesPaint(0, Color.GREEN);
            add(panelGraf, BorderLayout.CENTER);
        break;
            
        case(2):
           // plot.getRangeAxis().setRange(115,135);
            plot.getRendererForDataset(plot.getDataset(0)).setSeriesPaint(0, Color.GREEN);
            add(panelGraf, BorderLayout.CENTER);
        break;
            
            
        case(3):
           // plot.getRangeAxis().setRange(90,190);
            plot.getRendererForDataset(plot.getDataset(0)).setSeriesPaint(0, Color.yellow);
            add(panelGraf, BorderLayout.CENTER);
        break;
            
        case(4):
            pan=new JPanel();
            pan.setLayout(new BorderLayout());
            Mayor=new JLabel();
            Frecuen=new JLabel();
            Frecuen.setFont(new Font("Tahoma",0,50));
            Frecuen.setForeground(Color.CYAN);
            Mayor.setFont(new Font("Tahoma",1,60));
            pan.add(Frecuen,BorderLayout.SOUTH);
            pan.add(Mayor,BorderLayout.CENTER);
            //plot.getRangeAxis().setRange(0,100);
            plot.getRendererForDataset(plot.getDataset(0)).setSeriesPaint(0, Color.CYAN);
            add(panelGraf,BorderLayout.CENTER);
            add(pan,BorderLayout.EAST);
        break;
           
            
        case(5):
            contSer=new DynamicTimeSeriesCollection(getCantidadDeSeries(),768);
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
        XYPlot plot1=gaficaTiempo.getXYPlot();
        plot1.setBackgroundPaint(Color.BLACK);
        XYLineAndShapeRenderer render1=(XYLineAndShapeRenderer)plot1.getRenderer();
         ChartPanel panelGraf1=new ChartPanel(gaficaTiempo);
            pan=new JPanel();
            pan.setLayout(new BorderLayout());
            Mayor=new JLabel();
            Men=new JLabel();
            Mayor.setFont(new Font("Tahoma",1,90));
            Men.setFont(new Font("Tahoma",1,50));
            pan.add(Mayor,BorderLayout.WEST);
            pan.add(Men, BorderLayout.EAST);
           // plot.getRangeAxis().setRange(0,220);
            plot1.getRendererForDataset(plot1.getDataset(0)).setSeriesPaint(0, Color.RED);
            add(panelGraf1, BorderLayout.CENTER);
            add(pan,BorderLayout.EAST);
        break;
            
        case(6):
            pan=new JPanel();
            pan.setLayout(new BorderLayout());
            Mayor=new JLabel();
            Men=new JLabel("-");
            Mayor.setFont(new Font("Tahoma",1,90));
            Men.setFont(new Font("Tahome",1,50));
            pan.add(Mayor,BorderLayout.WEST);
            pan.add(Men,BorderLayout.EAST);
            //plot.getRangeAxis().setRange(0,35);
            plot.getRendererForDataset(plot.getDataset(0)).setSeriesPaint(0, Color.YELLOW);
            add(panelGraf, BorderLayout.CENTER); 
            add(pan,BorderLayout.EAST);
        break;
            
        case(7):
            //plot.getRangeAxis().setRange(20,220);
            plot.getRendererForDataset(plot.getDataset(0)).setSeriesPaint(0, Color.YELLOW);
            add(panelGraf, BorderLayout.WEST);
        break;
            
       case(8):
           //plot.getRangeAxis().setRange(20,220);
            plot.getRendererForDataset(plot.getDataset(0)).setSeriesPaint(0, Color.YELLOW);
           add(panelGraf, BorderLayout.WEST);
       break;
       }
    }
    
    
    /**
     * metodo que adiciona una lista a una lista acutal
     * @param list la nueva lista a adicionar
     */
    public void loadGrafic(ArrayList list){
       synchronized(grafica){
        grafica.notify();
        grafica.addAll(list);
       }
    }
    
    
    /**
     * metodo que retorna el elemento de la grafica
     * @return retorna un elemento de la grafica
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
     * hace referencia a los valores iniciales de la seria
     * @return una lista de valores iniciales para la serie
     */
     private float[] valoresDeLaSerie() {
        float[] respuesta = new float[getCantidadPorSerie()];
            for (int i = 0; i < respuesta.length; i++) {
                    respuesta[i]=120;//crea un aleatorio;
        	}
           return respuesta;
        }
     
     /**
      * permite identificar si la grafica cuenta con una URL
      * @return un boolean con false o verdadero
      */
     private boolean tineUrl() {
         return false;
     }
     
     /**
      * activa las herramientas de las grafica
      * @return un booleano con la propiedad de activar o desactivar la  grafica
      */
     private boolean tienToolTip() {
         return true;
     }
     
     /**
      * activa la leyenda de la grafica
      * @return un booleano con la leyenda de la grafica
      */
    private boolean tieneLeyenda() {
        return true;
    }
      /**
       * identifica el nombre del eje Y
       * @return un sitring con el nombre del EJE Y
       */          
    private String getEtiquetaDelasY() {
	return "ECG";
    }
    /**
     * genera el nombre de las X
     * @return un string con el nombre de las X
     */
    private String getEtiquetaDelasX() {
       return "hh:mm:ss";
    }
    /**
     * genera el titulo de la grafica
     * @return un string del titulo
     */
    public String getTitulo() {
	return "ECG";
    }
    /**
     * hace referencia a la medica en la cual se cuantifican los datos
     * @return un objeto de tipo millisecond
     */
     private Millisecond enMiSecond() {
	return new Millisecond();
     }
    
    
    /**
     * limita la cantidad de datos que se grafican por segundo
     * @return la cantidad de datos graficados
     */
    public int getCantidadPorSerie() {
        return 1280; 
    }
    /**
     * permite especificar la cantidad de lineas por tendencia
     * @return la cantidad de lineas a graficar
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
            Thread.yield();
        }  
       break;
       }
        
    }

    public synchronized void cargaFrecuen(int Fr){
    Frecuen.setText(String.valueOf(Fr));
    }
    
    public synchronized void cargarMay(int may){
    Mayor.setText(String.valueOf(may));
    }
    
    public synchronized void cargaMen(int men){
    Men.setText("/"+String.valueOf(men));
    }
    
}
