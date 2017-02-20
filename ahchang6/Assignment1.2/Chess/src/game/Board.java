package game;
import Pieces.King;
import Pieces.Piece;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;

public class Board{

	int width, height;
	Piece[][] board;
	//2D matrix of if pieces in a place has moved
	boolean[][] hasMoved;
	//location of Black and  White King respectively
	int BKingx,BKingy;
	int WKingx,WKingy;

	boolean isWhiteTurn = true;


	/**
	 *
	 * Default Constructor, default size of regular chess board: 8x8
	 */

	public Board(){
		this.width = 8;
		this.height = 8;

		board = new Piece[8][8];
		for(int i = 0;i<height;i++)
			for(int j = 0;j<width;j++)
				board[i][j]=null;

		hasMoved = new boolean[width][height];
		for(int i = 0;i<height;i++)
			for(int j = 0;j<width;j++)
				hasMoved[i][j]=false;
	}


	/**
	 *
	 * Constructor that can construct boards of different sizes
	 * @param width
	 * @param height
	 */
	public Board(int width, int height){

		this.width = width;
		this.height = height;

		board = new Piece[width][height];
		for(int i = 0;i<height;i++)
			for(int j = 0;j<width;j++)
				board[i][j]=null;

		hasMoved = new boolean[width][height];
		for(int i = 0;i<height;i++)
			for(int j = 0;j<width;j++)
				hasMoved[i][j]=false;
	}

	/**
	 *
	 * Returns the piece at given the square
	 * @param x of the piece
	 * @param y of the piece
	 * @return the Piece object at the given square
	 */

	public Piece getPiece(int x, int y){
		return board[x][y];		
	}

	/**
	 * Removes piece at the given square
	 * @param x of the piece
	 * @param y of the piece
	 * @return the Piece object removed at the given square
	 */

	public Piece remove(int x, int y){
		if(board[x][y] == null)
			return null;

		Piece returnPiece = board[x][y];
		board[x][y] = null;
		hasMoved[x][y] = true;
		return returnPiece;

	}

	/**
	 *
	 * Places a piece at the given square
	 * @param piece object that will be placed
	 * @param x coordinate that will be placed
	 * @param y coordinate that will be placed
	 */

	public void place(Piece piece, int x, int y){
		remove(x,y);
		board[x][y] = piece;

	}

	/**
	 * Getter function for width
	 *
	 * @return Width of the board
	 */

	public int getWidth(){

		return width;
	}

	/**
	 * Getter function for height
	 *
	 * @return Height of the board
	 */

	public int getHeight(){

		return height;
	}

	/**
	 * Getter function for if it's white's turn
	 *
	 * @return true if white's turn, false for black
	 */

	public boolean getWhoseTurn(){
		return isWhiteTurn;
	}

	/**
	 * Changes the turn to opposite player
	 */

	public void changeTurn(){
		isWhiteTurn = !isWhiteTurn;
	}

	private ArrayList<Piece> checkPiecesChecking(int x, int y, Piece.Color color){
		ArrayList<Piece> piecesChecking = new ArrayList<Piece>();

		for(int i = 0 ; i< width ; i++) {
			for (int j = 0; j < height; j++) {
				if (board[i][j] == null)
					continue;
				if(board[i][j].getColor()==color)
					continue;
				if(board[i][j] instanceof King && color == board[i][j].getColor()) {
					continue;
				}
				if (board[i][j].canCapture(new Move(i,j,x,y), this) && board[i][j].getColor() != color) {
					System.out.println("That would put your king in check");
					piecesChecking.add(board[i][j]);
				}

			}
		}
		return piecesChecking;


	}

	/**
	 *
	 * Check if King is in check, given that x and y are where the king is
	 * @param x coordinate that is being checked
	 * @param y coordinate that is being checked
	 * @param color of side being checked
	 * @return true if in check, false otherwise
	 */

	public boolean checkCheck(int x, int y, Piece.Color color){
		/*
		for(int i = 0 ; i< width ; i++) {
			for (int j = 0; j < height; j++) {
				if (board[i][j] == null)
					continue;
				if(board[i][j].getColor()==color)
					continue;
				if (board[i][j].canCapture(new Move(i,j,x,y), this) && board[i][j].getColor() != color) {
					System.out.println("That would put your king in check");
					return true;
				}

			}
		}
		*/
		if(checkPiecesChecking(x,y,color).size()>0)
			return true;
		return false;
	}

	//TODO Finish for regular game

	/**
	 *	Check if King is in Checkmate
	 * @param x location of King
	 * @param y location of King
	 * @param color of King
	 * @return
	 */

	public boolean checkCheckMate(int x, int y, Piece.Color color){
		Move[] checkSurrounding = new Move[8];

		checkSurrounding[0] = new Move(x,y,x+1,y);
		checkSurrounding[1] = new Move(x,y,x+1,y+1);
		checkSurrounding[2] = new Move(x,y,x+1,y-1);
		checkSurrounding[3]= new Move(x,y,x,y+1);
		checkSurrounding[4]= new Move(x,y,x,y-1);
		checkSurrounding[5]= new Move(x,y,x-1,y);
		checkSurrounding[6]= new Move(x,y,x-1,y+1);
		checkSurrounding[7] = new Move(x,y,x-1,y-1);

		for(int i = 0;i<8;i++){
			if(checkSurrounding[i].canExecute(this)){
				return false;
			}
		}
		ArrayList<Piece> piecesChecking = checkPiecesChecking(x,y,color);
		if(piecesChecking.size()>1)
			return true;
/*
		Piece pieceCheck = piecesChecking.get(0);


		if(pieceCheck.canBeBlocked(x,y,color,this)){

			return false;
		}
*/



		return true;
	}

	public boolean hasMoved(int x, int y){

		return hasMoved[x][y];
	}
/*
	public int moved(int x , int y){
		if(hasMoved(x,y))
			return 1;

		hasMoved[x][y] = true;
		return 0;
	}
*/
}
