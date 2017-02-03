/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import static java.lang.Math.random;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Andy
 */
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
        
        if (xo[0][2] != '-' && xo[0][2]== xo[1][1] && xo[1][1]==xo[2][0])
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
}
