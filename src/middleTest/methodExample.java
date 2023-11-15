package middleTest;

public class methodExample {
    public static void main(String[] args) {
        ep1(); // void
        System.out.println(plus(1,2)); // int 타입 메소드를 부름
        System.out.println(bool(6)); //boolean 타입 메소드를 부름
        System.out.println(st("이것입니다.")); // String 타입 메소드를 부름 위 세 메소드 전부 반환값을 확인하기 위해서
        //System.out.print 문을 쓴 것 참고로 pring() 또한 메소드임

    }

    public static void ep1() { //void
        System.out.println("이 함수는 return 값이 없습니다."); // return 문이 없어도 실행 가능, 특정 동작만을 필요로 할때 쓰는 메소드
    }

    public static int plus(int a, int b) {
        return a+b;
    }

    public static  boolean bool(int a) {
        if(a>5) return true;
        return false;
    }

    public static String st(String input) {
        return "출력값은 "+ input;
    }
}
