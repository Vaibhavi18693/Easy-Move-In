/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.facilitiesRole;

import business.account.Account;
import business.account.TenantAccount;
import business.enterprise.AmenitiesEnterprise;
import business.organization.ElectricianOrganization;
import business.organization.FacilitiesOrganization;
import business.organization.Organization;
import business.organization.PlumberOrganization;
import business.organization.SewageOrganization;
import business.organization.TransportOrganization;
import business.work.ElectricianWorkRequest;
import business.work.FacilitiesWorkRequest;
import business.work.PlumberWorkRequest;
import business.work.SewageWorkRequest;
import business.work.TransportWorkRequest;
import business.work.WorkRequest;
import java.awt.CardLayout;
import java.util.HashMap;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vaibhavi
 */
public class FacilitiesRequestMonitorJPanel extends javax.swing.JPanel {

    /**
     * Creates new form FacilitiesRequestMonitorJPanel
     */
    private JPanel userProcessContainer;
    private Account userAccount;
    private FacilitiesOrganization organization;
    private FacilitiesWorkRequest facilitiesRequest;
    private AmenitiesEnterprise enterprise;
    private int flag = 0;
    DefaultTableModel defaultTableModel;
            
    public FacilitiesRequestMonitorJPanel(JPanel userProcessContainer, Account userAccount, FacilitiesWorkRequest facilitiesRequest, FacilitiesOrganization organization, AmenitiesEnterprise enterprise, int flag) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.userAccount = userAccount;
        this.facilitiesRequest = facilitiesRequest;
        this.organization = organization;
        this.enterprise = enterprise;
        this.flag = flag;
        adminTextFiled.setText(userAccount.getEmployee().getFirstName());
        transportBtn.setEnabled(false);
        
        defaultTableModel = (DefaultTableModel)requestJTable.getModel();
        defaultTableModel.setRowCount(0);
        
        if(flag == 1)
        {
            electricianBtn.setEnabled(false);
            plumberBtn.setEnabled(false);
            sewageBtn.setEnabled(false);
        }
        
        sendRequestToTransport();
        
        populateTable();
    }
    
    public String getAddress(WorkRequest request)
    {
        int apartmentId, buildingId ;
        String street,state,zipCode;
        
        if(request.getRequestType().equalsIgnoreCase("Electrician"))
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
        else if (request.getRequestType().equalsIgnoreCase("Facilities"))
        {
            FacilitiesWorkRequest facRequest = (FacilitiesWorkRequest)request;
            apartmentId = facRequest.getTenantAccount().getTenant().getTempAddress().getApartmentId();
            buildingId = facRequest.getTenantAccount().getTenant().getTempAddress().getBuildingId();
            zipCode = facRequest.getTenantAccount().getTenant().getTempAddress().getZipCode();
            street = facRequest.getTenantAccount().getTenant().getTempAddress().getStreet();
            state = facRequest.getTenantAccount().getTenant().getTempAddress().getCity();
            return apartmentId + "," + buildingId + "," + street + "," + state + "," + zipCode;
        }
        return null;
    }
    
    public void populateTable()
    {
        for(WorkRequest request : userAccount.getWorkQueue().getWorkQueue())
        {
            if(!request.getRequestType().equalsIgnoreCase("Facilities"))
            {
                Object[] row = new Object[4];
                row[0] = request;
                row[1] = getAddress(request);
                row[2] = request.getRequestType();
                row[3] = request.getStatus();
                defaultTableModel.addRow(row);
            }
        }
    }

    public void sendRequestToTransport()
    {
        String address, requestType;
        HashMap<String, String> hashMap;
        
        for(WorkRequest workReq : userAccount.getWorkQueue().getWorkQueue())
        {
            if(workReq.getRequestType().equalsIgnoreCase("Facilities"))
            {
                address = getAddress(workReq);
                hashMap = new HashMap<String, String>();
                
                outer:
                for(WorkRequest req : userAccount.getWorkQueue().getWorkQueue())
                {
                    if(req.getRequestType().equalsIgnoreCase("Transport"))
                    {
                        String transAddr = getAddress(req);
                        if(transAddr.equals(address))
                        {
                            hashMap.clear();
                            break outer;
                        }
                    }
                    if(!req.getRequestType().equalsIgnoreCase("Facilities"))
                    {
                        String addr = getAddress(req);
                        if(addr.equals(address))
                        {
                            hashMap.put(req.getRequestType(), req.getStatus());
                        }
                    }
                }
                if(!hashMap.isEmpty())
                {
                    int  i = 0;
                    for(String val : hashMap.values())
                    {
                        if(val.equalsIgnoreCase("Completed"))
                        {
                            ++i;
                        }
                    }
                    if(i == 3)
                    {
                        transportBtn.setEnabled(true);
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

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        adminTextFiled = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        requestJTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        electricianBtn = new javax.swing.JButton();
        plumberBtn = new javax.swing.JButton();
        sewageBtn = new javax.swing.JButton();
        transportBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setPreferredSize(new java.awt.Dimension(900, 600));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Facilities Request Monitor");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Welcome: ");

        adminTextFiled.setEditable(false);
        adminTextFiled.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        requestJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RequestId", "Tenant Address", "Request Type", "Status"
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Send Requests To", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        electricianBtn.setBackground(new java.awt.Color(153, 51, 0));
        electricianBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        electricianBtn.setForeground(new java.awt.Color(255, 255, 255));
        electricianBtn.setText("Electrician");
        electricianBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.darkGray, null, null));
        electricianBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                electricianBtnActionPerformed(evt);
            }
        });

        plumberBtn.setBackground(new java.awt.Color(153, 51, 0));
        plumberBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        plumberBtn.setForeground(new java.awt.Color(255, 255, 255));
        plumberBtn.setText("Plumber");
        plumberBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.darkGray, null, null));
        plumberBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plumberBtnActionPerformed(evt);
            }
        });

        sewageBtn.setBackground(new java.awt.Color(153, 51, 0));
        sewageBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        sewageBtn.setForeground(new java.awt.Color(255, 255, 255));
        sewageBtn.setText("Sewage");
        sewageBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.darkGray, null, null));
        sewageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sewageBtnActionPerformed(evt);
            }
        });

        transportBtn.setBackground(new java.awt.Color(153, 51, 0));
        transportBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        transportBtn.setForeground(new java.awt.Color(255, 255, 255));
        transportBtn.setText("Transport");
        transportBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.darkGray, null, null));
        transportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transportBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(electricianBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(sewageBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addGap(89, 89, 89)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(plumberBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(transportBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(electricianBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sewageBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(plumberBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(transportBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        backBtn.setBackground(new java.awt.Color(153, 51, 0));
        backBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        backBtn.setForeground(new java.awt.Color(255, 255, 255));
        backBtn.setText("<< Back");
        backBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.darkGray, null, null));
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(adminTextFiled, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 581, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adminTextFiled, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(142, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void sewageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sewageBtnActionPerformed
        
        SewageWorkRequest workRequest = new SewageWorkRequest();
        workRequest.setSender(userAccount);
        workRequest.setTenantAccount(facilitiesRequest.getTenantAccount());
        workRequest.setStatus("Sent");
        workRequest.setRequestType("Sewage");
        TenantAccount tenAcc = facilitiesRequest.getTenantAccount();
        
        Organization org = null;
        for(Organization organization : enterprise.getOrganizationDirectory().getOrganizationDirectory())
        {
            if(organization instanceof SewageOrganization)
            {
                org = organization;
                break;
            }
        }
        
        if(org != null)
        {
            org.getWorkQueue().getWorkQueue().add(workRequest);
            userAccount.getWorkQueue().getWorkQueue().add(workRequest);
            tenAcc.getWorkQueue().getWorkQueue().add(workRequest);
        }
        
        Object row[] = new Object[4];
        row[0] = workRequest.getId();
        row[1] = workRequest.getSender();
        row[2] = workRequest.getRequestType();
        row[3] = workRequest.getStatus();
        defaultTableModel.addRow(row);
        sewageBtn.setEnabled(false);
    }//GEN-LAST:event_sewageBtnActionPerformed

    private void electricianBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_electricianBtnActionPerformed
        
        ElectricianWorkRequest workRequest = new ElectricianWorkRequest();
        workRequest.setSender(userAccount);
        workRequest.setTenantAccount(facilitiesRequest.getTenantAccount());
        workRequest.setStatus("Sent");
        workRequest.setRequestType("Electrician");
        TenantAccount tenAcc = facilitiesRequest.getTenantAccount();
        
        Organization org = null;
        for(Organization organization : enterprise.getOrganizationDirectory().getOrganizationDirectory())
        {
            if(organization instanceof ElectricianOrganization)
            {
                org = organization;
                break;
            }
        }
        
        if(org != null)
        {
            org.getWorkQueue().getWorkQueue().add(workRequest);
            userAccount.getWorkQueue().getWorkQueue().add(workRequest);
            tenAcc.getWorkQueue().getWorkQueue().add(workRequest);
        }
        
        Object row[] = new Object[4];
        row[0] = workRequest.getId();
        row[1] = workRequest.getSender();
        row[2] = workRequest.getRequestType();
        row[3] = workRequest.getStatus();
        defaultTableModel.addRow(row);
        electricianBtn.setEnabled(false);
    }//GEN-LAST:event_electricianBtnActionPerformed

    private void plumberBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plumberBtnActionPerformed
        
        PlumberWorkRequest workRequest = new PlumberWorkRequest();
        workRequest.setSender(userAccount);
        workRequest.setTenantAccount(facilitiesRequest.getTenantAccount());
        workRequest.setStatus("Sent");
        workRequest.setRequestType("Plumber");
        TenantAccount tenAcc = facilitiesRequest.getTenantAccount();
        
        Organization org = null;
        for(Organization organization : enterprise.getOrganizationDirectory().getOrganizationDirectory())
        {
            if(organization instanceof PlumberOrganization)
            {
                org = organization;
                break;
            }
        }
        
        if(org != null)
        {
            org.getWorkQueue().getWorkQueue().add(workRequest);
            userAccount.getWorkQueue().getWorkQueue().add(workRequest);
            tenAcc.getWorkQueue().getWorkQueue().add(workRequest);
        }
        
        Object row[] = new Object[4];
        row[0] = workRequest.getId();
        row[1] = workRequest.getSender();
        row[2] = workRequest.getRequestType();
        row[3] = workRequest.getStatus();
        defaultTableModel.addRow(row);
        plumberBtn.setEnabled(false);
    }//GEN-LAST:event_plumberBtnActionPerformed

    private void transportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transportBtnActionPerformed
        
        TransportWorkRequest workRequest = new TransportWorkRequest();
        workRequest.setSender(userAccount);
        workRequest.setTenantAccount(facilitiesRequest.getTenantAccount());
        workRequest.setStatus("Sent");
        workRequest.setRequestType("Transport");
        TenantAccount tenAcc = facilitiesRequest.getTenantAccount();
        
        Organization org = null;
        for(Organization organization : enterprise.getOrganizationDirectory().getOrganizationDirectory())
        {
            if(organization instanceof TransportOrganization)
            {
                org = organization;
                break;
            }
        }
        
        if(org != null)
        {
            org.getWorkQueue().getWorkQueue().add(workRequest);
            userAccount.getWorkQueue().getWorkQueue().add(workRequest);
            tenAcc.getWorkQueue().getWorkQueue().add(workRequest);
        }
        
        Object row[] = new Object[4];
        row[0] = workRequest.getId();
        row[1] = workRequest.getSender();
        row[2] = workRequest.getRequestType();
        row[3] = workRequest.getStatus();
        defaultTableModel.addRow(row);
        transportBtn.setEnabled(false);
    }//GEN-LAST:event_transportBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout)userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField adminTextFiled;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton electricianBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton plumberBtn;
    private javax.swing.JTable requestJTable;
    private javax.swing.JButton sewageBtn;
    private javax.swing.JButton transportBtn;
    // End of variables declaration//GEN-END:variables
}
