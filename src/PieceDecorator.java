
public abstract class PieceDecorator implements Board {

	protected Board originalBoard;
	
	public PieceDecorator(Board ogBoard) {
		this.originalBoard = ogBoard;
	}
}
