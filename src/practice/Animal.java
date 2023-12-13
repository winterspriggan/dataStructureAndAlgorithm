package practice;

public class Animal {
    public String name;
    public Animal(String name) {
        this.name = name;
    }
    public void eat(String food) {
        System.out.println(this.name +"가 " + food+"를 먹습니다.");
    }
}
