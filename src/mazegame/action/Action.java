package mazegame.action;

public abstract class Action {

	/**
	 * Execute l'action courante
	 * 
	 * @param character le personnage qui execute l'action
	 */
	public abstract void run(Character character);
}
