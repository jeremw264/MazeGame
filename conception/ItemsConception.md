# Conception des objets du jeu

[Retour](README.md)

##Objets 1 (Purse)
La bourse est un object permettant de stocker la quantit� d'or que le joueur ou le pnj as.

### Gold

L'or n'est pas un item � part enti�re, il s'agira seulement d'un int qui sera stocker dans la bourse.
Celui ci augmentera ou regressera en fonction de l'utilisation du joueurs.
		
##Objets 2 (Scroll)
Le parchemin est un item permettant d'obtenir un indice sur la qu�te du h�ros, il requiert une action pour l'utiliser / conna�tre son contenu.
Il ne peut �tre utilis� qu'une seule fois, apr�s l'utilisation il sera d�truit.


### Extensions Scroll
Dans l'extensions, le parchemin pourra �tre utilis� plusieurs et pour servir � des qu�tes annexes 

##Objets 3 (Jewels)
Ces joyaux ont chaucun une valeur diff�rentes li� � leur couleur ( Vert / Bleu / Rouge ) 
Le vert poss�dant la plus petite valeur : 10 Gold
Le Bleu, une valeur moyenne : 50 Gold
le Rouge, la plus grand valeur : 100 Gold

### Extensions Jewels 
Dans l'extensions, deux joyaux pourront �tre ajout�s : Le violet et le jaune ( Golden )
Le violet aura une valeur de 250 Gold.
Le Golden Joyaux donnera la possibilit� aux joueurs d'achet� un item au marchand gratuitement.

##Objets 4 (QuestObject)
L'objets de quete est un objet obligatoire afin de valider la quete principale et donc la sortie du labyrinthe.
Celui-ci n'as aucune valeur marchande. Il peut être obtenus via un samaritan, le sphinx en fonction de la difficulté de l'enigme ou encore en le ramassant par terre.