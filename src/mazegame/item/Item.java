package mazegame.item;

abstract public class Item {
	
	protected int value;
	protected boolean sellability;
	
	public Item() {}
	
	
	protected boolean canSell(){
		return this.sellability;
	}
	
	protected void switchSellability() {
		this.sellability = !(this.sellability);
	}
	
	protected int getValue() {
		return this.value;
	}
	
	protected void setValue(int newValue) {
		this.value = newValue;
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

