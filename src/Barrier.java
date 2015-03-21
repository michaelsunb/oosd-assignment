
public class Barrier extends PieceDecorator {

	public Barrier(Board ogBoard) {
		super(ogBoard);
	}

	@Override
	public void draw() {
		System.out.print("*");
	}

	@Override
	public int positon() {
		return this.originalBoard.positon();
	}
}
