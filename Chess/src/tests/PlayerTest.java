package tests;

import Pieces.King;
import Pieces.Piece;
import Pieces.Rook;
import game.Player;
import junit.framework.TestCase;

import java.util.ArrayList;

import static Pieces.Piece.Color.WHITE;

/**
 * Created by ahchang6 on 2/6/17.
 */
public class PlayerTest extends TestCase {
    public void testPlayer() throws Exception
    {


        Player player = new Player(WHITE);

        ArrayList<Piece> list = new ArrayList<Piece>();
        Piece rook = new Rook(WHITE);
        Piece king = new King(WHITE);
        list.add(rook);
        list.add(king);
        player.addToList(rook);
        player.addToList(king);
        assertTrue(player.getColor()==WHITE);
        assertTrue(player.getList().equals(list));
    }

}