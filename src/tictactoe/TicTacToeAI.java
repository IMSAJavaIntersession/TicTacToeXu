
package tictactoe;

// http://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-2-evaluation-function/

import static java.lang.Math.max;
import static java.lang.Math.min;
import java.util.Map;
import java.util.TreeMap;

// http://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-3-tic-tac-toe-ai-finding-optimal-move/
// minimax idea:
// maximizer return the max of value of all possible next move. if it is not leaf node, it is max of minimal of opponent moves
// Likewise opponent is a minimizer, returm min of values of all possible next move. minimum of all maximizer values
// alpha beta pruing is to eliminate unnecessary computation
// For maximizer, 
public class TicTacToeAI extends TicTacToe {
        
    final char PLAYER='X';
    final char COMPUTER='O';
    int evaluate()  // 10 points if COMPUTER won
    {
        for (int r=0; r<BOARD_SIZE; r++)
        {
            if (isRowWon(r))
                return xo[r][0]==COMPUTER?10:-10;
            if (isColWon(r))
                return xo[0][r]==COMPUTER?10:-10;            
        }
        
        if (isCrossWon() || isBackCrossWon())
            return xo[1][1]==COMPUTER?10:-10;
        
        return 0;
    }
    
    int minimax(int depth, boolean isMax, int alpha, int beta) {  // work on maximizer first
        int score = evaluate();
        if ( score==10)
            return score-depth;  // adjust score according depth, find optimal move
        else if ( score==-10)
            return score+depth;
        if (gameOver())
            return 0;
        score = isMax?-10000:10000;  // initialize before min or max calc
        for (int r=0; r<BOARD_SIZE; r++) {
            for (int c=0; c<BOARD_SIZE; c++) {
                if ( !isBlank(r, c) )
                    continue;
                setMove(r, c, isMax);
                int newScore=minimax(depth+1, !isMax, alpha, beta);
                score = isMax?max(score, newScore):min(score, newScore);
                rollback(r,c);
                if ( isMax )
                    alpha = max(alpha, score);
                else
                    beta = min(beta, score);
                if (beta<=alpha) {
                    //System.out.println("prune alpha="+alpha+" beta="+beta+" maximizer?"+isMax+" row="+r+" col="+c);
                    break;
                }
            }
        }
        return score;
    }
    public void computer()
    {
        moves++;
        BestMove bm=findBestMove(true);
        if (bm.row>=0) {
            setMove(bm.row, bm.col, true);
            System.out.println("computer move "+moves+":"+bm.row+","+bm.col+". Score="+bm.score);
            printBoard();
        }
        else
            System.out.println("computer skip move "+moves);
    }
    
    String board2String()
    {
        StringBuilder sb=new StringBuilder();
        for (int r=0; r<BOARD_SIZE; r++) {
            for (int c=0; c<BOARD_SIZE; c++) {
                sb.append(xo[r][c]);
            }
        }
        return sb.toString();
    }
    class BestMove
    {
        int score;
        int row;
        int col;
        BestMove()
        {
            row=-1; col=-1;
        }
    }
    //Map<String, BestMove> completeGameTree=new TreeMap<>();
    BestMove findBestMove(boolean isMax)
    {        
        BestMove bm=new BestMove();
        bm.score = isMax?-10000:10000;
        for (int r=0; r<BOARD_SIZE; r++) {
            for (int c=0; c<BOARD_SIZE; c++) {
                if (!isBlank(r,c))
                    continue;
                setMove(r, c, isMax);
                int score=minimax(0, !isMax, -10000, 10000);
                rollback(r,c);
                if (isMax && score>bm.score || !isMax &&score<bm.score) {
                    bm.score=score;
                    bm.row=r;
                    bm.col=c;
                }
            }
        }
        return bm;
    }
}
