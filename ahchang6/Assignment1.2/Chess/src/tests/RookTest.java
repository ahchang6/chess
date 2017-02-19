package tests;

import Pieces.Piece;
import Pieces.Rook;
import game.Board;
import game.Move;
import junit.framework.TestCase;

import static Pieces.Piece.Color.WHITE;

/**
 * Created by ahchang6 on 2/5/17.
 */
public class RookTest extends TestCase {
    public void testCanMoveTo() throws Exception {

        Piece rookOne = new Rook(WHITE);
        Piece rookTwo = new Rook(WHITE);
        Board board = new Board();

        board.place(rookOne, 4,4);
        Move moveOne = new Move(4,4,0,4);

        assertTrue(moveOne.execute(board));
        board.remove(0,4);

        board.place(rookTwo, 4,4);
        Move moveTwo = new Move(4,4,6,6);

        assertFalse(moveTwo.execute(board));
    }

    public void testBlocked() throws Exception {

        Piece rookOne = new Rook(WHITE);
        Piece rookTwo = new Rook(WHITE);
        Board board = new Board();

        board.place(rookOne, 4,4);
        board.place(rookTwo, 5,4);
        Move moveOne = new Move(4,4,6,4);

        //System.out.println("This was tested");
        assertFalse(moveOne.execute(board));
        Move moveTwo = new Move(4,4,1,4);
        Move moveThr = new Move(1,4,1,2);
        board.place(rookTwo,1,5);
        Move moveFour = new Move(1,2,1,6);
        assertTrue(moveTwo.execute(board));
        assertTrue(moveThr.execute(board));
        assertFalse(moveFour.execute(board));
    }

}