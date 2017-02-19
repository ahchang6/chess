package tests;

import Pieces.Knight;
import Pieces.Piece;
import game.Board;
import game.Move;
import junit.framework.TestCase;

import static Pieces.Piece.Color.WHITE;

/**
 * Created by ahchang6 on 2/5/17.
 */
public class KnightTest extends TestCase {
    public void testCanMoveTo() throws Exception {


        Piece knightOne = new Knight(WHITE);
        Piece knightTwo = new Knight(WHITE);
        Board board = new Board();

        board.place(knightOne, 4,4);
        Move moveOne = new Move(4,4,2,3);

        assertTrue(moveOne.execute(board));
        board.remove(2,3);

        board.place(knightTwo, 4,4);
        Move moveTwo = new Move(4,4,6,6);

        assertFalse(moveTwo.execute(board));
    }

    public void testBlocked() throws Exception {
        Piece knightOne = new Knight(WHITE);
        Board board = new Board();

        board.place(knightOne, 4,4);
        Move move = new Move(4,4,2,3);
        assertFalse(knightOne.blocked(move, board));

    }

}