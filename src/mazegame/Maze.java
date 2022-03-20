package mazegame;

import java.util.List;

import mazegame.character.Character;
import mazegame.character.Player;
import mazegame.character.Pnj;
import mazegame.generation.GenerationAlgorithm;

public class Maze {
	private final int width;
	private final int height;

	private Grid grid;

	/**
	 * Constructeur de la classe Maze
	 * 
	 * @param height Hauteur du labyrinthe
	 * @param width  Largueur du labyrinthe
	 */
	public Maze(int width, int height, GenerationAlgorithm genAlgo, List<Pnj> pnjList,Player player) {
		this.width = width;
		this.height = height;
		this.grid = genAlgo.generation(width, height);
		this.setPnjs(pnjList);
		this.setPlayer(player);
	}

	/**
	 * Renvoie la largueur du labyrinthe.
	 * 
	 * @return la largueur du labyrinthe.
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Renvoie la hauteur du labyrinthe.
	 * 
	 * @return la hauteur du labyrinthe.
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Renvoie la liste des cellules du labyrinthe.
	 * 
	 * @return la liste des cellules du labyrinthe.
	 */
	public Grid getGrid() {
		return this.grid;
	}

	/**
	 * Place tous les personnage dans le labyrinthe.
	 * 
	 * @param characters la liste des personnages à placer
	 */
	private void setPnjs(List<Pnj> pnjList) {
		for (Pnj pnj : pnjList) {
			int x = pnj.getX();
			int y = pnj.getY();

			Cell cell = this.grid.getCell(x, y);
			// TODO: gerer cas ou x et y sup a width et heigh

			cell.setCharacter(pnj);
		}
	}
	
	private void setPlayer(Player player) {
		int x = player.getX();
		int y = player.getY();
		
		Cell cell = this.grid.getCell(x, y);
		cell.setCharacter(player);
		cell.setVisited();
	}


	/**
	 * Renvoie la représentation du labyrinte en chaine de caractère.
	 * 
	 * @return la représentation du labyrinte en chaine de caractère.
	 */
	public String toString() {
		
		StringBuilder mazeStringBuilder = new StringBuilder();
		
		// First Line

		for (int x = 0; x < this.width; x++) {
			mazeStringBuilder.append("+---");
		}

		mazeStringBuilder.append("+\n");

		// Other Line

		for (int y = 0; y < this.height; y++) {

			mazeStringBuilder.append("|");

			for (int x = 0; x < this.width; x++) {
				Cell cell = this.grid.getCell(x, y);
				if (cell.wallExist(Direction.E)) {

					if (cell.containPlayer()) {
						mazeStringBuilder.append(" H |");
					}
					else if (cell.isVisited()) {
						mazeStringBuilder.append("   |");
					} else {
						mazeStringBuilder.append(" # |");
					}
				} else {
					if (cell.containPlayer()) {
						mazeStringBuilder.append(" P  ");
					}
					else if (cell.isVisited()) {
						mazeStringBuilder.append("    ");
					} else {
						mazeStringBuilder.append(" #  ");
					}
				}
			}

			mazeStringBuilder.append("\n+");


			for (int x = 0; x < this.width; x++) {
				if (this.grid.getCell(x, y).wallExist(Direction.S)) {
					mazeStringBuilder.append("---+");
				} else {
					mazeStringBuilder.append("   +");
				}
			}

			mazeStringBuilder.append("\n");

		}
		
		

		return mazeStringBuilder.toString();
	}
}
