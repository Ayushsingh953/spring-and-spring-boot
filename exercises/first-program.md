# first-program Documentation

it explains necessary parts of first-program.

# Main Class
**Object Creation**
```java
// Mario game = new Mario();
// SuperContra game = new SuperContra();
Pacman game = new Pacman(); // Object creation
```
Here, a Pacman game object is created. The commented-out lines show examples of creating Mario and SuperContra game objects.

**Dependency Injection**
```java
GameRunner gameRunner = new GameRunner(game); // Object creation + wiring of dependency
```
A GameRunner object is created, passing the Pacman game object as a dependency. This is an example of dependency injection, where an object (the game) is passed to another object (the game runner) that depends on it.

# GameRunner class
**Constructor**
```java
public GameRunner(GamingConsole game){
    this.game = game;
}
```
The constructor takes a GamingConsole object as a parameter and assigns it to the game field. This demonstrates dependency injection, where the game object is passed to the GameRunner and stored for later use.

# GamingConsole interface
**Methods**
```java
void up();
void down();
void left();
void right();
```
The GamingConsole interface defines the basic controls for a game. Any class implementing this interface must provide implementations for the up, down, left, and right methods.

