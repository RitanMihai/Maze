package backend;

//For using Scanner Class 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
* A class for a basic Graph
* A custom Node class for the above Graph
* A template class Pair 
*/

import structures.*;

public class Solution {
	//Constructors
	public Solution(){
		this.matrixRawData = null;
	}
	public Solution(Scanner aFile){
		this.matrixRawData = aFile;
	}
	
	//Getters
	public Graph getGraph() {
		return this.graph;
	}
	//Setters
	void setGraph(Graph aGraph) {
		this.graph = aGraph;
	}
	
	//Functions 
	public void addEdgeToGaph(Graph graph,Node matrix[][], int i, int j) {
		try {
			if(matrix[i][j-1].getNodeValue() > 0) 
				graph.addEdge(matrix[i][j], matrix[i][j-1]);
		}
		catch(ArrayIndexOutOfBoundsException e) {}
			
		try {
			if(matrix[i][j+1].getNodeValue() > 0) 
				graph.addEdge(matrix[i][j], matrix[i][j+1]);
		}
		catch(ArrayIndexOutOfBoundsException e) {}
		
			
		try {
			if(matrix[i-1][j].getNodeValue() > 0) 
				graph.addEdge(matrix[i][j], matrix[i-1][j]);
		}
			catch(ArrayIndexOutOfBoundsException e) {}
			
		try {
			if(matrix[i+1][j].getNodeValue() > 0) 
				graph.addEdge(matrix[i][j], matrix[i+1][j]);
			}
		catch(ArrayIndexOutOfBoundsException e) {}
		
	}

	public void convertMatrixInGraph() {
	int rowsNumber, columnsNumber;

	rowsNumber = matrixRawData.nextInt();
	this.rowsNumber = rowsNumber;
	columnsNumber = matrixRawData.nextInt();
	this.columnsNumber = columnsNumber;
	
	//Create a matrix with the dimension of rowsNumber * columnsNumber
	Node [][] matrix = new Node [rowsNumber][columnsNumber];
			
			//Copy the data from file into a matrix and adapt the data
			Integer counterNodeNumber = 0;
			
			
			for (Integer i = 0; i < rowsNumber; ++i) {
				for (Integer j = 0; j < columnsNumber; ++j) {
					int currentNodeValue = 	matrixRawData.nextInt();
					Node currentNode = new Node();
					
					if(currentNodeValue > 0)
					{
						Pair<Integer, Integer> position = new Pair<Integer, Integer>(i,j);
						currentNode = new Node(currentNodeValue, counterNodeNumber , position);
						++counterNodeNumber;
					}
					
					matrix[i][j] = currentNode;
					
				}
			}
			
			//Create a new graph with the dimension of the all valid numbers in our matrix 
			graph = new Graph(counterNodeNumber);
			
			for (Integer i = 0; i < rowsNumber; ++i) {
				for (Integer j = 0; j < columnsNumber; ++j) {
					if(!(matrix[i][j].getNodeValue().equals(0))) {
						graph.addEdge(matrix[i][j], matrix[i][j]);
						addEdgeToGaph (graph, matrix,i,j);				
					
					}
				}
			}
			
			matrixRawData.close();
	}
	
	private Scanner matrixRawData;
	private Graph graph;
	public Integer rowsNumber;
	public Integer columnsNumber;
}