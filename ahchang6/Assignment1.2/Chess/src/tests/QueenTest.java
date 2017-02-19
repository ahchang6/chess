package tests;

import Pieces.Piece;
import Pieces.Queen;
import game.Board;
import game.Move;
import junit.framework.TestCase;

import static Pieces.Piece.Color.BLACK;
import static Pieces.Piece.Color.WHITE;

/**
 * Created by ahchang6 on 2/5/17.
 */
public class QueenTest extends TestCase {
    public void testCanMoveTo() throws Exception {

        Piece queenOne = new Queen(WHITE);
        Piece queenTwo = new Queen(WHITE);
        Piece queenThr = new Queen(WHITE);
        Board board = new Board();

        board.place(queenOne, 4,4);
        Move moveOne = new Move(4,4,0,4);

        assertTrue(moveOne.execute(board));
        board.remove(0,4);

        board.place(queenTwo, 4,4);
        Move moveTwo = new Move(4,4,6,6);

        assertTrue(moveTwo.execute(board));
        board.remove(6,6);
        board.place(queenThr, 4, 4);
        Move moveThr = new Move(4, 4, 6, 5);
        assertFalse(moveThr.execute(board));

    }

    public void testBlocked() throws Exception {
        Piece queenOne = new Queen(WHITE);
        Piece block = new Queen(WHITE);
        Board board = new Board();

        board.place(queenOne, 3, 3);
        Move moveThr = new Move(3, 3, 6, 5);
        assertFalse(moveThr.execute(board));
        board.place(block, 6, 6);
        Move moveFour = new Move(3, 3, 7, 7);
        //blocked
        assertFalse(moveFour.execute(board));
        board.place(block, 4, 3);

        Move moveFive = new Move(3, 3, 6, 3);
        assertFalse(moveFive.execute(board));

        Move moveSix = new Move(3,3,1,1);
        assertTrue(moveSix.execute(board));
    }
    public void testCapture() throws Exception {
        Piece queenOne = new Queen(WHITE);
        Piece queenCapture = new Queen(BLACK);
        Board board = new Board();

        board.place(queenOne, 3, 3);
        board.place(queenCapture, 6, 6);
        Move moveOne = new Move(3, 3, 6, 6);
        assertTrue(moveOne.execute(board));
        assertFalse(queenOne.blocked(moveOne,board));


    }
}
