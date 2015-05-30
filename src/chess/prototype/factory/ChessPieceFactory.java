<<<<<<< HEAD
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
=======
/*
 * Author: Siang Ling
 * Number: 3295217
 */
package chess.prototype.factory;

import chess.core.Piece;
import chess.prototype.composite.*;

public class ChessPieceFactory {
	
	public Piece generateChessPiece(char symbol){

		switch(symbol){
			case 'k': return new Knight();
			case 'r': return new Rook();
			case 'b': return new Bishop();
			case 'x': return new Barrier();
			case 'j': return new Jester();
			case 'm': return new Musician();
			case 's': return null;
			default: return null;
		}
	}
}

>>>>>>> origin/stabilize-part-2
