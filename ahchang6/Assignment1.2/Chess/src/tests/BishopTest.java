package tests;

import Pieces.Bishop;
import Pieces.Piece;
import game.Board;
import game.Move;
import junit.framework.TestCase;


/**
 * Created by ahchang6 on 2/5/17.
 */
public class BishopTest extends TestCase {


    public void testBishop() throws Exception{
        Piece bishopOne = new Bishop(Piece.Color.WHITE);
        Piece bishopTwo = new Bishop(Piece.Color.WHITE);
        Piece bishopThr = new Bishop(Piece.Color.WHITE);
        Piece bishopBlock = new Bishop(Piece.Color.WHITE);
        Board board = new Board();

        board.place(bishopOne, 4, 4);
        board.place(bishopTwo, 5, 4);
        board.place(bishopThr, 0, 0);
        board.place(bishopBlock, 1, 1);

        Move moveOne = new Move(4, 4, 5, 5);
        Move moveTwo = new Move(5, 4, 6, 6);
        Move moveThr = new Move(0, 0, 2, 2);
       // assertTrue(true);
        assertTrue(moveOne.execute(board));
        assertFalse(moveTwo.execute(board));
        assertFalse(moveThr.execute(board));
    }


}