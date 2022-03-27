package mazegame.item;

public class Item {
	
	protected int value;
	protected boolean sellability;
	
	public Item(int value) {
		this.value = value;
		this.sellability = true;
	}
	
	public boolean canSell(){
		return this.sellability;
	}
	
	public void switchSellability() {
		this.sellability = !(this.sellability);
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void setValue(int newValue) {
		this.value = newValue;
	}
	
	public String toString() {
		if (this.sellability == true)
				return "Cet objet n'est pas vendable mais il vaut " + this.value ;
		return "Cet objet peut se vendre et vaut " + this.value;
	}
	
	public boolean equals(Object o) {
		if (o instanceof Item) {
			Item other = ((Item) o);
			return this.value == other.value
					&& this.sellability == other.sellability;		
		}
		return false;
		}
	
}

