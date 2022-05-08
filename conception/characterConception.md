# Conception des personnage du jeu 

[Retour](README.md)

## Conception Préliminaire

### Personnage

Un personnage qu'il soit le heros ou bien un pnj doit pouvoir se deplacer sauf exception (voir ci-dessous) d'une cellule.
L'ensemble des personnages du jeu, que ce soit le heros ou les pnj ne peuvent faire qu'une seule action par tour.
C'est a  dire : se deplacer, rester sur place, interroger un autre personnage present sur la meme case, ramasser un objet, utiliser un objet possede, etc
L'achat et la vente d'objet sont considere comme l'action discuter lorsque cela se produit avec le marchand.
Tout les personnages doivent posseder l'action discuter afin d'interagir entre le joueur et les pnj.
Les pnj ainsi que le héros doivent pouvoir stocké des objets.

### Héros
Il doit pouvoir ramasser des objets et pouvoir les stocker.
Il doit pouvoir utiliser des objets stocké.
Il doit pouvoir accéder à son inventaire ( stockage d'objets )
Il doit pouvoir acheter et/ou vendre ses objets

		
#### Action 
- Se déplacer
- Discuter
- Ramasser
- Utiliser
- Inventaire
- Acheter / Vendre
			
### Pnj 1 (Vendor)
Le marchand est un pnj, se déplaçant dans le labyrinthe pendant le tour du Héros. 
Il peut y avoir un ou plusieurs marchand selon la taille du labyrinthe.
Le marchand en échange d'or peut vendre des items au Héro ou alors les lui acheter.
		
#### Action
- Déplacement (automatique)
- Achat / Vente (automatique)
- Stocker (automatique)

### PNJ 2 (Sphinx)
Le sphinx est un pnj apparaissant sur une cellule du labyrinthe.
Il ne peut pas se d�placer.
Le sphinx pose une question auquel le h�ros doit r�pondre, si celui-ci r�ponds correctement, en fonction de la difficulte, une recompense sera fournit
#### Action
- Discuter
- Stocker (extensions)

##### Extensions Sphinx
Il posséde différents thèmes de question en relation avec le lore du jeux. Dans chaque thèmes, plusieurs questions existe de différentes difficultés.
Le Héros pourra choisir le thèmes mais la question sera aléatoirement choisis dans la liste du thèmes.
Si la réponse est bonne, le sphinx fournira un objet ou un indice permettant au Héros d'avancer dans sa quête.
		

		
### PNJ 3 (Samaritan)
Le samaritain est un pnj qui ne se déplace pas.
Il peut en exister plusieurs dans un même labyrinthe. 
Ils sont présents pour aider le Héro a terminé sa quête ou lui en apprendre plus sur celle ci. 
Le samaritain ne demande rien en échange de ses informations
		
#### Action
- Discuter
- Stocker
		
### PNJ 4 (Imp)
Le diablotin est l'inverse du samaritain.
Il est là pour déstabiliser le Héros dans sa partie. 
Il fournis de faux indices.
Il fournira également ces indices gratuitement en se faisant passant pour un samaritain.
		
#### Action
- Discuter
- Stocker
		
### PNJ 5 (Compteur) (extensions)
Le compteur est un pnj se trouvant au début du labyrinthe et à différent point du labyrinthe. Celui ci compte l'aventure du Héros, au début du jeu, il explique en quoi consiste la quête principale ainsi que les règles.
		
## Conception Détaillé

### Implémentation 

Precedement nous avons défini les caractéristique de chaque personnage ainsi que leurs actions ou comportement car cela va de paire.

Maintenant comment l'implémenter puisque l'idéale serait de pouvoir appliqué plusieurs contrainte :

- Générisité entre les personages
- Code facilement testable
- Facilement modifiable

Ici nous pouvons distinguer deux type de personnage, le Hero qui va ce deplacer en fonction des saisie de l'utilisateur (joueur) et les autres personnages qui n'ont pas a attendre de saisie de l'utilisateur. Même si les deux type sont different il est utile de les distinger car même si leurs comportement est similaire, il va varier a cause de l'utilisateur.

Nous avons donc le Joueur qui controlle le Hero et les Pnj donc les autres personnage. Nous avons choisie de crée une classe abstraite Character pour representer les personnages.

Puis nos deux type de personnage qui sont également des personnages, Pnj et Player qui hérite de Character. Puis nous avons Hero qui hérite de Player et les autre de Pnj.

On obtient donc:

```
Character:
	- Player:
		- Hero
	- Pnj:
		- Imp
		- Samaritan
		- Sphinx
		- Vendor
	
```


   


