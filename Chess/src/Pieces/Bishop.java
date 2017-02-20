
package Pieces;

import game.Board;
import game.Move;

import javax.swing.*;


public class Bishop extends Piece{
	public Bishop(Color color){
		super(color);
	}
	public boolean canMoveTo(Move move, Board board){
		int diffX = move.endX()-move.startX();
		int diffY = move.endY()-move.startY();

		//Bishop moves diagonally, hences, the change in X and Y will be the same
		if(Math.abs(diffX) != Math.abs(diffY)){
			return false;
		}
		//blocked takes more computation power

		if(blocked(move, board)){
			return false;
		}

		return true;

	}
	public boolean canCapture(Move move, Board board){

		return canMoveTo(move, board);
	}
	public boolean blocked(Move move, Board board){
		int x = move.startX();
		int y = move.startY();
		int dirX, dirY; //direction of incrementation
		if(move.endX() - move.startX() > 0){
			dirX = 1;
		}
		else{ dirX = -1; }

		if(move.endY() - move.startY() > 0){
			dirY = 1;
		}
		else{ dirY = -1;}
		//check for everything in path but itself and destination
		for(int i = move.startX(); i<move.endX()-1; i+=dirX){
			x+=dirX;
			y+=dirY;
			if(board.getPiece(x,y)!=null)
				return true;

		}
		return false;
	}
	public Icon getIcon(Color color){
		Icon icon;
		if(color == Color.WHITE){

			icon = new ImageIcon("src/game/images/whiteBishop.png");
		}
		else{
			icon = new ImageIcon("src/game/images/blackBishop.png");
		}
		return icon;
	}

}
