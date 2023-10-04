package week5;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class GraphInMatrix {  // Vertices # is fixed, Undirected Graph in Matrix

    String[] vertices;
    int[][] edges; // adjacency matrix
    boolean[] visited;


    public void createGraph(String [] input) {
        vertices = input;
        edges = new int[vertices.length][vertices.length]; // 초기값 0 default
        visited = new boolean[vertices.length]; //초기값 false;
    }

    public void insertVertex(String v) { // N.A
    }
    public void deleteVertex(String v) { // N.A
    }

    public void insertEdge(String u, String v, int dist) {
        int ui = indexOf(u);
        int vi = indexOf(v);

        edges[ui][vi] = dist;
        edges[vi][ui] = dist; //대칭 directed graph는 이거 해줘야됨

    }

    public int indexOf(String u) {
        for(int i=0; i<vertices.length; i++) {
            if(vertices[i].equals(u)) return  i;
        }
        return -1;
    }

    public void deleteEdge(String u, String v) {
        int ui = indexOf(u);
        int vi = indexOf(v);

        edges[ui][vi] = 0;
        edges[vi][ui] = 0; //대칭 directed graph는 이거 해줘야됨
    }

    public boolean isEmpty() {
        return (vertices.length==0);
    }

    public Set<String> adjacent(String v){
        Set<String> set = new HashSet<>();
        int vi = indexOf(v);
        for(int i=0; i<vertices.length; i++) {
            if(edges[vi][i]!=0) set.add(vertices[i]);
        }
        return set;
    }

    public void showGraph() {
        for (int i=0; i<vertices.length;i++) {
            System.out.printf("%10s >> ",vertices[i]);
            for (int j=0;j<vertices.length;j++) {
                String temp = "----------";
                if (edges[i][j]!=0)
                    temp=vertices[j];
                System.out.printf("%12s ",temp);
            }
            System.out.println();
        }
    }

    public void showGraph2() {
        for (int i=0; i<vertices.length;i++) {
            for (int j=0;j<vertices.length;j++) {
                System.out.printf("%10d ", edges[i][j]);
            }
            System.out.println();
        }
    }

    public void DFS(String v) {
        initVisited();
        System.out.println("\n*** DFS Recursion ***\n");
        DFSRecursion(v);
    }
    public void initVisited() {
        for(int i=0; i<vertices.length; i++) visited[i] = false;
    }

    private void DFSRecursion(String v) {
        int vi = indexOf(v);
        visited[vi] = true;
//        for(int j=0; j< visited.length; j++) {
//            if(edges[vi][j] != 0 && !visited[j]) DFSRecursion(vertices[j]);
//        }
        for(String u : adjacent(v)) {
            if(!visited[indexOf(u)]) DFSRecursion(u);   //위에랑 결과가 다름 다르다고 틀린게 아님, 마지막만 같으면 된다.
        }
        System.out.println(v + " is visited !");
    }

    public void BFS(String v) {
        initVisited();
        System.out.println("*** BFS Iteration ***\n");
        Deque<String> que = new ArrayDeque<>();

        visited[indexOf(v)] = true;
        System.out.println(v + " is visited !");
        que.add(v);
        while(!que.isEmpty()) {
            String u = que.poll();
            for(String w : adjacent(u)) {
                int wi = indexOf(w);
                if(!visited[wi]) {
                    visited[wi] = true;
                    System.out.println(w + " is visited !");
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

        GraphInMatrix g = new GraphInMatrix();

        System.out.println("<< GraphInMatrix >>");

        g.createGraph(cities);

        for (int i=0; i<paths.length;i++)
            g.insertEdge(cities[paths[i][0]], cities[paths[i][1]], paths[i][2]);

        System.out.println(g.adjacent("Seoul"));

        g.showGraph2();
        g.showGraph();

        g.DFS("Seoul");

        System.out.println("\n\n");

        g.BFS("Seoul");

    }

}