package chess.core;

public class Board implements IBoard {
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
	
	@Override
	public void init() {
		int boardSize = height * width;
		squares = new Piece[boardSize]; 
	}
	
	@Override
	public Piece[] getPieces() {
		return squares;
	}
	
	@Override
	public Piece getPiece(int pos) {
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

	public boolean isSqureEmpty(int pos) {
		return (squares[pos] == null) ? true : false;
	}
}
