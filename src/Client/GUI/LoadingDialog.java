/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import javax.swing.Timer;

/**
 *
 * @author Nguyen Van Hoang
 */
public class LoadingDialog extends javax.swing.JDialog {

    /**
     * Creates new form LoadingDialog
     */
    JDialog itSefl = this;
    public static final String FLAG_SONG = "SONG";
    public static final String FLAG_SINGER = "SINGER";
    public static final String FLAG_LOGIN = "LOGIN";
    private String flag;

    public LoadingDialog(java.awt.Frame parent, boolean modal, String flag) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setBounds(parent.getBounds());
        this.setBackground(new Color(0, 0, 0, (float) 0.3));
        System.out.println("trước wait data");
        this.flag = flag;
        MyWorker worker = new MyWorker();
        // Get notification back from the worker...
        worker.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {

                MyWorker worker = (MyWorker) evt.getSource();
                // Progress has been updated
                if (evt.getPropertyName().equalsIgnoreCase("progress")) {

                    // The state of the worker has changed...
                } else if (evt.getPropertyName().equalsIgnoreCase("state")) {

                    if (worker.getState().equals(SwingWorker.StateValue.DONE)) {

                        itSefl.dispose();

                    }

                }

            }

        });

        worker.execute();
        Timer timer = new Timer(20000, new ActionListener() { // 10 sec
            public void actionPerformed(ActionEvent e) {
                itSefl.setVisible(false);
                itSefl.dispose();
            }
        });
        timer.start();
        this.validate();
        this.setVisible(true);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbLoading = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        lbLoading.setBackground(new java.awt.Color(204, 204, 204));
        lbLoading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbLoading.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/loading_100px.gif"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbLoading, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbLoading, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void WaitDataLogin() {
        try {
            while (true) {
                System.out.print("");
                String message = new String(Client.userFlag);
                if (!message.equals("")) {
                    break;

                }
            }
        } catch (Exception e) {

        }
    }

    public void WaitDataSearchSong() {
        try {
            while (true) {
                System.out.print("");
                String message = new String(Client.songFlag);
                if (!message.equals("")) {
                    break;

                }
            }
        } catch (Exception e) {

        }
    }

    public void WaitDataSearchSinger() {
        try {
            while (true) {
                System.out.print("");
                String message = new String(Client.singerFlag);
                if (!message.equals("")) {
                    break;

                }
            }
        } catch (Exception e) {

        }
    }

    protected class MyWorker extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() throws Exception {

            switch (flag) {
                case FLAG_LOGIN:
                    WaitDataLogin();
                    break;
                case FLAG_SONG:
                    WaitDataSearchSong();
                    break;
                case FLAG_SINGER:
                    WaitDataSearchSinger();
                    break;

                default:
                    System.out.println("Default");
            }
            setProgress(99);

            return null;

        }

    }

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
            java.util.logging.Logger.getLogger(LoadingDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoadingDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoadingDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoadingDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoadingDialog dialog = new LoadingDialog(new javax.swing.JFrame(), true, "");
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbLoading;
    // End of variables declaration//GEN-END:variables
}
