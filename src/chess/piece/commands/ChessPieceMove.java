package chess.piece.commands;

import java.awt.Color;
import java.awt.Component;

import chess.core.Board;
import chess.core.Game;
import chess.core.Piece;
import chess.core.Player;
import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.ChessEventDispatcher;
import chess.prototype.observer.IObserver;
import chess.prototype.observer.PieceCapturedEvent;
import chess.prototype.observer.PieceJoinEvent;
import chess.prototype.observer.PieceMovedEvent;
import chess.prototype.observer.PieceMovesEvent;

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
		if(event instanceof PieceMovesEvent)
		{
			PieceMovesEvent tempEvent = (PieceMovesEvent)event;
			piece = tempEvent.getPiece();
			component = tempEvent.getComponent();
			currPos = tempEvent.getPosition();
			this.displayMoves();
		}
		else if(event instanceof PieceMovedEvent)
		{
			Board board = (Board) Game.getInstance().getBoardInstance();
			PieceMovedEvent tempEvent = (PieceMovedEvent)event;
			int targetPos = tempEvent.getNewPosition();

			if(piece != null) {
				int[] allMovableSquares = piece.getMovablePositions(currPos);
				
				for(int i :allMovableSquares) {
					if(i == targetPos) {
						Player currOwner = piece.getOwner();
						Player targetOwner = (board.getPiece(targetPos) != null) ? board.getPiece(targetPos).getOwner() : null;
						if(currOwner != null && targetOwner == null)
						{
							Game.getInstance().getBoardInstance().getPieces()[targetPos] = Game.getInstance().getBoardInstance().getPiece(currPos);
						}
						else if(currOwner != null && currOwner.getColour() != targetOwner.getColour())
						{
							Piece capturedPiece = board.getPiece(currPos);

			            	ChessEvent movePieceEvent = new PieceCapturedEvent(currOwner,capturedPiece,targetPos);
			    			ChessEventDispatcher.getInstance().fireEvent(movePieceEvent);
						}
						else if(currOwner != null && currOwner.getColour() == targetOwner.getColour())
						{
							Piece capturedPiece = board.getPiece(targetPos);

			            	ChessEvent movePieceEvent = new PieceJoinEvent(piece,capturedPiece,targetPos);
			    			ChessEventDispatcher.getInstance().fireEvent(movePieceEvent);
						}
						else
						{
							Game.getInstance().getBoardInstance().getPieces()[targetPos] = Game.getInstance().getBoardInstance().getPiece(currPos);
						}

						Game.getInstance().getBoardInstance().getPieces()[currPos] = null;
		            	Game.getInstance().swapPlayer();
		            	break;
					}
				}
			}
		}
	}
}
