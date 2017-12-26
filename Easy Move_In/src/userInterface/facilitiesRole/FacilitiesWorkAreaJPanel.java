/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.facilitiesRole;

import business.account.Account;
import business.enterprise.AmenitiesEnterprise;
import business.organization.FacilitiesOrganization;
import business.work.ElectricianWorkRequest;
import business.work.FacilitiesWorkRequest;
import business.work.PlumberWorkRequest;
import business.work.SewageWorkRequest;
import business.work.TransportWorkRequest;
import business.work.WorkRequest;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vaibhavi
 */
public class FacilitiesWorkAreaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form FacilitiesWorkAreaJPanel
     */
    private JPanel userProcessContainer;
    private Account userAccount;
    private FacilitiesOrganization facilitiesOrganization;
    private AmenitiesEnterprise enterprise;
    
    public FacilitiesWorkAreaJPanel(JPanel userProcessContainer, Account userAccount, FacilitiesOrganization facilitiesOrganization, AmenitiesEnterprise enterprise) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.userAccount = userAccount;
        this.facilitiesOrganization = facilitiesOrganization;
        this.enterprise = enterprise;
        facilitiesAdminTextField.setText(userAccount.getEmployee().getFirstName());
        
        getStatus();
        populateTable();
        
        int rowCount = facilitiesJTable.getRowCount();
        String status = "In Progress";
        monitorRequestsBtn.setEnabled(false);
        
        if(rowCount > 0)
        {
            for(int i=0; i<rowCount; i++)
            {
                String statusVal = facilitiesJTable.getValueAt(i, 4).toString();
                if(statusVal.equalsIgnoreCase(status))
                {
                    monitorRequestsBtn.setEnabled(true);
                }
            }
        }
        getNotification();
    }
    
    public void getNotification()
    {
        String facAddress = null, tranAddress = null, elecAddress = null, plumAddress = null, sewAddress = null,
                elecRequest = null, plumRequest = null, sewRequest = null, transRequest = "";
       for(WorkRequest request : userAccount.getWorkQueue().getWorkQueue())
       {
           if(request.getRequestType().equals("Facilities"))
           {
               facAddress = getAddress(request);
           }
           if(request.getRequestType().equals("Electrician"))
           {
               elecAddress = getAddress(request);
               elecRequest = request.getStatus();
           }
           if(request.getRequestType().equals("Plumber"))
           {
               plumAddress = getAddress(request);
               plumRequest = request.getStatus();
           }
           if(request.getRequestType().equals("Sewage"))
           {
               sewAddress = getAddress(request);
               sewRequest = request.getStatus();
           }
           if(request.getRequestType().equals("Transport"))
           {
               tranAddress = getAddress(request);
               transRequest = request.getStatus();
           }
        }
       if(facAddress != null)
        {
            if(!facAddress.equals(tranAddress))
            {
                if(facAddress.equals(elecAddress) && facAddress.equals(sewAddress) && facAddress.equals(plumAddress))
                {
                    if(elecRequest.equals("Completed") && plumRequest.equals("Completed") && sewRequest.equals("Completed"))
                    {
                        JOptionPane.showMessageDialog(null, "Requests are approved by Electrician, Plumber, Sewage for Address : \n"+facAddress+"\n Please send request to Transport.","Notification",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
       
    }
    
    public void populateTable()
    {
        DefaultTableModel defaultTableModel =(DefaultTableModel)facilitiesJTable.getModel();
        defaultTableModel.setRowCount(0);
        int apartmentId, buildingId;
        String street, state,zipCode;
        
        for(WorkRequest request : facilitiesOrganization.getWorkQueue().getWorkQueue())
        {
            FacilitiesWorkRequest facilitiesRequest = (FacilitiesWorkRequest)request;
            apartmentId = facilitiesRequest.getTenantAccount().getTenant().getTempAddress().getApartmentId();
            buildingId = facilitiesRequest.getTenantAccount().getTenant().getTempAddress().getBuildingId();
            zipCode = facilitiesRequest.getTenantAccount().getTenant().getTempAddress().getZipCode();
            street = facilitiesRequest.getTenantAccount().getTenant().getTempAddress().getStreet();
            state = facilitiesRequest.getTenantAccount().getTenant().getTempAddress().getCity();
            
            Object row[] = new Object[5];
            row[0] = request;
            row[1] = facilitiesRequest.getSender();
            row[2] = apartmentId + "," + buildingId + "," + street + "," + state + "," + zipCode;
            row[3] = facilitiesRequest.getReceiver();
            row[4] = facilitiesRequest.getStatus();
            defaultTableModel.addRow(row);
        }
    }
    
    public String getAddress(WorkRequest request)
    {
        int apartmentId, buildingId;
        String street,state,zipCode;
        
        if (request.getRequestType().equalsIgnoreCase("Facilities"))
        {
            FacilitiesWorkRequest facRequest = (FacilitiesWorkRequest)request;
            apartmentId = facRequest.getTenantAccount().getTenant().getTempAddress().getApartmentId();
            buildingId = facRequest.getTenantAccount().getTenant().getTempAddress().getBuildingId();
            zipCode = facRequest.getTenantAccount().getTenant().getTempAddress().getZipCode();
            street = facRequest.getTenantAccount().getTenant().getTempAddress().getStreet();
            state = facRequest.getTenantAccount().getTenant().getTempAddress().getCity();
            return apartmentId + "," + buildingId + "," + street + "," + state + "," + zipCode;
        }
        else if (request.getRequestType().equalsIgnoreCase("Transport"))
        {
            TransportWorkRequest transRequest = (TransportWorkRequest)request;
            apartmentId = transRequest.getTenantAccount().getTenant().getTempAddress().getApartmentId();
            buildingId = transRequest.getTenantAccount().getTenant().getTempAddress().getBuildingId();
            zipCode = transRequest.getTenantAccount().getTenant().getTempAddress().getZipCode();
            street = transRequest.getTenantAccount().getTenant().getTempAddress().getStreet();
            state = transRequest.getTenantAccount().getTenant().getTempAddress().getCity();
            return apartmentId + "," + buildingId + "," + street + "," + state + "," + zipCode;
        }
        else if(request.getRequestType().equalsIgnoreCase("Electrician"))
        {
            ElectricianWorkRequest elecRequest = (ElectricianWorkRequest)request;
            apartmentId = elecRequest.getTenantAccount().getTenant().getTempAddress().getApartmentId();
            buildingId = elecRequest.getTenantAccount().getTenant().getTempAddress().getBuildingId();
            zipCode = elecRequest.getTenantAccount().getTenant().getTempAddress().getZipCode();
            street = elecRequest.getTenantAccount().getTenant().getTempAddress().getStreet();
            state = elecRequest.getTenantAccount().getTenant().getTempAddress().getCity();
            return apartmentId + "," + buildingId + "," + street + "," + state + "," + zipCode;
        }
        else if (request.getRequestType().equalsIgnoreCase("Plumber"))
        {
            PlumberWorkRequest plumRequest = (PlumberWorkRequest)request;
            apartmentId = plumRequest.getTenantAccount().getTenant().getTempAddress().getApartmentId();
            buildingId = plumRequest.getTenantAccount().getTenant().getTempAddress().getBuildingId();
            zipCode = plumRequest.getTenantAccount().getTenant().getTempAddress().getZipCode();
            street = plumRequest.getTenantAccount().getTenant().getTempAddress().getStreet();
            state = plumRequest.getTenantAccount().getTenant().getTempAddress().getCity();
            return apartmentId + "," + buildingId + "," + street + "," + state + "," + zipCode;
        }  
        else if (request.getRequestType().equalsIgnoreCase("Sewage"))
        {
            SewageWorkRequest sewRequest = (SewageWorkRequest)request;
            apartmentId = sewRequest.getTenantAccount().getTenant().getTempAddress().getApartmentId();
            buildingId = sewRequest.getTenantAccount().getTenant().getTempAddress().getBuildingId();
            zipCode = sewRequest.getTenantAccount().getTenant().getTempAddress().getZipCode();
            street = sewRequest.getTenantAccount().getTenant().getTempAddress().getStreet();
            state = sewRequest.getTenantAccount().getTenant().getTempAddress().getCity();
            return apartmentId + "," + buildingId + "," + street + "," + state + "," + zipCode;
        }
        return null;
    }
    
    public void getStatus()
    {
        int apartmentId, buildingId;
        String street,state, facAddress = null, tranAddress = null, zipCode;
        FacilitiesWorkRequest facRequest = null;
        
        for(WorkRequest request : userAccount.getWorkQueue().getWorkQueue())
        {
            if(request.getRequestType().equalsIgnoreCase("Facilities"))
            {
                facRequest = (FacilitiesWorkRequest)request;
                apartmentId = facRequest.getTenantAccount().getTenant().getTempAddress().getApartmentId();
                buildingId = facRequest.getTenantAccount().getTenant().getTempAddress().getBuildingId();
                zipCode = facRequest.getTenantAccount().getTenant().getTempAddress().getZipCode();
                street = facRequest.getTenantAccount().getTenant().getTempAddress().getStreet();
                state = facRequest.getTenantAccount().getTenant().getTempAddress().getCity();
                facAddress =  apartmentId + "," + buildingId + "," + street + "," + state + "," + zipCode;
            }
            if(request.getRequestType().equalsIgnoreCase("Transport"))
            {
                TransportWorkRequest transRequest = (TransportWorkRequest)request;
                apartmentId = transRequest.getTenantAccount().getTenant().getTempAddress().getApartmentId();
                buildingId = transRequest.getTenantAccount().getTenant().getTempAddress().getBuildingId();
                zipCode = transRequest.getTenantAccount().getTenant().getTempAddress().getZipCode();
                street = transRequest.getTenantAccount().getTenant().getTempAddress().getStreet();
                state = transRequest.getTenantAccount().getTenant().getTempAddress().getCity();
                tranAddress = apartmentId + "," + buildingId + "," + street + "," + state + "," + zipCode;
            }
        }
        
        for(WorkRequest request : userAccount.getWorkQueue().getWorkQueue())
        {
            if(facAddress != null && tranAddress != null)
            {
                if(facAddress.equalsIgnoreCase(tranAddress))
                {
                    if(request.getRequestType().equalsIgnoreCase("Transport"))
                    {
                        if(request.getStatus().equalsIgnoreCase("Completed"))
                        {
                            facRequest.setStatus("Completed");
                        }
                    }
                }
            }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        facilitiesJTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        facilitiesAdminTextField = new javax.swing.JTextField();
        assignBtn = new javax.swing.JButton();
        monitorRequestsBtn = new javax.swing.JButton();
        viewReqBtn = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(900, 600));

        facilitiesJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RequestId", "Requested Tenant", "Address", "Admin ", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(facilitiesJTable);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Facilities Work Area");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Welcome: ");

        facilitiesAdminTextField.setEditable(false);
        facilitiesAdminTextField.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        assignBtn.setBackground(new java.awt.Color(153, 51, 0));
        assignBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        assignBtn.setForeground(new java.awt.Color(255, 255, 255));
        assignBtn.setText("Assign to me");
        assignBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.darkGray, null, null));
        assignBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignBtnActionPerformed(evt);
            }
        });

        monitorRequestsBtn.setBackground(new java.awt.Color(153, 51, 0));
        monitorRequestsBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        monitorRequestsBtn.setForeground(new java.awt.Color(255, 255, 255));
        monitorRequestsBtn.setText("Monitor Request");
        monitorRequestsBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.darkGray, null, null));
        monitorRequestsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monitorRequestsBtnActionPerformed(evt);
            }
        });

        viewReqBtn.setBackground(new java.awt.Color(153, 51, 0));
        viewReqBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        viewReqBtn.setForeground(new java.awt.Color(255, 255, 255));
        viewReqBtn.setText("View Request");
        viewReqBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.darkGray, null, null));
        viewReqBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewReqBtnActionPerformed(evt);
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
                        .addComponent(viewReqBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(monitorRequestsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(assignBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(facilitiesAdminTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 581, Short.MAX_VALUE))
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(facilitiesAdminTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(assignBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(monitorRequestsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(viewReqBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(311, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void assignBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assignBtnActionPerformed
        
        int selectedRow = facilitiesJTable.getSelectedRow();
        
        if(selectedRow < 0)
        {
            JOptionPane.showMessageDialog(null, "Please select a row!","Warning", JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            FacilitiesWorkRequest request = (FacilitiesWorkRequest)facilitiesJTable.getValueAt(selectedRow, 0);
            
            if(request.getStatus().equalsIgnoreCase("Completed"))
            {
                JOptionPane.showMessageDialog(null, "The request is already completed.","Information",JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                request.setReceiver(userAccount);
                request.setStatus("In Progress");
                request.setRequestType("Facilities");
                userAccount.getWorkQueue().getWorkQueue().add(request);
                populateTable();
                monitorRequestsBtn.setEnabled(true);

                JOptionPane.showMessageDialog(null, "Please send the requests to the respective facilities!", "Information", JOptionPane.INFORMATION_MESSAGE);
                FacilitiesRequestMonitorJPanel facilitiesRequestMonitorJPanel  = new FacilitiesRequestMonitorJPanel(userProcessContainer, userAccount, request, facilitiesOrganization, enterprise,0);
                userProcessContainer.add("FacilitiesRequestMonitorJPanel", facilitiesRequestMonitorJPanel);
                CardLayout layout = (CardLayout)userProcessContainer.getLayout();
                layout.next(userProcessContainer);
            }
        }
    }//GEN-LAST:event_assignBtnActionPerformed

    private void monitorRequestsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monitorRequestsBtnActionPerformed
        
        int selectedRow = facilitiesJTable.getSelectedRow();
        
        if(selectedRow < 0)
        {
            JOptionPane.showMessageDialog(null, "Please select a row!","Warning",JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            FacilitiesWorkRequest request = (FacilitiesWorkRequest)facilitiesJTable.getValueAt(selectedRow, 0);
            FacilitiesRequestMonitorJPanel facilitiesRequestMonitorJPanel  = new FacilitiesRequestMonitorJPanel(userProcessContainer, userAccount, request, facilitiesOrganization, enterprise,1);
            userProcessContainer.add("FacilitiesRequestMonitorJPanel", facilitiesRequestMonitorJPanel);
            CardLayout layout = (CardLayout)userProcessContainer.getLayout();
            layout.next(userProcessContainer);
        }
    }//GEN-LAST:event_monitorRequestsBtnActionPerformed

    private void viewReqBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewReqBtnActionPerformed
        
        int selectedRow = facilitiesJTable.getSelectedRow();
        
        if(selectedRow < 0)
        {
            JOptionPane.showMessageDialog(null, "Please select a row!","Warning", JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            FacilitiesWorkRequest request = (FacilitiesWorkRequest)facilitiesJTable.getValueAt(selectedRow, 0);
            ViewRequestJPanel viewRequestJPanel  = new ViewRequestJPanel(userProcessContainer, userAccount, request);
            userProcessContainer.add("ViewRequestJPanel", viewRequestJPanel);
            CardLayout layout = (CardLayout)userProcessContainer.getLayout();
            layout.next(userProcessContainer);
        }
    }//GEN-LAST:event_viewReqBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton assignBtn;
    private javax.swing.JTextField facilitiesAdminTextField;
    private javax.swing.JTable facilitiesJTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton monitorRequestsBtn;
    private javax.swing.JButton viewReqBtn;
    // End of variables declaration//GEN-END:variables
}
