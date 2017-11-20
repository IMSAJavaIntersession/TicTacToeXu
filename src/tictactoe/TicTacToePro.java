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
    final static char COMPUTER='O';
    
    private boolean isCorner(int r, int c)
    {
        if (r==c && r!=1)
            return true;
        if (r+c==2 && r!=1)
            return true;
        return false;
    }
    private boolean findWinningMove(boolean test)
    {
        for(int r=0; r<this.BOARD_SIZE; r++) {
            for(int c=0; c<this.BOARD_SIZE; c++) {
                if (!isBlank(r,c))
                    continue;
                // pretend to make a move
                setMove(r, c, true);
                if (won()) { // if computer would win next
                    if ( test )
                        rollback(r, c);
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
    
    public int winningWays()
    {
        int ways=0;
        for (int r=0; r<BOARD_SIZE; r++)
        {
            if (isRowWon(r))
                ways++;
            if (isColWon(r))
                ways++;
        }
        
        if (isCrossWon())
            ways++;
        if (isBackCrossWon())
            ways++;
        return ways;
    }
    
    // make your pieces two in a line
    // prevent opponent piece to 
    private boolean isMoveGood(int r, int c) {
        if (!isBlank(r,c))
            return false;
        // pretend to make a move
        setMove(r, c, true);
        boolean good = findWinningMove(true);
        if ( !good )
            rollback(r, c);
        return good;
    }
    protected int computerNextWinningChance(boolean bComputer)
    {
        int ways = 0;
        for(int r=0; r<this.BOARD_SIZE; r++) {
            for(int c=0; c<this.BOARD_SIZE; c++) {
                if (!isBlank(r,c))
                    continue;
                // pretend to make a move
                setMove(r, c, bComputer);
                ways += winningWays();
                rollback(r, c);                
            }
        }    
        return ways;
    }
    private int estimateMoveValue(int r, int c)
    {
        if (!isBlank(r,c))
            return 0;
        // 
        setMove(r, c, false);  // if human moves here
        int humanWin = computerNextWinningChance(false);
        rollback(r, c);        
        setMove(r, c, true);  // if compuetr moves here
        int computerWin = computerNextWinningChance(true);
        rollback(r, c);
        if ( humanWin> 1)
            return 10;
        if ( computerWin> 1)
            return 8;
        if ( humanWin== 1 && computerWin== 1)
            return 6;
        if ( computerWin== 1)
            return 4;
        if ( humanWin== 1)
            return 2;
        return 0;
    }
    private boolean findBestMove()
    {
        // pick a corner move to prevent opponent to form two winning options
    /*    if ( isMoveGood(0, 0) )
            return true;
        if ( isMoveGood(0, 2) )
            return true;
        if ( isMoveGood(2, 0) )
            return true;
        if ( isMoveGood(2, 2) )
            return true;*/
        int bestScore=0;
        int bestRow=0;
        int bestCol=0;
        for(int r=0; r<this.BOARD_SIZE; r++) {
            for(int c=0; c<this.BOARD_SIZE; c++) {
                int val = estimateMoveValue(r, c);
                if (val > bestScore) {
                    bestScore=val;
                    bestRow=r;
                    bestCol=c;
                }
            }            
        }
        if (bestScore>0) {
            setMove(bestRow, bestCol, true);
            return true;
        }
        return false;
    }
    
    public void computer()
    {
        moves++;
        if (choose1stMove()) 
            return;
        System.out.println("next move");
        if (findWinningMove(false))
            return;
        if (preventLosingMove())
            return;
        if (findBestMove())
            return;
        super.computer();
    }
}
