
package tictactoe;

import static java.lang.Math.random;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static final int BOARD_SIZE=3;
    Random random = new Random();
    protected char [][] xo= new char[BOARD_SIZE][BOARD_SIZE];

    {
        for (int r=0; r<BOARD_SIZE; r++)
            for (int c =0; c<BOARD_SIZE; c++)
                xo[r][c] = '-';                    
    }

    public void printBoard()
    {
        for (char[] row: xo) {
            for (char c : row) {
                System.out.print(c);
                System.out.print(" ");
            }
            System.out.println();
        }
        //System.out.println("3x3 board");
    }
    
    public boolean isBlank(int r, int c)
    {
        return xo[r][c]=='-';
    }
    
    public char getBoardValue(int r, int c)
    {
        return xo[r][c];
    }
    
    void rollback(int r, int c)
    {
        xo[r][c] ='-';
        bGameOver = false;
    }
    public boolean setMove(int r, int c, boolean bComputer)
    {
        if ( xo[r][c] !='-')
            return false;
        if ( bComputer )
            xo[r][c] = 'O';
        else
            xo[r][c] = 'X';
        return true;
    }
    public boolean move()
    {
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("enter your move");
            int r = scan.nextInt();
            int c = scan.nextInt();
            return setMove(r,c,false);
        }
        catch (Exception e){
            return false;
        }            
    }
    
    public void computer()
    {
        if ( gameOver() )
            return;
        int r,c;
        do {
            r = random.nextInt(BOARD_SIZE);
            c = random.nextInt(BOARD_SIZE);
        } while (!isBlank(r, c));
        xo[r][c]='O';
    }
    
    protected boolean isRowWon(int r)
    {
        if ( isBlank(r, 0) || isBlank(r, 1) || isBlank(r, 2) )
            return false;
        if (xo[r][0] == xo[r][1] && xo[r][1]==xo[r][2])
            return true;
        return false;
    }
        
    protected boolean isColWon(int c)
    {
        if ( isBlank(0, c) || isBlank(1, c) || isBlank(2, c) )
            return false;
        if (xo[0][c] == xo[1][c] && xo[1][c]==xo[2][c])
            return true;
        return false;
    }
    
    protected boolean isCrossWon()
    {
        if ( isBlank(0,0) || isBlank(1, 1) || isBlank(2, 2) )
            return false;            
        if ( xo[0][0] == xo[1][1] && xo[1][1]==xo[2][2])
            return true;
        return false;
    }
    
    protected boolean isBackCrossWon()
    {
        if ( isBlank(2,0) || isBlank(1, 1) || isBlank(0, 2) )
            return false;            
        if ( xo[2][0] == xo[1][1] && xo[1][1]==xo[0][2])
            return true;
        return false;
    }
    
    public boolean won()
    {
        for (int r=0; r<BOARD_SIZE; r++)
        {
            if (isRowWon(r))
                return bGameOver=true;
            if (isColWon(r))
                return bGameOver=true;            
        }
        
        if (isCrossWon())
            return bGameOver=true;
        if (isBackCrossWon())
            return bGameOver=true;
        
        return false;
    }
    
    public boolean gameOver()
    {
        for (int r=0; r<BOARD_SIZE; r++)
            for (int c =0; c<BOARD_SIZE; c++)
                if (isBlank(r, c))
                    return false;  
        return bGameOver=true;
    }
    
    boolean isGameOver() {
        return bGameOver;
    }
    
    boolean bGameOver=false;
    
    //smart moves
    int moves=0; // computer moves
    // transform coordinates to next square
    private int transform(int orig, int trans)
    {
        orig = trans-orig;
        if (orig<0)
            orig *= -1;  
        return orig;
    }
    
    private void counterCornerMove(int r, int c)
    {
        int choice = random.nextInt(6); // 3 different moves
        switch (choice) {
            case 0:
                setMove(2-r,2-c,true);
                break;
            case 1:
                setMove(1,1,true);
            case 2:
                r = transform(r, 1);
                setMove(r,c,true);
                break;
            case 3:
                c = transform(c, 1);
                setMove(r,c,true);
                break;
            case 4:
                r = transform(r, 2);
                setMove(r,c,true);
                break;
            case 5:
            default:
                c = transform(c, 2);
                setMove(r,c,true);
                break;
        }
    }
    protected boolean choose1stMove() {
        if ( moves>1)
            return false;
        // 
        if (!isBlank(1,1)) // choose a corner move
            return setMove(0,0,true);
        // opposite corner
        if ( !isBlank(0,0))
            counterCornerMove(0,0);
        else if ( !isBlank(2,2))
            counterCornerMove(2,2);
        else if ( !isBlank(0,2))
            counterCornerMove(0,2);
        else if ( !isBlank(2,0))
            counterCornerMove(2,0);
        else
            return setMove(1,1,true);
        return true;
    }
    
}
