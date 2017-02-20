package game;
import Pieces.*;
import javafx.scene.control.ToolBar;

import javax.swing.*;
import java.applet.Applet;
import java.awt.Button;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import static Pieces.Piece.Color.BLACK;
import static Pieces.Piece.Color.WHITE;

public class Chessboard extends Applet {
    ArrayList<Move> history = new ArrayList<Move>();

    private final JPanel chessBoard = new JPanel(new GridLayout(8,8,0,0));
    private final JToolBar toolGUI = new JToolBar("TaskBar", JToolBar.VERTICAL);
    private final JLabel playerTurn = new JLabel("White's turn.");

    JButton[][] board;

    int startX, startY;
    int currentTurn = -1;
    Move currentMove;

    Board game;
    boolean isStartingClick = true;
    boolean isWhiteTurn = true;

    public Chessboard(){
        initialize();
    }

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


        JButton undo = new JButton("Undo");
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undoOneMove();
            }
        });
        toolGUI.add(newGame);
        toolGUI.add(undo);
        toolGUI.add(playerTurn);
        toolGUI.setOrientation(JToolBar.HORIZONTAL);


        //chessBoard.setLayout(new GridLayout(10,10,0,0));
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
        setUpBoard();

        chessBoard.setPreferredSize(new Dimension(800,800));// changed it to preferredSize, Thanks!
    }



    private void undoOneMove(){
        if(history.size()==0) {
            System.out.println("Nothing to undo");
            return;
        }
        System.out.println("trying to undo");
        currentMove = history.get(currentTurn);
        history.remove(currentTurn);
        currentTurn--;


        Piece pieceRemovedLast = currentMove.getPieceRemoved();
        Piece revertPiece = game.remove(currentMove.endX(),currentMove.endY());
        if(pieceRemovedLast != null){
           game.place(pieceRemovedLast,currentMove.endX(),currentMove.endY());
        }
        game.changeTurn();
        game.place(revertPiece,currentMove.startX(),currentMove.startY());
        fullBoardUpdate();


    }

    /**
     * Handles the button click
     */
    public void buttonClick(int y, int x, Board board){
        System.out.println("x:" + x + "y:" + y);

        System.out.println("boolean:" + isStartingClick);
        if(isStartingClick){
            if(board.getPiece(x,y)!=null) {
                startX = x;
                startY = y;
                isStartingClick = !isStartingClick;
            }
        }
        else{
            currentMove = new Move(startX,startY,x,y);
            if(currentMove.execute(game)){
                history.add(currentMove);
                currentTurn++;
                boardUpdateHelper();
            }
            isStartingClick = !isStartingClick;

        }

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

    private void boardUpdateHelper(){
        fullBoardUpdateHelper(currentMove.startX,currentMove.startY);
        fullBoardUpdateHelper(currentMove.endX,currentMove.endY);
    }

    private void fullBoardUpdateHelper(int i, int j){
        Piece curPiece = game.getPiece(i,j);
        if(curPiece != null ) {
            board[i][j].setIcon(curPiece.getIcon(curPiece.getColor()));
        }
        else{
            board[i][j].setIcon(new ImageIcon());
        }
    }

    private void fullBoardUpdate(){

       for(int i = 0; i < 8;i++){
           for(int j = 0; j < 8; j++){
               fullBoardUpdateHelper(i,j);
           }
       }
    }

    private void setUpBoardTwo() {
        Piece whitePawn = new Pawn(WHITE);
        game.place(whitePawn,1,4);
        fullBoardUpdate();
    }
    /**
     * Sets up board to a regular chess game
     */
    private void setUpBoard(){


        Piece whitePawn = new Pawn(WHITE);
        Piece whiteRook = new Rook(WHITE);
        Piece whiteKnight = new Knight(WHITE);
        Piece whiteBishop = new Bishop(WHITE);
        Piece whiteKing = new King(WHITE);
        Piece whiteQueen = new Queen(WHITE);
        Piece blackPawn = new Pawn(BLACK);
        Piece blackRook = new Rook(BLACK);
        Piece blackKnight = new Knight(BLACK);
        Piece blackBishop = new Bishop(BLACK);
        Piece blackKing = new King(BLACK);
        Piece blackQueen = new Queen(BLACK);
        for(int i = 0; i<8;i++) {
            game.place(blackPawn,i,6);
        }
        for(int i = 0; i<8;i++) {
            game.place(whitePawn,i,1);
        }
        /*
        Icon temp = new ImageIcon();

       board[4][1]=temp;
        //board[4][3]=whitePawn;

        board[3][6]=temp;
        */
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

    public final JComponent getGui() {
        return chessBoard;
    }

    public final JComponent getToolGUI() { return toolGUI; }



        public static void main(String[] args){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Chessboard cg = new Chessboard();

                JFrame frame = new JFrame("Chessboard");

                frame.add(cg.getToolGUI(),BorderLayout.EAST);
                frame.add(cg.getGui(),BorderLayout.CENTER);


                // Ensures JVM closes aframeter frame(s) closed and
                // all non-daemon threads are frameinished
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                // See http://stackoverframelow.com/a/7143398/418556 for demo.
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