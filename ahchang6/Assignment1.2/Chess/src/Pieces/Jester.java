package Pieces;

import game.Board;
import game.Move;

/**
 * Created by ahchang6 on 2/9/17.
 *
 * Moves as a King, however, when it captures a non-Pawn/King piece, it becomes that piece of your color
 */

public class Jester extends Piece {

    public Jester(Color color){
        super(color);
    }

    public boolean canMoveTo(Move move, Board board){
        if(Math.abs(move.endX()-move.startX())>1 || Math.abs(move.endY()-move.startY())>1)
            return false;
        return true;
    }

    public boolean canCapture(Move move, Board board){
        if(canMoveTo(move, board)) {
            Piece promote = board.getPiece(move.endX(), move.endY());
            Piece promotee;
            if(promote instanceof Knight)
                promotee = new Knight(color);
            else if(promote instanceof Rook)
                promotee = new Rook(color);
            else if(promote instanceof Bishop)
                promotee = new Bishop(color);
            else if(promote instanceof Queen)
                promotee = new Queen(color);
            else if(promote instanceof Cannon)
                promotee = new Cannon(color);
            else
                promotee = new Pawn(color);

            board.remove(move.startX(),move.startY());
            board.place(promotee,move.startX(),move.startY());
            return true;
        }
        return false;
    }

    public boolean blocked(Move move, Board board){
            return true;
    }

}

