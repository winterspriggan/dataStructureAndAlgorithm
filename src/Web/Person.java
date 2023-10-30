package Web;

public class Person {
    String name;
    public Person(String name) {
        this.name = name;
    }
    public void eat(String food) {
        System.out.println(name + "이가 "+ food + "를 먹습니다.");
    }

    public static void main(String[] args) {
        Person p = new Student("길동");
        p.eat("과자");
    }
}
