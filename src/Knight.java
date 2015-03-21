
public class Knight extends PieceDecorator implements Movable {

	public Knight(Board ogBoard) {
		super(ogBoard);
	}

	@Override
	public void draw() {
		System.out.print("K");
	}

	@Override
	public int positon() {
		return this.originalBoard.positon();
	}

	@Override
	public void moves() {
		// TODO move 3x North or South or East or West and then 1x perpendicular to it
	}
}
