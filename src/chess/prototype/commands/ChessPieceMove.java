/*
 * Author: Siang Ling
 * Number: s3295217
 */
package chess.prototype.commands;

import java.awt.*;
import chess.core.*;
import chess.prototype.observer.*;

public class ChessPieceMove implements IObserver{
	private int currPos;
	private Piece piece;
	private Component component;
	
	private void displayMoves() {
		if(piece != null) {
			int movablePos[] = piece.getMovablePositions(currPos);

			for(int square : movablePos) {
				Component movableSource = component.getParent().getComponent(square);
				movableSource.setBackground(Color.RED);
			}
		}
	}
	
	@Override
	public void update(ChessEvent event) {
		if(event instanceof PieceSelectedEvent)
		{
			PieceSelected((PieceSelectedEvent)event);
		}
		else if(event instanceof PieceMovedEvent)
		{
			PieceMoved((PieceMovedEvent)event);
		}
	}

	public void PieceMoved(PieceMovedEvent event) {
		// declare variables for short-handing
		Game game = Game.getInstance();
		Board board = (Board) game.getBoardInstance();
		ChessEventDispatcher eventMgr = ChessEventDispatcher.getInstance();
		
		int targetPos = event.getNewPosition();
		
		// can't move empty piece
		if(this.piece == null) return;
		
		event.setPreviousPiece(piece);
		
		int[] allMovableSquares = piece.getMovablePositions(currPos);
		
		for(int i :allMovableSquares) {
			if(i == targetPos) {
				ChessEvent pieceAction = event.returnAction();
				
				if(pieceAction == null) return;
				
				if(pieceAction instanceof PieceMovedEvent) {
					board.getPieces()[targetPos] = piece;
				} else {
	            	eventMgr.fireEvent(pieceAction);
				}

				board.getPieces()[currPos] = null;
	        	Game.getInstance().swapPlayer();
	        	break;
			}
		}
	}

	public void PieceSelected(PieceSelectedEvent event) {
		piece = event.getPiece();
		component = event.getComponent();
		currPos = event.getPosition();
		this.displayMoves();
	}
}
