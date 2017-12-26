/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.Tenant;

import business.EcoSystem;
import business.account.TenantAccount;
import business.event.Event;
import business.project.Project;
import business.work.WorkRequest;
import java.awt.CardLayout;
import javax.swing.JPanel;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Shruti
 */
public class TenantWorkAreaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form TenantWorkAreaJPanel
     */
    private JPanel userProcessContainer;
    private EcoSystem ecosystem;
    private TenantAccount tenantAccount;
    private Event event;

    public TenantWorkAreaJPanel(JPanel userProcessContainer, TenantAccount tenantAccount, Event event, EcoSystem ecosystem) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.ecosystem = ecosystem;
        this.tenantAccount = tenantAccount;
        this.event = event;

        feedbackBttn.setEnabled(false);
        manageOptions();
        qrCode(tenantAccount);
        enableFeedback();
    }
    
    public void enableFeedback()
    {
        String transRequest = "";
        for(WorkRequest request : tenantAccount.getWorkQueue().getWorkQueue())
        {
            if(request.getRequestType().equals("Transport"))
            {
                transRequest = request.getStatus();
            }
        }
        if(transRequest.equalsIgnoreCase("Completed"))
        {
            feedbackBttn.setEnabled(true);
        }
    }

    public void qrCode(TenantAccount account) {
        int apartmentId, buildingId;
        String street, city, zipCode, address, elecStatus = null, plumStatus = null, sewStatus = null, tranStatus = "";

        apartmentId = account.getTenant().getTempAddress().getApartmentId();
        buildingId = account.getTenant().getTempAddress().getBuildingId();
        street = account.getTenant().getTempAddress().getStreet();
        city = account.getTenant().getTempAddress().getCity();
        zipCode = account.getTenant().getTempAddress().getZipCode();
        address = apartmentId + "," + buildingId + "," + street + "," + city + "," + zipCode;

        for (WorkRequest request : account.getWorkQueue().getWorkQueue()) {
            if (request.getRequestType().equals("Electrician")) {
                elecStatus = request.getStatus();
            }
            if (request.getRequestType().equals("Plumber")) {
                plumStatus = request.getStatus();
            }
            if (request.getRequestType().equals("Sewage")) {
                sewStatus = request.getStatus();
            }
            if (request.getRequestType().equals("Transport")) {
                tranStatus = request.getStatus();
            }
        }

        String qrCodeData = "Welcome: " + account.getUsername() + "!" + "\n\n Address : " + address
                + "\n\n Electrician Status: " + elecStatus + "\n\n Plumber Status: " + plumStatus
                + "\n\n Sewage Status: " + sewStatus + "\n\n Transport Status: " + tranStatus;
        String filePath = "S:\\NEU\\Assignments\\AED\\AED_LAB_DEMO\\aed_final_project\\AEDFinalProject\\QRCode\\" + account.getUsername() + "-QR-Code.jpg";
        File imageFile = new File(filePath);
        String charset = "UTF-8"; // or "ISO-8859-1"
        int qrCodeWidth = 200;
        int qrCodeHeight = 200;
        Map hintMap = new HashMap();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        if (!account.getWorkQueue().getWorkQueue().isEmpty()) {
            if (imageFile.exists()) {
                imageFile.delete();
            }
            displayQRCodeJLabel.setIcon(null);
            generateQRCode(qrCodeData, filePath, charset, hintMap, qrCodeWidth, qrCodeHeight);
            ImageIcon img = new ImageIcon(filePath);
            displayQRCodeJLabel.setIcon(img);
            img.getImage().flush();
        }
    }

    public void generateQRCode(String qrCodeData, String filePath, String charset, Map hintMap, int qrCodeWidth, int qrCodeHeight)// throws UnsupportedEncodingException, WriterException, IOException
    {
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(charset), charset),
                    BarcodeFormat.QR_CODE, qrCodeWidth, qrCodeHeight, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                    .lastIndexOf('.') + 1), new File(filePath));
            JOptionPane.showMessageDialog(null, "QR Code created successfully.", "Information", JOptionPane.INFORMATION_MESSAGE);
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (WriterException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
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
        managePollingBttn = new javax.swing.JButton();
        selectApartmentButton = new javax.swing.JButton();
        feedbackBttn = new javax.swing.JButton();
        displayQRCodeJLabel = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Welcome");

        managePollingBttn.setBackground(new java.awt.Color(153, 51, 0));
        managePollingBttn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        managePollingBttn.setForeground(new java.awt.Color(255, 255, 255));
        managePollingBttn.setText("Manage Polling");
        managePollingBttn.setEnabled(false);
        managePollingBttn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                managePollingBttnMouseClicked(evt);
            }
        });
        managePollingBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                managePollingBttnActionPerformed(evt);
            }
        });

        selectApartmentButton.setBackground(new java.awt.Color(153, 51, 0));
        selectApartmentButton.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        selectApartmentButton.setForeground(new java.awt.Color(255, 255, 255));
        selectApartmentButton.setText("Select Apartment");
        selectApartmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectApartmentButtonActionPerformed(evt);
            }
        });

        feedbackBttn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userInterface/icons8-Talk-48.png"))); // NOI18N
        feedbackBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feedbackBttnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(754, Short.MAX_VALUE)
                .addComponent(feedbackBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
            .addGroup(layout.createSequentialGroup()
                .addGap(219, 219, 219)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(managePollingBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(selectApartmentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(displayQRCodeJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(feedbackBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(managePollingBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectApartmentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(displayQRCodeJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(238, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void selectApartmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectApartmentButtonActionPerformed
        // TODO add your handling code here:

        SelectApartmentJPanel panel = new SelectApartmentJPanel(userProcessContainer, event, ecosystem, tenantAccount);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        userProcessContainer.add("SelectApartmentJPanel", panel);
        layout.next(userProcessContainer);
    }//GEN-LAST:event_selectApartmentButtonActionPerformed

    private void managePollingBttnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managePollingBttnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_managePollingBttnMouseClicked

    private void managePollingBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_managePollingBttnActionPerformed
        // TODO add your handling code here:
        AnswerPollJPanel awjp = new AnswerPollJPanel(userProcessContainer, tenantAccount, event);
        userProcessContainer.add(awjp);
        ((CardLayout) userProcessContainer.getLayout()).next(userProcessContainer);
    }//GEN-LAST:event_managePollingBttnActionPerformed

    private void feedbackBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feedbackBttnActionPerformed
        // TODO add your handling code here:
        FeedbackFormJPanel panel = new FeedbackFormJPanel(userProcessContainer, ecosystem, tenantAccount);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        userProcessContainer.add("FeedbackFormJPanel", panel);
        layout.next(userProcessContainer);

    }//GEN-LAST:event_feedbackBttnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel displayQRCodeJLabel;
    private javax.swing.JButton feedbackBttn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton managePollingBttn;
    private javax.swing.JButton selectApartmentButton;
    // End of variables declaration//GEN-END:variables

    public void manageOptions() {
        if (tenantAccount.getDecision() != null || event.getProject().getProjectStatus() == Project.ProjectStatus.INITIATED) {
            managePollingBttn.setEnabled(true);
            selectApartmentButton.setEnabled(false);
        }

        if (event.getProject().getProjectStatus() == Project.ProjectStatus.APPROVED) {
            managePollingBttn.setEnabled(false);
            selectApartmentButton.setEnabled(true);
        }
    }
}
