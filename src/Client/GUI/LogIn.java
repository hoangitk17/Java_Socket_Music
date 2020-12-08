/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author Nguyen Van Hoang
 */
public class LogIn extends javax.swing.JFrame {

    /**
     * Creates new form LogIN
     */
    public Client client;

    public LogIn() {
        initComponents();
        this.setLocationRelativeTo(null);
        client = new Client();
        addWindowListener(new CustomWindowAdapter(this));
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnSignIn = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        textUserName = new javax.swing.JTextField();
        textPassword = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(68, 142, 246));
        jPanel1.setToolTipText(null);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(68, 142, 246));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Sign In");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 120, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Asset 5@0.5x.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 90, 80));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Path 3.png"))); // NOI18N
        jLabel2.setToolTipText(null);
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -60, 682, -1));

        btnSignIn.setBackground(new java.awt.Color(255, 255, 255));
        btnSignIn.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        btnSignIn.setForeground(new java.awt.Color(68, 142, 246));
        btnSignIn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSignIn.setText("Sign In");
        btnSignIn.setVerifyInputWhenFocusTarget(false);
        btnSignIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSignInMousePressed(evt);
            }
        });
        jPanel1.add(btnSignIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 469, 230, 63));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Rectangle 10.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 450, 330, 100));

        textUserName.setBackground(new java.awt.Color(68, 142, 246));
        textUserName.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        textUserName.setForeground(new java.awt.Color(255, 255, 255));
        textUserName.setBorder(null);
        textUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textUserNameActionPerformed(evt);
            }
        });
        jPanel1.add(textUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, 240, 40));

        textPassword.setBackground(new java.awt.Color(68, 142, 246));
        textPassword.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        textPassword.setForeground(new java.awt.Color(255, 255, 255));
        textPassword.setBorder(null);
        jPanel1.add(textPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 240, 40));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, 240, 10));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 410, 240, 10));

        jLabel5.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Password");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 80, 40));

        jLabel6.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Username");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 80, 40));

        jLabel3.setBackground(new java.awt.Color(68, 142, 246));
        jLabel3.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 233, 129));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("New Account");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 560, 210, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_user_24px.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, 30, 30));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_password_26px.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void textUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textUserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textUserNameActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:

        SignUp signUpForm = new SignUp(client);
        signUpForm.setVisible(true);
        signUpForm.pack();
        signUpForm.setLocationRelativeTo(null);
        signUpForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    public boolean isValidData(String user, String pass) {
        boolean isValid = true;
        StringBuilder strBuilder = new StringBuilder();
        if (user.equals("")) {
            strBuilder.append("Username không được để trống");
            isValid = false;
        }
        if (pass.equals("")) {
            strBuilder.append("\nPassword không được để trống");
            isValid = false;
        }
        if (!isValid) {
            JOptionPane.showMessageDialog(this, strBuilder.toString());
            return isValid;
        }
        if (user.contains(" ")) {
            strBuilder.append("Username không được chứa khoảng trắng");
            isValid = false;
        }
        if (pass.contains(" ")) {
            strBuilder.append("\nPassword không được chứa khoảng trắng");
            isValid = false;
        }
        if (!isValid) {
            JOptionPane.showMessageDialog(this, strBuilder.toString());
            return isValid;
        }
        return isValid;
    }

    private void btnSignInMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSignInMousePressed
        // TODO add your handling code here:
        try {
            System.out.println("start login");
            String userName = textUserName.getText();
            String password = textPassword.getText();
            if (!isValidData(userName, password)) {
                return;
            }
            client.send.message = "key:login:" + userName + " " + password;
            client.send.flag = true;
//            System.out.println("Trước dialog");
//            LoadingDialog load = new LoadingDialog(this, true);
//            System.out.println("Qua dialog");
            String message;
            int i = 0;
            while (true) {
                System.out.print("");
                message = new String(Client.userFlag);
                if (!message.equals("")) {
                    Client.userFlag = "";
                    if (message.equals("success")) {
                        Home home = new Home(client);
                        home.setVisible(true);
                        home.pack();
                        home.setLocationRelativeTo(null);
//                        load.Close();
                        this.dispose();
                        System.out.println("login ok");
                        break;

                    }
                    if (message.equals("fail")) {
                        System.out.println("\nlogin fail - to fail");

                        JOptionPane.showMessageDialog(this, "User or Pass Wrong");
                        break;
                    }

                }
                i++;
            }
            System.out.println("end login");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }//GEN-LAST:event_btnSignInMousePressed

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
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {       
                new LogIn().setVisible(true);
            }
        });
    }

    class CustomWindowAdapter extends WindowAdapter {

        JFrame window = null;

        CustomWindowAdapter(JFrame window) {
            this.window = window;
        }

        // implement windowClosing method
        public void windowClosing(WindowEvent e) {
            // exit the application when window's close button is clicked
            client.send.message = "bye";
            client.send.flag = true;
            JOptionPane.showMessageDialog(this.window, "Close window");
            System.exit(0);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnSignIn;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPasswordField textPassword;
    private javax.swing.JTextField textUserName;
    // End of variables declaration//GEN-END:variables
}
