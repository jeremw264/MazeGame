# Conception des personnage du jeu 

[Retour](README.md)

##Héros
Le héros est un personnage pouvant se déplacer d'une ou plusieurs cases pendant son tour. 
Il doit pouvoir discuter avec les différents personnages du jeux.
Il doit pouvoir ramasser des objets et pouvoir les stocker.
Il doit pouvoir utiliser des objets stocké ou non.
Il doit pouvoir accéder à son inventaire ( stockage d'objets )
Il doit pouvoir acheter et/ou vendre ses objets
		
###Action 
- Se déplacer
- Discuter
- Ramasser
- Stocker
- Utiliser
- Inventaire
-Acheter / Vendre
			
##Pnj 1 (Marchand)
Le marchand est un pnj, se déplaçant dans le labyrinthe pendant le tour du Héros. 
Il peut y avoir un ou plusieurs marchand selon la taille du labyrinthe.
Le marchand en échange d'or peut vendre des items au Héro ou alors les lui acheter.
		
###Action
-Déplacement (automatique)
-Achat / Vente (automatique)

##PNJ 2 (Sphinx)
Le sphinx est un pnj, apparaissant à des checkpoints / des cellules événements.
Il posséde différents thèmes de question en relation avec le lore du jeux. Dans chaque thèmes, plusieurs questions existe de différentes difficultés.Le Héros pourra choisir le thèmes mais la question sera aléatoirement choisis dans la liste du thèmes.
Si la réponse est bonne, le sphinx fournira un objet ou un indice permettant au Héros d'avancer dans sa quête.
		
###Action
		
##PNJ 3 (Samaritain)
Le samaritain est un pnj qui ne se déplace pas, il peut en exister plusieurs dans un même labyrinthe. Ils sont présents pour aider le Héro a terminé sa quête ou lui en apprendre plus sur celle ci. Le samaritain ne demande rien en échange de ses informations / objets.
		
###Action
		
##PNJ 4 (Diablotin)
Le diablotin est l'inverse du samaritain. Celui ci se déplacer dans un périphérique pré-définis. Il est là pour déstabiliser le Héros dans sa partie. Il fournira de faux indice ou des objets défaillant ( demandant une contrepartie à chaque utilisation ).
Il fournira également ces indices et objets gratuitement en se faisant passant pour un samaritain.
		
###Action
		
##PNJ 5 (Compteur) (extensions)
Le compteur est un pnj se trouvant au début du labyrinthe et à différent point du labyrinthe. Celui ci compte l'aventure du Héros, au début du jeu, il explique en quoi consiste la quête principale ainsi que les règles.
		
