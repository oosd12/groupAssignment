/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import CoreClasses.Customer;
import CoreClasses.DBConnector;
import CoreClasses.Product;
import CoreClasses.ShoppingCart;
import CoreClasses.Supplier;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.TableColumnModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Abdullah
 */
public class MainWindow extends javax.swing.JFrame {

    int productID, supplierID, quantity;

    //Make initial connection to DB
    java.sql.Connection conn = new DBConnector().connect();

    Product p = new Product();
    Customer c = new Customer();
    ShoppingCart sc = new ShoppingCart();

    /**
     * Creates new form MainWindow3
     */
    public MainWindow() {
        //look & feel
        initComponents();
        String s;
        s = "de.javasoft.plaf.synthetica.SyntheticaClassyLookAndFeel"; 

        try {
            javax.swing.UIManager.setLookAndFeel(s);
            SwingUtilities.updateComponentTreeUI(this);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
        }

        //Showing logged out controls only
        panelAccountControls.removeAll();
        panelAccountControls.repaint();
        panelAccountControls.revalidate();

        panelAccountControls.add(panelNotLoggedIn);

        panelAccountControls.repaint();
        panelAccountControls.revalidate();
    }

    public MainWindow(int userType) {
        //Setting look and feel
        initComponents();
        String s;
        s = "de.javasoft.plaf.synthetica.SyntheticaClassyLookAndFeel";

        try {
            javax.swing.UIManager.setLookAndFeel(s);
            SwingUtilities.updateComponentTreeUI(this);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
        }

        if (userType == 1) {
            //Showing customer controls 
            panelAccountControls.removeAll();
            panelAccountControls.repaint();
            panelAccountControls.revalidate();

            panelAccountControls.add(panelCustomerLogin);

            panelAccountControls.repaint();
            panelAccountControls.revalidate();

            //Showing greeting message
            lblGreet1.setText("Hi " + c.getCurrentCustomerName() + ", welcome back!");
        }
        else if(userType == 2){
             //Showing customer controls 
            panelAccountControls.removeAll();
            panelAccountControls.repaint();
            panelAccountControls.revalidate();

            panelAccountControls.add(panelAdminLogin);

            panelAccountControls.repaint();
            panelAccountControls.revalidate();
            
            lblCustomerName.setText(c.getCurrentCustomerName());
        }
    }

    public void refreshTable() {
        tblProducts.setModel(DbUtils.resultSetToTableModel(p.getAvailableProducts()));
        adjustColumns();
        txtProductName.setText("");
        txtSupplierName.setText("");
        txtPrice.setText("");
        ftxtProductionDate.setText("");

    }

    public void disableButton() {
        if (txtProductName.getText().equals("")) {
            btnAddToCart.setEnabled(false);
            btnViewReview.setEnabled(false);
        } else {
            btnAddToCart.setEnabled(true);
            btnViewReview.setEnabled(true);
        }
    }

    public void adjustColumns() {
        TableColumnModel tcm = tblProducts.getColumnModel();
        tcm.removeColumn(tcm.getColumn(0));//removing product ID
        tcm.removeColumn(tcm.getColumn(0));//removing supplier ID
        tcm.removeColumn(tcm.getColumn(6));
        tcm.removeColumn(tcm.getColumn(6));//removing City
        
        tcm.getColumn(0).setHeaderValue("Product");
        tcm.getColumn(1).setHeaderValue("Supplier");
        tcm.getColumn(2).setHeaderValue("Quantity");
        tcm.getColumn(3).setHeaderValue("Manufactured");
        tcm.getColumn(4).setHeaderValue("Price");
        tcm.getColumn(5).setHeaderValue("Category");


        //adjusting cell widths of the table
        tcm.getColumn(2).setMaxWidth(70);
        tcm.getColumn(4).setMaxWidth(70);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContainer = new javax.swing.JPanel();
        panelAccountControls = new javax.swing.JPanel();
        panelNotLoggedIn = new javax.swing.JPanel();
        lblGreet = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        btnRegister = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        panelCustomerLogin = new javax.swing.JPanel();
        lblGreet1 = new javax.swing.JLabel();
        lblItems = new javax.swing.JLabel();
        lblMessages = new javax.swing.JLabel();
        btnViewCart = new javax.swing.JButton();
        btnMessages = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        panelAdminLogin = new javax.swing.JPanel();
        lblCustomerName = new javax.swing.JLabel();
        lblItems1 = new javax.swing.JLabel();
        btnViewCart1 = new javax.swing.JButton();
        btnCancelPhoneOrder = new javax.swing.JButton();
        lblGreet3 = new javax.swing.JLabel();
        panelProductInfo = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtProductName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSupplierName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        ftxtProductionDate = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        spnQuantity = new javax.swing.JSpinner();
        btnAddToCart = new javax.swing.JButton();
        btnViewReview = new javax.swing.JButton();
        lblProductImage = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        panelTop = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSearchBar = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        cmbFilter = new javax.swing.JComboBox<>();
        cmbSort = new javax.swing.JComboBox<>();
        cmbSupplier = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panelAccountControls.setBackground(new java.awt.Color(51, 153, 255));
        panelAccountControls.setOpaque(false);
        panelAccountControls.setLayout(new java.awt.CardLayout());

        panelNotLoggedIn.setBackground(new java.awt.Color(204, 51, 255));
        panelNotLoggedIn.setOpaque(false);

        lblGreet.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblGreet.setText("<html>Register with us and login to <br> purchase products online.... </html>");

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnRegister.setText("Register");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        jLabel9.setText("In a hurry ? Place a phone order, call : +94-2XXXXX ");

        javax.swing.GroupLayout panelNotLoggedInLayout = new javax.swing.GroupLayout(panelNotLoggedIn);
        panelNotLoggedIn.setLayout(panelNotLoggedInLayout);
        panelNotLoggedInLayout.setHorizontalGroup(
            panelNotLoggedInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNotLoggedInLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNotLoggedInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGreet, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        panelNotLoggedInLayout.setVerticalGroup(
            panelNotLoggedInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNotLoggedInLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGreet, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRegister)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        panelAccountControls.add(panelNotLoggedIn, "card2");

        panelCustomerLogin.setBackground(new java.awt.Color(255, 51, 51));
        panelCustomerLogin.setOpaque(false);

        lblGreet1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblGreet1.setText("<html>Hi Abdullah, welcome back!</html>");

        lblItems.setText("Items in Cart : 0");

        lblMessages.setText("Messages : 0");

        btnViewCart.setText("View Cart");
        btnViewCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewCartActionPerformed(evt);
            }
        });

        btnMessages.setText("Messages");
        btnMessages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMessagesActionPerformed(evt);
            }
        });

        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCustomerLoginLayout = new javax.swing.GroupLayout(panelCustomerLogin);
        panelCustomerLogin.setLayout(panelCustomerLoginLayout);
        panelCustomerLoginLayout.setHorizontalGroup(
            panelCustomerLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustomerLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCustomerLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGreet1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblItems, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMessages, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViewCart, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMessages, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        panelCustomerLoginLayout.setVerticalGroup(
            panelCustomerLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustomerLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGreet1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblItems, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMessages, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnViewCart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMessages)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLogout)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        panelAccountControls.add(panelCustomerLogin, "card3");

        panelAdminLogin.setBackground(new java.awt.Color(255, 153, 51));
        panelAdminLogin.setOpaque(false);

        lblCustomerName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCustomerName.setText("Abdullah");

        lblItems1.setText("Items in Cart : 0");

        btnViewCart1.setText("View Cart");
        btnViewCart1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewCart1ActionPerformed(evt);
            }
        });

        btnCancelPhoneOrder.setText("Cancel Phone Order");
        btnCancelPhoneOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelPhoneOrderActionPerformed(evt);
            }
        });

        lblGreet3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblGreet3.setText("<html>Placing Phone Order For Customer : </html>");

        javax.swing.GroupLayout panelAdminLoginLayout = new javax.swing.GroupLayout(panelAdminLogin);
        panelAdminLogin.setLayout(panelAdminLoginLayout);
        panelAdminLoginLayout.setHorizontalGroup(
            panelAdminLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAdminLoginLayout.createSequentialGroup()
                .addGroup(panelAdminLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGreet3, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblItems1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelAdminLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnViewCart1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelPhoneOrder, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 23, Short.MAX_VALUE))
        );
        panelAdminLoginLayout.setVerticalGroup(
            panelAdminLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAdminLoginLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblGreet3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblItems1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnViewCart1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelPhoneOrder)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        panelAccountControls.add(panelAdminLogin, "card4");

        panelProductInfo.setBackground(new java.awt.Color(51, 51, 255));
        panelProductInfo.setOpaque(false);

        jLabel3.setText("Product Name");

        txtProductName.setEditable(false);

        jLabel4.setText("Supplier Name");

        txtSupplierName.setEditable(false);

        jLabel5.setText("Price");

        txtPrice.setEditable(false);

        jLabel6.setText("Production Date");

        ftxtProductionDate.setEditable(false);
        ftxtProductionDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("d/MM/yyyy"))));
        ftxtProductionDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ftxtProductionDateActionPerformed(evt);
            }
        });

        jLabel7.setText("Quantity");

        spnQuantity.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        btnAddToCart.setText("Add To Cart");
        btnAddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToCartActionPerformed(evt);
            }
        });

        btnViewReview.setText("View Reviews");
        btnViewReview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewReviewActionPerformed(evt);
            }
        });

        lblProductImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProductImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setBackground(java.awt.SystemColor.controlHighlight);
        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel8.setOpaque(true);

        javax.swing.GroupLayout panelProductInfoLayout = new javax.swing.GroupLayout(panelProductInfo);
        panelProductInfo.setLayout(panelProductInfoLayout);
        panelProductInfoLayout.setHorizontalGroup(
            panelProductInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProductInfoLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(panelProductInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelProductInfoLayout.createSequentialGroup()
                        .addComponent(btnAddToCart, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnViewReview))
                    .addComponent(jLabel4)
                    .addGroup(panelProductInfoLayout.createSequentialGroup()
                        .addGroup(panelProductInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(30, 30, 30)
                        .addGroup(panelProductInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(ftxtProductionDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSupplierName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProductName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrice, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spnQuantity, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblProductImage, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelProductInfoLayout.setVerticalGroup(
            panelProductInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProductInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelProductInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelProductInfoLayout.createSequentialGroup()
                        .addGroup(panelProductInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelProductInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtSupplierName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelProductInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelProductInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(ftxtProductionDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelProductInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(spnQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelProductInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddToCart)
                            .addComponent(btnViewReview)))
                    .addComponent(lblProductImage, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelTop.setBackground(new java.awt.Color(0, 51, 51));
        panelTop.setOpaque(false);

        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblProducts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProducts);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Welcome To Happy Shoppers");

        jLabel2.setText("Search For Products");

        txtSearchBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchBarActionPerformed(evt);
            }
        });

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        cmbFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Categories", "Beverages", "Canned Goods", "Dairy", "Frozen Foods", "Fruits", "Meat", "Vegetables" }));
        cmbFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFilterActionPerformed(evt);
            }
        });

        cmbSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Price Highest to Lowest", "Price Lowest to Highest", "Quantity Highest to Lowest", "Quantity Lowest to Highest", "Production Date Latest to Oldest", "Production Date Oldest to Latest" }));
        cmbSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSortActionPerformed(evt);
            }
        });

        cmbSupplier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Supplier City..." }));

        javax.swing.GroupLayout panelTopLayout = new javax.swing.GroupLayout(panelTop);
        panelTop.setLayout(panelTopLayout);
        panelTopLayout.setHorizontalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTopLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopLayout.createSequentialGroup()
                        .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 795, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelTopLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtSearchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(51, 51, 51))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(253, 253, 253))))
        );
        panelTopLayout.setVerticalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSearchBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addComponent(cmbFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelContainerLayout = new javax.swing.GroupLayout(panelContainer);
        panelContainer.setLayout(panelContainerLayout);
        panelContainerLayout.setHorizontalGroup(
            panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelContainerLayout.createSequentialGroup()
                .addComponent(panelProductInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelAccountControls, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelContainerLayout.setVerticalGroup(
            panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContainerLayout.createSequentialGroup()
                .addComponent(panelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelAccountControls, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelProductInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchBarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchBarActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        //Getting the conditions to search for
        String keyword = txtSearchBar.getText();
        String filter = cmbFilter.getSelectedItem().toString();
        String sort = cmbSort.getSelectedItem().toString();
        String location = cmbSupplier.getSelectedItem().toString();

        tblProducts.setModel(DbUtils.resultSetToTableModel(p.searchForProducts(keyword, filter, sort, location)));
        adjustColumns();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void cmbFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbFilterActionPerformed

    private void cmbSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSortActionPerformed

    private void ftxtProductionDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftxtProductionDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ftxtProductionDateActionPerformed

    private void btnViewReviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewReviewActionPerformed
        new ReviewGUI(this.productID, this.supplierID).setVisible(true);
    }//GEN-LAST:event_btnViewReviewActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        refreshTable();
        disableButton();
        Supplier s = new Supplier();
        //Display all supplier locations (cities)
        cmbSupplier.setModel(new javax.swing.DefaultComboBoxModel(s.getAllSupplierNames().toArray()));

        //Display No. Items in cart
        if (Customer.getCurrentCustomer() != null) {
            lblItems.setText("Items in Cart : " + sc.getNoItemsInCart());
            lblItems1.setText("Items in Cart : " + sc.getNoItemsInCart());
        }
        
        if (!(Customer.getCurrentCustomer().equals(""))) {
            lblItems.setText("Items in Cart : " + sc.getNoItemsInCart());
            lblItems1.setText("Items in Cart : " + sc.getNoItemsInCart());
        }

    }//GEN-LAST:event_formWindowOpened

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        Login l = new Login();
        l.setVisible(true);
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        dispose();
        Registration r1 = new Registration();
        r1.setVisible(true);
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void btnViewCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewCartActionPerformed
        new ShoppingCartGUI().setVisible(true);
    }//GEN-LAST:event_btnViewCartActionPerformed

    private void btnMessagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMessagesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMessagesActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        sc.emptyShoppingCart();
        Customer.setCurrentCustomer("");
        JOptionPane.showMessageDialog(this, "Logged out successfully", "Logout Successful", JOptionPane.INFORMATION_MESSAGE);

        //Closing all open windows
        java.awt.Window win[] = java.awt.Window.getWindows();
        for (int i = 0; i < win.length; i++) {
            win[i].dispose();
        }

        new MainWindow().setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnViewCart1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewCart1ActionPerformed
       new ShoppingCartGUI().setVisible(true);
        System.out.println(""+conn);
    }//GEN-LAST:event_btnViewCart1ActionPerformed

    private void btnCancelPhoneOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelPhoneOrderActionPerformed
        sc.emptyShoppingCart();
        Customer.setCurrentCustomer("");
        JOptionPane.showMessageDialog(this, "Phone order cancelled", "Phone order cancelled", JOptionPane.INFORMATION_MESSAGE);

        //Closing all open windows
        java.awt.Window win[] = java.awt.Window.getWindows();
        for (int i = 0; i < win.length; i++) {
            win[i].dispose();
        }

        new AdminDashboard().setVisible(true);
    }//GEN-LAST:event_btnCancelPhoneOrderActionPerformed

    private void tblProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductsMouseClicked
        int row = tblProducts.getSelectedRow();
        this.productID = Integer.parseInt((tblProducts.getModel().getValueAt(row, 0).toString()));
        this.supplierID = Integer.parseInt((tblProducts.getModel().getValueAt(row, 1).toString()));

        txtProductName.setText((tblProducts.getModel().getValueAt(row, 2).toString()));
        txtSupplierName.setText((tblProducts.getModel().getValueAt(row, 3).toString()));
        txtPrice.setText((tblProducts.getModel().getValueAt(row, 6).toString()));
        ftxtProductionDate.setText((tblProducts.getModel().getValueAt(row, 5).toString()));

        //Getting the image for the product and loading it into the label
        String image_link = tblProducts.getModel().getValueAt(row, 8).toString();
        Image image = null;
        try {
            URL url = new URL("" + image_link);
            image = ImageIO.read(url);
        } catch (IOException e) {
            System.out.println("Error " + e);
        }
        Image scaledInstance = image.getScaledInstance(193, 162, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(scaledInstance);
        lblProductImage.setIcon(icon);

        //Setting the spinner maximum
        int maxQuantity = Integer.parseInt((tblProducts.getModel().getValueAt(row, 4).toString()));
        SpinnerNumberModel model = new SpinnerNumberModel(1, 1, maxQuantity, 1);
        spnQuantity.setModel(model);

        disableButton();

    }//GEN-LAST:event_tblProductsMouseClicked

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        refreshTable();

        //Display No. Items in cart
        if (Customer.getCurrentCustomer() != null) {
            lblItems.setText("Items in Cart : " + sc.getNoItemsInCart());
            lblItems1.setText("Items in Cart : " + sc.getNoItemsInCart());
        } else if (!(Customer.getCurrentCustomer().equals(""))) {
            lblItems.setText("Items in Cart : " + sc.getNoItemsInCart());
            lblItems1.setText("Items in Cart : " + sc.getNoItemsInCart());
        }
    }//GEN-LAST:event_formWindowGainedFocus

    private void btnAddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToCartActionPerformed
        //if order customer is not logged in or phone order is not being placed do not add to cart
        if (Customer.getCurrentCustomer() == null) {
            JOptionPane.showMessageDialog(this, "You are not logged in, cannot add to cart.\n Please register and login to add items to your shopping cart", "Please Login", JOptionPane.ERROR_MESSAGE);
        } else if (Customer.getCurrentCustomer().equals("")) {
            JOptionPane.showMessageDialog(this, "You are not logged in, cannot add to cart.\n Please register and login to add items to your shopping cart", "Please Login", JOptionPane.ERROR_MESSAGE);
        } else {
            sc.addToCart(this.productID, this.supplierID, (int) spnQuantity.getValue());
            JOptionPane.showMessageDialog(null, spnQuantity.getValue() + " " + txtProductName.getText() + " added to shopping cart", "Added to cart", JOptionPane.INFORMATION_MESSAGE);
            refreshTable();
        }

//JOptionPane.showMessageDialog(null, ""+Customer.getCurrentCustomer(), "Added to cart", JOptionPane.INFORMATION_MESSAGE);
        disableButton();
    }//GEN-LAST:event_btnAddToCartActionPerformed

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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddToCart;
    private javax.swing.JButton btnCancelPhoneOrder;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMessages;
    private javax.swing.JButton btnRegister;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnViewCart;
    private javax.swing.JButton btnViewCart1;
    private javax.swing.JButton btnViewReview;
    private javax.swing.JComboBox<String> cmbFilter;
    private javax.swing.JComboBox<String> cmbSort;
    private javax.swing.JComboBox<String> cmbSupplier;
    private javax.swing.JFormattedTextField ftxtProductionDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCustomerName;
    private javax.swing.JLabel lblGreet;
    private javax.swing.JLabel lblGreet1;
    private javax.swing.JLabel lblGreet3;
    private javax.swing.JLabel lblItems;
    private javax.swing.JLabel lblItems1;
    private javax.swing.JLabel lblMessages;
    private javax.swing.JLabel lblProductImage;
    private javax.swing.JPanel panelAccountControls;
    private javax.swing.JPanel panelAdminLogin;
    private javax.swing.JPanel panelContainer;
    private javax.swing.JPanel panelCustomerLogin;
    private javax.swing.JPanel panelNotLoggedIn;
    private javax.swing.JPanel panelProductInfo;
    private javax.swing.JPanel panelTop;
    private javax.swing.JSpinner spnQuantity;
    private javax.swing.JTable tblProducts;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtSearchBar;
    private javax.swing.JTextField txtSupplierName;
    // End of variables declaration//GEN-END:variables
}
