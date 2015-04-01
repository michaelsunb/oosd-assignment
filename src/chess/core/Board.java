package chess.core;

import java.util.Observable;
	
public class Board /*extends Observable*/ implements IBoard {
	private int height;
	private int width;
	private Piece[] squares;
	
	public Board() {
		this.height = 6;
		this.width = 6;
		
		this.init();
	}
	
	public Board(int height,int width) {
		this.height = height;
		this.width = width;
		
		this.init();
	}
	
	/* (non-Javadoc)
	 * @see chess.core.IBoard#init()
	 */
	@Override
	public void init() {
		int boardSize = height * width;
		squares = new Piece[boardSize]; 
	}
	
	public void move(int fromPos, int toPos) {
		// update position
		
		// 
//		this.positionsChanged();
	}
	
	/*
	private void positionsChanged() {
		setChanged();
		notifyObservers();
	}
*/
	/* (non-Javadoc)
	 * @see chess.core.IBoard#getPieces()
	 */
	@Override
	public Piece[] getPieces() {
		return squares;
	}
	
	/* (non-Javadoc)
	 * @see chess.core.IBoard#getPiece(int)
	 */
	@Override
	public Piece getPiece(int pos) {
		/*
		 * TODO: validation design-by-contract
		 */
		return squares[pos];
	}
}
