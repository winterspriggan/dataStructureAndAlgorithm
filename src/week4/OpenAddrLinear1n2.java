package week4;

import week3.OpenAddressingLinear0;

import java.util.Arrays;
import java.util.HashSet;

public class OpenAddrLinear1n2 {

    final float threshold = (float)0.9;
    int nOfItems;
    int nOfHops;
    int[] hTable;
    int m;


    public OpenAddrLinear1n2(int tableSize) {
        m = tableSize;
        hTable = new int[m];
        Arrays.fill(hTable, -1);
        nOfItems = 0;

    }


    public int delete(int d) {
        nOfHops =1;
        int index = hashFunction(d);
        int probeIndex = index;
        while(hTable[probeIndex]!=-1) {
            nOfHops++;
            if(d==hTable[probeIndex]) {
                hTable[probeIndex] = -999;
                nOfItems--;
                return  nOfHops;
            } else {
                probeIndex = (probeIndex+1)%m;
                if(probeIndex==index) {
                    System.out.println("Not Found !");
                    return  -nOfHops;
                }
            }
        }
        return -nOfHops;
    }

    private float loadFactor() {
//        return nOfItems/m;
        return (float)nOfItems/m;
    }

    private void enlarge() {
        System.out.println("Double Up !");
        int[] temp = hTable;
        int oldM = m;
        m *= 2;
        hTable = new int[m];
        Arrays.fill(hTable, -1);
        nOfItems = 0;
        for (int i=0; i<oldM; i++) {
            if(temp[i] != -1 && temp[i] != -999) insert(temp[i]);
        }
    }
    public int insert(int d) {
        nOfHops =1;
        int index = hashFunction(d);
        int probeIndex = index;
        while(hTable[probeIndex]!=-1 && hTable[probeIndex]!=-999) {
            nOfHops++;
            System.out.println("Collison at "+probeIndex);
            probeIndex = (probeIndex+1)%m;
//            if(probeIndex==index) {
//                System.out.println("Insert Fail!");
//                return -nOfHops;
//            }
        }
        hTable[probeIndex] = d;
        nOfItems++;

        if(loadFactor()>=threshold) {
            enlarge();
        }
        return nOfHops;
    }

    public int search(int d) {
        nOfHops=1;
        int index = hashFunction(d);
        int probeIndex = index;
        while(hTable[probeIndex]!=-1) {
            nOfHops++;
            if(d==hTable[probeIndex]) {
                return  nOfHops;
            } else {
                probeIndex = (probeIndex+1)%m;
                if(probeIndex==index) {
                    System.out.println("Not Found !");
                    return  -nOfHops;
                }
            }
        }
        return -nOfHops;
    }

    private int hashFunction(int d) {
//        double temp = (double)value * 0.6180339887;
//		double res = temp - Math.floor(temp);
//		return (int)(res*m);
        return d%m;
    }

    public void showTable() {
        System.out.println("\n\n<< Current Table Status >>");
        for (int i=0;i<m; i++) {
            System.out.print("\n "+i+" : "+hTable[i]);
        }
    }




    public static void main(String[] args) {

        int dataSize = 20;
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

        OpenAddrLinear1n2 mh = new OpenAddrLinear1n2(HashTableSize);

        System.out.println("\n *** Chaining ***");
        int testcase =dataSize;
        //
        System.out.println("<< Insert >>");
        for (int i=0;i<testcase;i++) {
            mh.insert(data[i]);
        }

        mh.showTable();

        System.out.println("\n<< Search >>");
        for (int i=0;i<testcase;i++) {
            System.out.printf("%10d ==> %5d%n", data[i],mh.search(data[i]));
        }
        System.out.println("\n<< Delete >>");
        for (int i=0;i<5;i++) {
            System.out.printf("%10d ==> %5d%n", data[i],mh.delete(data[i]));
        }
        mh.showTable();
        System.out.println("\n<< Search >>");
        for (int i=0;i<testcase;i++) {
            System.out.printf("%10d ==> %5d%n", data[i],mh.search(data[i]));
        }
    }
    }
