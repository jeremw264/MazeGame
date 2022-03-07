# Conception des personnage du jeu 

[Retour](README.md)


##Personnage

Un personnage qu'il soit le h�ros ou bien un pnj doit pouvoir se d�placer sauf exception (voir ci-dessous) d'une cellule.
L'ensemble des personnages du jeu, que ce soit le h�ros ou les pnj ne peuvent faire qu'une seule action par tour.
C'est �  dire : se d�placer, rester sur place, interroger un autre personnage pr�sent sur la m�me case, ramasser un objet, utiliser un objet poss�d�.
L'achat et la vente d'objet sont consid�r� comme l'action discuter lorsque cela se produit avec le marchand.
Tout les personnages doivent poss�der l'action discuter afin d'int�ragir entre le joueur et les pnj.

##Héros
Il doit pouvoir ramasser des objets et pouvoir les stocker.
Il doit pouvoir utiliser des objets stocké.
Il doit pouvoir accéder à son inventaire ( stockage d'objets )
Il doit pouvoir acheter et/ou vendre ses objets

		
###Action 
- Se déplacer
- Discuter
- Ramasser
- Stocker ( li� � ramasser ? )
- Utiliser
- Inventaire
- Acheter / Vendre
			
##Pnj 1 (Marchand)
Le marchand est un pnj, se déplaçant dans le labyrinthe pendant le tour du Héros. 
Il peut y avoir un ou plusieurs marchand selon la taille du labyrinthe.
Le marchand en échange d'or peut vendre des items au Héro ou alors les lui acheter.
		
###Action
- Déplacement (automatique)
- Achat / Vente (automatique)
- Stocker (automatique)

##PNJ 2 (Sphinx)
Le sphinx est un pnj apparaissant sur une cellule du labyrinthe.
Il ne peut pas se d�placer.
Le sphinx pose une question auquel le h�ros doit r�pondre, si celui-ci r�ponds correctement la partie est gagn�e.

###Action
- Discuter
- Stocker (extensions)

####Extensions Sphinx
Il posséde différents thèmes de question en relation avec le lore du jeux. Dans chaque thèmes, plusieurs questions existe de différentes difficultés.
Le Héros pourra choisir le thèmes mais la question sera aléatoirement choisis dans la liste du thèmes.
Si la réponse est bonne, le sphinx fournira un objet ou un indice permettant au Héros d'avancer dans sa quête.
		

		
##PNJ 3 (Samaritain)
Le samaritain est un pnj qui ne se déplace pas.
Il peut en exister plusieurs dans un même labyrinthe. 
Ils sont présents pour aider le Héro a terminé sa quête ou lui en apprendre plus sur celle ci. 
Le samaritain ne demande rien en échange de ses informations
		
###Action
- Discuter
- Stocker
		
##PNJ 4 (Diablotin)
Le diablotin est l'inverse du samaritain.
Il est là pour déstabiliser le Héros dans sa partie. 
Il fournis de faux indices.
Il fournira également ces indices gratuitement en se faisant passant pour un samaritain.
		
###Action
- Discuter
- Stocker
		
##PNJ 5 (Compteur) (extensions)
Le compteur est un pnj se trouvant au début du labyrinthe et à différent point du labyrinthe. Celui ci compte l'aventure du Héros, au début du jeu, il explique en quoi consiste la quête principale ainsi que les règles.
		
