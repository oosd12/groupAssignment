package GUI;


import CoreClasses.Customer;
import CoreClasses.Review;
import CoreClasses.ReviewDAO;
import java.awt.Dimension;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultStyledDocument;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abdullah
 */
public class ReviewGUI extends javax.swing.JFrame {
    ReviewDAO rd = new ReviewDAO();
    
    private int productID, supplierID;
    
    
    /**
     * Creates new form DisplayReviews
     */
    public ReviewGUI() {
        initComponents();
   
    }
    
    public ReviewGUI(int productID, int supplierID) {
        this.productID =productID;
        this.supplierID =supplierID;
        
        initComponents();
        displayReviewPanels();
        
        jScrollPane2.setHorizontalScrollBarPolicy(jScrollPane2.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(jScrollPane2.VERTICAL_SCROLLBAR_NEVER);
        
        txtAComment.setLineWrap(true);
        txtAComment.setWrapStyleWord(true);
        
        lblRating.setText("Rating : "+sliderRating.getValue());
    }
    
    public java.sql.Date getTodayDate() throws ParseException{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String today = dateFormat.format(date);
        Date today2 = dateFormat.parse(today);
        java.sql.Date sqlDate = new java.sql.Date(today2.getTime());
        return sqlDate;
    }
    
    
    public void displayReviewPanels(){
        ArrayList<Review> x = new ArrayList<>();
        x = rd.getReviews(this.productID, this.supplierID);
        
        panelContainer.setPreferredSize(new Dimension(510, x.size()*300));
        for(int i=0;i < x.size();i++){
            
            JPanel temp = x.get(i).getPanel();
            temp.setAlignmentX(panelContainer.LEFT_ALIGNMENT);
            panelContainer.add(temp);
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
        panelContainer = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        sliderRating = new javax.swing.JSlider();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAComment = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        lblRating = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblCharsRemaining = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panelContainer.setPreferredSize(new java.awt.Dimension(510, 1000));
        panelContainer.setLayout(new javax.swing.BoxLayout(panelContainer, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(panelContainer);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Reviews");

        sliderRating.setMaximum(5);
        sliderRating.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sliderRatingMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sliderRatingMouseEntered(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sliderRatingMouseReleased(evt);
            }
        });

        txtAComment.setColumns(20);
        txtAComment.setRows(5);
        txtAComment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtACommentKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(txtAComment);

        jLabel2.setText("Product Rating");

        lblRating.setText("Rating : ");

        jLabel3.setText("Comment :");

        lblCharsRemaining.setText("Characters Remaining : 750");

        btnAdd.setText("Add Review");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(448, 448, 448))
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(sliderRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblRating, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblCharsRemaining, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAdd)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(39, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(sliderRating, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRating, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lblCharsRemaining))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd)
                            .addComponent(btnCancel)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        //Bringing the scroll bar to the top
        jScrollPane1.getVerticalScrollBar().setValue(0);
        
    }//GEN-LAST:event_formWindowOpened

    private void txtACommentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtACommentKeyPressed
        int charLength = txtAComment.getText().length();
        lblCharsRemaining.setText("Characters Remaining : "+(750 - txtAComment.getText().length()));
        
        if(750 - txtAComment.getText().length() == 0){
            JOptionPane.showMessageDialog(this,"You have reached the character limit for your comment,\n extras will not be displayed", "Character Limit Reached", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//GEN-LAST:event_txtACommentKeyPressed

    private void sliderRatingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sliderRatingMouseClicked
        lblRating.setText("Rating : "+sliderRating.getValue());
    }//GEN-LAST:event_sliderRatingMouseClicked

    private void sliderRatingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sliderRatingMouseEntered
        
    }//GEN-LAST:event_sliderRatingMouseEntered

    private void sliderRatingMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sliderRatingMouseReleased
        lblRating.setText("Rating : "+sliderRating.getValue());
    }//GEN-LAST:event_sliderRatingMouseReleased

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if(Customer.getCurrentCustomer().equals("") || Customer.getCurrentCustomer() == null){
            JOptionPane.showMessageDialog(this, "You are not logged in, cannot add review.\n Please register and login to review products.", "Please Login", JOptionPane.ERROR_MESSAGE);
        }
        else{
            Review r;
            try {
                r = new Review(Customer.getCurrentCustomer(), txtAComment.getText(), sliderRating.getValue(), getTodayDate());
                r.setProductID(productID);
                r.setSupplierID(supplierID);
                
                rd.addReview(r);
                JOptionPane.showMessageDialog(this, "Your review for this product has been added successfully!.", "Review Added", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (ParseException ex) {
                Logger.getLogger(ReviewGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        panelContainer.removeAll();
        panelContainer.repaint();
        panelContainer.revalidate();
        displayReviewPanels();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReviewGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReviewGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReviewGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReviewGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReviewGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCharsRemaining;
    private javax.swing.JLabel lblRating;
    private javax.swing.JPanel panelContainer;
    private javax.swing.JSlider sliderRating;
    private javax.swing.JTextArea txtAComment;
    // End of variables declaration//GEN-END:variables
}
