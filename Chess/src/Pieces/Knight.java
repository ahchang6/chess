package Pieces;

import game.Board;
import game.Move;


public class Knight extends Piece{
	public Knight(Color color){
		super(color);
	}
	public boolean canMoveTo(Move move, Board board){
		int diffX = move.endX()-move.startX();
		int diffY = move.endY()-move.startY();


		if((Math.abs(diffX) == 1 && Math.abs(diffY) == 2) ||(Math.abs(diffX) == 2 && Math.abs(diffY) == 1)){
			return true;
		}
		return false;

	}

	/*
	 * Knights can't be blocked
	 */

	public boolean blocked(Move move, Board board){
		return false;
	}

	public boolean canCapture(Move move, Board board){
		return canMoveTo(move, board);
	}

}

