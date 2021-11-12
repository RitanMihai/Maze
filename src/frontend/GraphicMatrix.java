package frontend;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import structures.Graph;
import structures.Node;

public class GraphicMatrix extends JPanel {
	
	/*Default Constructor */
	public GraphicMatrix() {
		this.squareWitdth = 100;
		this.squareHeight = 100;
		
		this.setPosX(0);
		this.setPosY(0);
	}
	
	public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	       //Draw the schema
	       Color gray = new Color (68, 68, 68);
	       for(int i = 0;i < numberRows; ++i) {
	        	for (int j = 0; j < numberColumns; ++j) {
	        		draw(g, j*this.squareWitdth + this.posX, i*this.squareHeight + this.posY, gray, Color.BLACK);
	        	}	
	       }
	       Node start = new Node();
	       ArrayList<Node> finish = new ArrayList<>();
	       
	       //Draw that exits and entry
	       ArrayList<Node> allNodesList = graph.BFS();
	       
	       for (Node current: allNodesList) {
	    	   Integer X = current.getPair().getSecond()*this.squareWitdth + this.posX;
    		   Integer Y = current.getPair().getFirst()*this.squareHeight + this.posY;
    		   
	    	   if(current.getNodeValue().equals(2)) {
	    		   draw(g, X, Y , new Color(255, 246, 173), Color.BLACK);
	    		   finish.add(current);
	    	   }
	    	   else if(current.getNodeValue().equals(3)) {
	    		   draw(g, X, Y, new Color(166, 235, 252), Color.BLACK); 
	    		   start = current;  
	    		   g.drawString("START", X + squareWitdth/3, Y + squareHeight/4);
	    	   }
	    	   else
	    	   draw(g, X, Y, new Color(255, 255, 255), Color.BLACK);
	       }
	       
	       //Draw the path
	       for(Node next : finish) {
	    	   ArrayList<Node> currentPredecesorList = graph.predecesorList(start);
	    	   //Random color for each path
	    	   Integer R = new Random().nextInt(255);
	    	   Integer G = new Random().nextInt(255);
	    	   Integer B = new Random().nextInt(255);
	    	   
	    	   while(next.getNodeNumber() != start.getNodeNumber()) { 
	    		
	    		   draw(g, next.getPair().getSecond()*this.squareWitdth + this.posX, next.getPair().getFirst()*this.squareHeight + this.posY, new Color(R,G,B), Color.BLACK);  

	          	 next = currentPredecesorList.get(next.getNodeNumber());
	    	   }
	       }
	       //Redraw exist
	       for(Node next : finish) {
	    	   Integer X = next.getPair().getSecond()*this.squareWitdth + this.posX;
    		   Integer Y = next.getPair().getFirst()*this.squareHeight + this.posY;
	    	   draw(g,X, Y, new Color(255, 246, 173), Color.BLACK);  
	    	   g.drawString("EXIT", X + squareWitdth/3, Y + squareHeight/4);
	       }
	}

	    public void draw(Graphics g, Integer posX, Integer posY, Color fill, Color border) {

	    	g.setColor(fill);
	        g.fillRect(posX, posY, squareWitdth, squareHeight);
	        
	        g.setColor(border);
	        g.drawRect(posX, posY, squareWitdth, squareHeight);
	    }

	    
	    public Integer getNumberRows() {
	    	return this.numberRows;
	    }
	    public void setNumberRows(Integer numberRows) {
	    	this.numberRows = numberRows;
	    }
	    
	    public Integer getNumberColumns() {
	    	return this.numberColumns;
	    }
	    
	    public void setNumberColumns(Integer numberColumns) {
	    	this.numberColumns = numberColumns;
	    }
	    
	    public void setSquareWidth(Integer squareWidth) {
	    	this.squareWitdth = squareWidth;
	    }
	    
	    public Integer getSquareWidth() {
	    	return this.squareWitdth;
	    }
	    
	    public Integer getSquareHeight() {
	    	return this.squareHeight;
	    }
	    
	    public void setSquareHeight(Integer squareHeight) {
	    	this.squareHeight = squareHeight;
	    }
	    
	    public Integer getPosX() {
			return posX;
		}

		public void setPosX(Integer posX) {
			this.posX = posX;
		}

		public Integer getPosY() {
			return posY;
		}

		public void setPosY(Integer posY) {
			this.posY = posY;
		}

		//private members:
	    private Integer squareWitdth;
	    private Integer squareHeight;
	    
	    private Integer numberRows;
	    private Integer numberColumns;
	    
	    private Integer posX;
	    private Integer posY;
	    public Graph graph;
}

