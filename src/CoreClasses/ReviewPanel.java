package CoreClasses;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextArea;
import java.sql.Date;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abdullah
 */
public class ReviewPanel {
    
    
    JLabel lblUsername = new JLabel("", JLabel.LEFT);
    JLabel lblDate = new JLabel("", JLabel.LEFT);
    JLabel lblRating = new JLabel("", JLabel.LEFT);
    JTextArea txtComment = new JTextArea();
    JPanel panelReview = new JPanel();
    JPanel panelPadding  = new JPanel();

    public ReviewPanel(String userName, String comment, int rating, Date date) {
        this.lblUsername.setText("Customer : "+userName);
        this.lblDate.setText("Date : "+date);
        this.lblRating.setText("Product Rating : "+rating);
        this.txtComment.setText("Comment : \n"+comment);
    }
    
    
    
   
    
    public void setProperties(){
        panelReview.setLayout(new BoxLayout(panelReview, BoxLayout.Y_AXIS));
        panelReview.setBorder(new TitledBorder("Review"));
        Dimension panelDim = new Dimension(500, 300);
        panelReview.setMinimumSize(panelDim);
        panelReview.setMaximumSize(panelDim);
        panelReview.setPreferredSize(panelDim);
        
        //Text area
        txtComment.setWrapStyleWord(true);
        txtComment.setLineWrap(true);
        txtComment.setOpaque(false);
        txtComment.setEditable(false);
        txtComment.setFocusable(false);
        txtComment.setBackground(UIManager.getColor("Label.background"));
        txtComment.setFont(UIManager.getFont("Label.font"));
        txtComment.setBorder(UIManager.getBorder("Label.border"));
        txtComment.setBackground(new Color(0, 0, 0, 0));
        
        Dimension commentDim = new Dimension(480, 200);
        txtComment.setMinimumSize(commentDim);
        txtComment.setPreferredSize(commentDim);
        txtComment.setMaximumSize(commentDim);
        
        //padding
        panelPadding.setMinimumSize(new Dimension(10, 10));
        panelPadding.setPreferredSize(new Dimension(10, 10));
        panelPadding.setBackground(Color.red);
        
        panelPadding.setAlignmentX(panelReview.LEFT_ALIGNMENT);
        
        //alignment
        lblUsername.setAlignmentX(panelReview.LEFT_ALIGNMENT);
        lblRating.setAlignmentX(panelReview.LEFT_ALIGNMENT);
        txtComment.setAlignmentX(panelReview.LEFT_ALIGNMENT);
        
    }
    
    public void preparePanel(){
        panelReview.add(lblUsername);
        panelReview.add(lblDate);
        panelReview.add(lblRating);
        panelReview.add(txtComment);
        //panelReview.add(panelPadding);
    }
    
    public JPanel getReviewPanel(){
        setProperties();
        preparePanel();
        return panelReview;
    }
   
    
    
}
