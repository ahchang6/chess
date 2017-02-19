package game;
import javax.swing.*;
import java.applet.Applet;
import java.awt.Button;
import java.awt.*;
import java.io.File;

public class Chessboard extends Applet {
    //private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private final JPanel chessBoard = new JPanel(new GridLayout(8,8,0,0));
    JButton[][] board;
    public Chessboard(){
        initialize();
    }

    public void initialize(){
        /*
        JFrame frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        */
        //frame.setSize(new Dimension(200,200));
        //frame.setSize(200,200);
        //JPanel chessBoard = new JPanel();

        //chessBoard.setLayout(new GridLayout(10,10,0,0));
        board = new JButton[8][8];
        //chessBoard.setLayout(new FlowLayout()); //Centered components
        //chessBoard.setSize(new Dimension(200,200));
        /*
        setLayout(new GridLayout(8,8));
        */
        for(int i = 0; i< 8 ; i++) {
            for (int j = 0; j < 8; j++) {
                int temp = i * 8 + j +1;

                //ImageIcon piece = new ImageIcon(this.getClass().getResource("assets/images/blackBishop.png"));
                //ImageIcon piece = new ImageIcon("/home/ahchang6/IdeaProjects/CS242HW0/Assignment1/Chess/src/assets/images/blackBishop.png");
                //Integer button = new Integer(temp);
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
                chessBoard.add(tempButton);
            }
        }
        setUpBoard();

        chessBoard.setPreferredSize(new Dimension(400,800));// changed it to preferredSize, Thanks!

        /*
        frame.getContentPane().add( chessBoard );// adding to content pane will work here. Please read the comment bellow.
        frame.pack();
*/





    }
    /**
     * Initializes the GUI of chess game
     */
    public void init() {

        initialize();

    }

    /**
     * Sets up board to a regular chess game
     */
    private void setUpBoard(){
        Icon whitePawn = new ImageIcon("/home/ahchang6/IdeaProjects/CS242HW0/Assignment1/Chess/src/assets/images/whitePawn.png");
        Icon whiteRook = new ImageIcon("/home/ahchang6/IdeaProjects/CS242HW0/Assignment1/Chess/src/assets/images/whiteRook.png");
        Icon whiteKnight = new ImageIcon("/home/ahchang6/IdeaProjects/CS242HW0/Assignment1/Chess/src/assets/images/whiteKnight.png");
        Icon whiteBishop = new ImageIcon("/home/ahchang6/IdeaProjects/CS242HW0/Assignment1/Chess/src/assets/images/whiteBishop.png");
        Icon whiteKing = new ImageIcon("/home/ahchang6/IdeaProjects/CS242HW0/Assignment1/Chess/src/assets/images/whiteKing.png");
        Icon whiteQueen = new ImageIcon("/home/ahchang6/IdeaProjects/CS242HW0/Assignment1/Chess/src/assets/images/whiteQueen.png");
        Icon blackPawn = new ImageIcon("/home/ahchang6/IdeaProjects/CS242HW0/Assignment1/Chess/src/assets/images/blackPawn.png");
        Icon blackRook = new ImageIcon("/home/ahchang6/IdeaProjects/CS242HW0/Assignment1/Chess/src/assets/images/blackRook.png");
        Icon blackKnight = new ImageIcon("/home/ahchang6/IdeaProjects/CS242HW0/Assignment1/Chess/src/assets/images/blackKnight.png");
        Icon blackBishop = new ImageIcon("/home/ahchang6/IdeaProjects/CS242HW0/Assignment1/Chess/src/assets/images/blackBishop.png");
        Icon blackKing = new ImageIcon("/home/ahchang6/IdeaProjects/CS242HW0/Assignment1/Chess/src/assets/images/blackKing.png");
        Icon blackQueen = new ImageIcon("/home/ahchang6/IdeaProjects/CS242HW0/Assignment1/Chess/src/assets/images/blackQueen.png");
        for(int i = 0; i<8;i++) {
            board[i][6].setIcon(blackPawn);
        }
        for(int i = 0; i<8;i++) {
            board[i][1].setIcon(whitePawn);
        }
        Icon temp = new ImageIcon();

       board[4][1].setIcon(temp);
        //board[4][3].setIcon(whitePawn);

        board[3][6].setIcon(temp);
        board[3][4].setIcon(whitePawn);

        board[2][0].setIcon(whiteRook);
        board[7][0].setIcon(whiteRook);
        //board[1][0].setIcon(whiteKnight);
        board[6][0].setIcon(whiteKnight);
       // board[2][0].setIcon(whiteBishop);
        board[5][0].setIcon(whiteBishop);
        board[4][0].setIcon(whiteQueen);
        board[1][0].setIcon(whiteKing);

        board[0][7].setIcon(blackRook);
        board[7][7].setIcon(blackRook);
        board[1][7].setIcon(blackPawn);
        board[6][7].setIcon(blackKnight);
        board[2][7].setIcon(blackBishop);
        board[5][7].setIcon(blackBishop);
        board[4][7].setIcon(blackQueen);
        board[3][7].setIcon(blackKing);

    }

    public void run(){

    }

    public final JComponent getGui() {
        return chessBoard;
    }

    public static void main(String[] args){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Chessboard cg = new Chessboard();

                JFrame frame = new JFrame("Chessboard");
                frame.add(cg.getGui());

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