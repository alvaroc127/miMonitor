/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControladorCapRed;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author ELECTRONICA
 */
public class PrincipalPre extends javax.swing.JFrame implements ActionListener{
    
    private ControladorCapRed ccapR;
    private ArrayList<FrameVisual> frames;
    private Timer temp;
    /**
     * Creates new form PrincipalPre
     */
    public PrincipalPre(){
        initComponents();
        temp=new Timer(250, this);
        jListaIps.setVisible(false);
        ccapR=new ControladorCapRed();
        frames=new  ArrayList();
        temp.start();
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnIniciCap = new javax.swing.JButton();
        JBdispositivos = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLInterfaz = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListaIps = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnIniciCap.setText("Iniciar Captura");
        btnIniciCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciCapActionPerformed(evt);
            }
        });

        JBdispositivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JBdispositivosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JBdispositivosMouseEntered(evt);
            }
        });

        jLabel1.setText("Capturando datos de la interfaz :");

        jListaIps.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListaIpsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jListaIps);

        jLabel3.setText("Ip capturadas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JBdispositivos, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIniciCap))
                .addGap(64, 64, 64)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(256, 256, 256)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(9, 9, 9)
                        .addComponent(jLInterfaz, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLInterfaz, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnIniciCap, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JBdispositivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(243, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciCapActionPerformed
        // TODO add your handling code here:
        ccapR.iniciarCapt();
        for(String ad:ccapR.getDispostiRed()){
            JBdispositivos.addItem(ad);
        }
    }//GEN-LAST:event_btnIniciCapActionPerformed

    private void JBdispositivosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JBdispositivosMouseClicked
        // TODO add your handling code here:
        btnIniciCap.setVisible(false);
        JBdispositivos.setVisible(false);
        jLInterfaz.setText((String)JBdispositivos.getSelectedItem());
        ccapR.capturararDe(ccapR.buscaDisp((String)JBdispositivos.getSelectedItem()));  
        ccapR.cargarDispositivos();
        jListaIps.setVisible(true);
    }//GEN-LAST:event_JBdispositivosMouseClicked

    private void JBdispositivosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JBdispositivosMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JBdispositivosMouseEntered

    private void jListaIpsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListaIpsMouseClicked
        // TODO add your handling code here
        if(buscarFrame((String)jListaIps.getSelectedValue())==true&&(String)jListaIps.getSelectedValue()!=null){
            FrameVisual frv=new FrameVisual((String)jListaIps.getSelectedValue());
            Thread hilo=new Thread(frv);
            hilo.setPriority(Thread.NORM_PRIORITY);
            hilo.start();
            frv.setVisible(true);
            frames.add(frv);
        }else{
            if(buscarFrame((String)jListaIps.getSelectedValue())==false){
                frames.get(buscarFrame1((String)jListaIps.getSelectedValue())).setVisible(true);
                frames.get(buscarFrame1((String)jListaIps.getSelectedValue())).setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        }
    }//GEN-LAST:event_jListaIpsMouseClicked

    
    public int buscarFrame1(String ip){
        int sali=0;
        boolean ban=true;
        for(int i=0;i<frames.size()&&ban==true;i++){
            if(frames.get(i).getIp().equals(ip)){
                    ban=false;
                    sali=i;
            }
        }
        return sali;
    }
    

    
    public boolean buscarFrame(String ip){
        boolean ban=true;
        for(int i=0;i<frames.size()&&ban==true;i++){
            if(frames.get(i).getIp().equals(ip)){
                    ban=false;
            }
        }
        return ban;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox JBdispositivos;
    private javax.swing.JButton btnIniciCap;
    private javax.swing.JLabel jLInterfaz;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jListaIps;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
      jListaIps.removeAll();
        if(ccapR.getIps()!=null){
        jListaIps.setListData(ccapR.getIps().toArray());
        }
    }

}