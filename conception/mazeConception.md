# Conception du plateau de jeu

[Retour](README.md)

## Enoncé

Comme la quête du héros est associée à une case particulière du labyrinbthe, il faut nécessairement que le labyrinthe
construit permette l’accès à ladite case. On mettra donc en oeuvre des algorithmes permettant de construire de
labyrinthes parfaits (2 algorithmes différents seront implémentés).

-> Dans cette énoncé nous pouvons déja apercevoir une premiere contrainte, le labyrinthe généré doit être dit "parfait".

---

## Contrainte du plateau

Génération :

-   le labyrinthe doit possèdé au minimum deux algorithmes de génération
-   Respecté le principe ouvert-fermé

---

## Conception Préliminaire

Dans un premier temps nous sommes posés plusieurs questions même si d'autres sont venus en effectuant des essais nous allons les énoncer ici.

### FAQ

**Pour commencer quelle structure de données utilisé pour représenter et stocker notre labyrinthe ?**

Ici nous avons choisi de créer une classe pour obtenir un nouveau type, si on regarde bien, un labyrinthe est une grille composée de cellule, cette même grille à des caractéristiques et contrainte précise afin d'être un labyrinthe et d'autre pour être un labyrinthe parfait.

> _Nous avons donc crée une nouvelle classe (Grid) qui contiendra l'ensemble de nos cases. Cette classe ne sera pas le labyrinthe lui-même, mais un type de structure de donné qui va rendre certaine action plus simple au niveau du code._

**Pourquoi nous n'utilisons pas une liste ou un tableau bi-dimensionnel ?**

Il est vrai qu'il est tout à fait possible d'utiliser un tableau bi-dimensionnel pour représenter cette grille, mais le fait de créer un nouveau type nous permet d'ajouter des méthodes dans une seule classe pour effectuer des actions sur cette grille, donc au niveau de l'organisation cette solution est pour nous la meilleure. Pour l'utilisation d'une liste notre nouveau type va être un peu comme une sur-couche, à l'intérieur les cellules vont être stocker dans une liste, car cette structure à plusieurs avantages par exemple, nos cellules on deux attribue pour leurs positions un attribue **y** et un attribue **x** puis nous avons une méthode equals qui va comparer ces attribues. Pour récupérer les cellules voisines par exemple, une méthode simple est de regarder si la cellule avec l'attribue plus ou moins un dans chaque direction existe, avec notre liste on crée seulement une nouvelle cellule avec plus ou moins un pour la position et faire un contain pour savoir si la cellule existe ou non alors que si on avait utilisé un tableau on aurait dû gérer une exception OutOfBound qui est un peu plus fastidieux.

Donc, en soit on aurait pu utiliser les listes et les tableaux dans notre grille, mais comme un bon programmeur est un bon feignant nous avons moins d'instruction à écrire pour un cas comme celui-ci.

> De plus, crée un type nous permet de respecter le principe ouvert-fermer, car comme nous sommes les concepteurs de ce nouveau type nous pouvons le faire évoluer simplement

> _Donc pour le moment nous savons comment stocker et représenter notre labyrinthe, mais une simple grille n'est pas un labyrinthe, car on ne respecte pas les contraintes d'un labyrinthe parfait_

**Comment transformer notre grille pour respecter les contraintes d'un labyrinthe parfait ?**

Pour cela la réponse est simple c'est nos algorithme de génération qui vont faire en sorte que la grille respecte les contrainte d'un labyrinthe parfait, ils vont modifier notre structure de donnée grille afin de faire en sorte que l'objet grille respecte les contraintes d'un labyrinthe parfait.

**Comment implémenter nos algorithme ?**

Comme on veut pouvoir implémenter plusieurs labyrinthes on va créer une classe par algorithme qui sera appeler dans une autre classe labyrinthe, mais il faut que nos algorithme soit générique pour pouvoir tous les utilisé sans modifier l'ensemble du code. C'est ici que l'on va utiliser les classe abstraite qui réponde parfaitement à notre problème, nous allons créer une classe abstraite qui va contenir une méthode génération qui sera utilisée à chaque initialisation de labyrinthe de plus cette classe abstraite pourra contenir des méthodes utiles qui ne sont utilisés que pour la génération.

**Mais dans tout ça pourquoi un objet Maze ?**

Comme un algorithme nous renvoie un objet grille qui respecte les contraintes d'un labyrinthe, pourquoi avoir un objet labyrinthe et ne pas juste garder cet objet Grille ? Avoir un objet labyrinthe peut avoir plusieurs utilités par exemple les méthodes qui définissent la case d'arriver peuvent être définis dans cette classe ou d'autre méthode qui sont relatives à un labyrinthe et non à un objet grille, il est vrai qu'on pourrait définir des méthodes type dans la classe abstraite des algorithmes, mais comme la génération peut nécessité beaucoup de méthode ou être complexe d'un point de vue organisationnel, il est plus simple de séparer tout cela.

> Et dans le pire des cas comme l'obtention du labyrinthe fonctionne un peu comme des couches rien ne nous empêche de supprimer cet objet et de faire quelque modification pour changer le fonctionnement

**Au final ça fonctionne comment ? (Version simple)**

Pour résumer nous avons quatre classes :
- Maze
- GenerationAlgorithm
- Grid
- Cell

Comme GenerationAlgorithm est une classe abstraite, il nous faut une classe concrète dans notre exemple on va l'appeler Algo.

Lorsqu'on construit une instance de Maze on va lui passer en paramètre une instance de Algo, une fois l'instance crée le labyrinthe est déjà généré.

Comment cela fonctionne ? Pour faire simple dans son constructeur la classe Maze va appeler la méthode génération de la classe Algo qui est une méthode hérité de la classe abstraite GenerationAlgorithm. Cette méthode lui renvoie une grille qui respecte les contraintes d'un labyrinthe, cette grille est stockée dans un attribue de l'instance de Maze.

Que se passe-t-il quand on appelle la fonction génération ? La méthode génération va créer une grille classique puis la modifier avant de la renvoyé tout simplement.

> Chaque case de la grille est représenté par une instance de Cell

---

### Algorithmes de génération de labyrinthe parfait

D'après nos recherche on dénombre environ 12 algorithmes classiques pour la génération de labyrinthes "parfaits".

-   [x] Kruskal
-   [x] Prim's
-   [x] Recursive Backtracker (ou Exploration exhaustive)
-   [ ] Aldous-Broder
-   [ ] Arbre en croissance
-   [ ] Hunt-and-Kill
-   [ ] Wilson's
-   [ ] Eller
-   [ ] Automaton cellulaire (facile)
-   [x] Division récursive (très facile)
-   [ ] Sidewinder (prévisible)
-   [x] Arbre binaire

Comme pour notre projet il n'est pas necessaire de regarder si un algorithme et meilleur qu'un autre nous allons juste comparer  
quelque un de ces algorithmes pour avoir un idée générale

#### Arbre binaire

Le principe de cette algorithme est très simple : pour chaque cellule de la grille, on efface au hasard un passage vers le sud ou l'est.

##### Exemple

![Exemple d'affichage de génération](img/maze/labyrinte_arbre_binaire.png "Arbre binaire génération")

##### Fonctionnement

```
	On parcourt une à une toutes les cellules de la grille
	Pour chaque cellule on detruit aléatoirement le mur Est ou Sud:
		- si on est sur le bord droit on detruit le mur sud
		- si on est sur le bord bas on detruit le mur est
		- si on est sur le coin bas droit on ne detruit aucun mur
```

##### Avantage

Très simple à réaliser, génére un labyrinthe parfait sans conserver le moindre état. Il peut construire le labyrinthe entier en ne regardant qu'une seule cellule à la fois.

##### Inconvénient

Trop simple à résoudre il suﬃt de faire des déplacement droite bas pour un algo sud-est ce algorithme minimum qui il n'y a pas de cul-de-sac (et il n'y en aura jamais)

#### Recursive Backtracker (ou Exploration exhaustive)

##### exemple

![Exemple d'affichage de génération](img/maze/labyrinthe_recursive_backtracker.png "Exploration exhaustive génération")

##### Fonctionnement

Ici on utilise la formulation itérative.

```
	tant que la pile n'est pas vide:
  		soit c la premiere cellule de la pile
  		on recherche les voisines non visité de c

  		si il y en a
     			soit cn une de ces voisines, choisi au hasard
      			on détruit le mur entre c et la cellule cn
      			on indique dans que c est visité
      			on ajoute cn a la pile
  		sinon
      			on retire le premier élément de pile
```

##### Avantage

La formulation récursive donne de très bons résultats pour des labyrinthes de taille modeste.

##### Inconvénient

Dès lors que l'on veut générer de grands labyrinthes (1000 x 1000, par exemple), le programme risque de se terminer brutalement si la taille de la pile est insuffisante.

#### Kruskal

##### exemple

![Exemple d'affichage de génération](img/maze/labyrinthe_kruskal.png "Kruskal")

##### Fonctionnement

```
	On crée une liste de toute les arêtes du labyrinthe qui ne sont pas au bord et on mélange la liste
	On crée un liste d'ensemble avec un cellule par ensemble

	On parcours l'ensemble des arretes:
		si les cellules des deux coté de l'arrete ne sont pas dans le même ensemble donc deux ensemble disjoint 
			on supprime l'arrete (le mur) et on fusionne les ensembles

```

> On peut imaginer que chaque ensemble est un arbre et qu'une fois l'algorithme terminé il doit n'y avoir qu'un seul arbre

##### Avantage

Kruskal est un algorithme amusant à mettre en œuvre et à observer.

##### Inconvénient

Il a tendance à créer beaucoup d'impasses courtes, ce qui n'est pas forcément très esthétique.

#### Division Récursive

Le principe de la division récursive peut être expliqué en 4 étapes :

-   Etape 1 : Commencer avec un terrain vierge / blanc
-   Etape 2 : Couper en deux le terrain via un mur horizontal ou vertical. Ajouter un point de passage à travers le mur.
-   Etape 3 : Répéter l'etape 2 de chaque côté du mur.
-   Etape 4 : Continuer récursivement, jusqu'à obtenir le labyrinthe désiré.

##### exemple

##### Fonctionnement

##### Avantage

##### Inconvénient

---

## Conception Détaillé

### Implémentation et UML

#### UML

Ici on ne va pas détailler le fonctionnement de chaque algorithme mais parlé de l'inplémentation général, car n'importe quelle algorithme peut être implémenter.

##### Labyrinthe

![UML Labyrinthe](img/uml/mazeUML.png "UML Maze")

##### Algorithme de génération

![UML Algorithme de génération](img/uml/generationAlgorithmUML.png "UML Generation Algorithm")

##### Grille

![UML Grille](img/uml/gridUML.png "UML Grid")

Grid est utiliser pour représenter le plateau de jeu il s'occupe des calcules et de la récupération dans le plan

##### Cellule

![UML Cellule](img/uml/cellUML.png "UML Cell")

L'objet Cell n'est pas très complexe il stocke des données par rapport à sa position dans la grille, un état pour savoir si la cellule est visité et des méthodes afin de modifié ses murs

---

#### Implémentation


Pour obtenir un labyrinthe parfait le principe est simple. Quand on crée une instance de Maze, on lui fourni trois paramètres :

- La largueur du labyrinthe.
- La hauteur du labyrinthe.
- Une instance de l'algorithme de Génération choisie.


Les seules contraintes à respecter sont :

- L'algorithme doit hérité de GenerationAlgorithm
- Les tests hérité de GenerationAlgorithm doivent tous être passé (car dans le cas contraire le labyrinthe ne sera pas parfait)

Le constructeur de Maze va appeler dans son constructeur la méthode génération de l'algorithme qui va renvoyer un objet grille qui respecte les contraintes pour être un labyrinthe, elle va donc stocker cette grille dans ses attributs. La méthode generate elle va créer un objet Grid qui sera vide ou pleine (càd les murs internes existent ou non) puis elle va modifier cette grille en fonction de l'algorithme avant de renvoyer Grid.


Plusieurs méthodes sont disponibles pour facilité la modification de cette grille, tel que récupéré une cellule par rapport à des coordonnées 2d ou obtenir la cellule dans une direction choisie par rapport à une autre, mais aussi des méthodes pour effacer un mur ou le crée, et des méthodes pour le traitement des cellules qui sont beaucoup utilisées pour la génération.

---

## Ajouter un algorithme comment faire ?

Pour créer un nouvel algorithme rien de bien compliquer :

Etape 1 : Crée une nouvelle classe dans le package mazegame.generation
Etape 2 : la classe crée doit hérité de la classe abstraite GenerationAlgorithm
Etape 3 : la méthode generation hérité doit renvoyer **la grille finale** (Bien évidemment une instance de Grid)
Etape 4 : Crée un nouveau Test Case pour vérifier que l'algorithme génére bien un labyrinthe parfait
Etape 5 : la classe de test doit hériter de GenerationAlgorithmTest
Etape 6 : Crée une méthode setUp( avec @Before ) qui affecte la grille généré par le nouvel algorithme dans this.grid

Enjoy !!

> Enjoy si les tests passent&#128513;

## Source

[Pour dénombrer les algorithmes](https://codenostra.com/fr/quest-ce-quun-bon-algorithme-pour-gnrer-un-labyrinthe.html)  
[Pour Kruskal](http://weblog.jamisbuck.org/2011/1/3/maze-generation-kruskal-s-algorithm)  
[Pour l'arbre binaire et affichage du labyrinthe](https://iut-info.univ-reims.fr/users/coutant/Creation_Resolution_Labyrinthe_Sujet.html)  
[Pour Récursive BackTracker et représentation d'un labyrinthe](https://fr.wikipedia.org/wiki/Mod%C3%A9lisation_math%C3%A9matique_de_labyrinthe)
