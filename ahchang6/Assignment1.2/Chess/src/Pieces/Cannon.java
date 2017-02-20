
package Pieces;

import game.Board;
import game.Move;

import javax.swing.*;

/**
 * Created by ahchang6 on 2/9/17.
 *
 * Cannon
 * When it does not capture a piece, it behaves as a rook
 *
 * It can only capture a piece when it mounts on a piece, meaning it requires a piece directly
 * in front of the direction of capture
 *
 */




public class Cannon extends Piece {

    public Cannon(Color color){
        super(color);
    }

    private boolean xOR(boolean logicOne, boolean logicTwo) {
        if(logicOne && logicTwo) return false;
        return logicOne || logicTwo;

    }

    public boolean canMoveTo(Move move, Board board){
        if(xOR( move.endX()-move.startX() == 0 ,move.endY() - move.startY()==0)){
            if(!blocked(move, board)) {
                return true;
            }
        }
        return false;

    }

    public boolean canCapture(Move move, Board board) {
        if (xOR(move.endX() - move.startX() == 0, move.endY() - move.startY() == 0)) {
            Move newMove;
            if (move.endX() > move.startX()) {
                newMove = new Move(move.startX() + 1, move.startY(), move.endX(), move.endY());
            }
            else if (move.endX() < move.startX()) {
                newMove = new Move(move.startX() - 1, move.startY(), move.endX(), move.endY());
            }
            else if (move.endY() > move.startY()) {
                newMove = new Move(move.startX() , move.startY() +1, move.endX(), move.endY());
            }
            else {
                newMove = new Move(move.startX() , move.startY() -1, move.endX(), move.endY());
            }
            if(board.getPiece(newMove.startX(),newMove.startY())==null)
                return false;

            return canMoveTo(newMove, board);
        }
        return false;
    }

    // Sets up the beginning and end of path and iterates
    public boolean blocked(Move move, Board board){
        Piece rook = new Rook(color);
        return rook.blocked(move,board);
    }
    public Icon getIcon(Color color){
        Icon icon;
        if(color == Color.WHITE){
            icon = new ImageIcon("src/game/images/whiteCannon.png");
        }
        else{
            icon = new ImageIcon("src/game/images/blackCannon.png");
        }
        return icon;
    }
}
