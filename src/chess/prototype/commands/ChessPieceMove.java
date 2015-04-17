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
		
		int[] allMovableSquares = piece.getMovablePositions(currPos);
		
		for(int i :allMovableSquares) {
			if(i == targetPos) {
				Player currOwner = piece.getOwner();
				Player targetOwner = (board.getPiece(targetPos) != null) ? board.getPiece(targetPos).getOwner() : null;
				if(currOwner != null && targetOwner == null)
				{
					board.getPieces()[targetPos] = board.getPiece(currPos);
				}
				else if(currOwner != null && currOwner.getColour() != targetOwner.getColour())
				{
					Piece capturedPiece = board.getPiece(currPos);

	            	ChessEvent movePieceEvent = new PieceCapturedEvent(currOwner,capturedPiece,targetPos);
	            	eventMgr.fireEvent(movePieceEvent);
				}
				else if(currOwner != null && currOwner.getColour() == targetOwner.getColour())
				{
					Piece capturedPiece = board.getPiece(targetPos);

	            	ChessEvent movePieceEvent = new PieceJoinEvent(piece,capturedPiece,targetPos);
	            	eventMgr.fireEvent(movePieceEvent);
				}
				else
				{
					board.getPieces()[targetPos] = Game.getInstance().getBoardInstance().getPiece(currPos);
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
