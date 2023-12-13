package practice;

public class Cat extends Animal{

    public Cat(String name) {
        super(name);
    }
    public void character() {
        System.out.println(this.name+"는 장난이 많습니다.");
    }
}
