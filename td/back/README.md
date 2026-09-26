#TD1 - API REST Bibliotheque de films

API REST de gestion d'un bibliotheque de films(Spring boot).
Les films sont stockes en memoire (List/Map), sans base de données.

## Démarrer l'application 
Dans le dossier 'td/back' :
./gradlew bootRun

L'application démarre sur http://localhost/8080

## Endpoints
-GET /films : liste tous les films
GET /films-/{id}: un film par son id
-POST /films : ajoute un film
-PUT /films/{id}: met à jour un film
-DELETE /film/{id} supprime un film

Filtre :
-GET /films?realisateur=....: filtre par realisateur
-GET /film?genre=... : filtre par genre

## Tester l'API

Les requêtes de test sont dans `http/films.http`,
à exécuter avec l'extension REST Client de VSCode.