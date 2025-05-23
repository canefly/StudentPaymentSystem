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
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Map;




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
        new RoundedCornersMac(this);
        setBackground(new java.awt.Color(0, 0, 0, 0));

        /* helper initialisers */
        initClock();
        loadPrograms();
        loadProgramsToComboBox();
        loadTuitionData();

        /* lock tables as read-only & clear dummy rows */
        programListTable.setDefaultEditor(Object.class, null);
        ((DefaultTableModel) programListTable.getModel()).setRowCount(0);

        jTable1.setDefaultEditor(Object.class, null);
        ((DefaultTableModel) jTable1.getModel()).setRowCount(0);

        /* start-up panel visibility */
        DashPanel.setVisible(true);
        AccountPanel.setVisible(false);
        PGFpanel.setVisible(false);
        SecPanel.setVisible(false);
        SalesPanel.setVisible(false);

        /* active nav */
        activeButton = Dashboard;
        Dashboard.setIcon(dashActive);
    }
    
        Map<String, Integer> programYearMap = new HashMap<>();
        
    public void loadProgramsToComboBox() {
        try (Connection conn = db.connect()) {
            String sql = """
                    SELECT program_name, year_levels
                    FROM programs
                    """;
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                programComboBox.removeAllItems();
                yearSelector.removeAllItems();  // clear any dummy values
                programYearMap.clear();

                while (rs.next()) {
                    String name  = rs.getString("program_name");
                    int years    = rs.getInt("year_levels");

                    programComboBox.addItem(name);       // visible
                    programYearMap.put(name, years);     // logic
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this, "Failed to load programs: " + e.getMessage());
        }
    }

        public void loadPrograms() {
        try {
            Connection conn = db.connect();
            String sql = "SELECT program_name, code, year_levels FROM programs";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            DefaultTableModel model = (DefaultTableModel) programListTable.getModel();
            model.setRowCount(0); // clear table

            while (rs.next()) {
                String name = rs.getString("program_name");
                String code = rs.getString("code");
                int yearLevel = rs.getInt("year_levels");

                model.addRow(new Object[]{name, code, yearLevel});
            }

            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to load programs: " + e.getMessage());
        }
    }

    public void loadTuitionData() {
    try {
        Connection conn = db.connect(); // your db.java connection
        String sql = "SELECT p.program_name, t.year_level, t.amount " +
                     "FROM tuition_matrix t " +
                     "JOIN programs p ON t.program_id = p.program_id";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // clear table

        while (rs.next()) {
            String program = rs.getString("program_name");
            int year = rs.getInt("year_level");
            double amount = rs.getDouble("amount");

            model.addRow(new Object[]{program, year, amount});
        }

        conn.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Failed to load tuition data: " + e.getMessage());
    }
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
        LogOut = new javax.swing.JButton();
        Main = new javax.swing.JPanel();
        topDefault = new javax.swing.JPanel();
        Time = new javax.swing.JLabel();
        roleIdentifier = new javax.swing.JLabel();
        SalesPanel = new javax.swing.JPanel();
        salesPanel = new javax.swing.JLabel();
        SecPanel = new javax.swing.JPanel();
        securityPanel = new javax.swing.JLabel();
        PGFpanel = new javax.swing.JPanel();
        tabProgramFees = new javax.swing.JTabbedPane();
        coursePanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        programListTable = new javax.swing.JTable();
        programLists = new javax.swing.JLabel();
        progName = new javax.swing.JLabel();
        progName1 = new javax.swing.JLabel();
        progName2 = new javax.swing.JLabel();
        codeTextField = new javax.swing.JTextField();
        progNameTextField = new javax.swing.JTextField();
        yearLevels = new javax.swing.JComboBox<>();
        addBtn = new javax.swing.JButton();
        delBtn = new javax.swing.JButton();
        Refresh = new javax.swing.JButton();
        tuitionPanel = new javax.swing.JPanel();
        Refresh1 = new javax.swing.JButton();
        delBtn1 = new javax.swing.JButton();
        addBtn1 = new javax.swing.JButton();
        progName3 = new javax.swing.JLabel();
        label = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        progName4 = new javax.swing.JLabel();
        progName5 = new javax.swing.JLabel();
        yearSelector = new javax.swing.JComboBox<>();
        programComboBox = new javax.swing.JComboBox<>();
        progNameTextField1 = new javax.swing.JTextField();
        booksPanel = new javax.swing.JPanel();
        uniformsPanel = new javax.swing.JPanel();
        otherFeesPanel = new javax.swing.JPanel();
        pgfBG = new javax.swing.JLabel();
        DashPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        dash = new javax.swing.JLabel();
        AccountPanel = new javax.swing.JPanel();
        accPanel = new javax.swing.JTabbedPane();
        enrollStd = new javax.swing.JPanel();
        label1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        deployAcc = new javax.swing.JPanel();
        label2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
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

        LogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/logOut_btn.png"))); // NOI18N
        LogOut.setBorderPainted(false);
        LogOut.setContentAreaFilled(false);
        LogOut.setFocusPainted(false);
        LogOut.setOpaque(false);

        LogOut.setIcon(new ImageIcon(getClass().getResource("/assets/icons/logOut_btn.png")));
        LogOut.setRolloverIcon(new ImageIcon(getClass().getResource("/assets/icons/logOut_hover_btn.png")));
        LogOut.setPressedIcon(new ImageIcon(getClass().getResource("/assets/icons/logOut_onclick_btn.png")));
        LogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogOutActionPerformed(evt);
            }
        });
        dragBarPanel.add(LogOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 30, 80, 30));

        getContentPane().add(dragBarPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 60));

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

        coursePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        programListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Program Name", "Code", "Year Levels"
            }
        ));
        jScrollPane2.setViewportView(programListTable);

        coursePanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 860, 320));

        programLists.setFont(new java.awt.Font("Helvetica", 1, 36)); // NOI18N
        programLists.setText("Program lists");
        coursePanel.add(programLists, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 240, 50));

        progName.setFont(new java.awt.Font("Helvetica", 1, 14)); // NOI18N
        progName.setText("Year Levels:");
        coursePanel.add(progName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 120, 20));

        progName1.setFont(new java.awt.Font("Helvetica", 1, 14)); // NOI18N
        progName1.setText("Program Name:");
        coursePanel.add(progName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 120, 20));

        progName2.setFont(new java.awt.Font("Helvetica", 1, 14)); // NOI18N
        progName2.setText("Code:");
        coursePanel.add(progName2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 120, 20));
        coursePanel.add(codeTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 447, 130, -1));
        coursePanel.add(progNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 410, 130, -1));

        yearLevels.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));
        yearLevels.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearLevelsActionPerformed(evt);
            }
        });
        coursePanel.add(yearLevels, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 485, 90, -1));

        addBtn.setFont(new java.awt.Font("Helvetica", 0, 12)); // NOI18N
        addBtn.setText("Add");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        coursePanel.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 410, 80, -1));

        delBtn.setFont(new java.awt.Font("Helvetica", 0, 12)); // NOI18N
        delBtn.setText("Delete");
        delBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delBtnActionPerformed(evt);
            }
        });
        coursePanel.add(delBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 410, 80, -1));

        Refresh.setFont(new java.awt.Font("Helvetica", 0, 12)); // NOI18N
        Refresh.setText("Refresh");
        Refresh.setToolTipText("incase the tables didnt refresh automatically click me!");
        Refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshActionPerformed(evt);
            }
        });
        coursePanel.add(Refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 410, 80, -1));
        Refresh.getAccessibleContext().setAccessibleDescription("");

        tabProgramFees.addTab("Courses/Programs", coursePanel);

        tuitionPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Refresh1.setFont(new java.awt.Font("Helvetica", 0, 12)); // NOI18N
        Refresh1.setText("Update");
        Refresh1.setToolTipText("incase the tables didnt refresh automatically click me!");
        Refresh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Refresh1ActionPerformed(evt);
            }
        });
        tuitionPanel.add(Refresh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 410, 80, -1));

        delBtn1.setFont(new java.awt.Font("Helvetica", 0, 12)); // NOI18N
        delBtn1.setText("Delete");
        delBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delBtn1ActionPerformed(evt);
            }
        });
        tuitionPanel.add(delBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 410, 80, -1));

        addBtn1.setFont(new java.awt.Font("Helvetica", 0, 12)); // NOI18N
        addBtn1.setText("Add");
        addBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtn1ActionPerformed(evt);
            }
        });
        tuitionPanel.add(addBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 410, 80, -1));

        progName3.setFont(new java.awt.Font("Helvetica", 1, 14)); // NOI18N
        progName3.setText("Amount: ");
        tuitionPanel.add(progName3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 70, 20));

        label.setFont(new java.awt.Font("Helvetica", 1, 36)); // NOI18N
        label.setText("Tuition lists");
        tuitionPanel.add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 240, 50));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Program", "Year", "Amount"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        tuitionPanel.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 860, 320));

        progName4.setFont(new java.awt.Font("Helvetica", 1, 14)); // NOI18N
        progName4.setText("Program Name:");
        tuitionPanel.add(progName4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 120, 20));

        progName5.setFont(new java.awt.Font("Helvetica", 1, 14)); // NOI18N
        progName5.setText("Year:");
        tuitionPanel.add(progName5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 50, 20));

        yearSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        yearSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearSelectorActionPerformed(evt);
            }
        });
        tuitionPanel.add(yearSelector, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 450, 130, -1));

        programComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        programComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programComboBoxActionPerformed(evt);
            }
        });
        tuitionPanel.add(programComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 410, 130, -1));
        tuitionPanel.add(progNameTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 490, 130, -1));

        tabProgramFees.addTab("Tuition", tuitionPanel);

        booksPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        tabProgramFees.addTab("Books", booksPanel);

        uniformsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        tabProgramFees.addTab("Uniforms", uniformsPanel);

        otherFeesPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        tabProgramFees.addTab("Other Fee's", otherFeesPanel);

        PGFpanel.add(tabProgramFees, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, 900, 590));

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

        enrollStd.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label1.setFont(new java.awt.Font("Helvetica", 1, 36)); // NOI18N
        label1.setText("Enroll Students");
        enrollStd.add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 270, 50));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Full Name", "Email", "Program", "Year Level", "Section"
            }
        ));
        jScrollPane4.setViewportView(jTable2);

        enrollStd.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 860, 250));

        jLabel1.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
        jLabel1.setText("Confirm Password:");
        enrollStd.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, 140, 20));

        jLabel2.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
        jLabel2.setText("Full Name:");
        enrollStd.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 100, 20));

        jLabel3.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
        jLabel3.setText("Program:");
        enrollStd.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 100, 20));

        jLabel4.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
        jLabel4.setText("Year Level:");
        enrollStd.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 100, 20));

        jLabel5.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
        jLabel5.setText("Section:");
        enrollStd.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 100, 20));

        jLabel6.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
        jLabel6.setText("Email:");
        enrollStd.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 100, 20));

        jLabel7.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
        jLabel7.setText("Password:");
        enrollStd.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 100, 20));

        jButton1.setText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        enrollStd.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 330, 70, -1));
        enrollStd.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 510, 190, -1));

        jButton3.setText("Enroll");
        enrollStd.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 330, -1, -1));

        jButton4.setText("Drop Student");
        enrollStd.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 330, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        enrollStd.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 390, 190, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        enrollStd.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 360, 190, -1));
        enrollStd.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 330, 190, -1));
        enrollStd.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 420, 190, -1));
        enrollStd.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 450, 190, -1));
        enrollStd.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 480, 190, -1));

        accPanel.addTab("Enroll Student", enrollStd);

        deployAcc.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label2.setFont(new java.awt.Font("Helvetica", 1, 36)); // NOI18N
        label2.setText("Deploy Cashier Accounts");
        deployAcc.add(label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 440, 50));

        jLabel8.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
        jLabel8.setText("Confirm Password:");
        deployAcc.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 150, 20));

        jLabel9.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
        jLabel9.setText("Email:");
        deployAcc.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 100, 20));

        jLabel10.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
        jLabel10.setText("Password:");
        deployAcc.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 100, 20));
        deployAcc.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 200, -1));
        deployAcc.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 200, -1));
        deployAcc.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 200, -1));

        jButton2.setText("Add Account");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        deployAcc.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 240, -1, -1));

        accPanel.addTab("Deploy Accounts", deployAcc);

        AccountPanel.add(accPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, 900, 590));

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

        // ✅ FIRST update the memory
        activeButton = button;

        // 🔁 THEN reset the others
        resetNavIcons();

        // ✅ Finally, set the active icon
        button.setIcon(activeIcon);
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

    private void LogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogOutActionPerformed
    LogOut.addActionListener(e -> {
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Do you really want to log out?",
            "Confirm Logout",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            dispose(); // Close current window
            new LoginPage().setVisible(true); // Reopen Login
        }
    });
    }//GEN-LAST:event_LogOutActionPerformed

    private void delBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delBtnActionPerformed
                int selectedRow = programListTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            return;
        }

        // Get program name from selected row (assuming it's the first column)
        String programName = programListTable.getValueAt(selectedRow, 0).toString();

        int confirm = JOptionPane.showConfirmDialog(this, "Delete program \"" + programName + "\"?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Connection conn = db.connect();
                String sql = "DELETE FROM programs WHERE program_name = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, programName);
                int affectedRows = stmt.executeUpdate();

                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(this, "Program deleted.");
                    loadPrograms(); // refresh table
                } else {
                    JOptionPane.showMessageDialog(this, "Program not found or already deleted.");
                }

                conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Failed to delete program: " + e.getMessage());
            }
        }


    }//GEN-LAST:event_delBtnActionPerformed

    private void RefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshActionPerformed
        loadPrograms();
    }//GEN-LAST:event_RefreshActionPerformed

    private void yearLevelsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearLevelsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yearLevelsActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        String name = progNameTextField.getText().trim();
String code = codeTextField.getText().trim();
String yearStr = yearLevels.getSelectedItem().toString();

if (name.isEmpty() || code.isEmpty() || yearStr.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Please fill in all fields.");
    return;
}

try {
    int yearLevel = Integer.parseInt(yearStr);
    Connection conn = db.connect();
    String sql = "INSERT INTO programs (program_name, code, year_levels) VALUES (?, ?, ?)";
    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setString(1, name);
    stmt.setString(2, code);
    stmt.setInt(3, yearLevel);
    stmt.executeUpdate();

    JOptionPane.showMessageDialog(this, "Program added successfully!");

    // Clear fields
    progNameTextField.setText("");
    codeTextField.setText("");
    yearLevels.setSelectedIndex(0);

    // Refresh table
    loadPrograms();

    conn.close();
} catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Failed to add program: " + e.getMessage());
}

    }//GEN-LAST:event_addBtnActionPerformed

    private void Refresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Refresh1ActionPerformed
        loadPrograms();
        loadProgramsToComboBox();
        loadTuitionData();
    }//GEN-LAST:event_Refresh1ActionPerformed

    private void delBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delBtn1ActionPerformed
                int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a tuition entry to delete.");
            return;
        }

        String programName = jTable1.getValueAt(selectedRow, 0).toString();
        int yearLevel = Integer.parseInt(jTable1.getValueAt(selectedRow, 1).toString());

        int confirm = JOptionPane.showConfirmDialog(this,
                "Delete tuition for \"" + programName + "\" (Year " + yearLevel + ")?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Connection conn = db.connect();

                // Find program_id
                String getIdSql = "SELECT program_id FROM programs WHERE program_name = ?";
                PreparedStatement getIdStmt = conn.prepareStatement(getIdSql);
                getIdStmt.setString(1, programName);
                ResultSet idRs = getIdStmt.executeQuery();

                if (idRs.next()) {
                    int programId = idRs.getInt("program_id");

                    // Delete matching record
                    String deleteSql = "DELETE FROM tuition_matrix WHERE program_id = ? AND year_level = ?";
                    PreparedStatement delStmt = conn.prepareStatement(deleteSql);
                    delStmt.setInt(1, programId);
                    delStmt.setInt(2, yearLevel);

                    int rows = delStmt.executeUpdate();

                    if (rows > 0) {
                        JOptionPane.showMessageDialog(this, "Tuition deleted!");
                        loadTuitionData();
                    } else {
                        JOptionPane.showMessageDialog(this, "No matching record found.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Program not found.");
                }

                conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Failed to delete: " + e.getMessage());
            }
        }

    }//GEN-LAST:event_delBtn1ActionPerformed

    private void addBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtn1ActionPerformed
       String selectedProgram = (String) programComboBox.getSelectedItem();
String selectedYear = (String) yearSelector.getSelectedItem();
String amountStr = progNameTextField1.getText().trim();

if (selectedProgram == null || selectedYear == null || amountStr.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Please complete all fields.");
    return;
}

try {
    int yearLevel = Integer.parseInt(selectedYear);
    double amount = Double.parseDouble(amountStr);

    // Get program_id from program name
    Connection conn = db.connect();
    String getProgramIdSql = "SELECT program_id FROM programs WHERE program_name = ?";
    PreparedStatement getIdStmt = conn.prepareStatement(getProgramIdSql);
    getIdStmt.setString(1, selectedProgram);
    ResultSet idRs = getIdStmt.executeQuery();

    if (idRs.next()) {
        int programId = idRs.getInt("program_id");

        // Insert into tuition_matrix
        String insertSql = "INSERT INTO tuition_matrix (program_id, year_level, amount) VALUES (?, ?, ?)";
        PreparedStatement insertStmt = conn.prepareStatement(insertSql);
        insertStmt.setInt(1, programId);
        insertStmt.setInt(2, yearLevel);
        insertStmt.setDouble(3, amount);
        insertStmt.executeUpdate();

        JOptionPane.showMessageDialog(this, "Tuition added!");

        // Clear form
        yearSelector.setSelectedIndex(0);
        progNameTextField1.setText("");

        // Refresh table
        loadTuitionData();
    } else {
        JOptionPane.showMessageDialog(this, "Program not found.");
    }

    conn.close();

} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this, "Invalid amount or year level.");
} catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Failed to add tuition: " + e.getMessage());
}

    }//GEN-LAST:event_addBtn1ActionPerformed

    private void programComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programComboBoxActionPerformed
            {
        String selected = (String) programComboBox.getSelectedItem();
        if (selected == null) return;

        Integer maxYear = programYearMap.get(selected);
        if (maxYear == null) return;

        yearSelector.removeAllItems();
        for (int y = 1; y <= maxYear; y++) {
            yearSelector.addItem(String.valueOf(y));
        }
    }

    }//GEN-LAST:event_programComboBoxActionPerformed

    private void yearSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearSelectorActionPerformed
       
    }//GEN-LAST:event_yearSelectorActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JButton LogOut;
    private javax.swing.JPanel Main;
    private javax.swing.JButton PGFbtn;
    private javax.swing.JPanel PGFpanel;
    private javax.swing.JLabel RawPanel;
    private javax.swing.JButton Refresh;
    private javax.swing.JButton Refresh1;
    private javax.swing.JButton Sales;
    private javax.swing.JPanel SalesPanel;
    private javax.swing.JPanel SecPanel;
    private javax.swing.JButton Security;
    private javax.swing.JLabel Time;
    private javax.swing.JTabbedPane accPanel;
    private javax.swing.JButton addBtn;
    private javax.swing.JButton addBtn1;
    private javax.swing.JLabel bg;
    private javax.swing.JPanel booksPanel;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnMin;
    private javax.swing.JTextField codeTextField;
    private javax.swing.JPanel coursePanel;
    private javax.swing.JLabel dash;
    private javax.swing.JButton delBtn;
    private javax.swing.JButton delBtn1;
    private javax.swing.JPanel deployAcc;
    private javax.swing.JPanel dragBarPanel;
    private javax.swing.JPanel enrollStd;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JLabel label;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JPanel otherFeesPanel;
    private javax.swing.JLabel pgfBG;
    private javax.swing.JLabel progName;
    private javax.swing.JLabel progName1;
    private javax.swing.JLabel progName2;
    private javax.swing.JLabel progName3;
    private javax.swing.JLabel progName4;
    private javax.swing.JLabel progName5;
    private javax.swing.JTextField progNameTextField;
    private javax.swing.JTextField progNameTextField1;
    private javax.swing.JComboBox<String> programComboBox;
    private javax.swing.JTable programListTable;
    private javax.swing.JLabel programLists;
    private javax.swing.JLabel roleIdentifier;
    private javax.swing.JLabel salesPanel;
    private javax.swing.JLabel securityPanel;
    private javax.swing.JPanel sideNav;
    private javax.swing.JTabbedPane tabProgramFees;
    private javax.swing.JPanel topDefault;
    private javax.swing.JPanel tuitionPanel;
    private javax.swing.JPanel uniformsPanel;
    private javax.swing.JComboBox<String> yearLevels;
    private javax.swing.JComboBox<String> yearSelector;
    // End of variables declaration//GEN-END:variables
}
