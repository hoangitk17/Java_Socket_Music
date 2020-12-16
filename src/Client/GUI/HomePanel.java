/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.GUI;

import Server.Song;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Nguyen Van Hoang
 */
public class HomePanel extends javax.swing.JPanel {

    /**
     * Creates new form AccountPanel
     */
    private final String LIST_SONG = "listsong";
    private final String SONG = "song";
    private CardLayout cardLayout;
    Client client;
    Song song = null;
    JFrame parent;
    ArrayList<Song> topSongs;

    public HomePanel(JFrame frame, Client client) {
        initComponents();
        parent = frame;
        cardLayout = (CardLayout) plCards.getLayout();
        cardLayout.show(plCards,SONG);
        lbLyric.setWrapStyleWord(true);
        lbLyric.setLineWrap(true);
        topSongs = Client.topSongs;
        showListSong(topSongs);
        this.client = client;
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
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        plCards = new javax.swing.JPanel();
        cardListSong = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListSong = new javax.swing.JList<Song>();
        cardSong = new javax.swing.JPanel();
        lbSongName = new javax.swing.JLabel();
        lbSingerName = new javax.swing.JLabel();
        btnYoutube = new javax.swing.JButton();
        btnMp3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lbLyric = new javax.swing.JTextArea();

        setName("cardAbout"); // NOI18N

        jLabel1.setBackground(new java.awt.Color(68, 142, 246));
        jLabel1.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Home");
        jLabel1.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Roboto Mono", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Top 20 bài hát được nghe nhiều nhất hiện nay");

        plCards.setLayout(new java.awt.CardLayout());

        cardListSong.setBackground(new java.awt.Color(255, 255, 255));

        ListSong.setFont(new java.awt.Font("Roboto Mono", 0, 13)); // NOI18N
        jScrollPane1.setViewportView(ListSong);

        javax.swing.GroupLayout cardListSongLayout = new javax.swing.GroupLayout(cardListSong);
        cardListSong.setLayout(cardListSongLayout);
        cardListSongLayout.setHorizontalGroup(
            cardListSongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardListSongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                .addContainerGap())
        );
        cardListSongLayout.setVerticalGroup(
            cardListSongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardListSongLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                .addContainerGap())
        );

        plCards.add(cardListSong, "listsong");

        cardSong.setBackground(new java.awt.Color(255, 255, 255));

        lbSongName.setFont(new java.awt.Font("Roboto Mono", 0, 13)); // NOI18N
        lbSongName.setText("Tên bài hát");

        lbSingerName.setFont(new java.awt.Font("Roboto Mono", 0, 13)); // NOI18N
        lbSingerName.setText("Tên ca sĩ");

        btnYoutube.setText("Xem Video");
        btnYoutube.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYoutubeActionPerformed(evt);
            }
        });

        btnMp3.setText("Nghe Mp3");
        btnMp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMp3ActionPerformed(evt);
            }
        });

        lbLyric.setColumns(20);
        lbLyric.setRows(5);
        jScrollPane2.setViewportView(lbLyric);

        javax.swing.GroupLayout cardSongLayout = new javax.swing.GroupLayout(cardSong);
        cardSong.setLayout(cardSongLayout);
        cardSongLayout.setHorizontalGroup(
            cardSongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardSongLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(cardSongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbSingerName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbSongName, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addComponent(btnYoutube)
                .addGap(64, 64, 64)
                .addComponent(btnMp3)
                .addGap(46, 46, 46))
            .addGroup(cardSongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        cardSongLayout.setVerticalGroup(
            cardSongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardSongLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cardSongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardSongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnMp3)
                        .addComponent(btnYoutube))
                    .addComponent(lbSongName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbSingerName)
                .addGap(40, 40, 40)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE))
        );

        plCards.add(cardSong, "song");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(plCards, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(plCards, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnYoutubeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYoutubeActionPerformed
        // TODO add your handling code here:
        //https://sourceforge.net/p/djproject/discussion/671154/thread/e813001e/
        try {
            if (!NativeInterface.isOpen()) {
                NativeInterface.open();
                new Thread(new Runnable() {
                    public void run() {
                        NativeInterface.runEventPump();
                    }
                }).start();

            }
            if (song == null) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn bài hát để xem");
            } else {
                if (song.getIDYoutube() != null) {
//                    if (song.isHasKey()) {
//                    new YoutubeViewerDialog(parent, true, song.getIDYoutube() + ";fs=1").setVisible(true);
//                    } else {
                    new YoutubeViewerDialog(parent, true, "https://www.youtube.com/embed/" + song.getIDYoutube()).setVisible(true);
//                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Bài hát không có video");
                }
            }

            // don't forget to properly close native components
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    NativeInterface.close();
                }
            }));
        } catch (Exception e) {
        }

    }//GEN-LAST:event_btnYoutubeActionPerformed

    private void btnMp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMp3ActionPerformed
        // TODO add your handling code here:
        try {
                   
            if (song == null) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn bài hát để nghe");
            } else {
                if (song.getMp3() != null) {
                    new MP3DialogNew(parent, true, song.getMp3()).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Không có file mp3.");
                }
            }
            
        } catch (Exception e) {

        }
    }//GEN-LAST:event_btnMp3ActionPerformed

    public void showListSong(ArrayList<Song> listsSongs) {
        System.out.println("Show song near correct");
        DefaultListModel<Song> listModel = new DefaultListModel<>();
        for (Song song : listsSongs) {
            listModel.addElement(song);
            ListSong.setModel(listModel);
        }
        ListSong.setCellRenderer(new SongRenderer());
        ListSong.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSong.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {

                    // Double-click detected
                    int index = list.locationToIndex(evt.getPoint());
//                    final Song element = ListSong.getModel().getElementAt(index);
                    SearchSongWithIndex(index);
                    System.out.println("2" + index);
                } else if (evt.getClickCount() == 3) {

                    // Triple-click detected
                    int index = list.locationToIndex(evt.getPoint());
                    System.out.println("3" + index);
                }
            }
        });

    }

    public void showSongOfSinger(Song s) {
        System.out.println("Show song of singer");
        this.song = s;
        cardLayout.show(plCards, SONG);
        lbSongName.setText("Bài hát : " + song.getName());
        lbSingerName.setText("Ca sĩ : " + song.getSinger());
        lbLyric.setText(song.getLyrics());
        System.out.println(s.getLyrics());
    }

    public void SearchSongWithIndex(int index) {
        try {
            // handle when server shutdown => Client to Login
            if (Client.isConnectionReset == 1) {
                JOptionPane.showMessageDialog(this, "Server Connection reset");
                client.send.message = "bye";
                client.send.flag = true;
                Client.isConnectionReset = 0;
                new LogIn();
                parent.dispose();
                return;
            }
            System.out.println("Click-exactly");
            client.send.message = "key:musicE:" + index;
            client.send.flag = true;
            String message;
            LoadingDialog load = new LoadingDialog(parent, true, LoadingDialog.FLAG_SONG);
            while (true) {
                message = new String(Client.songFlag);
                if (!message.equals("")) {
                    switch (message) {
                        case "exactly": {
                            showSongOfSinger(Client.song);
                            System.out.println("Exactly");
                        }
                        break;
                        case "nosong": {
                            JOptionPane.showMessageDialog(this, "Server search error");
                            System.out.println("No song");
                        }
                        break;
                        default: {
                            JOptionPane.showMessageDialog(this, "Server search error");
                        }
                    }
                    Client.song = null;
                    Client.songFlag = "";
                    break;
                }
            }
        } catch (Exception e) {

        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<Song> ListSong;
    private javax.swing.JButton btnMp3;
    private javax.swing.JButton btnYoutube;
    private javax.swing.JPanel cardListSong;
    private javax.swing.JPanel cardSong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea lbLyric;
    private javax.swing.JLabel lbSingerName;
    private javax.swing.JLabel lbSongName;
    private javax.swing.JPanel plCards;
    // End of variables declaration//GEN-END:variables
}
