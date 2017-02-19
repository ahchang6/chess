package game;
import Pieces.King;
import Pieces.Piece;

import java.util.AbstractCollection;
import java.util.ArrayList;

public class Player {
        /**
         * Player class, currently not fully utilized
         * stores the color of the player
         */
        protected Piece.Color color;
        protected ArrayList<Piece> list;

        /**
         * creates a player based on color passed in
         * @param color of created player
         */

        public Player(Piece.Color color){
                list = new ArrayList<Piece>();
                this.color = color;
        }

        /**
         * gets the color of the player
         * @return color of the player
         */

        public Piece.Color getColor(){
                return color;
        }

        /**
         * Get the list of pieces that the player still has
         * @return ArrayList containing the pieces that player has
         */
        public ArrayList<Piece> getList(){
                return list;
        }

        /**
         * Adds a piece to the list of pieces it is
         * @param piece to be added
         * @return true if successful, false otherwise
         */

        public boolean addToList(Piece piece){
                list.add(piece);
                return true;
        }


}
