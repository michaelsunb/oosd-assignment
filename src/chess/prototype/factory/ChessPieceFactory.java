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

