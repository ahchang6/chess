
package Pieces;

import game.Board;
import game.Move;

import javax.swing.*;


public class Rook extends Piece{

	/**
	 * Creates a Rook of the color provided, Rooks moves in only Horizontal or Vertical
	 * @param color of piece
	 */
	public Rook(Color color){
		super(color);
	}

	private boolean xOR(boolean logicOne, boolean logicTwo) {
		if(logicOne && logicTwo) return false;
		return logicOne || logicTwo;

	}

	public boolean canMoveTo(Move move, Board board){
		if(xOR( move.endX()-move.startX() == 0 ,move.endY() - move.startY()==0)){

			if(!blocked(move, board))
			return true;

		}
		return false;

	}

	public boolean canCapture(Move move, Board board){
		return canMoveTo(move, board);
	}

	// Sets up the beginning and end of path and iterates
	public boolean blocked(Move move, Board board){
		int pathSwitch = 0;
		int x = move.startX();
		int y = move.startY();
		int start; //x or y depending on the movement
		int end; //x or y depending on movement
		if(move.startX()!=move.endX()){
			if(move.startX()<move.endX()){
				pathSwitch = 0;
				start = move.startX();
				end = move.endX();
			}
			else{
				pathSwitch = 1;
				end = move.startX();
				start = move.endX();
			}
		}
		else if(move.startY()!=move.endY()){
			if(move.startY()<move.endY()){
				pathSwitch = 2;
				start = move.startY();
				end = move.endY();
			}
			else{
				pathSwitch = 3;
				end = move.startY();
				start = move.endY();
			}
		}
		else{ return true; }
		//check for everything in path but itself and the destination
		for(;start<end-1;start++){

			if(pathSwitch == 0)
				x++;
			else if(pathSwitch == 1)
				x--;
			else if(pathSwitch == 2)
				y++;
			else if(pathSwitch == 3)
				y--;
			else
				return true;

			if(board.getPiece(x,y)!=null){
				return true;
			}
		}
		return false;
	}

	public Icon getIcon(Color color){
		Icon icon;
		if(color == Color.WHITE){
			icon = new ImageIcon("src/game/images/whiteRook.png");
		}
		else{
			icon = new ImageIcon("src/game/images/blackRook.png");
		}
		return icon;
	}
}
