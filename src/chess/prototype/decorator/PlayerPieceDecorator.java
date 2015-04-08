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

	@Override
	public void init() {
		// both players pieces have been setup
		if (this.board.getPieces()[0] != null && 
				this.board.getPieces()[30] != null){
			return;
		}
		
		if (this.board.getPieces()[0] == null) {
			this.setupFirstPlayerPieces();
		} else {
			this.setupSecondPlayerPieces();
		}
	}
	
	private void setupFirstPlayerPieces(){
		this.board.getPieces()[0] = new Rook();
		this.board.getPieces()[1] = new Knight();
		this.board.getPieces()[2] = new Bishop();
		this.board.getPieces()[3] = new Bishop();
		this.board.getPieces()[4] = new Knight();
		this.board.getPieces()[5] = new Rook();
		
		// set owner
		for(int i = 0; i < 5; i++) {
			this.board.getPieces()[i].setOwner(player);
		}
	}
	
	private void setupSecondPlayerPieces(){
		this.board.getPieces()[30] = new Rook();
		this.board.getPieces()[31] = new Knight();
		this.board.getPieces()[32] = new Bishop();
		this.board.getPieces()[33] = new Bishop();
		this.board.getPieces()[34] = new Knight();
		this.board.getPieces()[35] = new Rook();
		
		// set owner
		for(int i = 30; i < 35; i++) {
			this.board.getPieces()[i].setOwner(player);
		}
	}

	@Override
	public Piece[] getPieces() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Piece getPiece(int pos) {
		// TODO Auto-generated method stub
		return null;
	}
}
