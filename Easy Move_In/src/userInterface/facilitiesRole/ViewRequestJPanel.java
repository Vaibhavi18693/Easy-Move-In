/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.facilitiesRole;

import business.account.Account;
import business.work.ElectricianWorkRequest;
import business.work.FacilitiesWorkRequest;
import business.work.PlumberWorkRequest;
import business.work.SewageWorkRequest;
import business.work.TransportWorkRequest;
import business.work.WorkRequest;
import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author Vaibhavi
 */
public class ViewRequestJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ViewRequestJPanel
     */
    private JPanel userProcessContainer;
    private Account userAccount; 
    private FacilitiesWorkRequest facilitiesRequest;
    
    public ViewRequestJPanel(JPanel userProcessContainer, Account userAccount, FacilitiesWorkRequest facilitiesRequest) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.userAccount = userAccount;
        this.facilitiesRequest = facilitiesRequest;
        displayData();
    }

    public String getAddress(WorkRequest request)
    {
        int apartmentId, buildingId;
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
    
    public void displayData()
    {
        String  street, state,zipCode;
        int  apartmentId, buildingId;
        
        zipCode = facilitiesRequest.getTenantAccount().getTenant().getTempAddress().getZipCode();
        apartmentId = facilitiesRequest.getTenantAccount().getTenant().getTempAddress().getApartmentId();
        buildingId = facilitiesRequest.getTenantAccount().getTenant().getTempAddress().getBuildingId();
        street = facilitiesRequest.getTenantAccount().getTenant().getTempAddress().getStreet();
        state = facilitiesRequest.getTenantAccount().getTenant().getTempAddress().getCity();
        
        tenantNameTextField.setText(facilitiesRequest.getTenantAccount().getTenant().getTenantName());
        apartmentIdTextField.setText(String.valueOf(apartmentId));
        buildingIdTextField.setText(String.valueOf(buildingId));
        streetTextField.setText(street);
        stateTextField.setText(state);
        zipcodeTextField.setText(String.valueOf(zipCode));
        
        String facAddress = apartmentId + "," + buildingId + "," + street + "," + state + "," + zipCode;
        
        for(WorkRequest request : userAccount.getWorkQueue().getWorkQueue())
        {
            if(request.getRequestType().equalsIgnoreCase("Electrician"))
            {
                String elecAddress = getAddress(request);
                if(facAddress != null && elecAddress != null)
                {
                    if(facAddress.equals(elecAddress))
                    {
                        elecStatusTextField.setText(request.getStatus());
                    }
                }
            }
                
            if(request.getRequestType().equalsIgnoreCase("Plumber"))
            {
                String plumAddress = getAddress(request);
                if(facAddress != null && plumAddress != null)
                {
                    if(facAddress.equals(plumAddress))
                    {
                        plumStatusTextField.setText(request.getStatus());
                    }
                }
            }
                
            if(request.getRequestType().equalsIgnoreCase("Sewage"))
            {
                String sewAddress = getAddress(request);
                if(facAddress != null && sewAddress != null)
                {
                    if(facAddress.equals(sewAddress))
                    {
                        sewStatusTextField.setText(request.getStatus());
                    }
                }
            }
              
            if(request.getRequestType().equalsIgnoreCase("Transport"))
            {
                String transAddress = getAddress(request);
                if(facAddress != null && transAddress != null)
                {
                    if(facAddress.equals(transAddress))
                    {
                        transportStatusTextField.setText(request.getStatus());
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tenantNameTextField = new javax.swing.JTextField();
        elecStatusTextField = new javax.swing.JTextField();
        plumStatusTextField = new javax.swing.JTextField();
        sewStatusTextField = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        apartmentIdTextField = new javax.swing.JTextField();
        buildingIdTextField = new javax.swing.JTextField();
        streetTextField = new javax.swing.JTextField();
        stateTextField = new javax.swing.JTextField();
        zipcodeTextField = new javax.swing.JTextField();
        backBtn = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        transportStatusTextField = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(900, 600));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Display Request Details");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Tenant Name:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Electrician Status:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Plumber Status:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Sewage Status:");

        tenantNameTextField.setEditable(false);

        elecStatusTextField.setEditable(false);

        plumStatusTextField.setEditable(false);

        sewStatusTextField.setEditable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Address", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("ApartmentId:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("BuildingId:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("State:");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setText("Street:");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setText("ZipCode:");

        apartmentIdTextField.setEditable(false);

        buildingIdTextField.setEditable(false);

        streetTextField.setEditable(false);

        stateTextField.setEditable(false);

        zipcodeTextField.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(zipcodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(streetTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(apartmentIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buildingIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(apartmentIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(buildingIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(streetTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(stateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(zipcodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
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

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setText("Transport Status:");

        transportStatusTextField.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(298, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(transportStatusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tenantNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(elecStatusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plumStatusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sewStatusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(222, 222, 222))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tenantNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(elecStatusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(plumStatusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(sewStatusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(transportStatusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout)userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apartmentIdTextField;
    private javax.swing.JButton backBtn;
    private javax.swing.JTextField buildingIdTextField;
    private javax.swing.JTextField elecStatusTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField plumStatusTextField;
    private javax.swing.JTextField sewStatusTextField;
    private javax.swing.JTextField stateTextField;
    private javax.swing.JTextField streetTextField;
    private javax.swing.JTextField tenantNameTextField;
    private javax.swing.JTextField transportStatusTextField;
    private javax.swing.JTextField zipcodeTextField;
    // End of variables declaration//GEN-END:variables
}
