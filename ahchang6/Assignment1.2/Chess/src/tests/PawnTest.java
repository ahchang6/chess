package tests;

import Pieces.Pawn;
import Pieces.Piece;
import game.Board;
import game.Move;
import junit.framework.TestCase;

import static Pieces.Piece.Color.BLACK;
import static Pieces.Piece.Color.WHITE;

/**
 * Created by ahchang6 on 2/5/17.
 */
public class PawnTest extends TestCase {
    public void testCanMoveTo() throws Exception {

        Piece blackPawnOne = new Pawn(BLACK);
        Piece blackPawnTwo = new Pawn(BLACK);
        Piece whitePawnOne = new Pawn(WHITE);
        Piece whitePawnTwo = new Pawn(WHITE);
        Piece whitePawnThr = new Pawn(WHITE);

        Board board = new Board();

        board.place(blackPawnOne, 2,6);
        board.place(blackPawnTwo, 2,3);
        board.place(whitePawnOne, 4,1);
        board.place(whitePawnTwo, 4,5);
        board.place(whitePawnOne, 5,5);

        Move moveOne = new Move(2,6,2,4);
        Move moveTwo = new Move(2,3,2,1);
        Move moveThr = new Move(4,1,4,3);
        Move moveFour = new Move(4,5,4,7);
        Move moveFive = new Move(5,5,5,6);

        assertTrue(moveOne.execute(board));
        assertFalse(moveTwo.execute(board));
        assertTrue(moveThr.execute(board));
        assertFalse(moveFour.execute(board));
        assertTrue(moveFive.execute(board));
    }

    public void testBlocked() throws Exception {

        Piece blackPawnOne = new Pawn(BLACK);
        Piece blackPawnTwo = new Pawn(BLACK);
        Piece whitePawnOne = new Pawn(WHITE);

        Board board = new Board();

        board.place(blackPawnOne, 2,3);
        board.place(blackPawnTwo, 2,4);
        board.place(whitePawnOne, 2,2);

        Move moveOne = new Move(2,4,2,3);
        Move moveTwo = new Move(2,2,2,3);

        assertFalse(moveOne.execute(board));
        assertFalse(moveTwo.execute(board));
    }
    public void testCapture() throws Exception {
        Piece blackPawnOne = new Pawn(BLACK);
        Piece blackPawnTwo = new Pawn(BLACK);
        Piece whitePawnOne = new Pawn(WHITE);

        Board board = new Board();

        board.place(blackPawnOne, 2,3);
        board.place(blackPawnTwo, 3,3);
        board.place(whitePawnOne, 3,2);

        Move moveOne = new Move(3,3,3,2);
        Move moveTwo = new Move(2,3,3,2);

        assertFalse(moveOne.execute(board));
        assertTrue(moveTwo.execute(board));

    }
    public void testBlock() throws Exception {
        Piece blackPawnOne = new Pawn(BLACK);
        Piece whitePawnOne = new Pawn(WHITE);
        Board board = new Board();

        board.place(blackPawnOne, 3,4);
        board.place(whitePawnOne, 3,3);

        Move moveOne = new Move(3,4,3,3);
        Move moveTwo = new Move(3,3,3,4);

        assertFalse(moveOne.execute(board));
        assertFalse(moveTwo.execute(board));
    }

}