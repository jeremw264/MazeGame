package mazegame.character;

import mazegame.Map;

/**
 * Classe Npc.
 */
public abstract class Npc extends Character {

	/**
	 * Constructeur de l'objet Npc.
	 * 
	 * @param x   Position verticale de départ.
	 * @param y   Position horizontale de départ.
	 * @param map La carte sur laquelle le personnage non joueur ce déplace.
	 */
	public Npc(int x, int y, Map map) {
		super(x, y, map);
		// TODO Auto-generated constructor stub
	}

}
