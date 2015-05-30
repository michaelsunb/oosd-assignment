package chess.prototype.commands;

import chess.mvc.models.GameNewEvent;
import chess.mvc.views.GameOptionViewPanel;
import chess.prototype.observer.ChessEvent;

public class NewGameCommand extends CommandBase {

	@Override
	public void update(ChessEvent event) {
		if (!(event instanceof GameNewEvent)) return;
		GameNewEvent theEvent = (GameNewEvent)event;

		new GameOptionViewPanel(theEvent.getMainFame()).setVisible(true);

		theEvent.getMainFame().getChessBoardPane().redraw(true);
		theEvent.getMainFame().revalidate();
	}

}
