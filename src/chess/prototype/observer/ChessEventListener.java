/*
 * Author: Sokun CHORN
 * Student Number: s3338291
 */
package chess.prototype.observer;

import chess.core.Piece;

public interface ChessEventListener {
	
	void notifyPieceMoved(PieceMovedEvent event);
	
	void notifyPieceCaptured(PieceCapturedEvent event);
	
	void notifyPieceJoint(PieceJoinEvent event);
	
	void notifyPieceSplit(PieceSplitEvent event);
}
