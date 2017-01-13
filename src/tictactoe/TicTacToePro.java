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
    int moves=0; // computer moves
    final static char COMPUTER='O';
    private boolean choose1stMove() {
        if (isBlank(1,1)) {
            xo[1][1]=COMPUTER;
            return true;
        }
        // else choose a corner move
        if ( moves==1) {
            return setMove(0,0,true);
        }
        return false;
    }
    private boolean findWinningMove()
    {
        for(int r=0; r<this.BOARD_SIZE; r++) {
            for(int c=0; c<this.BOARD_SIZE; c++) {
                if (!isBlank(r,c))
                    continue;
                // pretend to make a move
                setMove(r, c, true);
                if (won()) { // if computer would win next
                    return true;
                }
                rollback(r, c);
            }
        }
        return false;
    }
    
    private boolean preventLosingMove()
    {
        for(int r=0; r<this.BOARD_SIZE; r++) {
            for(int c=0; c<this.BOARD_SIZE; c++) {
                if (!isBlank(r,c))
                    continue;
                // pretend to make a move
                setMove(r, c, false);
                if (won()) { // if opponent would win next
                    rollback(r, c);
                    setMove(r,c,true); // take it first
                    System.out.println("lose "+r +" "+c);
                    return true;
                }
                rollback(r, c);
            }
        }
        System.out.println("lose false");
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
        moves++;
        if (choose1stMove()) 
            return;
        System.out.println("next move");
        if (findWinningMove())
            return;
        if (preventLosingMove())
            return;
        super.computer();
    }
}
