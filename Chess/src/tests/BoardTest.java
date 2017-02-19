package tests;

import Pieces.Bishop;
import Pieces.King;
import Pieces.Piece;
import Pieces.Rook;
import game.Board;
import game.Move;
import junit.framework.TestCase;

import static Pieces.Piece.Color.BLACK;
import static Pieces.Piece.Color.WHITE;
/**
 * Created by ahchang6 on 2/6/17.
 * tests basic board creation, invalid moves and different size boards
 */

public class BoardTest extends TestCase {

    public void testBoards() throws Exception {
        Piece kingOne = new King(WHITE);
        Board board = new Board();
        Board boardBig = new Board(10,10);

        board.place(kingOne, 7,7);
        Move moveOne = new Move(7,7,8,8);

        assertFalse(moveOne.execute(board));

        boardBig.place(kingOne,7,7);

        assertTrue(moveOne.execute(boardBig));

        Move moveFail = new Move();
        assertFalse(moveFail.execute(board));


    }
    public void testBlackWin() throws Exception {
        Piece kingOne = new King(WHITE);
        Piece bishop = new Bishop(BLACK);

        Board board = new Board();

        board.place(kingOne, 7,7);

        board.place(bishop,5,5);
        Move moveOne = new Move(5,5,7,7);

        assertTrue(moveOne.execute(board));
    }
    public void testWhiteWin() throws Exception {
        Piece kingOne = new King(BLACK);
        Piece bishop = new Bishop(WHITE);

        Board board = new Board();

        board.place(kingOne, 7,7);

        board.place(bishop,5,5);
        Move moveOne = new Move(5,5,7,7);

        assertTrue(moveOne.execute(board));
    }

}
