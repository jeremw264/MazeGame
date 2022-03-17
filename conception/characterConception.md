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
- Stocker ( li� � ramasser ? )
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

- Générisité entre chaque personage
- Code facilement testable
- Facilement modifiable

Premierement l'idéale serait de faire une classe abstraite pour les personnages, car comme on peut le voir ci-dessus les personnages on des actions similaire (bouger,discuter,stocker) puis chaque personnage héritera de cette classe et modifira certaine méthode ou en définira selon sa particularité, pour pouvoir facilement teste le code il serait très pratique de ne pas faire de rendu de trace en console mais de gérér cela dans la classe principale de notre jeu (Classe Game) même si elle n'est pas encore défini cela nous permettra d'obtenir un code facilement testable et stable la seule dificulter poura etre l'attende de saisie utilisateur.

#### Discution

Maintenant que nous savons comment représenter nos different personnage nous allons commencer à réfléchir à la premiere action la discuscion qui va etre un gros bout de notre implémentation.

Ici nous implémentons les actions avec les personnages car cela nous parait le plus simple car si on anticipe pas cela on pourrait avoir des problèmes ensuite.

Le premier problème va être de savoir comment stocker nos phrase, question-reponse ou autre, plusieur solution sont possible.

Utiliser un attribut dans chaque classe pour stocker tous nos phrase etc. Cela pourait rendre nos classe très longue et n'est pas forcement très pratique. Une autre solution serait d'utiliser une BDD mais uniquement pour stocké du texte ? C'est peu être un peu trop lourd. Ici nous avons choisie d'utiliser du JSON qui est une solution très pertinante dans notre cas car facile à comprendre, facile à utiliser, facile à parse , ... , facile pour nous au final.

Maintenant une discution c'est quoi ? des question, des réponse en fonction qui peuvent être correct ou non, juste des phrases sans réponse.

C'est plein de trucs au final.

On va donc fixer des contraintes:

- Plusieurs niveau de profondeurs
- Des réponse ou non
- Des question en fonction des réponses
- Different en fonction des personnages

C'est quand même un peu compliqué tous ca, on va tous gérer dans une classe ça sera plus simple non ? (OUI)

On va crée un nouveau type de donné Discussion (A bah ducoup oui ça parait logique) ou on va parse le json et le gérer. 

Pour la profondeur nous allons gérer ça a la manière d'une liste simplement chainer (Parceque les arbres en java c'est relou) chaque discution aura une réf vers un id si il y a une suite sinon elle aura une ref null, cela nous permettra de réutiliser une phase ou un question-reponse si c'est utile.

Ensuite si c'est une phase on aura juste pas de reponse dans l'element

On aura donc pour l'instant une JSON de cette forme

 ``` json

"Nom Perso" : {
	"talk": {
		id : {
			"content": "",
			"answer": ["rep1","rep2"],
			"correct": [],
			"next": refid
		},
		etc
	}
}

```

#### Achat-vente

#### Deplacement


   


