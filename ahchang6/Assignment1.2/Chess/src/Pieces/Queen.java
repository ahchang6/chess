package Pieces;

import game.Board;
import game.Move;

import static Pieces.Piece.Color.BLACK;
import static Pieces.Piece.Color.WHITE;


public class Queen extends Piece{
	public Queen(Color color){
		super(color);
	}

    //Queen acts as either Rook or Bishop

	public boolean canMoveTo(Move move, Board board) {
       Piece bishop = new Bishop(color);
        Piece rook = new Rook(color);
        if(bishop.canMoveTo(move,board) || rook.canMoveTo(move,board))
            return true;

        return false;
	}

	public boolean canCapture(Move move, Board board){
        return canMoveTo(move, board);
    }

	//Queen acts as either Rook or Bishop

	public boolean blocked(Move move, Board board){
/*
		if(Math.abs(move.endX()-move.startX()) == Math.abs(move.endY()-move.startY())){
                System.out.print("Bishop");
			Bishop tempPiece = new Bishop(this.getColor());
			return tempPiece.blocked(move, board);
		}
		else{
            System.out.print("Rook");
			Rook tempPiece = new Rook(this.getColor());
			return tempPiece.blocked(move, board);

		}
		*/
return false;
	}

}

