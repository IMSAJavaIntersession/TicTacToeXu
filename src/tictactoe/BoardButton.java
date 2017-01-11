/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author WXU
 */
public class BoardButton extends javax.swing.JButton {
    
        public BoardButton(int r, int c)
        {
            row=r;
            col=c;
        }
        public int getRow() {
            return row;
        }
        public int getCol() {
            return col;
        }
        
          // Paint the round background and label.
        protected void paintComponent(Graphics g) {
          if (getModel().isArmed()) {
            // You might want to make the highlight color 
            // a property of the RoundButton class.
            g.setColor(Color.lightGray);
          } else {
            g.setColor(getBackground());
          }

          int x4Points[] = {0, getSize().width, getSize().width, 0};
          int y4Points[] = {0, 0, getSize().height, getSize().height};
          g.fillPolygon(x4Points, y4Points, x4Points.length); 

          // This call will paint the label and the 
          // focus rectangle.
          super.paintComponent(g);
        }
        private int row;
        private int col;
}
