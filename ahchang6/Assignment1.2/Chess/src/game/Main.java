package game;

import javax.swing.*;

/**
 * Created by ahchang6 on 2/13/17.
 */
public class Main {

    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Chessboard();
            }
        });
    }


}
