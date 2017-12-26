/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.governmentAdminRole;

import business.event.Event;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author Hardik
 */
public class ViewPollsJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ViewPollsJPanel
     */
    private JPanel userProcessContainer;
    private Event event;
    
    public ViewPollsJPanel() {
        initComponents();
    }

    public ViewPollsJPanel(JPanel userProcessContainer, Event event) {
        this();
        this.userProcessContainer = userProcessContainer;
        this.event = event;
        PieDataset dataset = createDataSet();
        JFreeChart chart = createChart(dataset);
        piePanel.setLayout(new java.awt.BorderLayout());
        ChartPanel cp = new ChartPanel(chart) {

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(900, 550);
            }
        };
        cp.setMouseWheelEnabled(true);
        piePanel.add(cp, BorderLayout.CENTER);
        piePanel.validate();
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        piePanel = new javax.swing.JPanel();

        backButton.setText("<< Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        piePanel.setPreferredSize(new java.awt.Dimension(500, 300));

        javax.swing.GroupLayout piePanelLayout = new javax.swing.GroupLayout(piePanel);
        piePanel.setLayout(piePanelLayout);
        piePanelLayout.setHorizontalGroup(
            piePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        piePanelLayout.setVerticalGroup(
            piePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(backButton)
                    .addComponent(piePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 349, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(piePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(backButton)
                .addContainerGap(225, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        userProcessContainer.remove(this);
        ((CardLayout)userProcessContainer.getLayout()).previous(userProcessContainer);
    }//GEN-LAST:event_backButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JPanel piePanel;
    // End of variables declaration//GEN-END:variables

    private JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Approval ratings", // chart title
                dataset, // data
                false, // include legend
                true,
                false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionOutlinesVisible(false);
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(true);
        plot.setLabelGap(0.02);
        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
                "{0}: {1} ({2})", new DecimalFormat("0.00"), new DecimalFormat("0.00%"));
        plot.setLabelGenerator(gen);
        return chart;
    }

    private PieDataset createDataSet() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        int approvalCount = event.getApprovalCount();
        int denialCount = event.getDenialCount();
        dataset.setValue("Approval", approvalCount);
        dataset.setValue("Denial", denialCount);
        dataset.setValue("Undecided", event.getAccountDirectory().getAccountDirectory().size() - approvalCount - denialCount);
        return dataset;
    }
}
