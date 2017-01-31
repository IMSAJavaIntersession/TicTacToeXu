/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

/**
 *
 * @author Andy
 */
public class TicTacToeJrame extends javax.swing.JFrame {

    /**
     * Creates new form TicTacToeJrame
     */
    public TicTacToeJrame() {
        //initComponents();
        initBoard();
    }
    
    
    class ButtonAction implements java.awt.event.ActionListener
    {
        public ButtonAction(BoardButton btn)
        {
            button=btn;
        }
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            ActionPerformed(button, evt);
        }

        private BoardButton button;
    }
    
    public static int vk(char key)
    {
        switch(key) {
            case 'F':
                return KeyEvent.VK_F;
            case 'P':
                return KeyEvent.VK_P;
        }
        return KeyEvent.VK_UNDEFINED;
    }
    
    public void createRadioMenu(JMenu menu2, String []items, int selected)
    {
        // create radio items
        ButtonGroup group = new ButtonGroup();
        for (int i=0; i<items.length; i++)
        {
            JRadioButtonMenuItem rbMenuItem=new JRadioButtonMenuItem(items[i]);
            rbMenuItem.setMnemonic(vk(items[i].charAt(0)));
            rbMenuItem.setSelected(selected==i);
            group.add(rbMenuItem);
            final int game=i;
            rbMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    gameLevelActionPerformed(game, evt);
                }
            });
            menu2.add(rbMenuItem);
        }
    }
    
    private void initBoard()
    {
        jButtons = new BoardButton[TicTacToe.BOARD_SIZE][TicTacToe.BOARD_SIZE];
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.awt.GridLayout grid=new java.awt.GridLayout(TicTacToe.BOARD_SIZE, TicTacToe.BOARD_SIZE, 0,0);
        grid.setHgap(0);
        grid.setVgap(0);
        getContentPane().setLayout(grid);
        for (int i=0; i<TicTacToe.BOARD_SIZE; i++) {
            for (int j=0; j<TicTacToe.BOARD_SIZE; j++)   {             
                jButtons[i][j] = new BoardButton(i, j);
                jButtons[i][j].setText("");
                jButtons[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(Color.darkGray));
                jButtons[i][j].setContentAreaFilled(false);
                jButtons[i][j].setMargin(new java.awt.Insets(0, 0, 0, 0));
                
                jButtons[i][j].setBackground(Color.GRAY);
                getContentPane().add(jButtons[i][j]);
                
                jButtons[i][j].addActionListener(new ButtonAction(jButtons[i][j]));
            }
        }
        getContentPane().setPreferredSize(new Dimension(300,300));

        pack();
        
        JMenuBar menuBar1 = new JMenuBar();
        JMenu menu1 = new JMenu("File");
        menuBar1.add(menu1);
        JMenuItem menuItem = new JMenuItem("New Game", KeyEvent.VK_N);
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewGameActionPerformed(evt);
            }
        });
        menu1.add(menuItem);
        
        JMenu menu2 = new JMenu("Level");
        createRadioMenu(menu2, new String[]{"Fun","Pro"}, proLevel?1:0);
        menuBar1.add(menu2);
        
        /*
        // create radio items
        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem rbMenuItem=new JRadioButtonMenuItem("Fun");
        rbMenuItem.setMnemonic(KeyEvent.VK_F);
        rbMenuItem.setSelected(!proLevel);
        group.add(rbMenuItem);
        rbMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FunLevelActionPerformed(evt);
            }
        });
        menu2.add(rbMenuItem);

        rbMenuItem=new JRadioButtonMenuItem("Pro");
        rbMenuItem.setMnemonic(KeyEvent.VK_P);
        rbMenuItem.setSelected(proLevel);
        group.add(rbMenuItem);
        rbMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProLevelActionPerformed(evt);
            }
        });
        menu2.add(rbMenuItem);
        */
        setJMenuBar(menuBar1);
    }
    
    private void display()
    {        
        for (int i=0; i<TicTacToe.BOARD_SIZE; i++) {
            for (int j=0; j<TicTacToe.BOARD_SIZE; j++)   {
                if (board.getBoardValue(i, j)!='-') {
                    jButtons[i][j].setText(""+board.getBoardValue(i, j));
                    System.out.println(""+i+" "+j+" "+board.getBoardValue(i, j));
                    jButtons[i][j].setBackground(Color.lightGray);
                }
                else
                    jButtons[i][j].setBackground(Color.GRAY);
            }
        }
    }
    private void ActionPerformed(BoardButton button, java.awt.event.ActionEvent evt) {                                    
        // TODO add your handling code here:
        if (board.isGameOver()) {
            System.out.println("game over");
            return;
        }
        if ( !board.setMove(button.getRow(), button.getCol(),false )) {
            this.setTitle("Bad Move. Try again");
            return;
        }
        display();
        if (board.won()) {
            this.setTitle("You Won");
            return;
        }
        board.computer();
        display();
        if (board.won())
            this.setTitle("Computer Won");
        if (board.gameOver())
            this.setTitle("Game Over");
    }
     
    private void NewGameActionPerformed(java.awt.event.ActionEvent evt) {                                    
        // TODO add your handling code here:
        createBoard();
        for (int i=0; i<TicTacToe.BOARD_SIZE; i++) {
            for (int j=0; j<TicTacToe.BOARD_SIZE; j++)   {
                jButtons[i][j].setText(""); 
                jButtons[i][j].setBackground(Color.GRAY);
            }
        }
        this.setTitle("New Game");
    }
    
    private void FunLevelActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("fun");  
        proLevel=false;
    }
    
    private void ProLevelActionPerformed(java.awt.event.ActionEvent evt) {  
        proLevel=true;
        System.out.println("pro");  
    }
    
    private void gameLevelActionPerformed(int level, java.awt.event.ActionEvent evt) {  
        if (level>0)
            proLevel=true;
        else
            proLevel=false;
    }
    private void createBoard()
    {
        if (proLevel) {
            board = new TicTacToePro();
            System.out.println("pro board");
        }
        else {
            board = new TicTacToe();
            System.out.println("fun board");
        }
    }
    
    TicTacToe       board;
    BoardButton[][] jButtons;
    boolean         proLevel=true;
    {
        createBoard();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(TicTacToeJrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TicTacToeJrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TicTacToeJrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TicTacToeJrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TicTacToeJrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
