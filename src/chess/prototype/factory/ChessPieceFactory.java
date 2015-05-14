package chess.prototype.factory;

import chess.core.Piece;
import chess.prototype.composite.Barrier;
import chess.prototype.composite.Bishop;
import chess.prototype.composite.Knight;
import chess.prototype.composite.Rook;

public class ChessPieceFactory {
	
	public Piece generateChessPiece(String pieceIndicator){

		switch(pieceIndicator){
			case "k": return(new Knight());
			case "r": return(new Rook());
			case "b": return(new Bishop());
			case "x": return(new Barrier());
			case "s": return null;
			default: return null;
		}
	}
}
