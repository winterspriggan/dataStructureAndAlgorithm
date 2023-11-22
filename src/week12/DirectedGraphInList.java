package week12;

import week9.GraphInList;

import java.util.*;

public class DirectedGraphInList {

	public class Node {
		public String key;
		public int num;
		int dist ;

		public Node (String k, int n) {
			key =k;
			dist=n;
		}

		public boolean equals(GraphInList.Node other) {
			return this.key.equals(other.key);
		}

		public int compareTo(GraphInList.Node other) {
			return this.key.compareTo(other.key);
		}

		public String toString() {
			return key+"("+dist+") ";
		}
	}

	// define Vertices, adjacentList, visited
	protected ArrayList<String> vertices;
	protected ArrayList<Integer> outDegree;
	protected ArrayList<LinkedList<Node>> adjacentList;

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
			adjacentList.get(ui).remove(new Node(v, 0));
			int dgr =outDegree.get(ui);
			outDegree.set(ui,dgr-1);
			System.out.println(">> degree : "+outDegree.get(ui));

			adjacentList.get(vi).remove(new Node(u, 0));
			dgr =outDegree.get(vi);
			outDegree.set(vi,dgr-1);
			System.out.println(">> degree : "+outDegree.get(vi));

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
		int [][] paths1 = {
				{1,7,295},{1,8,105},
				{2,3,122},{2,4,141},
				{3,4,173},{3,5,88},{3,6,74},{3,8,236},
				{4,5,202},{4,7,57},
				{5,9,76},
				{6,8,293},{6,9,36}
		};
		int [][] paths2 = {
				{0,1,59},{0,2,140},{0,3,237},{0,7,313},{0,8,75},
		};

			DirectedGraphInList g = new DirectedGraphInList();
			
			System.out.println("<< Directed Graph In List for Topological Sort >>");

			g.createGraph();

			System.out.println("\n<< Directed Graph without Seoul >>\n");

			for (int i=0; i<paths1.length;i++)
				g.insertEdge(cities[paths1[i][0]], cities[paths1[i][1]], paths1[i][2]);
			
			g.showGraph();
			
			System.out.println("\n<< Directed Graph After  Seoul is added >>\n");
			
			for (int i=0; i<paths2.length;i++) g.insertEdge(cities[paths2[i][0]], cities[paths2[i][1]], paths2[i][2]);
			
			g.showGraph();

			System.out.println("\n<< Directed Graph After  Seoul is deleted >>\n");

			g.deleteVertex("Seoul");
			
			g.showGraph();
		}

	} 