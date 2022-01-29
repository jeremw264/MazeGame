package mazegame.generation;

import mazegame.*;

public interface GenerationAlgorithm {

	/**
	 * Méthode de génération de labyrinthe.
	 * 
	 * @param maze Le labyrinthe à modifié.
	 * 
	 *             {@literal Le labyrinthe passé en paramètre doit être rempli d'objet Cell et avoir tout les mur existant.}
	 */
	public void generation(Maze maze);
}
