package week11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class BellmanFordSP extends DirectedGraphInList {

	public class EdgeElement{
		public String from;
		public String to;
		int weight;
		
		public EdgeElement(String u, String v, int w) {
			from = u;
			to = v ;
			weight = w;
		}
		
		public String toString() {
			return from+"->"+to+"("+weight+") ";
		}
	}

	int[] d;
	int r = -1;
	HashSet<String> S, V;
	String[] prev;
	ArrayList<EdgeElement> edgeList;


	public BellmanFordSP() {
		edgeList = new ArrayList<>();

	}

	public void insertEdge(String from, String to, int w) {
		super.insertEdge(from, to, w);
		edgeList.add(new EdgeElement(from,to,w));
	}


	private void showShortestPath() {
		for(int i=0; i<vertices.size(); i++) {
			System.out.print(prev[i]);
			System.out.println("==> "+ vertices.get(i) + " ("+d[i]+")");
		}
	}

	private void init(String start) {
		d = new int[vertices.size()];
		S = new HashSet<>();
		V = new HashSet<>();
		prev = new String[vertices.size()];
		r= vertices.indexOf(start);
		Arrays.fill(d, 9999);
		d[r] = 0;
	}

	private void ShortestPath(String start) {
		init(start);

		for(int i=0; i>vertices.size(); i++) {
			System.out.println(">>> "+i+" th iteration");
			for(EdgeElement e : edgeList) { // for all edges ..
			String u = e.from;
			String v = e.to;
			int wuv = e.weight;
			int du = d[vertices.indexOf(u)];
			int dv = d[vertices.indexOf(v)];

			if(du+wuv < dv) {

				System.out.println("  "+v+" : "+ dv+" ---> "+ du+wuv);
				d[vertices.indexOf(v)] = du+wuv;
				prev[vertices.indexOf(v)] = u;
			 }
			}
		   }
		for(EdgeElement e : edgeList) { // for all edges ..
			String u = e.from;
			String v = e.to;
			int wuv = e.weight;
			int du = d[vertices.indexOf(u)];
			int dv = d[vertices.indexOf(v)];
			if(du+wuv < dv) System.out.println(">>> Minus Cycle Found !!! => No Solution ");
		}

	}
	







		
		public static void main(String[] args) {
			String [] vertices = { "Seoul","Incheon", "Daejeon", "Daeku", "Kwangju", "Pusan", "Ulsan"};
			int [][] graphEdges = { {0, 1, 11 }, {0, 2, 8}, {0, 3, 9}, {1, 3, 13}, 
					{1, 6, 8}, {2, 4, 10}, {3, 4, 5}, {3, 5, 12}, {5, 6, 7},
					{3, 2, -3} };

			BellmanFordSP myG = new BellmanFordSP();

			myG.createGraph();
			for (int i = 0; i<graphEdges.length; i++)
				myG.insertEdge(vertices[graphEdges[i][0]],vertices[graphEdges[i][1]], graphEdges[i][2]);
			myG.showGraph();

			System.out.println("\nBellmanFord Shortest Path Algorithm ");
			myG.ShortestPath("Seoul");
			myG.showShortestPath();


			int [][] graphEdges2 = { {0, 1, 11 }, {2, 0, -8}, {0, 3, 9}, {1, 3, 13},
					{1, 6, 8}, {2, 4, 10}, {3, 4, 5}, {3, 5, 12}, {5, 6, 7},
					{3, 2, -3} };

			BellmanFordSP myG2 = new BellmanFordSP();

			myG2.createGraph();
			for (int i = 0; i<graphEdges2.length; i++)
				myG2.insertEdge(vertices[graphEdges2[i][0]],vertices[graphEdges2[i][1]], graphEdges2[i][2]);
			myG2.showGraph();

			System.out.println("\nBellmanFord Shortest Path Algorithm ");
			myG2.ShortestPath("Seoul");
			myG2.showShortestPath();
			
	
		}


}

