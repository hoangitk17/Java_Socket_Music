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
public class SingerRenderer extends JLabel implements ListCellRenderer<String>{
    
    public SingerRenderer() {
        setOpaque(true);
        setMinimumSize(new Dimension(500,50));
        setFont(new java.awt.Font("Roboto Mono", 0, 14));
        setBorder(
                javax.swing.BorderFactory.createCompoundBorder(
                        getBorder(),
                        javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)
                )
        );
    }
    @Override
    public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
        boolean isSelected, boolean cellHasFocus) {
        
        setText(value);     
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
