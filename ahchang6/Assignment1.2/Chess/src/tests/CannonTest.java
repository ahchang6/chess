package tests;

import Pieces.Cannon;
import Pieces.Pawn;
import Pieces.Piece;
import game.Board;
import game.Move;
import junit.framework.TestCase;

/**
 * Created by ahchang6 on 2/10/17.
 */
public class CannonTest extends TestCase {

    public void testCannon() throws Exception{
        Piece cannonOne = new Cannon(Piece.Color.WHITE);
        Board board = new Board();

        board.place(cannonOne, 4, 4);

        Move moveOne = new Move(4, 4, 6, 6);
        Move moveTwo = new Move(4, 4, 4, 6);

        assertFalse(moveOne.execute(board));
        assertTrue(moveTwo.execute(board));
    }
    public void testCannonCaptureOne() throws Exception{
        Piece cannonOne = new Cannon(Piece.Color.WHITE);
        Piece cannonTwo = new Cannon(Piece.Color.WHITE);
        Piece pawnOne = new Pawn(Piece.Color.BLACK);
        Piece pawnTwo = new Pawn(Piece.Color.BLACK);
        Board board = new Board();

        board.place(cannonOne, 3, 3);
        board.place(cannonTwo, 4, 3);
        board.place(pawnOne, 6, 3);
        board.place(pawnTwo, 1, 3);

        Move moveOne = new Move(3, 3, 1, 3);
        Move moveTwo = new Move(3, 3, 6, 3);
        // assertTrue(true);
        assertFalse(moveOne.execute(board));
        assertTrue(moveTwo.execute(board));
    }

    public void testCannonCaptureTwo() throws Exception{
        Piece cannonOne = new Cannon(Piece.Color.WHITE);
        Piece cannonBlocker = new Cannon(Piece.Color.WHITE);
        Piece cannonBlockerTwo = new Cannon(Piece.Color.WHITE);
        Piece cannonBlockerThr = new Cannon(Piece.Color.WHITE);
        Piece cannonThr = new Cannon(Piece.Color.WHITE);
        Piece cannonFour = new Cannon(Piece.Color.WHITE);
        Piece pawnOne = new Pawn(Piece.Color.BLACK);
        Piece pawnTwo = new Pawn(Piece.Color.BLACK);
        Piece pawnThr = new Pawn(Piece.Color.BLACK);
        Board board = new Board();

        board.place(cannonOne, 3, 3);
        board.place(cannonBlocker, 3, 4);
        board.place(pawnOne, 3, 6);


        board.place(cannonBlockerTwo, 3, 2);
        board.place(pawnTwo, 3, 0);


        board.place(cannonBlockerThr, 2, 3);
        board.place(pawnThr, 0, 3);

        Move moveOne = new Move(3, 3, 3, 6);

        Move moveTwo = new Move(3, 3, 3, 0);
        Move moveThr = new Move(3, 3, 0, 3);
        // assertTrue(true);
        assertTrue(moveOne.execute(board));
        board.place(cannonThr, 3, 3);
        assertTrue(moveTwo.execute(board));
        board.place(cannonFour, 3, 3);
        assertTrue(moveThr.execute(board));
    }
}