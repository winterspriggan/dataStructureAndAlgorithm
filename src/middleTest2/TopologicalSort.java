//package middleTest2;
//
//import java.util.ArrayDeque;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Deque;
//import java.util.LinkedList;
//
//public class TopologicalSort extends DirectedGraphInList {
//
//    boolean result;
//	public void tSort1() {
//
//
//
//        System.out.println(result);
//	}
//
//
//	private void deleteVertexNEdges(String string) {  // The vertex to be deleted must be of inDegree=0 !
//
//
//
//
//		showGraph();
//	}
//
//
//	private String getNextNode(ArrayList<String> result) {
//
//
//
//
//	}
//
//	public void tSort2() {
//
//
//
//
//
//		System.out.println(result);
//	}
//
//
//	private void dfsTS(boolean[] visited, String s, LinkedList<String> result) {
//
//
//
//
//	}
//
//	public void tSort3() {  // bfs-like algorithm
//
//
//
//
//
//
//
//
//		System.out.println(result);
//	}
//
//	public static void main(String[] args) {
//		String [] tasks = { "water", "ignition", "openPack", "noodle", "soup", "egg"};
//		int [][] graphEdges = { {0,1}, {1,3}, {1,4}, {1,5},
//				                {2,3}, {2,4}, {3,5}, {4,5} };
//
//		TopologicalSort myGM = new TopologicalSort();
//
//		myGM.createGraph();
//
//		for (int i = 0; i<graphEdges.length; i++)
//			myGM.insertEdge(tasks[graphEdges[i][0]],tasks[graphEdges[i][1]], 1);
//
//		System.out.println("\n<< Topological Sort1 >>\n ");
//		System.out.println(">>> Initial Graph\n");
//		myGM.showGraph();
//		myGM.tSort1();
//
//		TopologicalSort myGM2 = new TopologicalSort();
//
//		myGM2.createGraph();
//
//		for (int i = 0; i<graphEdges.length; i++)
//			myGM2.insertEdge(tasks[graphEdges[i][0]],tasks[graphEdges[i][1]],1);
//
//		System.out.println("\n<< Topological Sort2 >>\n ");
//		System.out.println("<< Initial Graph>>\n");
//		myGM2.showGraph();
//		myGM2.tSort2();
//
//		System.out.println("\n<< Topological Sort3 >>\n ");
//		System.out.println("<< Initial Graph>>\n");
//		myGM2.showGraph();
//		myGM2.tSort3();
//
//	}
//
//}
