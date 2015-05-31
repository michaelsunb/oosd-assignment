package chess.prototype.commands;

import chess.mvc.models.GameNewEvent;
import chess.mvc.models.UpdateUIEvent;
import chess.mvc.views.GameOptionViewPanel;
import chess.prototype.observer.ChessEvent;

public class NewGameCommand extends CommandBase {

	/**
	 * @pre.condition: GameNewEvent must be fired
	 * @post.condition: Pop up a dialog box to set a new game
	 */
	@Override
	public void update(ChessEvent event) {
		if (!(event instanceof GameNewEvent)) return;
		GameNewEvent theEvent = (GameNewEvent)event;

		new GameOptionViewPanel(theEvent.getMainFame()).setVisible(true);

		this.eventMgr().fireEvent(new UpdateUIEvent(true));
	}
}
