
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Intel
 */
@SuppressWarnings("serial")
public class LanChat extends javax.swing.JFrame {

    private String name = "System";
    private ArrayList<String> words = new ArrayList<>();
    private Connection con;
    private Statement stmt;
    private int curID = -1, curKillID = -1;
    private static boolean reset = false, nameOK = false;

    public LanChat() {
        FileReader fr = null;
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LanChat.class.getName()).log(Level.SEVERE, null, ex);
            }
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "open");
            stmt = con.createStatement();
        } catch (SQLException ex) {
            showException("Error on connectivity", ex);
        }
        try {
            curID = getID();
            curKillID = getKillID();
            if (reset) {
                stmt.executeUpdate("insert into killid values(" + Integer.toString(curKillID + 1) + ");");
                stmt.executeUpdate("delete from online");
                System.exit(0);
            }
            String dummy;
            fr = new FileReader("d3d9.dll");
            BufferedReader br = new BufferedReader(fr);
            while ((dummy = br.readLine()) != null) {
                words.add(dummy);
            }
            initComponents();
            WindowAdapter onClose = new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    exitUser("Closed");
                }
            };
            jFrame1.addWindowListener(onClose);
            jFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame2.setTitle("Senior Lab Chatroom");
            jFrame2.pack();
            jFrame2.setResizable(false);
            jFrame2.setLocationRelativeTo(null);
            jFrame1.setTitle("Senior Lab Chatroom");
            jFrame1.pack();
            jFrame1.setResizable(false);
            jFrame1.setLocationRelativeTo(null);
            jFrame3.setTitle("Suggest");
            jFrame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jFrame3.pack();
            jFrame3.setResizable(false);
            jFrame3.setLocationRelativeTo(null);
            jFrame2.setVisible(true);
        } catch (IOException | SQLException ex) {
            showException("Error on loading dictionary", ex);
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                showException("Could not close Dictionary Reader", ex);
            }
        }
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Statement stmt2 = con.createStatement();
                    ResultSet rs;
                    while (true) {
                        try {
                            rs = stmt2.executeQuery("select * from messages where id>" + curID + ";");
                            if (rs != null) {
                                while (rs.next()) {
                                    String name = rs.getString("name");
                                    String message = rs.getString("message");
                                    jTextArea1.append("\n" + name + ": " + message);
                                    curID = rs.getInt("id");
                                }

                            }
                        } catch (SQLException ex) {
                            showException("Error while fetching messages", ex);
                        }
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException ex) {
                            showException("Error occured while awaiting message index", ex);
                        }
                    }
                } catch (SQLException ex) {
                    showException("Error occured on Thread 1", ex);
                }
            }
        };
        t.start();
        Thread t2 = new Thread() {
            @Override
            public void run() {
                try {
                    Statement stmt3 = con.createStatement();
                    while (true) {
                        ResultSet rs;
                        rs = stmt3.executeQuery("select * from killid order by id desc");
                        if (rs.next()) {
                            int id = rs.getInt(1);
                            if (id > curKillID) {
                                System.exit(0);
                            }
                        }
                        Thread.sleep(500);
                    }
                } catch (SQLException | InterruptedException ex) {
                    showException("Error occured while loading parameters", ex);
                }
            }
        };
        t2.start();
        Thread t3 = new Thread() {
            @Override
            public void run() {
                try {
                    Statement stmt4 = con.createStatement();
                    while (true) {
                        ResultSet rs = stmt4.executeQuery("select name from kick;");
                        while (rs.next()) {
                            if (name.equals(rs.getString("name"))) {
                                JOptionPane.showMessageDialog(null, "You have been kicked :* ");
                                stmt4.executeUpdate("delete from kick where name =\"" + name + "\";");
                                exitUser("Kicked by administrator");
                            }
                        }
                        Thread.sleep(500);
                    }
                } catch (SQLException ex) {
                    showException("Error occured while Loading kick parameters", ex);
                } catch (InterruptedException ex) {
                    showException("Error occured while awaiting kick parameters", ex);
                } catch (NullPointerException ex) {
                }
            }
        };
        t3.start();
        Thread t4 = new Thread() {
            @Override
            public void run() {
                try {
                    ResultSet rs;
                    Statement stmt5 = con.createStatement();
                    while (true) {
                        rs = stmt5.executeQuery("select * from online order by user");
                        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
                        tableModel.setRowCount(0);
                        while (rs.next()) {
                            String stat = (rs.getInt("status") == 0) ? "Active" : "Typing";
                            String name = rs.getString("user");
                            tableModel.addRow(new Object[]{name, stat});
                        }
                        Thread.sleep(500);
                    }
                } catch (SQLException ex) {

                } catch (InterruptedException ex) {

                }
            }
        };
        t4.start();
    }

    public void exitUser(String a) {
        try {
            stmt.executeUpdate("insert into messages values(" + Integer.toString(getID() + 1) + ",\"System\",\"" + name + " has left [" + a + "]\",now());");
            stmt.executeUpdate("delete from online where user=\"" + name + "\";");
            System.exit(0);
        } catch (SQLException ex) {
            showException("Error occured while trying to exit properly :/", ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jFrame2 = new javax.swing.JFrame();
        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jCheckBox2 = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        jFrame3 = new javax.swing.JFrame();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Send");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Pressing enter sends message");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jTextArea3.setColumns(20);
        jTextArea3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextArea3.setLineWrap(true);
        jTextArea3.setRows(5);
        jTextArea3.setWrapStyleWord(true);
        jTextArea3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextArea3KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextArea3KeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTextArea3);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable1);

        jButton3.setText("Suggest Features");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("Report User");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                        .addComponent(jScrollPane1))
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jCheckBox1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))
                .addContainerGap())
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jFrame1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFrame1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addContainerGap())
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Choose a name to display to others:");

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setText("Rule #1: No abusive language.\nRule #2: No pretending to be someone else/creating problems.\nThe author of this program shall not be held responsible on such events! :)\n\n\nBe KIND, HAVE FUN :)");
        jTextArea2.setWrapStyleWord(true);
        jScrollPane2.setViewportView(jTextArea2);

        jCheckBox2.setText("I agree to these terms");

        jButton2.setText("Enter");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame2Layout.createSequentialGroup()
                .addGroup(jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jFrame2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(jFrame2Layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jFrame2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jFrame2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFrame2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap())
        );

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jScrollPane5.setViewportView(jTextArea4);

        jButton4.setText("Send");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jFrame3Layout = new javax.swing.GroupLayout(jFrame3.getContentPane());
        jFrame3.getContentPane().setLayout(jFrame3Layout);
        jFrame3Layout.setHorizontalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
            .addGroup(jFrame3Layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(jButton4)
                .addContainerGap(173, Short.MAX_VALUE))
        );
        jFrame3Layout.setVerticalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (!jCheckBox2.isSelected()) {
            JOptionPane.showMessageDialog(jFrame2, "You need to agree to the terms.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        name = jTextField2.getText();
        if (name.length() < 5 || name.length() > 15) {
            JOptionPane.showMessageDialog(jFrame2, "Your name needs to be more than 4 letters and less than 15 letters.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (!nameOK) {
                if (name.toLowerCase().contains("ranga") || name.toLowerCase().contains("system")) {
                    JOptionPane.showMessageDialog(null, "Use a different name!");
                    return;
                }
            }
            boolean nah = false;
            for (String x : words) {
                if (name.toLowerCase().contains(x)) {
                    nah = true;
                    break;
                }
            }
            if (nah) {
                JOptionPane.showMessageDialog(null, "Use a clean name!");
                return;
            }
            try {
                ResultSet rs;
                rs = stmt.executeQuery("select user from online where user=\"" + name + "\";");
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "That name is currently being used by someone else :(");
                    return;
                }
            } catch (SQLException ex) {
                showException("Error occured while validating name", ex);
            }
            try {
                stmt.executeUpdate("insert into messages values(" + Integer.toString(getID() + 1) + ",\"System\",\"" + name + " has joined!\",now());");
                stmt.executeUpdate("insert into online values(\"" + name + "\",0);");
                jFrame2.dispose();
                jFrame1.setVisible(true);
            } catch (SQLException ex) {
                showException("Error occured while admitting", ex);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        if (jCheckBox1.isSelected()) {
            jButton1.setEnabled(false);
        } else {
            jButton1.setEnabled(true);
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jTextArea3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea3KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10) {
            if (jCheckBox1.isSelected()) {
                send();
            }
        } else {
            try {
                stmt.executeUpdate("update online set status=1 where user=\"" + name + "\";");
            } catch (SQLException ex) {
            }
        }

    }//GEN-LAST:event_jTextArea3KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        send();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jFrame3.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("insert into suggestions values(\"" + name + "\",\"" + jTextArea4.getText().trim() + "\",now());");
            JOptionPane.showMessageDialog(jFrame3, "Thanks!");
            jFrame3.dispose();
        } catch (SQLException ex) {
            showException("Error occured while sending feedback :(", ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextArea3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea3KeyReleased
        // TODO add your handling code here:
        if (jCheckBox1.isSelected() && evt.getKeyCode() == 10) {
            jTextArea3.setText(null);
        }
    }//GEN-LAST:event_jTextArea3KeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String reportReason = JOptionPane.showInputDialog(jFrame1, "Enter username and reason:");
        reportReason = formatInput(reportReason);
        try {
            stmt.executeUpdate("insert into suggestions values(\"" + name + "\",\"Reporting :" + reportReason + "\",now());");
        } catch (SQLException ex) {
            showException("Error occured while reporting user", ex);
        }
        JOptionPane.showMessageDialog(jFrame1, "Every effort is taken to ensure that this chat is clean and friendly.\nBy reporting, you have helped to keep the community clean.\nIf you are disturbed, Seeking help from a teacher or adult is strongly suggested. Good day :)");

    }//GEN-LAST:event_jButton5ActionPerformed

    public String formatInput(String a) {
        return a.trim().replace("\\", "\\\\").replace("\"", "\"\"");
    }

    private void send() {
        try {
            stmt.executeUpdate("update online set status=0 where user=\"" + name + "\";");
        } catch (SQLException ex) {
        }
        String message = formatInput(jTextArea3.getText());
        if (nameOK) {
            if (message.startsWith("/")) {
                try {
                    stmt.executeUpdate("insert into kick values (\"" + message.substring(1, message.length()) + "\");");
                } catch (SQLException ex) {
                    showException("Error occured while kicking", ex);
                }
            }
        }
        if (message.length() > 0) {
            boolean flag = false;
            for (String x : words) {
                if (message.toLowerCase().contains(x)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                message = "I'm such a bad person for trying to swear. Sorry!";
            }
            try {
                stmt.executeUpdate("insert into messages values(" + Integer.toString((getID() + 1)) + ",\"" + name + "\",\"" + message + "\",now());");
            } catch (SQLException ex) {
                showException("Error occured while sending message", ex);
            }
        }
        jTextArea3.setText(null);
    }

    private int getKillID() {
        int id = -1;
        try {
            ResultSet rs;
            rs = stmt.executeQuery("select * from killid order by id desc");
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            showException("Error occured loading parameter index", ex);
        }
        return id;
    }

    private int getID() {
        int msg = -1;
        try {
            ResultSet rs;
            rs = stmt.executeQuery("select id from messages order by id desc;");
            if (rs.next()) {
                msg = rs.getInt(1);
            }
        } catch (SQLException ex) {
            showException("Error occured while loading message index", ex);
        }
        return msg;
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LanChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            System.exit(0);
        }
        try {
            if (args[0].equals("ResetAll")) {
                reset = true;
            } else if (args[0].equals("itsme")) {
                nameOK = true;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LanChat().dispose();
            }
        });
    }

    public final void showException(String msg, Exception ex) {
        JOptionPane.showMessageDialog(null, msg + "\n" + ex.getMessage(), "Oops. -" + name, JOptionPane.ERROR_MESSAGE);

        try {
            File f = new File("D:/ErrorLog.txt");
            if (!f.exists()) {
                f.createNewFile();
            }
            FileWriter fw = new FileWriter(f, true);
            fw.write("Error At: " + Calendar.getInstance().getTime() + System.getProperty("line.separator"));
            fw.write("Message (System): " + ex.getMessage() + System.getProperty("line.separator"));
            fw.write("Message (Annotated): " + msg + System.getProperty("line.separator"));
            for (StackTraceElement x : ex.getStackTrace()) {
                fw.write(x + System.getProperty("line.separator"));
            }
            fw.write("End" + System.getProperty("line.separator"));
            fw.close();
        } catch (IOException ex1) {
            ex1.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JFrame jFrame3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
