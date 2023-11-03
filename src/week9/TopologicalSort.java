package week9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TopologicalSort extends DirectedGraphInMatrix {


	int nOfVertices;
	private void tpSort1() {
		nOfVertices = vertices.length;
		List<String> result = new ArrayList<>();

		for(int i=0 ; i<nOfVertices; i++) {
			result.add(getNextNode(result));
			deleteVertexNEdges(result.get(i));
		}
		System.out.println(">>> Result of Topological Sort 1");
		for (String list : result) {
			System.out.print("--> " + list);
			System.out.println();
		}
	}
	private void tpSort2() {
		nOfVertices = vertices.length;
		LinkedList<String> result = new LinkedList<>();
		boolean[] visited = new boolean[nOfVertices];
		Arrays.fill(visited , false);
		for (String s : vertices) if(!visited[indexOf(s)]) dfsTS(visited, s, result);
		System.out.println(result);
	}

	private void dfsTS(boolean[] visited, String s, LinkedList<String> result) {
		visited[indexOf(s)] = true;
		for(String x : adjacent(s)) if(!visited[indexOf(x)]) dfsTS(visited , x , result);
		result.addFirst(s);
	}

	private void deleteVertexNEdges(String s) {
		int index = indexOf(s);
		for(int i=0; i<nOfVertices; i++) {
			edges[index][i] = 0;
		} // do not delete vertex
	}
	private String getNextNode(List<String> result) {
		for(int i=0; i<nOfVertices; i++) {
			int nOfIncoming = 0;
			for (int j=0; j<nOfIncoming; j++) {
				if(edges[j][i] != 0) nOfIncoming ++;
				if(nOfIncoming == 0 && !result.contains(vertices[i]))  return vertices[i];
			}
		}
	return null;
	};


	

	public static void main(String[] args) {
		int maxNoVertex = 10;
		String [] tasks = { "water", "ignition", "openPack", "noodle", "soup", "egg"};
		int [][] graphEdges = { {0,1}, {1,3}, {1,4}, {1,5}, 
				                {2,3}, {2,4}, {3,5}, {4,5} };
		
		TopologicalSort myGM = new TopologicalSort();

		myGM.createGraph(tasks);

		for (int i = 0; i<graphEdges.length; i++)
			myGM.insertEdge(tasks[graphEdges[i][0]],tasks[graphEdges[i][1]], 1);
		myGM.showGraph();
		
		System.out.println("Topological Sort1 : start ");
		myGM.tpSort1();

		TopologicalSort myGM2 = new TopologicalSort();

		myGM2.createGraph(tasks);

		for (int i = 0; i<graphEdges.length; i++)
			myGM2.insertEdge(tasks[graphEdges[i][0]],tasks[graphEdges[i][1]],1);
		myGM2.showGraph();
		
		System.out.println("Topological Sort2 : start ");
		myGM2.tpSort2();

	}



}
