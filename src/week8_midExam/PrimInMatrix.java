package week8_midExam;

//Question 2
//
//이름 학번
//전충영 60211694


import java.util.*;

public class PrimInMatrix  {

	public class Node {
		String key;
		public int dist;
		public Node (String k, int n) {
			key =k;
			dist=n;
		}
		public boolean equals(Node other) {
			return this.key.equals(other.key);
		}
		public int compareTo(Node other) {
			return this.key.compareTo(other.key);
		}
		public String toString() {
			return key+"("+dist+") ";
		}
	}

	protected ArrayList<String> vertices;
	protected ArrayList<Integer> outDegree;
	protected ArrayList<LinkedList<Node>> adjacentList;

	boolean [] visited;

//	protected static int[][]  edges;

	HashSet<String> V, S;
	int[] d;


	private void createGraph(String[] cities) {
		vertices = new ArrayList<>();
		adjacentList = new ArrayList<>();
		outDegree = new ArrayList<>();
	}

	private void showSelectedEdges() {
	}

	private void prim(String seoul) {
		V = new HashSet<>();
		S = new HashSet<>();
		d = new int[vertices.size()];
		Arrays.fill(d, 9999);
		for(String s : vertices) V.add(s);
		int r=vertices.indexOf(seoul);
		d[r] = 0;

		prim();
	}

	public void prim() {

		System.out.println("\n[Minimal Spanning Tree : Prim Algoritym]\n");

		while(S.size() < V.size()) {
			System.out.println("Set S : "+S);
			String u = extractMin(diff(V,S)); // diff(v,s) == v-s
			S.add(u);
			for(String v : adjacent(u)) {
				HashSet<String> temp = diff(V,S);
				int wuv = getWeight(u,v);
				int dv = d[vertices.indexOf(v)];
				if (temp.contains(v) && wuv < dv) d[vertices.indexOf(v)] = wuv;
			}
		}
	}

	public Set<String> adjacent(String v){
		Set<String> retSet = new HashSet<String>();
		int vi = indexOf(v);
		for (int i=0;i<adjacentList.get(vi).size();i++) {
			retSet.add(adjacentList.get(vi).get(i).key);
		}
		return retSet;
	}

	private String extractMin(HashSet<String> diff) {
		String minVertex = null;
		int min = 99999;
		for(String s : diff) {
			if(d[vertices.indexOf(s)]<min) {
				minVertex = s;
				min  = d[vertices.indexOf(s)];
			}
		}
		return minVertex;
	}

	public int getWeight(String u, String v) {
		int ui = indexOf(u);
		LinkedList<Node> list =  adjacentList.get(ui);
		for(int i=0; i<list.size(); i++) if(list.get(i).key.equals(v)) return list.get(i).dist;
		return -1;
	}

	public int indexOf(String u) {
		for (int i=0;i<vertices.size(); i++) {
			if (vertices.get(i).equals(u))
				return i;
		}
		return -1;

	}

	public HashSet<String> diff(HashSet<String> s1 , HashSet<String> s2) {
		HashSet<String> result = (HashSet<String>) s1.clone();
		for(String s : s2) result.remove(s);
		return result;
	}

	private void showGraph() {
		for (int i=0; i<vertices.size();i++) {
			System.out.print(vertices.get(i) +" [ "+outDegree.get(i)+" ] ");

			for (int j=0;j<adjacentList.get(i).size();j++) {
				System.out.print(" => "+adjacentList.get(i).get(j).toString());
			}
			System.out.println();
		}
	}

	public void insertVertex(String v) {
		if (!vertices.contains(v)) {
			vertices.add(v);
			outDegree.add(0);
			adjacentList.add(new LinkedList<Node>());
		}
	}

	private void insertEdge(String city, String city1, int i) {
		insertVertex(city);
		insertVertex(city1);
		int ui=indexOf(city);
		int vi=indexOf(city1);

		adjacentList.get(ui).add(new Node(city1, i));
		int dgr =outDegree.get(ui);
		outDegree.set(ui,dgr+1);

		adjacentList.get(vi).add(new Node(city, i));
		dgr =outDegree.get(vi);
		outDegree.set(vi,dgr+1);
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
		PrimInMatrix g = new PrimInMatrix();

		System.out.println("<< Prim Algorithm using GraphInMatrix >>\n");
		
		g.createGraph(cities);
		
		for (int i=0; i<paths.length;i++)
			g.insertEdge(cities[paths[i][0]], cities[paths[i][1]], paths[i][2]);
		
		g.showGraph();
		
		g.prim("Seoul");
		
		g.showSelectedEdges();
		
	}


}