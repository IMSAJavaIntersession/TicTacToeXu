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
    char [][] xo= new char[BOARD_SIZE][BOARD_SIZE];

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
    
    public boolean move()
    {
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("enter your move");
            int r = scan.nextInt();
            int c = scan.nextInt();
            if ( xo[r][c] !='-')
                return false;
            xo[r][c] = 'X';
            return true;
        }
        catch (Exception e){
            return false;
        }            
    }
    
    public void computer()
    {
        int r,c;
        do {
            r = random.nextInt(BOARD_SIZE);
            c = random.nextInt(BOARD_SIZE);
        } while (xo[r][c] !='-');
        xo[r][c]='O';
    }
    public boolean won()
    {
        for (int r=0; r<BOARD_SIZE; r++)
        {
            if (xo[r][0] != '-' && xo[r][0] == xo[r][1] && xo[r][1]==xo[r][2])
                return true;
            if (xo[0][r] != '-' &&xo[0][r] == xo[1][r] && xo[1][r]==xo[2][r])
                return true;                
        }
        
        if (xo[0][0] != '-' && xo[0][0] == xo[1][1] && xo[1][1]==xo[2][2])
            return true;
        
        if (xo[0][2] != '-' && xo[0][2]== xo[1][1] && xo[1][1]==xo[2][0])
            return true;
        return false;
    }
    
    public boolean gameOver()
    {
        for (int r=0; r<BOARD_SIZE; r++)
            for (int c =0; c<BOARD_SIZE; c++)
                if (xo[r][c] == '-')
                    return false;  
        return true;
    }
    
}
