package chess.core;

import java.io.Serializable;

/**
 * @see chess.prototype.decorator package
 */
public class Board implements IBoard, Serializable {
	private int height;
	private int width;
	private Piece[] squares;

	/**
	 * @pre.conditions: Default height and width
	 * @post.conditions: Board instance and list of pieces with 6x6
	 */
	public Board() {
		this.height = 6;
		this.width = 6;
	}

	/**
	 * @pre.conditions: Input integer value for height and width
	 * @post.conditions: Board instance and list of pieces with the size set by the user
	 */
	public Board(int height, int width) {
		this.height = height;
		this.width = width;
	}

	/**
	 * @pre.conditions: Values for width and height must have an integer value
	 * @post.conditions: Set an instance size of a list of Pieces
	 */
	@Override
	public void init() {
		int boardSize = height * width;
		squares = new Piece[boardSize];
	}

	/**
	 * @pre.conditions: Squares must be set through the init method
	 * @post.conditions: Returns a list of Pieces and null values
	 */
	@Override
	public Piece[] getPieces() {
		return squares;
	}

	/**
	 * @pre.conditions: Input value of an integer
	 * @post.conditions: Returns a piece at a set position according to the input
	 */
	@Override
	public Piece getPiece(int pos) {
		if (pos < 0 || pos > this.squares.length) return null;
		return squares[pos];
	}

	/**
	 * @pre.condition: Input value of a piece and integer position of of said piece
	 * @post.condition: Sets a piece to a position
	 */
	public void setPiece(int pos, Piece piece) {
		this.squares[pos]  = piece;
	}

	/**
	 * @pre.conditions: Must have a private height property with integer value
	 * @post.conditions: Return the height of the chess board
	 */
	@Override
	public int getHeight() {
		return this.height;
	}

	/**
	 * @pre.conditions: Must have a private width property with integer value
	 * @post.conditions: Return the height of the chess board
	 */
	@Override
	public int getWidth() {
		return this.width;
	}

	/**
	 * @pre.conditions: Input value of an integer position
	 * @post.conditions: Return true/false if square is empty
	 */
	public boolean isSqureEmpty(int pos) {
		return (squares[pos] == null) ? true : false;
	}

	/**
	 * @pre.conditions: Input value of Piece class
	 * @post.conditions: Return piece position or
	 * if it can't find returns -1
	 */
	@Override
	public int getPiecePosition(Piece currentPiece) {
		for(int i = 0; i < this.squares.length; i++) {
			if(this.squares[i] != null &&
				this.squares[i].equals(currentPiece)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * @pre.conditions: Input value of width and height
	 * @post.conditions: game restarted with board re-sized
	 */
	@Override
	public void reset(int height, int width) {
		this.width = width;
		this.height = height;
		
		// clear object
		int boardSize = height * width;
		squares = new Piece[boardSize];
	}
}
