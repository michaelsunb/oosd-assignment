package chess.prototype.composite;

import java.util.*;

import chess.core.Piece;

public class CombinePiece extends Piece {
	private List<Piece> pieces = new ArrayList<Piece>();
	
	public void add(Piece piece) {
		// if type already added ignore
		for(Piece p: pieces) {
			if (p.getClass().equals(piece.getClass())) {
				return;
			}
		}
		
		this.pieces.add(piece);
		this.score += piece.getScore();
	}
	
	public void remove(Piece piece) {
		this.pieces.remove(piece);
		this.score -= piece.getScore();
	}
	
	@Override
	public int[] getMovablePositions(int currPos) {
		TreeSet<Integer> positions = new TreeSet<Integer>();

		for(Piece p: pieces) {
			for(int position: p.getMovablePositions(currPos)) {
				positions.add(position);
			}
		}
		
		int[] ret = new int[positions.size()];
		int i = 0;
		for(Integer pos: positions) {
			ret[i++] = pos;
		}
		
		return ret;
	}

}
