package chess.core;

/**
 * @see chess.prototype.decorator package
 */
public interface IBoard {
	/**
	 * @pre.conditions: Child class of IBoard needs to be instantiated
	 * @post.conditions: Initialize the board object
	 */
	public abstract void init();

	/**
	 * @pre.conditions: Squares must be set through the init method
	 * @post.conditions: Get all the pieces from the board object Return Piece[]
	 */
	public abstract Piece[] getPieces();

	/**
	 * @pre.conditions: Input value of an integer
	 * @post.conditions: Get piece object from a given position Return Piece object if 
	 * success or null
	 */
	public abstract Piece getPiece(int pos);

	/**
	 * @pre.conditions: Input value of an integer
	 * @post.conditions: Get piece object from a given position Return Piece object if 
	 * success or null
	 */
	public abstract void setPiece(int pos, Piece piece) ;

	/**
	 * @pre.conditions: Must have a private height property with integer value
	 * @post.conditions: Return the height of the chess board
	 */
	public abstract int getHeight();

	/**
	 * @pre.conditions: Must have a private width property with integer value
	 * @post.conditions: Return the height of the chess board
	 */
	public abstract int getWidth();

	/**
	 * @pre.conditions: Input value of an integer position
	 * @post.conditions: Return true/false if square is empty
	 */
	public abstract boolean isSqureEmpty(int pos);

	/**
	 * @pre.conditions: Input value of Piece class
	 * @post.conditions: Return piece position or
	 * if it can't find returns -1
	 */
	public abstract int getPiecePosition(Piece currentPiece);

	/**
	 * @pre.conditions: Input value of width and height
	 * @post.conditions: game restarted with board re-sized
	 */
	public abstract void reset(int height, int width);

}