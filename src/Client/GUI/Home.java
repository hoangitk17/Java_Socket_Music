/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.GUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Nguyen Van Hoang
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    Client client;
    ArrayList<JPanel> menuItems = new ArrayList<>();
    ArrayList<JPanel> menuItemLines = new ArrayList<>();
    ArrayList<JLabel> menuItemLabels = new ArrayList<>();
    ArrayList<JLabel> menuItemIcons = new ArrayList<>();

    AboutPanel cardAbout = new AboutPanel();
    AccountPanel cardAccount;
    SingerPanel cardSinger;
    SongPanel cardSong = new SongPanel(this);
    HomePanel cardHome = new HomePanel();
    CardLayout cardLayout;
    private final String ABOUT = "about";
    private final String ACCOUNT = "account";
    private final String HOME = "home";
    private final String SONG = "song";
    private final String SINGER = "singer";
    private String activedItem = "";
    Color tranparencyColor = new Color(0, 0, 0, 0);
    Color hoverColor = new Color(247, 78, 105);
    ImageIcon whiteHomeIcon = new ImageIcon(getClass().getResource("/Icon/icons8_home_20px.png"));
    ImageIcon pinkHomeIcon = new ImageIcon(getClass().getResource("/Icon/icons8_home_20px_1.png"));
    ImageIcon whiteSongIcon = new ImageIcon(getClass().getResource("/Icon/icons8_search_in_list_20px.png"));
    ImageIcon pinkSongIcon = new ImageIcon(getClass().getResource("/Icon/icons8_search_in_list_20px_1.png"));
    ImageIcon whiteSingerIcon = new ImageIcon(getClass().getResource("/Icon/icons8_search_23px_1.png"));
    ImageIcon pinkSingerIcon = new ImageIcon(getClass().getResource("/Icon/icons8_search_23px_2.png"));
    ImageIcon whiteAccountIcon = new ImageIcon(getClass().getResource("/Icon/icons8_user_20px.png"));
    ImageIcon pinkAccountIcon = new ImageIcon(getClass().getResource("/Icon/icons8_user_20px_1.png"));
    ImageIcon whiteAboutIcon = new ImageIcon(getClass().getResource("/Icon/icons8_about_20px.png"));
    ImageIcon pinkAboutIcon = new ImageIcon(getClass().getResource("/Icon/icons8_about_20px_1.png"));
    HashMap<String, ImageIcon> hashWhiteIcon = new HashMap<String, ImageIcon>();
    HashMap<String, ImageIcon> hashPinkIcon = new HashMap<String, ImageIcon>();
    Account account;

    public Home() {
        initComponents();
        this.setLocationRelativeTo(null);
        menuItems.add(plAbout);
        menuItems.add(plHome);
        menuItems.add(plSinger);
        menuItems.add(plSong);
        menuItems.add(plAccount);

        menuItemLines.add(lineAbout);
        menuItemLines.add(lineHome);
        menuItemLines.add(lineSinger);
        menuItemLines.add(lineSong);
        menuItemLines.add(lineAccount);

        menuItemLabels.add(lbHome);
        menuItemLabels.add(lbAbout);
        menuItemLabels.add(lbAccount);
        menuItemLabels.add(lbSinger);
        menuItemLabels.add(lbSong);

        menuItemIcons.add(lbHomeIcon);
        menuItemIcons.add(lbAboutIcon);
        menuItemIcons.add(lbAccountIcon);
        menuItemIcons.add(lbSingerIcon);
        menuItemIcons.add(lbSongIcon);

        hashWhiteIcon.put(HOME, whiteHomeIcon);
        hashWhiteIcon.put(ABOUT, whiteAboutIcon);
        hashWhiteIcon.put(SONG, whiteSongIcon);
        hashWhiteIcon.put(SINGER, whiteSingerIcon);
        hashWhiteIcon.put(ACCOUNT, whiteAccountIcon);
        hashPinkIcon.put(HOME, pinkHomeIcon);
        hashPinkIcon.put(ABOUT, pinkAboutIcon);
        hashPinkIcon.put(SONG, pinkSongIcon);
        hashPinkIcon.put(SINGER, pinkSingerIcon);
        hashPinkIcon.put(ACCOUNT, pinkAccountIcon);

        // add cards panel to cardlayout
        cardLayout = (CardLayout) plCards.getLayout();
        plCards.add(cardAbout, ABOUT);
        plCards.add(cardAccount, ACCOUNT);
        plCards.add(cardSinger, SINGER);
        plCards.add(cardSong, SONG);
        plCards.add(cardHome, HOME);

        // set bgcolor for item
        for (JPanel panel : menuItems) {
            panel.setBackground(tranparencyColor);

        }
        for (JPanel panel : menuItemLines) {
            panel.setBackground(tranparencyColor);
        }

    }

    public Home(Client client, Account account) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.client = client;
        this.account = account;
        menuItems.add(plAbout);
        menuItems.add(plHome);
        menuItems.add(plSinger);
        menuItems.add(plSong);
        menuItems.add(plAccount);

        menuItemLines.add(lineAbout);
        menuItemLines.add(lineHome);
        menuItemLines.add(lineSinger);
        menuItemLines.add(lineSong);
        menuItemLines.add(lineAccount);

        menuItemLabels.add(lbHome);
        menuItemLabels.add(lbAbout);
        menuItemLabels.add(lbAccount);
        menuItemLabels.add(lbSinger);
        menuItemLabels.add(lbSong);

        menuItemIcons.add(lbHomeIcon);
        menuItemIcons.add(lbAboutIcon);
        menuItemIcons.add(lbAccountIcon);
        menuItemIcons.add(lbSingerIcon);
        menuItemIcons.add(lbSongIcon);

        hashWhiteIcon.put(HOME, whiteHomeIcon);
        hashWhiteIcon.put(ABOUT, whiteAboutIcon);
        hashWhiteIcon.put(SONG, whiteSongIcon);
        hashWhiteIcon.put(SINGER, whiteSingerIcon);
        hashWhiteIcon.put(ACCOUNT, whiteAccountIcon);
        hashPinkIcon.put(HOME, pinkHomeIcon);
        hashPinkIcon.put(ABOUT, pinkAboutIcon);
        hashPinkIcon.put(SONG, pinkSongIcon);
        hashPinkIcon.put(SINGER, pinkSingerIcon);
        hashPinkIcon.put(ACCOUNT, pinkAccountIcon);

        cardAccount = new AccountPanel(this, this.client, this.account);
        cardSinger = new SingerPanel(this, this.client);
        // add cards panel to cardlayout
        cardLayout = (CardLayout) plCards.getLayout();
        plCards.add(cardAbout, ABOUT);
        plCards.add(cardAccount, ACCOUNT);
        plCards.add(cardSinger, SINGER);
        plCards.add(cardSong, SONG);
        plCards.add(cardHome, HOME);

        // set bgcolor for item
        for (JPanel panel : menuItems) {
            panel.setBackground(tranparencyColor);

        }
        for (JPanel panel : menuItemLines) {
            panel.setBackground(tranparencyColor);
        }
        cardSong.setClient(client);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgPanel = new javax.swing.JPanel();
        slidePanel = new javax.swing.JPanel();
        plHome = new javax.swing.JPanel();
        lbHomeIcon = new javax.swing.JLabel();
        lbHome = new javax.swing.JLabel();
        lineHome = new javax.swing.JPanel();
        plSong = new javax.swing.JPanel();
        lbSongIcon = new javax.swing.JLabel();
        lbSong = new javax.swing.JLabel();
        lineSong = new javax.swing.JPanel();
        plSinger = new javax.swing.JPanel();
        lbSingerIcon = new javax.swing.JLabel();
        lbSinger = new javax.swing.JLabel();
        lineSinger = new javax.swing.JPanel();
        plAccount = new javax.swing.JPanel();
        lbAccountIcon = new javax.swing.JLabel();
        lbAccount = new javax.swing.JLabel();
        lineAccount = new javax.swing.JPanel();
        plAbout = new javax.swing.JPanel();
        lbAboutIcon = new javax.swing.JLabel();
        lbAbout = new javax.swing.JLabel();
        lineAbout = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        plCards = new javax.swing.JPanel();
        btnClose = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);

        bgPanel.setBackground(new java.awt.Color(255, 255, 255));
        bgPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bgPanel.setPreferredSize(new java.awt.Dimension(940, 550));
        bgPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        slidePanel.setBackground(new java.awt.Color(68, 142, 246));
        slidePanel.setAutoscrolls(true);
        slidePanel.setLayout(null);

        plHome.setBackground(new java.awt.Color(85, 85, 188));
        plHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        plHome.setName("home"); // NOI18N
        plHome.setOpaque(false);
        plHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                changeLineColor(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                resetColorLine(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                chooseMenuItem(evt);
            }
        });
        plHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbHomeIcon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbHomeIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_home_20px.png"))); // NOI18N
        lbHomeIcon.setName("home"); // NOI18N
        plHome.add(lbHomeIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 0, -1, 50));

        lbHome.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        lbHome.setForeground(new java.awt.Color(255, 255, 255));
        lbHome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbHome.setText("Home");
        lbHome.setAutoscrolls(true);
        lbHome.setName("home"); // NOI18N
        lbHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                changeLineColor(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                resetColorLine(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                chooseMenuItem(evt);
            }
        });
        plHome.add(lbHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 160, 50));

        lineHome.setBackground(new java.awt.Color(247, 78, 105));
        lineHome.setName("home"); // NOI18N
        lineHome.setOpaque(false);
        lineHome.setPreferredSize(new java.awt.Dimension(1, 20));

        javax.swing.GroupLayout lineHomeLayout = new javax.swing.GroupLayout(lineHome);
        lineHome.setLayout(lineHomeLayout);
        lineHomeLayout.setHorizontalGroup(
            lineHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        lineHomeLayout.setVerticalGroup(
            lineHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        plHome.add(lineHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 15, 4, 20));

        slidePanel.add(plHome);
        plHome.setBounds(0, 150, 210, 50);

        plSong.setBackground(new java.awt.Color(85, 85, 188));
        plSong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        plSong.setName("song"); // NOI18N
        plSong.setOpaque(false);
        plSong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                changeLineColor(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                resetColorLine(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                chooseMenuItem(evt);
            }
        });
        plSong.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbSongIcon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbSongIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_search_in_list_20px.png"))); // NOI18N
        lbSongIcon.setName("song"); // NOI18N
        plSong.add(lbSongIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 0, -1, 50));

        lbSong.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        lbSong.setForeground(new java.awt.Color(255, 255, 255));
        lbSong.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbSong.setText("Search Song");
        lbSong.setName("song"); // NOI18N
        plSong.add(lbSong, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 160, 50));

        lineSong.setBackground(new java.awt.Color(247, 78, 105));
        lineSong.setName("song"); // NOI18N
        lineSong.setOpaque(false);
        lineSong.setPreferredSize(new java.awt.Dimension(4, 20));

        javax.swing.GroupLayout lineSongLayout = new javax.swing.GroupLayout(lineSong);
        lineSong.setLayout(lineSongLayout);
        lineSongLayout.setHorizontalGroup(
            lineSongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );
        lineSongLayout.setVerticalGroup(
            lineSongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        plSong.add(lineSong, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 15, -1, -1));

        slidePanel.add(plSong);
        plSong.setBounds(0, 200, 210, 50);

        plSinger.setBackground(new java.awt.Color(85, 85, 188));
        plSinger.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        plSinger.setName("singer"); // NOI18N
        plSinger.setOpaque(false);
        plSinger.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                changeLineColor(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                resetColorLine(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                chooseMenuItem(evt);
            }
        });
        plSinger.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbSingerIcon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbSingerIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_search_23px_1.png"))); // NOI18N
        lbSingerIcon.setName("singer"); // NOI18N
        plSinger.add(lbSingerIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, -1, 50));

        lbSinger.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        lbSinger.setForeground(new java.awt.Color(255, 255, 255));
        lbSinger.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbSinger.setText("Search Singer");
        lbSinger.setName("singer"); // NOI18N
        plSinger.add(lbSinger, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 160, 50));

        lineSinger.setBackground(new java.awt.Color(247, 78, 105));
        lineSinger.setName("singer"); // NOI18N
        lineSinger.setOpaque(false);
        lineSinger.setPreferredSize(new java.awt.Dimension(4, 20));

        javax.swing.GroupLayout lineSingerLayout = new javax.swing.GroupLayout(lineSinger);
        lineSinger.setLayout(lineSingerLayout);
        lineSingerLayout.setHorizontalGroup(
            lineSingerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );
        lineSingerLayout.setVerticalGroup(
            lineSingerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        plSinger.add(lineSinger, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 15, -1, -1));

        slidePanel.add(plSinger);
        plSinger.setBounds(0, 250, 210, 50);

        plAccount.setBackground(new java.awt.Color(85, 85, 188));
        plAccount.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        plAccount.setName("account"); // NOI18N
        plAccount.setOpaque(false);
        plAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                changeLineColor(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                resetColorLine(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                chooseMenuItem(evt);
            }
        });
        plAccount.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbAccountIcon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbAccountIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_user_20px.png"))); // NOI18N
        lbAccountIcon.setName("account"); // NOI18N
        plAccount.add(lbAccountIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 0, -1, 50));

        lbAccount.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        lbAccount.setForeground(new java.awt.Color(255, 255, 255));
        lbAccount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbAccount.setText("Account");
        lbAccount.setAlignmentX(0.5F);
        lbAccount.setName("account"); // NOI18N
        plAccount.add(lbAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 1, 160, 50));

        lineAccount.setBackground(new java.awt.Color(247, 78, 105));
        lineAccount.setName("account"); // NOI18N
        lineAccount.setOpaque(false);
        lineAccount.setPreferredSize(new java.awt.Dimension(4, 20));

        javax.swing.GroupLayout lineAccountLayout = new javax.swing.GroupLayout(lineAccount);
        lineAccount.setLayout(lineAccountLayout);
        lineAccountLayout.setHorizontalGroup(
            lineAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );
        lineAccountLayout.setVerticalGroup(
            lineAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        plAccount.add(lineAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 15, -1, -1));

        slidePanel.add(plAccount);
        plAccount.setBounds(0, 300, 210, 50);

        plAbout.setBackground(new java.awt.Color(85, 85, 188));
        plAbout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        plAbout.setName("about"); // NOI18N
        plAbout.setOpaque(false);
        plAbout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                changeLineColor(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                resetColorLine(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                chooseMenuItem(evt);
            }
        });
        plAbout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbAboutIcon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbAboutIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_about_20px.png"))); // NOI18N
        lbAboutIcon.setName("about"); // NOI18N
        plAbout.add(lbAboutIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 0, -1, 50));

        lbAbout.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        lbAbout.setForeground(new java.awt.Color(255, 255, 255));
        lbAbout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbAbout.setText("About Us");
        lbAbout.setName("about"); // NOI18N
        plAbout.add(lbAbout, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 1, 160, 50));

        lineAbout.setBackground(new java.awt.Color(247, 78, 105));
        lineAbout.setName("about"); // NOI18N
        lineAbout.setOpaque(false);
        lineAbout.setPreferredSize(new java.awt.Dimension(4, 20));

        javax.swing.GroupLayout lineAboutLayout = new javax.swing.GroupLayout(lineAbout);
        lineAbout.setLayout(lineAboutLayout);
        lineAboutLayout.setHorizontalGroup(
            lineAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );
        lineAboutLayout.setVerticalGroup(
            lineAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        plAbout.add(lineAbout, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 15, -1, -1));

        slidePanel.add(plAbout);
        plAbout.setBounds(0, 350, 210, 50);

        jSeparator1.setToolTipText("");
        jSeparator1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jSeparator1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jSeparator1.setPreferredSize(new java.awt.Dimension(0, 10));
        slidePanel.add(jSeparator1);
        jSeparator1.setBounds(20, 110, 160, 10);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Asset 5@0.5x.png"))); // NOI18N
        slidePanel.add(jLabel1);
        jLabel1.setBounds(60, 20, 90, 90);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/cheers-204742_1280.jpg"))); // NOI18N
        slidePanel.add(jLabel13);
        jLabel13.setBounds(-160, 0, 370, 600);

        bgPanel.add(slidePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 600));

        plCards.setBackground(new java.awt.Color(68, 142, 246));
        plCards.setLayout(new java.awt.CardLayout());
        bgPanel.add(plCards, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 739, 568));

        btnClose.setBackground(new java.awt.Color(255, 51, 51));
        btnClose.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnClose.setText("X");
        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCloseMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCloseMousePressed(evt);
            }
        });
        bgPanel.add(btnClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(919, 1, 30, 29));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMousePressed
        // TODO add your handling code here: 
        client.send.message = "bye";
        client.send.flag = true;
        System.exit(0);
       
    }//GEN-LAST:event_btnCloseMousePressed

    private void changeLineColor(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeLineColor
        // TODO add your handling code here:
        try {
            String nameComp = evt.getComponent().getName();

            for (JLabel label : menuItemIcons) {
                if (label.getName().equals(nameComp)) {
                    label.setIcon(hashPinkIcon.get(nameComp));
                    break;
                }
            }
            for (JLabel label : menuItemLabels) {
                if (label.getName().equals(nameComp)) {
                    label.setForeground(hoverColor);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }//GEN-LAST:event_changeLineColor

    private void resetColorLine(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetColorLine
        // TODO add your handling code here:
        try {
            String nameComp = evt.getComponent().getName();
            for (JLabel label : menuItemIcons) {
                if (label.getName().equals(nameComp) && !label.getName().equals(activedItem)) {
                    label.setIcon(hashWhiteIcon.get(nameComp));
                    break;
                }
            }

            for (JLabel label : menuItemLabels) {
                if (label.getName().equals(nameComp) && !label.getName().equals(activedItem)) {
                    label.setForeground(Color.WHITE);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }//GEN-LAST:event_resetColorLine

    private void changeColorActive(String nameComp) {
        // TODO add your handling code here:
        try {
            for (JPanel panel : menuItemLines) {
                if (panel.getName().equals(nameComp)) {
                    panel.setOpaque(true);
                    panel.setBackground(hoverColor);

                } else {
                    panel.setOpaque(false);
                    panel.setBackground(tranparencyColor);
                }
            }

            for (JLabel label : menuItemLabels) {
                if (label.getName().equals(nameComp)) {
                    label.setForeground(hoverColor);

                } else {
                    label.setForeground(Color.white);
                }
            }
            for (JLabel label : menuItemIcons) {
                if (label.getName().equals(nameComp)) {
                    label.setIcon(hashPinkIcon.get(nameComp));
                } else {
                    label.setIcon(hashWhiteIcon.get(label.getName()));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    private void btnCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseEntered
        // TODO add your handling code here:
        btnClose.setForeground(Color.WHITE);
        btnClose.setOpaque(true);
        btnClose.setBackground(Color.RED);
    }//GEN-LAST:event_btnCloseMouseEntered

    private void btnCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseExited
        // TODO add your handling code here:
        btnClose.setForeground(Color.black);
        btnClose.setBackground(Color.white);
    }//GEN-LAST:event_btnCloseMouseExited

    private void chooseMenuItem(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chooseMenuItem
        // TODO add your handling code here:
        String nameComp = evt.getComponent().getName();
        switchCard(nameComp);
    }//GEN-LAST:event_chooseMenuItem
    private void switchCard(String cardName) {
        // TODO add your handling code here:
        activedItem = cardName;
        changeColorActive(cardName);
        switch (cardName) {
            case ABOUT: {
                cardLayout.show(plCards, ABOUT);
                break;
            }
            case ACCOUNT: {
                cardLayout.show(plCards, ACCOUNT);
                break;
            }
            case SINGER: {
                cardLayout.show(plCards, SINGER);
                break;
            }
            case SONG: {
                cardLayout.show(plCards, SONG);
                break;
            }
            case HOME: {
                cardLayout.show(plCards, HOME);
                break;
            }
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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bgPanel;
    private javax.swing.JLabel btnClose;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbAbout;
    private javax.swing.JLabel lbAboutIcon;
    private javax.swing.JLabel lbAccount;
    private javax.swing.JLabel lbAccountIcon;
    private javax.swing.JLabel lbHome;
    private javax.swing.JLabel lbHomeIcon;
    private javax.swing.JLabel lbSinger;
    private javax.swing.JLabel lbSingerIcon;
    private javax.swing.JLabel lbSong;
    private javax.swing.JLabel lbSongIcon;
    private javax.swing.JPanel lineAbout;
    private javax.swing.JPanel lineAccount;
    private javax.swing.JPanel lineHome;
    private javax.swing.JPanel lineSinger;
    private javax.swing.JPanel lineSong;
    private javax.swing.JPanel plAbout;
    private javax.swing.JPanel plAccount;
    private javax.swing.JPanel plCards;
    private javax.swing.JPanel plHome;
    private javax.swing.JPanel plSinger;
    private javax.swing.JPanel plSong;
    private javax.swing.JPanel slidePanel;
    // End of variables declaration//GEN-END:variables
}
