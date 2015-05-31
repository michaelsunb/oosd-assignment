package chess.mvc.views;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import chess.core.IBoard;
import chess.core.Piece;
import chess.mvc.controllers.GameController;
import chess.mvc.models.UpdateUIEvent;
import chess.prototype.dnd.PieceDropTarget;
import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.IObserver;


public class ChessboardViewPanel extends JPanel implements IObserver {
	private Square[] squares;
	private GameController actionHandler;

	/**
	 * @pre.condition: Input of UpdateUIEvent
	 * @post.condition: Redraws the chessboard
	 */
	@Override
	public void update(ChessEvent event) {
		if (event instanceof UpdateUIEvent) {
			this.redraw(((UpdateUIEvent)event).isRenew());
		}
	}

	/**
	 * @pre.condition: Instantiate the class with GameController
	 * @post.condition: Class is instantiated
	 */
	public ChessboardViewPanel(GameController handler) {
		this.actionHandler = handler;
	}

	/**
	 * @pre.condition: Game and Board object must have their properties set
	 * @post.condition: game board set up
	 */
	private void initialComponent() {
		IBoard board = actionHandler.getBoard();
		int w = board.getWidth();
		int h = board.getHeight();

		this.setLayout(new GridLayout(h, w));
		this.setBorder(new LineBorder(Color.BLACK));
		
		int boardSize = board.getHeight() * board.getWidth();
		this.squares = new Square[boardSize];
		
		for (int i = 0; i < boardSize; i++) {
			this.squares[i] = new Square(i);
			
			this.squares[i].addMouseListener(new MouseHandler(i, this.actionHandler));
			new PieceDropTarget(this.squares[i]);
		}
	}

	/**
	 * @pre.condition: Game board initialized
	 * 
	 * @post.condition: Redraw game board
	 */
	public void redraw(boolean clearView) {
		if (clearView) {
			this.removeAll();
			initialComponent();
		}

		int pos = 0;
		IBoard board = actionHandler.getBoard();
		for (Square square : this.squares) {
			
			int x = (pos % board.getWidth());
			int y = (pos / board.getHeight());

			if ((y % 2 == 1 && x % 2 == 1) || (y % 2 == 0 && x % 2 == 0)) {
				square.setBackground(Color.GRAY);
			} else {
				square.setBackground(Color.LIGHT_GRAY);
			}

			Piece piece = board.getPiece(pos);
			
			if (piece != null) {
				square.draw(piece);
			} else {
				square.empty();
			}
						
			this.add(square);
			pos++;
		}
		this.revalidate();
		this.repaint();
	}
	
	/*
	 * @pre.condition: this.components != null
	 */
	public void clearPath() {
		if (this.squares == null) return;
		
		this.redraw(false);
	}

	/**
	 * @pre.condition: Input integer position
	 * @post.condition: Sets the tile background to red
	 */
	public void markPath(int pos) {
		squares[pos].setBackground(Color.RED);
	}

	/*
	 * @pre.condition: i between 0 to this.components.length
	 * @post.condition: return a null or an instance of the component object
	 */
	public Square getSquare(int i) {
		if (i < 0 || i > this.squares.length) return null;
		return this.squares[i];
	}

	/**
	 * @pre.condition: Squares must be set
	 * @post.condition: returns the number of squares
	 */
	public int getSquareSize() {
		if(squares == null) return 0;
		
		return squares.length;
	}
	
	
}
