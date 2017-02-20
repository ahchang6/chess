package game;

import Pieces.King;
import Pieces.Piece;
import Pieces.Rook;

import java.util.ArrayList;

import static Pieces.Piece.Color.BLACK;
import static Pieces.Piece.Color.WHITE;



public class Move{
	int startX, startY, endX, endY;
    int actionCode;
    Piece lastPieceRemoved = null;

    /**
     * Default constructor for move
     */
    public Move(){
        actionCode = -1;
		startX = -1;
		startY = -1;
		endX = -1;
		endY = -1;
	}

	/**
	 * Constructor for special movements such as en passant and Castling
	 *
	 * 0 = White King-side Castling
	 * 1 = White Queen-side Castling
	 * 2 = White en passantA
	 * 3 = Black King-side Castling
	 * 4 = Black Queen-side Castling
	 * 5 = Black en passantA
     * @param actionCode used
	 */

	public Move(int actionCode){
        this.actionCode = actionCode;

    }

    /**
     * Constructor for the Move comamnd
     * @param startX of the command
     * @param startY of the command
     * @param endX of the command
     * @param endY of the command
     */

	public Move(int startX, int startY, int endX, int endY){
        actionCode = -1;
		this.startX = startX;
		this.startY = startY;
        this.endX = endX;
        this.endY = endY;

    }

    private boolean specialAction(Board board){
        if(actionCode == 0) {
            if (board.getPiece(4, 0) instanceof King && board.getPiece(7, 0) instanceof Rook) {
                if (board.getPiece(4, 0).getColor() == WHITE && board.getPiece(7, 0).getColor() == WHITE) {
                    if (board.getPiece(5, 0) == null && board.getPiece(6, 0) == null) {
                        if (!board.hasMoved(4, 0) && !board.hasMoved(7, 0)) {
                            if (!board.checkCheck(5, 0, WHITE) && !board.checkCheck(6, 0, WHITE)) {
                                board.place(board.remove(4, 0), 6, 0);
                                board.place(board.remove(7, 0), 5, 0);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        if(actionCode == 1) {
            if (board.getPiece(4, 0) instanceof King && board.getPiece(0, 0) instanceof Rook) {
                if(board.getPiece(4,0).getColor() == WHITE && board.getPiece(0,0).getColor() == WHITE){
                    if(board.getPiece(1,0) == null && board.getPiece(2,0) == null && board.getPiece(3,0) == null){
                        if (!board.hasMoved(4, 0) && !board.hasMoved(0, 0)) {
                            if (!board.checkCheck(1, 0, WHITE) && !board.checkCheck(2, 0, WHITE) && !board.checkCheck(3, 0, WHITE)) {
                                board.place(board.remove(4, 0), 2, 0);
                                board.place(board.remove(0, 0), 3, 0);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        if(actionCode == 3) {
            if (board.getPiece(4, 7) instanceof King && board.getPiece(7, 7) instanceof Rook) {
                if(board.getPiece(4,7).getColor() == BLACK && board.getPiece(7,7).getColor() == BLACK) {
                    if (board.getPiece(5, 7) == null && board.getPiece(6, 7) == null) {
                        if (!board.hasMoved(4, 7) && !board.hasMoved(7, 7)) {
                            if (!board.checkCheck(5, 7, BLACK) && !board.checkCheck(6, 7, BLACK)) {
                                board.place(board.remove(4, 7), 6, 7);
                                board.place(board.remove(7, 7), 5, 7);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        if(actionCode == 4) {
            if (board.getPiece(4, 7) instanceof King && board.getPiece(0, 7) instanceof Rook) {
                if(board.getPiece(4,7).getColor() == BLACK && board.getPiece(0,7).getColor() == BLACK) {
                    if (board.getPiece(1, 7) == null && board.getPiece(2, 7) == null && board.getPiece(3, 7) == null) {
                        if (!board.hasMoved(4, 7) && !board.hasMoved(0, 7)) {
                            if (!board.checkCheck(1, 7, BLACK) && !board.checkCheck(2, 7, BLACK) && !board.checkCheck(3, 7, BLACK)) {
                                board.place(board.remove(4, 7), 2, 7);
                                board.place(board.remove(0, 7), 3, 7);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean canExecute(Board board){
        if (endX < 0 || endY < 0) {
            System.out.println("Out of bounds");
            return false;
        }

        if (endX > board.getWidth() - 1 || endY > board.getHeight()) {
            System.out.println("Out of bounds");
            return false;
        }

        Piece piece = board.getPiece(startX, startY);

        if (board.getPiece(endX, endY) != null) {
            if (piece.getColor() == board.getPiece(endX, endY).getColor()) {
                return false;
            }

            // TODO place removed piece somewhere so it knows what is taken for record keeping
            if (!board.getPiece(startX, startY).canCapture(this, board)) {
                return false;
            }
        } else {
            if (!piece.canMoveTo(this, board)) {
                return false;
            }
        }
       return true;
    }

    /**
     * Executes the action stored in Move
     *
     * @return whether the action was successfully executed or not
     */

	public boolean execute(Board board) {
        //edge case checking
        if (startX == -1 || startY == -1 || endX == -1 || endY == -1)
            return false;

        if (actionCode != -1) {
            return specialAction(board);
        }

        if (endX < 0 || endY < 0) {
            System.out.println("Out of bounds");
            return false;
        }

        if (endX > board.getWidth() - 1 || endY > board.getHeight()) {
            System.out.println("Out of bounds");
            return false;
        }

        Piece piece = board.getPiece(startX, startY);
        if(board.getWhoseTurn() && piece.getColor() == BLACK) {
            System.out.println("It is not Black's turn!");
            return false;
        }

        if(!board.getWhoseTurn() && piece.getColor() == WHITE) {
            System.out.println("It is not White's turn!");
            return false;
        }

        //edge case checking end

        Piece pieceRemoved = null;
        if(piece == null)
            return false;

        if (board.getPiece(endX, endY) != null) {
            System.out.println(piece.getColor());
            if (piece.getColor() == board.getPiece(endX, endY).getColor()) {
                return false;
            }

            // TODO place removed piece somewhere so it knows what is taken for record keeping
            if (!board.getPiece(startX, startY).canCapture(this, board)) {
                return false;
            }
            pieceRemoved = board.remove(endX, endY);
        } else {
            if (!piece.canMoveTo(this, board)) {
                return false;
            }
        }

        Piece placeHolder = board.remove(startX, startY);
        board.place(piece , endX, endY);
        //check if moving piece will cause to be in check
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                if (board.getPiece(i, j) != null)
                    if (board.getPiece(i, j) instanceof King && board.getPiece(i, j).getColor().equals(placeHolder.getColor()))
                        if (board.checkCheck(i, j, placeHolder.getColor())) {
                            board.remove(endX,endY);
                            board.place(placeHolder, startX, startY);
                            if(pieceRemoved != null){
                                board.place(pieceRemoved, endX,endY);
                            }
                            return false;
                        }
            }
        }
        lastPieceRemoved = pieceRemoved;
        board.place(placeHolder, endX, endY);
        board.changeTurn();
        /*
        if(XofKing!=-1) {
            if (placeHolder.getColor() == BLACK){
                board.checkCheckMate(XofKing, YofKing, WHITE);
            }else {
                board.checkCheckMate(XofKing, YofKing, BLACK);
            }
        }
        */
        return true;
    }

    public Piece getPieceRemoved(){
        return lastPieceRemoved;
    }

    public int startX(){
		return startX;
	}
	public int startY(){
		return startY;
	}
	public int endX(){
		return endX;
	}
	public int endY(){
		return endY;
	}

}
