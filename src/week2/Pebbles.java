//package week2;
//
//public class Pebbles {
//
//    int[][] peb;
//    int memo[][];
//
//    int[][] nextPattern = {
//            {0,0,0},
//            {2,3,0},
//            {1,3,4},
//            {1,2,0},
//            {2,0,0}
//    };
//    int nCols, count;
//
//    public Pebbles(int[][] input) {
//        peb = input;
//        nCols = peb[0].length;
//        memo=new int[5][nCols];
//    }
//
//    public void reset() {
//        count = 0;
//            for(int i=0; i<5; i++) {
//                for(int j=0; j<nCols; j++) {
//                memo[i][j] = -999999;
//            }
//        }
//    }
//    public int getCount() {
//        return count;
//    }
//
//    public int maxPebble(int n) {
//        int max = -999999;
//        for(int i=1; i<4; i++) {
//            max = Math.max(max,pebble(n,i));
//        }
//        return max;
//    }
//
//    private int pebbleDP(int i, int p) {
//        count++;
//        if(memo[p][i]>-999999) return memo[p][i];
//        if(i==1) {
//            memo[p][i] = aPatternValue(i,p);
//            return aPatternValue(i,p);
//        }
//        int max = -999999;
//            int k=0;
//            while(k<3 && nextPattern[p][k] != 0) {
//                int q = nextPattern[p][k];
//                if(memo[q][i-1]==-999999)
//                    memo[q][i-1]= pebbleDP(i-1,q);
//                max = Math.max(max, pebble(i-1,q));
//                k++;
//            }
//            memo[p][i] = aPatternValue(i,p)+max;
//            return memo[p][i];
//    }
//
//    private int pebble(int i, int p) {
//        count++;
//        if(i==1) return aPatternValue(i,p);
//        int max = -999999;
//        int k=0;
//        while(k<3 && nextPattern[p][k] != 0) {
//            int q = nextPattern[p][k];
//            max = Math.max(max, pebble(i-1,q));
//            k++;
//        }
//        return aPatternValue(i,p)+max;
//    }
//
//    private int aPatternValue(int i, int p) {
//        int retVal = 0;
//        switch (p) {
//            case 1: retVal = peb[1][i]; break;
//            case 2: retVal = peb[2][i]; break;
//            case 3: retVal = peb[3][i]; break;
//            case 4: retVal = peb[1][i]+ peb[3][i]; break;
//        }
//        return retVal;
//    }
//
//    public static void main(String[] args) {
//        int[][] input = {
//                {0,0,0,0,0,0,0,0,0},
//                {0,2,5,1,6,2,8,5,-5},
//                {0,12,4,6,8,2,1,7,9}
//        };
//        Pebbles myPeb = new Pebbles(input);
//        for(int i=1;i<input[0].length; i++) {
//            myPeb.reset();
//            System.out.println(">>> %3d : [Iteration] %3d Count = %-10d" i,myPeb.getCount());
//        }
//    }
//
//
//}
