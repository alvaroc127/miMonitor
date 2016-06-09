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
import javax.swing.Timer;
import javax.swing.JPanel;
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
    private Timer cronometro;
    private int segundo;
    private int minuto;
    private int hora;
    private int dia;
    private int mes;
    private int anio;
    private int indiceSerie;
    private String nombreDeLaSerie;
                

    public PanelVisual() {
       cronometro=new Timer(4,this);
       contSer=new DynamicTimeSeriesCollection(getCantidadDeSeries(),getCantidadPorSerie(),enMiSecond());
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
        plot.getRangeAxis().setRange(20,220);
        plot.getRendererForDataset(plot.getDataset(0)).setSeriesPaint(0, Color.GREEN);
       ChartPanel panelGraf=new ChartPanel(gaficaTiempo);
        setLayout(new BorderLayout());
        add(panelGraf, BorderLayout.CENTER);
       cronometro.start();
    }
    
    
    /**
     * 
     * @param list 
     */
    public void loadGrafic(ArrayList list){
       
        grafica.addAll(list);
       
    }
    
    
    /**
     * 
     * @return 
     */
    public int getElemnGrafic(){        
        int re= Byte.toUnsignedInt((byte)grafica.get(0));
        System.out.println("tamaño del Buffer :"+grafica.size());
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
        return 600; 
    }
    /**
     * 
     * @return 
     */
    public int getCantidadDeSeries() {
        return 1;
    }
    
    /**
     * 
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
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
            System.err.println("NOhay");
            Thread.yield();
        }
    }
}
