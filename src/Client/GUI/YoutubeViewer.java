/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.GUI;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 *
 * @author Nguyen Van Hoang
 */
public class YoutubeViewer {

    public static void main(String[] args) {
        NativeInterface.open();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("YouTube Viewer");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.getContentPane().add(getBrowserPanel(), BorderLayout.CENTER);
                frame.setSize(800, 600);
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
        NativeInterface.runEventPump();
        // don't forget to properly close native components
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                NativeInterface.close();
            }
        }));
    }

    public static void showVideoYoutube(String url) {
        NativeInterface.open();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("YouTube Viewer");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.getContentPane().add(getBrowserPanel(url), BorderLayout.CENTER);
                frame.setSize(1000, 800);
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
        NativeInterface.runEventPump();
        // don't forget to properly close native components
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                NativeInterface.close();
            }
        }));
    }

    public static JPanel getBrowserPanel() {
        JPanel webBrowserPanel = new JPanel(new BorderLayout());
        JWebBrowser webBrowser = new JWebBrowser();
        webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
        webBrowser.setBarsVisible(false);
        //webBrowser.navigate("https://aredir.nixcdn.com/NhacCuaTui1004/ThuongQuaVietNam-DanTruong-6718609.mp3?st=Wze3N3h7k9ioD_wdw3Llfg&e=1606358366");

        webBrowser.navigate("https://cf-media.sndcdn.com/7HTR3qKfMtwU.128.mp3?Policy=eyJTdGF0ZW1lbnQiOlt7IlJlc291cmNlIjoiKjovL2NmLW1lZGlhLnNuZGNkbi5jb20vN0hUUjNxS2ZNdHdVLjEyOC5tcDMiLCJDb25kaXRpb24iOnsiRGF0ZUxlc3NUaGFuIjp7IkFXUzpFcG9jaFRpbWUiOjE2MDY4MTY1NzF9fX1dfQ__&Signature=JgN1HSnKwn3TC9w0yYw6uYi7IONJ2tJzIUV0X8fJPXe4S98M7kM917AyX5aD~aYPxPFhHTsETw6PM4Kom3wXyUyBfJMoM95v-Ji6V-d-1sExpzDb56dbW-AiarLayJEn4Qz~s4GHHObUpDfse9~Z6A26Me93tDWfpAFV0a-zeMTuXsiL2TaxF3-8eO-U9o~ERB4tKMgwYqa7b1S3ool8UF38KANFRGqSSTb9OevicNJ0lZT6GWtwDTZcoB5bEXNfbVv7ZHqfCyhRkd78oS7ahj7mMnWpplqC89jRf4QozHsX5FX9VKYw7bXRrb2Pj9avDLvPJB7P4uOYYld3RkJvzg__&Key-Pair-Id=APKAI6TU7MMXM5DG6EPQ");
        return webBrowserPanel;
    }

    public static JPanel getBrowserPanel(String url) {
        JPanel webBrowserPanel = new JPanel(new BorderLayout());
        JWebBrowser webBrowser = new JWebBrowser();
        webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
        webBrowser.setBarsVisible(false);

        webBrowser.navigate(url);

        return webBrowserPanel;
    }
}
