/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author canef
 */
import java.awt.*;              // All AWT classes (e.g., Color, Font, etc.)
import java.sql.*;              // All SQL-related classes (Connection, PreparedStatement, ResultSet, etc.)
import javax.swing.*;           // All Swing UI components



public class LoginPage extends javax.swing.JFrame {

    /**
     * Creates new form LoginPage
     */
    public LoginPage() {
        initComponents();
        
        new RoundedCornersMac(this); //rounded corners for mac
        
           // Delay rootPane access until frame is ready
        this.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowOpened(java.awt.event.WindowEvent e) {
        getRootPane().setDefaultButton(null);
        emailTxt.requestFocusInWindow();
    }
});
        setBackground(new java.awt.Color(0, 0, 0, 0));

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dragBarPanel = new javax.swing.JPanel();
        btnClose = new javax.swing.JButton();
        btnMin = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        FrgtPw = new javax.swing.JButton();
        btnEnter = new javax.swing.JButton();
        chkShowPassword = new javax.swing.JCheckBox();
        emailTxt = new javax.swing.JTextField();
        Email = new javax.swing.JLabel();
        pwTxt = new javax.swing.JPasswordField();
        pw = new javax.swing.JLabel();
        signInWindow = new javax.swing.JLabel();
        LoginPage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LoginPage");
        setAutoRequestFocus(false);
        setName("LoginPage"); // NOI18N
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dragBarPanel.setOpaque(false);

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
        btnClose.setContentAreaFilled(false);

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

        javax.swing.GroupLayout dragBarPanelLayout = new javax.swing.GroupLayout(dragBarPanel);
        dragBarPanel.setLayout(dragBarPanelLayout);
        dragBarPanelLayout.setHorizontalGroup(
            dragBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dragBarPanelLayout.createSequentialGroup()
                .addContainerGap(1156, Short.MAX_VALUE)
                .addComponent(btnMin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        dragBarPanelLayout.setVerticalGroup(
            dragBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dragBarPanelLayout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addGroup(dragBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(dragBarPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 50));

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FrgtPw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/labels/frgtpass_btn.png")));
        FrgtPw.setBorderPainted(false);
        FrgtPw.setContentAreaFilled(false);
        FrgtPw.setFocusPainted(false);
        FrgtPw.setOpaque(false);

        FrgtPw.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                FrgtPw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/labels/frgtpass_btn_hover.png")));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                FrgtPw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/labels/frgtpass_btn.png")));
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                FrgtPw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/labels/frgtpass_click_btn.png")));
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                FrgtPw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/labels/frgtpass_btn_hover.png")));
            }
        });
        FrgtPw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FrgtPwActionPerformed(evt);
            }
        });
        jPanel1.add(FrgtPw, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 530, 150, 30));

        btnEnter.setText("");
        btnEnter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/login_btn.png")));
        btnEnter.setBorderPainted(false);
        btnEnter.setContentAreaFilled(false);
        btnEnter.setFocusPainted(false);
        btnEnter.setOpaque(false);

        btnEnter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEnter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/login_btn_hover.png")));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEnter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/login_btn.png")));
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnEnter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/login_btn_click.png")));
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnEnter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/login_btn_hover.png")));
            }
        });

        btnEnter.addActionListener(e -> {
            btnEnter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/login_btn_click.png")));
            // Run validation
        });
        btnEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnterActionPerformed(evt);
            }
        });
        jPanel1.add(btnEnter, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 405, 33, 33));

        chkShowPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/eye_hide.png")));
        chkShowPassword.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/eye_show.png")));
        chkShowPassword.setBorderPainted(false);
        chkShowPassword.setContentAreaFilled(false);
        chkShowPassword.setFocusPainted(false);
        chkShowPassword.setOpaque(false);
        chkShowPassword.setRolloverEnabled(false);

        chkShowPassword.addActionListener(e -> {
            if (chkShowPassword.isSelected()) {
                pwTxt.setEchoChar((char) 0); // Show password as plain text
            } else {
                pwTxt.setEchoChar('•'); // Hide password with bullet (U+2022)
            }
        });
        jPanel1.add(chkShowPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(1115, 407, 30, 30));

        emailTxt.setBorder(null);
        emailTxt.setOpaque(false);
        emailTxt.setBackground(new java.awt.Color(0,0,0,0)); // transparent
        emailTxt.setForeground(java.awt.Color.BLACK); // or any text color you want
        emailTxt.setCaretColor(java.awt.Color.BLACK); // cursor color
        emailTxt.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        emailTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pwTxt.requestFocusInWindow(); // move to password field
            }
        });

        emailTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Email.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/labels/email_active.png")));
                pw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/labels/pw.png"))); // reset pw
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (emailTxt.getText().trim().isEmpty()) {
                    Email.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/labels/email.png")));
                }
            }
        });
        jPanel1.add(emailTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 332, 360, 30));

        Email.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/labels/email.png"))); // NOI18N
        jPanel1.add(Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 300, 400, 70));

        pwTxt.setBorder(null);
        pwTxt.setOpaque(false);
        pwTxt.setBackground(new java.awt.Color(0,0,0,0)); // transparent
        pwTxt.setForeground(java.awt.Color.BLACK); // text color
        pwTxt.setCaretColor(java.awt.Color.BLACK); // cursor color
        pwTxt.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        pwTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnter.doClick(); // simulate clicking login
            }
        });
        pwTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pwTxtActionPerformed(evt);
            }
        });
        pwTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/labels/pw_active.png")));
                Email.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/labels/email.png"))); // reset email
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (new String(pwTxt.getPassword()).trim().isEmpty()) {
                    pw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/labels/pw.png")));
                }
            }
        });
        jPanel1.add(pwTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 420, 290, 30));

        pw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/labels/pw.png"))); // NOI18N
        jPanel1.add(pw, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 390, 400, -1));

        signInWindow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/labels/SigninWindows.png"))); // NOI18N
        jPanel1.add(signInWindow, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 140, -1, -1));

        LoginPage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/rawLogin.png"))); // NOI18N
        LoginPage.setOpaque(false);
        jPanel1.add(LoginPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void FrgtPwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FrgtPwActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FrgtPwActionPerformed

    private void pwTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pwTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pwTxtActionPerformed

    private void btnEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnterActionPerformed

        String email = emailTxt.getText().trim();
        String password = new String(pwTxt.getPassword()).trim();
    
        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter email and password.", "Missing Fields", JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        try (Connection conn = db.connect()) {
            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, email);
                stmt.setString(2, password);
    
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String role = rs.getString("role").toLowerCase();
    
                        switch (role) {
                            case "admin":
                                new Admin().setVisible(true);
                                break;
                            case "cashier":
                                new Cashier().setVisible(true);
                                break;
                            case "student":
                                new Student().setVisible(true);
                                break;
                            default:
                                JOptionPane.showMessageDialog(this, "Unrecognized role: " + role, "Login Error", JOptionPane.ERROR_MESSAGE);
                                return;
                        }
    
                        this.dispose(); // Close login window after redirect
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid email or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    
    }//GEN-LAST:event_btnEnterActionPerformed
    

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new LoginPage().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Email;
    private javax.swing.JButton FrgtPw;
    private javax.swing.JLabel LoginPage;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnEnter;
    private javax.swing.JButton btnMin;
    private javax.swing.JCheckBox chkShowPassword;
    private javax.swing.JPanel dragBarPanel;
    private javax.swing.JTextField emailTxt;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel pw;
    private javax.swing.JPasswordField pwTxt;
    private javax.swing.JLabel signInWindow;
    // End of variables declaration//GEN-END:variables
}
