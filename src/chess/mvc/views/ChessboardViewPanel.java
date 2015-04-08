package chess.mvc.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.LineBorder;

/*
 * Adapted from http://stackoverflow.com/questions/21077322/create-a-chess-board-with-jpanel
 * TODO: inject board data into the view
 */
public class ChessboardViewPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3243088743876785303L;
	private static Font font = new Font("Sans-Serif", Font.PLAIN, 50);
	
	public ChessboardViewPanel()
	{
		/*
		 * TODO: get it from board object
		 */
		this.setLayout(new GridLayout(6, 6));
		
		this.setBorder(new LineBorder(Color.BLACK));
		
		renderBoard(6, 6);
        
        // fill the chess board
	}

	private void renderBoard(int rows, int cols) {
		Insets buttonMargin = new Insets(0,0,0,0);
		
        for (int ii = 0; ii < rows; ii++) {
            for (int jj = 0; jj < cols; jj++) {
                JButton b = new JButton();
                b.setFont(font);
                b.setMargin(buttonMargin);
               
                if ((jj % 2 == 1 && ii % 2 == 1)
                        //) {
                        || (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.BLACK);
                }
                this.add(b);
            }
        }
	}
}
