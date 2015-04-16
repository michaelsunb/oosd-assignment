package chess.mvc.controllers;

import chess.core.Game;
import chess.core.Piece;
import chess.core.Player;
import chess.prototype.observer.*;

public class GameController implements IObserver {
	
	private int prevPos;
	private Piece prevPiece;
	
	public GameController() {
		
	}

	private void moveAction(int next) {
		
	}
	
	@Override
	public void update(ChessEvent event) {
		if (event instanceof PieceMovesEvent) {
//			Player currentPlayer = Game.getInstance().getCurrentPlayer();
//			if(((PieceMovesEvent) event).getPiece().getOwner() == currentPlayer) {
				((PieceMovesEvent) event).displayMoves();
				prevPiece = ((PieceMovesEvent) event).getPiece();
				prevPos = ((PieceMovesEvent) event).getPosition();
//			}
		} else if (event instanceof PieceMovedEvent) {
			int next = ((PieceMovedEvent) event).getNewPosition();
			moveAction(next);
			System.out.println(event);
		}
	}
}
