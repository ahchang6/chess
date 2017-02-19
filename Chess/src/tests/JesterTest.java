package tests;


import Pieces.*;
import game.Board;
import game.Move;
import junit.framework.TestCase;

/**
 * Created by ahchang6 on 2/12/17.
 */
public class JesterTest extends TestCase {

    public void testJester() throws Exception{
        Piece jesterOne = new Jester(Piece.Color.WHITE);
        Board board = new Board();

        board.place(jesterOne, 4, 4);

        Move moveOne = new Move(4, 4, 5, 6);
        Move moveTwo = new Move(4, 4, 5, 5);

        assertFalse(moveOne.execute(board));
        assertTrue(moveTwo.execute(board));
    }
    public void testJesterCapture() throws Exception{
        Piece jesterOne = new Jester(Piece.Color.WHITE);
        Piece jesterTwo = new Jester(Piece.Color.WHITE);
        Piece jesterThr = new Jester(Piece.Color.WHITE);
        Piece jesterFour = new Jester(Piece.Color.WHITE);
        Piece rookOne = new Rook(Piece.Color.BLACK);
        Piece queenTwo = new Queen(Piece.Color.BLACK);
        Piece bishopTwo = new Bishop(Piece.Color.BLACK);
        Piece knightTwo = new Knight(Piece.Color.BLACK);
        Piece queenThr = new Queen(Piece.Color.BLACK);
        Board board = new Board();

        board.place(jesterOne, 3, 3);
        board.place(jesterTwo, 4, 4);
        board.place(jesterThr, 6, 6);
        board.place(jesterFour, 1, 6);
        board.place(bishopTwo, 6, 5);
        board.place(knightTwo, 1, 5);
        board.place(rookOne, 5, 5);
        board.place(queenTwo, 2, 2);
        board.place(queenThr, 1, 1);

        Move moveOne = new Move(4, 4, 5, 5);
        Move moveTwo = new Move(3, 3, 1, 1);
        Move moveThr = new Move(3, 3, 2, 2);
        Move moveFour = new Move(6, 6, 6, 5);
        Move moveFive = new Move(1, 6, 1, 5);
        // assertTrue(true);
        assertTrue(moveOne.execute(board));
        assertFalse(moveTwo.execute(board));
        assertTrue(moveThr.execute(board));
        assertTrue(moveFour.execute(board));
        assertTrue(moveFive.execute(board));
        assertTrue(board.getPiece(5,5) instanceof Rook);
        assertTrue(board.getPiece(5,5).getColor() == Piece.Color.WHITE);
        assertTrue(board.getPiece(2,2) instanceof Queen);
        assertTrue(board.getPiece(2,2).getColor() == Piece.Color.WHITE);
        assertTrue(board.getPiece(6,5) instanceof Bishop);
        assertTrue(board.getPiece(6,5).getColor() == Piece.Color.WHITE);
        assertTrue(board.getPiece(1,5) instanceof Knight);
        assertTrue(board.getPiece(1,5).getColor() == Piece.Color.WHITE);

        assertTrue(jesterOne.blocked(moveOne,board));
    }

    public void testJesterCaptureTwo() throws Exception{

        Piece jesterOne = new Jester(Piece.Color.WHITE);
        Piece jesterTwo = new Jester(Piece.Color.WHITE);
        Piece pawnOne = new Pawn(Piece.Color.BLACK);
        Piece cannonTwo = new Cannon(Piece.Color.BLACK);
        Board board = new Board();

        board.place(jesterOne, 3, 3);
        board.place(jesterTwo, 4, 4);
        board.place(pawnOne, 3, 2);
        board.place(cannonTwo, 4, 3);

        Move moveOne = new Move(4, 4, 4, 3);
        Move moveTwo = new Move(3, 3, 3, 2);

        assertTrue(moveOne.execute(board));
        assertTrue(moveTwo.execute(board));
    }
}