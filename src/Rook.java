
public class Rook extends PieceDecorator implements Movable {

	public Rook(Board ogBoard) {
		super(ogBoard);
	}

	@Override
	public void draw() {
		System.out.print("R");
	}

	@Override
	public int positon() {
		return this.originalBoard.positon();
	}

	@Override
	public void moves() {
		// TODO Move up one
	}

}
