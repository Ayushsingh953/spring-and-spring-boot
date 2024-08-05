# Static Keyword :


  

1. **Class-Level Data and Methods**:
   * **Static Variables**: Variables declared as static belong to the class rather than any specific instance of the class. All instances of the class share the same static variable.
    ```java
        public class Example {
            public static int count = 0; // Shared by all instances
        }
    ```
   * **Static Methods**: Methods declared as static can be called on the class itself, rather than on instances of the class. They can only access other static members (variables or methods) of the class.
    ```java
        public class Example {
            public static void printMessage() {
                System.out.println("Hello, World!");
            }
        }
        Example.printMessage(); // Calling static method without an instance
    ```
2. **Initialization**:
   * Static Blocks: A static block is used for static initialization of a class. It is executed when the class is first loaded into memory. This is useful for setting up static variables or performing setup tasks that need to happen only once.
    ```java
        public class Example {
            static {
                // Initialization code here
                System.out.println("Static block executed");
            }
        }
    ```
3. **Utility Methods and Constants**:
   * **Utility Classes**: Classes that contain only static methods (like java.lang.Math) are often used as utility classes. They provide common functionality without the need for creating instances.
    ```java
        public class MathUtils {
             public static int add(int a, int b) {
                  return a + b;
             }
        }
        int result = MathUtils.add(5, 3); // No instance required
    ```
4. **Singleton Pattern**:
   * **Singleton Class**: The static keyword is often used in the Singleton design pattern to ensure that only one instance of a class is created. The static variable holds the single instance of the class.
    ```java
        public class Singleton {
             private static Singleton instance;
             private Singleton() {} // Private constructor
             public static Singleton getInstance() {
                  if (instance == null) {
                       instance = new Singleton();
                  }
                  return instance;
             }
        }
    ```
5. **Performance Considerations**:
   * **Shared State**: Static members are shared across all instances, which can be useful for performance optimization, especially when dealing with immutable data or constants.
6. **Accessing Static Members**:
   * **Direct Access**: Static members can be accessed directly using the class name, without needing to instantiate the class.
    ```java
        public class Example {
             public static int value = 10;
        }
        int x = Example.value; // Accessing static variable directly
    ```