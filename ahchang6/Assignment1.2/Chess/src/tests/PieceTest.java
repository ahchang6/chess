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
 */
public class PieceTest extends TestCase {

    public void testWhiteKingSideCastling() throws Exception {
        Piece kingOne = new King(WHITE);
        Piece rookOne = new Rook(WHITE);
        Piece bishop = new Bishop(BLACK);
        Board board = new Board();

        board.place(kingOne, 4, 0);
        board.place(rookOne, 7, 0);
        board.place(bishop, 5, 0);
        Move moveOne = new Move(0);

        assertFalse(moveOne.execute(board));
        board.remove(5, 0);
        assertTrue(moveOne.execute(board));


    }

    public void testWhiteQueenSideCastling() throws Exception {

        Piece kingOne = new King();
        Piece rookOne = new Rook(WHITE);
        Piece bishop = new Bishop(BLACK);
        Board board = new Board();

        board.place(kingOne, 4, 0);
        board.place(rookOne, 0, 0);
        board.place(bishop, 1, 0);
        Move moveOne = new Move(1);

        assertFalse(moveOne.execute(board));
        board.remove(1, 0);
        assertTrue(moveOne.execute(board));
    }

    public void testBlackKingSideCastling() throws Exception {

        Piece kingOne = new King(BLACK);
        Piece rookOne = new Rook(BLACK);
        Piece bishop = new Bishop(BLACK);
        Board board = new Board();

        board.place(kingOne, 4, 7);
        board.place(rookOne, 7, 7);
        board.place(bishop, 5, 7);
        Move moveOne = new Move(3);

        assertFalse(moveOne.execute(board));
        board.remove(5, 7);
        assertTrue(moveOne.execute(board));
    }

    public void testBlackQueenSideCastling() throws Exception {

        Piece kingOne = new King(BLACK);
        Piece rookOne = new Rook(BLACK);
        Piece bishop = new Bishop(BLACK);
        Board board = new Board();

        board.place(kingOne, 4, 7);
        board.place(rookOne, 0, 7);
        board.place(bishop, 1, 7);
        Move moveOne = new Move(4);

        assertFalse(moveOne.execute(board));
        board.remove(1, 7);
        assertTrue(moveOne.execute(board));
    }

    public void testSameColorSameSpace() throws Exception {

        Piece rookOne = new Rook(BLACK);
        Piece rookTwo = new Rook(BLACK);
        Piece rookThr = new Rook(WHITE);
        Board board = new Board();

        board.place(rookOne, 4, 4);
        board.place(rookTwo, 2, 4);

        Move moveOne = new Move(2, 4, 4, 4);
        assertFalse(moveOne.execute(board));
        board.remove(4, 4);
        board.place(rookThr, 4, 4);
        assertTrue(moveOne.execute(board));
    }
}
