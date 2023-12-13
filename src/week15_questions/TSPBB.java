package week15_questions;

//60211694 전충영

public class TSPBB {
	int [][] AdjacentMatrix;
	int numberVertex;
	
	public TSPBB(int[][] input) {
		AdjacentMatrix = input;
		numberVertex=AdjacentMatrix.length;
	}
	
	private int bound(int level) {
		int res =0;
		for (int i=level;i<numberVertex;i++) {
			int min=999;
			for (int j=0;j<numberVertex;j++) {
				if (i!=j && AdjacentMatrix[i][j]<min)
					min=AdjacentMatrix[i][j];
			}
			res += min;
		}
		return res;
	}
	
	public int minDistance(int start) {

		int returnPoint = start;
		Node startNode = new Node(0, start, -1, 0, numberVertex);
		startNode.lowBound = bound(0);
		startNode.visited[start]=true;
		DEPQueue q = new DEPQueue();
		q.add(startNode);

		int currentMinDistance = 999;

		while(!q.isEmpty()) {
		}
		return currentMinDistance;
	}

	public static void main(String[] args) {
		int [][] input = { {0,10,10,30,25}, {10,0,14,21,10}, 
				{10,18,0,7,9}, {8,11,7,0,3}, {14,10,10,3,0} };
		
		TSPBB me = new TSPBB(input);
		System.out.println("Result = "+me.minDistance(0));

	}
}
