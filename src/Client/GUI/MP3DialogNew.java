/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.GUI;

import com.sun.javafx.application.PlatformImpl;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * SwingFXWebView
 */
public class MP3DialogNew extends JDialog {

    private Stage stage;
    private WebView browser;
    private JFXPanel jfxPanel;
    private JButton swingButton;
    private WebEngine webEngine;
    JDialog itself = this;
    String url = "";

    public MP3DialogNew(java.awt.Frame parent, boolean modal, String url) {
        super(parent, modal);
        try {            
            initComponents();
            setLocationRelativeTo(null);
            setMinimumSize(new Dimension(440, 280));
            addWindowListener(new WindowListener() {

                public void windowActivated(WindowEvent arg0) {
                    // Do nothing
                }

                public void windowClosed(WindowEvent arg0) {
                    System.out.println("closed");
                }

                public void windowClosing(WindowEvent arg0) {
                    // Do nothing
                    System.out.println("close - close - close");
                    Platform.runLater(new Runnable() {

                        @Override
                        public void run() {
                            webEngine.load(null);
                            setVisible(false);
                        }

                    });

                }

                public void windowDeactivated(WindowEvent arg0) {
                    // Do nothing
                }

                public void windowDeiconified(WindowEvent arg0) {
                    // Do nothing
                }

                public void windowIconified(WindowEvent arg0) {
                    // Do nothing
                }

                public void windowOpened(WindowEvent arg0) {
                    // Do nothing
                }

            });
            this.url = url;
            setVisible(true);
        } catch (Exception ex) {

        }

    }

    private void initComponents() {

        try {
            jfxPanel = new JFXPanel();
            createScene();

            setLayout(new BorderLayout());
            add(jfxPanel, BorderLayout.CENTER);

            swingButton = new JButton();
//            swingButton.addActionListener(new ActionListener() {
//
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    Platform.runLater(new Runnable() {
//
//                        @Override
//                        public void run() {
//                            webEngine.reload();
//                        }
//                    });
//                }
//            });
            swingButton.setText("Reload");

            add(swingButton, BorderLayout.SOUTH);
        } catch (Exception ex) {

        }
    }

    /**
     * createScene
     *
     * Note: Key is that Scene needs to be created and run on "FX user thread"
     * NOT on the AWT-EventQueue Thread
     *
     */
    private void createScene() {
        try {
            PlatformImpl.startup(new Runnable() {
                @Override
                public void run() {

                    stage = new Stage();

                    stage.setTitle("Hello Java FX");
                    stage.setResizable(true);

                    Group root = new Group();
                    Scene scene = new Scene(root, 80, 20);
                    stage.setScene(scene);

                    // Set up the embedded browser:
                    browser = new WebView();
                    webEngine = browser.getEngine();
                    String htmlText2 = "<audio controls>\n"
                            + "    <source src=\"" + url + "\">\n"
                            + "</audio>";
                    webEngine.loadContent(htmlText2);

                    ObservableList<Node> children = root.getChildren();
                    children.add(browser);

                    jfxPanel.setScene(scene);
                }
            });
        } catch (Exception ex) {

        }
    }
}
