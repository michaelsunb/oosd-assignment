package chess.prototype.commands;

import chess.core.Game;
import chess.core.IBoard;
import chess.core.Piece;
import chess.core.Player;
import chess.mvc.models.PieceMovedEvent;
import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.ChessEventDispatcher;
import chess.prototype.observer.IObserver;

public abstract class CommandBase implements IObserver {
	protected Game game;
	protected IBoard board;
	protected ChessEventDispatcher eventMgr;
	
	public CommandBase() {
		game = Game.getInstance();
		board = game.getBoardInstance();
		eventMgr = ChessEventDispatcher.getInstance();
	}
	
	@Override
	public abstract void update(ChessEvent event);

	// TODO extract to class or keep?
	protected int newPosition;
	protected int oldPosition;
	protected int[] allMovableSquares;
	protected Piece selectedPiece;
	protected Player selectedOwner;
	protected Player targetOwner;

	public boolean isSelectedPieceValid(PieceMovedEvent event) {
		newPosition = event.getNewPosition();
		oldPosition = event.getPreviousPosition();
		Piece selectedPiece = board.getPiece(oldPosition);

		if (selectedPiece == null || // selected piece is an empty square
				(selectedPiece != null && // selected piece has no owner
						selectedPiece.getOwner() == null))
			return false;
		
		selectedOwner = selectedPiece.getOwner();
		
		allMovableSquares = selectedPiece.getMovablePositions(oldPosition);

		targetOwner = (board.getPiece(newPosition) != null) ? board
				.getPiece(newPosition).getOwner() : null;
		
		return true;
	}
}
