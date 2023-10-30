package Web;

public class Student extends Person{

    public Student(String name) {
        super(name);
    }
    public void eat(String food) {
        System.out.println(name + "이가 학식을 먹습니다.");
    }
}
