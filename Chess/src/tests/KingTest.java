package tests;

import Pieces.Bishop;
import Pieces.King;
import Pieces.Piece;
import game.Board;
import game.Move;
import junit.framework.TestCase;

import static Pieces.Piece.Color.BLACK;
import static Pieces.Piece.Color.WHITE;

/**
 * Created by ahchang6 on 2/5/17.
 */
public class KingTest extends TestCase {
    public void testCanMoveTo() throws Exception {
        Piece kingOne = new King(WHITE);
        Piece kingTwo = new King(WHITE);
        Piece kingThr = new King(WHITE);
        Piece kingFour = new King(WHITE);

        Piece bishopWhite = new Bishop(WHITE);
        Piece bishop = new Bishop(BLACK);
        Board board = new Board();

        board.place(kingOne, 4,4);
        Move moveOne = new Move(4,4,5,4);

        assertTrue(moveOne.execute(board));
        board.remove(5,4);

        board.place(kingTwo, 4,4);
        Move moveTwo = new Move(4,4,5,5);

        assertTrue(moveTwo.execute(board));
        board.remove(5,5);

        board.place(kingThr, 4,4);
        Move moveThr = new Move(4,4,4,6);
        assertFalse(moveThr.execute(board));

        board.place(kingFour,0,0);

        //bishop says check
        board.place(bishop,0,1);
        Move moveFour = new Move(0,0,1,0);
        Move moveFive = new Move(0,0,1,1);

        assertFalse(moveFour.execute(board));
        assertTrue(moveFive.execute(board));

        board.remove(1,1);
        board.remove(0,1);

        board.place(bishop,2,2);
        board.place(kingFour,0,0);
        board.place(bishopWhite,1,1);

        Move moveSix = new Move(1,1,0,2);
        //Would caused a pinned piece to move king into check
        assertFalse(moveSix.execute(board));


    }

    public void testBlocked() throws Exception {

        Piece kingOne = new King(WHITE);
        Board board = new Board();

        board.place(kingOne, 4,4);
        Move move = new Move(4,4,5,4);
                assertFalse(kingOne.blocked(move,board));
    }
}