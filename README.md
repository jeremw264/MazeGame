# l2s4-projet-2022

# Equipe

- Jérémy Woirhaye
- Franck Beyaert
- Timothé Vanoverberghe

# Sujet

[Le sujet 2022](https://www.fil.univ-lille1.fr/portail/index.php?dipl=L&sem=S4&ue=Projet&label=Documents)

# Livrables

## Livrable 1

### Atteinte des objectifs

### Difficultés restant à résoudre

## Livrable 2

### Atteinte des objectifs

### Difficultés restant à résoudre

## Livrable 3

### Atteinte des objectifs

### Difficultés restant à résoudre

## Livrable 4

### Atteinte des objectifs

### Difficultés restant à résoudre

# Journal de bord

## Semaine 1

Pendant cette semaine nous avons réfléchi à l'implémentation du labyrinthe pour cela nous avons regardé les labyrinthes dits "parfaits" et rechercher les algorithmes classiques de génération, nous en avons dénombré 12 au total même s'il doit surement en avoir plus toute notre recherche est décrite dans le document mazeConception.md ( [Etude et recherche sur les algorithmes](conception/mazeConception.md) ) en dicutant ensemble nous avons orienté nos choix possible vers certain algorithme :

- Arbre Binaire pour une première decouverte de ce type d'algorithme de plus très simple

- Recursive Backtracker un premier vrai choix qui est justifié dans notre document de Conception

- Division récursive qui peut améliorer la difficulté du labyrinthe lors du jeu (Sugestion de Franck)

- Kruskal ou Prim's qui peuvent être de très bon algorithme au niveau de la complexité (même si durant le projet il n'est pas demandé de regarder cela)

Création de la structure global du projet et réflexion sur le modèle d'implémentation en java pour respecter les contraintes + discutions avec l'enseignant pour être sûr de bien comprendre les contraintes

Implémentation de la structure et du labyrinthe avec le premier algorithme (Arbre Binaire)

#### Difficulté 

1 : Comment tester si les algorithmes sont bien parfait alors qu'il hérite d'une interface en d'autre terme comment faire pour ne pas réécrire des tests pour chaque algorithmes 

## Semaine 2

Jérémy: Pour m'amuser j'ai essayé d'implémenter l'algorithme de Kruskal le plus vite possible car il avait l'air un peu compliqué 
je me suis donc fixé 1 heure pour obtenir un algorithme fonctionnel qui respecte les contraintes sans ecrire de test ou autre uniquement le code du début j'ai uniquement fait des recherches sur l'algo son fonctionnement et réfléchi à comment l'implémenter en ecrivant le code sans faire de test fonctionnel, au bout de 35 minute j'ai effectué un premier test fonctionnel au vu des résultats mon algo avait un problème j'ai donc commencer a debuger et je me suis rendu compte que c'était un problème lors de la fusion des Set de cellule je n'ajouter qu'une cellule celle en parametre a mon premier set et je supprimer l'autre j'ai donc corrigé cela en ajoutant toute les cellules du deuxieme set au premier résultat au bout de 54 minute mon algo fonctionner au vu des test fonctionnel sans problème et generer bien des labyrinthe parfait. Bien sur l'implémentation de base à aider et l'algo est améliorable comme en enlevant la classe Wall et la remplacer par des liste de taille 2 car il n'y a pas grand chose dedans et bien d'autre chose mais vu le temps fixé je trouve ça déja bien :)

## Semaine 3

## Semaine 4

## Semaine 5

## Semaine 6

## Semaine 7

## Semaine 8

## Semaine 9

## Semaine 10

## Semaine 11

## Semaine 12
