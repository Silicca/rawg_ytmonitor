# RAWG - Youtube Monitor

**Sujet de rattrapage - Android M2 E-Services 2020**

*Développeur : Floriane CARTON
Master 2 Informatique
Spécialité E-Services
Université de Lille
Faculté des Sciences et Technologies*

*Version 1.0 - 30/06/2020*

# Documentation technique et fonctionnelle

## Résumé
L’application RAWG – Youtube Monitor est une application Android du site rawg.io qui permet de voir la liste des vidéos Youtube disponibles pour les jeux qu’un utilisateur possède. Ainsi cet utilisateur peut rechercher un jeu dans la base de données de rawg.io, puis l’ajouter dans sa collection stockée en local sur son téléphone, il n’y a pas de compte utilisateur, et enfin il peut voir rapidement les vidéos YouTube associées aux jeux de sa collection.
Cette application respecte la charte graphique du site rawg.io. De plus, la structure et la navigation de celle-ci respecte les contraintes imposées par les besoins du site.
Ce document sera le support de présentation des différentes spécificités techniques (outils et technologies utilisées) et fonctionnelles (diagramme de classe, diagrammes de séquence et contrat d’interface) de cette application.


![Aperçu du site RAWG.io](https://i.imgur.com/sMIa4Lk.jpg "Aperçu du site RAWG.io") *Aperçu du site RAWG.io*


## Sommaire

1. Outils et technologies utilisées
0. Fonctionnement et structure
0. Contrat d’interface
0. Charte graphique
0. Diagramme de classes
0. Diagrammes de séquences
0. Aperçu de l'application

## Outils et technologies utilisées

Pour le développement de cette application le logiciel **Android Studio** a été utilisé avec le kit de développement **Android SDK** et les langages **Java 8** et **XML**.

Le niveau d’API *Android minimum* supporté par l’application est le **niveau 26** soit Android 8 Oreo et le *niveau cible* est le **niveau 29** soit Android 10.

Pour les différentes librairies qui ont été utilisées pour cette application, vous trouverez ci-dessous une liste descriptive de celles-ci et une explication de pourquoi les avoir choisies et utilisées :

* [Butterknife](https://jakewharton.github.io/butterknife/) est une bibliothèque permettant de simplifier la liaison des vues Android aux objets Java. Elle utilise des annotations pour éviter la répétition de l’écriture des codes de liaison aux vues et aussi des écouteurs d’événements. Ce qui améliore la lisibilité du code et donc facilite aussi sa compréhension. Cette librairie est simple d’utilisation et elle est bien documenté.
Pour être plus précise, l’initialisation des objets vues en Android de base se fait à deux endroits, premièrement la variable est déclarée en haut de la classe. Puis il faut utiliser la méthode `findViewByID(VIEW_ID)` pour associer l’objet à la vue. Alors qu’avec Butterknife tout ceci est fait à la déclaration de la variable. Il suffit juste d’ajouter avant la variable l’annotation `@BindView(VIEW_ID)` et ensuite dans la méthode d’initialisation de la vue il faut ajouter `ButterKnife.bind(this)`.
* [Lombok](https://projectlombok.org/) est une bibliothèque qui permet d’éviter la génération des getters, setters et constructeurs d’une classe grâce aux annotations. Ce qui va nous permettre d’écrire des classes contenant moins de lignes et la rendre plus lisible et faciliter la compréhension.
Pour être plus précise, pour les getters et setters de mes classes il faut ajouter les annotations `@Getter` et `@Setter` au début de mes classes. Avec Lombok, je peux aussi gérer l’accessibilité de certaine variable en `private`, en `protected` ou en `public`, générer les constructeurs de mes classes avec `@AllArgsConstructor` (pour la génération du constructeur avec tous les arguments) ou `@NoArgsConstructor` (pour le constructeur sans arguments).
* [BottomNavigationBar](https://github.com/Dimezis/BottomNavigationBar) (Eightbitlab.BottomNavigationBar) est une bibliothèque créée par Dmitry Saviuk qui a partagé son travail sur GitHub. Elle permet de créer rapidement et facilement une barre de navigation en bas de l’écran en quelques lignes de codes.
J’ai utilisé BottomNavigationBar dans mon projet pour faciliter le développement de ma barre de navigation. Je n’ai eu qu’à créer mes fragments, les initialiser et les ajouter dans ma barre de navigation, puis ajouter les icônes correspondantes pour chaque fragment dans ma vue.
* [Glide](https://www.glideapps.com/) est une bibliothèque qui permet de charger facilement des images dans des `ImageView`. Elle permet aussi de télécharger automatiquement des images via une URL et de les afficher dans l’`ImageView`. Ce qui correspond exactement à la demande : afficher une liste d’image de jeux et de vidéos. De plus, pour éviter de stocker l’image dans la base de données, Glide m’a permis de simplement la charger. Enfin, elle est très simple à utiliser ; Glide offre une classe static qui peut être utilisé partout, il suffit donc de l’appeler et de l’initialiser avec l’activité, d’y ajouter l’URL de l’image et enfin la destination de l’image chargé, c’est-à-dire l’`ImageView`.
Ce qui nous donne : `Glide.with(this).load(URL).into(IMAGE_VIEW)`.
* [Room](https://developer.android.com/training/data-storage/room) est une couche d’abstraction à [SQLite](https://www.sqlite.org/index.html) qui facilite la gestion de la base de données. Elle permet de mettre en cache des données pertinentes, issues du modèle, lorsqu’il n’y a plus de réseau et de synchroniser ces dernières avec le serveur une fois qu’il est retrouvé. La librairie fournit aussi un ensemble d’annotations qui va permettre d’accélérer grandement le développement des couches de persistance sans perdre en compréhension.
* [Gson](https://github.com/google/gson) est une bibliothèque open source développée par Google pour convertir un objet Java dans sa représentation JSON et vice versa. Je l’ai utilisé dans mon projet afin de convertir en objet Java les réponses en JSON des requêtes que j’obtiens à partir de l’API.
* [RxJava](https://github.com/ReactiveX/RxJava) est basé sur le patron de conception Observable, semblable aux `CallBack`, mais permettant de définir ici deux objets : l’opération longue, nommé `Observable` ; et le notifié, nommé `Subscriber`. Ceci nous permet de traiter plus facilement des tâches longues ou aussi dites asynchrones, comme par exemple des appels réseau, de la lecture de fichiers, ou encore des algorithmes assez lourds comme un tri, une recherche etc.
* [Retrofit](https://square.github.io/retrofit/) est une librairie permettant de réaliser des appels à des webservices REST sur Android de manière simple. Nous avons utilisé cette librairie durant tous nos TP d’Android, je l’ai donc réutilisée.

## Fonctionnement et structure

Cette application utilise le modèle MVP (Model View Presenter) et des RecyclerViews, voir *Diagramme de classes*.

### 1. Définition du modèle MVP

Le modèle-vue-présentateur est une dérivation de l’architecture : modèle-vue-contrôleur (MVC) qui est le modèle le plus utilisé pour créer des interfaces utilisateurs. Dans MVP, le présentateur assume la fonctionnalité de « l’intermédiaire ». En MVP, toute la logique de présentation est poussée au présentateur. Le MVP préconise de séparer la logique métier et la logique de persistance de l'activité et du fragment.
Le principe du modèle MVP est d’éviter des classes Activity de plusieurs centaine de lignes. Pour ce faire, on définit des rôles spécifiques pour chaque classe :

* La vue sert seulement à afficher et permet l’interaction avec l’utilisateur.
* Le présentateur permet de faire le lien entre la vue et le modèle, il sert aussi pour nos classes métiers et certaines actions spécifiques.
* Le modèle est la classe d’accès aux données.

### 2. Différences entre MVC et MVP

**Modèle Vue Contrôleur :**

* Les contrôleurs sont basés sur le comportement et peuvent partager plusieurs vues.
* La Vue peut communiquer directement avec le Modèle.

**Modèle Vue Présentateur :**

* La Vue est plus séparée du modèle et le Présentateur est le médiateur entre le Modèle et la Vue.
* Le Présentateur écoute et met à jour la Vue et le Modèle.
* La Vue écoute l'action de l'utilisateur et transmet l’action au Présentateur pour mettre à jour le Modèle.
* En général, il existe un mappage univoque entre Vue et Présentateur, avec la possibilité d’utiliser plusieurs présentateurs pour des vues complexes.
* Plus facile à créer des tests unitaires.

![Différences entre les modèles MVC-MVP](https://i.imgur.com/F36VEOG.png "Différences entre les modèles MVC-MVP") *Différences entre les modèles MVC-MVP*

### 3. Description de l’utilisation du modèle MVP dans le projet

**Les Vues**
Pour créer une Vue, on crée une classe qui étend vers une Activité ou un Fragment et qui va implémenter l’interface du Présentateur. Cette classe contiendra toutes les méthodes et fonctions pour générer l’interface (bouton, liste, etc …). La seule action que la Vue va faire est d’appeler le Présentateur à chaque fois qu’il y a une action effectuée sur l’interface.

**Les Présentateurs**
Pour créer un Présentateur, on crée une classe, qui aura un constructeur avec en paramètre la Vue et son contexte. Il aura aussi une interface qui permettra de faire le lien entre lui et la Vue. Enfin, le Présentateur s’étend vers le Modèle pour pouvoir mettre à jour les données.

**Les Modèles**
Pour créer un Modèle, on crée une interface qui contiendra toutes les méthodes d’échanges vers la base de données (local et/ou remote) et une classe qui va les implémenter. Le Modèle est appelé par le présentateur et retournera les données demandées.

### 4. Les RecyclerViews

Le composant RecyclerView, nous permet de gérer des listes d'articles et d'y apporter des modifications. Pour sa mise en place, ce composant a besoin d’un adapter pour gérer la liste d'articles (d'items) ainsi que l'affichage de ses données, puis d’une liste d'événements pour interagir avec cette liste. De plus, dans ce projet est utilisé un LayoutManager et un ViewHolder.

**L’Adapter**
L'adapter permet de faire la liaison (bind) entre la vue `RecyclerView` et une liste de données. Il va de contenir l'ensemble des données à afficher dans le `RecyclerView` en gérant également ses mises à jour.

**Le LayoutManager**
Le `LayoutManager` permet de structurer l'ensemble des sous-vues contenues dans le `RecyclerView` (gérer les colonnes et les lignes).

**Le ViewHolder**
Le `ViewHolder` permet de représenter visuellement un élément de la liste de données dans le `RecyclerView` (une ligne). Il établit un lien entre la Vue et ses différents éléments qui la compose que nous allons mettre à jour en fonction de l'article (l’item) qu'elle affiche.
Enfin, pour mettre à jour l'affichage, nous appelons de la méthode asynchrone : `notifyDataSetChanged()`.

![Architectue d'une RecyclerView](https://i.imgur.com/jW8V7JP.png "Architecture d'une RecyclerView") *Architectue d'une RecyclerView*

### 5. Le fonctionnement et l’utilité du stockage en local

Le stockage de données en local permet de stocker des données qu'on ne souhaite pas de nouveau rechercher à partir d'une nouvelle requête. Dans ce projet, la base de données locale a été utilisée essentiellement pour stocker des informations sur les jeux ajoutés en favori., comme son nom, sa note et l'url de l'image. Toutes les autres informations inutilisées par l'application que nous renvoie l'API ainsi que les images ne sont pas stockées en local. Ce qui signifie qu'à chaque requête, l'image est retéléchargée. De plus, la recherche des jeux et des vidéos en rapport avec les jeux favoris n’utilise pas de stockage en local.
Je vais donc présenter le fonctionnement de tout le processus de recherche d'un jeu et l'ajout de celui-ci dans les favoris, et donc dans le stockage en local.

Une fois sur l'écran de recherche, l'utilisateur va rechercher un jeu à l'aide de mots clés, par exemple "animal". L'application va envoyer une requête à l'API de rawg.io avec ces mots clés et recevoir une réponse en JSON avec une liste de l'ensemble des jeux correspondant aux mots clés. L'utilisateur va appuyer sur le bouton d'ajout pour l'ajouter à ses favoris, ce qui va ajouter le jeu à la base de données locale gérée avec SQLite.

Lorsque l'utilisateur veut afficher ses favoris ou les vidéos correspondant à ses favoris, l'application va simplement récupérer les jeux enregistrés en local puis envoyer une requête à l'API de rawg.io avec l'id de ses jeux pour trouver les vidéos. Pour plus de compréhension, voir le *Diagrammes de séquences*.

### 6. Le fonctionnement avec Room de la base de données SQLite

Afin d’utiliser la librairie Room sur la base de données SQLite, il nous faut différentes classes avec des annotations :

* Définir la ou les entités (tables) avec `@Entity`
* Déclarer la ou les interfaces DAO (Data Access Object) : afin que Room connaisse la façon dont on désire qu’il récupère des objets de notre base ; avec `@Dao`
* Créer la base de données héritant de RoomDatabase et avec `@Database`

**L’entité**
Dans ce projet, nous avons besoin d’une seule entité Game qui va correspondre à la table de nos jeux avec un `id` en `@PrimaryKey`, un `String name`, un `String imageUrl`, un `String note` et un `int gameId` (que nous aurons besoin pour accéder aux vidéos).

**La Dao**
Nous avons donc également besoin d’une seule Dao `IGameDao` qui va correspondre à la Dao de notre entité Game. Cette Dao va contenir, entre autres, les requêtes SQL dont nous aurons besoin pour manipuler les données de la table Game avec l’annotation `@Query`. Nous avons besoin d’une requête pour récupérer l’ensemble des jeux de la table, pour ajouter un jeu et pour retirer un jeu de la base.

**La base de données Room SQLite**
Nous précisons à la base de données `GameDataBase` quels sont ses entités et ajoutons la méthode permettant de récupérer un objet `IGameDao`. Ceci va nous permettre d’exécuter les requêtes pour accéder à la table.

### 7. Application de l’Android Architecture Components

Afin d’appliquer l’Architecture Components, des classes `Repository` et des `ViewModels` ont été utilisées.

Le rôle des `ViewModels` est de fournir au présentateur les données utilisées par l'interface graphique. Enfin, le rôle du `Repository` de servir de médiateur entre les `ViewModels` et les différentes sources de données (ici la DAO). Les `ViewModels` ne doivent pas manipuler la source de données directement.

![Exemple d'Android Architecture Components utilisée](https://i.imgur.com/Anis9xM.png "Exemple d'Android Architecture Components utilisée") *Exemple d'Android Architecture Components utilisée*

## Contrat d'interface

### 1. Recherche d’une liste de jeux à partir de mots clés

*Requête de l’API de type GET :* <https://api.rawg.io/api/games?search=[keywords]>

*Réponse de l’API :*
La réponse est donnée en JSON, on obtient la liste des jeux correspondant à ce mot clé et le nombre de résultats. Parmi les informations données sur un jeu ce qui nous intéresse sont les informations suivantes : `id`, `name`, `rating` et `background_image`.

![Exemple du JSON donné pour un jeu tiré de la liste `results` de la requête](https://i.imgur.com/fVHFJOU.png "Exemple du JSON donné pour un jeu tiré de la liste results de la requête") *Exemple du JSON donné pour un jeu tiré de la liste `results` de la requête*

À partir de ces informations extraites, nous allons utiliser la librairie GSON pour transformer ce JSON en plusieurs objets Java `Game`.

### 2. Recherche d’une liste de vidéos youtube associées à l’id d’un jeu

**Requête de l’API de type GET :** <https://api.rawg.io/api/games/[game_id]/youtube>

**Réponse de l’API :**
La réponse est donnée en JSON, on obtient la liste des vidéos youtube correspondant à l’id du jeu et le nombre de résultats. Parmi les informations données sur une vidéo ce qui nous intéresse sont les informations suivantes : `external_id`, `name`, `channel_title`, `view_count` et `thumbnails`.

![Exemple du JSON donné pour une vidéo tirée de la liste results de la requête](https://i.imgur.com/5nr2VPJ.png "Exemple du JSON donné pour une vidéo tirée de la liste results de la requête") *Exemple du JSON donné pour une vidéo tirée de la liste `results` de la requête*

À partir de ces informations extraites, nous allons utiliser la librairie GSON pour transformer ce JSON en plusieurs objets Java `Video`.

## Charte graphique 

La charte graphique de RAWG.io est composée de nuances de gris et de textes blancs contrastés. De plus, les cartes de présentation des jeux sont plutôt arrondies au niveau des coins, de même pour la barre de recherche.

Afin de reproduire le plus fidèlement possible les couleurs utilisées par le site, j’ai réalisé plusieurs impressions d’écrans et extrait les couleurs à l’aide de la pipette sur un éditeur photo (*voir extrait des 3 vues à la fin*).

J’ai pu établir le tableau suivant afin de réutiliser ces couleurs :

![Charte graphique du site RAWG.io](https://i.imgur.com/9Fq9Qhv.png "Charte graphique du site RAWG.io") *Charte graphique du site RAWG.io*

## Diagramme de classes

L’image étant illisible dans ce readme, je l’ai hébergé en ligne ici : <https://bit.ly/2VrGkbJ>

## Diagrammes de séquences

Pour une meilleure lisibilité, je l’ai également hébergé en ligne ici : <https://bit.ly/3eJHaIN>

## Aperçu de l'application

![Screenshot de la vue des vidéos (video)](https://i.imgur.com/5F6ugnY.jpg "Screenshot de la vue des vidéos (video)") *Screenshot de la vue des vidéos (video)*

![Screenshot de la vue des favoris (menu)](https://i.imgur.com/rzO6I5D.jpg "Screenshot de la vue des favoris (menu)") *Screenshot de la vue des favoris (menu)*

![Screenshot de la vue de recherche (search)](https://i.imgur.com/ot8Noz4.jpg "Screenshot de la vue de recherche (search)")*Screenshot de la vue de recherche (search)*
