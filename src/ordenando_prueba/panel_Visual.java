/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenando_prueba;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.CategoryPlot;
//import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.DynamicTimeSeriesCollection;
import org.jfree.data.time.Millisecond;


/**
 *
 * @author ELECTRONICA
 */
public class panel_Visual extends JPanel implements ActionListener,Runnable{
    
    private DynamicTimeSeriesCollection contenidoSerieDinamica;
		private JFreeChart graphicaDeTiempo;
		private Timer cronometro;
		private Random generadorDeAleatorios;
                private Conecxion_Envio co=new Conecxion_Envio();
 
		private int segundo;
		private int minuto;
		private int hora;
		private int dia;
		private int mes;
		private int anio;
		private int indiceSerie;
		private String nombreDeLaSerie;

                
                

    public panel_Visual() {
        generadorDeAleatorios=new Random();
        cronometro=new Timer(4,this);
        contenidoSerieDinamica = new DynamicTimeSeriesCollection(getCantidadDeSeries(),getCantidadPorSerie(),enMiSecond());
        contenidoSerieDinamica.setTimeBase(new Millisecond(0, segundo, minuto, hora, dia=1, mes=1,anio=2016));//pone el primer instante
	contenidoSerieDinamica.addSeries(valoresDeLaSerie(), indiceSerie=0,nombreDeLaSerie="ventas");//agrega una serie
        graphicaDeTiempo = ChartFactory.createTimeSeriesChart(
											getTitulo(),
											getEtiquetaDelasX(),
											getEtiquetaDelasY(),
											contenidoSerieDinamica,
											tieneLeyenda(),
											tienToolTip(),
											tineUrl());//crea una grafica de tiempo
        graphicaDeTiempo.setBackgroundPaint(new Color(0, 0, 0, 0));
        XYPlot plot=graphicaDeTiempo.getXYPlot();
        plot.setBackgroundPaint(Color.BLACK);
        XYLineAndShapeRenderer render=(XYLineAndShapeRenderer)plot.getRenderer();
        plot.getRangeAxis().setRange(20,220);
        plot.getRendererForDataset(plot.getDataset(0)).setSeriesPaint(0, Color.GREEN);
    ChartPanel panelDeLaGraphica = new ChartPanel(graphicaDeTiempo);//crea un panel para graficas
    add(panelDeLaGraphica);
    cronometro.start();
    }
    
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(co.getLista().isEmpty()==false){
            int resul=co.datosLista();
            contenidoSerieDinamica.advanceTime();//avansa el tiempo     
            contenidoSerieDinamica.appendData(new float[]{resul});//aqui llamra la funcion de retorno del socket 
            }
        }
            

    
         private float[] valoresDeLaSerie() {
		float[] respuesta = new float[getCantidadPorSerie()];
		Random creadorDeAleatorios = new Random();
		for (int i = 0; i < respuesta.length; i++) {
                        respuesta[i]=120;//crea un aleatorio;
			}
                    return respuesta;
		}
                
		private boolean tineUrl() {
			return false;
		}
 
		private boolean tienToolTip() {
			return true;
		}
 
		private boolean tieneLeyenda() {
			return true;
		}
 
		private String getEtiquetaDelasY() {
 
			return "ECG";
		}
 
		private String getEtiquetaDelasX() {
			return "hh:mm:ss";
		}
 
		public String getTitulo() {
 
			return "ECG";
		}
                public int getCantidadPorSerie() {
 
			return 
                                600;
		}
		public int getCantidadDeSeries() {
			return 1;
		}
                private Millisecond enMiSecond() {
			return new Millisecond();
		}

    @Override
    public void run() {
        co.ejecutarCliete();
        try{
        Thread.sleep(100);
        }catch(InterruptedException ex){
        ex.printStackTrace();
        }
        //aqui ejecuatara el  socket cliente
        //el inicio de este hilo sera una vez se tenga n datos 
    }
}
