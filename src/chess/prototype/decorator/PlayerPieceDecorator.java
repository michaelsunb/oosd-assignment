package chess.prototype.decorator;

import chess.core.IBoard;
import chess.core.Piece;
import chess.core.Player;
import chess.prototype.composite.*;

public class PlayerPieceDecorator extends BoardDecorator {
	private Player player;
	
	public PlayerPieceDecorator(IBoard board, Player player) {
		super(board);
		
		this.player = player;
	}

	/*
	 * Placing player pieces at the top or the bottom row of
	 * the chess board
	 * @precondition: a valid board object is given
	 * @precondition: a valid player is given
	 * @postcondition: pieces create and assign to given player
	 */
	@Override
	public void init() {
		// both players pieces have been setup
		if (this.board.getPieces()[0] != null && 
				this.board.getPieces()[30] != null){
			return;
		}
		
		if (this.board.getPieces()[0] == null) {
			this.arrangePlayerOnePieces();
		} else {
			this.arrangePlayerTwoPieces();
		}
	}
	
	private void arrangePlayerOnePieces(){
		arrangePieces(0);
		this.player.setTurn(true);
	}
	
	private void arrangePlayerTwoPieces(){
		arrangePieces(30);
	}

	/*
	 * Assume there is one way to arrange player pieces
	 * If future requirement changes, Strategy pattern
	 * can be use and invoke from this method
	 */
	private void arrangePieces(int offset) {
		this.board.getPieces()[offset + 0] = new Rook();
		this.board.getPieces()[offset + 1] = new Knight();
		this.board.getPieces()[offset + 2] = new Bishop();
		this.board.getPieces()[offset + 3] = new Bishop();
		this.board.getPieces()[offset + 4] = new Knight();
		this.board.getPieces()[offset + 5] = new Rook();
		
		// set owner
		for(int i = offset + 0; i <= offset + 5; i++) {
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
