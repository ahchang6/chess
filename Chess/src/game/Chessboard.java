package game;
import Pieces.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Pieces.Piece.Color.BLACK;
import static Pieces.Piece.Color.WHITE;

public class Chessboard extends Applet {
    ArrayList<Move> history = new ArrayList<Move>();

    private final JPanel chessBoard = new JPanel(new GridLayout(8,8,0,0));
    private final JToolBar toolGUI = new JToolBar("TaskBar", JToolBar.VERTICAL);
    private final JLabel playerTurn = new JLabel("White's turn.");
    private final JLabel warning = new JLabel("");

    JButton[][] board;

    int startX, startY;
    int currentTurn = -1;
    Move currentMove;

    Board game;
    boolean isStartingClick = true;

    /**
     * Constructor for a Chessboard GUI
     */

    public Chessboard(){
        initialize();
    }

    /**
     * Initializes the board GUI and all the required material
     */

    public void initialize(){
        toolGUI.setMinimumSize(new Dimension(100,75));
        toolGUI.setPreferredSize(new Dimension(100,75));
        toolGUI.setMaximumSize(new Dimension(100,75));
        playerTurn.setMinimumSize(new Dimension(75,75));
        playerTurn.setPreferredSize(new Dimension(75,75));
        playerTurn.setMaximumSize(new Dimension(75,75));
        startX = -1;
        startY = -1;
        game = new Board();

        toolGUI.setFloatable(false);
        chessBoard.add(toolGUI, BorderLayout.EAST);


        toolGUI.setLayout(new GridLayout(12, 1));
        toolGUI.setFloatable(false);
        JButton newGame = new JButton("New Game");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearBoard();
                setUpBoard();
                if(!game.getWhoseTurn()){
                    game.changeTurn();
                }
            }
        });
        JButton newGamePlus = new JButton("Crazy Game");
        newGamePlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearBoard();
                setUpBoardTwo();
                if(!game.getWhoseTurn()){
                    game.changeTurn();
                }
            }
        });


        JButton undo = new JButton("Undo");
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undoOneMove();
            }
        });
        toolGUI.add(newGame);
        toolGUI.add(newGamePlus);
        toolGUI.add(undo);
        toolGUI.add(playerTurn);
        toolGUI.add(warning);
        toolGUI.setOrientation(JToolBar.HORIZONTAL);

        board = new JButton[8][8];

        for(int i = 0; i< 8 ; i++) {
            for (int j = 0; j < 8; j++) {
                int temp = i * 8 + j +1;

                JButton tempButton = new JButton();
                tempButton.setPreferredSize(new Dimension(40,40));
                board[j][i] = tempButton;
                if(i % 2 == 0) {
                    if (temp % 2== 0) {
                        tempButton.setBackground(Color.black);
                    } else {
                        tempButton.setBackground(Color.white);
                    }
                } else{

                    if (temp % 2== 1) {
                        tempButton.setBackground(Color.black);
                    } else {
                        tempButton.setBackground(Color.white);
                    }

                }
                board[j][i].setBorder(new LineBorder(Color.BLACK));
                int buttonI = i;
                int buttonJ = j;
                ActionListener test = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buttonClick(buttonI,buttonJ, game);
                    }
                };
                tempButton.addActionListener(test);
                chessBoard.add(tempButton);
            }
        }
        //setUpBoard();

        chessBoard.setPreferredSize(new Dimension(800,800));
    }

    /**
     * Undos the previous move, all the way to the beginning
     */

    private void undoOneMove(){
        if(history.size()==0) {
            System.out.println("Nothing to undo");
            return;
        }
        System.out.println("trying to undo");
        currentMove = history.get(currentTurn);
        history.remove(currentTurn);
        currentTurn--;
        int actionCode = currentMove.getActionCode();

        if(actionCode==-1) {

            Piece pieceRemovedLast = currentMove.getPieceRemoved();
            Piece revertPiece = game.remove(currentMove.endX(), currentMove.endY());
            if (pieceRemovedLast != null) {
                game.place(pieceRemovedLast, currentMove.endX(), currentMove.endY());
            }

            game.place(revertPiece, currentMove.startX(), currentMove.startY());

        }
        else if(actionCode == 0){
            game.place(game.remove(1, 0), 3, 0);
            game.place(game.remove(2, 0), 0, 0);
        }
        else if(actionCode == 1){
            game.place(game.remove(5, 0), 3, 0);
            game.place(game.remove(4, 0), 7, 0);
        }
        else if(actionCode == 3){
            game.place(game.remove(1, 7), 3, 7);
            game.place(game.remove(2, 7), 0, 7);
        }
        else if(actionCode == 4){
            game.place(game.remove(5, 7), 3, 7);
            game.place(game.remove(4, 7), 7, 7);
        }
        game.changeTurn();
        changeTurnText();
        fullBoardUpdate();
    }

    /**
     * Handles the highlighting
     * @param board of the current game
     */
    private void moveHandleHighlight( Board board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(isStartingClick && new Move(startX,startY,i,j).canExecute(board)){
                    this.board[i][j].setBorder(new LineBorder(Color.GREEN));
                }
                else if(!isStartingClick){
                    this.board[i][j].setBorder(new LineBorder(Color.BLACK));
                }
            }
        }
    }

    /**
     * Handles the button click
     */
    public void buttonClick(int y, int x, Board board){

        //System.out.println("x:" + x + "y:" + y);

        //System.out.println("boolean:" + isStartingClick);
        if(isStartingClick){
            Piece currentPiece = board.getPiece(x,y);
            if(currentPiece!=null) {
                if((currentPiece.getColor() == WHITE && board.getWhoseTurn()) || (currentPiece.getColor() == BLACK && !board.getWhoseTurn())) {
                    startX = x;
                    startY = y;
                    moveHandleHighlight(board);
                    isStartingClick = !isStartingClick;
                }
            }
        }
        else{

            if (startX == 3 && startY==0 && game.getPiece(startX, startY) instanceof King && game.getPiece(startX, startY).getColor() == WHITE) {
                System.out.println("entered here" + 1);
                if (x == 1 && y == 0) {
                    currentMove = new Move(0);
                }
            } else if (startX == 3 && startY==0 && game.getPiece(startX, startY) instanceof King && game.getPiece(startX, startY).getColor() == WHITE) {
                System.out.println("entered here" + 2);
                if (x == 6 && y == 0) {
                    currentMove = new Move(1);
                }
            }
            else if (startX == 3 && startY == 7 && game.getPiece(startX, startY) instanceof King && game.getPiece(startX, startY).getColor() == BLACK) {
                System.out.println("entered here" + 3);
                if (x == 1 && y == 7) {
                    currentMove = new Move(3);
                }
            } else if (startX == 3 && startY == 7 && game.getPiece(startX, startY) instanceof King && game.getPiece(startX, startY).getColor() == BLACK) {
                System.out.println("entered here" + 4);
                if (x == 6 && y == 7) {
                    currentMove = new Move(4);
                }
            }
            else {
                currentMove = new Move(startX, startY, x, y);
            }
            if(currentMove.execute(game)){
                System.out.println(currentMove.getActionCode());
                history.add(currentMove);
                currentTurn++;
                if(currentMove.getActionCode()!=-1){
                    fullBoardUpdate();
                }
                else {
                    boardUpdateHelper();
                }
                warning.setText("");
            }
            else{
                warning.setText("Illegal Move");
            }
            moveHandleHighlight(board);
            isStartingClick = !isStartingClick;

        }
        changeTurnText();
    }

    /**
     * changes player's turn on GUI
     */

    private void changeTurnText(){
        String whoseTurn = "";
        if(game.getWhoseTurn()) {
            whoseTurn += "White";
        }
        else{
            whoseTurn += "Black";
        }
        whoseTurn += "'s turn.";
        playerTurn.setText(whoseTurn);
    }
    /**
     * Initializes the GUI of chess game
     */
    public void init() {
        initialize();
    }
    private void clearBoard(){

        for(int i = 0; i < 8;i++){
            for(int j = 0; j < 8; j++){
                game.remove(i,j);
            }
        }
    }

    /**
     * updates the current move
     */

    private void boardUpdateHelper(){
        fullBoardUpdateHelper(currentMove.startX,currentMove.startY);
        fullBoardUpdateHelper(currentMove.endX,currentMove.endY);
    }

    /**
     * Helper function that updates the currect square
     * @param x coordinate of the square updating
     * @param y coordinate of the square updating
     */

    private void fullBoardUpdateHelper(int x, int y){
        Piece curPiece = game.getPiece(x,y);
        if(curPiece != null ) {
            board[x][y].setIcon(curPiece.getIcon(curPiece.getColor()));
        }
        else{
            board[x][y].setIcon(new ImageIcon());
        }
    }

    /**
     * An update that updates the visuals of the whole board
     */

    private void fullBoardUpdate(){

       for(int i = 0; i < 8;i++){
           for(int j = 0; j < 8; j++){
               fullBoardUpdateHelper(i,j);
           }
       }
    }

    /**
     * Sets up the normal board
     */

    private void setUpBoard(){
        Piece whitePawn = new Pawn(WHITE);
        Piece blackPawn = new Pawn(BLACK);
        for(int i = 0; i<8;i++) {
            game.place(blackPawn,i,6);
        }
        for(int i = 0; i<8;i++) {
            game.place(whitePawn,i,1);
        }
        setUpBoardHelper();
    }

    /**
     * Sets a game of the custom chess
     */

    private void setUpBoardTwo() {
        Piece whitePawn = new Pawn(WHITE);
        Piece blackPawn = new Pawn(BLACK);
        Piece whiteCannon = new Cannon(WHITE);
        Piece blackCannon = new Cannon(BLACK);
        Piece whiteJester = new Jester(WHITE);
        Piece blackJester = new Jester(BLACK);
        for(int i = 0; i<8;i++) {
            if(i == 3 || i == 4)
                game.place(blackPawn,i,5);
            game.place(blackPawn,i,6);
        }
        for(int i = 0; i<8;i++) {
            if(i == 3 || i == 4)
                game.place(whitePawn,i,2);
            game.place(whitePawn,i,1);
        }
        game.place(whiteCannon,3,1);
        game.place(blackCannon,3,6);
        game.place(whiteJester,4,1);
        game.place(blackJester,4,6);
        setUpBoardHelper();
    }
    /**
     * Sets up board to a regular chess game
     */
    private void setUpBoardHelper(){


        Piece whiteRook = new Rook(WHITE);
        Piece whiteKnight = new Knight(WHITE);
        Piece whiteBishop = new Bishop(WHITE);
        Piece whiteKing = new King(WHITE);
        Piece whiteQueen = new Queen(WHITE);
        Piece blackRook = new Rook(BLACK);
        Piece blackKnight = new Knight(BLACK);
        Piece blackBishop = new Bishop(BLACK);
        Piece blackKing = new King(BLACK);
        Piece blackQueen = new Queen(BLACK);

        game.place(whiteRook,0,0);
        game.place(whiteRook,7,0);
        game.place(whiteKnight,6,0);
        game.place(whiteKnight,1,0);
        game.place(whiteBishop,5,0);
        game.place(whiteBishop,2,0);
        game.place(whiteKing,3,0);
        game.place(whiteQueen,4,0);

        game.place(blackRook,0,7);
        game.place(blackRook,7,7);
        game.place(blackKnight,6,7);
        game.place(blackKnight,1,7);
        game.place(blackBishop,5,7);
        game.place(blackBishop,2,7);
        game.place(blackKing,3,7);
        game.place(blackQueen,4,7);
        fullBoardUpdate();
    }

    /**
     * Getter for the GUI commponent
     * @return the GUI component
     */
    public final JComponent getGui() {
        return chessBoard;
    }

    /**
     * Getter for the Jtoolbar component
     * @return the Jtoolbar component
     */
    public final JComponent getToolGUI() { return toolGUI; }


    /**
     * Runs the game loop
     * @param args arbitrary
     */

        public static void main(String[] args){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Chessboard cg = new Chessboard();

                JFrame frame = new JFrame("Chessboard");

                frame.add(cg.getToolGUI(),BorderLayout.EAST);
                frame.add(cg.getGui(),BorderLayout.CENTER);


                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationByPlatform(true);

                // ensures the framerame is the minimum size it needs to be
                // in order display the components within it
                frame.pack();
                // ensures the minimum size is enframeorced.
                frame.setMinimumSize(frame.getSize());
                frame.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }

}