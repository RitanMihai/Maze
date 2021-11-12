package structures;

public class Node {
	//Default constructor
	public Node() {
		this.nodeValue = 0;
		this.nodeNumber = 0;
		this.pair = new Pair<>();
	}
	//Parametric constructor
	public Node(Integer nodeValue, Integer nodeNumber,Pair<Integer,Integer> pair) {
		this.nodeValue = nodeValue;
		this.nodeNumber = nodeNumber;
		this.pair = pair;
		}
	
	//Getters
	public Integer getNodeValue() {
		return this.nodeValue;
	}
	public Integer getNodeNumber() {
		return this.nodeNumber;
	}
	public Pair<Integer,Integer> getPair(){
		return this.pair;
	}
	
	//Setters
	public void setNodeValue(Integer nodeValue) {
		this.nodeValue = nodeValue;
	}
	public void setNodeNumber(Integer nodeNumber) {
		this.nodeNumber = nodeNumber;
	}
	public void setPair(Pair<Integer,Integer> pair) {
		this.pair = pair;
	}
	
	//Private:
	private Integer nodeValue;
	private Integer nodeNumber;
	private Pair<Integer,Integer> pair;
}