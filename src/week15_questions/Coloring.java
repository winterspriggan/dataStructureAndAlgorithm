package week15_questions;

import java.util.Arrays;

//  id-number & name
//60211694 전충영

public class Coloring extends GraphInMatrix {

	int [] color ;
	int numberOfVertex;
	public Coloring(int size) {
		numberOfVertex=size;
		color = new int[numberOfVertex];
		Arrays.fill(color, 0);
	}
	public int minColors() {
		int nColor=1;
		while (!kcoloring(0,0, nColor)) {
			nColor++;
		}
		return nColor;
	}
	public boolean kcoloring(int i, int c, int maxColor) {
		boolean result;
		if ( valid(i, c) ) {
	 // Q 1-3 : Complete method 'kcoloring'

			//kcolor =true
			if(i+1 < vertices.length) {
				for(int k=0; k<c; k++) {
					color[i] = k;
					return kcoloring(i+1 , c , maxColor);
				}
				}
			return true;
		}
		else
			return false;
	}
	
	private boolean valid(int i, int c) {
	// Q 1-2 : Complete method 'valid'
		for(int j=0; j<i; j++) {
			if(edges[i][j]==1) if(c == color[i]) return false;
		}
		return true;
	}
	
	public void showColor() {
		for (int i=0; i<numberOfVertex; i++)
			System.out.println("Vertex "+vertices[i]+" : "+"Color # "+color[i]);
		System.out.println();
	}

	public static void main(String[] args) {

		String [] vertices = {"A","B","C","D","E","F","G","H","I"};
        int numVertex = vertices.length  ;
        Coloring me = new Coloring(numVertex);
		String [][] edges = {{"A", "B"}, {"A", "C"}, {"A", "E"}, {"A", "H"},
				{"B" , "A"} , {"B", "C"}, {"B", "D"}, {"B", "G"},
				{"C", "A"}, {"C", "B"}, {"C", "D"}, {"C", "E"}, {"C", "F"},
				{"D", "B"}, {"D", "C"}, {"D", "E"}, {"D", "G"},
				{"E", "A"},{"E", "C"},{"E", "D"},{"E", "F"}, {"E", "G"}, {"E", "H"}, {"E", "I"},
				{"F", "C"},{"F", "E"},
				{"G", "B"},{"G", "D"},{"G", "E"},{"G", "I"},
				{"H", "A"},{"H", "E"},{"H", "I"},
				{"I", "E"},{"I", "G"},{"I", "H"}
				// Q 1-1 : Complete input matrix
		};


		me.createGraph(vertices);

		for (int i=0; i<edges.length; i++) {
			me.insertEdge(edges[i][0],edges[i][1],1);
		}
		me.showGraph();
		System.out.println();

		System.out.println("Number of Vertices : "+numVertex);
		int minNumColors = me.minColors();
		System.out.println("Minimum Number of Color : "+minNumColors);
		System.out.println("Result : ");
		me.kcoloring(0,0,  minNumColors);
		me.showColor();




	}

}