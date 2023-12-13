package practice;

public class Dog extends Animal{

    public Dog(String name) {
        super(name);
    }
    public void character() {
        System.out.println(this.name+"는 온순합니다.");
    }
}
