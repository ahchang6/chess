
package Pieces;

import game.Board;
import game.Move;

public class King extends Piece{
	public King(Color color){
		super(color);
	}
	public King(){
		super();
	}

	//king moves omnidirectionally one space
	public boolean canMoveTo(Move move, Board board){
		if(board.checkCheck(move.endX(),move.endY(), color))
			return false;
		if(Math.abs(move.endX()-move.startX())>1 || Math.abs(move.endY()-move.startY())>1)
			return false;
		return true;
	}

	public boolean canCapture(Move move, Board board){
		return canMoveTo(move, board);
	}

	//cannot be blocked
	public boolean blocked(Move move, Board board){
		return false;
	}

}

