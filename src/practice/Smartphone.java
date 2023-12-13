package practice;

public class Smartphone {
    String phoneNumb;
    String telecom;
    double chargePercent;
    int memory;
    int friends;
    //폰 번호 통신사 배터리 충전정도 메모리 잔량 한꺼번에 출력
    //통신사 변경
    //배터리 충전
    //사진 촬영
    //사진 삭제
    //창작 한개

    public void allPrint() {
        System.out.println("핸드폰 번호 : " + phoneNumb);
        System.out.println("통신사 : " + telecom);
        System.out.println("배터리 잔량 : " + chargePercent);
        System.out.println("메모리 잔량 : " + memory);
        System.out.println("연락처 수 : " + friends);
    }

    public void changeTelecom(String t) {
        if(t.equals(this.telecom)) {
            System.out.println("이미 해당 통신사입니다.");
            return;
        }
        switch (t) {
            case "SKT":
            case "LGU":
            case "KT":
                this.telecom = t;
                System.out.println("변경완료");
                return;
            default :
                System.out.println("에러 발생 !!");
        }
    }

    public void takeOffPhoto() {
        if(this.memory >= 5) {
            System.out.println("찰칵 ! ");
            this.memory -=5;
        }
        else System.out.println("메모리가 부족합니다.");
    }

    public void deletePhoto() {
        if(this.memory <= 95) {
            System.out.println("사진을 삭제했습니다 . ");
            this.memory += 5;
        }
        else System.out.println("사진이 없습니다.");
    }


    //배터리 충전
    public void charging(double time) {
        System.out.println(time + " 시간 충전합니다.");
        if(chargePercent +time*30 > 100) this.chargePercent = 100;
        else this.chargePercent += time*30;
    }

    public static void main(String[] args) {
        Smartphone s = new Smartphone();
        s.phoneNumb = "01093720683";
        s.chargePercent = 1;
        s.telecom = "SKT";
        s.memory = 100;
        s.friends = 100;
        s.allPrint();
        s.changeTelecom("LG");
        s.charging(1.5);
        s.allPrint();
        s.takeOffPhoto();
        s.allPrint();
        s.deletePhoto();
        s.allPrint();
    }
}
