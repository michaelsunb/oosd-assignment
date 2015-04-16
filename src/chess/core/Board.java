package chess.core;

public class Board /*extends Observable*/ implements IBoard {
	private int height;
	private int width;
	private Piece[] squares;
	
	public Board() {
		this.height = 6;
		this.width = 6;
	}
	
	public Board(int height,int width) {
		this.height = height;
		this.width = width;
	}
	
	/* (non-Javadoc)
	 * @see chess.core.IBoard#init()
	 */
	@Override
	public void init() {
		int boardSize = height * width;
		squares = new Piece[boardSize]; 
	}
	
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
	
	public int getHeight() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void changePieceLocation(int pos){
	}

	public boolean checkTargetSquareIfEmpty(int pos) {
		if(squares[pos] == null) {
			return true;
		} else {
			return false;
		}
	}
}
