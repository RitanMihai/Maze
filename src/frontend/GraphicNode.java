package frontend;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

import structures.Pair;
 
public class GraphicNode
{    
   
    //Constructors 
    GraphicNode(){ 
        GraphicNodeBorderColor = Color.black;
    }
    GraphicNode(int insideValue, int GraphicNodeX, int GraphicNodeY, int GraphicNodeWidth, int GraphicNodeHeight, Color GraphicNodeBorderColor, JFrame window){ //Constructor
        // X , Y , Width, Height, Border Color, container
        this.setGraphicNodeBorderColor(GraphicNodeBorderColor);
        this.setGraphicNodeWidth(GraphicNodeWidth);
        this.setGraphicNodeHeight(GraphicNodeHeight);
        this.setGraphicNodeX(GraphicNodeX);
        this.setGraphicNodeY(GraphicNodeY);
        this.drawGraphicNode(window);
        this.setInsideValue(insideValue);
    }

    //Getters
    public Color getGraphicNodeFillColor() {
        return this.GraphicNodeFillColor;
    }
    public Color getGraphicNodeBorderColor() {
        return this.GraphicNodeBorderColor;
    }
    public Integer getGraphicNodeX() {
        return this.GraphicNodeX;
    }
    public Integer getGraphicNodeY() {
        return this.GraphicNodeY;
    }
    public int getGraphicNodeWidth() {
        return this.GraphicNodeWidth;
    }
    public Integer getInsideValue() {
    	return this.insideValue;
    }
    public int getGraphicNodeHeight() {
        return GraphicNodeHeight;
    }

    //Setters
    public void setInsideValue(Integer aValue) {
    	this.insideValue = aValue;
    }
    public void setFillGraphicNode(Boolean aBool) {
    	this.fillGraphicNode = aBool;
    }
    public void setGraphicNodeFillColor(Color GraphicNodeFillColor) {
        this.GraphicNodeFillColor = GraphicNodeFillColor;
    }
    public void setGraphicNodeBorderColor(Color GraphicNodeBorderColor) {
        this.GraphicNodeBorderColor = GraphicNodeBorderColor;
    }
    public void setGraphicNodeX(int GraphicNodeX) {
        this.GraphicNodeX = GraphicNodeX;
    }
    public void setGraphicNodeY(int GraphicNodeY) {
        this.GraphicNodeY = GraphicNodeY;
    }
    public void setGraphicNodeWidth(int GraphicNodeWidth) {
        this.GraphicNodeWidth = GraphicNodeWidth;
    }
    public void setGraphicNodeHeight(int GraphicNodeHeight) {
        this.GraphicNodeHeight = GraphicNodeHeight;
    }
 
    //Draw Functions
    public void drawGraphicNode(JFrame frame) 
    {
        frame.getContentPane().add(new MyComponent());
    }
 
    public static void drawMultipleGraphicNodes(JFrame frame, ArrayList<GraphicNode> GraphicsNodes){
    	GraphicNodeArray = GraphicsNodes;
        frame.getContentPane().add(new MyComponents());
    }
    public static void drawArc(JFrame frame, ArrayList< Pair <Pair<Integer,Integer>, Pair<Integer,Integer> > >  aArcsArray ){
    	ArcsArray = aArcsArray;
        frame.getContentPane().add(new MyArc());
    }
    public static void drawCompleteGraph(JFrame frame, ArrayList<GraphicNode> GraphicsNodes, ArrayList< Pair <Pair<Integer,Integer>, Pair<Integer,Integer> > >  aArcsArray ){   	
    	GraphicNodeArray = GraphicsNodes;
    	ArcsArray = aArcsArray;
        frame.getContentPane().add(new FullGraph());
    }

    
    //Drawing one Node
    private class MyComponent extends JComponent{
        public void paintComponent(Graphics g){
             
            if (fillGraphicNode) //Fill first, and then draw outline.
            {
                g.setColor(GraphicNodeFillColor);
                g.fillOval(getGraphicNodeX(),getGraphicNodeY(), getGraphicNodeHeight(),getGraphicNodeWidth());
            }
             
            g.setColor(getGraphicNodeBorderColor());
            g.drawOval(getGraphicNodeX(),getGraphicNodeY(), getGraphicNodeHeight(),getGraphicNodeWidth());
             
        }
    }   
    //Drawing only Nodes
    private static class MyComponents extends JComponent{
        public void paintComponent(Graphics g){
             
            for (int i = 0; i < GraphicNodeArray.size(); i++)
            {
                if (GraphicNodeArray.get(i).fillGraphicNode) //Fill first, and then draw outline.
                {
                    g.setColor(GraphicNodeArray.get(i).GraphicNodeFillColor);
                    g.fillOval(GraphicNodeArray.get(i).getGraphicNodeX(),GraphicNodeArray.get(i).getGraphicNodeY(), GraphicNodeArray.get(i).getGraphicNodeHeight(),GraphicNodeArray.get(i).getGraphicNodeWidth());
                }
                g.setColor( GraphicNodeArray.get(i).getGraphicNodeBorderColor() );
                g.drawOval( GraphicNodeArray.get(i).getGraphicNodeX(),GraphicNodeArray.get(i).getGraphicNodeY(), GraphicNodeArray.get(i).getGraphicNodeHeight(),GraphicNodeArray.get(i).getGraphicNodeWidth());            
                g.drawString( GraphicNodeArray.get(i).getInsideValue().toString() ,GraphicNodeArray.get(i).getGraphicNodeX()+8, GraphicNodeArray.get(i).getGraphicNodeY()+20);               
            }
        }
    }
    //Drawing only Arcs
    private static class MyArc extends JComponent{
    	public void paint(Graphics g){
    		
    		super.paint(g);  // fixes the immediate problem.
    	       Graphics2D g2 = (Graphics2D) g;
    	       
    		for (int i = 0; i < ArcsArray.size(); i++) {
    			Integer x1 = ArcsArray.get(i).getFirst().getFirst();
    			Integer y1 = ArcsArray.get(i).getFirst().getSecond();
    			Integer x2 = ArcsArray.get(i).getSecond().getFirst();
    			Integer y2 = ArcsArray.get(i).getSecond().getSecond();
    
    			Line2D lin = new Line2D.Float(x1,y1,x2,y2);
  
    			g2.draw(lin);		
    		}
    	}
   }
    //Draw Full Graph  
    private static class FullGraph extends JComponent{
    	
    	public void paintComponent(Graphics g){
    		
    		for (int i = 0; i < ArcsArray.size(); i++) {
    			Integer x1 = ArcsArray.get(i).getFirst().getFirst() + 8;
    			Integer y1 = ArcsArray.get(i).getFirst().getSecond() + 20;
    			Integer x2 = ArcsArray.get(i).getSecond().getFirst() + 8;
    			Integer y2 = ArcsArray.get(i).getSecond().getSecond() + 20;
    
    			//Line2D lin = new Line2D.Float(x1,y1,x2,y2);
  
    			g.drawLine(x1,y1,x2,y2);		
    		}
    		
        	for (int i = 0; i < GraphicNodeArray.size(); i++)
            {
                if (GraphicNodeArray.get(i).fillGraphicNode) //Fill first, and then draw outline.
                {
                    g.setColor(GraphicNodeArray.get(i).GraphicNodeFillColor);
                    g.fillOval(GraphicNodeArray.get(i).getGraphicNodeX(),GraphicNodeArray.get(i).getGraphicNodeY(), GraphicNodeArray.get(i).getGraphicNodeHeight(),GraphicNodeArray.get(i).getGraphicNodeWidth());
                }
                g.setColor( GraphicNodeArray.get(i).getGraphicNodeBorderColor() );
                g.drawOval( GraphicNodeArray.get(i).getGraphicNodeX(),GraphicNodeArray.get(i).getGraphicNodeY(), GraphicNodeArray.get(i).getGraphicNodeHeight(),GraphicNodeArray.get(i).getGraphicNodeWidth());            
                g.drawString( GraphicNodeArray.get(i).getInsideValue().toString() ,GraphicNodeArray.get(i).getGraphicNodeX()+8, GraphicNodeArray.get(i).getGraphicNodeY()+20);               
            }
        	
        }
  
    }
    
    //Private members:
    
    //Colors
    private Color GraphicNodeFillColor;
    private Color GraphicNodeBorderColor;
    
    //Position
    private Integer GraphicNodeX = 0;
    private Integer GraphicNodeY = 0;
    
    //Size
    private int GraphicNodeWidth = 0;
    private int GraphicNodeHeight = 0; 
 
    //The number that are inside of my node
    private Integer insideValue = 0;
    
    
    public boolean fillGraphicNode = false;
    
    //Required for multiple components drawing
    private static ArrayList<GraphicNode> GraphicNodeArray; 
    private static ArrayList< Pair <Pair<Integer,Integer>, Pair<Integer,Integer> > > ArcsArray;
    
}