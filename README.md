# Turn Based Console Game

## Introduction:

Players advance through many battles, for which their character or party of characters gain experience that improves various attributes and abilities.

A trainer is given a choice of six pokemons to battle it all out and emerge as the pokemon champion.
Pokémon are divided into types, such as water and fire, each with different strengths. 

Battles between them can be likened to the simple hand game rock-paper-scissors. For example, to gain an advantage over a Pokémon that cannot beat an opponent’s Charizard character because of a weakness to fire, a player might substitute a water-based Pokémon. With experience, Pokémon grow stronger, gaining new abilities. By defeating Gym Leaders and obtaining Gym Badges, trainers garner acclaim.

## Compiling an Running:

<ol>
  <li>To Compile the program</li>
  > javac *.java
  <li>To execute it, you need the postgreSQL jar file. On mac, the syntax for execution is</li>
  > <code>java -cp /path/to/postgres/jar/file:/path/to/program Game</code>
  <li>On Windows</li>
  > <code>java -cp "path/to/jar/file";"path/to/src/folder" Game</code>
</ol>

## Architectural Patterns followed:

Model View Framework (2-Tier Architecture):
Model-view-viewmodel (MVVM) is a software architectural pattern that facilitates the separation of the development of the graphical user interface (the view) from the development of the business logic or back-end logic (the model).

## Tools used:

Java Database Connectivity (JDBC) is an application programming interface (API) for the programming language Java, which defines how a client may access a database. It is a Java-based data access technology used for Java database connectivity.

Tools used: VsCode, JDBC (Connecting to PostgresQL)


## Design Patterns utilized and the rational behind it:

<ol>
  <li>Singleton</li>
    The Singleton's purpose is to control object creation, limiting the number of objects to only one. Since there is only one Singleton instance, any instance fields of a Singleton will occur only once per class, just like static fields. Singletons often control access to resources, such as database connections or sockets.
    We have used this pattern to establish a connection with the database, creation of the pokedex and also creating a battleManager instance which handles battle between the user and opponentUI.

  <li>Factory</li>
    A Factory Pattern defines an interface or abstract class for creating an object but let the subclasses decide which class to instantiate. In other words, subclasses are responsible for creating the instance of the class.
    We have used this pattern for choosing the type of opponent the user wants to battle.

  <li>State</li>
    We also utilised the state design pattern. This design pattern allowed us to cycle through various states of the Battle class like the FightOpponent state, chooseOpponent state, CreateTeam state, EndGame state, StartBattle state. The states implement the BattleState class and the BattleManager is the context class responsible for changing the state upon user actions.
</ol>

## Constributors:

<ul>
    <li>B R Pratheek</li>
    <li>Darshan Madesh</li>
    <li>Chandradhar Rao</li>
</ul>