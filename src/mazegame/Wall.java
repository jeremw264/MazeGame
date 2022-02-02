package mazegame;

public class Wall {
	private Cell cell1;
	private Cell cell2;

	public Wall(Cell cell1, Cell cell2) {
		this.cell1 = cell1;
		this.cell2 = cell2;
	}

	public Cell getCell1() {
		return cell1;
	}

	public Cell getCell2() {
		return cell2;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "mur entre"+this.cell1+" et "+this.cell2;
	}
}
