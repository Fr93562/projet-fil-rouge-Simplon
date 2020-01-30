# Projet fil rouge - Trivial'Code


![hackList](https://www.wesco.fr/media/catalog/product/cache/19/image/1800x/040ec09b1e35df139433887a97daa66f/5/4/54653_P_54653_P@P2@XL.jpg)



* Equipe projet: Camille, Didier, Elodie, François 	
* Contexte: Parcours Développeur web et mobile / Simplon  	
* Date: 07/11/2019

* Version: 0.5
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

L'API propose 6 contrôleurs différents :

#### Contrôleur UserController :

Elles sont basées sur des requêtes du type:  ``` http://localhost:8080/users ```

__Exemples__ :

* Créer un utilisateur :

Méthode POST - ``` http://localhost:8080/users```

Type de Données passées dans le body de la requête :

```
{
    "username": "Test",
    "email": "test@laposte.fr",
    "password": "test",
    "dateInscription": "2020-01-20",
    "langage": [
        {
            "id": 1
        },
        {
        	"id": 6
        }
    ]
}
```


* Récuperer un utilisateur :

Méthode GET - ``` http://localhost:8080/users/?username=test ```

Réponse :

```
{
    "id": 7,
    "username": "Test",
    "email": "test@laposte.fr",
    "password": "test",
    "dateInscription": "2020-01-20T00:00:00.000+0000",
    "typeUser": {
        "id": 2,
        "type": "Joueur"
    },
    "langage": [
        {
            "id": 1,
            "language": "Java"
        },
        {
            "id": 6,
            "language": "PHP"
        }
    ],
    "ranking": 0
}
```

* Récuperer tous les utilisateurs :

Méthode GET - ``` http://localhost:8080/users ```

Réponse :

```
[
  ...
    {
        "id": 7,
        "username": "Test",
        "email": "test@laposte.fr",
        "password": "null",
        "dateInscription": "2020-01-20T00:00:00.000+0000",
        "typeUser": {
            "id": 2,
            "type": "Joueur"
        },
        "langage": [
            {
                "id": 1,
                "language": "Java"
            },
            {
                "id": 6,
                "language": "PHP"
            }
        ],
        "ranking": 0
    }
]
```

* Mise à jour utilisateur :

Méthode PUT - ``` http://localhost:8080/users ```

Un lien specifique existe pour l'administrateur.

Méthode PUT - ``` http://localhost:8080/users/admin ```

Type de données passées dans le body de la requête :

```
{
    "id": 7,
    "username": "Testmodifié",
    "email": "test@laposte.fr",
    "password": "test"
}
```
Réponse :

```
User has been update
```

* Suppression :

Méthode DELETE - ``` http://localhost:8080/users ```

Type de données passées dans le body de la requête :

```
{
    "id": 7
}
```
Réponse :

```
User has been delete
```
* Mise à jour du classement :

Méthode PUT - ``` http://localhost:8080/users/ranking/?id=8&point=200 ```

Réponse :

```
Ranking has been update
```

* Ajouter un type d'utilisateur :

Méthode POST - ``` http://localhost:8080/users/type ```

Type de Données passées dans le body de la requête :

```
    {
        "type": "Joueur Test"
    }
```
Réponse :

```
{
    "id": 4,
    "type": "Joueur Test"
}
```

* Récupérer les types d'utilisateur :

Méthode GET - ``` http://localhost:8080/users/type ```

Réponse :

```
[
    {
        "id": 1,
        "type": "Administrateur"
    },
    {
        "id": 2,
        "type": "Joueur"
    },
    {
        "id": 3,
        "type": "Joueur Premium"
    }
]
```
* Mise à jour du type d'utilisateur :

Méthode PUT - ``` http://localhost:8080/users/type ```

Type de Données passées dans le body de la requête :

```
{
    "id": 4,
    "type": "Joueur Essai"
}
```
Réponse :

```
Type has been update
```

* Suppression d'un type d'utilisateur :

Méthode DELETE - ``` http://localhost:8080/users/type ```

Type de Données passées dans le body de la requête :

```
{
    "id": 4
}
```
Réponse :

```
Type has been delete
```

#### Contrôleur RessourceController :

Elles sont basées sur des requêtes du type:  ``` http://localhost:8080/ressources ```

__Exemples__ :

* Ajouter une nouvelle ressource :

Méthode POST - ``` http://localhost:8080/ressources ```

Type de Données passées dans le body de la requête :

```
  {
    "text": "Cours TEST",
    "link": "https://test.org"
  }
```
Réponse :

```
{
    "id": 5,
    "text": "Cours TEST",
    "link": "https://test.org"
}
```

* Recherche la liste complète des ressources :

Méthode GET - ``` http://localhost:8080/ressources ```

Réponse :

```
  {
    "id": 4,
    "text": "Java",
    "link": "https://jmdoudoux.developpez.com/cours/developpons/java/"
  } ...
```

* Modifier une ressource :

Méthode PUT - ``` http://localhost:8080/ressources ```

Type de Données passées dans le body de la requête :

```
  {
    "id": 5,
    "text": "Cours TESTMODIF",
    "link": "https://testmodif.com"
  }
```
Réponse :

```
Ressource modifiée
```

* Supprimer d'une ressource :

Méthode DELETE - ``` http://localhost:8080/ressources ```

Type de Données passées dans le body de la requête:

```
    {
        "id": 5
    }
```
Réponse :

```
Ressource supprimée
```

*  Retourne la Liste des ressources correspondant a la liste des IDs des questions

Méthode GET - ``` http://localhost:8080/ressources/end ```

Type de Données passées dans le body de la requête :

```
[2,4]
```
Réponse :

```
[
    {
        "id": 2,
        "text": "CSS",
        "link": "https://openclassrooms.com/fr/courses/1603881-apprenez-a-creer-votre-site-web-avec-html5-et-css3"
    },
    {
        "id": 4,
        "text": "Java",
        "link": "https://jmdoudoux.developpez.com/cours/developpons/java/"
    }
]
```

#### Contrôleur QuestionController :

Elles sont basées sur des requêtes du type:  ``` http://localhost:8080/questions ```

__Exemples__ :

* Ajoute une nouvelle question :

Méthode POST - ``` http://localhost:8080/questions ```

Type de Données passées dans le body de la requête:

```
{
    "question": "C'est un Test ?",
    "level": 1,
    "answer": "Oui",
    "choice1": "Peut-etre",
    "choice2": "Non",
    "choice3": "Surement pas",
    "categorie": {
        "id": 1
    },
    "ressource": {
        "id": 3
    }
}
```

* Affiche la liste de toutes les questions :

Méthode GET - ``` http://localhost:8080/questions ```

Réponse :

``` 
[ 
    {
        "id": 1,
        "question": "Definition du terme HTML ?",
        "level": 1,
        "answer": "HyperText Markup Language",
        "choice1": "Pas ça",
        "choice2": "Non plus",
        "choice3": "Toujours pas",
        "categorie": {
            "id": 1,
            "type": "QCM"
        },
        "ressource": {
            "id": 1,
            "text": "Cours HTML",
            "link": "https://openclassrooms.com/fr/courses/1603881-apprenez-a-creer-votre-site-web-avec-html5-et-css3"
        }
    }, ...
]
```

* Affiche la liste de toutes les questions d'un langage :

Méthode GET - ``` http://localhost:8080/questions/?langage=java ```

Réponse :

```  
[
    {
        "id": 4,
        "question": "Pour compiler un programme Java de quoi a t'on besoin? JDK ou JRE",
        "level": 1,
        "answer": "JDK",
        "choice1": "Pas ça",
        "choice2": "Non plus",
        "choice3": "Toujours pas",
        "categorie": {
            "id": 1,
            "type": "QCM"
        },
        "ressource": {
            "id": 4,
            "text": "Java",
            "link": "https://jmdoudoux.developpez.com/cours/developpons/java/"
        }
    }
]
```
* Modifier une question :

Méthode PUT - ``` http://localhost:8080/questions ```

Type de Données passées dans le body de la requête :

```
{
    "id": 5,
    "question": "C'est un Test modifié ?",
    "level": 1,
    "answer": "Oui",
    "choice1": "Peut-etre",
    "choice2": "Non",
    "choice3": "Surement pas",
    "categorie": {
        "id": 1,
        "type": "QCM"
    },
    "ressource": {
        "id": 3,
        "text": "JavaScript",
        "link": "https://openclassrooms.com/fr/courses/2984401-apprenez-a-coder-avec-javascript"
      }
}
```
Réponse :

```
Question has been update
```

* Suppression d'une question:

Méthode DELETE - ``` http://localhost:8080/questions ```

Type de Données passées dans le body de la requête :

```
{
    "id": 5
}
```
Réponse :

```
Question has been delete
```
* Ajouter une categorie de question :

Méthode POST - ``` http://localhost:8080/questions/cat ```

Type de Données passées dans le body de la requête :

```
{
    "type": "Test"
}
```
Réponse :

```
{
    "id": 4,
    "type": "Test"
}
```

* Récupérer les categories questions :

Méthode GET - ``` http://localhost:8080/questions/cat ```

Réponse :

```
[
    {
        "id": 1,
        "type": "QCM"
    },
    {
        "id": 2,
        "type": "Code"
    },
    {
        "id": 3,
        "type": "Ecrite"
    }
]
```
* Mise à jour d'une categorie de questions :

Méthode PUT - ``` http://localhost:8080/questions/cat ```

Type de Données passées dans le body de la requête :

```
{
    "id": 4,
    "type": "Test modifié"
}
```
Réponse :

```
Categorie has been update
```

* Suppression d'une categorie de questions :

Méthode DELETE - ``` http://localhost:8080/questions/cat ```

Type de Données passées dans le body de la requête :

```
{
    "id": 4
}
```
Réponse :

```
Categorie has been delete
```

#### Contrôleur LangageController :

Elles sont basées sur des requêtes du type:  ``` http://localhost:8080/langages ```

__Exemples__ :

* Ajout d'un nouveau langage :

Méthode POST - ``` http://localhost:8080/langages ```

Type de Données passées dans le body de la requête:

```
{
    "language": "C++"
}
```

* Affiche la liste de tous les langages :

Méthode GET - ``` http://localhost:8080/langages ```

Réponse :

```  
[
    {
        "id": 1,
        "language": "Java"
    },
    {
        "id": 2,
        "language": "C#"
    },
    {
        "id": 3,
        "language": "HTML"
    },...
]
```

* Affiche un langage par son nom :

Méthode GET - ``` http://localhost:8080/langages/?langage=java ```

Réponse :

```  
{
    "id": 1,
    "language": "Java"
}
```
* Modifier un langage :

Méthode PUT - ``` http://localhost:8080/langages ```

Type de Données passées dans le body de la requête :

```
{
    "id": 9,
    "language": "C"
}
```
Réponse :

```
Language has been update
```

* Suppression d'un langage:

Méthode DELETE - ``` http://localhost:8080/langages ```

Type de Données passées dans le body de la requête :

```
{
    "id": 9
}
```
Réponse :

```
Language has been delete
```
* Ajout de la liaison Langage/Question :

Méthode POST - ``` http://localhost:8080/langages/qlink ```

Type de Données passées dans le body de la requête:

```
{
        "id": 6,
        "question": "C'est un Test ?"
}
```
#### Contrôleur FaqController :

Elles sont basées sur des requêtes du type:  ``` http://localhost:8080/faq ```

__Exemples__ :

* Ajout d'une nouvelle question a la FAQ :

Méthode POST - ``` http://localhost:8080/faq ```

Type de Données passées dans le body de la requête:

```
{
   "question": "Ou trouver la présentation des Endpoints?",
    "response": "Dans le Readme.",
    "priority": "1"
}
```

* Affiche la liste de toutes les questions FAQ :

Méthode GET - ``` http://localhost:8080/faq ```

Réponse :

```  
[
    {
        "id": 1,
        "question": "Comment marche ce jeu ?",
        "response": "Comme ca.",
        "priority": "1"
    },
    {
        "id": 2,
        "question": "Ou trouver la présentation des Endpoints?",
        "response": "Dans le Readme.",
        "priority": "1"
    }
]
```

* Modifier une question de la FAQ :

Méthode PUT - ``` http://localhost:8080/faq ```

Type de Données passées dans le body de la requête :

```
{
	  "id": 2,
    "question": "Ou trouver la présentation des Endpoints?",
    "response": "Dans le Readme à cette adresse: https://github.com/Fr93562/projet-fil-rouge-Simplon/blob/master/README.md",
    "priority": "1"
}
```

* Suppression d'une question de la FAQ:

Méthode DELETE - ``` http://localhost:8080/faq ```

Type de Données passées dans le body de la requête :

```
{
    "id": 2
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
