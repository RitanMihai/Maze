package structures;
import java.util.*;

import structures.Pair;

public class Graph {
    //Constructors
	public Graph() {
		this.size = 1; 
        
        // define the size of array as  this.size
        // number of vertices 
        adjListArray = new LinkedList[size]; 
          
        // Create a new list for each vertex 
        // such that adjacent nodes can be stored 
        for(int i = 0; i < size ; ++i){ 
            adjListArray[i] = new LinkedList<>(); 
        }
	}  
    public Graph(Integer size) { 
        this.size = size; 
          
        // define the size of array as  
        // number of vertices 
        adjListArray = new LinkedList[size]; 
          
        // Create a new list for each vertex 
        // such that adjacent nodes can be stored 
        for(int i = 0; i < size ; ++i){ 
            adjListArray[i] = new LinkedList<>(); 
        } 
    }    
    
    //Getters
    public Integer getSize() {
    	return this.size;
    }
    public Node getNode(Pair<Integer, Integer> position) {
    	for(int i = 0; i < this.size; ++i) { 
    		  for(Node var: adjListArray[i]) {
    			  if(var.getPair().getFirst() == position.getFirst() && var.getPair().getSecond() == position.getSecond()) {
    				  return var;
    			  }
    		  }
        }
    	return new Node();
    }
    public LinkedList<Node>[] getGraph(){
    	return this.adjListArray;
    }
    // Adds an edge to an undirected graph 
    public void addEdge(Node src, Node dest) 
    { 
        // Add an edge from source to destination.  
        this.adjListArray[src.getNodeNumber()].add(dest); 
    } 
    
    public void printGraph() 
    {        
        for(int i = 0; i < this.size; ++i) 
        { 
        	//System.out.println("("+currentNode.getPair().getFirst()+","+currentNode.getPair().getSecond()+")");
             for(Node varNode: this.adjListArray[i]){ 
                 System.out.print("("+varNode.getPair().getFirst() +","+varNode.getPair().getSecond()+") "); 
            } 
            System.out.println("\n"); 
        } 
    }  
    
    /*
     * An path finding algorithm that find the shortest way from one given node to another node
     */
    public ArrayList<Node>  BFSStartFinish(Node start, Node finish) {
    	 Vector<Boolean> visited = new Vector<Boolean>(this.size); 
    	 ArrayList<Node> prodecessorList = new ArrayList<>(this.size);
    	
    	 //Retain all the nodes from graph in this variable
    	 ArrayList<Node> exactPath = new ArrayList<>(this.size);
    	 
    	 for (int i = 0; i < this.size; i++) {
    		 visited.add(false); 
    		 Pair<Integer, Integer> pair = new Pair<Integer,Integer>(-1,-1);
    		 //Set this value for a Node to mark that a Node is not valid
    		 prodecessorList.add(new Node(-1,-1, pair));
    	 }
 
         Queue<Node> queue = new LinkedList<>();
         
         // Push the current source node 
         queue.add(start);
         Node top = start;
         while(!queue.isEmpty()) 
         { 
             //Retain the top value (first element) of queue in a variable  
        	 //Pop a the top element from queue
             top = queue.peek(); 
             queue.remove(); 
              
             //Predecessors vector
             if(visited.get(top.getNodeNumber()) == false) {
            	 visited.set(top.getNodeNumber(), true); 
             
             }
             
             Iterator<Node> itr = adjListArray[top.getNodeNumber()].iterator(); 
              
             while (itr.hasNext())  
             { 
                 Node currentNode = itr.next(); 
                 if(!visited.get(currentNode.getNodeNumber())) 
                	 queue.add(currentNode); 
                 
                 //Fill the predecessors list
                 Integer currentNodeInPredecesor = prodecessorList.get(currentNode.getNodeNumber()).getNodeValue();
                 if (currentNode != top && currentNodeInPredecesor.equals(-1)) {
                	 prodecessorList.set(currentNode.getNodeNumber(), top);
                	
                 }
                	 
              }
             
         }  
         
         Node next = finish;
         while(next.getNodeNumber() != start.getNodeNumber()) { 
        	 exactPath.add(next);
        	 //System.out.print(" (" + next.getPair().getFirst() + "," + next.getPair().getSecond()+ ") ");
        	 next = prodecessorList.get(next.getNodeNumber());
         }
         //System.out.print(" (" + next.getPair().getFirst() + "," + next.getPair().getSecond()+ ") ");
         exactPath.add(next);
         return exactPath;
     } 
    
    public  ArrayList<Node> BFS() 
    { 
    	 Vector<Boolean> visited = new Vector<Boolean>(this.size); 
    	 ArrayList<Node> prodecessorList = new ArrayList<>(this.size);
    	
    	 //Retain all the nodes from graph in this variable
    	 ArrayList<Node> allNodesList = new ArrayList<>(this.size);
         
    	 for (int i = 0; i < this.size; i++) {
    		 visited.add(false); 
    		 Pair<Integer, Integer> pair = new Pair<Integer,Integer>(-1,-1);
    		
    		 //Set this value for a Node to mark that a Node is not valid
    		 prodecessorList.add(new Node(-1,-1, pair));
    	 }
 
         Queue<Node> queue = new LinkedList<>();
         Node start = adjListArray[0].get(0);
         // Push the current source node 
         queue.add(start);
         Node top = start;
         while(!queue.isEmpty()) 
         { 
             //Retain the top value (first element) of queue in a variable  
        	 //Pop a the top element from queue
             top = queue.peek(); 
             queue.remove(); 
              
             //Predecessors vector
             if(visited.get(top.getNodeNumber()) == false) {
            	 //Add nodes in our node list
            	 allNodesList.add(top);
            	 
            	 //System.out.print(" (" + top.getPair().getFirst() + "," + top.getPair().getSecond()+ ") ");
            	 visited.set(top.getNodeNumber(), true); 
             }
             
            Iterator<Node> itr = adjListArray[top.getNodeNumber()].iterator(); 
              
            while (itr.hasNext())  
            { 
            	 Node currentNode = itr.next(); 
                 if(!visited.get(currentNode.getNodeNumber())) 
                	 queue.add(currentNode); 
                 
                 //Fill the predecessors list
                 Integer currentNodeInPredecesor = prodecessorList.get(currentNode.getNodeNumber()).getNodeValue();
                 if (currentNode != top && currentNodeInPredecesor.equals(-1)) {
                	 prodecessorList.set(currentNode.getNodeNumber(), top);
                 	}
            } 
              
        } 
        return allNodesList;
    } 
    
    public ArrayList<Node> predecesorList(Node start) 
    { 
    	 Vector<Boolean> visited = new Vector<Boolean>(this.size); 
    	 ArrayList<Node> predecesorList = new ArrayList<>(this.size);
    	
    	 //Retain all the nodes from graph in this variable
    	 ArrayList<Node> allNodesList = new ArrayList<>(this.size);
         
    	 for (int i = 0; i < this.size; i++) {
    		 visited.add(false); 
    		 Pair<Integer, Integer> pair = new Pair<Integer,Integer>(-1,-1);
    		 //Set this value for a Node to mark that a Node is not valid
    		 predecesorList.add(new Node(-1,-1, pair));
    	 }
 
         Queue<Node> queue = new LinkedList<>();
         
         // Push the current source node 
         queue.add(start);
         Node top = start;
         while(!queue.isEmpty()) 
         { 
             //Retain the top value (first element) of queue in a variable  
        	 //Pop a the top element from queue
             top = queue.peek(); 
             queue.remove(); 
              
             //Predecessors vector
             if(visited.get(top.getNodeNumber()) == false) {
            	 //Add nodes in our node list
            	 allNodesList.add(top);
            	 
            	 //System.out.print(" (" + top.getPair().getFirst() + "," + top.getPair().getSecond()+ ") ");
            	 visited.set(top.getNodeNumber(), true); 
             }
             
             Iterator<Node> itr = adjListArray[top.getNodeNumber()].iterator(); 
              
            while (itr.hasNext())  
            { 
            	 Node currentNode = itr.next(); 
                 if(!visited.get(currentNode.getNodeNumber())) 
                	 queue.add(currentNode); 
                 
                 //Fill the predecessors list
                 Integer currentNodeInPredecesor = predecesorList.get(currentNode.getNodeNumber()).getNodeValue();
                 if (currentNode != top && currentNodeInPredecesor.equals(-1)) {
                	 predecesorList.set(currentNode.getNodeNumber(), top);
                 	}
            } 
              
        } 
        return predecesorList;
    } 
    
    private Integer size; 
    private LinkedList<Node> adjListArray[]; 
}
