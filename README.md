# Projet fil rouge - Trivial'Code


![hackList](https://www.wesco.fr/media/catalog/product/cache/19/image/1800x/040ec09b1e35df139433887a97daa66f/5/4/54653_P_54653_P@P2@XL.jpg)



* Equipe projet: Camille, Didier, Elodie, François 	
* Contexte: Parcours Développeur web et mobile / Simplon  	
* Date: 07/11/2019

* Version: 0.2
* Etat du projet: en cours


-----------------


## Résumé du projet:


Ce projet est réalisé dans le cadre de la formation de développeur web & mobile. Ce jeu est composé de deux application, une API et front tournant sous Angular.

Le but de ce jeu est de donner envie à ses utilisateurs de découvrir et de développer leurs connaissances dans le monde de la tech et plus précisément celui du développement.


## Installation du projet:

Ce projet se compose en deux parties.

L'application back-end est une API: 

- Elle s'installe sous SprinBoot en tant que projet Maven

- Avoir le port 8080 de libre  pour faire tourner le serveur

- Avoir une base de données "filrouge" et indiquer son accès dans properties qui est situé dans ressources

L'application front-end est une APS:

- *A venir*

## Designs de l'application

### Maquette

Lien Marvel App:
- [Maquette](https://marvelapp.com/ddeaiaa)

### MCD
![MCD](https://github.com/Fr93562/projet-fil-rouge-Simplon/blob/master/docs/mcd/MCDCamille-Final-.PNG?raw=true)

### Uses cases
![USE CASES](https://github.com/Fr93562/projet-fil-rouge-Simplon/blob/master/docs/use-cases/francois%20-%20use%20case%20v2.jpg?raw=true)


## Règles de jeu

* Un plateau de jeu
* Un dé
* Des pions
* Un portefeuille 

La partie se termine quand le portefeuille de son adversaire est vide. Vous êtes alors le ``MEILLEUR développeur`` de la partie et votre portefeuille est bien rempli.

## Déroulement de la partie

Au début de la partie, chaque joueur dispose du même montant dans son portefeuille (par exemple 1000 euros fictif). 

Le plateau forment un rond. Chaque case correspond à un thème. Si le langage choisi est java, il y aura des cases de couleurs différentes qui definiront le thème de la question. Pour java les différents thèmes seront les tableaux, les boucles, les conditions, les variables, les héritages, etc..  

Le premier joueur est choisi de façon aléatoire par l'ordinateur. Il lance le dé et son pion avance sur le plateau.

Son pion tombera sur une case avec un thème prédéfini. Selon le thème de la case, il pourra alors choisir le niveau de difficulté de la question (sélection entre niveau facile, moyen et difficile) et définir sa mise (sélection entre plusieurs mises possibles 50, 100, 150 et 200).

Par exemple, il pourra choisir une question facile et misera 150 euros, l’autre joueur devra suivre sa mise. Avec un minuteur, la question sera posée à chaque joueur. Questions sous forme de quizz ou de pratique (par exemple une méthode ou il manquerait des mots a remplir).

Dans cet exemple, si un joueur répond faux alors la mise du perdant ira au gagnant. Ainsi la cagnotte du gagnant sera créditée de 150 euros et l'autre joueur perdra sa mise. Si les deux joueurs répondent vrai alors ils gardent leurs 150 euros mais si chacun des joueurs répond faux alors ils perdent leurs mises respectives.

Ensuite le deuxième joueur joue. Chaque joueur joue chacun son tour. La partie se termine quand un joueur n’a plus d’argent dans son portefeuille.

## Compte utilisateur
Si le joueur a un compte. Son score viendra alimenter son classement personnel et le classement des inscrits qui sera définie en nombre de mise remportée et en jeux gagnés.

## Fonctionnement de l'application

### L'application back-end

L'API propose 7 types de ressources:

#### Les ressources users:

Elles sont basées sur des requêtes du type:  ``` http://localhost:8080/student/ ```

__Exemples__:

* Création:

Méthode POST - ``` http://localhost:8080/student/add ```

Type de Données passées dans le body de la requête:

```
{
    "id": 1,
    "name": "Black",
    "username": "Wildow",
    "mail": "shield@avengers.fr",
    "idLocalisation": "5"
}
```


* Recherche:

Méthode GET - ``` http://localhost:8080/student/?username=Fran%C3%A7ois&mail=test@hotmail.fr ```

Réponse:

```
{
  "id": 11,
  "idLocalisation": 16,
  "mail": "test@hotmail.fr",
  "name": "Macko",
  "username": "François"
}
```


* Tout rechercher:

Méthode GET - ``` http://localhost:8080/student/all ```

Réponse:

```
Array[17][

  {
    "id": 11,
    "idLocalisation": 16,
    "mail": "test@hotmail.fr",
    "name": "Macko",
    "username": "François"
  },
  
  {
    "id": 12, ...
```


* Suppression:

Méthode DELETE - ``` http://localhost:8080/student/remove ```

Type de données passées dans le body de la requête:

```
{
  "id": 11,
  "idLocalisation": 16,
  "mail": "test@hotmail.fr",
  "name": "Macko",
  "username": "François"
}
```

#### Les ressources ranking:

Elles sont basées sur des requêtes du type:  ``` http://localhost:8080/localisation/ ```


* Création:

Méthode POST - ``` http://localhost:8080/localisation/add ```

Type de Données passées dans le body de la requête:

```
  {
    "id": 1,
    "localisation": "55 Rue de Vincennes, 93100 Montreuil",
    "name": "Simplon"
  }
```

* Recherche:

Méthode GET - ``` ttp://localhost:8080/localisation/?id=15 ```

Réponse:

```
{
  "id": 15,
  "localisation": "5 Place de Wicklow, 78180 Montigny-le-Bretonneux",
  "name": "Montigny-le-Bretonneux"
}
```

* Tout rechercher:

Méthode GET - ``` ttp://localhost:8080/localisation/all ```

Réponse:

```
Array[5][
  
  {
    "id": 13,
    "localisation": "42 Rue de Cornulier, 44000 Nantes",
    "name": "Nantes"
  }, ...
```

* Suppression:

Méthode DELETE - ``` http://localhost:8080/localisation/remove ```

Réponse:

```
  {
    "id": 1,
    "localisation": "55 Rue de Vincennes, 93100 Montreuil",
    "name": "Simplon"
  }
```

#### Les ressources langages:

Elles sont basées sur des requêtes du type:  ``` http://localhost:8080/localisation/ ```


* Création:

Méthode POST - ``` http://localhost:8080/localisation/add ```

Type de Données passées dans le body de la requête:

```
  {
    "id": 1,
    "localisation": "55 Rue de Vincennes, 93100 Montreuil",
    "name": "Simplon"
  }
```

* Recherche:

Méthode GET - ``` ttp://localhost:8080/localisation/?id=15 ```

Réponse:

```
{
  "id": 15,
  "localisation": "5 Place de Wicklow, 78180 Montigny-le-Bretonneux",
  "name": "Montigny-le-Bretonneux"
}
```

* Tout rechercher:

Méthode GET - ``` ttp://localhost:8080/localisation/all ```

Réponse:

```
Array[5][
  
  {
    "id": 13,
    "localisation": "42 Rue de Cornulier, 44000 Nantes",
    "name": "Nantes"
  }, ...
```

* Suppression:

Méthode DELETE - ``` http://localhost:8080/localisation/remove ```

Réponse:

```
  {
    "id": 1,
    "localisation": "55 Rue de Vincennes, 93100 Montreuil",
    "name": "Simplon"
  }
```

```

Array[7][
  "Projet = Student Simplon LP4",
  "Version = 0.1",
  "Format utilisé = Json",
  "Fonctionnalités des students = Création, lecture, mise à jour et suppression des étudiants de la LP4",
  "Fonctionnalités des localisations = Création, lecture et suppression des lieux des étudiants de la LP4",
  "Adresse github = https://github.com/Fr93562/projet-1-fullstack-Simplon",
  "Technologies utilisées = Java/Spring/MySQL"
]

```

#### Les ressources questions:

Elles sont basées sur des requêtes du type:  ``` http://localhost:8080/localisation/ ```


* Création:

Méthode POST - ``` http://localhost:8080/localisation/add ```

Type de Données passées dans le body de la requête:

```
  {
    "id": 1,
    "localisation": "55 Rue de Vincennes, 93100 Montreuil",
    "name": "Simplon"
  }
```

* Recherche:

Méthode GET - ``` ttp://localhost:8080/localisation/?id=15 ```

Réponse:

```
{
  "id": 15,
  "localisation": "5 Place de Wicklow, 78180 Montigny-le-Bretonneux",
  "name": "Montigny-le-Bretonneux"
}
```

* Tout rechercher:

Méthode GET - ``` ttp://localhost:8080/localisation/all ```

Réponse:

```
Array[5][
  
  {
    "id": 13,
    "localisation": "42 Rue de Cornulier, 44000 Nantes",
    "name": "Nantes"
  }, ...
```

* Suppression:

Méthode DELETE - ``` http://localhost:8080/localisation/remove ```

Réponse:

```
  {
    "id": 1,
    "localisation": "55 Rue de Vincennes, 93100 Montreuil",
    "name": "Simplon"
  }
```

#### Les ressources "ressources":

Elles sont basées sur des requêtes du type:  ``` http://localhost:8080/localisation/ ```


* Création:

Méthode POST - ``` http://localhost:8080/localisation/add ```

Type de Données passées dans le body de la requête:

```
  {
    "id": 1,
    "localisation": "55 Rue de Vincennes, 93100 Montreuil",
    "name": "Simplon"
  }
```

* Recherche:

Méthode GET - ``` ttp://localhost:8080/localisation/?id=15 ```

Réponse:

```
{
  "id": 15,
  "localisation": "5 Place de Wicklow, 78180 Montigny-le-Bretonneux",
  "name": "Montigny-le-Bretonneux"
}
```

* Tout rechercher:

Méthode GET - ``` ttp://localhost:8080/localisation/all ```

Réponse:

```
Array[5][
  
  {
    "id": 13,
    "localisation": "42 Rue de Cornulier, 44000 Nantes",
    "name": "Nantes"
  }, ...
```

* Suppression:

Méthode DELETE - ``` http://localhost:8080/localisation/remove ```

Réponse:

```
  {
    "id": 1,
    "localisation": "55 Rue de Vincennes, 93100 Montreuil",
    "name": "Simplon"
  }
```

### Gestion des erreurs

Si une requête erronées, l'API renverra une réponse générique avec le code erreur 404.

``` 
Erreur. Pour plus d'informations sur l'Api, rendez-vous sur: http://localhost:8080/info
```


### Exemple de controller

Un exemple sur la récupération d'un étudiant:

```java

	/**
	 * Récupère en base un student par rapport à son username et mail
	 * Renvoie le résultat sous la forme d'un Json
	 * 
	 * @param username
	 * @param mail
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/student/", params = {"username","mail"})
	public Student read(String username, String mail) throws SQLException {
		
		Connexion connect = new Connexion();
		return StudentRequest.read(connect.getConnection(), username , mail);
	}

```

/

### Mises à jour

- Création du projet: 07/10/19
- Ajouts divers: 28/11/19
- Ajout de partie règle de jeu: 29/11/19
- Ajout maquettes, MCD, Use Case: 20/01/20
- Mises à jour multiples: 20/10/19


### Releases à venir

- Back-End: 24/01/20
- Front-End: 14/02/20
- Ajout d'un système d'authentification: 24/01/20
- Rendre l'Api auto-découvrable: 29/01/20
