package structures;

public class Pair<Type1, Type2>{
	
	//Default constructor of the class pair
	public Pair (){
	this.first = null;
	this.second = null;
	}
	//Parametric constructor of the class pair
	public Pair(Type1 first, Type2 second ){
		this.first = first;
		this.second = second;
	}
	
	//Getters 
	public Type1 getFirst () {
		return this.first;
	}
	public Type2 getSecond() {
		return this.second;
	}
	
	//Setters
	public void setFirst(Type1 value) {
		this.first = value;
	}
	public void setSecond(Type2 value) {
		this.second = value;
	}
	
	//Private: 
	private Type1 first ;
	private Type2 second;
}