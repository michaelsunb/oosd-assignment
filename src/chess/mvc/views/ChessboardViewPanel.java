package chess.mvc.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.LineBorder;

import chess.core.*;
import chess.prototype.composite.CombinePiece;

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
	private IBoard board;
	
	public ChessboardViewPanel()
	{
		board = Game.getInstance().getBoardInstance();
		/*
		 * TODO: get it from board object
		 */
		this.setLayout(new GridLayout(board.getWidth(), board.getHeight()));
		
		this.setBorder(new LineBorder(Color.BLACK));
		
		renderBoard(6, 6);
        
        // fill the chess board
	}

	private void renderBoard(int rows, int cols) {
		Insets buttonMargin = new Insets(0,0,0,0);
		int pos = 0;
        for (int ii = 0; ii < rows; ii++) {
            for (int jj = 0; jj < cols; jj++) {
            	
            	Piece p = this.board.getPiece(pos++);
            	
                JButton b = new JButton();
                b.setFont(font);
                b.setMargin(buttonMargin);
                
                if (p != null) {
	                /*
	                 * TODO: UI player piece has different color
	                 */
	                b.setText(p.getSymbol());
                }
          	
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
