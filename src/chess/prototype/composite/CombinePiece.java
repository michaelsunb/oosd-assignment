package chess.prototype.composite;

import java.util.*;
import chess.core.Piece;

public class CombinePiece extends Piece {
	private List<Piece> pieces = new ArrayList<Piece>();

	public boolean add(Piece piece) {
		for (Piece p : pieces) {
			if (p.getClass().equals(piece.getClass())) {
				return false;
			}
		}

		this.pieces.add(piece);
		this.score += piece.getScore();
		return true;
	}

	public Piece remove(Piece piece) {
		if (piece instanceof CombinePiece) {
			List<Piece> splitPieces =  ((CombinePiece)piece).getPieces();
			for(Piece p: splitPieces) {
				this.pieces.remove(p);
				this.score -= piece.getScore();	
			}
		} else {
			this.pieces.remove(piece);
			this.score -= piece.getScore();	
		}

		if (this.pieces.size() == 1) {
			return this.pieces.get(0);
		}
		
		return this;
	}

	@Override
	public int[] getMovablePositions(int currPos) {
		TreeSet<Integer> positions = new TreeSet<Integer>();

		for (Piece p : pieces) {
			for (int position : p.getMovablePositions(currPos)) {
				positions.add(position);
			}
		}

		int[] ret = new int[positions.size()];
		int i = 0;
		for (Integer pos : positions) {
			ret[i++] = pos;
		}

		return ret;
	}

	@Override
	public String getSymbol() {
		String symbols = "";
		for (Piece p : this.pieces) {
			symbols += p.getSymbol();
		}
		return symbols;
	}
	
	public List<Piece> getPieces() {
		return this.pieces;
	}
}
