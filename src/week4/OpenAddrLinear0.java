package week4;

import java.util.Arrays;

public class OpenAddrLinear0 {

    public void showTable() {
        System.out.println("\n\n<< Current Table Status >>");
        for (int i=0;i<m; i++) {
            System.out.print("\n "+i+" : "+hTable[i]);
        }
    }


    public boolean delete(int d) {
        int index = hashFunction(d);
        int probeIndex = index;
        while(hTable[probeIndex]!=-1) {
            if(d==hTable[probeIndex]) {
                hTable[probeIndex] = -999;
                return  true;
            } else {
                probeIndex = (probeIndex+1)%m;
                if(probeIndex==index) {
                    System.out.println("Not Found !");
                    return  false;
                }
            }
        }
        return false;
    }
    public void insert(int d) {
        int index = hashFunction(d);
        int probeIndex = index;
        while(hTable[probeIndex]!=-1 && hTable[probeIndex]!=-999) {

            System.out.println("Collison at "+probeIndex);
            probeIndex = (probeIndex+1)%m;
            if(probeIndex==index) {
                System.out.println("Insert Fail!");
                return;
            }
        }
    }

    public boolean search(int d) {
        int index = hashFunction(d);
        int probeIndex = index;
        while(hTable[probeIndex]!=-1) {
            if(d==hTable[probeIndex]) {
                return  true;
            } else {
                probeIndex = (probeIndex+1)%m;
                if(probeIndex==index) {
                    System.out.println("Not Found !");
                    return  false;
                }
            }
        }
        return false;
    }

    private int hashFunction(int d) {
//        double temp = (double)value * 0.6180339887;
//		double res = temp - Math.floor(temp);
//		return (int)(res*m);
        return d%m;
    }


    int[] hTable;
    int m;


    public OpenAddrLinear0(int tableSize) {
        m = tableSize;
        hTable = new int[m];
        Arrays.fill(hTable, -1);

    }
    public static void main(String[] args) {

    }
}
