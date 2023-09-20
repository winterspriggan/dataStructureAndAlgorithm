package week3;

import java.util.Arrays;
import java.util.HashSet;

public class OpenAddressingLinear0 {

    public OpenAddressingLinear0(int tableSize) {
        m = tableSize;
        hTable = new int[m];
        Arrays.fill(hTable, -1);
    }

    private int hashFunction(int d) {
        // 정수인 아닌 경우에는  정수로 만드는 절차의 정의 필요
        // 곱하기방법
//		double temp = (double)value * 0.6180339887;
//		double res = temp - Math.floor(temp);
//		return (int)(res*m);
        // 나누기 방법
        return d%m;
    }

    int[] hTable;
    int m;


    public void insert(int d) {
        int index = hashFunction(d);
        if(hTable[index] != -1) {
            System.out.println("Collision at "+index);
            int probeIndex = (index+1)%m;
            while(hTable[probeIndex] != -1) {
                probeIndex = (probeIndex+1)%m;
                if(probeIndex == index) { // 한바퀴 돌 때까지 넣을 곳을 찾지 못했다면 (테이블이 전부 다 차있다면)
                    System.out.println("Insert Fail !");
                    return;
                }
            }
            index=probeIndex;
        }
        hTable[index] = d;
    }

    public boolean search(int d) {
        return false;
    }
    public boolean delete(int d) {
        return false;

    }

    public void showTable() {
        System.out.println("\n\n<< Current Table Status >>");
        for (int i=0;i<m; i++) {
            System.out.print("\n "+i+" : "+hTable[i]);
        }
    }


        public static void main(String[] args) {

            int dataSize = 10;
            int maxKeyValue = 1000;
            int HashTableSize = 17;

            int [] data = new int [dataSize];
            // 서로 다른 데이터 n개를 만들자
            HashSet<Integer> rdata = new HashSet<Integer>(); //중복된 데이터는 알아서 처리해줌
            while (rdata.size()<dataSize) {
                rdata.add((int)(Math.random()*maxKeyValue));
            }
            int k=0;
            for (int d : rdata) {
                data[k]=d;
                k++;
            }

            OpenAddressingLinear0 mh = new OpenAddressingLinear0(HashTableSize);

            System.out.println("\n *** Chaining ***");
            int testcase =dataSize;
            System.out.println("<< Insert >>");
            for (int i=0;i<testcase;i++) {
                mh.insert(data[i]);
            }

            mh.showTable();

            System.out.println("\n<< Search >>");
            for (int i=0;i<testcase;i++) {
                System.out.printf("%10d ==> %5b%n", data[i],mh.search(data[i]));
            }
            System.out.println("\n<< Delete >>");
            for (int i=0;i<5;i++) {
                System.out.printf("%10d ==> %5b%n", data[i],mh.delete(data[i]));
            }
            mh.showTable();
            System.out.println("\n<< Search >>");
            for (int i=0;i<testcase;i++) {
                System.out.printf("%10d ==> %5b%n", data[i],mh.search(data[i]));
            }

        }



}
