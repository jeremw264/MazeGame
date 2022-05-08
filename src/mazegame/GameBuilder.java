package mazegame;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import mazegame.challenge.Challenge;
import mazegame.character.Character;
import mazegame.character.Npc;
import mazegame.character.Player;
import mazegame.exception.GameBuilderException;
import mazegame.generation.GenerationAlgorithm;
import mazegame.item.GoldCoin;
import mazegame.item.Item;

/**
 * Classe GameBuilder
 * 
 * Construit le jeu.
 * 
 */
public class GameBuilder {

	/**
	 * Ressource utiliser pour Conception de la classe
	 * 
	 * Type Générique :
	 * https://www.fil.univ-lille.fr/~quinton/coo/cours/generics.pdf Création des
	 * instance : https://www.jmdoudoux.fr/java/dej/chap-introspection.htm
	 * 
	 */

	// La carte du jeu.
	private Map map;

	// La liste des Challenges.
	private List<Challenge> listOfChallenges;

	// La liste des classe Npc à crée dans le jeu
	private List<Class<? extends Npc>> npcsClasses;

	// La liste des classe Npc à crée dans le jeu
	private List<Class<? extends Item>> itemsClasses;

	// La liste de tous les personnage.
	private List<Character> characters;

	// La liste des objets dans le jeu.
	private List<Item> items;

	// Le joueur.
	private Player player;

	// La classe du player
	private Class<? extends Player> playerClass;

	// La quete du jeu.
	private Quest quest;

	// Le nombre de personnages à mettre dans le jeu

	private int nbOfCharacters;

	// Le nombre d'objets à mettre dans le jeu
	private int nbOfItems;

	public GameBuilder() {
		this.listOfChallenges = new LinkedList<Challenge>();
		this.characters = new LinkedList<>();
		this.items = new LinkedList<>();
		this.npcsClasses = new LinkedList<>();
		this.itemsClasses = new LinkedList<>();
	}

	/**
	 * Crée la carte du jeu avec l'algorithme passé en paramètre.
	 * 
	 * @param width     La largueur de la carte.
	 * @param height    La hauteur de la carte.
	 * @param algorithm L'algorithme à utilisé pour la génération.
	 * @return L'instance courante du GameBuilder
	 * 
	 * @see Lors de plusieurs appel, seul le dernier sera pris en compte.
	 */
	public GameBuilder setMap(int width, int height, GenerationAlgorithm algorithm) {

		this.nbOfCharacters = this.nbOfItems = (width * height / 2) / 2;

		this.map = algorithm.generation(width, height);
		return this;
	}

	/**
	 * Ajoute un Challenge dans le jeu.
	 * 
	 * @param challenge Le challenge à ajouter.
	 * @return L'instance courante du GameBuilder
	 * 
	 */
	public GameBuilder setChallenge(Challenge challenge) {
		this.listOfChallenges.add(challenge);
		return this;
	}

	/**
	 * Ajoute un joueur dans le jeu.
	 * 
	 * @param <T>
	 * @param className La classe du joueur à ajouter.
	 * @return L'instance courante du GameBuilder
	 * 
	 * @see Lors de plusieurs appel, seul le dernier sera pris en compte.
	 */
	public GameBuilder setPlayer(Class<? extends Player> className) {
		this.playerClass = className;

		return this;
	}

	/**
	 * Ajoute un type de Npc qui sera présent dans le jeu.
	 * 
	 * @param npcClass La classe du Npc choisie.
	 * @return L'instance courante du GameBuilder
	 */
	public GameBuilder setNpcClass(Class<? extends Npc> npcClass) {

		// Si la classe est déja présente on ne l'ajoute pas , car sinon on n'aura pas
		// les bonne proportion dans le jeu.
		if (!this.npcsClasses.contains(npcClass)) {
			this.npcsClasses.add(npcClass);
		}

		return this;
	}

	/**
	 * Ajoute un type d'Item qui sera présent dans le jeu.
	 * 
	 * @param itemClass La classe de l'Item choisie.
	 * @return L'instance courante du GameBuilder
	 */
	public GameBuilder setItemClass(Class<? extends Item> itemClass) {

		if (!this.itemsClasses.contains(itemClass)) {
			this.itemsClasses.add(itemClass);
		}

		return this;
	}

	/**
	 * Construit une instance du jeu et la renvoie.
	 * 
	 * @return Le jeu créé.
	 * @throws GameBuilderException Si il y a un problème pendant la construction.
	 */
	public Game build() throws GameBuilderException {

		this.verify();

		this.characters.add(this.player);

		if (this.npcsClasses.size() > 0) {
			this.generateNpc();
		}
		if (this.itemsClasses.size() > 0) {
			this.generateItem();
			this.dispatchItemInMap();
		}

		this.checksChallengesAreAchievable();
		
		for (int i = 0; i < 5; i++) {
			this.map.getCell(0, 0).addItem(new GoldCoin());
		}
		
		this.quest = new Quest(this.player, this.listOfChallenges);

		return new Game(this.map, this.characters, this.player, this.quest);
	}

	/**
	 * Génére tous les NPC avec les bonne proportion, les place et les ajoute dans
	 * la liste des personnages.
	 */
	private void generateNpc() {

		int numberOfEachNpc = (this.nbOfCharacters - 1) / this.npcsClasses.size();
		Random random = new Random();

		for (Class<? extends Character> npcClassName : this.npcsClasses) {
			for (int i = 0; i < numberOfEachNpc; i++) {

				Character npcCharacter = this.constructCharacter(npcClassName, random.nextInt(this.map.getWidth()),
						random.nextInt(this.map.getHeight()), this.map);

				this.characters.add(npcCharacter);

			}
		}
	}

	/**
	 * Génére tous les Item avec les bonne proportion, les place et les ajoute sur
	 * la carte.
	 */
	private void generateItem() {
		int numberOfEachItem = this.nbOfItems / this.itemsClasses.size();

		for (Class<? extends Item> itemClassName : this.itemsClasses) {
			for (int i = 0; i < numberOfEachItem; i++) {
				Item item = this.constructItem(itemClassName);

				items.add(item);
			}
		}

	}

	/**
	 * Renvoie une instance d'un perssonage grâce à la classe.
	 * 
	 * @param characterClass Le nom de la classe qui hérite de Character.
	 * @param x              La position horizontale.
	 * @param y              La position vertical.
	 * @param map            La carte où le personnage sera placé.
	 * @return L'instance du personnage, null si il y a un problème.
	 */
	private Character constructCharacter(Class<? extends Character> characterClass, int x, int y, Map map) {
		try {
			// Recupère le constructeur du personnage et crée une instance.
			return characterClass.getDeclaredConstructor(int.class, int.class, Map.class).newInstance(x, y, map);
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException
				| InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Renvoie une instance d'un objet du jeu grâce à la classe.
	 * 
	 * @param itemClass le nom de la classe qui hérite de item.
	 * @return L'instance de l'objet.
	 */
	private Item constructItem(Class<? extends Item> itemClass) {
		try {
			return itemClass.getDeclaredConstructor().newInstance();
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException
				| InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Répartie les objets sur la carte.
	 */
	private void dispatchItemInMap() {
		for (Item item : items) {
			Cell cell = this.getRandomCellInMap();
			cell.addItem(item);
		}
	}

	/**
	 * Renvoie une cellule aleatoire dans la carte.
	 * 
	 * @return
	 */
	private Cell getRandomCellInMap() {
		Random random = new Random();
		int x = random.nextInt(this.map.getWidth());
		int y = random.nextInt(this.map.getHeight());

		return this.map.getCell(x, y);
	}

	/**
	 * Vérifie si le jeu peu bien être construit.
	 * 
	 * @throws GameBuilderException Si le jeu ne peut pas être construit.
	 */
	private void verify() throws GameBuilderException {
		if (this.map == null) {
			throw new GameBuilderException("La carte n'a pas été ajouter au jeu ! (Utilisé setMap)");
		} else {
			this.player = (Player) this.constructCharacter(playerClass, 0, 0, this.map);
		}
		if (this.player == null) {
			throw new GameBuilderException("Aucun joueur n'a été ajouter au jeu ! (Utilisé setPlayer)");
		}
		if (this.listOfChallenges.isEmpty()) {
			throw new GameBuilderException("Aucun défis n'a été ajouter au jeu ! (Utilisé setChallenge)");
		}
	}

	/**
	 * Verifie que tous les challenges sont réalisable.
	 * 
	 * @return true si ils sont réalisable, false sinon.
	 * @throws GameBuilderException Déclanche une exception si un des challenges
	 *                              n'est pas réalisable.
	 */
	private void checksChallengesAreAchievable() throws GameBuilderException {
		for (Challenge challenge : this.listOfChallenges) {
			if (!challenge.isPossible(this.characters, this.items)) {
				throw new GameBuilderException("Le challenge " + challenge
						+ " n'est pas réalisable avec les personnages et les objets ajouté !");
			}
		}

	}
}
