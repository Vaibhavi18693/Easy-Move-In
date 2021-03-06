/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.transportRole;

import business.account.Account;
import business.organization.TransportOrganization;
import business.work.TransportWorkRequest;
import business.work.WorkRequest;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vaibhavi
 */
public class TransportWorkAreaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form TransportWorkAreaJPanel
     */
    private JPanel userProcessContainer;
    private TransportOrganization transportOrganization;
    private Account userAccount;
    
    public TransportWorkAreaJPanel(JPanel userProcessContainer, Account userAccount, TransportOrganization transportOrganization) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.transportOrganization = transportOrganization;
        this.userAccount = userAccount;
        
        adminTextFiled.setText(userAccount.getEmployee().getFirstName());
        
        for(WorkRequest request : transportOrganization.getWorkQueue().getWorkQueue())
        {
            if(!request.getStatus().equalsIgnoreCase("Completed"))
                request.setStatus("In Progress");
        }        
        populateTable();
    }
    
    public void populateTable()
    {
        DefaultTableModel defaultTableModel = (DefaultTableModel)requestJTable.getModel();
        defaultTableModel.setRowCount(0);
        
        int apartmentId, buildingId;
        String street, state,zipCode;
        
        for(WorkRequest request : transportOrganization.getWorkQueue().getWorkQueue())
        {
            TransportWorkRequest transportRequest = (TransportWorkRequest)request;
            apartmentId = transportRequest.getTenantAccount().getTenant().getTempAddress().getApartmentId();
            buildingId = transportRequest.getTenantAccount().getTenant().getTempAddress().getBuildingId();
            zipCode = transportRequest.getTenantAccount().getTenant().getTempAddress().getZipCode();
            street = transportRequest.getTenantAccount().getTenant().getTempAddress().getStreet();
            state = transportRequest.getTenantAccount().getTenant().getTempAddress().getCity();
            
            Object row[] = new Object[4];
            row[0] = request;
            row[1] = transportRequest.getSender();
            row[2] = apartmentId + "," + buildingId + "," + street + "," + state + "," + zipCode;
            row[3] = transportRequest.getStatus();
            defaultTableModel.addRow(row);
            
            request.setReceiver(userAccount);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        adminTextFiled = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        requestJTable = new javax.swing.JTable();
        completeBtn = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(900, 600));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Transport Work Area");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Welcome: ");

        adminTextFiled.setEditable(false);
        adminTextFiled.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        requestJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RequestId", "Requested Person", "Address", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(requestJTable);

        completeBtn.setBackground(new java.awt.Color(153, 51, 0));
        completeBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        completeBtn.setForeground(new java.awt.Color(255, 255, 255));
        completeBtn.setText("Complete");
        completeBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.darkGray, null, null));
        completeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                completeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(completeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(adminTextFiled, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adminTextFiled, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(completeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(316, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void completeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_completeBtnActionPerformed

        int selectedRow = requestJTable.getSelectedRow();
        String address;

        if(selectedRow >= 0)
        {
            TransportWorkRequest workRequest = (TransportWorkRequest) requestJTable.getValueAt(selectedRow, 0);
            
            if(workRequest.getStatus().equalsIgnoreCase("Completed"))
            {
                JOptionPane.showMessageDialog(null, "The request is already completed.","Information",JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                workRequest.setStatus("Completed");
                JOptionPane.showMessageDialog(null, "Request is completed!","Information",JOptionPane.INFORMATION_MESSAGE);
                populateTable();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please select a request to complete.","Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_completeBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField adminTextFiled;
    private javax.swing.JButton completeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable requestJTable;
    // End of variables declaration//GEN-END:variables
}
