package chess.prototype.commands;

import java.awt.*;

import chess.core.*;
import chess.mvc.models.*;
import chess.prototype.observer.*;

public class PieceSelectedCommand extends CommandBase {

	@Override
	public void update(ChessEvent event) {
		if (!((event instanceof PieceSelectedEvent))) return;
		
		renderPosibleMove((PieceSelectedEvent)event);
	}
	
	public void renderPosibleMove(PieceSelectedEvent event) {
		Piece piece = event.getPiece();
		Component component = event.getComponent();
		int currPos = event.getPosition();
		
		if (piece == null) return;
		
		int movablePos[] = piece.getMovablePositions(currPos);

		for (int square : movablePos) {
			Component movableSource = component.getParent().getComponent(square);
			movableSource.setBackground(Color.RED);
		}
	}
	
}
