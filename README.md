# Projet Microservices

## Description
Ce projet est un système de microservices développé avec **Spring Boot**.  
Il comprend plusieurs services :
- `api-client` : Client consommant les autres microservices.
- `authorization-service` : Service d’authentification et d’autorisation.
- `config-server` : Serveur de configuration centralisée.
- `gateway` : API Gateway pour centraliser les requêtes.
- `product-service` : Gestion des produits.
- `product-composite-service` : Agrégateur de données produits, recommandations et avis.
- `review-service` : Gestion des avis clients.
- `recommendation-service` : Service de recommandations.
- `eureka-server` : Service de découverte.

## Prérequis

- Java 17 ou supérieur
- Maven
- Docker (optionnel)
- IDE : IntelliJ, Eclipse ou VS Code
- Zipkin
- OpenFeign
- Eureka
- MySQL
- Postman

  

## Installation
1. Cloner le dépôt :
```bash
git clone https://github.com/NidalIG/projet-microservice.git
cd projet-microservice
2. Construire les microservices :
mvn clean install

3. Lancer les services :
Chaque microservice peut être lancé séparément depuis l’IDE ou en ligne de commande :
```bash
cd product-service
mvn spring-boot:run
## Configuration

- Les fichiers `application.properties` de chaque microservice contiennent les configurations locales (port, base de données, etc.)

- ConfigServer centralise certaines propriétés pour tous les services.

## Gestion des profils (Configuration)
Le projet utilise différents profils d’exécution, dont **`prod`** pour l’environnement de production.  

Toutes les configurations sont centralisées dans le **Config Server**, qui contient à la fois :  
- les fichiers de configuration par défaut (`application.properties`)  
- les fichiers spécifiques au profil de production (`application-prod.properties`)
