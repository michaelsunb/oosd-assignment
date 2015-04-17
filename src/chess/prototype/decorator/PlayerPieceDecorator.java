package chess.prototype.decorator;

import java.awt.Color;

import chess.core.IBoard;
import chess.core.Piece;
import chess.core.Player;
import chess.prototype.composite.*;

public class PlayerPieceDecorator extends BoardDecorator {
	final private int NORTH_LOWER_BOUND = 0;
	final private int SOUTH_LOWER_BOUND = 30;

	final private int LOWER_OFFSET = 0;
	final private int UPPER_OFFSET = 5;

	final private int OFFSET_FIRST_ROOK = 0;
	final private int OFFSET_FIRST_KNIGHT = 1;
	final private int OFFSET_FIRST_BISHOP = 2;
	final private int OFFSET_SECOND_ROOK = 3;
	final private int OFFSET_SECOND_KNIGHT = 4;
	final private int OFFSET_SECOND_BISHOP = 5;

	private Player player;

	public PlayerPieceDecorator(IBoard board, Player player) {
		super(board);

		this.player = player;
	}

	/*
	 * Placing player pieces at the top or the bottom row of the chess board
	 * 
	 * @precondition: a valid board object is given
	 * 
	 * @precondition: a valid player is given
	 * 
	 * @postcondition: pieces create and assign to given player
	 */
	@Override
	public void init() {
		// both players pieces have been setup
		if (this.board.getPieces()[NORTH_LOWER_BOUND] != null
				&& this.board.getPieces()[SOUTH_LOWER_BOUND] != null) {
			return;
		}

		if (this.board.getPieces()[NORTH_LOWER_BOUND] == null) {
			this.arrangePlayerOnePieces();
		} else {
			this.arrangePlayerTwoPieces();
		}
	}

	private void arrangePlayerOnePieces() {
		this.player.setColour(Color.WHITE);
		this.player.setTurn(true);
		arrangePieces(NORTH_LOWER_BOUND);
	}

	private void arrangePlayerTwoPieces() {
		this.player.setColour(Color.BLACK);
		arrangePieces(SOUTH_LOWER_BOUND);
	}

	/*
	 * Assume there is one way to arrange player pieces If future requirement
	 * changes, Strategy pattern can be use and invoke from this method
	 */
	private void arrangePieces(int offset) {
		this.board.getPieces()[offset + OFFSET_FIRST_ROOK] = new Rook();
		this.board.getPieces()[offset + OFFSET_FIRST_KNIGHT] = new Knight();
		this.board.getPieces()[offset + OFFSET_FIRST_BISHOP] = new Bishop();
		this.board.getPieces()[offset + OFFSET_SECOND_BISHOP] = new Bishop();
		this.board.getPieces()[offset + OFFSET_SECOND_KNIGHT] = new Knight();
		this.board.getPieces()[offset + OFFSET_SECOND_ROOK] = new Rook();

		// set owner
		for (int i = offset + LOWER_OFFSET; i <= offset + UPPER_OFFSET; i++) {
			this.board.getPieces()[i].setOwner(player);
		}
	}

	@Override
	public Piece[] getPieces() {

		return this.board.getPieces();
	}

	@Override
	public Piece getPiece(int pos) {
		return this.board.getPiece(pos);
	}
}
