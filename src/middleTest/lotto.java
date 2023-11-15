package middleTest;

import java.util.ArrayList;
import java.util.Scanner;

public class lotto {
    public static void main(String[] args) {
        ArrayList<Integer> lotto = new ArrayList<>();
        lotto.add((int)(Math.random()*20)+1);
         while(true) {
            int temp = (int)(Math.random()*20)+1;
            if(lotto.contains(temp)) continue;
            lotto.add(temp);
            if(lotto.size() == 4) break;
        }
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> input = new ArrayList<>();
        int deungsoo = 0;
        for(int i=0; i<lotto.size(); i++) input.add(sc.nextInt());
        for(int i=0; i<lotto.size(); i++) if(lotto.contains(input.get(i))) deungsoo++;

        switch(deungsoo) {
            case 0: System.out.println("꽝입니다.");
            break;
            case 1: System.out.println("3등입니다.");
            break;
            case 2: System.out.println("2등입니다.");
            break;
            case 3: System.out.println("1등입니다.");
            break;
        }
        for (int i=0; i<lotto.size(); i++) System.out.println(lotto.get(i));
    }
}
