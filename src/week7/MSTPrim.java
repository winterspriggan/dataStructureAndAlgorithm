package week7;

import week7.GraphInList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class MSTPrim extends GraphInList {

    HashSet<String> V, S;
    int[] d;

    public void prim(String start) {
        V = new HashSet<>();
        S = new HashSet<>();
        d = new int[vertices.size()];
        Arrays.fill(d, 9999);
        for(String s : vertices) V.add(s);
        int r=vertices.indexOf(start);
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

    public HashSet<String> diff(HashSet<String> s1 , HashSet<String> s2) {
        HashSet<String> result = (HashSet<String>) s1.clone();
        for(String s : s2) result.remove(s);
        return result;
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

        MSTPrim g = new MSTPrim();
//        Arrays.fill(edges, 999);

        System.out.println("<< GraphInMatrix >>");

        g.createGraph();

//       for(i) g.insertVertex(cities[i]);


        for (int i = 0; i < paths.length; i++)
            g.insertEdge(cities[paths[i][0]], cities[paths[i][1]], paths[i][2]);

        System.out.println(g.adjacent("Seoul"));

//        g.showGraph2();
        g.showGraph();

//        g.DFS("Seoul");

        System.out.println("\n\n");

//        g.BFS("Seoul");

        g.prim("Seoul");
    }

}
