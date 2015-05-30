package chess.prototype.momento;

import chess.core.Game;
import chess.core.Piece;
import chess.mvc.models.UndoEvent;
import chess.mvc.models.UpdateUIEvent;
import chess.prototype.commands.CommandBase;
import chess.prototype.observer.ChessEvent;

public class UndoCommand extends CommandBase
{
	private Piece oldPiece;
	private Piece newPiece;

	private Boolean[] playerTurns;
	private Integer[] playerScore;
	private Integer[] numberOfMove;

	public void restoreFromMemento(GameMemento momento) {
		this.oldPosition = momento.getOldPosition();
		this.newPosition = momento.getNewPosition();
		this.oldPiece = momento.getOldPiece();
		this.newPiece = momento.getNewPiece();
		this.playerTurns = momento.getPlayerTurns();
		this.playerScore = momento.getPlayerScore();
		this.numberOfMove = momento.getNumberOfMove();
	}

	@Override
	public void update(ChessEvent event) {
		if (!(event instanceof UndoEvent)) return;

		update((UndoEvent)event);
	}

	public void update(UndoEvent event) {
		int num = event.getNumOfReverts();

		Game game = this.getGame();
		GameCaretaker caretaker = game.getCaretaker();
		for(int i = 0; i < num; i++) {
			this.restoreFromMemento(caretaker.getMemento());

			game.getPlayer(1).setTurn(playerTurns[0]);
			game.getPlayer(2).setTurn(playerTurns[1]);
			game.getPlayer(1).setScore(playerScore[0]);
			game.getPlayer(2).setScore(playerScore[1]);
			game.getPlayer(1).setNumberOfMove(numberOfMove[0]);
			game.getPlayer(2).setNumberOfMove(numberOfMove[1]);

			this.getBoard().setPiece(newPosition, this.newPiece);
			this.getBoard().setPiece(oldPosition, this.oldPiece);
		}

		this.eventMgr().fireEvent(new UpdateUIEvent());
	}
}