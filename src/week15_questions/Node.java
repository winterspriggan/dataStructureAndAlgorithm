package week15_questions;

import java.util.Arrays;

public class Node {
	int level; 
	int vertexId;
	int prevId;
	int accDistance;
	int lowBound;
	boolean [] visited;

	public Node(int l,int v, int p, int d, int nV) {
		level =l;vertexId=v;prevId=p;accDistance=d;lowBound=0;
		visited=new boolean [nV];
		Arrays.fill(visited, false);
	}
	public String toString() {
		return "Level : "+level+"  Vertex : "+vertexId+ "  Acc.Distance : "+accDistance+"  Low Bound : "+lowBound;
	}
	// code added
	
	public int compareTo(Node that) {
		if ((this.lowBound+this.accDistance)>(that.lowBound+that.accDistance)) 
			return 1;
		else if ((this.lowBound+this.accDistance)<(that.lowBound+that.accDistance)) 
			return -1;
		else 
			return 0;
	}
}
