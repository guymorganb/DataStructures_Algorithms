package generics;

public class Pair <T> {
	
	// the variable type T is set as generic so any type can be passed to it
	
	private T first;
	private T second;
	
	public Pair(T first, T second) {
		this.first = first;
		this.second = second;
	}
	public T getFirst() {
		return first;
	}
	public void setFirst(T first) {
		this.first = first;
	}
	public T getSecond() {
		return second;
	}
	public void setSecond(T second) {
		this.second = second;
	}
}
