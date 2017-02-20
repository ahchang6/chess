package Pieces;

import game.Board;
import game.Move;

import javax.swing.*;

import static Pieces.Piece.Color.BLACK;
import static Pieces.Piece.Color.WHITE;

public class Pawn extends Piece{
	public Pawn(Color color){
		super(color);
	}
	public boolean canMoveTo(Move move, Board board){
		int direction = directionHelper(move); 

		if(direction == 0){
			return false;
		}
		if(color == BLACK && direction == 1)
			return false;
		if(color == WHITE && direction == -1)
			return false;
		if(color == BLACK && move.startY()==6 && direction == -1){
			if(!blocked(move, board) && move.endY()==4 && move.startX() == move.endX())
				return true;
		}
		if(color == WHITE && move.startY()==1 && direction == 1){
			if(!blocked(move, board) && move.endY()==3 && move.startX() == move.endX())
				return true;
		}
		if(direction == -1 && move.startX()==move.endX() && move.startY()-1!=move.endY())
			return false;

		if(direction == 1 && move.startX()==move.endX() && move.startY()+1!=move.endY())
			return false;
		if(blocked(move, board)){
			return false;
		}
		return true;
	}

	private int directionHelper(Move move){
		int direction = 0;
		if(move.startY() - move.endY() < 0)
			direction = 1;
		else if(move.startY() - move.endY() > 0)
			direction = -1;

		return direction;
	}

	public boolean canCapture(Move move, Board board){
		int direction = directionHelper(move);
		if(direction == -1 && Math.abs(move.endX() - move.startX()) == 1 && move.endY()+1 == move.startY())
			return true;
		else if(direction == 1 && Math.abs(move.endX() - move.startX()) == 1 && move.endY()-1 == move.startY())
			return true;
		else{
			return false;
		}
	}

	public boolean blocked(Move move, Board board){
		int direction = directionHelper(move);

		if(direction == -1 && board.getPiece(move.startX(),move.startY()-1)!= null)
			return true;
							
		if(direction == 1 && board.getPiece(move.startX(),move.startY()+1)!= null)
			return true;

		return false;
	}

	public Icon getIcon(Color color){
		Icon icon;
		if(color == Color.WHITE){
			icon = new ImageIcon("/home/ahchang6/IdeaProjects/CS242HW0/Assignment1/Chess/src/assets/images/whitePawn.png");
		}
		else{
			icon = new ImageIcon("/home/ahchang6/IdeaProjects/CS242HW0/Assignment1/Chess/src/assets/images/blackPawn.png");
		}
		return icon;
	}
}
