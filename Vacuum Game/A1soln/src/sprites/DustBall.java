package sprites;

public class DustBall extends Dirt implements Moveable<Sprite> {

	public DustBall(char symbol,
			int row, int column, int value) {
		super(symbol, row, column, value);
	}
	
	public void moveTo(int row2, int column2) {		
		this.row = row2;
		this.column = column2;
		
				
	}
	

}
