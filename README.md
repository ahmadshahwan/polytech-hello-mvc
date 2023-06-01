# Salutation
Dans ce TP, vous réalisez des exercices sur les patrons de conception et le modèle MVC à l'aide d'une application qui salue les utilisateurs avec leurs noms.

## Modalité
* Ces travaux pratiques sont notés.
* Ils sont à réaliser en binôme.
* Le compte rendu à remplir se trouve [ici](Rapport.md).

## Maven, un outil d'industrialisation
Maven est un gestionnaire de compilation des produits logiciels.
Il permet de gérer et d'assembler les livrables progiciels avec des configurations reproductibles en formal XML.
Les configurations d'un module progiciel sont définies dans le fichier `pom.xml`.

Maven permet la gestion des dépendances logicielles d'une manière déclarative. Chaque dépendance a une portée.

> **Exercice 1**
>
> Compilez votre code en exécutant la commande `mvn clean package` à la racine du dépôt git, ou en utilisant votre IDE.
> 
> Essayez d'exécuter le programme avec la commande `./run` ou grâce à votre IDE.
> 
> Pourquoi notre application échoue-t-elle à s'exécuter alors que le programme a bien compilé ?
> 
> Exécutez la commande `git apply pom.xml.patch` et observez la partie ajoutée dans le fichier `pom.xml`.
> Essayez de lancer l'application à nouveau.
> 
> À quoi sert la portée `runtime` dans la déclaration d'une dépendance maven ?


## Une application pour dire bonjour
Une fois l'application est lancée, vous pouvez la visualiser sur l'adresse http://localhost:8080/.

Il s'agit d'une application simple qui accueille l'utilisateur connecté avec son nom. Si aucun utilisateur n'est connecté, une salutation générique est affichée.

## Injection de dépendance
L'injection de dépendance est gérée au sein de l'application par la carde applicative. Le standard Jakarta CDI[^2] est utilisé à cette fin.

> **Exercice 2**
> 
> Observez la classe `GreetingController`.
> De quoi dépend une instance de cette classe ?
> Comment ses dépendances sont-elles injectées ?
> 
> Observer la classe `LoggerFactory`.
> À quoi sert l'annotation `@jakarta.enterprise.inject.Produces` ?
> Qu'est-ce que représente la classe `InjectionPoint` ?
> 
> Modifiez le code pour injecter des journaux d'événement de type `ConsoleLogger` au lieu de `FileLogger`.

## Journaux avec suivi du temps
On souhaite faire en sorte que les journaux, peu importe leurs types, affichent à côte de chaque message l'heure précise à laquelle le message a été produit.

> **Exercice 3**
>
> Appliquez le patron de conception décorateur pour créer la classe `TimestampedLoggerDecorator` qui permet d'ajouter l'heure au message d'un journal d'événement. 
> La nouvelle fonctionnalité devra marcher peu importe le type de journal (console ou fichier).

## Modèle-vue-contrôleur
Le code est organisé selon l'architecture MVC : modèle-vue-contrôleur.

> **Exercice 4**
>
> Identifiez dans le code les endroits (paquetages, sous-dossier) qui contiennent chacune de ces catégories : modèle, vue et contrôleur.
> 
> Le contrôleur `GreetingController` doit envoyer une salutation dédiée à l'utilisateur connecté s'il existe ou une salutation générique sinon.
> 
> À l'état actuel, le contrôleur salue toujours la même personne, John ! En regardant le code de plus près, on voit que le contrôleur n'est pas branché au modèle.
> Modifiez la classe `GreetingController` pour permettre de saluer la personne connectée.
> Attention, il est possible qu'aucun utilisateur ne soit connecté.

## Les services Web
Les services Web et un moyen d'offrir une interface de programmation applicative[^1] exposée en ligne, normalement en dessus du protocole HTTP.
Les services Web peuvent utiliser des différents protocoles d'échange comme SOAP ou REST.

### Les service Web REST
L'architecture REST implique que les services Web tirent bénéfices de la puissance du protocole HTTP pour représenter les requêtes et les réponses.
Par exemple, le verbe HTTP (GET, POST, PUT, DELETE, etc.) est utilisé pour refléter la nature de l'opération, alors que le code de status de réponse est utilisé pour refléter la nature de réponse (erreur, succès, etc.).

### Appels asynchrones au services Web via HTTP
Selon l'architecture proposée, la vue communique avec le contrôleur au travers des appels aux API REST. Ces appels sont de nature asynchrone.

> **Exercice 5**
>
> Identifiez les appels aux API REST dans le fichier `index.html`.
> 
> Est-ce que toutes les requêtes ont le bon contrôleur pour générer les bonnes réponses ?
> 
> À quoi sert-elle la méthode `getClasses()` de la classe `WebApplication` ?
> 
> À l'exemple de la classe `GreetingController`, créez les contrôleurs manquants pour répondre aux besoins exprimés dans la vue.
> Notez que vous ne devez pas avoir besoin de créer le modèle. Il est déjà présent dans le code.

## Gestion de stockage des mots de passe
Comme tous les logiciels respectueux, notre application ne stocke pas les mots de passe en claire.
Elle les chiffre grâce au service `PasswordEncrypter`.
Ce service peut avoir plusieurs réalisations, avec différents algorithmes.
À présent, une seule réalisation est fournie avec l'algorithme MD5.
La classe abstraite `BasePasswordEncrypter` factorise les fonctionnalités communes du chiffrage de mot de passe.

> **Exercice 6**
>
> Sur quel patron de conception s'appuie la classe `BasePasswordEncrypter` pour réaliser cette factorisation ? 
>
> Créez la classe `Sha512PasswordEncrypter` qui réalise `PasswordEncrypter` avec l'algorithme SHA512.
> Faite en sorte que cette réalisation-là soit utilisée pour le chiffrage des mots de passe au lieu de celle de MD5.
> Attention : tous les anciens mots de passe stockés seront invalides quand on change l'algorithme.

[^1]: *Application Programming Interface*, ou API, en anglais.
[^2]: CDI pour *Context and Dependencies Injection* en anglais.

