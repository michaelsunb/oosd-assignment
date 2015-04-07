package chess.mvc.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.*;

/*
 * Adapted from http://stackoverflow.com/questions/21077322/create-a-chess-board-with-jpanel
 * TODO: inject board data into the view
 */
public class ChessboardViewPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3243088743876785303L;
	
	private JButton[][] chessBoardSquares = new JButton[6][6];
	private static Font font = new Font("Sans-Serif", Font.PLAIN, 50);
	
	public ChessboardViewPanel()
	{
		/*
		 * Using board data to generate the chessboard
		 */
		Insets buttonMargin = new Insets(0,0,0,0);
		
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
//                ImageIcon icon = new ImageIcon(
//                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
//                
//                b.setIcon(icon);
                
                /*
                 * Fill the chess board using unicode char http://stackoverflow.com/questions/18686199/fill-unicode-characters-in-labels
                 */
//                String[] pieces = {
//                        "\u2654","\u2655","\u2656","\u2657","\u2658","\u2659",
//                        "\u265A","\u265B","\u265C","\u265D","\u265E","\u265F"
//                    };
               
                if ((jj % 2 == 1 && ii % 2 == 1)
                        //) {
                        || (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.BLACK);
                }
                
                chessBoardSquares[jj][ii] = b;
            }
        }
	}
}
