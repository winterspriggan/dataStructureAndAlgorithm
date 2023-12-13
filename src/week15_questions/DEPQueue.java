package week15_questions;

import java.util.LinkedList;

public class DEPQueue {
	LinkedList<Node> q = new LinkedList<>();
	
	public void add(Node s) {
		if (q.size()==0)
			q.addFirst(s);
		else {
			int index =0;
			while(index<q.size()) {
				if (s.compareTo(q.get(index))==1) {
					q.add( index, s);
					return;
				}
				index++;
			}
			q.addLast(s);
		}
	}
	
	public Node maxPop(){
		return q.remove(0);
	}
	
	public Node minPop(){
		return q.remove(size()-1);
	}
	
	public int size() {
		return q.size();
	}
	
	public boolean isEmpty() {
		if (q.size()==0)
			return true;
		else
			return false;
	}
	public void showQ() {
		System.out.println("\n-------------- Q-Status ------------------------------------------------");
		for (int i=0;i<size();i++)
			System.out.println(i+"  : "+q.get(i).toString()+"  ");
		System.out.println("--------------------------------------------------------------------------");
	}
}
