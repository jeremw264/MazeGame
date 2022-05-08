package mazegame.item;

import mazegame.character.Character;

/**
 * Classe Item.
 *
 * Représentation des objets.
 */
abstract public class Item {

	protected int value;
	protected boolean sellability;
	protected boolean usability;

	/**
	 * Constructeur de l'objet Item.
	 */
	public Item(int value, boolean sellability, boolean usability) {
		this.value = value;
		this.sellability = sellability;
		this.usability = usability;
	}

	/**
	 * Renvoie si l'objet peut être vendu.
	 *
	 * @return true si il peut être vendu, false sinon.
	 */
	public boolean canSell() {
		return this.sellability;
	}

	protected void switchSellability() {
		this.sellability = !this.sellability;
	}

	public boolean isUsable() {
		return this.usability;
	}

	/**
	 * Renvoie la valeur de vente de l'objet.
	 *
	 * @return La valeur de vente de l'objet.
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * Définit la valeur de vente de l'objet.
	 *
	 * @param newValue La nouvelle valeur de vente de l'objet
	 */
	public void setValue(int newValue) {
		this.value = newValue;
	}

	abstract public void use(Character character);

	/**
	 * Renvoie si l'objet en paramètre est egal à l'objet courant.
	 *
	 * @param L'objet à comparé.
	 * @return true si les deux objets sont égaux.
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Item) {
			Item other = ((Item) o);
			return this.value == other.value && this.sellability == other.sellability;
		}
		return false;
	}
}
