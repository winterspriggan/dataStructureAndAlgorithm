package practice;

public class Main {
    public static void main(String[] args) {
        Animal animal = new Animal("코코");
        Dog dog = new Dog("또또");
        Cat cat = new Cat("따따");
        animal.eat("과자");
        dog.eat("간식");
        dog.character();
        cat.eat("츄르");
        cat.character();
    }
}
