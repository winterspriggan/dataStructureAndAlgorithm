package week3;

import java.util.HashSet;

public class Chaining0 {
	
	private class HashNode {
		int key;
		HashNode next;
		public HashNode(int k) { //원래 value가 있음
			key=k;
			next=null;
		}
		public String toString() {
			return "->"+key;
		}	
	}
	
	HashNode [] hTable;
	int m;
	
	public Chaining0(int n) {
		m = n;
		hTable = new HashNode[m];
		for (int i=0; i<m; i++)
			hTable[i]=null;  
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
	
//	Insert : hTable[index]에는 하나도 저장하지 말고 모두 linkedlist로 구성
	public void insert(int d) {
		int index= hashFunction(d);
		HashNode newNode = new HashNode(d);
		newNode.next = hTable[index];
		hTable[index] = newNode;
	}

//	Search
	public boolean search(int d) {
		int index = hashFunction(d);
		HashNode hashNode = hTable[index];
		while(hashNode != null) {
			if(hashNode.key==d) return true;
			else hashNode = hashNode.next;
		}
		return false;
	}
	
//	Delete 
	public boolean delete(int d) {
		int index = hashFunction(d);
		HashNode hashNode = hTable[index];

		if(hashNode == null) return false;
		else if(hashNode.key == d) {
			hTable[index]=hashNode.next;
			return true;
		}
		HashNode q = hashNode.next;
		while(q!=null) {
			if(q.key==d) {
				hashNode.next = q.next;
				return true;
			} else {
				hashNode = q;
				q = q.next;
			}
		}
		return false;
	}
	
	public void showTable() {
		System.out.println("\n\n<< Current Table Status >>");
		for (int i=0;i<m; i++) {
			HashNode p = hTable[i];
			System.out.print("\n "+i+" : ");
			while(p!=null) {
				System.out.print(p.toString());
				p=p.next;
			}
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
		
		Chaining0 mh = new Chaining0(HashTableSize);

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
