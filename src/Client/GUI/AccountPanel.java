/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.GUI;

import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Nguyen Van Hoang
 */
public class AccountPanel extends javax.swing.JPanel {

    /**
     * Creates new form AccountPanel
     */
    ArrayList<JTextField> listTextField = new ArrayList<>();
    Client client;
    JFrame parent;
    Account account;

    public AccountPanel(JFrame parent, Client client, Account account) {
        initComponents();
        listTextField.add(tfUserName);
        listTextField.add(tfOldPass);
        listTextField.add(tfNewPass);
        listTextField.add(tfRetypeNewPass);
        setPaddingForTextField();
        this.client = client;
        this.parent = parent;
        this.account = account;
        tfUserName.setText(account.getUserName());
    }

    public void setPaddingForTextField() {
        // set padding 10 for text field
        for (JTextField tf : listTextField) {
            tf.setBorder(
                    javax.swing.BorderFactory.createCompoundBorder(
                            getBorder(),
                            javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)
                    )
            );
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
        jPanel2 = new javax.swing.JPanel();
        Username = new javax.swing.JLabel();
        tfUserName = new javax.swing.JTextField();
        Username1 = new javax.swing.JLabel();
        Username3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        tfNewPass = new javax.swing.JPasswordField();
        jSeparator3 = new javax.swing.JSeparator();
        Username4 = new javax.swing.JLabel();
        tfRetypeNewPass = new javax.swing.JPasswordField();
        jSeparator4 = new javax.swing.JSeparator();
        tfOldPass = new javax.swing.JPasswordField();
        btnSave = new javax.swing.JLabel();

        setName("cardAccount"); // NOI18N
        setPreferredSize(new java.awt.Dimension(754, 561));

        jLabel1.setBackground(new java.awt.Color(68, 142, 246));
        jLabel1.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Change Password");
        jLabel1.setOpaque(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(754, 525));

        Username.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        Username.setForeground(new java.awt.Color(68, 142, 246));
        Username.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Username.setText("Username");

        tfUserName.setEditable(false);
        tfUserName.setBackground(new java.awt.Color(204, 204, 204));
        tfUserName.setFont(new java.awt.Font("Roboto Mono", 1, 14)); // NOI18N
        tfUserName.setText("Text");
        tfUserName.setBorder(null);
        tfUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfUserNameActionPerformed(evt);
            }
        });

        Username1.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        Username1.setForeground(new java.awt.Color(68, 142, 246));
        Username1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Username1.setText("Old password");

        Username3.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        Username3.setForeground(new java.awt.Color(68, 142, 246));
        Username3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Username3.setText("New password");

        tfNewPass.setFont(new java.awt.Font("Roboto Mono", 1, 14)); // NOI18N
        tfNewPass.setBorder(null);
        tfNewPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNewPassActionPerformed(evt);
            }
        });

        Username4.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        Username4.setForeground(new java.awt.Color(68, 142, 246));
        Username4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Username4.setText("Retype new password");

        tfRetypeNewPass.setFont(new java.awt.Font("Roboto Mono", 1, 14)); // NOI18N
        tfRetypeNewPass.setBorder(null);
        tfRetypeNewPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfRetypeNewPassActionPerformed(evt);
            }
        });

        tfOldPass.setFont(new java.awt.Font("Roboto Mono", 1, 14)); // NOI18N
        tfOldPass.setBorder(null);
        tfOldPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfOldPassActionPerformed(evt);
            }
        });

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/button_save.png"))); // NOI18N
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSaveMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tfRetypeNewPass, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Username4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tfNewPass, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                            .addComponent(jSeparator2)
                            .addComponent(Username1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Username3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Username, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfUserName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(jSeparator3)))
                    .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                    .addComponent(tfOldPass, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE))
                .addGap(209, 209, 209))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(286, 286, 286)
                .addComponent(btnSave)
                .addContainerGap(320, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(Username, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(tfUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(Username1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(tfOldPass, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Username3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(tfNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE)
                .addComponent(Username4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE)
                .addComponent(tfRetypeNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnSave)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tfUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfUserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfUserNameActionPerformed

    private void tfNewPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNewPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNewPassActionPerformed

    private void tfRetypeNewPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfRetypeNewPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfRetypeNewPassActionPerformed

    private void tfOldPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfOldPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfOldPassActionPerformed
    public boolean isValidData(String userName, String oldPass, String newPass, String retypeNewPass) {
        boolean isValid = true;
        StringBuilder strBuilder = new StringBuilder();
        if (userName.equals("")) {
            isValid = false;
        }
        if (oldPass.equals("")) {
            isValid = false;
        }
        if (newPass.equals("")) {
            isValid = false;
        }
        if (retypeNewPass.equals("")) {
            isValid = false;
        }
        if (!isValid) {
            strBuilder.append("\nCác trường phải nhập đủ không được bỏ trống");
            JOptionPane.showMessageDialog(this, strBuilder.toString());
            return isValid;
        }
        if (newPass.contains(" ")) {
            strBuilder.append("Mật khẩu mới không được chứa khoảng trắng");
            isValid = false;
        }
        if (!isValid) {
            JOptionPane.showMessageDialog(this, strBuilder.toString());
            return isValid;
        }
        if(!Pattern.matches("[a-zA-Z0-9]{0,}", newPass)){
            strBuilder.append("\nPassword không chứa kí tự đặc biệt");
            isValid = false;
        }
        if (!isValid) {
            JOptionPane.showMessageDialog(this, strBuilder.toString());
            return isValid;
        } 
        if (!newPass.equals(retypeNewPass)) {
            strBuilder.append("\nMật khẩu mới và nhập lại mật khẩu không trùng khớp");
            isValid = false;
        }
        if (!isValid) {
            JOptionPane.showMessageDialog(this, strBuilder.toString());
            return isValid;
        }
        if (!oldPass.equals(this.account.getPassword())) {
            strBuilder.append("\nMật khẩu cũ không đúng");
            isValid = false;
        }
        if (!isValid) {
            JOptionPane.showMessageDialog(this, strBuilder.toString());
            return isValid;
        }
        return isValid;
    }
    private void btnSaveMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMousePressed
        // TODO add your handling code here:
        try {
            System.out.println("start login");
            String userName = tfUserName.getText();
            String oldPass = tfOldPass.getText();
            String newPass = tfNewPass.getText();
            String retypeNewPass = tfRetypeNewPass.getText();
            if (!isValidData(userName, oldPass, newPass, retypeNewPass)) {
                return;
            }
            client.send.message = "key:password:" + userName + " " + newPass;
            client.send.flag = true;
            LoadingDialog load = new LoadingDialog(parent, true, LoadingDialog.FLAG_LOGIN);
            String message;
            message = new String(Client.userFlag);
            if (!message.equals("")) {
                Client.userFlag = "";
                if (message.equals("success")) {
                    JOptionPane.showMessageDialog(this, "Update Success");
                }
                if (message.equals("fail")) {
                    System.out.println("\nsave pass  fail - to fail");
                    JOptionPane.showMessageDialog(this, "User or Pass Wrong");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Connection error. Try Again");
            }
            System.out.println("end login");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnSaveMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Username;
    private javax.swing.JLabel Username1;
    private javax.swing.JLabel Username3;
    private javax.swing.JLabel Username4;
    private javax.swing.JLabel btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPasswordField tfNewPass;
    private javax.swing.JPasswordField tfOldPass;
    private javax.swing.JPasswordField tfRetypeNewPass;
    private javax.swing.JTextField tfUserName;
    // End of variables declaration//GEN-END:variables
}
