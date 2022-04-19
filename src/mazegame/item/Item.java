package mazegame.item;

/**
 * Classe Item.
 * 
 * Représentation des objets.
 */
abstract public class Item {

	protected int value;
	protected boolean sellability;

	/**
	 * Constructeur de l'objet Item.
	 */
	public Item() {
		this.sellability = false;
	}

	/**
	 * Renvoie si l'objet peut être vendu.
	 * 
	 * @return true si il peut être vendu, false sinon.
	 */
	protected boolean canSell() {
		return this.sellability;
	}

	protected void switchSellability() {
		this.sellability = !(this.sellability);
	}

	/**
	 * Renvoie la valeur de vente de l'objet.
	 * 
	 * @return La valeur de vente de l'objet.
	 */
	protected int getValue() {
		return this.value;
	}

	/**
	 * Définit la valeur de vente de l'objet.
	 * 
	 * @param newValue La nouvelle valeur de vente de l'objet
	 */
	protected void setValue(int newValue) {
		this.value = newValue;
	}

	/**
	 * Renvoie si l'objet en paramètre est egal à l'objet courant.
	 * 
	 * @param L'objet à comparé.
	 * @return true si les deux objets sont égaux.
	 */
	public boolean equals(Object o) {
		if (o instanceof Item) {
			Item other = ((Item) o);
			return this.value == other.value && this.sellability == other.sellability;
		}
		return false;
	}
}
