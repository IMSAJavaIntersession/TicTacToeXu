
package tictactoe;

// http://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-2-evaluation-function/

import static java.lang.Math.max;
import static java.lang.Math.min;

// http://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-3-tic-tac-toe-ai-finding-optimal-move/
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
    
    int minimax(int depth, boolean isMax) {  // work on maximizer first
        int score = evaluate();
        if ( score==10)
            return score-depth;  // adjust score according depth, find optimal move
        else if ( score==-10)
            return score+depth;
        if (gameOver())
            return 0;
        if ( isMax ) {
            score = -10000;
            for (int r=0; r<BOARD_SIZE; r++) {
                for (int c=0; c<BOARD_SIZE; c++) {
                    if ( !isBlank(r, c) )
                        continue;
                    setMove(r, c, true);
                    score = max(score, minimax(depth+1, !isMax));
                    rollback(r,c);
                }
            }
        }
        else {
            score = 10000;
            for (int r=0; r<BOARD_SIZE; r++) {
                for (int c=0; c<BOARD_SIZE; c++) {
                    if ( !isBlank(r, c) )
                        continue;
                    setMove(r, c, false);
                    score = min(score, minimax(depth+1, !isMax));
                    rollback(r,c);
                }
            }
        }
        return score;
    }
    public void computer()
    {
        moves++;
        //if (choose1stMove()) 
        //    return;
        System.out.println("computer move "+moves);
        int best = -10000;
        int bestR=-1;
        int bestC=-1;
        for (int r=0; r<BOARD_SIZE; r++) {
            for (int c=0; c<BOARD_SIZE; c++) {
                if (!isBlank(r,c))
                    continue;
                setMove(r, c, true);
                int score=minimax(0, false);
                rollback(r,c);
                if (score>best) {
                    best=score;
                    bestR=r;
                    bestC=c;
                }
            }
        }
        if (bestR>=0) {
            setMove(bestR, bestC, true);
            System.out.println("computer move "+moves+":"+bestR+","+bestC+". Score="+best);
            printBoard();
        }
    }
}
