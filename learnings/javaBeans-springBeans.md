# Exploring java Bean vs POJO vs Spring Bean

## Java Bean :
**Classes adhering to 3 constraints :**
1. Have public default(no argument) constructor.
2. Allow access tp their properties using getter and setter methods
3. Implement java.io.Serializable interface

```java
// EJB - enterprise java bean
class JavaBean implements Serializable{
    public JavaBean(){}

    private String text;

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }
}
```
## POJO :
**Plain Old Java Object**
1. No constraints
2. Any java Object is a POJO
```java
class POJO{
    private String text;
    public String getString(){
        return text;
    }
}
```

```java
POJO pojo = new POJO();
```

## Spring Bean :
Any java object that is managed by Spring 
1. Spring uses IOC( Inversion of Control) container (Bean Factory or Application context) to manage these objects