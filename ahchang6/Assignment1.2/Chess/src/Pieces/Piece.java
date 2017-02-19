package Pieces;

import game.Board;
import game.Move;

import static Pieces.Piece.Color.WHITE;


/*
 * abstract class for any given chess piece
 */
public abstract class Piece{

	/**
	 *  Two colors
	 */
	public enum Color{ BLACK, WHITE }

	protected Color color;

	/**
	 *
	 * Constructor for the piece class, setting the color also
	 * @param color
	 */

	public Piece(Color color){
		this.color = color;
	}

	/**
	 * default color is set to White
	 */

	public Piece(){
		this.color = WHITE;
	}

	/**
	 * Gets color of the piece
	 *
	 * @return color enum of the piece
	 */

	public Color getColor(){
		return color;
	}


	/**
	 *
	 * Returns whether the given piece can move to the chosen square
	 *
 	 * @param move
	 * @param board
	 * @return true if piece can move there, false otherwise
	 */

	public abstract	boolean canMoveTo(Move move, Board board);

	/**
	 *
	 * @param move
	 * @param board
	 * @return whether it can capture
	 */

	public abstract boolean canCapture(Move move, Board board);


	/**
	 * returns whether the given piece will be hindered on the way to end square
	 *
	 * @param move
	 * @param board
	 * @return true if piece will be blocked, false otherwise
	 */

	public abstract boolean blocked(Move move, Board board);

	/*
	 * Check if there can be a piece that blocks this piece from checking
	 * @param XofKing
	 * @param YofKing
	 * @param color
	 * @param board
	 * @return True if a piece can block this piece from checking the king
	 */

	//public abstract boolean canBeBlocked(int XofKing, int YofKing, Color color, Board board);
}
