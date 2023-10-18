package week7;

import week5.GraphInList;

import java.util.*;

public class MSTKruscal extends GraphInList {


    public class Edge {
        String from;
        String to;
        int weight;
        public Edge(String from, String to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        public String toString() {
            return this.from+"->"+this.to+"("+this.weight+")";
        }
    }
    HashSet<Edge> T;
    LinkedList<Edge> Q;
    ArrayList<String> parent;

    public MSTKruscal() {
        this.Q = new LinkedList<>();
        this.T = new HashSet<>();
        this.parent = new ArrayList<>();
    }

    public void insertVertex(String v) {
        if(!vertices.contains(v)) {
            vertices.add(v);
            outDgree.add(0);
            adjacentList.add(new LinkedList<Node>());
            parent.add(v);
        }
    }

    public void deleteVertex(String v) {
        int vi =indexOf(v);
        if(vi>=0) {
            for(int i=0; i<vertices.size(); i++) {
                deleteEdge(v, vertices.get(i));
                deleteEdge((vertices.get(i),v));
            }
            adjacentList.remove(vi);
            outDegree.remove(vi);
            vertices.remove(vi);
            parent.remove(vi);
        }
    }

    public void insertEdge(String u, String v, int dist) {
        insertVertex(u);
        insertVertex(v);
        int ui = indexOf(u);
        int vi = indexOf(v);

        adjacentList.get(ui).add(new Node(v, dist));
        int dgr = outDegree.get(ui);
        outDegree.set(ui, dgr+1);

        adjacentList.get(vi).add(new Node(u, dist));
        dgr = outDegree.get(vi);
        outDegree.set(vi, dgr+1);

        sortInsert(new Edge(u,v,dist));
    }

    public void sortInsert(Edge newEdge) {
        int index = 0;
        Iterator<Edge> iter = Q.iterator();
        while(iter.hasNext()) {
            if (newEdge.weight>iter.next().weight)index++;
        }
        Q.add(index, newEdge);
        showQ();
    }

    private void showQ() {
        System.out.print("\n>>> Q : ");
        for(Edge e : Q) System.out.print(" -> "+e.weight);
    }

    public void kruscal() {
        System.out.println("\n[Minimal Spanning Tree : Kruscal Algorithm]\n");

        while(T.size()<vertices.size()-1) {
            Edge euv = Q.remove(0);
            if (findSet(euv.from)!= findSet(euv.to)) {
                union(euv.from, euv.to);
                T.add(euv);
            }
        }
        System.out.println("\n [Final T] "+T);
    }

    private String findSet(String u) {
        String p = parent.get(indexOf(u));
        if (p == u) return u;
        else return findSet(p);
    }

    private void union(String u, String v) {
        parent.set(vertices.indexOf(v) , parent.get(vertices.indexOf(u)));
    }

    public static void main(String[] args) {
        String[] cities = {"Seoul", "Incheon", "Daejeon", "Daegu", "Kwangju", "Pusan", "Ulsan", "Mokpo", "Chuncheon", "Kyeongju"};
        int[][] paths = {
                {0, 1, 59}, {0, 2, 140}, {0, 3, 237}, {0, 7, 313}, {0, 8, 75},
                {1, 7, 295}, {1, 8, 105},
                {2, 3, 122}, {2, 4, 141},
                {3, 4, 173}, {3, 5, 88}, {3, 6, 74}, {3, 8, 236},
                {4, 5, 202}, {4, 7, 57},
                {5, 9, 76},
                {6, 8, 293}, {6, 9, 36}
        };

        MSTKruscal g = new MSTKruscal();
        Arrays.fill(edges, 999);

        System.out.println("<< GraphInMatrix >>");

        g.createGraph();

//       for(i) g.insertVertex(cities[i]);


        for (int i = 0; i < paths.length; i++)
            g.insertEdge(cities[paths[i][0]], cities[paths[i][1]], paths[i][2]);

        System.out.println(g.adjacent("Seoul"));

        g.showGraph2();
        g.showGraph();

//        g.DFS("Seoul");

        System.out.println("\n\n");

//        g.BFS("Seoul");

        g.kruscal();
    }
}
