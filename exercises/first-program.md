# first-program Documentation

it explains necessary parts of first-program.

# AppGamingBasicJava Class
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

## GamingConfiguration class
In this class, we are manually creating beans for the gaming console interface and game runner class. which is then used by spring context to run a specific game based on which game we return.
```java
@Configuration
public class GamingConfiguration {
    @Bean
    public GamingConsole game(){
        return new SuperContra();
    }

    @Bean
    public GameRunner gameRunner(GamingConsole game){
        return new GameRunner(game);
    }
}
```
But now we are letting spring automatically create beans and manage objects for us.
We are achieving this as follows :
## AppGamingSpringBean class
```java
@Configuration
@ComponentScan("com.ayush953.first_program.game")
public class AppGamingSpringBean {
    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppGamingSpringBean.class)){
            context.getBean(GameRunner.class).run();
        }

    }
}
```
* We are making this class a configuration class for spring context.
* **@ComponentScan("com.ayush953.first_program.game")** tells spring context to look for the components in this package to create beans.
* Finally, we are retrieving the game runner bean and calling the run method of this class.

**But just using component scan annotation on this class won't let spring automatically create beans. We need to annotate classes with **@Component** which we want spring to create beans for.
In this case, these classes are GameRunner, Mario, Pacman, and SuperContra.**
```java
@Component
public class GameRunner {
    // .....
}

@Component
public class Mario implements GamingConsole{
    // .....
}

@Component
public class Pacman implements GamingConsole{
    // .....
}

@Component
public class SuperContra implements GamingConsole{
    // .....
}
```