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
        cardLayout.show(plCards,LIST_SONG);
        lbLyric.setWrapStyleWord(true);
        lbLyric.setLineWrap(true);
        topSongs = Client.topSongs;
        showListSong(topSongs);
        this.client = client;
        btnBack.setVisible(false);
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
        btnBack = new javax.swing.JLabel();

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                .addContainerGap())
        );
        cardListSongLayout.setVerticalGroup(
            cardListSongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardListSongLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                .addContainerGap())
        );

        plCards.add(cardListSong, "listsong");

        cardSong.setBackground(new java.awt.Color(255, 255, 255));

        lbSongName.setFont(new java.awt.Font("Roboto Mono", 0, 13)); // NOI18N
        lbSongName.setText("Tên bài hát");

        lbSingerName.setFont(new java.awt.Font("Roboto Mono", 0, 13)); // NOI18N
        lbSingerName.setText("Tên ca sĩ");

        btnYoutube.setFont(new java.awt.Font("Roboto Mono", 0, 13)); // NOI18N
        btnYoutube.setText("Xem Video");
        btnYoutube.setContentAreaFilled(false);
        btnYoutube.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnYoutube.setOpaque(true);
        btnYoutube.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYoutubeActionPerformed(evt);
            }
        });

        btnMp3.setFont(new java.awt.Font("Roboto Mono", 0, 13)); // NOI18N
        btnMp3.setText("Nghe Mp3");
        btnMp3.setContentAreaFilled(false);
        btnMp3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMp3.setOpaque(true);
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
                .addContainerGap()
                .addGroup(cardSongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                    .addGroup(cardSongLayout.createSequentialGroup()
                        .addGroup(cardSongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cardSongLayout.createSequentialGroup()
                                .addComponent(btnYoutube)
                                .addGap(29, 29, 29)
                                .addComponent(btnMp3))
                            .addComponent(lbSongName, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbSingerName, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        cardSongLayout.setVerticalGroup(
            cardSongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardSongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbSongName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbSingerName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cardSongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnYoutube)
                    .addComponent(btnMp3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
        );

        plCards.add(cardSong, "song");

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_back_arrow_40px.png"))); // NOI18N
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnBackMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(plCards, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(btnBack)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void btnBackMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMousePressed
        // TODO add your handling code here:
        cardLayout.show(plCards, LIST_SONG);
        lbSongName.setText("Tên bài hát : ");
        lbSingerName.setText("Tên ca sĩ : ");
        lbLyric.setText("");
        btnBack.setVisible(false);
    }//GEN-LAST:event_btnBackMousePressed

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
        System.out.println(s.getName());
        System.out.println("Show song of singer");
        this.song = s;
        cardLayout.show(plCards, SONG);
        lbSongName.setText("Bài hát : " + song.getName());
        lbSingerName.setText("Ca sĩ : " + song.getSinger());
        lbLyric.setText(song.getLyrics());
        System.out.println(s.getLyrics());
        btnBack.setVisible(true);
    }

    public void SearchSongWithIndex(int index) {
        try {
            showSongOfSinger(topSongs.get(index));
        } catch (Exception e) {

        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<Song> ListSong;
    private javax.swing.JLabel btnBack;
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
