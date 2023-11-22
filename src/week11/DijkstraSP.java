package week11;

import week12.DirectedGraphInList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class DijkstraSP extends DirectedGraphInList {
    HashSet<String> V, S;
    int [] d;
    int r = -1;
    String prev;
    int max;


    public void init(String start) {
        max = vertices.size();
        V= new HashSet<String>();
        S= new HashSet<String>();
        d=new int[vertices.size()];
        Arrays.fill(d,  9999);
        for (String s : vertices)
            V.add(s);
        int r=vertices.indexOf(start);
        d[r]=0;
    }

    private void dijkstra(String start) {
        init(start);
        System.out.println("\n[New Version of Prim Algorithm]\n");

        while(S.size()<V.size()) {
            String u = extractMin(diff(V,S));
            S.add(u);
            System.out.println("Set S : "+ S);
            for(String v : adjacent(u)) {
                HashSet<String> temp = diff(V, S);
                int wuv = getWeight(u,v);
                int du = d[vertices.indexOf(u)];
                int dv = d[vertices.indexOf(v)];
                if( temp.contains(v) && (du+wuv < dv))
                    d[vertices.indexOf(v)] = du+wuv;
            }
        }
        showD();  // DO NOT DELETE THIS LINE !!
    }
    private void showD() {
        System.out.print(" d[] values : " );
        for (int i=0;i<vertices.size();i++)
            System.out.print(d[i]+"  ");
        System.out.println();

    }

    private String extractMin(HashSet<String> diff) {
        String minVertex = null;
        int min = 9999;
        for (String s : diff) {
            if (d[vertices.indexOf(s)]<min) {
                minVertex = s;
                min = d[vertices.indexOf(s)];
            }
        }
        return minVertex;
    }

    private HashSet<String> diff(HashSet<String> s1, HashSet<String> s2) {  // s1-s2
        HashSet<String> result =new HashSet<String>();
        result =(HashSet<String>) s1.clone();
        for (String s : s2)
            result.remove(s);

        return result;
    }

    private int getWeight(String u, String v) {
        int ui= indexOf(u);
        LinkedList<Node> list = adjacentList.get(ui);
        for (int i=0; i<list.size(); i++) {
            if (list.get(i).key.equals(v))
                return list.get(i).num;
        }
        return -1;
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

        DijkstraSP g = new DijkstraSP();

        System.out.println("<< GraphInList >>");

        g.createGraph();

        for (int i=0; i<paths.length;i++)
            g.insertEdge(cities[paths[i][0]], cities[paths[i][1]], paths[i][2]);

        g.showGraph();

        g.dijkstra("Seoul");

    }

}
