/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author Andy
 */
public class TicTacToePro  extends TicTacToe{
    
    private boolean choose1stMove() {
        if (isBlank(1,1)) {
            xo[1][1]='O';
            return true;
        }
        // else choose a corner move
        return false;
    }
    private boolean findWinningMove()
    {
        return false;
    }
    
    private boolean preventLosingMove()
    {
        return false;
    }
    
    // make your pieces two in a line
    // prevent opponent piece to 
    private boolean findBestMove()
    {
        return false;
    }
    
    public void computer()
    {
        if (choose1stMove()) 
            return;
        if (findWinningMove())
            return;
        if (preventLosingMove())
            return;
        super.computer();
    }
}
