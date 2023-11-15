package middleTest2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class GraphInList {  // Graph without Direction
	
	public class Node {
		String key;
		int num ;  
		
		public Node (String k, int n) {
				key =k;
				num=n;
		}

		public boolean equals(Node other) {
			return this.key.equals(other.key);
		}
		
		public int compareTo(Node other) {    
			return this.key.compareTo(other.key);
		}
		
		public String toString() {
			return key+"("+num+") ";
		}
	}
	
	// define Vertices, adjacentList, visited
	ArrayList<String> vertices;
	ArrayList<Integer> outDegree;
	ArrayList<LinkedList<Node>> adjacentList;
	
	boolean [] visited;
			
	public void createGraph() {
		vertices = new ArrayList<>();
		adjacentList = new ArrayList<>();
		outDegree = new ArrayList<>();
	}
	
	public int indexOf(String u) {
		for (int i=0;i<vertices.size(); i++) {
			if (vertices.get(i).equals(u))
				return i;
		}
		return -1;

	}
	
	public void insertVertex(String v) {
		if (!vertices.contains(v)) {
			vertices.add(v);
			outDegree.add(0);
			adjacentList.add(new LinkedList<Node>());
		}
	}
	
	public void deleteVertex(String v) {
		int vi=indexOf(v); // Vertices.indexOf(v)
		if (vi>=0) {
			for (int i=0;i<vertices.size();i++) {
				deleteEdge(v, vertices.get(i));
				deleteEdge(vertices.get(i), v);
			}
			adjacentList.remove(vi);
			outDegree.remove(vi);
			vertices.remove(vi);
		}
	}
	
	public void insertEdge(String u, String v, int dist) {
		insertVertex(u);
		insertVertex(v);
		int ui=indexOf(u); 
		int vi=indexOf(v); 
		
		adjacentList.get(ui).add(new Node(v, dist));
		int dgr =outDegree.get(ui);
		outDegree.set(ui,dgr+1);

		adjacentList.get(vi).add(new Node(u, dist));
		dgr =outDegree.get(vi);
		outDegree.set(vi,dgr+1);

	}
	public void deleteEdge(String u, String v) {
		int ui=indexOf(u); 
		int vi=indexOf(v); 
		if (ui>=0 && vi>=0) {
			for (int i=0; i<adjacentList.get(ui).size();i++) {
				if (adjacentList.get(ui).get(i).key.equals(v)) {
					adjacentList.get(ui).remove(i);
					int dgr =outDegree.get(ui);
					outDegree.set(ui,dgr-1);
					break;
				}
			}
			for (int i=0; i<adjacentList.get(vi).size();i++) {
				if (adjacentList.get(vi).get(i).key.equals(v)) {
					adjacentList.get(vi).remove(i);
					int dgr =outDegree.get(vi);
					outDegree.set(vi,dgr-1);
					break;
				}
			}
		}
	}		
	
	public boolean isEmpty() {
		return vertices.isEmpty();
	}
	
	public Set<String> adjacent(String v){
		Set<String> retSet = new HashSet<String>();
		int vi = indexOf(v);
		for (int i=0;i<adjacentList.get(vi).size();i++) {
				retSet.add(adjacentList.get(vi).get(i).key);
		}
		return retSet;
	}
	
	public void showGraph() {
		for (int i=0; i<vertices.size();i++) {
			System.out.print(vertices.get(i) +" [ "+outDegree.get(i)+" ] ");

			for (int j=0;j<adjacentList.get(i).size();j++) {
				System.out.print(" => "+adjacentList.get(i).get(j).toString());
			}
			System.out.println();
		}
	}
	
	public void initVisited() {
		visited = new boolean[vertices.size()];
		for (int i=0; i<vertices.size();i++) 
			visited[i] = false;		
	}

	public void DFS(String v) {
		initVisited();
		System.out.println("\n *** DFS Recursion *** \n");
		DFSRecursion(v);
	}
	private void DFSRecursion(String v) {
		visited[indexOf(v)]=true;
		for(String u:adjacent(v))
			if (!visited[indexOf(u)])
				DFSRecursion(u);
		System.out.println(v+" is visited");
	
	}

	public void BFS(String v) {
		initVisited();
		System.out.println("\n *** BFS Iteration *** \n");

		Deque<String> que = new ArrayDeque<String>();
		visited[indexOf(v)]=true;
		System.out.println(v+" is visited ");
		que.add(v);

		while (!que.isEmpty()) {
			String u = que.poll();
			
			for (String w : adjacent(u)) {
				int wi = indexOf(w);
				if (!visited[wi]) {
					visited[wi]=true;
					System.out.println(w+" is visited ");
					que.add(w);
				}
			}
		}
	}

	public static void main(String[] args) {
		
		String [] cities = { "Seoul", "Incheon", "Daejeon", "Daegu", "Kwangju", "Pusan", "Ulsan","Mokpo", "Chuncheon", "Kyeongju"};
		int [][] paths = {
				{0,1,59},{0,2,140},{0,3,237},{0,7,313},{0,8,75},
				{1,7,295},{1,8,105},
				{2,3,122},{2,4,141},
				{3,4,173},{3,5,88},{3,6,74},{3,8,236},
				{4,5,202},{4,7,57},
				{5,9,76},
				{6,8,293},{6,9,36}
		};
		
		GraphInList g = new GraphInList();
		
		System.out.println("<< GraphInList >>");

		g.createGraph();
		
		for (int i=0; i<paths.length;i++)
			g.insertEdge(cities[paths[i][0]], cities[paths[i][1]], paths[i][2]);
		
		System.out.println(g.adjacent("Seoul"));
		
		g.showGraph();
		
		g.DFS("Seoul");
		
		g.BFS("Seoul");
		
		g.deleteVertex("Seoul");
		
		g.showGraph();


	}

}