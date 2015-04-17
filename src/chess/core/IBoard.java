package chess.core;

public interface IBoard {
	/*
	 * Initialize the board object
	 */
	public abstract void init();

	/*
	 * Get all the pieces from the board object
	 * Return Piece[]
	 */
	public abstract Piece[] getPieces();

	/*
	 * Get piece object from a given position
	 * Return Piece object if success or null
	 */
	public abstract Piece getPiece(int pos);
	
	/*
	 * Get the height of the chess board
	 */
	public int getHeight();
	
	/*
	 * Get the width of the chess board
	 */
	public int getWidth();
	
}