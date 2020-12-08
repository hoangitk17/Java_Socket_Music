/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.GUI;

import Server.Song;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Nguyen Van Hoang
 */
public class SongRenderer extends JLabel implements ListCellRenderer<Song>{
    
    public SongRenderer() {
        setOpaque(true);
        setMinimumSize(new Dimension(500,50));
        setBorder(
                javax.swing.BorderFactory.createCompoundBorder(
                        getBorder(),
                        javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)
                )
        );
    }
    @Override
    public Component getListCellRendererComponent(JList<? extends Song> list, Song song, int index,
        boolean isSelected, boolean cellHasFocus) {
        
        setText(song.getName() + " --- " + song.getSinger());     
        if(isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        return this;
    }
}
