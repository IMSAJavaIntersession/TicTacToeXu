/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author WXU
 */
public class TicTacToeConsole {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        TicTacToe game = new TicTacToe();
        game.printBoard();
        
        while ( !game.gameOver())
        {
            while (!game.move());
            game.printBoard();
            if ( game.won()) {
                System.out.println(" You won");
                break;
            }
            game.computer();
            game.printBoard();
            if ( game.won()) {
                System.out.println(" Computer won");
                break;
            }
        }
    }
    
}
