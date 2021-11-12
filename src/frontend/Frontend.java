package frontend;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import structures.Graph;
import structures.Node;
import structures.Pair;

public class Frontend extends JPanel {
	private class GraphicGraph{
	//Retain the arcs in correlation with nodes
	public void retainLines (JFrame f,ArrayList < Pair <Pair<Integer,Integer>, Pair<Integer,Integer> > > gLines,ArrayList<GraphicNode> gNodes, Graph aGraph) {
			for(Integer i = 0;i < graph.getSize(); ++i) {
				Integer counter = 0;
		    	GraphicNode origin = new GraphicNode();
		    	GraphicNode currentGNode = new GraphicNode();
				for(Node currentNode : graph.getGraph()[i])  { 
					
					GraphicNode equivalent = searchGNodes(currentNode.getNodeNumber(), gNodes);
					
					if(counter == 0 ) {
		    			origin = equivalent;
		    		}
		    		else {
		    			currentGNode = searchGNodes(currentNode.getNodeNumber(),gNodes);
		    			Pair<Integer,Integer> originCoordonates = new Pair<>(origin.getGraphicNodeX(),origin.getGraphicNodeY());
		    			Pair<Integer,Integer> currentGNodeCoordonates = new Pair<>(currentGNode.getGraphicNodeX(),currentGNode.getGraphicNodeY());
		    			Pair< Pair<Integer, Integer>, Pair<Integer,Integer> > arcCoordonates = new Pair<>(originCoordonates,currentGNodeCoordonates);
		    			gLines.add(arcCoordonates);
		    		}
		    		++counter;
		    	}}
		}
	 //Memorize the graphic components in a specific pattern
    public void retainInListShape(JFrame f, ArrayList<GraphicNode> gNodes, ArrayList<Boolean> visited) {
			Integer i = 0;
	    	while(i < graph.getSize()){
	    		Iterator<Node> itr = graph.getGraph()[i].iterator();
	    		Integer betweenDistanceX = 50;
	    	    	
	    		while (itr.hasNext())  
	            { 
	    			Node currentNode = itr.next(); 	
	                if(!visited.get(currentNode.getNodeNumber())) {
	                	visited.set(currentNode.getNodeNumber(), true);
	                	Integer x;
	                	Integer y = (i+1) * 30;
	                	Integer w = 30,h = 30;
	                	
	                	if( i % 2 == 0 ) {
	                		x = betweenDistanceX + 45;
	               	 	}
	                	else {
	                		x = betweenDistanceX - 30;
	                	}
	                	GraphicNode gNode = new GraphicNode(currentNode.getNodeNumber(),x,y,w,h,Color.BLACK,f);
	                	gNode.setFillGraphicNode(true);
	                	gNode.setGraphicNodeFillColor(new Color(252,70,203));
	                	gNodes.add(gNode);
	                }
	                
	                betweenDistanceX += 100;
	                
	            }
	    		++i;
	    	}
		}
	public void retainInCircleShape(JFrame f,ArrayList<GraphicNode> gNodes, ArrayList<Boolean> visited) {
			Integer radius = 300;
			
			for(Integer i = 0;i < graph.getSize(); ++i) {
	    	for(Node currentNode : graph.getGraph()[i])  { 
	    		 if(!visited.get(currentNode.getNodeNumber())) {
	             	visited.set(currentNode.getNodeNumber(), true);
	    			//Set in the middle of the window
	    			Integer centeredX = f.getWidth()/2 - radius;
	    			Integer centeredY = f.getHeight()/2 - radius;

	    			double t = 2 * Math.PI * (i+1) / graph.getSize();
	                Integer x = (int) Math.round(250 + radius * Math.cos(t)) + centeredX;
	                Integer y = (int) Math.round(250 + radius * Math.sin(t)) + centeredY;
	                Integer w = 30,h = 30;
	                
	                GraphicNode gNode = new GraphicNode(currentNode.getNodeNumber(),x,y,w,h,Color.BLACK,f);
	                	
	                gNode.setFillGraphicNode(true);
	                gNode.setGraphicNodeFillColor(new Color(252,70,203));
	                	
	                gNodes.add(gNode);
	          }}}
		}
	public void retainInRandomShape(JFrame f,ArrayList<GraphicNode> gNodes, ArrayList<Boolean> visited) {
			
			for(Integer i = 0;i < graph.getSize(); ++i) {
	    	for(Node currentNode : graph.getGraph()[i])  { 
	    		 if(!visited.get(currentNode.getNodeNumber())) {
	             	visited.set(currentNode.getNodeNumber(), true);
	    			//Set in the middle of the window
	    			Random random = new Random();
	    			Integer randomX = random.nextInt(f.getWidth());
	    			Integer randomY = random.nextInt(f.getHeight());
	    			
	                Integer x = randomX;
	                Integer y = randomY;
	                Integer w = 30,h = 30;
	                
	                GraphicNode gNode = new GraphicNode(currentNode.getNodeNumber(),x,y,w,h,Color.BLACK,f);
	                	
	                gNode.setFillGraphicNode(true);
	                gNode.setGraphicNodeFillColor(new Color(252,70,203));
	                	
	                gNodes.add(gNode);
	          }}}
		}
}	
	//Constructors
	public Frontend() {
		this.graph = null;
	}
	public Frontend (Graph aGraph){
		this.graph = aGraph;
	}
	public Frontend (Graph aGraph, Integer numbersRows, Integer numberColumns) {
		this.graph = aGraph;
		this.numberColumns = numberColumns;
		this.numberRows = numbersRows;
	}
	
	//Function that find the correspondent of a Node by number in a gNodes list ( graphic Node list) 
	GraphicNode searchGNodes(Integer aNumber,ArrayList<GraphicNode> gNodes) {
		for(GraphicNode current : gNodes) {
			if(current.getInsideValue().equals(aNumber)) {
				return current;
			}
		}
		return new GraphicNode();
	}

	public void oldDrawGraphicFunction() {
		//Define a window and his size
		JFrame f = new JFrame("Algoritmica Grafurilor");
		GraphicGraph graphicGraph = new GraphicGraph();
        f.setSize(1280, 720 );
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
        //IDK if I need gNodes
        ArrayList < Pair <Pair<Integer,Integer>, Pair<Integer,Integer> > > gLines = new ArrayList < Pair <Pair<Integer,Integer>, Pair<Integer,Integer> > >();
        ArrayList<GraphicNode> gNodes = new ArrayList<>();
        ArrayList<Boolean> visited = new ArrayList<>(this.graph.getSize());

        //Set all values in visited to false
        for (int i = 0; i < this.graph.getSize(); i++)
   		 	visited.add(false); 
        
        	//Sent the graph to the front end
    		Pair<Integer, Integer> position1 = new Pair<>(2,3);
    	
    		ArrayList<Node> aList = graph.predecesorList(graph.getNode(position1));
    		//ArrayList<Node> aList = a.DFSStartFinish(graph1.getNode(position1),graph1.getNode(position2));
    	
    	
    	graphicGraph.retainInRandomShape(f,gNodes, visited);
    	graphicGraph.retainLines(f,gLines,gNodes, this.graph);
    	
    	GraphicNode.drawCompleteGraph(f, gNodes, gLines);
           
        f.setVisible(true);
    }
	
	public void draw() {
		SwingUtilities.invokeLater(new Runnable() //new Thread()
				{
		            public void run() 
		            {
		            	initUI(); 
		            }
		        });		
	}
	
	private void initUI() {
		//Define a window and his size
				JFrame f = new JFrame("Algoritmica Grafurilor");
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 
				GraphicMatrix graphicGraph = new GraphicMatrix();
				graphicGraph.setNumberColumns(this.numberColumns);
				graphicGraph.setNumberRows(this.numberRows);
				graphicGraph.graph = this.graph;
				
				f.setSize(graphicGraph.getSquareWidth() * graphicGraph.getNumberColumns() + 20, graphicGraph.getSquareHeight()*graphicGraph.getNumberRows()+50);
				
				f.getContentPane().add(graphicGraph, BorderLayout.CENTER);
				
				f.setVisible(true);

	}
	
	//Private members:
	private GraphicNode graphicNode;
	private Graph graph;
	private Integer numberRows;
	private Integer numberColumns;
}
