/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */


import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;


public class Admin extends javax.swing.JFrame {
    


    private JButton activeButton; // Tracks which nav button is active

    // Dashboard PNGs
    private final ImageIcon dashDefault = new ImageIcon(getClass().getResource("/assets/icons/Admin-inactive/AdminDashboard_inactive_btn.png"));
    private final ImageIcon dashHover = new ImageIcon(getClass().getResource("/assets/icons/admin-inactive-hover/AdminDashboard_inactive_hover_btn.png"));
    private final ImageIcon dashClick = new ImageIcon(getClass().getResource("/assets/icons/Admin-click/AdminDashboard_click_btn.png"));
    private final ImageIcon dashActive = new ImageIcon(getClass().getResource("/assets/icons/Admin-active/AdminDashboard_active_btn.png"));
    //Accounts Png
    private final ImageIcon accountsDefault = new ImageIcon(getClass().getResource("/assets/icons/Admin-inactive/AdminAccounts_inactive_btn.png"));
    private final ImageIcon accountsHover = new ImageIcon(getClass().getResource("/assets/icons/admin-inactive-hover/AdminAccounts_inactive_hover_btn.png"));
    private final ImageIcon accountsClick = new ImageIcon(getClass().getResource("/assets/icons/Admin-click/AdminAccounts_click_btn.png"));
    private final ImageIcon accountsActive = new ImageIcon(getClass().getResource("/assets/icons/Admin-active/AdminAccounts_active_btn.png"));
    //Program and Fees
    private final ImageIcon pgfDefault = new ImageIcon(getClass().getResource("/assets/icons/Admin-inactive/AdminPGF_inactive_btn.png"));
    private final ImageIcon pgfHover = new ImageIcon(getClass().getResource("/assets/icons/admin-inactive-hover/AdminPGF_inactive_hover_btn.png"));
    private final ImageIcon pgfClick = new ImageIcon(getClass().getResource("/assets/icons/Admin-click/AdminPGF_click_btn.png"));
    private final ImageIcon pgfActive = new ImageIcon(getClass().getResource("/assets/icons/Admin-active/AdminPGF_active_btn.png"));
    // Security
    private final ImageIcon secDefault = new ImageIcon(getClass().getResource("/assets/icons/Admin-inactive/AdminSec_inactive_btn.png"));
    private final ImageIcon secHover = new ImageIcon(getClass().getResource("/assets/icons/admin-inactive-hover/AdminSec_inactive_hover_btn.png"));
    private final ImageIcon secClick = new ImageIcon(getClass().getResource("/assets/icons/Admin-click/AdminSec_click_btn.png"));
    private final ImageIcon secActive = new ImageIcon(getClass().getResource("/assets/icons/Admin-active/AdminSec_active_btn.png"));
    // Sales and records
    private final ImageIcon salesDefault = new ImageIcon(getClass().getResource("/assets/icons/Admin-inactive/AdminSales_inactive_btn.png"));
    private final ImageIcon salesHover = new ImageIcon(getClass().getResource("/assets/icons/admin-inactive-hover/AdminSales_inactive_hover_btn.png"));
    private final ImageIcon salesClick = new ImageIcon(getClass().getResource("/assets/icons/Admin-click/AdminSales_click_btn.png"));
    private final ImageIcon salesActive = new ImageIcon(getClass().getResource("/assets/icons/Admin-active/AdminSales_active_btn.png"));

    public Admin() {
        initComponents();
        new RoundedCornersMac(this); //rounded corners for mac
        setBackground(new java.awt.Color(0, 0, 0, 0));
        initClock();
        
        // on startup menu
        DashPanel.setVisible(true);
        AccountPanel.setVisible(false);
        PGFpanel.setVisible(false);
        SecPanel.setVisible(false);
        SalesPanel.setVisible(false);
        
        activeButton = Dashboard;
        Dashboard.setIcon(dashActive);
    }
    

    
            private void initClock() {
        // Create a timer that updates every second (1000ms)
        Timer clockTimer = new Timer(1000, e -> {
            // Format the current time (12-hour format with AM/PM)
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
            Time.setText(sdf.format(new Date()));
        });
        clockTimer.start(); // Start the timer
        
        // Set initial time immediately
        Time.setText(new SimpleDateFormat("hh:mm a").format(new Date()));
    }
    
            
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dragBarPanel = new javax.swing.JPanel();
        btnClose = new javax.swing.JButton();
        btnMin = new javax.swing.JButton();
        Main = new javax.swing.JPanel();
        topDefault = new javax.swing.JPanel();
        Time = new javax.swing.JLabel();
        roleIdentifier = new javax.swing.JLabel();
        SalesPanel = new javax.swing.JPanel();
        salesPanel = new javax.swing.JLabel();
        SecPanel = new javax.swing.JPanel();
        securityPanel = new javax.swing.JLabel();
        PGFpanel = new javax.swing.JPanel();
        pgfBG = new javax.swing.JLabel();
        DashPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        dash = new javax.swing.JLabel();
        AccountPanel = new javax.swing.JPanel();
        AccountBG = new javax.swing.JLabel();
        sideNav = new javax.swing.JPanel();
        Sales = new javax.swing.JButton();
        Security = new javax.swing.JButton();
        PGFbtn = new javax.swing.JButton();
        Accounts = new javax.swing.JButton();
        Dashboard = new javax.swing.JButton();
        RawPanel = new javax.swing.JLabel();
        DefaultBg = new javax.swing.JPanel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dragBarPanel.setOpaque(false);
        dragBarPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        final Point[] mousePoint = {null};

        dragBarPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mousePoint[0] = evt.getPoint();
            }
        });

        dragBarPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                Point location = evt.getLocationOnScreen();
                setLocation(location.x - mousePoint[0].x, location.y - mousePoint[0].y);
            }
        });

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/close_btn.png")));
        btnClose.setBorderPainted(false);
        btnClose.setContentAreaFilled(false);
        btnClose.setFocusPainted(false);
        btnClose.setOpaque(false);

        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/close_hover_btn.png")));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/close_btn.png")));
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/close_click_btn.png")));
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/close_hover_btn.png")));
            }
        });

        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });
        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/close_btn.png"))); // NOI18N
        btnClose.setAlignmentX(0.2F);
        btnClose.setContentAreaFilled(false);
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dragBarPanel.add(btnClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(1227, 2, 40, 40));

        btnMin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/min_btn.png")));
        btnMin.setBorderPainted(false);
        btnMin.setContentAreaFilled(false);
        btnMin.setFocusPainted(false);
        btnMin.setOpaque(false);

        btnMin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/min_hover_btn.png")));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/min_btn.png")));
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnMin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/min_click_btn.png")));
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnMin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/min_hover_btn.png")));
            }
        });

        btnMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ((java.awt.Frame) btnMin.getTopLevelAncestor()).setState(java.awt.Frame.ICONIFIED);
            }
        });
        btnMin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/min_btn.png"))); // NOI18N
        btnMin.setContentAreaFilled(false);
        btnMin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dragBarPanel.add(btnMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(1178, 2, 40, 40));

        getContentPane().add(dragBarPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 50));

        Main.setMaximumSize(new java.awt.Dimension(1280, 720));
        Main.setOpaque(false);
        Main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        topDefault.setOpaque(false);
        topDefault.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Time.setFont(new java.awt.Font("Helvetica", 1, 14)); // NOI18N
        Time.setForeground(new java.awt.Color(30, 53, 118));
        Time.setText("00:00 PM");
        topDefault.add(Time, new org.netbeans.lib.awtextra.AbsoluteConstraints(1179, 60, -1, 20));

        roleIdentifier.setFont(new java.awt.Font("Helvetica", 1, 18)); // NOI18N
        roleIdentifier.setForeground(new java.awt.Color(30, 53, 118));
        roleIdentifier.setText("Admin");
        topDefault.add(roleIdentifier, new org.netbeans.lib.awtextra.AbsoluteConstraints(385, 36, -1, 20));

        Main.add(topDefault, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        SalesPanel.setOpaque(false);
        SalesPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        salesPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Admin Windows/SalesRec.png"))); // NOI18N
        SalesPanel.add(salesPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Main.add(SalesPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        SecPanel.setOpaque(false);
        SecPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        securityPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Admin Windows/Sec.png"))); // NOI18N
        SecPanel.add(securityPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Main.add(SecPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        PGFpanel.setOpaque(false);
        PGFpanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pgfBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Admin Windows/ProgFees.png"))); // NOI18N
        PGFpanel.add(pgfBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Main.add(PGFpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        DashPanel.setOpaque(false);
        DashPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        DashPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, 790, 570));

        dash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Admin Windows/Dashboard.png"))); // NOI18N
        DashPanel.add(dash, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Main.add(DashPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        AccountPanel.setOpaque(false);
        AccountPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AccountBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Admin Windows/Accounts.png"))); // NOI18N
        AccountPanel.add(AccountBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Main.add(AccountPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        sideNav.setMaximumSize(new java.awt.Dimension(1280, 720));
        sideNav.setOpaque(false);
        sideNav.setLayout(null);
        sideNav.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Sales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/Admin-inactive/AdminSales_inactive_btn.png"))); // NOI18N
        Sales.setContentAreaFilled(false);
        Sales.setMaximumSize(new java.awt.Dimension(254, 52));
        Sales.setMinimumSize(new java.awt.Dimension(252, 52));
        Sales.setBorderPainted(false);
        Sales.setContentAreaFilled(false);
        Sales.setFocusPainted(false);
        Sales.setBorderPainted(false);

        Sales.setContentAreaFilled(false);

        Sales.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Sales.setIconTextGap(0);

        Sales.setMargin(new java.awt.Insets(0, 0, 0, 0));

        Sales.setOpaque(false);
        Sales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (activeButton != Sales) {
                    Sales.setIcon(salesHover);
                }
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (activeButton != Sales) {
                    Sales.setIcon(salesDefault);
                }
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                Sales.setIcon(salesClick);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                if (activeButton != Sales) {
                    Sales.setIcon(salesHover);
                }
            }
        });
        Sales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalesActionPerformed(evt);
            }
        });
        sideNav.add(Sales, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 426, 280, 50));

        Security.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/Admin-inactive/AdminSec_inactive_btn.png"))); // NOI18N
        Security.setContentAreaFilled(false);
        Security.setMaximumSize(new java.awt.Dimension(254, 52));
        Security.setMinimumSize(new java.awt.Dimension(252, 52));
        Security.setBorderPainted(false);
        Security.setContentAreaFilled(false);
        Security.setFocusPainted(false);
        Security.setBorderPainted(false);

        Security.setContentAreaFilled(false);

        Security.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Security.setIconTextGap(0);

        Security.setMargin(new java.awt.Insets(0, 0, 0, 0));

        Security.setOpaque(false);
        Security.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (activeButton != Security) {
                    Security.setIcon(secHover);
                }
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (activeButton != Security) {
                    Security.setIcon(secDefault);
                }
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                Security.setIcon(secClick);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                if (activeButton != Security) {
                    Security.setIcon(secHover);
                }
            }
        });
        Security.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SecurityActionPerformed(evt);
            }
        });
        sideNav.add(Security, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 366, 280, 50));

        PGFbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/Admin-inactive/AdminPGF_inactive_btn.png"))); // NOI18N
        PGFbtn.setContentAreaFilled(false);
        PGFbtn.setMaximumSize(new java.awt.Dimension(254, 52));
        PGFbtn.setMinimumSize(new java.awt.Dimension(252, 52));
        PGFbtn.setBorderPainted(false);
        PGFbtn.setContentAreaFilled(false);
        PGFbtn.setFocusPainted(false);
        PGFbtn.setBorderPainted(false);

        PGFbtn.setContentAreaFilled(false);

        PGFbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        PGFbtn.setIconTextGap(0);

        PGFbtn.setMargin(new java.awt.Insets(0, 0, 0, 0));

        PGFbtn.setOpaque(false);
        PGFbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (activeButton != PGFbtn) {
                    PGFbtn.setIcon(pgfHover);
                }
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (activeButton != PGFbtn) {
                    PGFbtn.setIcon(pgfDefault);
                }
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                PGFbtn.setIcon(pgfClick);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                if (activeButton != PGFbtn) {
                    PGFbtn.setIcon(pgfHover);
                }
            }
        });
        PGFbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PGFbtnActionPerformed(evt);
            }
        });
        sideNav.add(PGFbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 307, 280, 50));

        Accounts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/Admin-inactive/AdminAccounts_inactive_btn.png"))); // NOI18N
        Accounts.setBorderPainted(false);
        Accounts.setContentAreaFilled(false);
        Accounts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Accounts.setIconTextGap(0);
        Accounts.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Accounts.setOpaque(false);
        Accounts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (activeButton != Accounts) {
                    Accounts.setIcon(accountsHover);
                }
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (activeButton != Accounts) {
                    Accounts.setIcon(accountsDefault);
                }
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                Accounts.setIcon(accountsClick);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                if (activeButton != Accounts) {
                    Accounts.setIcon(accountsHover);
                }
            }
        });
        Accounts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccountsActionPerformed(evt);
            }
        });
        sideNav.add(Accounts, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 245, 280, 50));

        Dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/Admin-inactive/AdminDashboard_inactive_btn.png"))); // NOI18N
        Dashboard.setBorderPainted(false);
        Dashboard.setContentAreaFilled(false);
        Dashboard.setFocusPainted(false);
        Dashboard.setBorderPainted(false);
        Dashboard.setContentAreaFilled(false);
        Dashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Dashboard.setIconTextGap(0);
        Dashboard.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Dashboard.setOpaque(false);
        Dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (activeButton != Dashboard) {
                    Dashboard.setIcon(dashHover);
                }
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (activeButton != Dashboard) {
                    Dashboard.setIcon(dashDefault);
                }
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                Dashboard.setIcon(dashClick);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                if (activeButton != Dashboard) {
                    Dashboard.setIcon(dashHover);
                }
            }
        });
        Dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DashboardActionPerformed(evt);
            }
        });
        sideNav.add(Dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 185, 280, 50));

        RawPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Admin Windows/Admin_SideNav.png"))); // NOI18N
        sideNav.add(RawPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Main.add(sideNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        DefaultBg.setOpaque(false);
        DefaultBg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/bg.png"))); // NOI18N
        DefaultBg.add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Main.add(DefaultBg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        getContentPane().add(Main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void resetNavIcons() {
    if (activeButton != Dashboard) {
        Dashboard.setIcon(dashDefault);
    }
    
    if (activeButton != Accounts) {
        Accounts.setIcon(accountsDefault);
    }
    
    if (activeButton != PGFbtn) {
    PGFbtn.setIcon(pgfDefault);
    }
    
    if (activeButton != Security) {
    Security.setIcon(secDefault);
    }
    
    if (activeButton != Sales) {
    Sales.setIcon(salesDefault);
    } 
}

    private void switchPanel(javax.swing.JPanel panel, javax.swing.JButton button, ImageIcon activeIcon) {
        // Hide all panels
        DashPanel.setVisible(false);
        AccountPanel.setVisible(false);
        PGFpanel.setVisible(false);
        SecPanel.setVisible(false);
        SalesPanel.setVisible(false);
        // Add more here later like: SalesPanel.setVisible(false);

        // Show selected
        panel.setVisible(true);

        // Update nav icons
        resetNavIcons();
        button.setIcon(activeIcon);
        activeButton = button;
    }

    
    private void DashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DashboardActionPerformed
        switchPanel(DashPanel, Dashboard, dashActive);
    }//GEN-LAST:event_DashboardActionPerformed

    private void AccountsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AccountsActionPerformed
        switchPanel(AccountPanel, Accounts, accountsActive);
    }//GEN-LAST:event_AccountsActionPerformed

    private void PGFbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PGFbtnActionPerformed
         switchPanel(PGFpanel, PGFbtn, pgfActive);
    }//GEN-LAST:event_PGFbtnActionPerformed

    private void SecurityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SecurityActionPerformed
        switchPanel(SecPanel, Security, secActive);
    }//GEN-LAST:event_SecurityActionPerformed

    private void SalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalesActionPerformed
        switchPanel(SalesPanel, Sales, salesActive);
    }//GEN-LAST:event_SalesActionPerformed

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
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AccountBG;
    private javax.swing.JPanel AccountPanel;
    private javax.swing.JButton Accounts;
    private javax.swing.JPanel DashPanel;
    private javax.swing.JButton Dashboard;
    private javax.swing.JPanel DefaultBg;
    private javax.swing.JPanel Main;
    private javax.swing.JButton PGFbtn;
    private javax.swing.JPanel PGFpanel;
    private javax.swing.JLabel RawPanel;
    private javax.swing.JButton Sales;
    private javax.swing.JPanel SalesPanel;
    private javax.swing.JPanel SecPanel;
    private javax.swing.JButton Security;
    private javax.swing.JLabel Time;
    private javax.swing.JLabel bg;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnMin;
    private javax.swing.JLabel dash;
    private javax.swing.JPanel dragBarPanel;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel pgfBG;
    private javax.swing.JLabel roleIdentifier;
    private javax.swing.JLabel salesPanel;
    private javax.swing.JLabel securityPanel;
    private javax.swing.JPanel sideNav;
    private javax.swing.JPanel topDefault;
    // End of variables declaration//GEN-END:variables
}
