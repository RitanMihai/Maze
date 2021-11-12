package mainPck;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;

import backend.*;
import structures.*;
import frontend.*;

public class Main extends JPanel {
	public static void main(String[] args) throws FileNotFoundException {
	
	//We read our file with that contains the matrix
	Scanner matrixFile = new Scanner(new File("data/input.in"));
	
	//Initialize our backend with the matrix
	Solution solution = new Solution(matrixFile);
	
	//Convert the raw data into a graph
	solution.convertMatrixInGraph();
	
	//Retains the graph into a variable 
	Graph graph = solution.getGraph();
		
	Frontend frontend = new Frontend(graph,solution.rowsNumber,solution.columnsNumber);
	frontend.draw();
}	 
}
