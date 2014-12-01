package game;

import java.util.ArrayList;

public class TetrisMomento {
	ArrayList<Object> objArray;
	ArrayList<Number> numArray;

	TetrisMomento() {
		objArray = new ArrayList<Object>();
		numArray = new ArrayList<Number>();
	}
	
	public boolean empty() {
		return objArray.size() == 0 && numArray.size() == 0;
	}

	public void addObj(Object obj) {
		objArray.add(obj);
	}
	
	public Object removeObj() {
		return objArray.remove(0);
	}

	public void addNum(Number someNum) {
		numArray.add(someNum);
	}
	
	public Number removeNum() {
		return numArray.remove(0);
	}
}