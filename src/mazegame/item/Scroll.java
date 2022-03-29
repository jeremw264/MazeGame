package mazegame.item;

public class Scroll extends Item{
	
	public Scroll() {
		this.value = 0;
		this.sellability = false;
	}
	
	public String toString() {
		return "Cet objet n'est pas vendable";
	}
}
